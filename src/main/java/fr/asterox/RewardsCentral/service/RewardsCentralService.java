package fr.asterox.RewardsCentral.service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.asterox.RewardsCentral.dto.AttractionDTO;
import fr.asterox.RewardsCentral.dto.LocationDTO;
import fr.asterox.RewardsCentral.dto.UserRewardDTO;
import fr.asterox.RewardsCentral.dto.VisitedLocationDTO;
import fr.asterox.RewardsCentral.proxy.UserManagementProxy;
import rewardCentral.RewardCentral;

@Service
public class RewardsCentralService implements IRewardsCentralService {
	@Autowired
	private RewardCentral rewardsCentral;
	@Autowired
	UserManagementProxy userManagementProxy;
	@Autowired
	AttractionService attractionService;

	private Logger logger = LoggerFactory.getLogger(RewardsCentralService.class);
	private static final double STATUTE_MILES_PER_NAUTICAL_MILE = 1.15077945;
	private ExecutorService executor = Executors.newWorkStealingPool();

	// proximity in miles
	private int defaultProximityBuffer = 10000;
	private int proximityBuffer = defaultProximityBuffer;

	public RewardsCentralService() {
		super();
	}

	public void setProximityBuffer(int proximityBuffer) {
		this.proximityBuffer = proximityBuffer;
	}

	public void setDefaultProximityBuffer() {
		proximityBuffer = defaultProximityBuffer;
	}

	@Override
	public void calculateRewards(String username) {
		executor.submit(() -> this.calculateAndAddRewards(username));
	}

	@Override
	public void calculateAndAddRewards(String username) {
		List<VisitedLocationDTO> userLocations = userManagementProxy.getVisitedLocations(username);
		List<UserRewardDTO> userRewards = userManagementProxy.getUserRewards(username);
		logger.debug("calculating rewards for user :" + username);

		for (VisitedLocationDTO visitedLocation : userLocations) {
			for (AttractionDTO attraction : attractionService.getAttractionsList()) {
				if (userRewards.stream().filter(r -> r.attraction.attractionName.equals(attraction.attractionName))
						.count() == 0) {
					if (nearAttraction(visitedLocation, attraction)) {
						userManagementProxy.addReward(username, new UserRewardDTO(visitedLocation, attraction,
								getRewardPoints(attraction, userManagementProxy.getUserId(username))));

					}
				}
			}
		}
	}

	@Override
	public int getAttractionRewardPoints(UUID attractionId, UUID userId) {
		return rewardsCentral.getAttractionRewardPoints(attractionId, userId);
	}

	private boolean nearAttraction(VisitedLocationDTO visitedLocation, AttractionDTO attraction) {
		return getDistance(visitedLocation.location, attraction) > proximityBuffer ? false : true;
	}

	private int getRewardPoints(AttractionDTO attraction, UUID userId) {
		return rewardsCentral.getAttractionRewardPoints(attraction.attractionId, userId);
	}

	public double getDistance(LocationDTO loc1, LocationDTO loc2) {
		double lat1 = Math.toRadians(loc1.latitude);
		double lon1 = Math.toRadians(loc1.longitude);
		double lat2 = Math.toRadians(loc2.latitude);
		double lon2 = Math.toRadians(loc2.longitude);

		double angle = Math
				.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2));

		double nauticalMiles = 60 * Math.toDegrees(angle);
		double statuteMiles = STATUTE_MILES_PER_NAUTICAL_MILE * nauticalMiles;
		return statuteMiles;
	}

}

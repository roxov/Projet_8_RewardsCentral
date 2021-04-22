package fr.asterox.RewardsCentral.service;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import fr.asterox.RewardsCentral.dto.LocationDTO;
import fr.asterox.RewardsCentral.dto.UserRewardDTO;
import fr.asterox.RewardsCentral.dto.VisitedLocationDTO;
import fr.asterox.RewardsCentral.proxy.UserManagementProxy;
import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;
import rewardCentral.RewardCentral;

@ExtendWith(MockitoExtension.class)
public class RewardsCentralServiceTest {
	@Mock
	UserManagementProxy userManagementProxy;

	@Mock
	RewardCentral rewardsCentral;

	@Mock
	GpsUtil gpsUtil;

	@InjectMocks
	RewardsCentralService rewardsCentralService;

	@Test
	public void givenNearUserLocationAndAttraction_whenCalculateRewards_thenReturnCalculation() {
		// GIVEN
		VisitedLocationDTO visitedLocation = new VisitedLocationDTO(new LocationDTO(144, 155), new Date(),
				UUID.fromString("329e4bf3-ee62-4a67-b7d7-b0dc06989c6e"));
		List<VisitedLocationDTO> userLocations = new ArrayList<>();
		userLocations.add(visitedLocation);

		Attraction attraction = new Attraction("name", "city", "state", 144, 155);
		// Attraction attraction2 = new Attraction("name2", "city", "state", 144, 155);
		UserRewardDTO userReward = new UserRewardDTO(visitedLocation, attraction);
		List<UserRewardDTO> userRewards = new ArrayList<>();
		// userRewards.add(userReward);

		when(gpsUtil.getAttractions()).thenReturn(List.of(attraction));
		when(userManagementProxy.getUserId("jo")).thenReturn(UUID.fromString("329e4bf3-ee62-4a67-b7d7-b0dc06989c6e"));
		doNothing().when(userManagementProxy).addReward("jo", userReward);

		// WHEN
		rewardsCentralService.calculateRewards(userLocations, userRewards, "jo");

		// THEN
		verify(gpsUtil, Mockito.times(1)).getAttractions();
		verify(userManagementProxy, Mockito.times(1)).getUserId(anyString());
		verify(userManagementProxy, Mockito.times(1)).addReward("jo", userReward);
	}

	@Test
	public void givenAttractionAlreadyExistingInUserRewards_whenCalculateRewards_thenReturnCalculation() {
		// GIVEN
		VisitedLocationDTO visitedLocation = new VisitedLocationDTO(new LocationDTO(144, 155), new Date(),
				UUID.fromString("329e4bf3-ee62-4a67-b7d7-b0dc06989c6e"));
		List<VisitedLocationDTO> userLocations = new ArrayList<>();
		userLocations.add(visitedLocation);

		Attraction attraction = new Attraction("name", "city", "state", 144, 155);
		UserRewardDTO userReward = new UserRewardDTO(visitedLocation, attraction);
		List<UserRewardDTO> userRewards = new ArrayList<>();
		userRewards.add(userReward);

		when(gpsUtil.getAttractions()).thenReturn(List.of(attraction));
		lenient().when(userManagementProxy.getUserId("jo")).thenThrow(RuntimeException.class);

		// WHEN
		rewardsCentralService.calculateRewards(userLocations, userRewards, "jo");

		// THEN
		verify(gpsUtil, Mockito.times(1)).getAttractions();
	}

}

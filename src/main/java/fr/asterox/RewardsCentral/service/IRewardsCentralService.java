package fr.asterox.RewardsCentral.service;

import java.util.List;

import fr.asterox.RewardsCentral.dto.UserRewardDTO;
import fr.asterox.RewardsCentral.dto.VisitedLocationDTO;

/**
 * 
 * Microservice calculating the rewards based on users' visited locations.
 *
 */
public interface IRewardsCentralService {
	public void calculateRewards(List<VisitedLocationDTO> userLocations, List<UserRewardDTO> userRewards, String userName);
}

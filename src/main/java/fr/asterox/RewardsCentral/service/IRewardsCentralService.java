package fr.asterox.RewardsCentral.service;

import java.util.UUID;

/**
 * 
 * Microservice calculating the rewards based on users' visited locations.
 *
 */
public interface IRewardsCentralService {
	public void calculateRewards(String userName);

	public void calculateAndAddRewards(String userName);

	public int getAttractionRewardPoints(UUID attractionId, UUID userId);
}

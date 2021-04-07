package fr.asterox.RewardsCentral.dto;

import gpsUtil.location.Attraction;

public class UserRewardDTO {

	public final VisitedLocationDTO visitedLocation;
	public final Attraction attraction;
	private int rewardPoints;

	public UserRewardDTO(VisitedLocationDTO visitedLocation, Attraction attraction, int rewardPoints) {
		this.visitedLocation = visitedLocation;
		this.attraction = attraction;
		this.rewardPoints = rewardPoints;
	}

	public UserRewardDTO(VisitedLocationDTO visitedLocation, Attraction attraction) {
		this.visitedLocation = visitedLocation;
		this.attraction = attraction;
	}

	public void setRewardPoints(int rewardPoints) {
		this.rewardPoints = rewardPoints;
	}

	public int getRewardPoints() {
		return rewardPoints;
	}

}

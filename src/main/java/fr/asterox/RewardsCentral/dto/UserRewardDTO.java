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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attraction == null) ? 0 : attraction.hashCode());
		result = prime * result + rewardPoints;
		result = prime * result + ((visitedLocation == null) ? 0 : visitedLocation.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRewardDTO other = (UserRewardDTO) obj;
		if (attraction == null) {
			if (other.attraction != null)
				return false;
		} else if (!attraction.equals(other.attraction))
			return false;
		if (rewardPoints != other.rewardPoints)
			return false;
		if (visitedLocation == null) {
			if (other.visitedLocation != null)
				return false;
		} else if (!visitedLocation.equals(other.visitedLocation))
			return false;
		return true;
	}

}

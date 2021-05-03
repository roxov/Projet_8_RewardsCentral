package fr.asterox.RewardsCentral.dto;

public class UserRewardDTO {

	public VisitedLocationDTO visitedLocation;
	public AttractionDTO attraction;
	private int rewardPoints;

	public UserRewardDTO() {
		super();
	}

	public UserRewardDTO(VisitedLocationDTO visitedLocation, AttractionDTO attraction, int rewardPoints) {
		this.visitedLocation = visitedLocation;
		this.attraction = attraction;
		this.rewardPoints = rewardPoints;
	}

	public UserRewardDTO(VisitedLocationDTO visitedLocation, AttractionDTO attraction) {
		this.visitedLocation = visitedLocation;
		this.attraction = attraction;
	}

	public void setRewardPoints(int rewardPoints) {
		this.rewardPoints = rewardPoints;
	}

	public int getRewardPoints() {
		return rewardPoints;
	}

	public VisitedLocationDTO getVisitedLocation() {
		return visitedLocation;
	}

	public void setVisitedLocation(VisitedLocationDTO visitedLocation) {
		this.visitedLocation = visitedLocation;
	}

	public AttractionDTO getAttraction() {
		return attraction;
	}

	public void setAttraction(AttractionDTO attraction) {
		this.attraction = attraction;
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

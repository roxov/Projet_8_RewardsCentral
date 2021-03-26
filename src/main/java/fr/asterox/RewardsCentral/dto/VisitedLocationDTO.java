package fr.asterox.RewardsCentral.dto;

import java.util.Date;
import java.util.UUID;

public class VisitedLocationDTO {
	public final LocationDTO location;
	public final Date timeVisited;
	public final UUID userId;

	public VisitedLocationDTO(LocationDTO location, Date timeVisited, UUID userId) {
		super();
		this.location = location;
		this.timeVisited = timeVisited;
		this.userId = userId;
	}

	public LocationDTO getLocation() {
		return location;
	}

	public Date getTimeVisited() {
		return timeVisited;
	}

	public UUID getUserId() {
		return userId;
	}

}

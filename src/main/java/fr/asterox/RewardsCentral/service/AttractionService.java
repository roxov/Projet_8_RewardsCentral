package fr.asterox.RewardsCentral.service;

import java.util.List;

import fr.asterox.RewardsCentral.dto.AttractionDTO;

public class AttractionService {

	private List<AttractionDTO> attractionsList;

	public AttractionService(List<AttractionDTO> attractionsList) {
		super();
		this.attractionsList = attractionsList;
	}

	public List<AttractionDTO> getAttractionsList() {
		return attractionsList;
	}
}

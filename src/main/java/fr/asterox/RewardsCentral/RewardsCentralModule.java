package fr.asterox.RewardsCentral;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fr.asterox.RewardsCentral.dto.AttractionDTO;
import fr.asterox.RewardsCentral.service.AttractionService;
import gpsUtil.GpsUtil;
import rewardCentral.RewardCentral;

@Configuration
public class RewardsCentralModule {

	@Bean
	public GpsUtil getGpsUtil() {
		return new GpsUtil();
	}

	@Bean
	public RewardCentral getRewardCentral() {
		return new RewardCentral();
	}

	@Bean
	public AttractionService getAttractionService() {
		List<AttractionDTO> attractionsDTOList = new GpsUtil().getAttractions().stream().map(a -> new AttractionDTO(a))
				.collect(Collectors.toList());
		return new AttractionService(attractionsDTOList);
	}

}

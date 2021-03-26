package fr.asterox.RewardsCentral;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

}

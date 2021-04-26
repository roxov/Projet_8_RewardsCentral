package fr.asterox.RewardsCentral.controller;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.asterox.RewardsCentral.proxy.UserManagementProxy;
import fr.asterox.RewardsCentral.service.RewardsCentralService;

@RestController
public class RewardsCentralController {

	@Autowired
	RewardsCentralService rewardsCentralService;

	@Autowired
	UserManagementProxy userManagementProxy;

	private Logger logger = LoggerFactory.getLogger(RewardsCentralController.class);

	@PutMapping("/calculateRewards")
	public void calculateRewards(@RequestParam @NotNull(message = "username is compulsory") String userName) {
		logger.debug("calculating rewards of user :" + userName);
		rewardsCentralService.calculateRewards(userManagementProxy.getVisitedLocations(userName),
				userManagementProxy.getUserRewards(userName), userName);
	}

	@PutMapping("/testCalculateRewards")
	public void testCalculateRewards(@RequestParam @NotNull(message = "username is compulsory") String userName) {
		logger.debug("calculating rewards for tests");
		rewardsCentralService.calculateAndAddRewards(userManagementProxy.getVisitedLocations(userName),
				userManagementProxy.getUserRewards(userName), userName);
	}

}

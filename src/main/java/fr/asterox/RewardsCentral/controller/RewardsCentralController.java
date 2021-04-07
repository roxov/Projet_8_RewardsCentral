package fr.asterox.RewardsCentral.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.asterox.RewardsCentral.service.RewardsCentralService;

@RestController
public class RewardsCentralController {

	@Autowired
	RewardsCentralService rewardsCentralService;

	@Autowired
	UserManagementController userManagementController;

	private Logger logger = LoggerFactory.getLogger(RewardsCentralController.class);

	@RequestMapping("/calculateRewards")
	public void calculateRewards(@RequestParam String userName) {
		logger.debug("calculating rewards of user :" + userName);
		rewardsCentralService.calculateRewards(userManagementController.getVisitedLocations(userName),
				userManagementController.getRewards(userName), userName);
	}

}

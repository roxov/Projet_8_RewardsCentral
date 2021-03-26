package fr.asterox.RewardsCentral.controller;

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

	@RequestMapping("/calculateRewards")
	public void calculateRewards(@RequestParam String userName) {
		rewardsCentralService.calculateRewards(userManagementController.getVisitedLocations(userName),
				userManagementController.getRewards(userName), userName);
	}

}

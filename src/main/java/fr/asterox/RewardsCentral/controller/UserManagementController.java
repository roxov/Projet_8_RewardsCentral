package fr.asterox.RewardsCentral.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.asterox.RewardsCentral.dto.UserReward;
import fr.asterox.RewardsCentral.dto.VisitedLocationDTO;
import fr.asterox.RewardsCentral.proxy.UserManagementProxy;

@RestController
public class UserManagementController {
	@Autowired
	UserManagementProxy userManagementProxy;

	@RequestMapping("/getUserId")
	public UUID getUserId(@RequestParam String userName) {
		return userManagementProxy.getUserId(userName);
	}

	@RequestMapping("/getVisitedLocations")
	public List<VisitedLocationDTO> getVisitedLocations(@RequestParam String userName) {
		return userManagementProxy.getVisitedLocations(userName);
	}

	@RequestMapping("/getRewards")
	public List<UserReward> getRewards(@RequestParam String userName) {
		return userManagementProxy.getRewards(userName);
	}

	@RequestMapping("/addRewards")
	public void addUserReward(@RequestParam String userName, @RequestBody UserReward userReward) {
		userManagementProxy.addUserReward(userName, userReward);
	}
}

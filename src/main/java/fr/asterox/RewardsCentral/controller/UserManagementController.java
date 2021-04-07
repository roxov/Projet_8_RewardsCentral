package fr.asterox.RewardsCentral.controller;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.asterox.RewardsCentral.dto.UserRewardDTO;
import fr.asterox.RewardsCentral.dto.VisitedLocationDTO;
import fr.asterox.RewardsCentral.proxy.UserManagementProxy;

@RestController
public class UserManagementController {
	@Autowired
	UserManagementProxy userManagementProxy;

	private Logger logger = LoggerFactory.getLogger(UserManagementController.class);

	@RequestMapping("/getUserId")
	public UUID getUserId(@RequestParam String userName) {
		logger.debug("sending request to UserManagement microservice to get userId of user :" + userName);
		return userManagementProxy.getUserId(userName);
	}

	@RequestMapping("/getVisitedLocations")
	public List<VisitedLocationDTO> getVisitedLocations(@RequestParam String userName) {
		logger.debug("sending request to UserManagement microservice to get the list of locatiosn visited by user :"
				+ userName);
		return userManagementProxy.getVisitedLocations(userName);
	}

	@RequestMapping("/getUserRewards")
	public List<UserRewardDTO> getRewards(@RequestParam String userName) {
		logger.debug("sending request to UserManagement microservice to get rewards of user :" + userName);
		return userManagementProxy.getUserRewards(userName);
	}

	@RequestMapping("/addReward")
	public void addReward(@RequestParam String userName, @RequestBody UserRewardDTO userReward) {
		logger.debug("sending request to UserManagement microservice to add rewards to user :" + userName);
		userManagementProxy.addReward(userName, userReward);
	}
}

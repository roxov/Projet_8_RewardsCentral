package fr.asterox.RewardsCentral.proxy;

import java.util.List;
import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.asterox.RewardsCentral.dto.UserRewardDTO;
import fr.asterox.RewardsCentral.dto.VisitedLocationDTO;

@FeignClient(name = "UserManagement", url = "localhost:9001")
public interface UserManagementProxy {

	@RequestMapping("/getUserId")
	public UUID getUserId(@RequestParam String userName);

	@RequestMapping("/getVisitedLocations")
	public List<VisitedLocationDTO> getVisitedLocations(@RequestParam String userName);

	@RequestMapping("/getUserRewards")
	public List<UserRewardDTO> getUserRewards(@RequestParam String userName);

	@RequestMapping("/addReward")
	public void addReward(@RequestParam String userName, @RequestBody UserRewardDTO userReward);

}

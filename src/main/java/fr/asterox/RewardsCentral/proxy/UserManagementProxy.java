package fr.asterox.RewardsCentral.proxy;

import java.util.List;
import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import fr.asterox.RewardsCentral.dto.UserRewardDTO;
import fr.asterox.RewardsCentral.dto.VisitedLocationDTO;

@FeignClient(name = "UserManagement", url = "localhost:9001")
public interface UserManagementProxy {

	@GetMapping("/getUserId")
	public UUID getUserId(@RequestParam String userName);

	@GetMapping("/getVisitedLocations")
	public List<VisitedLocationDTO> getVisitedLocations(@RequestParam String userName);

	@GetMapping("/getUserRewards")
	public List<UserRewardDTO> getUserRewards(@RequestParam String userName);

	@PutMapping("/addReward")
	public void addReward(@RequestParam String userName, @RequestBody UserRewardDTO userReward);

}

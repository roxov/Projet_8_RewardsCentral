package fr.asterox.RewardsCentral.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import fr.asterox.RewardsCentral.dto.AttractionDTO;
import fr.asterox.RewardsCentral.dto.LocationDTO;
import fr.asterox.RewardsCentral.dto.UserRewardDTO;
import fr.asterox.RewardsCentral.dto.VisitedLocationDTO;
import fr.asterox.RewardsCentral.proxy.UserManagementProxy;
import rewardCentral.RewardCentral;

@ExtendWith(MockitoExtension.class)
public class RewardsCentralServiceTest {
	@Mock
	UserManagementProxy userManagementProxy;

	@Mock
	RewardCentral rewardsCentral;

	@Mock
	AttractionService attractionService;

	@InjectMocks
	RewardsCentralService rewardsCentralService;

	@Test
	public void givenNearUserLocationAndAttraction_whenCalculateRewards_thenReturnCalculation()
			throws InterruptedException {
		// GIVEN
		VisitedLocationDTO visitedLocation = new VisitedLocationDTO(new LocationDTO(144, 155), new Date(),
				UUID.fromString("329e4bf3-ee62-4a67-b7d7-b0dc06989c6e"));
		List<VisitedLocationDTO> userLocations = List.of(visitedLocation);

		AttractionDTO attraction = new AttractionDTO("name", "city", "state", 143, 155);
		List<UserRewardDTO> userRewards = Collections.emptyList();

		when(attractionService.getAttractionsList()).thenReturn(List.of(attraction));
		when(userManagementProxy.getVisitedLocations("jo")).thenReturn(userLocations);
		when(userManagementProxy.getUserRewards("jo")).thenReturn(userRewards);
		when(userManagementProxy.getUserId("jo")).thenReturn(UUID.fromString("329e4bf3-ee62-4a67-b7d7-b0dc06989c6e"));
		doNothing().when(userManagementProxy).addReward(anyString(), any(UserRewardDTO.class));

		// WHEN
		rewardsCentralService.calculateRewards("jo");
		Thread.sleep(1000);

		// THEN
		verify(userManagementProxy, Mockito.times(1)).getVisitedLocations(anyString());
		verify(userManagementProxy, Mockito.times(1)).getUserId(anyString());
		verify(userManagementProxy, Mockito.times(1)).addReward(anyString(), any(UserRewardDTO.class));
	}

	@Test
	public void givenAttractionAlreadyExistingInUserRewards_whenCalculateRewards_thenReturnCalculation()
			throws InterruptedException {
		// GIVEN
		VisitedLocationDTO visitedLocation = new VisitedLocationDTO(new LocationDTO(144, 155), new Date(),
				UUID.fromString("329e4bf3-ee62-4a67-b7d7-b0dc06989c6e"));
		List<VisitedLocationDTO> userLocations = List.of(visitedLocation);

		AttractionDTO attraction = new AttractionDTO("name", "city", "state", 144, 155);
		UserRewardDTO userReward = new UserRewardDTO(visitedLocation, attraction);
		List<UserRewardDTO> userRewards = List.of(userReward);

		when(userManagementProxy.getVisitedLocations("jo")).thenReturn(userLocations);
		when(userManagementProxy.getUserRewards("jo")).thenReturn(userRewards);
		lenient().when(userManagementProxy.getUserId("jo")).thenThrow(RuntimeException.class);

		// WHEN
		rewardsCentralService.calculateRewards("jo");
		Thread.sleep(1000);

		// THEN
		verify(userManagementProxy, Mockito.times(1)).getVisitedLocations(anyString());
		verify(userManagementProxy, Mockito.times(1)).getUserRewards(anyString());
		verify(userManagementProxy, Mockito.times(0)).getUserId("jo");
	}

	@Test
	public void givenUUIDForAttractionAndUser_whenGetAttractionRewardsPoints_thenReturnRewardsPoints() {
		// GIVEN
		UUID attractionId = UUID.fromString("329e4bf3-ee62-4a67-b7d7-b0dc06989c6e");
		UUID userId = UUID.fromString("333e4bf3-ee62-4a67-b7d7-b0dc06989c6e");
		when(rewardsCentral.getAttractionRewardPoints(attractionId, userId)).thenReturn(105);

		// WHEN
		int result = rewardsCentral.getAttractionRewardPoints(attractionId, userId);

		// THEN
		assertEquals(105, result);
	}

}

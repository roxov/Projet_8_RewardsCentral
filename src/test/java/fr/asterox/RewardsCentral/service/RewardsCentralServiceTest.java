package fr.asterox.RewardsCentral.service;

public class RewardsCentralServiceTest {

	// Needs fixed - can throw ConcurrentModificationException
//	@Test
//	public void nearAllAttractions() {
//		GpsUtil gpsUtil = new GpsUtil();
//		RewardsService rewardsService = new RewardsService(gpsUtil, new RewardCentral());
//		rewardsService.setProximityBuffer(Integer.MAX_VALUE);
//
//		InternalTestHelper.setInternalUserNumber(1);
//		TourGuideService tourGuideService = new TourGuideService(gpsUtil, rewardsService);
//
//		rewardsService.calculateRewards(tourGuideService.getAllUsers().get(0));
//		List<UserReward> userRewards = tourGuideService.getUserRewards(tourGuideService.getAllUsers().get(0));
//		tourGuideService.tracker.stopTracking();
//
//		assertEquals(gpsUtil.getAttractions().size(), userRewards.size());
//	}

}

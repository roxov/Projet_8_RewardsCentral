package fr.asterox.RewardsCentral.IT;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RewardsCentralIT {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void givenAUsername_whenCalculateRewards_thenReturn200() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.put("/calculateRewards?userName={userName}", "jo")
				.accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void givenUserAndAttractionUUID_whenGetAttractionRewardPoints_thenReturn200() throws Exception {
		UUID attractionId = UUID.fromString("329e4bf3-ee62-4a67-b7d7-b0dc06989c6e");
		UUID userId = UUID.fromString("333e4bf3-ee62-4a67-b7d7-b0dc06989c6e");
		mockMvc.perform(MockMvcRequestBuilders
				.get("/getAttractionRewardPoints?attractionId={userName}&userId={userId}", attractionId, userId)
				.accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
	}

}

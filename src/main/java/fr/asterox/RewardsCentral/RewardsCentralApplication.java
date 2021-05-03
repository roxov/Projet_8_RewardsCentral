package fr.asterox.RewardsCentral;

import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("fr.asterox")
public class RewardsCentralApplication {
	private static final Logger LOGGER = LogManager.getLogger(RewardsCentralApplication.class);

	public static void main(String[] args) {
		LOGGER.info("Initializing RewardsCentral");
		Locale.setDefault(Locale.US);
		SpringApplication.run(RewardsCentralApplication.class, args);
	}

}

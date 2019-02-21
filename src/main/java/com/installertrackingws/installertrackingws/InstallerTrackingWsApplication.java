package com.installertrackingws.installertrackingws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class InstallerTrackingWsApplication {

	// test
	public static void main(String[] args) {
		SpringApplication.run(InstallerTrackingWsApplication.class, args);
	}

}

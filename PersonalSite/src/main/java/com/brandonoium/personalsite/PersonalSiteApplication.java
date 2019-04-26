package com.brandonoium.personalsite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class PersonalSiteApplication
{

	public static void main(String[] args) {
		SpringApplication.run(PersonalSiteApplication.class, args);
	}

}


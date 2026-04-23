package com.artconnect.artistservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ArtistServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArtistServiceApplication.class, args);
	}
}

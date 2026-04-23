package com.artconnect.artistservice.dto;

import lombok.Data;

@Data
public class ArtistRequestDTO {

	private String username;
	private String email;
	private String password;
	private String contact;
	private String city;
	private String bio;
	private String artistType;
}

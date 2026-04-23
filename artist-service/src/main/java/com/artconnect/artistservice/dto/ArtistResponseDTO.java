package com.artconnect.artistservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArtistResponseDTO {

	private Long id;
	private String username;
	private String email;
	private String contact;
	private String city;
	private String bio;
	private String artistType;
}

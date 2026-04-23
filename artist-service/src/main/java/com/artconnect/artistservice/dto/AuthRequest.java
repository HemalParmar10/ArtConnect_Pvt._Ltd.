package com.artconnect.artistservice.dto;

import lombok.Data;

@Data
public class AuthRequest {
	private String email;
	private String password;
}

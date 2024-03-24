package com.dsp.app.command.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginCommandResp {
	private String token;
	private String tokenType = "Bearer";
	private String username;
	private String role;
	
	public LoginCommandResp(String token, String username, String role) {
		this.token = token;
		this.username = username;
		this.role = role;
	}
}

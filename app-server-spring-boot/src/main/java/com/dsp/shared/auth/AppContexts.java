package com.dsp.shared.auth;

import org.springframework.security.core.userdetails.UserDetails;

public class AppContexts {
	
	public static String getUser(JwtTokenProvider jwtTokenProvider) throws Exception {
		UserDetails principal = jwtTokenProvider.getPrincipalFromJwtToken();
		if (principal == null) {
			throw new Exception();
		}
		String userId = principal.getUsername();
		return userId;
	}

}

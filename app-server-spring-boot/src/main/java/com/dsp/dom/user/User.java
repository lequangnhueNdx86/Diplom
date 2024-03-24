package com.dsp.dom.user;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class User {
	public String id;
	private String userName;
	private String password;
	private boolean removed;
	private Role role;
	public User(String userName, String password, boolean removed, Role role) {
		this.userName = userName;
		this.password = password;
		this.removed = removed;
		this.role = role;
	}
	
	public void encodePassword(PasswordEncoder passwordEncoder) {
		this.password = passwordEncoder.encode(password);
	}

	
	
}

package com.dsp.dom.user;

public enum Role {
	Lecturer(0), Student(1);
	public int value;

	private Role(int value) {
		this.value = value;
	}
}

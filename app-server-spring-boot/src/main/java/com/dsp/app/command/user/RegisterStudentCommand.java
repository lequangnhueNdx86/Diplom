package com.dsp.app.command.user;

import com.dsp.dom.user.Role;
import com.dsp.dom.user.Student;
import com.dsp.dom.user.User;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RegisterStudentCommand {
	private String userName;
	private String password;
	private String studentCode;
	
	public Student toDomain() {
		return new Student(new User(userName, password, false, Role.Student), studentCode);
	}

}

package com.dsp.app.command.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsp.dom.user.StudentRepository;
import com.dsp.dom.user.UserRepository;
import com.dsp.shared.base.CommandHandler;
@Service
public class RegisterStudentCommandHandler extends CommandHandler<RegisterStudentCommand>{

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void handle(RegisterStudentCommand command) throws Exception {
		if (userRepository.getByUserName(command.getUserName()).isPresent()) {
			throw new Exception("Username already exists");
		}
		if (studentRepository.getByStudentCode(command.getStudentCode()).isPresent()) {
			throw new Exception("Student code already exists");
		}
		studentRepository.insert(command.toDomain());
	}

}

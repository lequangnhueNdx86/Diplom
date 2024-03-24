package com.dsp.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dsp.app.command.user.LoginCommand;
import com.dsp.app.command.user.LoginCommandHandler;
import com.dsp.app.command.user.LoginCommandResp;
import com.dsp.app.command.user.RegisterStudentBatchService;
import com.dsp.app.command.user.RegisterStudentCommand;
import com.dsp.app.command.user.RegisterStudentCommandHandler;
import com.dsp.shared.base.BaseResponse;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class UserWebService {

	@Autowired
	private LoginCommandHandler loginCommandHandler;
	
	@Autowired
	private RegisterStudentCommandHandler registerStudentCommandHandler;
	
	@Autowired
	private RegisterStudentBatchService registerStudentBatchService;
	
	@PostMapping("/login")
	public LoginCommandResp login(@RequestBody LoginCommand command) {
		return loginCommandHandler.handle(command);
	}
	
	@GetMapping("/student/register/batch")
	public BaseResponse<?> registerStudentBatch() throws Exception {
		registerStudentBatchService.register();
		return BaseResponse.ofSucceeded();
	}
	
	@PostMapping("/register")
	public BaseResponse<?> register(@RequestBody RegisterStudentCommand command) throws Exception {
		registerStudentCommandHandler.handle(command);
		return BaseResponse.ofSucceeded();
	}
}

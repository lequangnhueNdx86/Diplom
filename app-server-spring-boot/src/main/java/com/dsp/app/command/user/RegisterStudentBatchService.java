package com.dsp.app.command.user;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsp.shared.aspose.AsposeExcelUtils;

@Service
public class RegisterStudentBatchService {
	@Autowired
	private RegisterStudentCommandHandler commandHandler;
	
	public void register() throws Exception {
		Map<String, List<String>> data = AsposeExcelUtils.getExcelDataAsMap(System.getProperty("user.dir") + File.separator + "data" + File.separator + "students.xlsx", "Student");
		List<String> userNameList = data.get("USERNAME");
		List<String> passwordList = data.get("PASSWORD");
		List<String> studentCodeList = data.get("STUDENT_CODE");
		for (int i = 0; i < Math.max(userNameList.size(), Math.max(passwordList.size(), studentCodeList.size())); i++) {
			commandHandler.handle(new RegisterStudentCommand(userNameList.get(i), passwordList.get(i), studentCodeList.get(i)));
		}
	}
}

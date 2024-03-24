package com.dsp.dom.user;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

public interface StudentRepository {
	public void insert(Student student);
	
	public List<Student> getAll();
	
	public Optional<Student> getByStudentCode(String studentCode);

}

package com.dsp.infra.repository.user;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.dsp.dom.user.Student;
import com.dsp.dom.user.StudentRepository;
import com.dsp.infra.entity.user.StudentEntity;
import com.dsp.infra.entity.user.UserEntity;
import com.dsp.shared.utils.UuidUtils;

@Component
public class StudentRepositoryImpl implements StudentRepository {
	@Autowired
	private JpaStudentRepository jpaStudentRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void insert(Student student) {
		student.getUser().encodePassword(passwordEncoder);
		jpaStudentRepository.save(fromDomain(student));	
	}

	@Override
	public List<Student> getAll() {
		return jpaStudentRepository.findAll().stream().map(s -> s.toDomain()).collect(Collectors.toList());
	}
	
	@Override
	public Optional<Student> getByStudentCode(String studentCode) {
		return jpaStudentRepository.findById(studentCode).map(StudentEntity::toDomain);
	}
	
	private StudentEntity fromDomain(Student student) {
		return new StudentEntity(new UserEntity(student.getUser().getId() == null ? UuidUtils.generateUUID() : student.getUser().getId(),
												student.getUser().getUserName(), 
												student.getUser().getPassword(), 
												student.getUser().isRemoved(), 
												student.getUser().getRole().value), student.getStudentCode());
	}

}

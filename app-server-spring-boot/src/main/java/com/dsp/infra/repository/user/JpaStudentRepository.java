package com.dsp.infra.repository.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dsp.infra.entity.user.StudentEntity;

@Repository
public interface JpaStudentRepository extends JpaRepository<StudentEntity, String>{
	List<StudentEntity> findAll();

}

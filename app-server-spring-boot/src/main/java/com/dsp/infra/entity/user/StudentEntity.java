package com.dsp.infra.entity.user;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.dsp.dom.user.Role;
import com.dsp.dom.user.Student;
import com.dsp.dom.user.User;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student_entity")
public class StudentEntity {
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL) @JoinColumn(name = "user_id")
	private UserEntity userEntity;
	
	@Id @Column(name = "student_code")
	private String studentCode;
	
	public Student toDomain() {
		return new Student(new User(this.userEntity.getUserName(), 
									this.userEntity.getPassword(), 
									this.userEntity.isRemoved(), 
									this.userEntity.getRole() == 0 ? Role.Lecturer : Role.Student), 
							this.studentCode);
	}
}

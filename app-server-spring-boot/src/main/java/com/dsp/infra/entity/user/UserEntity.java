package com.dsp.infra.entity.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.dsp.dom.user.Role;
import com.dsp.dom.user.User;
import com.dsp.shared.base.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "user_entity",  uniqueConstraints = @UniqueConstraint(columnNames = {"user_name"}))
public class UserEntity extends BaseEntity {
	@Id
	private String id;
	@Column(name = "user_name")
	private String userName;
	private String password;
	private boolean removed;
	private int role;
	
	public User toDomain() {
		return new User(id, userName, password, removed, role == 0 ? Role.Lecturer : Role.Student);
	}

}

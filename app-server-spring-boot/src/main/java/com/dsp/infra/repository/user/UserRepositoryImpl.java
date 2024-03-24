package com.dsp.infra.repository.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dsp.dom.user.User;
import com.dsp.dom.user.UserRepository;
import com.dsp.infra.entity.user.UserEntity;

@Component
public class UserRepositoryImpl implements UserRepository {
	@Autowired
	private JpaUserRepository jpaUserRepository;

	@Override
	public Optional<User> getByUserName(String userName) {
		return jpaUserRepository.findByUserNameAndRemovedFalse(userName).map(UserEntity::toDomain);
	}

	@Override
	public Optional<User> getById(String id) {
		return jpaUserRepository.findById(id).map(UserEntity::toDomain);
	}

}

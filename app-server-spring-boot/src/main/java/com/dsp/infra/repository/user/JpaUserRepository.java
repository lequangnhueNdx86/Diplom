package com.dsp.infra.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dsp.infra.entity.user.UserEntity;

@Repository
public interface JpaUserRepository extends JpaRepository<UserEntity, String> {

	Optional<UserEntity> findByUserNameAndRemovedFalse(String userName);
}

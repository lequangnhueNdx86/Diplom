package com.dsp.dom.user;

import java.util.Optional;

public interface UserRepository {

	Optional<User> getByUserName(String userName);
	Optional<User> getById(String id);	
}

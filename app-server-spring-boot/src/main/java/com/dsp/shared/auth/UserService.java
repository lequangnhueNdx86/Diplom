package com.dsp.shared.auth;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dsp.dom.user.User;
import com.dsp.dom.user.UserRepository;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.getByUserName(username);
		if (!user.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(user.get());
	}

	// JWTAuthenticationFilter будет использовать эту функцию
    @Transactional
    public UserDetails loadUserById(String id) {
        User user = userRepository.getById(id).orElseThrow(
                () -> new UsernameNotFoundException("User not found with id : " + id)
        );

        return new CustomUserDetails(user);
    }

}

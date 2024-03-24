package com.dsp.app.command.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.dsp.shared.auth.CustomUserDetails;
import com.dsp.shared.auth.JwtTokenProvider;
import com.dsp.shared.base.CommandHandlerReturn;

@Service
public class LoginCommandHandler extends CommandHandlerReturn<LoginCommand, LoginCommandResp>{
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Override
	public LoginCommandResp handle(LoginCommand command) {
		Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        command.getUserName(),
                        command.getPassword()
                )
		);
     // Если exception исключений не возникает, информация действительна
        // Set authentication in Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Возвращает jwt пользователю
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String jwt = jwtTokenProvider.generateToken(userDetails);
        return new LoginCommandResp(jwt, userDetails.getUser().getUserName(), userDetails.getUser().getRole().name());
	}

}

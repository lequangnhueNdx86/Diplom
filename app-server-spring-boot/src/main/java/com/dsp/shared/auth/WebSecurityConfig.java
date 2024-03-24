package com.dsp.shared.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserService userService;

	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter() {
		return new JwtAuthenticationFilter();
	}

	@SuppressWarnings("deprecation")
	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// Get AuthenticationManager bean
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		// Password encoder, чтобы Spring Security использовал шифрование пароля пользователя
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService) // Предоставление userservice для spring security
				.passwordEncoder(passwordEncoder()); // Предоставление password encoder
	}

	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .cors() // Запретить request из другого домена domain 
                .and()
                    
                .authorizeRequests()
                    .antMatchers("/api/login").permitAll() // Разрешить всем доступ к этому адресу /api/login
                    .antMatchers("/api/lessons", "/api/lessons/*", "/api/lessons/*/*", "/api/lessons/*/*/*",
                    		"/api/levels", "/api/levels/*",
                    		"/api/exams",
                    		"api/questions", "/api/questions/*").authenticated(); // Все остальные запросы требуют аутентификации для доступа.
//        http.headers().addHeaderWriter(
//                new StaticHeadersWriter("Access-Control-Allow-Origin", "*"));
        // Добавьте фильтр, который проверяет jwt
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

}

package com.app.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers("/rest/v1/user/signup", "/rest/v1/note/create")
		.permitAll() // Cho phép truy cập mà không cần xác thực
		.anyRequest()
		.authenticated()
		.and().httpBasic();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth, BCryptPasswordEncoder passwordEncoder) throws Exception {
	    auth.inMemoryAuthentication()
	        .withUser("user")
	        .password(passwordEncoder.encode("12345"))
	        .authorities("ROLE_USER");
	}
}

package com.brandonoium.personalsite.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class SecurityJavaConfig extends WebSecurityConfigurerAdapter {
	

	@Autowired
    private AuthenticationEntryPoint restAuthenticationEntryPoint;
	
	@Autowired
	private PasswordEncryptionService encoder;
	
	@Autowired
	private UserAuthenticationProvider authProvider;

	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider);
		//.withUser("admin").password(encoder.encode("adminpass")).authorities("ROLE_ADMIN")
		//.and()
		//.withUser("user").password(encoder.encode("userpass")).authorities("ROLE_USER");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception { 
	    
		http
		.httpBasic()
		.and()
	    .csrf().disable()
	    .exceptionHandling()
	    .authenticationEntryPoint(restAuthenticationEntryPoint)
	    .and()
	    .authorizeRequests()
	    .antMatchers(HttpMethod.GET, "/**").permitAll()
	    .antMatchers(HttpMethod.POST, "/**").hasAnyRole("USER", "ADMIN")
	    .antMatchers(HttpMethod.DELETE, "/**").hasAnyRole("ADMIN")
	    .and()
	    .formLogin()
	    .and()
	    .logout();
	}
}

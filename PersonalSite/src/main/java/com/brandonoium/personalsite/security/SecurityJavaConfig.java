package com.brandonoium.personalsite.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class SecurityJavaConfig extends WebSecurityConfigurerAdapter {
	

	@Autowired
    AuthenticationEntryPoint restAuthenticationEntryPoint;

	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("admin").password(encoder().encode("adminpass")).roles("ADMIN")
		.and()
		.withUser("user").password(encoder().encode("userpass")).roles("USER");
	}
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception { 
	    
		http
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

package com.jpa.demo.security;



import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
   
	@Autowired
	private DataSource datasouce;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder passwordEnco=passwordEncodre();
		System.out.println("*********");
		System.out.println(passwordEnco.encode("1234"));
		System.out.println("***************");
	
		auth.jdbcAuthentication()
		    .dataSource(datasouce)
		    .usersByUsernameQuery("select username as principal, password as credentials, active from users where username=?")
		    .authoritiesByUsernameQuery("select username as principal, role as role from users_roles where username=?")
		    .passwordEncoder(passwordEnco)
		    .rolePrefix("ROLE_");
		   
		
		/*
		 * auth.inMemoryAuthentication().withUser("user").password("{noop}1234").roles(
		 * "USER");
		 * auth.inMemoryAuthentication().withUser("admin").password("{noop}1234").roles(
		 * "USER","ADMIN");
		 */
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().loginPage("/login");
		http.authorizeRequests().antMatchers("/save**/**","/delete**/**","/form**/**").hasRole("ADMIN");
		http.authorizeRequests().antMatchers("/login","/webjars/**","/resources/**").permitAll();
		http.authorizeRequests().anyRequest().authenticated();
		http.csrf();
		http.exceptionHandling().accessDeniedPage("/notAuthorized");
	}
	

  
	public PasswordEncoder passwordEncodre() {
		return new BCryptPasswordEncoder();
	}
	
}

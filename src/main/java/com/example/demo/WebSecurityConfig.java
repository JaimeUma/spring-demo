package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.model.User;
import com.example.demo.model.UserRepository;
import com.example.demo.model.UserRepositoryDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// configura las urls que por defecto estan disponibles sin autenticacion
		http.authorizeRequests()
				// permitimos estas urls sin autenticacion
				// console se usa para poder acceder a la pagina de configuracion de la base de
				// datos
				.antMatchers("/", "/console/**").permitAll().anyRequest().authenticated()

				.and()

				.formLogin().loginPage("/login").permitAll()

				.and()

				.logout().logoutSuccessUrl("/").permitAll();

		// solo usar durante la fase de desarrollo para poder acceder a la pagina de
		// administracion de la base de datos
		// despues comentar estas lineas
		http.csrf().disable();
		http.headers().frameOptions().disable();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth, UserRepositoryDetailsService userDetailsService,
			UserRepository userRepository) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());

		// Crear usuario por defecto
		User user = new User();
		user.setEmail("test@test.com");
		// la contraseña es test (encriptada usando bcrypt)
		// https://www.dailycred.com/article/bcrypt-calculator
		user.setPassword("$2a$04$mhVuX7/zGzhPu7xKCrqY8e7M0RkORqZ4QB/4rCEgYQNddyt1mAKZK");
		user.setFirstName("Alan");
		user.setLastName("Wake");
		if (userRepository.findByEmail(user.getEmail()) == null) {
			userRepository.save(user);
		}
	}

	/*
	 * @Bean
	 * 
	 * @Override public UserDetailsService userDetailsService() { UserBuilder users
	 * = User.withDefaultPasswordEncoder(); UserDetails user =
	 * users.username("user").password("user").roles("USER") .build();
	 * 
	 * return new InMemoryUserDetailsManager(user); }
	 */

}

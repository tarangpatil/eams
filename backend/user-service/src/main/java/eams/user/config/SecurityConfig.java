package eams.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import eams.user.filter.JwtFilter;

@Component
public class SecurityConfig {

	private final JwtFilter jwtFilter;

	public SecurityConfig(JwtFilter jwtFilter) {
		super();
		this.jwtFilter = jwtFilter;
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder(10);
	}

	@Bean
	public SecurityFilterChain chain(HttpSecurity http) throws Exception {
		return http//
				.csrf(cust -> cust.disable())//
				.sessionManagement(cust -> cust.sessionCreationPolicy(SessionCreationPolicy.STATELESS))//
				.authorizeHttpRequests(cust -> cust//
						.requestMatchers("/register", "/error*")//
						.permitAll()//
						.anyRequest()//
						.authenticated())//
				.httpBasic(Customizer.withDefaults())//
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)//
				.build();
	}
}

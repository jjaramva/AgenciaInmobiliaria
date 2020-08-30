package co.com.udem.crudagencia.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import co.com.udem.crudagencia.security.jwt.JwtSecurityConfigurer;
import co.com.udem.crudagencia.security.jwt.JwtTokenProvider;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	JwtTokenProvider jwtTokenProvider;

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http.httpBasic().disable().csrf().disable().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
				.antMatchers("/auth/signin").permitAll()
				.antMatchers(HttpMethod.GET, "/usuarios/**").permitAll()
				.antMatchers(HttpMethod.POST, "/usuarios/adicionarUsuario**").permitAll()
				.antMatchers(HttpMethod.GET, "/propiedades/**").permitAll()
				.anyRequest().authenticated().and().apply(new JwtSecurityConfigurer(jwtTokenProvider));
		// @formatter:on

		// http.csrf().disable().authorizeRequests().anyRequest().authenticated().and().httpBasic();

	}

}
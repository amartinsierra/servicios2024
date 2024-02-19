package init;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	JwtAuthConverter jwtAuthConverter;
	
	@Bean
	public SecurityFilterChain filter(HttpSecurity http) throws Exception{
		http.csrf(c->c.disable())
		.authorizeHttpRequests(
				aut->aut.requestMatchers(HttpMethod.GET, "/catalogo").authenticated()
				.requestMatchers(HttpMethod.POST, "/alta").hasRole("ADMINS")
				.anyRequest().permitAll()
				)
		.oauth2ResourceServer(oauth2ResourceServer-> 
	 		oauth2ResourceServer.jwt(jwt->jwt 
	 			.jwtAuthenticationConverter(jwtAuthConverter))) 
	 			.sessionManagement(sessionManagement-> 
	 				sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		return http.build();
		
	}
}

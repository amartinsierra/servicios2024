package init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
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
				aut->aut.requestMatchers(HttpMethod.GET, "/curso").authenticated()
				.requestMatchers(HttpMethod.GET, "/cursos/*/*").authenticated()
				.requestMatchers(HttpMethod.POST, "/alta").hasRole("ADMINS")
				.requestMatchers(HttpMethod.DELETE, "/eliminar").hasAnyRole("ADMINS","OPERATORS")
				.requestMatchers(HttpMethod.PUT, "/actualizar").hasAnyRole("OPERATORS")
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

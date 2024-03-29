package init.service.implementations;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import init.model.Formacion;
import init.model.TokenResponse;
import init.service.interfaces.FormacionService;
import jakarta.annotation.PostConstruct;
@Service
public class FormacionServiceImpl implements FormacionService {
	@Autowired
	RestClient restClient;
	@Value("${app.urlAuth}")
	String urlAuth;
	@Value("${app.username}")
	String username;
	@Value("${app.password}")
	String password;
	@Value("${app.client_id}")
	String clientId;
	@Value("${app.grant_type}")
	String grantType;
	String urlBase="http://localhost:8500/";
	
	String token;
	
	@PostConstruct //este método será llamado cuando la instancia esté disponible
	public void init() {
		token=getToken();
	}

	@Override
	public List<Formacion> catalogo() {
		return Arrays.asList(restClient.get()
				.uri(urlBase+"cursos")
				.retrieve()
				.body(Formacion[].class) //Formacion[].class
				);
	}

	@Override
	public List<Formacion> catalogoPorDuracionMax(int max) {		
		return catalogo().stream()
				.filter(f->f.getHoras()<=max)
				.toList();
	}

	@Override
	public void alta(Formacion formacion) {
		try {
		 restClient.post()
			.uri(urlBase+"alta")
			.contentType(MediaType.APPLICATION_JSON)
			.body(formacion)
			.header("Authorization", "Bearer "+token)
			.retrieve()
			.toBodilessEntity(); //ResponseEntity<Void>
		}
		catch(Exception ex) {
			//si hay una excepción porque el token ha caducado
			//lo regeneramos y intentamos el alta de nuevo
			getToken();
			alta(formacion);
		}
	}
	
	private String getToken() {
		System.out.println(username+" - "+password);
		MultiValueMap<String,String> params=new LinkedMultiValueMap<>();
		params.add("client_id", clientId);
		params.add("username", username);
		params.add("password", password);
		params.add("grant_type", grantType);
		
		return restClient.post()
		.uri(urlAuth)
		.contentType(MediaType.APPLICATION_FORM_URLENCODED)
		.body(params)
		.retrieve()
		.body(TokenResponse.class) //TokenResponse
		.getAccess_token();
	}

}

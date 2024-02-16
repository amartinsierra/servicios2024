package init.service.implementations;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import init.model.Book;
import init.model.TokenResponse;
import init.service.interfaces.BookService;
import jakarta.annotation.PostConstruct;
@Service
public class BookServiceImpl implements BookService {

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
	String urlBase="http://localhost:9000/";
	
	String token;
	
	@PostConstruct //este método será llamado cuando la instancia esté disponible
	public void init() {
		token=getToken();
	}
	@Override
	public List<String> tematicas() {
		return Arrays.asList(restClient.get()
				.uri(urlBase+"tematicas")
				.retrieve()
				.body(String[].class) //Formacion[].class
				);
	}

	@Override
	public List<Book> librosTematica(String tematica) {
		try {
			return Arrays.asList(restClient.get()
				.uri(urlBase+"catalogo")
				.header("Authorization", "Bearer "+token)
				.retrieve()
				.body(Book[].class)
			)
			.stream()
			.filter(b->b.getTematica().equals(tematica))
			.toList();
		}
		catch(Exception ex) {//solo se pone si todo funciona bien
			token=getToken();
			return librosTematica(tematica);
		}
	}
	private String getToken() {
		
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

package init.service.implementations;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import init.model.Book;
import init.service.interfaces.BookService;
@Service
public class BookServiceImpl implements BookService {

	@Autowired
	RestClient restClient;
	@Value("${app.user}")
	String usuario;
	@Value("${app.password}")
	String pass;
	String urlBase="http://localhost:9000/";
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
		return Arrays.asList(restClient.get()
			.uri(urlBase+"catalogo")
			.header("Authorization", "Basic "+getBase64(usuario,pass))
			.retrieve()
			.body(Book[].class)
		)
		.stream()
		.filter(b->b.getTematica().equals(tematica))
		.toList();
	}
	private String getBase64(String us, String pwd) {
		String cad=us+":"+pwd;
		return Base64.getEncoder().encodeToString(cad.getBytes());
	}
}

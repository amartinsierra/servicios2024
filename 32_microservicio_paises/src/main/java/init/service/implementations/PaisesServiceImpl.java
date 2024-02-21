package init.service.implementations;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import init.model.Pais;
import init.service.interfaces.PaisesService;
@Service
public class PaisesServiceImpl implements PaisesService {
	@Autowired
	RestClient restClient;
	private List<Pais> paises(){
		String url="https://restcountries.com/v2/all";
		return Arrays.asList(restClient.get()
				.uri(url)
				.retrieve()
				.body(Pais[].class) 
				);
	}

	@Override
	public List<String> continentes() {
		return paises().stream()
				.map(p->p.getContinente()) //transforma Pais en String
				.distinct()//elimina duplicados
				.toList();//convierte Stream final a lista
				
	}

	@Override
	public List<Pais> paisesPorContinente(String continente) {
		return paises().stream()
				.filter(p->p.getContinente().equals(continente))
				.toList();
	}

	@Override
	public Pais paisMasPoblado() {
		return paises().stream()
				.max(Comparator.comparingLong(p->p.getPoblacion()))
				.orElse(null);
	}

}

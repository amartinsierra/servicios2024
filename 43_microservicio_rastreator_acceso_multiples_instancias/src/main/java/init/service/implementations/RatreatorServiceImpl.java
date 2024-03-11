package init.service.implementations;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import init.model.Vehiculo;
import init.service.interfaces.RastreatorService;
@Service
public class RatreatorServiceImpl implements RastreatorService {
	@Autowired
	RestClient restClient;
	
	String url="http://servicio-coches/";
	
	@Override
	public List<Vehiculo> buscarPorRangoKm(int km1, int km2) {

		return Arrays.asList(
				restClient.get()
				.uri(url+"catalogo")
				.retrieve()
				.body(Vehiculo[].class)
				).stream()
				.peek(v->v.setTipo("coche"))
				.filter(v->v.getKilometros()>=km1&&v.getKilometros()<=km2)
				.toList();
		
	}

	@Override
	public List<Vehiculo> buscarPorPrecioMax(double precio) {
		return Arrays.asList(
				restClient.get()
				.uri(url+"buscarPrecioMax/"+precio)
				.retrieve()
				.body(Vehiculo[].class)
				).stream()
				.peek(v->v.setTipo("coche"))
				.toList();
	}

}

package init.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import init.model.Resultado;
import init.service.interfaces.BuscadorService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@CrossOrigin("*")//permitimos llamadas desde cualquier origen
@RestController
public class BuscadorController {
	@Autowired
	BuscadorService buscadorService;
	@GetMapping(value="buscar")
	public Flux<Resultado> buscar(@RequestParam("tematica") String tematica) {
		return buscadorService.buscar(tematica);
	}
	
	
	@PostMapping(value="alta",consumes="application/json")//para volcado del cuerpo de la petición(JSON) en el JavaBean
	public Mono<Void> alta(@RequestBody Resultado resultado) {
		return buscadorService.agregar(resultado);
		
	}
	@DeleteMapping(value="eliminar")
	public Flux<Resultado> eliminar(@RequestParam("url") String url){
		return buscadorService.eliminarResultado(url);
	}
	@PutMapping(value="actualizar",consumes="application/json")
	public Mono<Resultado> actualizar(@RequestBody Resultado resultado) {
		return buscadorService.actualizarDescripcion(resultado.getUrl(), resultado.getDescripcion());
	}
	
}

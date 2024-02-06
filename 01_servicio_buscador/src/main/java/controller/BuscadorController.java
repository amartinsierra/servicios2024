package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.Resultado;
import service.interfaces.BuscadorService;

@RestController
public class BuscadorController {
	@Autowired
	BuscadorService buscadorService;
	@GetMapping(value="buscar",produces = "application/json")
	public List<Resultado> buscar(@RequestParam("tematica") String tematica) {
		return buscadorService.buscar(tematica);
	}
	
	
	@PostMapping(value="alta",consumes="application/json")//para volcado del cuerpo de la petición(JSON) en el JavaBean
	public void alta(@RequestBody Resultado resultado) {
		buscadorService.agregar(resultado);
		
	}
	
	
}

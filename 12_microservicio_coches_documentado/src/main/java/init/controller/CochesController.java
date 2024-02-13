package init.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import init.model.Coche;
import init.service.interfaces.CochesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
public class CochesController {
	@Autowired
	CochesService service;
	@Operation(summary = "Alta de nuevos coches",description = "Recibe como parámetro un JSON con los datos del nuevo coche. Solo lo da de alta si la matrícula no existe")
	@PostMapping(value="alta",consumes=MediaType.APPLICATION_JSON_VALUE)
	public void alta(@Parameter(description = "Objeto JSON con los datos del coche") @RequestBody Coche coche) {
		service.alta(coche);
	}
	@PutMapping(value="actualizar",consumes=MediaType.APPLICATION_JSON_VALUE)
	public void actualizar(@RequestBody Coche coche) {
		service.actualizar(coche);
	}
	@DeleteMapping(value="eliminar/{matricula}",produces=MediaType.APPLICATION_JSON_VALUE)
	public Coche eliminar(@PathVariable("matricula")String matricula) {
		return service.eliminar(matricula);
	}
	@GetMapping(value="catalogo",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Coche> catalogo(){
		return service.catalogo();
	}
	@Operation(summary = "Búsqueda por marca",description = "Devuelve un JSON con los datos de los coches cuya marca se recibe en URL")
	@GetMapping(value="buscarMarca/{marca}",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Coche> marca(@Parameter(description = "Marca de los coches buscados") @PathVariable("marca")String marca){
		return service.cochesMarca(marca);
	}
	@GetMapping(value="buscarPrecioMax/{precio}",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Coche> precio(@PathVariable("precio") double precio){
		return service.cochesPrecioMax(precio);
	}
}

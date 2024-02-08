package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import model.Pais;
import service.interfaces.PaisesService;
@CrossOrigin("*")
@RestController
public class PaisesController {
	@Autowired
	PaisesService service;
	@GetMapping(value="continentes",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<String> continentes(){
		return service.continentes();
	}
	@GetMapping(value="paises/{continente}",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Pais> paisesContinente(@PathVariable("continente") String continente){
		return service.paisesPorContinente(continente);
	}
	@GetMapping(value="poblado",produces=MediaType.APPLICATION_JSON_VALUE)
	public Pais paisMasPoblado() {
		return service.paisMasPoblado();
	}
}

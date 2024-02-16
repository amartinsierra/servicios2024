package init.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import init.model.Libro;
import init.service.interfaces.LibrosService;

@RestController
public class LibrosController {
	@Autowired
	LibrosService librosService;
	@GetMapping(value="tematicas", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> tematicas(){
		return new ResponseEntity<>(librosService.tematicas(),HttpStatus.OK);
	}
	@GetMapping(value="catalogo", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Libro>> catalogo(){
		return new ResponseEntity<>(librosService.catalogo(),HttpStatus.OK);
	}
	@GetMapping(value="libro/{isbn}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Libro> libro(@PathVariable("isbn") int isbn){
		Libro lb=librosService.libroPorIsbn(isbn);
		if(lb!=null) {
			return new ResponseEntity<>(lb,HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	@PostMapping(value="alta", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> alta(@RequestBody Libro libro){		
		if(librosService.altaLibro(libro)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
}

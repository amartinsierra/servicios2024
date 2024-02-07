package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.Curso;
import service.interfaces.CursosService;

@RestController
public class CursosController {
	@Autowired
	CursosService service;
	@GetMapping(value="cursos",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> cursos(){
		return service.cursos();
	}
	@GetMapping(value="curso",produces=MediaType.APPLICATION_JSON_VALUE)
	public Curso buscar(@RequestParam("idCurso") int idCurso) {
		return service.buscarCursoPorId(idCurso);
	}
	@GetMapping(value="cursosRango",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> cursosRango(@RequestParam("min") double min, @RequestParam("max")  double max){
		return service.buscarCursosRangoPrecios(min, max);
	}
	@PostMapping(value="alta",produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> alta(@RequestBody Curso curso){
		return service.alta(curso);
	}
	@DeleteMapping(value="eliminar",produces=MediaType.APPLICATION_JSON_VALUE)
	public Curso eliminar(@RequestParam("nombre") String nombre) {
		return service.eliminarCurso(nombre);
	}
	@PutMapping(value="actualizar")
	public void actualizar(@RequestParam("porcentaje") int porcentaje, @RequestParam("nombre")  String nombre) {
		service.actualizarPrecioCurso(porcentaje, nombre);
	}
}

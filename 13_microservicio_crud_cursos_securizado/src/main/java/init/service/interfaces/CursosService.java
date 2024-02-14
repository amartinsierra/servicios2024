package init.service.interfaces;

import java.util.List;

import init.exceptions.CursoExistenteException;
import init.model.Curso;

public interface CursosService {
	List<Curso> cursos();
	Curso buscarCursoPorId(int idCurso);
	List<Curso> buscarCursosRangoPrecios(double min, double max);
	List<Curso> alta(Curso curso) throws  CursoExistenteException;
	Curso eliminarCurso(String nombre);
	void actualizarPrecioCurso(int porcentaje, String nombre);
}

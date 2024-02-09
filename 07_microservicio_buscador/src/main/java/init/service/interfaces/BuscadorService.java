package init.service.interfaces;

import java.util.List;

import init.model.Resultado;

public interface BuscadorService {
	List<Resultado> buscar(String tematica);
	void agregar(Resultado resultado);
	List<Resultado> eliminarResultado(String url);
	Resultado actualizarDescripcion(String url, String nuevaDescripcion);
}

package init.service.interfaces;

import java.util.List;

import init.model.Libro;

public interface LibrosService {
	List<String> tematicas();
	List<Libro> catalogo();
	Libro libroPorIsbn(int isbn);
	boolean altaLibro(Libro libro);
}

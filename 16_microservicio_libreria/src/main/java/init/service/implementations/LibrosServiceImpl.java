package init.service.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import init.dao.LibrosDao;
import init.model.Libro;
import init.service.interfaces.LibrosService;
@Service
public class LibrosServiceImpl implements LibrosService{
	@Autowired
	LibrosDao librosDao;

	@Override
	public List<String> tematicas() {
		/*return librosDao.findAll()
				.stream()
				.map(l->l.getTematica())
				.distinct()
				.toList();
				*/
		return librosDao.findTematicas();
	}

	@Override
	public List<Libro> catalogo() {
		return librosDao.findAll();
	}

	@Override
	public Libro libroPorIsbn(int isbn) {
		return librosDao.findById(isbn).orElse(null);
	}

	@Override
	public boolean altaLibro(Libro libro) {
		if(libroPorIsbn(libro.getIsbn())==null) {
			librosDao.save(libro);
			return true;
		}
		return false;
		
	}

}

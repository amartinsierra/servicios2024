package init.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import init.model.Libro;

public interface LibrosDao extends JpaRepository<Libro, Integer> {
	@Query("select distinct l.tematica from Libro l")
	public List<String> findTematicas();
}

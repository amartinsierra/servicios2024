package dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import model.Curso;

public interface CursosDao extends JpaRepository<Curso, Integer> {
	
	List<Curso> findByPrecioBetween(double p1, double p2);
	
	Curso findByDenominacion(String denominacion);
	@Transactional
	@Modifying
	void deleteByDenominacion(String denominacion);
	@Transactional
	@Modifying
	@Query("update Curso c set c.precio=c.precio+c.precio*?1/100 where c.denominacion=?2")
	void updatePrecio(int porcentaje, String denominacion);
}

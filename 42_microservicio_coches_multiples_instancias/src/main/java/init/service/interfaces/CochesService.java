package init.service.interfaces;

import java.util.List;

import init.model.Coche;

public interface CochesService {
	void alta(Coche coche);
	Coche eliminar(String matricula);
	void actualizar(Coche coche);
	List<Coche> cochesMarca(String marca);
	List<Coche> cochesPrecioMax(double precio);
	List<Coche> catalogo();
}

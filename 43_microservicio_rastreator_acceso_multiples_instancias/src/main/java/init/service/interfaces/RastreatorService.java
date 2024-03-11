package init.service.interfaces;

import java.util.List;

import init.model.Vehiculo;

public interface RastreatorService {
	List<Vehiculo> buscarPorRangoKm(int km1, int km2);
	List<Vehiculo> buscarPorPrecioMax(double precio);
}

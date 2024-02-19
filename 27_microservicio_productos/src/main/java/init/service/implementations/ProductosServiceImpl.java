package init.service.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import init.dao.ProductosDao;
import init.model.Producto;
import init.service.interfaces.ProductosService;
@Service
public class ProductosServiceImpl implements ProductosService {
	@Autowired
	ProductosDao productosDao;
	@Override
	public List<Producto> productos() {
		return productosDao.findAll();
	}

	@Override
	public double precioProducto(int codigoProducto) {
		Optional<Producto> prodOp=productosDao.findById(codigoProducto);
		if(prodOp.isPresent()) {
			return prodOp.get().getPrecioUnitario();
		}else {
			return -1;
		}
	}

	@Override
	public void actualizarStock(int codigoProducto, int unidades) {
		Optional<Producto> prodOp=productosDao.findById(codigoProducto);
		if(prodOp.isPresent()) {
			Producto p=prodOp.get();
			p.setStock(p.getStock()-unidades);
			productosDao.save(p);
		}

	}

}

package init.service.implementations;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import init.dao.PedidosDao;
import init.model.Pedido;
import init.service.interfaces.PedidosService;
@Service
public class PedidosServiceImpl implements PedidosService {
	@Autowired
	RestClient restClient;
	@Autowired
	PedidosDao pedidosDao;

	@Override
	public List<Pedido> pedidos() {
		return pedidosDao.findAll();
	}

	@Override
	public void registrarPedido(Pedido pedido) {
		String urlBase="http://servicio-productos/";
		pedido.setFechaPedido(new Date());
		//obtenemos precio producto llamado al recurso remoto
		double precio=Double.parseDouble(restClient.get()
				.uri(urlBase+"precio/"+pedido.getCodigoProducto())
				.retrieve()
				.body(String.class));
		pedido.setTotal(pedido.getUnidades()*precio);
		//salvamos pedido
		pedidosDao.save(pedido);
		//actualizamos stock llamado a recurso remoto
		restClient.put()
		.uri(urlBase+"actualizar/"+pedido.getCodigoProducto()+"/"+pedido.getUnidades())
		.retrieve();

	}

}

package init.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import init.model.Pedido;

public interface PedidosDao extends JpaRepository<Pedido, Integer> {

}

package init.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import init.model.Producto;
import init.service.interfaces.ProductosService;

@RestController
public class ProductosController {
	@Autowired
	ProductosService productosService;
	@GetMapping(value="productos", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Producto>> productos(){
		return new ResponseEntity<>(productosService.productos(),HttpStatus.OK);
	}
	@GetMapping(value="precio/{codigo}", produces=MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> buscarPrecio(@PathVariable("codigo") int codigoProducto) {
		return new ResponseEntity<>(Double.toString(productosService.precioProducto(codigoProducto)),HttpStatus.OK);
	}
	@PutMapping(value="actualizar/{codigo}/{unidades}")
	public ResponseEntity<Void> actualizarStock(@PathVariable("codigo") int codigoProducto,@PathVariable("unidades") int unidades){
		productosService.actualizarStock(codigoProducto, unidades);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}






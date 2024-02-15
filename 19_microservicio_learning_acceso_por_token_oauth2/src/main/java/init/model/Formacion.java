package init.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Formacion {
	@JsonProperty(value = "denominacion")  //ambos sentidos
	//@JsonAlias(value = "denominacion")   //solo serialización
	private String nombre;
	@JsonProperty(value = "duracion")
	//@JsonAlias(value = "duracion")
	private int horas;
	private double precio;
}

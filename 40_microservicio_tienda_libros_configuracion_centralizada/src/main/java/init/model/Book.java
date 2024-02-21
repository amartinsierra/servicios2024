package init.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Book {
	private int isbn;
	private String titulo;
	private String tematica;
	
}

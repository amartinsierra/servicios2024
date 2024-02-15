package init.service.interfaces;

import java.util.List;

import init.model.Book;

public interface BookService {
	List<String> tematicas();
	List<Book> librosTematica(String tematica);
}

package tuyen.novahub.assignment4.repository;

import org.springframework.data.repository.CrudRepository;

import tuyen.novahub.assignment4.model.Book;
import tuyen.novahub.assignment4.model.BookDelete;

public interface BookDeleteRepository extends CrudRepository<Book, Long> {
	
	public BookDelete save(BookDelete bookDelete);
	
}

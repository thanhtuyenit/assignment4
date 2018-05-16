package tuyen.novahub.assignment4.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import tuyen.novahub.assignment4.model.Book;

public interface BookRepository extends PagingAndSortingRepository<Book, Long> {

	public List<Book> findAll();

	public List<Book> findByEnabled(int enabled);

	public Book findByIdBook(int idBook);

	public List<Book> findByIdUser(int idUser);

	@SuppressWarnings("unchecked")
	public Book save(Book objBook);

	public int deleteByIdBook(int idBook);

	public Page<Book> findByEnabled(int enabled, Pageable pageable);

	public Page<Book> findByIdUser(int idUser, Pageable pageable);

	public Page<Book> findByAuthorContainingAndEnabledOrTitleContainingAndEnabled(String author, int enalbed,String title, int enabled,
			Pageable pageable);

	public Page<Book> findByAuthorContainingOrTitleContaining(String author, String title, Pageable pageable);

	public Page<Book> findByAuthorContainingAndIdUserOrTitleContainingAndIdUser(String author, int idUser, String title,
			int idUser2, Pageable pageable);

}

package tuyen.novahub.assignment4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tuyen.novahub.assignment4.model.BookDelete;
import tuyen.novahub.assignment4.repository.BookDeleteRepository;

@Service
@Transactional
public class BookDeleteService {
	
	@Autowired
	BookDeleteRepository bookDeleteRepository;

	public BookDelete save(BookDelete bookDelete) {
		return bookDeleteRepository.save(bookDelete);
	}
}

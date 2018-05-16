package tuyen.novahub.assignment4.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tuyen.novahub.assignment4.common.Define;
import tuyen.novahub.assignment4.model.Book;
import tuyen.novahub.assignment4.model.BookDelete;
import tuyen.novahub.assignment4.model.Comment;
import tuyen.novahub.assignment4.model.CommentDelete;
import tuyen.novahub.assignment4.service.BookDeleteService;
import tuyen.novahub.assignment4.service.BookService;
import tuyen.novahub.assignment4.service.CommentDeleteService;
import tuyen.novahub.assignment4.service.CommentService;
import tuyen.novahub.assignment4.service.UserDeleteService;
import tuyen.novahub.assignment4.service.UserService;

@RestController
public class BookController {

	@Autowired
	UserService userService;

	@Autowired
	BookService bookService;

	@Autowired
	CommentService commentService;

	@Autowired
	UserDeleteService userDeleteService;

	@Autowired
	BookDeleteService bookDeleteService;

	@Autowired
	CommentDeleteService commentDeleteService;

	@RequestMapping(value = "/admin/showEditBook/{idBook}", method = RequestMethod.PUT)
	public Book showEditBook(Model model, @PathVariable int idBook) {
		return bookService.findByIdBook(idBook);
	}

	@RequestMapping(value = "/admin/editBook", method = RequestMethod.PUT)
	public Book editBook(Model model, @RequestBody Book newBook) {
		Book editBook = bookService.findByIdBook(newBook.getIdBook());
		newBook.setEnabled(editBook.getEnabled());
		newBook.setImage(editBook.getImage());
		newBook.setCreatedAt(editBook.getCreatedAt());
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String st = simpleDateFormat.format(date);
		newBook.setUpdatedAt(st);
		bookService.save(newBook);
		return bookService.findByIdBook(newBook.getIdBook());
	}

	@RequestMapping(value = "/admin/deleteBook/{idBook}", method = RequestMethod.GET)
	public int deleteBook(Model model, @PathVariable int idBook) {
		// find book by idBook
		Book objBook = bookService.findByIdBook(idBook);
		// add book in book_delete
		BookDelete bookDelete = new BookDelete(objBook.getIdBook(), objBook.getTitle(), objBook.getAuthor(),
				objBook.getDescription(), objBook.getCreatedAt(), objBook.getUpdatedAt(), objBook.getImage(),
				objBook.getEnabled(), objBook.getIdUser());
		bookDeleteService.save(bookDelete);

		// find all comment by IdBook and add this in comment_delete
		List<Comment> listComment = commentService.findByIdBook(idBook);
		for (Comment objComment : listComment) {
			CommentDelete commentDelete = new CommentDelete(objComment.getIdComment(), objComment.getMessage(),
					objComment.getIdUser(), objComment.getIdBook(), objComment.getCreatedComment(),
					objComment.getUpdatedComment());
			commentDeleteService.save(commentDelete);
		}
		return bookService.deleteByIdBook(idBook);
	}

	@RequestMapping(value = "/admin/changeStatusBook/{idBook}", method = RequestMethod.GET)
	public Book changeStatus(Model model, @PathVariable int idBook, @RequestParam int enabled) {
		Book changeBook = bookService.findByIdBook(idBook);
		if (enabled == 0) {
			changeBook.setEnabled(1);
		} else {
			if (enabled == 1) {
				changeBook.setEnabled(0);
			}
		}
		bookService.save(changeBook);
		return bookService.findByIdBook(idBook);
	}

	@RequestMapping(value = "/listBook", method = RequestMethod.GET)
	public Page<Book> showListBookPageJson(Model model, @RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("page") Optional<Integer> page, Authentication authentication,
			@RequestParam("direction") Direction direction, @RequestParam("properties") String properties) {
		Define define = new Define();
		if (authentication != null && authentication.getAuthorities().toString().equals("[ROLE_ADMIN]")) {
			int evalPageSize = pageSize.orElse(define.getInitialPageSize());
			int evalPage = (page.orElse(0) < 1) ? define.getInitialPage() : page.get() - 1;
			if (direction == null || properties == null) {
				direction = Direction.DESC;
				properties = "Title";
			}
			return bookService.findAllPageable(PageRequest.of(evalPage, evalPageSize, direction, properties));
		} else {
			if (direction == null) {
				direction = Direction.DESC;
				properties = "Title";
			}
			int evalPageSize = pageSize.orElse(define.getInitialPageSize());
			int evalPage = (page.orElse(0) < 1) ? define.getInitialPage() : page.get() - 1;
			return bookService.findByEnabled(1, PageRequest.of(evalPage, evalPageSize, direction, properties));
		}

	}


	@RequestMapping(value = "/searchBook", method = RequestMethod.GET)
	public Page<Book> searchBook(@RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("page") Optional<Integer> page, Authentication authentication, Model model,
			@RequestParam("keyword") String keyword, @RequestParam("direction") Direction direction,
			@RequestParam("properties") String properties) {
		if (authentication != null && authentication.getAuthorities().toString().equals("[ROLE_ADMIN]")) {
			Define define = new Define();
			if (direction == null || properties == null) {
				direction = Direction.DESC;
				properties = "Title";
			}
			int evalPageSize = pageSize.orElse(define.getInitialPageSize());
			int evalPage = (page.orElse(0) < 1) ? define.getInitialPage() : page.get() - 1;

			return bookService.findByAuthorContainingOrTitleContaining(keyword, keyword,
					PageRequest.of(evalPage, evalPageSize, direction, properties));
		} else {
			Define define = new Define();
			if (direction == null || properties == null) {
				direction = Direction.DESC;
				properties = "Title";
			}
			int evalPageSize = pageSize.orElse(define.getInitialPageSize());
			int evalPage = (page.orElse(0) < 1) ? define.getInitialPage() : page.get() - 1;
			return bookService.findByAuthorContainingAndEnabledOrTitleContainingAndEnabled(keyword, 1, keyword, 1,
					PageRequest.of(evalPage, evalPageSize, direction, properties));
		}
	}

}

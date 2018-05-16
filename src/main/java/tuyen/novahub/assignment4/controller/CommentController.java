package tuyen.novahub.assignment4.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tuyen.novahub.assignment4.model.Book;
import tuyen.novahub.assignment4.model.Comment;
import tuyen.novahub.assignment4.model.User;
import tuyen.novahub.assignment4.service.BookService;
import tuyen.novahub.assignment4.service.CommentService;
import tuyen.novahub.assignment4.service.UserService;

@RestController
public class CommentController {
	@Autowired
	CommentService commentService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	BookService bookService;
	
	@RequestMapping(value = "/deleteComment/{idComment}", method = RequestMethod.GET)
	public List<Comment> deleteComment(Model model, @PathVariable int idComment,@RequestParam int idBook) {
		Logger.getLogger("dddd");
		Comment del = commentService.findByIdComment(idComment);
		commentService.save(del);
		return commentService.findByIdBook(idBook);
	}
	
	@RequestMapping(value = "/allComments/{idBook}", method = RequestMethod.GET)
	public List<Comment> allComment(Model model, @PathVariable int idBook) {
		Logger.getLogger("allComments");
		return commentService.findByIdBook(idBook);
	}
	
	@RequestMapping(value = "/addNewComment", method = RequestMethod.POST)
	public List<Comment> addNewlComment(Model model, @RequestBody Comment newComment,Principal principal) {
		String emailLogin = principal.getName();
		User userLogin = userService.findByEmail(emailLogin);
		Book objBook = bookService.findByIdBook(newComment.getIdBook());
		userService.save(userLogin);
		bookService.save(objBook);
		newComment.setIdUser(userLogin.getIdUser());
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String st = simpleDateFormat.format(date);
		newComment.setCreatedComment(st);
		newComment.setUpdatedComment(st);
		newComment.setUser(userLogin);
		newComment.setBook(objBook);
		System.out.println(newComment.toString());
		commentService.save(newComment);
		
		return commentService.findByIdBook(newComment.getIdBook());
	}
}

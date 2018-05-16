package tuyen.novahub.assignment4.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tuyen.novahub.assignment4.model.Book;
import tuyen.novahub.assignment4.model.BookDelete;
import tuyen.novahub.assignment4.model.Comment;
import tuyen.novahub.assignment4.model.CommentDelete;
import tuyen.novahub.assignment4.model.User;
import tuyen.novahub.assignment4.model.UserDelete;
import tuyen.novahub.assignment4.service.BookDeleteService;
import tuyen.novahub.assignment4.service.BookService;
import tuyen.novahub.assignment4.service.CommentDeleteService;
import tuyen.novahub.assignment4.service.CommentService;
import tuyen.novahub.assignment4.service.UserDeleteService;
import tuyen.novahub.assignment4.service.UserService;

@RestController
public class UserController {

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

	String superAdmin = "admin@gmail.com";

	@RequestMapping(value = "/admin/getAllUser", method = RequestMethod.GET)
	public List<User> allUser() {
		return userService.findAll();
	}

	@RequestMapping(value = "/admin/addUser", method = RequestMethod.POST)
	public User addUserJson(Model model, @RequestBody User newUser) {
		newUser.setPassword(BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt()));
		newUser.setEnabled(1); // enable

		return userService.save(newUser);
	}

	@RequestMapping(value = "/admin/deleteUser/{idUser}", method = RequestMethod.DELETE)
	public int deleteUserJson(Model model, @PathVariable int idUser) {
		User objUser = userService.findByIdUser(idUser);
		if (objUser.getEmail() != superAdmin) {
			UserDelete userDelete = new UserDelete(objUser.getIdUser(), objUser.getEmail(), objUser.getPassword(),
					objUser.getFirstName(), objUser.getLastName(), 0, objUser.getAvatar(), objUser.getIdRole());
			userDeleteService.save(userDelete);
			// find allBook of user need delete by idUser, add allBook in book_delete
			List<Book> listBook = bookService.findByIdUser(idUser);
			for (Book objBook : listBook) {
				BookDelete bookDelete = new BookDelete(objBook.getIdBook(), objBook.getTitle(), objBook.getAuthor(),
						objBook.getDescription(), objBook.getCreatedAt(), objBook.getUpdatedAt(), objBook.getImage(),
						objBook.getEnabled(), idUser);
				bookDeleteService.save(bookDelete);
			}

			// find all comment of user need delete, add allComment of user this in
			// comment_delete
			List<Comment> listComment = commentService.findByIdUser(idUser);
			for (Comment objComment : listComment) {
				CommentDelete commentDelete = new CommentDelete(objComment.getIdComment(), objComment.getMessage(),
						idUser, objComment.getIdBook(), objComment.getCreatedComment(), objComment.getUpdatedComment());
				commentDeleteService.save(commentDelete);
			}
			// delete user

		}

		return userService.deleteByIdUser(objUser.getIdUser());
	}

	@RequestMapping(value = "/admin/showEditUser/{idUser}", method = RequestMethod.PUT)
	public User showEditUserJson(Model model, @PathVariable int idUser) {
		return userService.findByIdUser(idUser);
	}

	@RequestMapping(value = "/admin/editUser", method = RequestMethod.PUT)
	public User editUserJson(Model model, @RequestBody User newUser) {
		User editUser = userService.findByIdUser(newUser.getIdUser());
		if (newUser.getPassword().equals("")) {
			// not change password
			newUser.setPassword(editUser.getPassword());
		} else {
			newUser.setPassword(BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt()));
		}
		if (editUser.getEmail() == superAdmin) {
			// do not allow change role admin with account admin@gmail.com
			newUser.setIdRole(0);
		}
		newUser.setEnabled(editUser.getEnabled());
		newUser.setAvatar(editUser.getAvatar());

		return userService.save(newUser);
	}

	@RequestMapping(value = "/admin/changeStatus/{idUser}", method = RequestMethod.GET)
	public List<User> changeStatus(Model model, @PathVariable int idUser, @RequestParam int enabled) {
		User changeUser = userService.findByIdUser(idUser);
		if (changeUser.getEmail() == superAdmin) {
			return userService.findAll();
		} else {
			if (enabled == 0) {
				changeUser.setEnabled(1);
			} else {
				if (enabled == 1) {
					changeUser.setEnabled(0);
				}
			}

			userService.save(changeUser);
			return userService.findAll();
		}

	}

	@RequestMapping(value = { "/checkEmail" }, method = RequestMethod.POST)
	public boolean checkEmail(@RequestParam String aemail, HttpServletResponse response) throws IOException {
		if(userService.findByEmail(aemail) == null) {
			return true;
		}
		return false;
	}

	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
	public User signUp(Model model, @RequestBody User newUser) {
		newUser.setPassword(BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt()));
		newUser.setIdRole(2);
		newUser.setEnabled(0); // enable
		return userService.save(newUser);
	}

	@RequestMapping(value = "/showEditUserLogin", method = RequestMethod.PUT)
	public User showEditUserLogin(Model model, Principal principal) {
		int idUserLogin = userService.findByEmail(principal.getName()).getIdUser();
		return userService.findByIdUser(idUserLogin);
	}

	@RequestMapping(value = "/editUserLogin", method = RequestMethod.PUT)
	public User editUserLogin(Model model, @RequestBody User newUser, Principal principal) {
		int idUserLogin = userService.findByEmail(principal.getName()).getIdUser();
		User editUser = userService.findByIdUser(idUserLogin);
		editUser.setFirstName(newUser.getFirstName());
		editUser.setLastName(newUser.getLastName());
		userService.save(editUser);
		return userService.findByIdUser(idUserLogin);
	}

	@RequestMapping(value = "/changePasswordUserLogin", method = RequestMethod.GET)
	public User changePasswordUserLogin(Model model, @RequestParam("oldPassword") String oldPassword,
			@RequestParam("newPassword") String newPassword, Principal principal) {
		User userLogin = userService.findByEmail(principal.getName());
		int idUserLogin = userLogin.getIdUser();
		if (BCrypt.checkpw(oldPassword, userLogin.getPassword())) {
			// true
			userLogin.setPassword(BCrypt.hashpw(newPassword, BCrypt.gensalt()));
			userService.save(userLogin);
			return userService.findByIdUser(idUserLogin);
		} else {
			return null;
		}

	}
}

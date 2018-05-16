package tuyen.novahub.assignment4.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import tuyen.novahub.assignment4.common.Define;
import tuyen.novahub.assignment4.model.Book;
import tuyen.novahub.assignment4.model.Comment;
import tuyen.novahub.assignment4.model.User;
import tuyen.novahub.assignment4.service.BookService;
import tuyen.novahub.assignment4.service.CommentService;
import tuyen.novahub.assignment4.service.UserService;

@Controller
public class IndexController {

	@Autowired
	UserService userService;
	@Autowired
	BookService bookService;
	@Autowired
	CommentService commentService;
	String error403 = "admin/403";
	String selectedPageSize = "selectedPageSize";
	String pageSizes = "pageSizes";

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accessDenied() {
		return error403;
	}

	@RequestMapping(value = "/show-login", method = RequestMethod.GET)
	public String showLogin(@RequestParam(required = false) String msg, Model model) {
		if (msg != null) {
			String tmpMsg = "message";
			switch (msg) {
			case "0":
				model.addAttribute(tmpMsg, "Invalid username or password");
				break;
			case "1":
				model.addAttribute(tmpMsg, "The account is locked, please contact admin!");
				break;
			default:
				model.addAttribute(tmpMsg, "");
				break;
			}

		}
		return "admin/login";
	}

	@RequestMapping(value = "/admin/allUser", method = RequestMethod.GET)
	public String showListUser(Model model) {
		List<User> list = userService.findAll();
		model.addAttribute("listUser", list);
		return "admin/user";
	}

	// login-success
	@RequestMapping(value = "/login-success", method = RequestMethod.GET)
	public String loginSuccess(Principal principal, Model model, HttpServletRequest request) {
		if (userService.findByEmailAndEnabled(principal.getName(), 1) != null) {
			
			return "redirect:/";
		} else {
			HttpSession session = request.getSession(false);
			session.invalidate();
			return "redirect:/show-login?msg=1";
		}

	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showListBookPage(Principal principal, Model model,
			@RequestParam("pageSize") Optional<Integer> pageSize, @RequestParam("page") Optional<Integer> page,
			Authentication authentication) {
		Define define = new Define();
		if (authentication != null && authentication.getAuthorities().toString().equals("[ROLE_ADMIN]")) {
			int evalPageSize = pageSize.orElse(define.getInitialPageSize());
			model.addAttribute(selectedPageSize, evalPageSize);
			model.addAttribute(pageSizes, define.getPageSize());
			User userLogin = userService.findByEmail(principal.getName());
			model.addAttribute("userLogin", userLogin);
		} else {
			int evalPageSize = pageSize.orElse(define.getInitialPageSize());
			model.addAttribute(selectedPageSize, evalPageSize);
			model.addAttribute(pageSizes, define.getPageSize());
			model.addAttribute("userLogin", new User());
		}
		return "admin/book";
	}

	@RequestMapping(value = "/detailBook/{idBook}", method = RequestMethod.GET)
	public String showDetailBook(Model model, @PathVariable int idBook, Authentication authentication,
			Principal principal) {
		Book objBook = bookService.findByIdBook(idBook);
		if (authentication != null) {
			// login
			int idUserLogin = userService.findByEmail(principal.getName()).getIdUser();
			if (authentication.getAuthorities().toString().equals("[ROLE_USER]") && (objBook.getIdUser() != idUserLogin)
					&& (objBook.getEnabled() == 0)) {
				// user login and access book with enabled = 0 and objBook.getIdUser() !=
				// idUserLogin =>not allow
				return error403;
			}
			if (objBook.getEnabled() == 1) {
				// accept comment
				model.addAttribute("accept", 1);
			} else {
				model.addAttribute("accept", 0);
			}
		} else {
			// if not login and access book with enabled = 0 => not allow
			if (objBook.getEnabled() == 0) {
				return error403;
			}
		}
		List<Comment> listComment = commentService.findByIdBook(idBook);
		model.addAttribute("objBook", objBook);
		model.addAttribute("listComment", listComment);
		model.addAttribute("numberCmt", listComment.size());
		return "admin/detailBook";
	}

	@RequestMapping(value = "/myBook", method = RequestMethod.GET)
	public String showMyBook(Model model, Principal principal, @RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("page") Optional<Integer> page, Authentication authentication) {
		if(authentication != null) {
			Define define = new Define();
			int evalPageSize = pageSize.orElse(define.getInitialPageSize());
			model.addAttribute(selectedPageSize, evalPageSize);
			model.addAttribute(pageSizes, define.getPageSize());
			return "admin/myBook";
		}else {
			return "admin/login";
		}
		
	}
}

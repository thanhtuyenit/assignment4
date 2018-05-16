package tuyen.novahub.assignment4.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "comment_delete")
public class CommentDelete {
	
	@Id
	@Column(name = "idComment", nullable = false)
	int							idComment;
	
	@Column(name = "message", length = 1000, nullable = false)
	private String	message;
	
	@Column(name = "idUser", nullable = false)
	private int			idUser;
	
	@Column(name = "idBook", nullable = false)
	private int			idBook;
	
	@Column(name = "createdAt", nullable = false)
	private String	createdComment;
	
	@Column(name = "updatedAt", nullable = false)
	private String	updatedComment;
	
	public CommentDelete() {
		super();
	}
	
	public CommentDelete(int idComment, String message, int idUser, int idBook, String createdComment,
	    String updatedComment) {
		super();
		this.idComment = idComment;
		this.message = message;
		this.idUser = idUser;
		this.idBook = idBook;
		this.createdComment = createdComment;
		this.updatedComment = updatedComment;
	}
	
	public int getIdComment() {
		return idComment;
	}
	
	public void setIdComment(int idComment) {
		this.idComment = idComment;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public int getIdUser() {
		return idUser;
	}
	
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	
	public int getIdBook() {
		return idBook;
	}
	
	public void setIdBook(int idBook) {
		this.idBook = idBook;
	}
	
	public String getCreatedComment() {
		return createdComment;
	}
	
	public void setCreatedComment(String createdComment) {
		this.createdComment = createdComment;
	}
	
	public String getUpdatedComment() {
		return updatedComment;
	}
	
	public void setUpdatedComment(String updatedComment) {
		this.updatedComment = updatedComment;
	}
	
}

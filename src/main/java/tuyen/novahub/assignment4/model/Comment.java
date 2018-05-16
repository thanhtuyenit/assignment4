package tuyen.novahub.assignment4.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comment")
public class Comment {
	
	@Id
	@GeneratedValue
	@Column(name = "id_comment", nullable = false)
	int							idComment;
	
	@Column(name = "message", length = 1000, nullable = false)
	private String	message;
	
	@Column(name = "id_user", nullable = false)
	private int			idUser;
	
	@Column(name = "id_book", nullable = false)
	private int			idBook;
	
	@Column(name = "created_at", nullable = false)
	private String	createdComment;
	
	@Column(name = "updated_at", nullable = false)
	private String	updatedComment;
	
	@ManyToOne
    @JoinColumn(name = "id_user",referencedColumnName = "id_user", insertable = false, updatable = false)
    private User user;
	
	@ManyToOne
    @JoinColumn(name = "id_book",referencedColumnName = "id_book", insertable = false, updatable = false)
    private Book book;
	
	public Comment() {
		super();
	}
	
	public Comment(int idComment, String message, int idUser, int idBook, String createdComment, String updatedComment) {
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	@Override
	public String toString() {
		return "Comment [idComment=" + idComment + ", message=" + message + ", idUser=" + idUser + ", idBook=" + idBook
				+ ", createdComment=" + createdComment + ", updatedComment=" + updatedComment + ", user=" + user
				+ ", book=" + book + "]";
	}
	
	
}

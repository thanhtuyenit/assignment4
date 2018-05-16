package tuyen.novahub.assignment4.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_delete")
public class UserDelete{
	
	
	@Id
	@Column(name = "id_user", nullable = false)
	int												idUser;
	
	@Column(name = "email", length = 100, nullable = false)
	private String						email;
	
	@Column(name = "password", length = 100, nullable = false)
	private String						password;
	
	@Column(name = "first_name", length = 100, nullable = true)
	private String						firstName;
	
	@Column(name = "last_name", length = 100, nullable = true)
	private String						lastName;
	
	@Column(name = "enabled", nullable = false)
	private int								enabled;
	
	@Column(name = "avatar", length = 100, nullable = true)
	private String						avatar;
	
	@Column(name = "id_role", nullable = false)
	private int								idRole;
	
	
	public UserDelete() {
		super();
	}
	
	public UserDelete(int idUser, String email, String password, String firstName, String lastName, int enabled, String avatar,
	    int idRole) {
		super();
		this.idUser = idUser;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.enabled = enabled;
		this.avatar = avatar;
		this.idRole = idRole;
	}
	
	

	public int getIdUser() {
		return idUser;
	}
	
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public int getEnabled() {
		return enabled;
	}
	
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	
	public String getAvatar() {
		return avatar;
	}
	
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	public int getIdRole() {
		return idRole;
	}
	
	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}
	
}

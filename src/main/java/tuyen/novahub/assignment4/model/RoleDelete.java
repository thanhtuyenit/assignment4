package tuyen.novahub.assignment4.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "role_delete")
public class RoleDelete implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "idRole", nullable = false)
	int			idRole;
	
	@Column(name = "nameRole", length = 45, nullable = false)
	String	nameRole;
	
	
	public RoleDelete() {
		super();
	}
	
	public RoleDelete(int idRole, String nameRole) {
		super();
		this.idRole = idRole;
		this.nameRole = nameRole;
	}
	
	

	public int getIdRole() {
		return idRole;
	}
	
	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}
	
	public String getNameRole() {
		return nameRole;
	}
	
	public void setNameRole(String nameRole) {
		this.nameRole = nameRole;
	}

	@Override
	public String toString() {
		return "Role [idRole=" + idRole + ", nameRole=" + nameRole + "]";
	}
	
}

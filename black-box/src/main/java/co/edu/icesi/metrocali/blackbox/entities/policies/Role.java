package co.edu.icesi.metrocali.blackbox.entities.policies;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the t_002_roles database table.
 * 
 */
@Entity
@Table(name="t_002_roles", schema="policies")
@NamedQuery(name="Role.findAll", query="SELECT t FROM Role t")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String name;

	//bi-directional many-to-many association to Service
	@ManyToMany(mappedBy="Roles")
	private List<Service> Services;

	//bi-directional many-to-many association to User
	@ManyToMany(mappedBy="Roles")
	private List<User> Users;

	public Role() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Service> getServices() {
		return this.Services;
	}

	public void setServices(List<Service> Services) {
		this.Services = Services;
	}

	public List<User> getUsers() {
		return this.Users;
	}

	public void setUsers(List<User> Users) {
		this.Users = Users;
	}

}
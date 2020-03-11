package co.edu.icesi.metrocali.blackbox.entities.policies;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the t_002_roles database table.
 * 
 */
@Entity
@Table(name="t_002_roles")
@NamedQuery(name="T002Role.findAll", query="SELECT t FROM T002Role t")
public class T002Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String name;

	//bi-directional many-to-many association to T002Service
	@ManyToMany(mappedBy="t002Roles")
	private List<T002Service> t002Services;

	//bi-directional many-to-many association to T002User
	@ManyToMany(mappedBy="t002Roles")
	private List<T002User> t002Users;

	public T002Role() {
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

	public List<T002Service> getT002Services() {
		return this.t002Services;
	}

	public void setT002Services(List<T002Service> t002Services) {
		this.t002Services = t002Services;
	}

	public List<T002User> getT002Users() {
		return this.t002Users;
	}

	public void setT002Users(List<T002User> t002Users) {
		this.t002Users = t002Users;
	}

}
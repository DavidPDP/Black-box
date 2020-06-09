package co.edu.icesi.metrocali.blackbox.entities.policies;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="t_002_roles", schema="policies")
@Getter
@Setter
public class Role implements Serializable {

	private static final long serialVersionUID = -2250057454504936058L;

	@Id
	private Integer id;

	private String name;

	@ManyToMany(mappedBy="roles")
	private List<Service> services;

	@ManyToMany(mappedBy="roles")
	private List<User> users;

}
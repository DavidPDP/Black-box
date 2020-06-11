package co.edu.icesi.metrocali.blackbox.entities.policies;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="t_002_users", schema="policies")
@Getter @Setter
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;

	@Column(name="account_name")
	private String accountName;

	@Column(name="email")
	private String email;
	
	@Column(name="name")
	private String name;
	
	@Column(name="last_name")
	private String lastName;

	@Column(name="password")
	private String password;
	
	@ManyToMany(cascade = {
	    CascadeType.PERSIST,
	    CascadeType.REMOVE
	})
	@JoinTable(
		schema="policies",
		name="t_002_users_roles",
		joinColumns=@JoinColumn(name="owner"),
		inverseJoinColumns=@JoinColumn(name="role")
	)
	private List<Role> roles;
	
}
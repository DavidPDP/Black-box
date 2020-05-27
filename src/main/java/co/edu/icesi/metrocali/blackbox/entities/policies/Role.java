package co.edu.icesi.metrocali.blackbox.entities.policies;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import co.edu.icesi.metrocali.blackbox.utils.RoleDeserializer;
import lombok.Getter;
import lombok.Setter;

@JsonDeserialize(using = RoleDeserializer.class)
@Entity
@Table(name="t_002_roles", schema="policies")
@Getter
@Setter
public class Role {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;

	@Column(name="name")
	private String name;

	@ManyToMany(mappedBy="roles", fetch = FetchType.LAZY)
	@JsonInclude(value=Include.NON_NULL)
	private List<User> users;
	
	// TODO review the granularity of the system authorization.  
	//	@ManyToMany(cascade = {
	//    CascadeType.PERSIST,
	//    CascadeType.REMOVE
	//}, fetch = FetchType.LAZY)
	//@JoinTable(
	//	schema="policies",
	//	name="t_002_permissions",
	//	joinColumns=@JoinColumn(name="role"),
	//	inverseJoinColumns=@JoinColumn(name="service")
	//)
	//@JsonInclude(value=Include.NON_NULL)
	//private List<Service> services;

}
package co.edu.icesi.metrocali.blackbox.entities.policies;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="t_002_roles", schema="policies")
@Getter @Setter
public class Role {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;

	@Column(name="name")
	private String name;
	
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
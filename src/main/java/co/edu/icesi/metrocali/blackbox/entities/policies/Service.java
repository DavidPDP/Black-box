package co.edu.icesi.metrocali.blackbox.entities.policies;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="t_002_services", schema="policies")
@Getter
@Setter
public class Service implements Serializable {
	
	private static final long serialVersionUID = 6250455060050872391L;

	@Id
	private Integer id;

	@Column(name="is_internal")
	private Boolean isInternal;

	private String name;

	private String protocol;

	private String uri;

	@ManyToMany(cascade = {
	    CascadeType.PERSIST,
	    CascadeType.MERGE
	})
	@JoinTable(
		schema="policies",
		name="t_002_permissions",
		joinColumns=@JoinColumn(name="service"),
		inverseJoinColumns=@JoinColumn(name="role")
	)
	private List<Role> roles;

}
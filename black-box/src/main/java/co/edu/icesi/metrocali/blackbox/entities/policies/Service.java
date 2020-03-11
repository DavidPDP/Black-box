package co.edu.icesi.metrocali.blackbox.entities.policies;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the t_002_services database table.
 * 
 */
@Entity
@Table(name="t_002_services", schema="policies")
@NamedQuery(name="Service.findAll", query="SELECT t FROM Service t")
public class Service implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Column(name="is_internal")
	private Boolean isInternal;

	private String name;

	private String protocol;

	private String uri;

	//bi-directional many-to-many association to Role
	@ManyToMany
	@JoinTable(
		name="t_002_permissions"
		, joinColumns={
			@JoinColumn(name="service")
			}
		, inverseJoinColumns={
			@JoinColumn(name="role")
			}
		)
	private List<Role> Roles;

	public Service() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getIsInternal() {
		return this.isInternal;
	}

	public void setIsInternal(Boolean isInternal) {
		this.isInternal = isInternal;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProtocol() {
		return this.protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getUri() {
		return this.uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public List<Role> getRoles() {
		return this.Roles;
	}

	public void setRoles(List<Role> Roles) {
		this.Roles = Roles;
	}

}
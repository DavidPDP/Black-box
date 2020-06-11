package co.edu.icesi.metrocali.blackbox.entities.policies;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

//TODO this class must be restated depending on the granularity of the authorization.
@Entity
@Table(name="t_002_services", schema="policies")
@Getter @Setter
public class Service {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;

	@Column(name="is_internal")
	private Boolean isInternal;

	@Column(name="name")
	private String name;

	@Column(name="protocol")
	private String protocol;

	@Column(name="uri")
	private String uri;

//	@ManyToMany(mappedBy = "services", fetch= FetchType.LAZY)
//	@JsonBackReference("ssr1")
//	@JsonManagedReference("ssr1")
//	@JsonInclude(value=Include.NON_NULL)
//	private List<Role> roles;

}
package co.edu.icesi.metrocali.blackbox.entities.policies;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the t_002_users database table.
 * 
 */
@Entity
@Table(name="t_002_users")
@NamedQuery(name="T002User.findAll", query="SELECT t FROM T002User t")
public class T002User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Column(name="account_name")
	private String accountName;

	@Column(name="is_active")
	private Boolean isActive;

	private String password;

	//bi-directional many-to-one association to T002Setting
	@OneToMany(mappedBy="t002User")
	private List<T002Setting> t002Settings;

	//bi-directional many-to-many association to T002Role
	@ManyToMany
	@JoinTable(
		name="t_002_users_roles"
		, joinColumns={
			@JoinColumn(name="owner")
			}
		, inverseJoinColumns={
			@JoinColumn(name="role")
			}
		)
	private List<T002Role> t002Roles;

	public T002User() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccountName() {
		return this.accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<T002Setting> getT002Settings() {
		return this.t002Settings;
	}

	public void setT002Settings(List<T002Setting> t002Settings) {
		this.t002Settings = t002Settings;
	}

	public T002Setting addT002Setting(T002Setting t002Setting) {
		getT002Settings().add(t002Setting);
		t002Setting.setT002User(this);

		return t002Setting;
	}

	public T002Setting removeT002Setting(T002Setting t002Setting) {
		getT002Settings().remove(t002Setting);
		t002Setting.setT002User(null);

		return t002Setting;
	}

	public List<T002Role> getT002Roles() {
		return this.t002Roles;
	}

	public void setT002Roles(List<T002Role> t002Roles) {
		this.t002Roles = t002Roles;
	}

}
package co.edu.icesi.metrocali.blackbox.entities.policies;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;
import co.edu.icesi.metrocali.blackbox.entities.event_managment.EventsTrack;
import co.edu.icesi.metrocali.blackbox.entities.event_managment.EventsRemark;
import co.edu.icesi.metrocali.blackbox.entities.event_managment.UsersTrack;


/**
 * The persistent class for the t_002_users database table.
 * 
 */
@Entity
@Table(name="t_002_users", schema="policies")
@NamedQuery(name="User.findAll", query="SELECT t FROM User t")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Column(name="account_name")
	private String accountName;

	@Column(name="is_active")
	private Boolean isActive;

	private String password;

	//bi-directional many-to-one association to Setting
	@OneToMany(mappedBy="User")
	private List<Setting> Settings;

	//bi-directional many-to-many association to Role
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
	private List<Role> Roles;
	
	@OneToMany(mappedBy="manager")
	private List<EventsTrack> eventsTracks;
	
	@OneToMany(mappedBy="author")
	private List<EventsRemark> eventsRemarks;
	
	@OneToMany(mappedBy="user")
	private List<UsersTrack> usersTracks;

	public User() {
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

	public List<Setting> getSettings() {
		return this.Settings;
	}

	public void setSettings(List<Setting> Settings) {
		this.Settings = Settings;
	}

	public Setting addSetting(Setting Setting) {
		getSettings().add(Setting);
		Setting.setUser(this);

		return Setting;
	}

	public Setting removeSetting(Setting Setting) {
		getSettings().remove(Setting);
		Setting.setUser(null);

		return Setting;
	}

	public List<Role> getRoles() {
		return this.Roles;
	}

	public void setRoles(List<Role> Roles) {
		this.Roles = Roles;
	}

}
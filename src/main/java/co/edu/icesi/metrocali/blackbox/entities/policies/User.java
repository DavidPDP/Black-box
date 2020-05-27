package co.edu.icesi.metrocali.blackbox.entities.policies;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="t_002_users", schema="policies")
@Getter
@Setter
public class User implements Serializable {
	
	private static final long serialVersionUID = 4013208759001158845L;

	@Id
	private Integer id;

	@Column(name="account_name")
	private String accountName;

	@Column(name="is_active")
	private Boolean isActive;

	private String password;

	@OneToMany(mappedBy="user")
	private List<Setting> settings;

	@ManyToMany(cascade = {
	    CascadeType.PERSIST,
	    CascadeType.MERGE
	})
	@JoinTable(
		schema="policies",
		name="t_002_users_roles",
		joinColumns=@JoinColumn(name="owner"),
		inverseJoinColumns=@JoinColumn(name="role")
	)
	private List<Role> roles;

	public Setting addSetting(Setting setting) {
		getSettings().add(setting);
		setting.setUser(this);
		return setting;
	}

	public Setting removeSetting(Setting setting) {
		getSettings().remove(setting);
		setting.setUser(null);
		return setting;
	}

}
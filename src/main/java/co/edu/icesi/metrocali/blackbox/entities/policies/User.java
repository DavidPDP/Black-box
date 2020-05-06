package co.edu.icesi.metrocali.blackbox.entities.policies;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import co.edu.icesi.metrocali.blackbox.entities.event_managment.EventRemark;
import co.edu.icesi.metrocali.blackbox.entities.event_managment.EventTrack;
import co.edu.icesi.metrocali.blackbox.entities.event_managment.UsersRemark;
import co.edu.icesi.metrocali.blackbox.entities.event_managment.UsersTrack;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="t_002_users", schema="policies")
@Getter
@Setter
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
	
	@Column(name="lastName")
	private String lastName;

	@Column(name="password")
	private String password;
	
	@Column(name="token")
	private String token;

	@OneToMany(mappedBy="user", fetch = FetchType.LAZY)
	@JsonManagedReference("ser1")
	@JsonInclude(value=Include.NON_NULL)
	private List<Setting> settings;

	@ManyToMany(cascade = {
	    CascadeType.PERSIST,
	    CascadeType.REMOVE
	}, fetch = FetchType.LAZY)
	@JoinTable(
		schema="policies",
		name="t_002_users_roles",
		joinColumns=@JoinColumn(name="owner"),
		inverseJoinColumns=@JoinColumn(name="role")
	)
	@JsonInclude(value=Include.NON_NULL)
	private List<Role> roles;
	
	@OneToMany(mappedBy="user")
	@JsonManagedReference("ur4")
	@JsonInclude(value=Include.NON_NULL)
	private List<UsersTrack> userTracks;
	
	@OneToMany(mappedBy="user")
	@JsonManagedReference("ur2")
	@JsonInclude(value=Include.NON_NULL)
	private List<EventTrack> eventTracks;
	
	@OneToMany(mappedBy="user")
	@JsonManagedReference("ur5")
	@JsonInclude(value=Include.NON_NULL)
	private List<UsersRemark> userRemarks;
	
	@OneToMany(mappedBy="user")
	@JsonManagedReference("ur1")
	@JsonInclude(value=Include.NON_NULL)
	private List<EventRemark> eventRemarks;

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
package co.edu.icesi.metrocali.blackbox.entities.event_managment;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import co.edu.icesi.metrocali.blackbox.entities.policies.User;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="t_002_users_track", schema="event_managment")
@Getter
@Setter
public class UsersTrack {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;

	@Column(name="end_time")
	private Timestamp endTime;

	@Column(name="start_time")
	private Timestamp startTime;

	@OneToMany(mappedBy="usersTrack")
	@JsonManagedReference("urr3")
	private List<UsersRemark> usersRemarks;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_tracks")
	@JsonBackReference("ur4")
	private User user;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="state")
	@JsonBackReference("utr2")
	private State state;

}
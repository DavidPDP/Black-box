package co.edu.icesi.metrocali.blackbox.entities.event_managment;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the t_002_users_track database table.
 * 
 */
@Entity
@Table(name="t_002_users_track", schema="event_managment")
@NamedQuery(name="UsersTrack.findAll", query="SELECT t FROM UsersTrack t")
public class UsersTrack implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UsersTrackPK id;

	@Column(name="end_time")
	private Timestamp endTime;

	@Column(name="start_time")
	private Timestamp startTime;

	//bi-directional many-to-one association to UsersRemark
	@OneToMany(mappedBy="UsersTrack")
	private List<UsersRemark> UsersRemarks;

	//bi-directional many-to-one association to State
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="state")
	private State State;
	
	//bi-directional many-to-one association to State
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user")
	private State user;

	public UsersTrack() {
	}

	public UsersTrackPK getId() {
		return this.id;
	}

	public void setId(UsersTrackPK id) {
		this.id = id;
	}

	public Timestamp getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public Timestamp getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public List<UsersRemark> getUsersRemarks() {
		return this.UsersRemarks;
	}

	public void setUsersRemarks(List<UsersRemark> UsersRemarks) {
		this.UsersRemarks = UsersRemarks;
	}

	public UsersRemark addUsersRemark(UsersRemark UsersRemark) {
		getUsersRemarks().add(UsersRemark);
		UsersRemark.setUsersTrack(this);

		return UsersRemark;
	}

	public UsersRemark removeUsersRemark(UsersRemark UsersRemark) {
		getUsersRemarks().remove(UsersRemark);
		UsersRemark.setUsersTrack(null);

		return UsersRemark;
	}

	public State getState() {
		return this.State;
	}

	public void setState(State State) {
		this.State = State;
	}

}
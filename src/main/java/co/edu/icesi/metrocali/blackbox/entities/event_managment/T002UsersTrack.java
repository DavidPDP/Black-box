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
@NamedQuery(name="T002UsersTrack.findAll", query="SELECT t FROM T002UsersTrack t")
public class T002UsersTrack implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private T002UsersTrackPK id;

	@Column(name="end_time")
	private Timestamp endTime;

	@Column(name="start_time")
	private Timestamp startTime;

	//bi-directional many-to-one association to T002UsersRemark
	@OneToMany(mappedBy="t002UsersTrack")
	private List<T002UsersRemark> t002UsersRemarks;

	//bi-directional many-to-one association to T002State
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="state", insertable=false, updatable=false)
	private T002State t002State;

	public T002UsersTrack() {
	}

	public T002UsersTrackPK getId() {
		return this.id;
	}

	public void setId(T002UsersTrackPK id) {
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

	public List<T002UsersRemark> getT002UsersRemarks() {
		return this.t002UsersRemarks;
	}

	public void setT002UsersRemarks(List<T002UsersRemark> t002UsersRemarks) {
		this.t002UsersRemarks = t002UsersRemarks;
	}

	public T002UsersRemark addT002UsersRemark(T002UsersRemark t002UsersRemark) {
		getT002UsersRemarks().add(t002UsersRemark);
		t002UsersRemark.setT002UsersTrack(this);

		return t002UsersRemark;
	}

	public T002UsersRemark removeT002UsersRemark(T002UsersRemark t002UsersRemark) {
		getT002UsersRemarks().remove(t002UsersRemark);
		t002UsersRemark.setT002UsersTrack(null);

		return t002UsersRemark;
	}

	public T002State getT002State() {
		return this.t002State;
	}

	public void setT002State(T002State t002State) {
		this.t002State = t002State;
	}

}
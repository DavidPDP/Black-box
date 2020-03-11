package co.edu.icesi.metrocali.blackbox.entities.event_managment;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the t_002_events_track database table.
 * 
 */
@Entity
@Table(name="t_002_events_track")
@NamedQuery(name="T002EventsTrack.findAll", query="SELECT t FROM T002EventsTrack t")
public class T002EventsTrack implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private T002EventsTrackPK id;

	@Column(name="end_time")
	private Timestamp endTime;

	private Integer priority;

	@Column(name="start_time")
	private Timestamp startTime;

	//bi-directional many-to-one association to T002EventsRemark
	@OneToMany(mappedBy="t002EventsTrack")
	private List<T002EventsRemark> t002EventsRemarks;

	//bi-directional many-to-one association to T002Event
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="event")
	private T002Event t002Event;

	//bi-directional many-to-one association to T002State
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="state")
	private T002State t002State;

	public T002EventsTrack() {
	}

	public T002EventsTrackPK getId() {
		return this.id;
	}

	public void setId(T002EventsTrackPK id) {
		this.id = id;
	}

	public Timestamp getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public Integer getPriority() {
		return this.priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Timestamp getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public List<T002EventsRemark> getT002EventsRemarks() {
		return this.t002EventsRemarks;
	}

	public void setT002EventsRemarks(List<T002EventsRemark> t002EventsRemarks) {
		this.t002EventsRemarks = t002EventsRemarks;
	}

	public T002EventsRemark addT002EventsRemark(T002EventsRemark t002EventsRemark) {
		getT002EventsRemarks().add(t002EventsRemark);
		t002EventsRemark.setT002EventsTrack(this);

		return t002EventsRemark;
	}

	public T002EventsRemark removeT002EventsRemark(T002EventsRemark t002EventsRemark) {
		getT002EventsRemarks().remove(t002EventsRemark);
		t002EventsRemark.setT002EventsTrack(null);

		return t002EventsRemark;
	}

	public T002Event getT002Event() {
		return this.t002Event;
	}

	public void setT002Event(T002Event t002Event) {
		this.t002Event = t002Event;
	}

	public T002State getT002State() {
		return this.t002State;
	}

	public void setT002State(T002State t002State) {
		this.t002State = t002State;
	}

}
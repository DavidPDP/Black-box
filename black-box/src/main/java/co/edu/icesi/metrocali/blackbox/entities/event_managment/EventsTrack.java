package co.edu.icesi.metrocali.blackbox.entities.event_managment;

import java.io.Serializable;
import javax.persistence.*;

import co.edu.icesi.metrocali.blackbox.entities.policies.User;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the t_002_events_track database table.
 * 
 */
@Entity
@Table(name="t_002_events_track", schema="event_managment")
@NamedQuery(name="EventsTrack.findAll", query="SELECT t FROM EventsTrack t")
public class EventsTrack implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EventsTrackPK id;

	@Column(name="end_time")
	private Timestamp endTime;

	private Integer priority;

	@Column(name="start_time")
	private Timestamp startTime;

	//bi-directional many-to-one association to EventsRemark
	@OneToMany(mappedBy="EventsTrack")
	private List<EventsRemark> EventsRemarks;

	//bi-directional many-to-one association to Event
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="event")
	private Event Event;

	//bi-directional many-to-one association to State
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="state")
	private State State;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="manager")
	private User manager;
	
	public EventsTrack() {
	}

	public EventsTrackPK getId() {
		return this.id;
	}

	public void setId(EventsTrackPK id) {
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

	public List<EventsRemark> getEventsRemarks() {
		return this.EventsRemarks;
	}

	public void setEventsRemarks(List<EventsRemark> EventsRemarks) {
		this.EventsRemarks = EventsRemarks;
	}

	public EventsRemark addEventsRemark(EventsRemark EventsRemark) {
		getEventsRemarks().add(EventsRemark);
		EventsRemark.setEventsTrack(this);

		return EventsRemark;
	}

	public EventsRemark removeEventsRemark(EventsRemark EventsRemark) {
		getEventsRemarks().remove(EventsRemark);
		EventsRemark.setEventsTrack(null);

		return EventsRemark;
	}

	public Event getEvent() {
		return this.Event;
	}

	public void setEvent(Event Event) {
		this.Event = Event;
	}

	public State getState() {
		return this.State;
	}

	public void setState(State State) {
		this.State = State;
	}

}
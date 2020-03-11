package co.edu.icesi.metrocali.blackbox.entities.event_managment;

import java.io.Serializable;
import javax.persistence.*;

import co.edu.icesi.metrocali.blackbox.entities.policies.User;

import java.sql.Timestamp;


/**
 * The persistent class for the t_002_events_remarks database table.
 * 
 */
@Entity
@Table(name="t_002_events_remarks" , schema="event_managment")
@NamedQuery(name="EventsRemark.findAll", query="SELECT t FROM EventsRemark t")
public class EventsRemark implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EventsRemarkPK id;

	private String content;

	private Timestamp creation;

	//bi-directional many-to-one association to EventsTrack
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="event", referencedColumnName="event"),
		@JoinColumn(name="manager", referencedColumnName="manager"),
		@JoinColumn(name="state", referencedColumnName="state"),
		@JoinColumn(name="track", referencedColumnName="id")
		})
	private EventsTrack EventsTrack;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="author")
	private User author;

	public EventsRemark() {
	}

	public EventsRemarkPK getId() {
		return this.id;
	}

	public void setId(EventsRemarkPK id) {
		this.id = id;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getCreation() {
		return this.creation;
	}

	public void setCreation(Timestamp creation) {
		this.creation = creation;
	}

	public EventsTrack getEventsTrack() {
		return this.EventsTrack;
	}

	public void setEventsTrack(EventsTrack EventsTrack) {
		this.EventsTrack = EventsTrack;
	}

}
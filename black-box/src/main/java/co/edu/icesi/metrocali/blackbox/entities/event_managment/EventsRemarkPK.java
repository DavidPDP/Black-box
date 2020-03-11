package co.edu.icesi.metrocali.blackbox.entities.event_managment;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the t_002_events_remarks database table.
 * 
 */
@Embeddable
public class EventsRemarkPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private Long id;

	@Column(insertable=false, updatable=false)
	private Integer author;

	@Column(insertable=false, updatable=false)
	private Long event;

	@Column(insertable=false, updatable=false)
	private Integer state;

	@Column(insertable=false, updatable=false)
	private Integer manager;

	@Column(insertable=false, updatable=false)
	private Long track;

	public EventsRemarkPK() {
	}
	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getAuthor() {
		return this.author;
	}
	public void setAuthor(Integer author) {
		this.author = author;
	}
	public Long getEvent() {
		return this.event;
	}
	public void setEvent(Long event) {
		this.event = event;
	}
	public Integer getState() {
		return this.state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getManager() {
		return this.manager;
	}
	public void setManager(Integer manager) {
		this.manager = manager;
	}
	public Long getTrack() {
		return this.track;
	}
	public void setTrack(Long track) {
		this.track = track;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EventsRemarkPK)) {
			return false;
		}
		EventsRemarkPK castOther = (EventsRemarkPK)other;
		return 
			this.id.equals(castOther.id)
			&& this.author.equals(castOther.author)
			&& this.event.equals(castOther.event)
			&& this.state.equals(castOther.state)
			&& this.manager.equals(castOther.manager)
			&& this.track.equals(castOther.track);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.id.hashCode();
		hash = hash * prime + this.author.hashCode();
		hash = hash * prime + this.event.hashCode();
		hash = hash * prime + this.state.hashCode();
		hash = hash * prime + this.manager.hashCode();
		hash = hash * prime + this.track.hashCode();
		
		return hash;
	}
}
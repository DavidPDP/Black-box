package co.edu.icesi.metrocali.blackbox.entities.event_managment;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the t_002_events_track database table.
 * 
 */
@Embeddable
public class T002EventsTrackPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private Long event;

	@Column(insertable=false, updatable=false)
	private Integer state;

	@Column(insertable=false, updatable=false)
	private Integer manager;

	private Long id;

	public T002EventsTrackPK() {
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
	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof T002EventsTrackPK)) {
			return false;
		}
		T002EventsTrackPK castOther = (T002EventsTrackPK)other;
		return 
			this.event.equals(castOther.event)
			&& this.state.equals(castOther.state)
			&& this.manager.equals(castOther.manager)
			&& this.id.equals(castOther.id);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.event.hashCode();
		hash = hash * prime + this.state.hashCode();
		hash = hash * prime + this.manager.hashCode();
		hash = hash * prime + this.id.hashCode();
		
		return hash;
	}
}
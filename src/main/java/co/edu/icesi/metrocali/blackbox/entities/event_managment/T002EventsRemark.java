package co.edu.icesi.metrocali.blackbox.entities.event_managment;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the t_002_events_remarks database table.
 * 
 */
@Entity
@Table(name="t_002_events_remarks", schema="event_managment")
@NamedQuery(name="T002EventsRemark.findAll", query="SELECT t FROM T002EventsRemark t")
public class T002EventsRemark implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private T002EventsRemarkPK id;

	private String content;

	private Timestamp creation;

	//bi-directional many-to-one association to T002EventsTrack
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="event", referencedColumnName="event", insertable=false, updatable=false),
		@JoinColumn(name="manager", referencedColumnName="manager", insertable=false, updatable=false),
		@JoinColumn(name="state", referencedColumnName="state", insertable=false, updatable=false),
		@JoinColumn(name="track", referencedColumnName="id", insertable=false, updatable=false)
		})
	private T002EventsTrack t002EventsTrack;

	public T002EventsRemark() {
	}

	public T002EventsRemarkPK getId() {
		return this.id;
	}

	public void setId(T002EventsRemarkPK id) {
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

	public T002EventsTrack getT002EventsTrack() {
		return this.t002EventsTrack;
	}

	public void setT002EventsTrack(T002EventsTrack t002EventsTrack) {
		this.t002EventsTrack = t002EventsTrack;
	}

}
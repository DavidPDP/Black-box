package co.edu.icesi.metrocali.blackbox.entities.event_managment;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the t_002_users_remarks database table.
 * 
 */
@Entity
@Table(name="t_002_users_remarks", schema="event_managment")
@NamedQuery(name="T002UsersRemark.findAll", query="SELECT t FROM T002UsersRemark t")
public class T002UsersRemark implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private T002UsersRemarkPK id;

	private String content;

	private Timestamp creation;

	//bi-directional many-to-one association to T002UsersTrack
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="owner", referencedColumnName="owner", insertable=false, updatable=false),
		@JoinColumn(name="state", referencedColumnName="state", insertable=false, updatable=false),
		@JoinColumn(name="track", referencedColumnName="id", insertable=false, updatable=false)
		})
	private T002UsersTrack t002UsersTrack;

	public T002UsersRemark() {
	}

	public T002UsersRemarkPK getId() {
		return this.id;
	}

	public void setId(T002UsersRemarkPK id) {
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

	public T002UsersTrack getT002UsersTrack() {
		return this.t002UsersTrack;
	}

	public void setT002UsersTrack(T002UsersTrack t002UsersTrack) {
		this.t002UsersTrack = t002UsersTrack;
	}

}
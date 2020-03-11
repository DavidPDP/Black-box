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
@NamedQuery(name="UsersRemark.findAll", query="SELECT t FROM UsersRemark t")
public class UsersRemark implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UsersRemarkPK id;

	private String content;

	private Timestamp creation;

	//bi-directional many-to-one association to UsersTrack
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="owner", referencedColumnName="owner"),
		@JoinColumn(name="state", referencedColumnName="state"),
		@JoinColumn(name="track", referencedColumnName="id")
		})
	private UsersTrack UsersTrack;

	public UsersRemark() {
	}

	public UsersRemarkPK getId() {
		return this.id;
	}

	public void setId(UsersRemarkPK id) {
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

	public UsersTrack getUsersTrack() {
		return this.UsersTrack;
	}

	public void setUsersTrack(UsersTrack UsersTrack) {
		this.UsersTrack = UsersTrack;
	}

}
package co.edu.icesi.metrocali.blackbox.entities.event_managment;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the t_002_users_remarks database table.
 * 
 */
@Embeddable
public class UsersRemarkPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private Long id;

	@Column(insertable=false, updatable=false)
	private Integer track;

	@Column(insertable=false, updatable=false)
	private Integer state;

	@Column(insertable=false, updatable=false)
	private Integer owner;

	public UsersRemarkPK() {
	}
	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getTrack() {
		return this.track;
	}
	public void setTrack(Integer track) {
		this.track = track;
	}
	public Integer getState() {
		return this.state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getOwner() {
		return this.owner;
	}
	public void setOwner(Integer owner) {
		this.owner = owner;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UsersRemarkPK)) {
			return false;
		}
		UsersRemarkPK castOther = (UsersRemarkPK)other;
		return 
			this.id.equals(castOther.id)
			&& this.track.equals(castOther.track)
			&& this.state.equals(castOther.state)
			&& this.owner.equals(castOther.owner);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.id.hashCode();
		hash = hash * prime + this.track.hashCode();
		hash = hash * prime + this.state.hashCode();
		hash = hash * prime + this.owner.hashCode();
		
		return hash;
	}
}
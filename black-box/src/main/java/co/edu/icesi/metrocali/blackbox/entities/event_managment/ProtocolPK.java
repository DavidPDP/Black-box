package co.edu.icesi.metrocali.blackbox.entities.event_managment;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the t_002_protocols database table.
 * 
 */
@Embeddable
public class ProtocolPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private Integer id;

	@Column(insertable=false, updatable=false)
	private Integer category;

	@Column(insertable=false, updatable=false)
	private Integer step;

	public ProtocolPK() {
	}
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCategory() {
		return this.category;
	}
	public void setCategory(Integer category) {
		this.category = category;
	}
	public Integer getStep() {
		return this.step;
	}
	public void setStep(Integer step) {
		this.step = step;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ProtocolPK)) {
			return false;
		}
		ProtocolPK castOther = (ProtocolPK)other;
		return 
			this.id.equals(castOther.id)
			&& this.category.equals(castOther.category)
			&& this.step.equals(castOther.step);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.id.hashCode();
		hash = hash * prime + this.category.hashCode();
		hash = hash * prime + this.step.hashCode();
		
		return hash;
	}
}
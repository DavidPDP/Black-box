package co.edu.icesi.metrocali.blackbox.entities.event_managment;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the t_002_flow_rules database table.
 * 
 */
@Embeddable
public class T002FlowRulePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private Integer current;

	@Column(insertable=false, updatable=false)
	private Integer next;

	public T002FlowRulePK() {
	}
	public Integer getCurrent() {
		return this.current;
	}
	public void setCurrent(Integer current) {
		this.current = current;
	}
	public Integer getNext() {
		return this.next;
	}
	public void setNext(Integer next) {
		this.next = next;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof T002FlowRulePK)) {
			return false;
		}
		T002FlowRulePK castOther = (T002FlowRulePK)other;
		return 
			this.current.equals(castOther.current)
			&& this.next.equals(castOther.next);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.current.hashCode();
		hash = hash * prime + this.next.hashCode();
		
		return hash;
	}
}
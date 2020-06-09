package co.edu.icesi.metrocali.blackbox.entities.event_managment;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the t_002_flow_rules database table.
 * 
 */
@Entity
@Table(name="t_002_flow_rules", schema="event_managment")
@NamedQuery(name="T002FlowRule.findAll", query="SELECT t FROM T002FlowRule t")
public class T002FlowRule implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private T002FlowRulePK id;

	//bi-directional one-to-one association to T002State
	@OneToOne(mappedBy="t002FlowRule1", fetch=FetchType.LAZY)
	private T002State t002State1;

	//bi-directional one-to-one association to T002State
	@OneToOne(mappedBy="t002FlowRule2", fetch=FetchType.LAZY)
	private T002State t002State2;

	public T002FlowRule() {
	}

	public T002FlowRulePK getId() {
		return this.id;
	}

	public void setId(T002FlowRulePK id) {
		this.id = id;
	}

	public T002State getT002State1() {
		return this.t002State1;
	}

	public void setT002State1(T002State t002State1) {
		this.t002State1 = t002State1;
	}

	public T002State getT002State2() {
		return this.t002State2;
	}

	public void setT002State2(T002State t002State2) {
		this.t002State2 = t002State2;
	}

}
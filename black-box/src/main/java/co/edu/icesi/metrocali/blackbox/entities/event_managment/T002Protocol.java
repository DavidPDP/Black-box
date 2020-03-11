package co.edu.icesi.metrocali.blackbox.entities.event_managment;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the t_002_protocols database table.
 * 
 */
@Entity
@Table(name="t_002_protocols")
@NamedQuery(name="T002Protocol.findAll", query="SELECT t FROM T002Protocol t")
public class T002Protocol implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private T002ProtocolPK id;

	@Column(name="step_order")
	private Integer stepOrder;

	//bi-directional many-to-one association to T002Category
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="category")
	private T002Category t002Category;

	//bi-directional many-to-one association to T002Step
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="step")
	private T002Step t002Step;

	public T002Protocol() {
	}

	public T002ProtocolPK getId() {
		return this.id;
	}

	public void setId(T002ProtocolPK id) {
		this.id = id;
	}

	public Integer getStepOrder() {
		return this.stepOrder;
	}

	public void setStepOrder(Integer stepOrder) {
		this.stepOrder = stepOrder;
	}

	public T002Category getT002Category() {
		return this.t002Category;
	}

	public void setT002Category(T002Category t002Category) {
		this.t002Category = t002Category;
	}

	public T002Step getT002Step() {
		return this.t002Step;
	}

	public void setT002Step(T002Step t002Step) {
		this.t002Step = t002Step;
	}

}
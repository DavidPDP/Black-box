package co.edu.icesi.metrocali.blackbox.entities.event_managment;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the t_002_protocols database table.
 * 
 */
@Entity
@Table(name="t_002_protocols", schema="event_managment")
@NamedQuery(name="Protocol.findAll", query="SELECT t FROM Protocol t")
public class Protocol implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ProtocolPK id;

	@Column(name="step_order")
	private Integer stepOrder;

	//bi-directional many-to-one association to Category
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="category")
	private Category Category;

	//bi-directional many-to-one association to Step
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="step")
	private Step Step;

	public Protocol() {
	}

	public ProtocolPK getId() {
		return this.id;
	}

	public void setId(ProtocolPK id) {
		this.id = id;
	}

	public Integer getStepOrder() {
		return this.stepOrder;
	}

	public void setStepOrder(Integer stepOrder) {
		this.stepOrder = stepOrder;
	}

	public Category getCategory() {
		return this.Category;
	}

	public void setCategory(Category Category) {
		this.Category = Category;
	}

	public Step getStep() {
		return this.Step;
	}

	public void setStep(Step Step) {
		this.Step = Step;
	}

}
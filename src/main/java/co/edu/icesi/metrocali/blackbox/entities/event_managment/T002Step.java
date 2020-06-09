package co.edu.icesi.metrocali.blackbox.entities.event_managment;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the t_002_steps database table.
 * 
 */
@Entity
@Table(name="t_002_steps", schema="event_managment")
@NamedQuery(name="T002Step.findAll", query="SELECT t FROM T002Step t")
public class T002Step implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String description;

	//bi-directional many-to-one association to T002Protocol
	@OneToMany(mappedBy="t002Step")
	private List<T002Protocol> t002Protocols;

	//bi-directional many-to-one association to T002StepType
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="step_type")
	private T002StepType t002StepType;

	public T002Step() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<T002Protocol> getT002Protocols() {
		return this.t002Protocols;
	}

	public void setT002Protocols(List<T002Protocol> t002Protocols) {
		this.t002Protocols = t002Protocols;
	}

	public T002Protocol addT002Protocol(T002Protocol t002Protocol) {
		getT002Protocols().add(t002Protocol);
		t002Protocol.setT002Step(this);

		return t002Protocol;
	}

	public T002Protocol removeT002Protocol(T002Protocol t002Protocol) {
		getT002Protocols().remove(t002Protocol);
		t002Protocol.setT002Step(null);

		return t002Protocol;
	}

	public T002StepType getT002StepType() {
		return this.t002StepType;
	}

	public void setT002StepType(T002StepType t002StepType) {
		this.t002StepType = t002StepType;
	}

}
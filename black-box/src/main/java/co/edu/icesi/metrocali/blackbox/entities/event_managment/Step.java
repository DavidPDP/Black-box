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
@NamedQuery(name="Step.findAll", query="SELECT t FROM Step t")
public class Step implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String description;

	//bi-directional many-to-one association to Protocol
	@OneToMany(mappedBy="Step")
	private List<Protocol> Protocols;

	//bi-directional many-to-one association to StepType
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="step_type")
	private StepType StepType;

	public Step() {
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

	public List<Protocol> getProtocols() {
		return this.Protocols;
	}

	public void setProtocols(List<Protocol> Protocols) {
		this.Protocols = Protocols;
	}

	public Protocol addProtocol(Protocol Protocol) {
		getProtocols().add(Protocol);
		Protocol.setStep(this);

		return Protocol;
	}

	public Protocol removeProtocol(Protocol Protocol) {
		getProtocols().remove(Protocol);
		Protocol.setStep(null);

		return Protocol;
	}

	public StepType getStepType() {
		return this.StepType;
	}

	public void setStepType(StepType StepType) {
		this.StepType = StepType;
	}

}
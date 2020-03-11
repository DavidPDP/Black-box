package co.edu.icesi.metrocali.blackbox.entities.event_managment;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the t_002_step_types database table.
 * 
 */
@Entity
@Table(name="t_002_step_types", schema="event_managment")
@NamedQuery(name="StepType.findAll", query="SELECT t FROM StepType t")
public class StepType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String name;

	//bi-directional many-to-one association to Step
	@OneToMany(mappedBy="StepType")
	private List<Step> Steps;

	public StepType() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Step> getSteps() {
		return this.Steps;
	}

	public void setSteps(List<Step> Steps) {
		this.Steps = Steps;
	}

	public Step addStep(Step Step) {
		getSteps().add(Step);
		Step.setStepType(this);

		return Step;
	}

	public Step removeStep(Step Step) {
		getSteps().remove(Step);
		Step.setStepType(null);

		return Step;
	}

}
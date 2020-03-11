package co.edu.icesi.metrocali.blackbox.entities.event_managment;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the t_002_step_types database table.
 * 
 */
@Entity
@Table(name="t_002_step_types")
@NamedQuery(name="T002StepType.findAll", query="SELECT t FROM T002StepType t")
public class T002StepType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String name;

	//bi-directional many-to-one association to T002Step
	@OneToMany(mappedBy="t002StepType")
	private List<T002Step> t002Steps;

	public T002StepType() {
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

	public List<T002Step> getT002Steps() {
		return this.t002Steps;
	}

	public void setT002Steps(List<T002Step> t002Steps) {
		this.t002Steps = t002Steps;
	}

	public T002Step addT002Step(T002Step t002Step) {
		getT002Steps().add(t002Step);
		t002Step.setT002StepType(this);

		return t002Step;
	}

	public T002Step removeT002Step(T002Step t002Step) {
		getT002Steps().remove(t002Step);
		t002Step.setT002StepType(null);

		return t002Step;
	}

}
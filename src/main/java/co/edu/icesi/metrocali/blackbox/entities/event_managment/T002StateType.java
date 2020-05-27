package co.edu.icesi.metrocali.blackbox.entities.event_managment;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the t_002_state_types database table.
 * 
 */
@Entity
@Table(name="t_002_state_types", schema="event_managment")
@NamedQuery(name="T002StateType.findAll", query="SELECT t FROM T002StateType t")
public class T002StateType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String name;

	//bi-directional many-to-one association to T002State
	@OneToMany(mappedBy="t002StateType")
	private List<T002State> t002States;

	public T002StateType() {
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

	public List<T002State> getT002States() {
		return this.t002States;
	}

	public void setT002States(List<T002State> t002States) {
		this.t002States = t002States;
	}

	public T002State addT002State(T002State t002State) {
		getT002States().add(t002State);
		t002State.setT002StateType(this);

		return t002State;
	}

	public T002State removeT002State(T002State t002State) {
		getT002States().remove(t002State);
		t002State.setT002StateType(null);

		return t002State;
	}

}
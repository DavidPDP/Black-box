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
@NamedQuery(name="StateType.findAll", query="SELECT t FROM StateType t")
public class StateType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String name;

	//bi-directional many-to-one association to State
	@OneToMany(mappedBy="StateType")
	private List<State> States;

	public StateType() {
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

	public List<State> getStates() {
		return this.States;
	}

	public void setStates(List<State> States) {
		this.States = States;
	}

	public State addState(State State) {
		getStates().add(State);
		State.setStateType(this);

		return State;
	}

	public State removeState(State State) {
		getStates().remove(State);
		State.setStateType(null);

		return State;
	}

}
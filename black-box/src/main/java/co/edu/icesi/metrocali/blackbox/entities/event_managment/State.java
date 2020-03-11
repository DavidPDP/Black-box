package co.edu.icesi.metrocali.blackbox.entities.event_managment;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the t_002_states database table.
 * 
 */
@Entity
@Table(name="t_002_states", schema="event_managment")
@NamedQuery(name="State.findAll", query="SELECT t FROM State t")
public class State implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String name;

	//bi-directional many-to-one association to EventsTrack
	@OneToMany(mappedBy="State")
	private List<EventsTrack> EventsTracks;

	//bi-directional many-to-many association to State
	@ManyToMany
	@JoinTable(
		name="t_002_flow_rules"
		, joinColumns={
			@JoinColumn(name="next")
			}
		, inverseJoinColumns={
			@JoinColumn(name="current")
			}
		)
	private List<State> States1;

	//bi-directional many-to-many association to State
	@ManyToMany(mappedBy="States1")
	private List<State> States2;

	//bi-directional many-to-one association to StateType
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="state_type")
	private StateType StateType;

	//bi-directional many-to-one association to UsersTrack
	@OneToMany(mappedBy="State")
	private List<UsersTrack> UsersTracks;

	public State() {
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

	public List<EventsTrack> getEventsTracks() {
		return this.EventsTracks;
	}

	public void setEventsTracks(List<EventsTrack> EventsTracks) {
		this.EventsTracks = EventsTracks;
	}

	public EventsTrack addEventsTrack(EventsTrack EventsTrack) {
		getEventsTracks().add(EventsTrack);
		EventsTrack.setState(this);

		return EventsTrack;
	}

	public EventsTrack removeEventsTrack(EventsTrack EventsTrack) {
		getEventsTracks().remove(EventsTrack);
		EventsTrack.setState(null);

		return EventsTrack;
	}

	public List<State> getStates1() {
		return this.States1;
	}

	public void setStates1(List<State> States1) {
		this.States1 = States1;
	}

	public List<State> getStates2() {
		return this.States2;
	}

	public void setStates2(List<State> States2) {
		this.States2 = States2;
	}

	public StateType getStateType() {
		return this.StateType;
	}

	public void setStateType(StateType StateType) {
		this.StateType = StateType;
	}

	public List<UsersTrack> getUsersTracks() {
		return this.UsersTracks;
	}

	public void setUsersTracks(List<UsersTrack> UsersTracks) {
		this.UsersTracks = UsersTracks;
	}

	public UsersTrack addUsersTrack(UsersTrack UsersTrack) {
		getUsersTracks().add(UsersTrack);
		UsersTrack.setState(this);

		return UsersTrack;
	}

	public UsersTrack removeUsersTrack(UsersTrack UsersTrack) {
		getUsersTracks().remove(UsersTrack);
		UsersTrack.setState(null);

		return UsersTrack;
	}

}
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
@NamedQuery(name="T002State.findAll", query="SELECT t FROM T002State t")
public class T002State implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String name;

	//bi-directional many-to-one association to T002EventsTrack
	@OneToMany(mappedBy="t002State")
	private List<T002EventsTrack> t002EventsTracks;

	//bi-directional many-to-one association to T002StateType
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="state_type")
	private T002StateType t002StateType;

	//bi-directional many-to-one association to T002UsersTrack
	@OneToMany(mappedBy="t002State")
	private List<T002UsersTrack> t002UsersTracks;

	//bi-directional one-to-one association to T002FlowRule
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id", referencedColumnName="next")
	private T002FlowRule t002FlowRule1;

	//bi-directional one-to-one association to T002FlowRule
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id", referencedColumnName="current")
	private T002FlowRule t002FlowRule2;

	public T002State() {
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

	public List<T002EventsTrack> getT002EventsTracks() {
		return this.t002EventsTracks;
	}

	public void setT002EventsTracks(List<T002EventsTrack> t002EventsTracks) {
		this.t002EventsTracks = t002EventsTracks;
	}

	public T002EventsTrack addT002EventsTrack(T002EventsTrack t002EventsTrack) {
		getT002EventsTracks().add(t002EventsTrack);
		t002EventsTrack.setT002State(this);

		return t002EventsTrack;
	}

	public T002EventsTrack removeT002EventsTrack(T002EventsTrack t002EventsTrack) {
		getT002EventsTracks().remove(t002EventsTrack);
		t002EventsTrack.setT002State(null);

		return t002EventsTrack;
	}

	public T002StateType getT002StateType() {
		return this.t002StateType;
	}

	public void setT002StateType(T002StateType t002StateType) {
		this.t002StateType = t002StateType;
	}

	public List<T002UsersTrack> getT002UsersTracks() {
		return this.t002UsersTracks;
	}

	public void setT002UsersTracks(List<T002UsersTrack> t002UsersTracks) {
		this.t002UsersTracks = t002UsersTracks;
	}

	public T002UsersTrack addT002UsersTrack(T002UsersTrack t002UsersTrack) {
		getT002UsersTracks().add(t002UsersTrack);
		t002UsersTrack.setT002State(this);

		return t002UsersTrack;
	}

	public T002UsersTrack removeT002UsersTrack(T002UsersTrack t002UsersTrack) {
		getT002UsersTracks().remove(t002UsersTrack);
		t002UsersTrack.setT002State(null);

		return t002UsersTrack;
	}

	public T002FlowRule getT002FlowRule1() {
		return this.t002FlowRule1;
	}

	public void setT002FlowRule1(T002FlowRule t002FlowRule1) {
		this.t002FlowRule1 = t002FlowRule1;
	}

	public T002FlowRule getT002FlowRule2() {
		return this.t002FlowRule2;
	}

	public void setT002FlowRule2(T002FlowRule t002FlowRule2) {
		this.t002FlowRule2 = t002FlowRule2;
	}

}
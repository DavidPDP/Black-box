package co.edu.icesi.metrocali.blackbox.entities.event_managment;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="t_002_states", schema="event_managment")
@Getter @Setter
public class State {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;

	@Column(name="name")
	private String name;

	@ManyToOne
	@JoinColumn(name="state_type")
	private StateType stateType;

	@OneToMany
	@JoinTable(
		schema="event_managment",
		name="t_002_flow_rules",
		joinColumns=@JoinColumn(name="current"),
		inverseJoinColumns=@JoinColumn(name="next")
	)
	@JsonIgnoreProperties("nextStates")
	private List<State> nextStates;
	
}
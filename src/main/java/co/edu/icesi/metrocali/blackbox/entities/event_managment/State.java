package co.edu.icesi.metrocali.blackbox.entities.event_managment;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Entity
@Table(name="t_002_states", schema="event_managment")
@Getter
@Setter
public class State {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;

	@Column(name="name")
	private String name;

	@OneToMany(mappedBy="state", fetch=FetchType.LAZY)
	@JsonManagedReference("etr3")
	private List<EventTrack> eventsTracks;

	@ManyToOne
	@JoinColumn(name="state_type")
	@JsonBackReference("sr2")
	private StateType stateType;

	@OneToMany(mappedBy="state", fetch=FetchType.LAZY)
	@JsonManagedReference("utr2")
	private List<UsersTrack> usersTracks;
}
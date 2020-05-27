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

@Entity
@Table(name="t_002_steps", schema="event_managment")
@Getter
@Setter
public class Step {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;

	@Column(name="description")
	private String description;

	@OneToMany(mappedBy="step", fetch=FetchType.LAZY)
	@JsonManagedReference("sr1")
	private List<Protocol> protocols;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="step_type")
	@JsonBackReference("str1")
	private StepType stepType;

}
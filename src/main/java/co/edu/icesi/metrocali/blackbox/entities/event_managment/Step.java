package co.edu.icesi.metrocali.blackbox.entities.event_managment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="t_002_steps", schema="event_managment")
@Getter @Setter
public class Step {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Generated(GenerationTime.ALWAYS)
	@Column(name="code")
	private String code;

	@Column(name="description")
	private String description;

	@ManyToOne
	@JoinColumn(name="step_type")
	private StepType stepType;

}
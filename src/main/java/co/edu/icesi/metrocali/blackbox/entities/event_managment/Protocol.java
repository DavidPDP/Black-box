package co.edu.icesi.metrocali.blackbox.entities.event_managment;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="t_002_protocols", schema="event_managment")
@Getter @Setter
public class Protocol {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;

	@Column(name="step_order")
	private Integer stepOrder;
	
	@ManyToOne
	@JoinColumn(name="category")
	@JsonBackReference("category-protocol")
	private Category category;

	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name="step")
	private Step step;

}
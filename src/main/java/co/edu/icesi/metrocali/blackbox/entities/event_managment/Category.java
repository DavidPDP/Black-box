package co.edu.icesi.metrocali.blackbox.entities.event_managment;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents a category regarding the types of events that 
 * can occur within the system. This class is an Aggregate Root 
 * and its children are: Protocol
 */
@Entity
@Table(name="t_002_categories", schema="event_managment")
@Getter @Setter
public class Category {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;

	@Column(name="base_priority")
	@JsonProperty("base_priority")
	private Integer basePriority;

	@Column(name="name")
	private String name;

	@ManyToOne
	@JoinColumn(name="parent")
	private Category category;

	@OneToMany(mappedBy="category", cascade=CascadeType.ALL)
	@JsonManagedReference("category-protocol")
	private List<Protocol> protocols;	

}
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
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import co.edu.icesi.metrocali.blackbox.utils.CategorySerializer;
import lombok.Getter;
import lombok.Setter;

@JsonSerialize(using = CategorySerializer.class)
@Entity
@Table(name="t_002_categories", schema="event_managment")
@Getter
@Setter
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

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="parent")
	@JsonBackReference("cr2")
	private Category category;

	@OneToMany(mappedBy="category")
	@JsonManagedReference("cr2")
	private List<Category> categories;

	@OneToMany(mappedBy="category")
	@JsonManagedReference("cr1")
	private List<Event> events;

	@OneToMany(mappedBy="category")
	@JsonManagedReference("pr1")
	private List<Protocol> protocols;

	@Override
	public String toString() {
		return this.id + "-" + this.name;
	}
}
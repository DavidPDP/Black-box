package co.edu.icesi.metrocali.blackbox.entities.event_managment;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the t_002_categories database table.
 * 
 */
@Entity
@Table(name="t_002_categories", schema="event_managment")
@NamedQuery(name="Category.findAll", query="SELECT t FROM Category t")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Column(name="base_priority")
	private Integer basePriority;

	private String name;

	//bi-directional many-to-one association to Category
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="parent")
	private Category Category;

	//bi-directional many-to-one association to Category
	@OneToMany(mappedBy="Category")
	private List<Category> Categories;

	//bi-directional many-to-one association to Event
	@OneToMany(mappedBy="Category")
	private List<Event> Events;

	//bi-directional many-to-one association to Protocol
	@OneToMany(mappedBy="Category")
	private List<Protocol> Protocols;

	public Category() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBasePriority() {
		return this.basePriority;
	}

	public void setBasePriority(Integer basePriority) {
		this.basePriority = basePriority;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getCategory() {
		return this.Category;
	}

	public void setCategory(Category Category) {
		this.Category = Category;
	}

	public List<Category> getCategories() {
		return this.Categories;
	}

	public void setCategories(List<Category> Categories) {
		this.Categories = Categories;
	}

	public Category addCategory(Category Category) {
		getCategories().add(Category);
		Category.setCategory(this);

		return Category;
	}

	public Category removeCategory(Category Category) {
		getCategories().remove(Category);
		Category.setCategory(null);

		return Category;
	}

	public List<Event> getEvents() {
		return this.Events;
	}

	public void setEvents(List<Event> Events) {
		this.Events = Events;
	}

	public Event addEvent(Event Event) {
		getEvents().add(Event);
		Event.setCategory(this);

		return Event;
	}

	public Event removeEvent(Event Event) {
		getEvents().remove(Event);
		Event.setCategory(null);

		return Event;
	}

	public List<Protocol> getProtocols() {
		return this.Protocols;
	}

	public void setProtocols(List<Protocol> Protocols) {
		this.Protocols = Protocols;
	}

	public Protocol addProtocol(Protocol Protocol) {
		getProtocols().add(Protocol);
		Protocol.setCategory(this);

		return Protocol;
	}

	public Protocol removeProtocol(Protocol Protocol) {
		getProtocols().remove(Protocol);
		Protocol.setCategory(null);

		return Protocol;
	}

}
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
@NamedQuery(name="T002Category.findAll", query="SELECT t FROM T002Category t")
public class T002Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Column(name="base_priority")
	private Integer basePriority;

	private String name;

	//bi-directional many-to-one association to T002Category
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="parent")
	private T002Category t002Category;

	//bi-directional many-to-one association to T002Category
	@OneToMany(mappedBy="t002Category")
	private List<T002Category> t002Categories;

	//bi-directional many-to-one association to T002Event
	@OneToMany(mappedBy="t002Category")
	private List<T002Event> t002Events;

	//bi-directional many-to-one association to T002Protocol
	@OneToMany(mappedBy="t002Category")
	private List<T002Protocol> t002Protocols;

	public T002Category() {
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

	public T002Category getT002Category() {
		return this.t002Category;
	}

	public void setT002Category(T002Category t002Category) {
		this.t002Category = t002Category;
	}

	public List<T002Category> getT002Categories() {
		return this.t002Categories;
	}

	public void setT002Categories(List<T002Category> t002Categories) {
		this.t002Categories = t002Categories;
	}

	public T002Category addT002Category(T002Category t002Category) {
		getT002Categories().add(t002Category);
		t002Category.setT002Category(this);

		return t002Category;
	}

	public T002Category removeT002Category(T002Category t002Category) {
		getT002Categories().remove(t002Category);
		t002Category.setT002Category(null);

		return t002Category;
	}

	public List<T002Event> getT002Events() {
		return this.t002Events;
	}

	public void setT002Events(List<T002Event> t002Events) {
		this.t002Events = t002Events;
	}

	public T002Event addT002Event(T002Event t002Event) {
		getT002Events().add(t002Event);
		t002Event.setT002Category(this);

		return t002Event;
	}

	public T002Event removeT002Event(T002Event t002Event) {
		getT002Events().remove(t002Event);
		t002Event.setT002Category(null);

		return t002Event;
	}

	public List<T002Protocol> getT002Protocols() {
		return this.t002Protocols;
	}

	public void setT002Protocols(List<T002Protocol> t002Protocols) {
		this.t002Protocols = t002Protocols;
	}

	public T002Protocol addT002Protocol(T002Protocol t002Protocol) {
		getT002Protocols().add(t002Protocol);
		t002Protocol.setT002Category(this);

		return t002Protocol;
	}

	public T002Protocol removeT002Protocol(T002Protocol t002Protocol) {
		getT002Protocols().remove(t002Protocol);
		t002Protocol.setT002Category(null);

		return t002Protocol;
	}

}
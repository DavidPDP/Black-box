package co.edu.icesi.metrocali.blackbox.entities.event_managment;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the t_002_events database table.
 * 
 */
@Entity
@Table(name="t_002_events")
@NamedQuery(name="T002Event.findAll", query="SELECT t FROM T002Event t")
public class T002Event implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	private Timestamp creation;

	private String description;

	private String title;

	//bi-directional many-to-one association to T002Category
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="category")
	private T002Category t002Category;

	//bi-directional many-to-one association to T002EventsTrack
	@OneToMany(mappedBy="t002Event")
	private List<T002EventsTrack> t002EventsTracks;

	public T002Event() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getCreation() {
		return this.creation;
	}

	public void setCreation(Timestamp creation) {
		this.creation = creation;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public T002Category getT002Category() {
		return this.t002Category;
	}

	public void setT002Category(T002Category t002Category) {
		this.t002Category = t002Category;
	}

	public List<T002EventsTrack> getT002EventsTracks() {
		return this.t002EventsTracks;
	}

	public void setT002EventsTracks(List<T002EventsTrack> t002EventsTracks) {
		this.t002EventsTracks = t002EventsTracks;
	}

	public T002EventsTrack addT002EventsTrack(T002EventsTrack t002EventsTrack) {
		getT002EventsTracks().add(t002EventsTrack);
		t002EventsTrack.setT002Event(this);

		return t002EventsTrack;
	}

	public T002EventsTrack removeT002EventsTrack(T002EventsTrack t002EventsTrack) {
		getT002EventsTracks().remove(t002EventsTrack);
		t002EventsTrack.setT002Event(null);

		return t002EventsTrack;
	}

}
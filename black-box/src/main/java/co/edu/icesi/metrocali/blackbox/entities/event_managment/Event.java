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
@Table(name="t_002_events", schema="event_managment")
@NamedQuery(name="Event.findAll", query="SELECT t FROM Event t")
public class Event implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	private Timestamp creation;

	private String description;

	private String title;

	//bi-directional many-to-one association to Category
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="category")
	private Category Category;

	//bi-directional many-to-one association to EventsTrack
	@OneToMany(mappedBy="Event")
	private List<EventsTrack> EventsTracks;

	public Event() {
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

	public Category getCategory() {
		return this.Category;
	}

	public void setCategory(Category Category) {
		this.Category = Category;
	}

	public List<EventsTrack> getEventsTracks() {
		return this.EventsTracks;
	}

	public void setEventsTracks(List<EventsTrack> EventsTracks) {
		this.EventsTracks = EventsTracks;
	}

	public EventsTrack addEventsTrack(EventsTrack EventsTrack) {
		getEventsTracks().add(EventsTrack);
		EventsTrack.setEvent(this);

		return EventsTrack;
	}

	public EventsTrack removeEventsTrack(EventsTrack EventsTrack) {
		getEventsTracks().remove(EventsTrack);
		EventsTrack.setEvent(null);

		return EventsTrack;
	}

}
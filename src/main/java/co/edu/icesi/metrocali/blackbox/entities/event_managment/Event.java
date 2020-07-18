package co.edu.icesi.metrocali.blackbox.entities.event_managment;

import java.sql.Timestamp;
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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="t_002_events", schema="event_managment")
@Getter @Setter
public class Event {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Generated(GenerationTime.ALWAYS)
	@Column(name="code")
	private String code;

	@CreationTimestamp
	@Column(name="creation")
	private Timestamp creation;

	@Column(name="description")
	private String description;

	@Column(name="source")
	private Long source;
	
	@Column(name="source_type")
	private String sourceType;

	@Column(name="title")
	private String title;

	@ManyToOne
	@JoinColumn(name="category")
	private Category category;

	@OneToMany(mappedBy="event", cascade=CascadeType.ALL, orphanRemoval=true)
	@JsonManagedReference("event-event_track")
	private List<EventTrack> eventsTracks;
	
	@OneToMany(mappedBy="event", cascade=CascadeType.ALL, orphanRemoval=true)
	@JsonManagedReference("event-protocol_track")
	private List<ProtocolTrack> protocolsTracks;
	
}
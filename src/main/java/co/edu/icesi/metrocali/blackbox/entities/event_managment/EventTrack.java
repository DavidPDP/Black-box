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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import co.edu.icesi.metrocali.blackbox.entities.policies.User;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="t_002_events_track", schema="event_managment")
@Getter @Setter
public class EventTrack {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Generated(GenerationTime.ALWAYS)
	@Column(name="code")
	private String code;

	@Column(name="end_time")
	private Timestamp endTime;

	@Column(name="priority")
	private Integer priority;

	@CreationTimestamp
	@Column(name="start_time")
	private Timestamp startTime;

	@OneToMany(mappedBy="eventTrack", cascade= CascadeType.ALL)
	@JsonManagedReference("event_track-event_remark")
	private List<EventRemark> eventsRemarks;
	
	@ManyToOne
	@JoinColumn(name="manager")
	private User user;

	@ManyToOne
	@JoinColumn(name="event")
	@JsonBackReference("event-event_track")
	private Event event;

	@ManyToOne
	@JoinColumn(name="state")
	private State state;
	
}
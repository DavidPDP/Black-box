package co.edu.icesi.metrocali.blackbox.entities.event_managment;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
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

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import co.edu.icesi.metrocali.blackbox.entities.policies.User;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="t_002_events_track", schema="event_managment")
@Getter
@Setter
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

	@Generated(GenerationTime.ALWAYS)
	@Column(name="start_time", insertable=false, updatable=false)
	private Timestamp startTime;

	@OneToMany(mappedBy="eventsTrack",
		cascade= {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE}
	)
	@JsonManagedReference("err1")
	private List<EventRemark> eventsRemarks;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="manager")
	@JsonBackReference("ur2")
	private User user;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="event")
	@JsonBackReference("etr1")
	private Event event;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="state")
	@JsonBackReference("etr3")
	private State state;
	
	@Override
	public String toString() {
		String state = this.getState() != null ? this.getState().getName() : "";
		return "id: " + this.id + " - code: " + this.code 
				+ " - startTime: " + this.startTime + " - endTime: " 
				+ this.endTime + " - state: " + state;
	}
	
}
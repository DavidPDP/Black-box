package co.edu.icesi.metrocali.blackbox.entities.event_managment;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import co.edu.icesi.metrocali.blackbox.entities.policies.User;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="t_002_events_remarks", schema="event_managment")
@Getter @Setter
public class EventRemark {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;

	@Generated(GenerationTime.ALWAYS)
	@Column(name="code")
	private String code;
	
	@Column(name="content")
	private String content;

	@Generated(GenerationTime.ALWAYS)
	@Column(name="creation", insertable=false, updatable=false)
	private Timestamp creation;

	@ManyToOne
	@JoinColumn(name="author")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="track")
	@JsonBackReference("event_track-event_remark")
	private EventTrack eventTrack;

}
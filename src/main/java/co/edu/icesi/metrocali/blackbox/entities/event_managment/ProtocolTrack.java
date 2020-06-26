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

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="t_002_protocols_tracks", schema="event_managment")
@Getter @Setter
public class ProtocolTrack {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="done")
	private Boolean done;
	
	@Generated(GenerationTime.ALWAYS)
	@Column(name="creation", insertable=false, updatable=false)
	private Timestamp creation;
	
	@ManyToOne
	@JoinColumn(name="event")
	@JsonBackReference("event-protocol_track")
	private Event event;
	
	@ManyToOne
	@JoinColumn(name="protocol")
	private Protocol protocol;
	
}

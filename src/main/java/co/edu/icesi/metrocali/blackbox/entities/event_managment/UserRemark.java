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

import com.fasterxml.jackson.annotation.JsonBackReference;

import co.edu.icesi.metrocali.blackbox.entities.policies.User;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="t_002_users_remarks", schema="event_managment")
@Getter @Setter
public class UserRemark {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;

	@Column(name="content")
	private String content;

	@Column(name="creation")
	private Timestamp creation;

	@ManyToOne
	@JoinColumn(name="author")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="track")
	@JsonBackReference("user_track-user_remark")
	private UserTrack usersTrack;

}
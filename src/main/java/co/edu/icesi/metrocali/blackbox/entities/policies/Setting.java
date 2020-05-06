package co.edu.icesi.metrocali.blackbox.entities.policies;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="t_002_settings", schema="policies")
@Getter
@Setter
public class Setting {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="creation")
	private Timestamp creation;

	@Column(name="key")
	private String key;

	@Column(name="type")
	private String type;

	@Column(name="value")
	private String value;

	@Column(name="version")
	private String version;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "creator")
	@JsonBackReference("ser1")
	private User user;

}

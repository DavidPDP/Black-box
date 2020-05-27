package co.edu.icesi.metrocali.blackbox.entities.policies;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name="t_002_settings", schema="policies")
@Getter
@Setter
public class Setting implements Serializable {
	
	private static final long serialVersionUID = 2217554849373549216L;

	@Id
	private Integer id;

	private Timestamp creation;

	private String key;

	private String type;

	private String value;

	private String version;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="creator")
	private User user;

}
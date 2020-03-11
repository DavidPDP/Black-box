package co.edu.icesi.metrocali.blackbox.entities.policies;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the t_002_settings database table.
 * 
 */
@Entity
@Table(name="t_002_settings")
@NamedQuery(name="T002Setting.findAll", query="SELECT t FROM T002Setting t")
public class T002Setting implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private Timestamp creation;

	private String key;

	private String type;

	private String value;

	private String version;

	//bi-directional many-to-one association to T002User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="creator")
	private T002User t002User;

	public T002Setting() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getCreation() {
		return this.creation;
	}

	public void setCreation(Timestamp creation) {
		this.creation = creation;
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public T002User getT002User() {
		return this.t002User;
	}

	public void setT002User(T002User t002User) {
		this.t002User = t002User;
	}

}
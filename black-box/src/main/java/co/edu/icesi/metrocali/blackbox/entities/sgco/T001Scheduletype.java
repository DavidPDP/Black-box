package co.edu.icesi.metrocali.blackbox.entities.sgco;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the t_001_scheduletypes database table.
 * 
 */
@Entity
@Table(name="t_001_scheduletypes")
@NamedQuery(name="T001Scheduletype.findAll", query="SELECT t FROM T001Scheduletype t")
public class T001Scheduletype implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long scheduletypeid;

	private String description;

	private Long planversionid;

	private String shortname;

	public T001Scheduletype() {
	}

	public Long getScheduletypeid() {
		return this.scheduletypeid;
	}

	public void setScheduletypeid(Long scheduletypeid) {
		this.scheduletypeid = scheduletypeid;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getPlanversionid() {
		return this.planversionid;
	}

	public void setPlanversionid(Long planversionid) {
		this.planversionid = planversionid;
	}

	public String getShortname() {
		return this.shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

}
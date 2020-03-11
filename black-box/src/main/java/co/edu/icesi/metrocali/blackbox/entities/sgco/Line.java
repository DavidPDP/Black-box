package co.edu.icesi.metrocali.blackbox.entities.sgco;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the t_001_lines database table.
 * 
 */
@Entity
@Table(name="t_001_lines", schema="sgco")
@NamedQuery(name="Line.findAll", query="SELECT t FROM Line t")
public class Line implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long lineid;

	private String description;

	private Long planversionid;

	private String shortname;

	public Line() {
	}

	public Long getLineid() {
		return this.lineid;
	}

	public void setLineid(Long lineid) {
		this.lineid = lineid;
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
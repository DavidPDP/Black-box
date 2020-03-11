package co.edu.icesi.metrocali.blackbox.entities.sgco;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the t_001_linestops database table.
 * 
 */
@Entity
@Table(name="t_001_linestops", schema="sgco")
@NamedQuery(name="Linestop.findAll", query="SELECT t FROM Linestop t")
public class Linestop implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long linestopid;

	private Long lineid;

	private Long linevariant;

	private Long linevarianttype;

	private Long orientation;

	private Long planversionid;

	private Timestamp registerdate;

	private Long stopid;

	private Long stopsequence;

	public Linestop() {
	}

	public Long getLinestopid() {
		return this.linestopid;
	}

	public void setLinestopid(Long linestopid) {
		this.linestopid = linestopid;
	}

	public Long getLineid() {
		return this.lineid;
	}

	public void setLineid(Long lineid) {
		this.lineid = lineid;
	}

	public Long getLinevariant() {
		return this.linevariant;
	}

	public void setLinevariant(Long linevariant) {
		this.linevariant = linevariant;
	}

	public Long getLinevarianttype() {
		return this.linevarianttype;
	}

	public void setLinevarianttype(Long linevarianttype) {
		this.linevarianttype = linevarianttype;
	}

	public Long getOrientation() {
		return this.orientation;
	}

	public void setOrientation(Long orientation) {
		this.orientation = orientation;
	}

	public Long getPlanversionid() {
		return this.planversionid;
	}

	public void setPlanversionid(Long planversionid) {
		this.planversionid = planversionid;
	}

	public Timestamp getRegisterdate() {
		return this.registerdate;
	}

	public void setRegisterdate(Timestamp registerdate) {
		this.registerdate = registerdate;
	}

	public Long getStopid() {
		return this.stopid;
	}

	public void setStopid(Long stopid) {
		this.stopid = stopid;
	}

	public Long getStopsequence() {
		return this.stopsequence;
	}

	public void setStopsequence(Long stopsequence) {
		this.stopsequence = stopsequence;
	}

}
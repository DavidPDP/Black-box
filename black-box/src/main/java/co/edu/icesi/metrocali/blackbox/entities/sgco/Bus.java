package co.edu.icesi.metrocali.blackbox.entities.sgco;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the t_001_buses database table.
 * 
 */
@Entity
@Table(name="t_001_buses", schema="sgco")
@NamedQuery(name="Bus.findAll", query="SELECT t FROM Bus t")
public class Bus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long busid;

	private Long busnumber;

	private Long bustypeid;

	private String identification;

	private Long planversionid;

	public Bus() {
	}

	public Long getBusid() {
		return this.busid;
	}

	public void setBusid(Long busid) {
		this.busid = busid;
	}

	public Long getBusnumber() {
		return this.busnumber;
	}

	public void setBusnumber(Long busnumber) {
		this.busnumber = busnumber;
	}

	public Long getBustypeid() {
		return this.bustypeid;
	}

	public void setBustypeid(Long bustypeid) {
		this.bustypeid = bustypeid;
	}

	public String getIdentification() {
		return this.identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public Long getPlanversionid() {
		return this.planversionid;
	}

	public void setPlanversionid(Long planversionid) {
		this.planversionid = planversionid;
	}

}
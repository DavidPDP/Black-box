package co.edu.icesi.metrocali.blackbox.entities.sgco;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the t_001_stops database table.
 * 
 */
@Entity
@Table(name="t_001_stops")
@NamedQuery(name="T001Stop.findAll", query="SELECT t FROM T001Stop t")
public class T001Stop implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long stopid;

	private double decimallatitude;

	private double decimallongitude;

	@Column(name="gps_x")
	private double gpsX;

	@Column(name="gps_y")
	private double gpsY;

	private String longname;

	private Long planversionid;

	private String shortname;

	public T001Stop() {
	}

	public Long getStopid() {
		return this.stopid;
	}

	public void setStopid(Long stopid) {
		this.stopid = stopid;
	}

	public double getDecimallatitude() {
		return this.decimallatitude;
	}

	public void setDecimallatitude(double decimallatitude) {
		this.decimallatitude = decimallatitude;
	}

	public double getDecimallongitude() {
		return this.decimallongitude;
	}

	public void setDecimallongitude(double decimallongitude) {
		this.decimallongitude = decimallongitude;
	}

	public double getGpsX() {
		return this.gpsX;
	}

	public void setGpsX(double gpsX) {
		this.gpsX = gpsX;
	}

	public double getGpsY() {
		return this.gpsY;
	}

	public void setGpsY(double gpsY) {
		this.gpsY = gpsY;
	}

	public String getLongname() {
		return this.longname;
	}

	public void setLongname(String longname) {
		this.longname = longname;
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
package co.edu.icesi.metrocali.blackbox.entities.sgco;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the t_001_trips database table.
 * 
 */
@Entity
@Table(name="t_001_trips")
@NamedQuery(name="T001Trip.findAll", query="SELECT t FROM T001Trip t")
public class T001Trip implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long tripid;

	private String description;

	private Long endstopid;

	private Long lineid;

	private Integer linevariant;

	private Integer orientation;

	private Long planversionid;

	private Timestamp registerdate;

	private Long scheduleprofileid;

	private Long scheduletypeid;

	private Long startstopid;

	private String starttime;

	private Long taskid;

	private Long tripsequence;

	private Long triptypeid;

	public T001Trip() {
	}

	public Long getTripid() {
		return this.tripid;
	}

	public void setTripid(Long tripid) {
		this.tripid = tripid;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getEndstopid() {
		return this.endstopid;
	}

	public void setEndstopid(Long endstopid) {
		this.endstopid = endstopid;
	}

	public Long getLineid() {
		return this.lineid;
	}

	public void setLineid(Long lineid) {
		this.lineid = lineid;
	}

	public Integer getLinevariant() {
		return this.linevariant;
	}

	public void setLinevariant(Integer linevariant) {
		this.linevariant = linevariant;
	}

	public Integer getOrientation() {
		return this.orientation;
	}

	public void setOrientation(Integer orientation) {
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

	public Long getScheduleprofileid() {
		return this.scheduleprofileid;
	}

	public void setScheduleprofileid(Long scheduleprofileid) {
		this.scheduleprofileid = scheduleprofileid;
	}

	public Long getScheduletypeid() {
		return this.scheduletypeid;
	}

	public void setScheduletypeid(Long scheduletypeid) {
		this.scheduletypeid = scheduletypeid;
	}

	public Long getStartstopid() {
		return this.startstopid;
	}

	public void setStartstopid(Long startstopid) {
		this.startstopid = startstopid;
	}

	public String getStarttime() {
		return this.starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public Long getTaskid() {
		return this.taskid;
	}

	public void setTaskid(Long taskid) {
		this.taskid = taskid;
	}

	public Long getTripsequence() {
		return this.tripsequence;
	}

	public void setTripsequence(Long tripsequence) {
		this.tripsequence = tripsequence;
	}

	public Long getTriptypeid() {
		return this.triptypeid;
	}

	public void setTriptypeid(Long triptypeid) {
		this.triptypeid = triptypeid;
	}

}
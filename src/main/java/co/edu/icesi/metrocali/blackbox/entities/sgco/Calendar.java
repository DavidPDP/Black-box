package co.edu.icesi.metrocali.blackbox.entities.sgco;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the t_001_calendars database table.
 * 
 */
@Entity
@Table(name="t_001_calendars", schema="sgco")
@NamedQuery(name="Calendar.findAll", query="SELECT t FROM Calendar t")
public class Calendar implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long calendarid;

	private Timestamp operationday;

	private Long planversionid;

	private Long scheduletypeid;

	public Calendar() {
	}

	public Long getCalendarid() {
		return this.calendarid;
	}

	public void setCalendarid(Long calendarid) {
		this.calendarid = calendarid;
	}

	public Timestamp getOperationday() {
		return this.operationday;
	}

	public void setOperationday(Timestamp operationday) {
		this.operationday = operationday;
	}

	public Long getPlanversionid() {
		return this.planversionid;
	}

	public void setPlanversionid(Long planversionid) {
		this.planversionid = planversionid;
	}

	public Long getScheduletypeid() {
		return this.scheduletypeid;
	}

	public void setScheduletypeid(Long scheduletypeid) {
		this.scheduletypeid = scheduletypeid;
	}

}
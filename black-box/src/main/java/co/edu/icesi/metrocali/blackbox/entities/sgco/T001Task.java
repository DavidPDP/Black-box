package co.edu.icesi.metrocali.blackbox.entities.sgco;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the t_001_tasks database table.
 * 
 */
@Entity
@Table(name="t_001_tasks")
@NamedQuery(name="T001Task.findAll", query="SELECT t FROM T001Task t")
public class T001Task implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long taskid;

	@Column(name="lines_lineid")
	private Long linesLineid;

	private Long planversionid;

	private Long scheduletypeid;

	public T001Task() {
	}

	public Long getTaskid() {
		return this.taskid;
	}

	public void setTaskid(Long taskid) {
		this.taskid = taskid;
	}

	public Long getLinesLineid() {
		return this.linesLineid;
	}

	public void setLinesLineid(Long linesLineid) {
		this.linesLineid = linesLineid;
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
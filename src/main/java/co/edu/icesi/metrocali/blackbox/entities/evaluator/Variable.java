package co.edu.icesi.metrocali.blackbox.entities.evaluator;


import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the t_003_variables database table.
 * 
 */
@Entity
@Table(name = "t_003_variables", schema = "aviom_eval")
@NamedQuery(name = "Variable.findAll", query = "SELECT v FROM Variable v")
public class Variable implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "name")
	private String nameVariable;

	@Column(name = "classification")
	private String classification;

	@Column(name = "description_var")
	private String descriptionVar;

	@Column(name = "is_kpi")
	private Boolean isKPI;



	public Variable() {
	}

	public String getNameVariable() {
		return this.nameVariable;
	}

	public void setNameVariable(String nameVariable) {
		this.nameVariable = nameVariable;
	}

	public String getClassification() {
		return this.classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public String getDescriptionVar() {
		return this.descriptionVar;
	}

	public void setDescriptionVar(String descriptionVar) {
		this.descriptionVar = descriptionVar;
	}

	public Boolean getIsKPI() {
		return this.isKPI;
	}

	public void setIsKPI(boolean isKPI) {
		this.isKPI = isKPI;
	}

}

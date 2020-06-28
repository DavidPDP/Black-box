package co.edu.icesi.metrocali.blackbox.entities.evaluator;


import java.io.Serializable;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * The persistent class for the t_003_variables database table.
 * 
 */
@Entity
@Table(name = "t_003_variables", schema = "aviom_eval")
@NamedQuery(name = "Variable.findAll", query = "SELECT v FROM Variable v")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Variable implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "name")
	@JsonProperty("name")
	private String nameVariable;

	@Column(name = "classification")
	@JsonProperty("classification")
	private String classification;

	@Column(name = "description_var")
	@JsonProperty("description")
	private String descriptionVar;

	@Column(name = "is_kpi")
	@JsonProperty("is_kpi")
	private Boolean isKPI;
}

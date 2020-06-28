package co.edu.icesi.metrocali.blackbox.entities.evaluator;


import java.io.Serializable;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;


/**
 * The persistent class for the t_003_parameters database table.
 * 
 */
@Entity
@Table(name="t_003_parameters", schema = "aviom_eval")
@NamedQuery(name="EvalParameter.findAll", query="SELECT t FROM EvalParameter t")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class EvalParameter implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_parameter")
	//TODO: Must be a sequence.
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonProperty("id")
	private Integer idParameter;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="enable_end")
	@JsonProperty("enable_end")
	private Date enableEnd;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="enable_start")
	@JsonProperty("enable_start")
	private Date enableStart;

	@Column(name="name")
	@JsonProperty("name")
	private String name;

	@Column(name="value")
	@JsonProperty("value")
	private double value;


	public EvalParameter( Date enableStart, Date enableEnd, String name, double value) {
		this.enableStart=enableStart;
		this.enableEnd=enableEnd;
		this.name=name;
		this.value=value;
	}	

}
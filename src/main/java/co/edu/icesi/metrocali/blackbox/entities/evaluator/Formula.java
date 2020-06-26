package co.edu.icesi.metrocali.blackbox.entities.evaluator;

import java.io.Serializable;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;


/**
 * The persistent class for the t_003_measurements database table.
 * 
 */
@Entity
@Table(name="t_003_formulas", schema = "aviom_eval" )
@NamedQuery(name="Formula.findAll", query="SELECT t FROM Formula t")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Formula implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_formula")
	//TODO: Must be a sequence.
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idFormula;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="end_date")
	private Date endDate;

    @Column(name="formula_expression")
	private String expression;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="start_date")
	private Date startDate;


	//bi-directional many-to-one association to T003Variable
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="variable_name")
	private Variable variable;

}
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
@Table(name = "t_003_measurements", schema = "aviom_eval")
@NamedQuery(name = "Measurement.findAll", query = "SELECT t FROM Measurement t")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Measurement implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_measurement")
	// TODO: Must be a sequence.
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idMeasurement;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_date")
	private Date endDate;

	private double value;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_date")
	private Date startDate;


	// bi-directional many-to-one association to T003Variable
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "variable_name")
	private Variable variable;

}

package co.edu.icesi.metrocali.blackbox.entities.evaluator;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="t_003_measurements_parameter", schema = "aviom_eval")
@NamedQuery(name="MeasurementsParameters.findAll", query="SELECT t FROM MeasurementsParameters t")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class MeasurementsParameters {

    @Id
    @Column(name="id_measurements_parameter")
    //TODO: Must be a sequence.
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_parameter")
    private EvalParameter parameter;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_measurement")
    private Measurement measurement;

    
}
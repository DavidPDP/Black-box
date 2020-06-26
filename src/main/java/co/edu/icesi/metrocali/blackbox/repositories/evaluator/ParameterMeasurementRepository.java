package co.edu.icesi.metrocali.blackbox.repositories.evaluator;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import co.edu.icesi.metrocali.blackbox.entities.evaluator.ParameterMeasurement;

@Repository
public interface ParameterMeasurementRepository
        extends CrudRepository<ParameterMeasurement, Integer> {

    public <S extends ParameterMeasurement> List<S> saveAll(Iterable<S> entities);
}

package co.edu.icesi.metrocali.blackbox.repositories.evaluator;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import co.edu.icesi.metrocali.blackbox.entities.evaluator.MeasurementsParameters;


@Repository
public interface MeasurementsParametersRepository
        extends CrudRepository<MeasurementsParameters, Integer> {

    public <S extends MeasurementsParameters> List<S> saveAll(Iterable<S> entities);
}

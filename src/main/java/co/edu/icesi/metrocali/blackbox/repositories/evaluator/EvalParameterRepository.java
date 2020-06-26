package co.edu.icesi.metrocali.blackbox.repositories.evaluator;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import co.edu.icesi.metrocali.blackbox.entities.evaluator.EvalParameter;


@Repository
public interface EvalParameterRepository extends CrudRepository<EvalParameter, Integer> {

    public <S extends EvalParameter> S save(S entity);

    public Optional<EvalParameter> findById(Integer id);

    public List<EvalParameter> findAll();

    public boolean existByName(String name);

    public List<EvalParameter> findByName(String name);

    public EvalParameter findByNameAndEnableEndIsNull(String name);

    public List<EvalParameter> findByEnableEndIsNull();


    public List<EvalParameter> findByNameAndEnableStartGreaterThanAndEnableEndLessThan(String name,
            Date enableStart, Date enableEnd);


    public List<EvalParameter> findByEnableStartGreaterThanAndEnableEndLessThan(Date enableStart,
            Date enableEnd);



}

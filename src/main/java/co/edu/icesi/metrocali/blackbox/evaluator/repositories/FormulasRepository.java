package co.edu.icesi.metrocali.blackbox.evaluator.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import co.edu.icesi.metrocali.blackbox.evaluator.entities.Formula;
import co.edu.icesi.metrocali.blackbox.evaluator.entities.Variable;


@Repository
public interface FormulasRepository extends CrudRepository<Formula, Integer> {
    
    public List<Formula> findByVariableAndEndDateIsNull(Variable variable);
}
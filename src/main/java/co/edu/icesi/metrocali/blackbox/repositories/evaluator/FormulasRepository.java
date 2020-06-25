package co.edu.icesi.metrocali.blackbox.repositories.evaluator;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import co.edu.icesi.metrocali.blackbox.entities.evaluator.Formula;
import co.edu.icesi.metrocali.blackbox.entities.evaluator.Variable;


@Repository
public interface FormulasRepository extends CrudRepository<Formula, Integer> {
    
    public List<Formula> findByVariableAndEndDateIsNull(Variable variable);
}
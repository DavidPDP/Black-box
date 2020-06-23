package co.edu.icesi.metrocali.evaluator.repositories;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import co.edu.icesi.metrocali.evaluator.entities.Formula;
import co.edu.icesi.metrocali.evaluator.entities.Variable;


@Repository
public interface FormulasRepository extends CrudRepository<Formula, Integer> {
    
    public List<Formula> findByVariableAndEndDateIsNull(Variable variable);
}
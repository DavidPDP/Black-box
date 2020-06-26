package co.edu.icesi.metrocali.blackbox.repositories.evaluator;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import co.edu.icesi.metrocali.blackbox.entities.evaluator.Formula;
import co.edu.icesi.metrocali.blackbox.entities.evaluator.Variable;


@Repository
public interface FormulasRepository extends CrudRepository<Formula, Integer> {

    public Formula findTop1ByVariableAndEndDateIsNull(Variable variable);

    public List<Formula> findByVariable(Variable variable);

    public List<Formula> findAll();

    public void findByEndDateIsNull();

    @Query(value = "SELECT f FROM Formula f, Variable v WHERE f.variable = v AND v.isKPI = TRUE")
    public List<Formula> findByKPIs();

    @Query(value = "SELECT f FROM Formula f, Variable v WHERE f.endDate IS NULL AND f.variable = v AND v.isKPI = TRUE")
    public List<Formula> findActivesByKPIs();
}

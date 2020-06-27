package co.edu.icesi.metrocali.blackbox.repositories.evaluator;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import co.edu.icesi.metrocali.blackbox.entities.evaluator.Formula;
import co.edu.icesi.metrocali.blackbox.entities.evaluator.Variable;


@Repository
public interface FormulasRepository extends CrudRepository<Formula, Integer> {

    public Formula findTop1ByVariableAndEndDateIsNull(Variable variable);

    public List<Formula> findByVariable(Variable variable);

    public List<Formula> findAll();

    public List<Formula> findByEndDateIsNull();

    @Query(value = "SELECT f FROM Formula f, Variable v WHERE f.variable = v AND v.isKPI = TRUE")
    public List<Formula> findByKPIs();

    @Query(value = "SELECT f FROM Formula f, Variable v WHERE f.endDate IS NULL AND f.variable = v AND v.isKPI = TRUE")
    public List<Formula> findActivesByKPIs();

    @Query(value = "SELECT f FROM Formula f, Variable v WHERE f.variable.nameVariable = :variable_name AND f.endDate IS NULL")
    public Formula findActiveByVariable(@Param("variable_name") String variableName);
}

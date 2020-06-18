package co.edu.icesi.metrocali.blackbox.repositories.evaluator;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import co.edu.icesi.metrocali.blackbox.entities.evaluator.Variable;


@Repository
public interface VariableRepository extends CrudRepository<Variable, String>{
    
    public <S extends Variable> S save(S entity);

    public Optional<Variable> findById(String id);
    public List<Variable> findAll();
    public List<Variable> findByIsKPI(boolean isKpi);
    public Variable findByNameVariable(String name);
       

}
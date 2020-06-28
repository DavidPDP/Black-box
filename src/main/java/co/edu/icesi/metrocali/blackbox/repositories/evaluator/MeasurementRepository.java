package co.edu.icesi.metrocali.blackbox.repositories.evaluator;


import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import co.edu.icesi.metrocali.blackbox.entities.evaluator.Measurement;
import co.edu.icesi.metrocali.blackbox.entities.evaluator.Variable;


@Repository
public interface MeasurementRepository extends CrudRepository<Measurement, Integer> {

        public <S extends Measurement> S save(S entity);


        public <S extends Measurement> List<S> saveAll(Iterable<S> entities);

        public List<Measurement> findAll();

        public List<Measurement> findByVariable(Variable variable);

        @Query("select m from Measurement m where m.variable.nameVariable in :names and m.startDate >= :s_date and m.endDate <= :e_date")
        public List<Measurement> findByVariablesAndDatesBetween(
                        @Param("names") List<String> variableNames, @Param("s_date") Date start,
                        @Param("e_date") Date end);

        public List<Measurement> findTop5ByVariableOrderByEndDateDesc(Variable variable);


}

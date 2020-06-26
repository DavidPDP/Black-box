package co.edu.icesi.metrocali.blackbox.api.evaluator;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.edu.icesi.metrocali.blackbox.entities.evaluator.EvalParameter;
import co.edu.icesi.metrocali.blackbox.entities.evaluator.Measurement;
import co.edu.icesi.metrocali.blackbox.entities.evaluator.ParameterMeasurement;
import co.edu.icesi.metrocali.blackbox.repositories.evaluator.EvalParameterRepository;
import co.edu.icesi.metrocali.blackbox.repositories.evaluator.MeasurementRepository;
import co.edu.icesi.metrocali.blackbox.repositories.evaluator.ParameterMeasurementRepository;
import co.edu.icesi.metrocali.blackbox.repositories.evaluator.VariableRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * EvaluatorRest
 */
@RestController()
@RequestMapping(path = "/evaluator/measurements")
@Log4j2
public class HTTPRestMeasurementsAPI {



    @Autowired
    private VariableRepository variableRepository;

    @Autowired
    private MeasurementRepository measurementRepository;

    @Autowired
    private EvalParameterRepository parameterRepository;

    @Autowired
    private ParameterMeasurementRepository parameterMeasurementRepository;

    private HashMap<String, List<Measurement>> getLastVariableMeasurements(
            List<String> variablesNames) throws Exception {
        HashMap<String, List<Measurement>> measurementsByVariable = new HashMap<>();


        if (variablesNames != null && variablesNames.size() > 0) {
            for (String name : variablesNames) {
                List<Measurement> measurements =
                        measurementRepository.findTop5ByVariableOrderByEndDateDesc(
                                variableRepository.findByNameVariable(name));
                measurementsByVariable.put(name, measurements);
            }

            return measurementsByVariable;
        } else {
            throw new Exception("Se debe especificar, al menos, el nombre de una variable");
        }

    }

    // dashboard
    /**
     * Returns a map with measurements for the indicated variables. Each measurements group is
     * ordered in an descending way
     * 
     * @param names     a list of variable names which measurements will be obtained
     * @param startDate a date which represents the 'from' date from measurements (for each
     *                  variable) will be obtained. Is an optional parameter
     * @param endDate   a date which represents the 'until' date until measurements (for each
     *                  variable) will be obtained. Is an optional parameter.
     * @param lasts     a boolean which specify if the values to calculate will be the last five
     *                  values. It overrides the dates parameters. if its value, the service will
     *                  return the last five measurements for each variable.
     * @return a hashmap (json) with measurements for each variable. key is variable name and value
     *         is a list with measurements for that variable.
     * @see #getLastVariableMeasurements
     */
    @GetMapping
    public ResponseEntity<HashMap<String, List<Measurement>>> getVariableMeasurements(
            @RequestParam(required = true, value = "names") List<String> variablesNames,
            @RequestParam(required = false, name = "s_date") Date startDate,
            @RequestParam(required = false, name = "e_date") Date endDate,
            @RequestParam(required = false, name = "lasts") boolean lasts) throws Exception {

        HashMap<String, List<Measurement>> measurementsByVariable = new HashMap<>();
        try {
            if (variablesNames != null && variablesNames.size() > 0) {
                if (lasts) {
                    measurementsByVariable = getLastVariableMeasurements(variablesNames);
                } else {

                    Date start = Date
                            .from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());

                    Date end = Date.from(LocalDate.now().plusDays(1)
                            .atStartOfDay(ZoneId.systemDefault()).toInstant());

                    if (startDate != null) {
                        start = startDate;
                    }
                    if (endDate != null) {
                        end = endDate;
                    }


                    for (String name : variablesNames) {
                        List<Measurement> measurements = new ArrayList<>();
                        measurements = measurementRepository
                                .findByVariableAndStartDateGreaterThanAndEndDateLessThanOrderByEndDateDesc(
                                        variableRepository.findByNameVariable(name), start, end);
                        measurementsByVariable.put(name, measurements);
                    }
                }
                return ResponseEntity.ok().body(measurementsByVariable);
            } else {
                throw new Exception("Se debe especificar, al menos, el nombre de una variable.");
            }
        } catch (Exception e) {
            log.error("error on getting last parameters", e);
            throw e;
        }

    }

    @PostMapping
    public ResponseEntity<?> saveAll(@RequestBody List<Measurement> measurements) {
        try {
            List<Measurement> newMeasurements = measurementRepository.saveAll(measurements);
            List<ParameterMeasurement> relationships = new ArrayList<>();
            List<EvalParameter> activeParameters = parameterRepository.findByEnableEndIsNull();
            for (Measurement measurement : newMeasurements) {
                for (EvalParameter parameter : activeParameters) {
                    ParameterMeasurement relationship = new ParameterMeasurement();
                    relationship.setMeasurement(measurement);
                    relationship.setParameter(parameter);
                    relationships.add(relationship);
                }
            }
            parameterMeasurementRepository.saveAll(relationships);
            return ResponseEntity.ok().body(newMeasurements);
        } catch (Exception e) {
            log.error("error on getting last parameters", e);
            throw e;
        }
    }


}

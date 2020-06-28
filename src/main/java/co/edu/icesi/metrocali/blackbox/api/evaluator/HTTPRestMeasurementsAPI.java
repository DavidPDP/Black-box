package co.edu.icesi.metrocali.blackbox.api.evaluator;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.edu.icesi.metrocali.blackbox.entities.evaluator.EvalParameter;
import co.edu.icesi.metrocali.blackbox.entities.evaluator.Measurement;
import co.edu.icesi.metrocali.blackbox.entities.evaluator.MeasurementsParameters;
import co.edu.icesi.metrocali.blackbox.repositories.evaluator.EvalParameterRepository;
import co.edu.icesi.metrocali.blackbox.repositories.evaluator.MeasurementRepository;
import co.edu.icesi.metrocali.blackbox.repositories.evaluator.MeasurementsParametersRepository;
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
    private MeasurementsParametersRepository parameterMeasurementRepository;

    private List<Measurement> getLastMeasurementsByVariable(List<String> variablesNames)
            throws Exception {

        List<Measurement> measurements = new ArrayList<>();
        if (variablesNames != null && variablesNames.size() > 0) {
            for (String name : variablesNames) {
                measurements.addAll(measurementRepository.findTop5ByVariableOrderByEndDateDesc(
                        variableRepository.findByNameVariable(name)));
            }
            return measurements;
        } else {
            throw new Exception("Se debe especificar, al menos, el nombre de una variable");
        }
    }

    @GetMapping("/{variable_name}/lasts")
    public ResponseEntity<List<Measurement>> getLastMeasurementsByVariable(
            @PathVariable(name = "variable_name", required = true) String variableName) {
        try {
            List<Measurement> measurements =
                    measurementRepository.findTop5ByVariableOrderByEndDateDesc(
                            variableRepository.findByNameVariable(variableName));
            return ResponseEntity.ok().body(measurements);
        } catch (Exception e) {
            log.error("Erorr at GET '/evaluator/" + variableName + "'/lasts", e);
            throw e;
        }
    }

    @GetMapping("/lasts")
    public ResponseEntity<List<Measurement>> getLastMeasurements(
            @RequestParam(required = true, value = "names") List<String> variablesNames)
            throws Exception {

        try {
            return ResponseEntity.ok().body(getLastMeasurementsByVariable(variablesNames));
        } catch (Exception e) {
            log.error("Erorr at GET '/lasts", e);
            throw e;
        }

    }


    @GetMapping
    public ResponseEntity<List<Measurement>> getVariableMeasurements(
            @RequestParam(required = true, value = "names") List<String> variablesNames,
            @RequestParam(required = false, name = "s_date") Date startDate,
            @RequestParam(required = false, name = "e_date") Date endDate,
            @RequestParam(required = false, name = "lasts") boolean lasts) throws Exception {

        List<Measurement> measurements = new ArrayList<>();
        try {
            if (variablesNames != null && variablesNames.size() > 0) {
                if (lasts) {
                    measurements = getLastMeasurementsByVariable(variablesNames);
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

                    measurements.addAll(measurementRepository.findByVariablesAndDatesBetween(
                            variablesNames, start, end));

                }
                return ResponseEntity.ok().body(measurements);
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
            List<MeasurementsParameters> relationships = new ArrayList<>();
            List<EvalParameter> activeParameters = parameterRepository.findByEnableEndIsNull();
            for (Measurement measurement : newMeasurements) {
                for (EvalParameter parameter : activeParameters) {
                    MeasurementsParameters relationship = new MeasurementsParameters();
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

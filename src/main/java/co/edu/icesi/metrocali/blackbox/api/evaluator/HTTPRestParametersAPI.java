package co.edu.icesi.metrocali.blackbox.api.evaluator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import lombok.extern.log4j.Log4j2;
import co.edu.icesi.metrocali.blackbox.entities.evaluator.EvalParameter;
import co.edu.icesi.metrocali.blackbox.repositories.evaluator.EvalParameterRepository;


@RestController
@RequestMapping(path = "/evaluator/parameters")
@Log4j2


public class HTTPRestParametersAPI {

    @Autowired
    private EvalParameterRepository parametersRepository;

    @GetMapping
    public ResponseEntity<List<EvalParameter>> getAllParameter(
            @RequestParam(required = false, name = "enable_from") Date enableStart,
            @RequestParam(required = false, name = "enable_until") Date enableEnd) {
        try {
            List<EvalParameter> parameters = new ArrayList<>();

            if (enableStart != null && enableEnd != null) {
                parameters = parametersRepository
                        .findByEnableStartGreaterThanAndEnableEndLessThan(enableStart, enableEnd);
            } else {
                parameters = parametersRepository.findAll();
            }
            return ResponseEntity.ok().body(parameters);
        } catch (Exception e) {
            log.error("Error at GET /evaluator/parameters", e);
            throw e;
        }
    }

    @GetMapping("/active")
    public ResponseEntity<List<EvalParameter>> getActiveParameters() {
        try {
            List<EvalParameter> parameters = parametersRepository.findByEnableEndIsNull();
            return ResponseEntity.ok().body(parameters);

        } catch (Exception e) {
            log.error("Error at /evaluator/active", e);
            throw e;
        }

    }

    @GetMapping("/{parameter_name}")
    public ResponseEntity<List<EvalParameter>> getParameterByName(
            @PathVariable(name = "parameter_name", required = true) String parameterName) {
        try {
            List<EvalParameter> parameters = new ArrayList<>();
            parameters = parametersRepository.findByName(parameterName);
            return ResponseEntity.ok().body(parameters);
        } catch (Exception e) {
            log.error("Error at GET /evaluator/parameters/" + parameterName, e);
            throw e;
        }

    }

    @GetMapping("/{parameter_name}/filtered")
    public ResponseEntity<List<EvalParameter>> getFilteredParameter(
            @PathVariable(name = "parameter_name", required = true) String parameterName,
            @RequestParam(name = "enable_from", required = true) Date enableStart,
            @RequestParam(required = false, name = "enable_until") Date enableEnd) {
        try {
            List<EvalParameter> parameters = new ArrayList<>();
            if (enableStart != null && enableStart != null) {
                parameters = parametersRepository
                        .findByNameAndEnableStartGreaterThanAndEnableEndLessThan(parameterName,
                                enableStart, enableEnd);
            } else {
                parameters = parametersRepository.findByName(parameterName);
            }
            return ResponseEntity.ok().body(parameters);
        } catch (Exception e) {
            log.error("Error at GET /evaluator/parameters/" + parameterName + "/filtered", e);
            throw e;
        }
    }

    @GetMapping("/{parameter_name}/active")
    public ResponseEntity<EvalParameter> getActiveParameter(
            @PathVariable(name = "parameter_name", required = true) String parameterName) {
        try {
            EvalParameter parameter =
                    parametersRepository.findByNameAndEnableEndIsNull(parameterName);

            return ResponseEntity.ok().body(parameter);
        } catch (Exception e) {
            log.error("Error at GET /evaluator/parameters/" + parameterName + "/active", e);
            throw e;
        }
    }


    @PutMapping("/{parameter_name}")
    public ResponseEntity<EvalParameter> updateParameter(
            @PathVariable(name = "parameter_name", required = true) String parameterName,
            @RequestBody(required = true) EvalParameter parameter) throws Exception {

        try {
            parameter.setName(parameterName);

            EvalParameter oldParameter =
                    parametersRepository.findByNameAndEnableEndIsNull(parameterName);
            if (oldParameter == null) {
                throw new Exception("El parámetro: " + parameterName + " no existe.");
            }

            Date enableStart = parameter.getEnableStart();
            if (enableStart != null) {
                oldParameter.setEnableEnd(enableStart);
                parameterRepository.save(oldParameter);
            } else {
                throw new NullPointerException("EnableStart must not be null");
            }

            EvalParameter newParameter = parametersRepository.save(parameter);
            return ResponseEntity.ok().body(newParameter);
        } catch (Exception e) {
            log.error("at PUT /evaluator/parameters", e);
            throw e;
        }
    }
}

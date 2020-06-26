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
import java.sql.Timestamp;
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
    public ResponseEntity<List<EvalParameter>> getParameters(
            @RequestParam(required = false, name = "name") String name,
            @RequestParam(required = false, name = "enable_from") Date enableStart,
            @RequestParam(required = false, name = "enable_until") Date enableEnd,
            @RequestParam(required = false, name = "active") boolean active) throws Exception {

        try {
            List<EvalParameter> parameters = new ArrayList<>();
            if (name != null && !name.isEmpty()) {
                if (active) {
                    parameters.add(parametersRepository.findByNameAndEnableEndIsNull(name));
                } else if (enableStart != null && enableStart != null) {
                    parameters = parametersRepository
                            .findByNameAndEnableStartGreaterThanAndEnableEndLessThan(name,
                                    enableStart, enableEnd);
                } else {
                    parameters = parametersRepository.findByName(name);
                }

            } else {
                if (active) {
                    parameters = parametersRepository.findByEnableEndIsNull();
                } else if (enableStart != null && enableEnd != null) {
                    parameters =
                            parametersRepository.findByEnableStartGreaterThanAndEnableEndLessThan(
                                    enableStart, enableEnd);
                } else {
                    parameters = parametersRepository.findAll();
                }
            }
            return ResponseEntity.ok().body(parameters);
        } catch (

        Exception e) {
            log.error("Error at GET /parameters", e);
            throw e;
        }

    }

    @PutMapping
    public ResponseEntity<EvalParameter> updateParameter(
            @RequestBody(required = true) EvalParameter parameter) throws Exception {

        try {
            Date currentDate = Date.from(new Timestamp(System.currentTimeMillis()).toInstant());
            String parameterName = parameter.getName();
            if (parametersRepository.existByName(parameterName)) {
                throw new Exception("El parámetro: " + parameter.getName() + " no existe.");
            }
            EvalParameter oldParameter =
                    parametersRepository.findByNameAndEnableEndIsNull(parameterName);
            oldParameter.setEnableEnd(currentDate);

            EvalParameter newParameter = parameter;
            newParameter.setEnableStart(currentDate);
            newParameter.setEnableEnd(null);
            newParameter = parametersRepository.save(newParameter);
            return ResponseEntity.ok().body(newParameter);
        } catch (Exception e) {
            log.error("at PUT /parameters", e);
            throw e;
        }
    }
}

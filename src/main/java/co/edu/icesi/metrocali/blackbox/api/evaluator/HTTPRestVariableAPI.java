package co.edu.icesi.metrocali.blackbox.api.evaluator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.edu.icesi.metrocali.blackbox.entities.evaluator.Variable;
import co.edu.icesi.metrocali.blackbox.repositories.evaluator.VariableRepository;
import java.util.List;
import java.util.ArrayList;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping(path = "/evaluator/variables")
@Log4j2
public class HTTPRestVariableAPI {

    @Autowired
    private VariableRepository variableRepository;

    @GetMapping
    public ResponseEntity<Object> getVariables() {

        List<Variable> variables = new ArrayList<>();
        try {
            variables = variableRepository.findAll();
            return ResponseEntity.ok().body(variables);
        } catch (Exception e) {
            log.error("error at GET /evaluator/variables", e);
            throw e;
        }

    }

    @PostMapping
    public ResponseEntity<Variable> saveVariable(@RequestBody Variable variable) throws Exception {

        try {
            if (variableRepository.existsById(variable.getNameVariable())) {
                throw new Exception("La variable: " + variable.getNameVariable() + " ya existe.");
            }

            Variable newVariable = variableRepository.save(variable);

            return ResponseEntity.ok().body(newVariable);
        } catch (Exception e) {
            log.error("Error at POST /variables", e);
            throw e;
        }

    }

    @PutMapping
    public ResponseEntity<Variable> updateVariable(@RequestBody Variable variable)
            throws Exception {

        try {
            if (!variableRepository.existsById(variable.getNameVariable())) {
                throw new Exception("La variable: " + variable.getNameVariable() + " no existe.");
            }

            Variable newVariable = variableRepository.save(variable);

            return ResponseEntity.ok().body(newVariable);
        } catch (Exception e) {
            log.error("Error at POST /variables", e);
            throw e;
        }

    }

}

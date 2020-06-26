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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import co.edu.icesi.metrocali.blackbox.entities.evaluator.Formula;
import co.edu.icesi.metrocali.blackbox.repositories.evaluator.FormulasRepository;
import co.edu.icesi.metrocali.blackbox.repositories.evaluator.VariableRepository;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping(path = "/evaluator/formulas")
@Log4j2
public class HTTPRestFormulasAPI {

    @Autowired
    private FormulasRepository formulasRepository;

    @Autowired
    private VariableRepository variableRepository;


    @GetMapping("/{variable_name}")
    public ResponseEntity<List<Formula>> retrieve(@PathVariable(name = "variable_name") String variableName,
            @RequestParam(name = "active", required = false, defaultValue = "false") boolean active)
            throws Exception {


        List<Formula> formulas = new ArrayList<>();
        try {
            if (variableName != null) {
                if (!variableRepository.existsById(variableName)) {
                    throw new Exception("La variable: " + variableName + " no existe");
                }
                if (active) {
                    formulas.add(formulasRepository.findTop1ByVariableAndEndDateIsNull(
                            variableRepository.findByNameVariable(variableName)));
                } else {
                    formulas = formulasRepository
                            .findByVariable(variableRepository.findByNameVariable(variableName));
                }
            } else if (active) {
                formulasRepository.findByEndDateIsNull();
            } else {
                formulasRepository.findAll();
            }
            return ResponseEntity.ok().body(formulas);
        } catch (

        Exception e) {
            log.error("Error at '/evaluator/formulas", e);
            throw e;
        }
    }

    @PostMapping
    public ResponseEntity<Formula> saveFormula(@RequestBody Formula formula) {
        try {
            Formula lastFormula =
                    formulasRepository.findTop1ByVariableAndEndDateIsNull(formula.getVariable());
            Date currentDate =
                    Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
            lastFormula.setEndDate(currentDate);

            formula.setSartDate(currentDate);
            formula.setEndDate(null);

            Formula newFormula = formulasRepository.save(formula);
            return ResponseEntity.ok().body(newFormula);
        } catch (Exception e) {
            log.error("Error at POST '/evaluator/formulas' ", e);
            throw e;
        }
    }

}

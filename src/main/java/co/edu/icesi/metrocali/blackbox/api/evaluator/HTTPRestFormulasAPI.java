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
import lombok.var;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping(path = "/evaluator/formulas")
@Log4j2
public class HTTPRestFormulasAPI {

    @Autowired
    private FormulasRepository formulasRepository;

    @Autowired
    private VariableRepository variableRepository;

    @GetMapping
    public ResponseEntity<List<Formula>> getFormulas() {
        try {
            List<Formula> formulas = new ArrayList<>();
            formulas = formulasRepository.findAll();
            return ResponseEntity.ok().body(formulas);
        } catch (Exception e) {
            log.error("Error at 'evaluator/variables'", e);
            throw e;
        }

    }

    @GetMapping("{variable_name}")
    public ResponseEntity<List<Formula>> getFormulasByVariable(
            @PathVariable(name = "variable_name", required = true) String variableName) {
        try {
            List<Formula> formulas = new ArrayList<>();
            formulas = formulasRepository
                    .findByVariable(variableRepository.findByNameVariable(variableName));
            return ResponseEntity.ok().body(formulas);
        } catch (Exception e) {
            log.error("Error at 'evaluator/variables/" + variableName + "'", e);
            throw e;
        }

    }

    @GetMapping("/filtered")
    public ResponseEntity<List<Formula>> getFormulasByFilter(
            @RequestParam(name = "variable_name", required = false) String variableName,
            @RequestParam(name = "active", required = false, defaultValue = "false") boolean active,
            @RequestParam(name = "is_kpi", required = false, defaultValue = "false") boolean isKPI)
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
                if (isKPI) {
                    formulasRepository.findActivesByKPIs();
                } else {
                    formulasRepository.findByEndDateIsNull();
                }
            } else if(isKPI){
                formulasRepository.findByKPIs();
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

            formula.setStartDate(currentDate);
            formula.setEndDate(null);

            Formula newFormula = formulasRepository.save(formula);
            return ResponseEntity.ok().body(newFormula);
        } catch (Exception e) {
            log.error("Error at POST '/evaluator/formulas' ", e);
            throw e;
        }
    }

}

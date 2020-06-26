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

    @GetMapping("/{variable_name}")
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

    @GetMapping("/kpi")
    public ResponseEntity<List<Formula>> getFormulasByKPI() {
        try {
            List<Formula> formulas = new ArrayList<>();
            formulas = formulasRepository.findByKPIs();
            return ResponseEntity.ok().body(formulas);
        } catch (Exception e) {
            log.error("Error at 'evaluator/kpis'", e);
            throw e;
        }
    }

    @GetMapping("/kpi/active")
    public ResponseEntity<List<Formula>> getFormulasByKPIAndActive() {
        try {
            List<Formula> formulas = new ArrayList<>();
            formulas = formulasRepository.findActivesByKPIs();
            return ResponseEntity.ok().body(formulas);
        } catch (Exception e) {
            log.error("Error at 'evaluator/kpis'", e);
            throw e;
        }
    }

    @GetMapping("/active")
    public ResponseEntity<List<Formula>> getFormulasByActive() {
        try {
            List<Formula> formulas = new ArrayList<>();
            formulas = formulasRepository.findByEndDateIsNull();
            return ResponseEntity.ok().body(formulas);
        } catch (Exception e) {
            log.error("Error at 'evaluator/kpis'", e);
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

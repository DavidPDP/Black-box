package co.edu.icesi.metrocali.blackbox.api.event_managment;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotBlank;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.metrocali.blackbox.entities.event_managment.Step;
import co.edu.icesi.metrocali.blackbox.repositories.events.StepsRepository;

@RestController
@RequestMapping("/event_managment/steps")
public class HTTPRestStepsAPI {

	private StepsRepository stepsRepository;
	
	public HTTPRestStepsAPI(StepsRepository stepsRepository) {
		this.stepsRepository = stepsRepository;
	}
	
	@GetMapping
	public ResponseEntity<List<Step>> retrieveAll(){
		
		List<Step> steps = stepsRepository.findAll();
				
		if(steps != null && !steps.isEmpty()) {
			return ResponseEntity.ok(steps);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/{code}")
	public ResponseEntity<Step> retrieve(
			@PathVariable @NotBlank String code){
		
		Optional<Step> state = 
			stepsRepository.findByCode(code);
		
		if(state.isPresent()) {
			return ResponseEntity.ok(state.get());
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@PostMapping
	public ResponseEntity<Step> save(
			@RequestBody @NonNull Step step) {
		
		Step persistedStep = stepsRepository.save(step);
		return ResponseEntity.ok(persistedStep);
		
	}
	
	@DeleteMapping("/{name}")
	public ResponseEntity<HttpStatus> delete(
			@PathVariable @NotBlank String name) {
		
		stepsRepository.deleteByDescription(name);
		return ResponseEntity.ok().build();
		
	}
	
}

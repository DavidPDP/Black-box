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

import co.edu.icesi.metrocali.blackbox.entities.event_managment.State;
import co.edu.icesi.metrocali.blackbox.repositories.events.StatesRepository;

/**
 * Exposes the API of the state related services 
 * in event_managment context.
 */
@RestController
@RequestMapping("/event_managment/states")
public class HTTPRestStatesAPI {
	
	private StatesRepository statesRepository;
	
	public HTTPRestStatesAPI(StatesRepository statesRepository) {
		this.statesRepository = statesRepository;
	}
		
	@GetMapping
	public ResponseEntity<List<State>> retrieveAll(){
		
		List<State> states = statesRepository.findAll();
		
		if(states != null && !states.isEmpty()) {
			return ResponseEntity.ok(states);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/{name}")
	public ResponseEntity<State> retrieve(
			@PathVariable @NotBlank String name){
		
		Optional<State> state = statesRepository.findByName(name);
		
		if(state.isPresent()) {
			return ResponseEntity.ok(state.get());
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@PostMapping
	public ResponseEntity<State> save(
			@RequestBody @NonNull State state) {
		
		State persistedState = statesRepository.save(state);
		return ResponseEntity.ok(persistedState);
		
	}
	
	@DeleteMapping("/{name}")
	public ResponseEntity<HttpStatus> delete(
			@PathVariable @NotBlank String name) {
		
		statesRepository.deleteByName(name);
		return ResponseEntity.ok().build();
		
	}
	
}

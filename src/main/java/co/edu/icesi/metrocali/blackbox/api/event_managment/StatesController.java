package co.edu.icesi.metrocali.blackbox.api.event_managment;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
public class StatesController {
	
	private StatesRepository statesRepository;
	
	public StatesController(StatesRepository statesRepository) {
		this.statesRepository = statesRepository;
	}
		
	@GetMapping
	public ResponseEntity<List<State>> retrieveAllStates(){
		
		List<State> states = statesRepository.findAll();
				
		if(states != null && !states.isEmpty()) {
			
			return ResponseEntity.ok(states);
			
		}else {
			
			return ResponseEntity.notFound().build();
			
		}
	}
	
	@GetMapping("/{name}")
	public ResponseEntity<State> retrieveState(@PathVariable String name){
		
		Optional<State> state = statesRepository.findByName(name);
		
		if(state.isPresent()) {
			return ResponseEntity.ok(state.get());
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	
}

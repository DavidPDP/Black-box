package co.edu.icesi.metrocali.blackbox.api.event_managment;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.metrocali.blackbox.dtos.OutStateMessage;
import co.edu.icesi.metrocali.blackbox.entities.event_managment.State;
import co.edu.icesi.metrocali.blackbox.entities.event_managment.StateType;
import co.edu.icesi.metrocali.blackbox.repositories.events.StatesRepository;
import co.edu.icesi.metrocali.blackbox.repositories.events.StatesTypesRepository;
import co.edu.icesi.metrocali.blackbox.utils.EntitiesMapper;

/**
 * Exposes the API of the state related services in event_managment context.
 */
@RestController
@RequestMapping("/event_managment/states")
public class StatesController {
	
	private StatesRepository statesRepository;
	
	private StatesTypesRepository statesTypesRepository;
	
	private EntitiesMapper entitiesMapper;
	
	@Autowired
	public StatesController(StatesRepository statesRepository,
			EntitiesMapper entitiesMapper) {
		this.statesRepository = statesRepository;
		this.entitiesMapper = entitiesMapper;
	}
	
//	@GetMapping
//	public ResponseEntity<List<StateType>> retrieveAllStatesTypes(){
//		List<StateType> statesTypes = statesTypesRepository.findAll();
//		if(statesTypes != null && !statesTypes.isEmpty()) {
//			return new ResponseEntity<List<StateType>>(
//				statesTypes, HttpStatus.OK
//			);
//		}else {
//			return new ResponseEntity<List<StateType>>(
//				HttpStatus.INTERNAL_SERVER_ERROR
//			);
//		}
//	}
	
	@GetMapping
	public ResponseEntity<List<OutStateMessage>> retrieveAllStates(){
		
		List<State> states = statesRepository.findAll();
				
		if(states != null && !states.isEmpty()) {
			
			return new ResponseEntity<List<OutStateMessage>>(
				states.stream()
					.map(state -> {
						return entitiesMapper.convertStateEntity(state);
					})
					.collect(Collectors.toList()),
				HttpStatus.OK
			);
			
		}else {
			
			return new ResponseEntity<List<OutStateMessage>>(
				HttpStatus.INTERNAL_SERVER_ERROR
			);
			
		}
	}
	
	@GetMapping("/{name}")
	public ResponseEntity<State> retrieveState(@PathVariable String name){
		Optional<State> state = statesRepository.findByName(name);
		return state.isPresent() ? 
				new ResponseEntity<State>(state.get(), HttpStatus.OK) :
				new ResponseEntity<State>(HttpStatus.BAD_REQUEST);
	}
	
	
}

package co.edu.icesi.metrocali.blackbox.api.event_managment;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotBlank;

import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.metrocali.blackbox.entities.event_managment.Event;
import co.edu.icesi.metrocali.blackbox.entities.event_managment.EventTrack;
import co.edu.icesi.metrocali.blackbox.repositories.events.EventsRepository;

@RestController
@RequestMapping("/event_managment/events")
public class HTTPRestEventsAPI {

	private EventsRepository eventsRepository;
	
	public HTTPRestEventsAPI(EventsRepository eventsRepository) {
		this.eventsRepository = eventsRepository;
	}
	
	@GetMapping
	public ResponseEntity<List<Event>> retrieveAll(
			@RequestParam @NotBlank String interval) {

		List<Event> events = 
			eventsRepository.findLastEvents(interval);
		
		if(events != null && !events.isEmpty()) {
			return ResponseEntity.ok(events);
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@GetMapping("/{code}")
	public ResponseEntity<Event> retrieve(
			@PathVariable @NotBlank String code) {
		
		Optional<Event> event = 
			eventsRepository.findByCode(code);
		
		if(event.isPresent()) {
			return ResponseEntity.ok(event.get());
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@PostMapping
	public ResponseEntity<Event> saveEvent(
			@RequestBody @NonNull Event event){

		Event savedEvent = eventsRepository.save(event);
		return ResponseEntity.ok(savedEvent);
		
	}
	
	@GetMapping("/history/{accountName}")
	public ResponseEntity<List<EventTrack>> history(
			@PathVariable @NotBlank String accountName,
			@RequestParam @NotBlank String interval) {
		
		List<EventTrack> eventTracks = 
			eventsRepository.findAllByUserAndDate(
				accountName, interval
			);
		
		if(eventTracks != null && !eventTracks.isEmpty()) {
			return ResponseEntity.ok(eventTracks);
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
		
}

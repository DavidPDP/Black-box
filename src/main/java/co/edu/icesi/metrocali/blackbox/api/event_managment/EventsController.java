package co.edu.icesi.metrocali.blackbox.api.event_managment;

import java.util.List;

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
import co.edu.icesi.metrocali.blackbox.repositories.events.EventsRepository;

@RestController
@RequestMapping("/event_managment/events")
public class EventsController {

	private EventsRepository eventsRepository;
	
	public EventsController(EventsRepository eventsRepository) {
		this.eventsRepository = eventsRepository;
	}
	
	@GetMapping("/lasted")
	public ResponseEntity<List<Event>> retrieveLastEvents(
			@NonNull @RequestParam String interval) {
		System.out.println(interval);
		List<Event> events = eventsRepository.findLastEvents(interval);

		if(events != null && !events.isEmpty()) {
			return ResponseEntity.ok(events);
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
		
}

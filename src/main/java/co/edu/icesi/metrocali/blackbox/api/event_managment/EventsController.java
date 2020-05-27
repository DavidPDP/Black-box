package co.edu.icesi.metrocali.blackbox.api.event_managment;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.metrocali.blackbox.entities.event_managment.Event;
import co.edu.icesi.metrocali.blackbox.entities.event_managment.EventTrack;
import co.edu.icesi.metrocali.blackbox.repositories.events.EventTracksRepository;
import co.edu.icesi.metrocali.blackbox.repositories.events.EventsRepository;

@RestController
@RequestMapping("/event_managment/events")
public class EventsController {

	private EventsRepository eventsRepository;
	
	private EventTracksRepository eventTracksRepository;
	
	public EventsController(EventsRepository eventsRepository,
			EventTracksRepository eventTracksRepository) {
		this.eventsRepository = eventsRepository;
		this.eventTracksRepository = eventTracksRepository;
	}
	
	@GetMapping("/lasted/{interval}")
	public ResponseEntity<List<Event>> retrieveLastEvents(
			@NonNull @PathVariable String interval) {
		return new ResponseEntity<List<Event>>(
			eventsRepository.findLastEvents(interval),
			HttpStatus.OK
		);
	}
	
	@PostMapping
	public ResponseEntity<Event> persistEvent(@RequestBody Event event){
		try {
			System.out.println("Request: " + event);
			Event sevent = eventsRepository.save(event);
			System.out.println("Callback: " + sevent);
			return new ResponseEntity<Event>(
				sevent, 
				HttpStatus.OK
			);
		}catch(IllegalArgumentException e) {
			return new ResponseEntity<Event>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/event_tracks")
	public ResponseEntity<List<EventTrack>> persistEventTracks(
			@RequestBody List<EventTrack> eventTracks) {
		try {
			return new ResponseEntity<List<EventTrack>>(
					eventTracksRepository.saveAll(eventTracks),
					HttpStatus.OK
			);
		}catch(IllegalArgumentException e) {
			return new ResponseEntity<List<EventTrack>>(HttpStatus.BAD_REQUEST);
		}
	}
	
}

package co.edu.icesi.metrocali.blackbox.api.event_managment;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.metrocali.blackbox.entities.event_managment.UserTrack;
import co.edu.icesi.metrocali.blackbox.repositories.events.UsersTrackRepository;

@RestController
@RequestMapping("/event_managment/users_track")
public class UsersTrackController {

	private UsersTrackRepository usersTrackRepository;
	
	public UsersTrackController(
		UsersTrackRepository usersTrackRepository) {
		this.usersTrackRepository = usersTrackRepository;
	}
	
	@GetMapping("/{accountName}/history/{interval}")
	public ResponseEntity<List<UserTrack>> history(
			@PathVariable @NonNull String accountName,
			@PathVariable @NonNull String interval){
		
		List<UserTrack> history = 
			usersTrackRepository.findLastTracksByUser(accountName,interval);
		
		if(history != null && !history.isEmpty()) {
			return ResponseEntity.ok(history);
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@PostMapping
	public ResponseEntity<UserTrack> saveUserTrack(
			@RequestBody UserTrack userTrack){
		return ResponseEntity.ok(usersTrackRepository.save(userTrack));
	}
	
}

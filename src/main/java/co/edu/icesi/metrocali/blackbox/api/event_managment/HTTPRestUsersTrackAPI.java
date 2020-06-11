package co.edu.icesi.metrocali.blackbox.api.event_managment;

import java.util.List;

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

import co.edu.icesi.metrocali.blackbox.entities.event_managment.UserTrack;
import co.edu.icesi.metrocali.blackbox.repositories.events.UsersTrackRepository;

@RestController
@RequestMapping("/event_managment/users_track")
public class HTTPRestUsersTrackAPI {

	private UsersTrackRepository usersTrackRepository;
	
	public HTTPRestUsersTrackAPI(
		UsersTrackRepository usersTrackRepository) {
		this.usersTrackRepository = usersTrackRepository;
	}
	
	@GetMapping("/{accountName}")
	public ResponseEntity<List<UserTrack>> retrieveAll(
			@PathVariable @NotBlank String accountName,
			@RequestParam @NotBlank String interval){
		
		List<UserTrack> userTracks = 
			usersTrackRepository.findLastTracksByUser(
				accountName, interval
		);
		
		if(userTracks != null && !userTracks.isEmpty()) {
			return ResponseEntity.ok(userTracks);
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@PostMapping
	public ResponseEntity<UserTrack> save(
			@RequestBody @NonNull UserTrack userTrack){
		
		UserTrack persistedUserTrack = 
				usersTrackRepository.save(userTrack);
		
		return ResponseEntity.ok(persistedUserTrack);
		
	}
	
}

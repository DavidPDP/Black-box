package co.edu.icesi.metrocali.blackbox.api.policies;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.metrocali.blackbox.entities.policies.User;
import co.edu.icesi.metrocali.blackbox.repositories.policies.UsersRepository;

@RestController
@RequestMapping("/policies/users")
public class HTTPRestUsersAPI {
	
	private UsersRepository usersRepository;
	
	public HTTPRestUsersAPI(UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}
	
	@GetMapping
	public ResponseEntity<List<User>> retrieveAll() {
		
		List<User> users = usersRepository.findAll();
		
		if(users != null && !users.isEmpty()) {
			return ResponseEntity.ok(users);
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@GetMapping("/types")
	public ResponseEntity<List<User>> retrieveAll(
			@RequestParam @NotBlank String type) {
		
		List<User> users = usersRepository.findByRoles_Name(type);
		
		if(users != null && !users.isEmpty()) {
			return ResponseEntity.ok(users);
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@GetMapping("/{accountName}")
	public ResponseEntity<User> retrieve(
			@PathVariable @NotBlank String accountName) {
		
		Optional<User> user = 
				usersRepository.findByAccountName(accountName);
		
		if(user.isPresent()) {
			return ResponseEntity.ok(user.get());
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@GetMapping("/online")
	public ResponseEntity<List<User>> retrieveOnline(){
		
		List<User> controllers = 
				usersRepository.findAllOnline();
		
		if(controllers != null && !controllers.isEmpty()) {
			return ResponseEntity.ok(controllers);
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@PostMapping
	public ResponseEntity<User> save(
			@RequestBody @NonNull User user) {
			
		User persistedUser = usersRepository.save(user);
		return ResponseEntity.ok(persistedUser);
		
	}
	
	@DeleteMapping("/{accountName}")
	public ResponseEntity<HttpStatus> delete(
			@PathVariable String accountName) {
		
		usersRepository.deleteByAccountName(accountName);
		return ResponseEntity.ok().build();
		
	}
	
}

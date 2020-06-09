package co.edu.icesi.metrocali.blackbox.api.policies;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.metrocali.blackbox.entities.policies.User;
import co.edu.icesi.metrocali.blackbox.repositories.policies.UsersRepository;

@RestController
@RequestMapping("/policies/users")
public class UsersController {
	
	private UsersRepository usersRepository;
	
	public UsersController(UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}
	
	@GetMapping
	public ResponseEntity<List<User>> retrieveAllUsers(){
		
		List<User> users = usersRepository.findAll();
		
		if(users != null && !users.isEmpty()) {
			return ResponseEntity.ok(users);
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> retrieveUser(@PathVariable int id) {
		
		Optional<User> user = usersRepository.findById(id);
		
		if(user.isPresent()) {
			return ResponseEntity.ok(user.get());
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@GetMapping("/account_names/{accountName}")
	public ResponseEntity<User> retrieveUser(
			@PathVariable String accountName) {
		
		Optional<User> user = 
				usersRepository.findByAccountName(accountName);
		
		if(user.isPresent()) {
			return ResponseEntity.ok(user.get());
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@GetMapping("/controllers/active")
	public ResponseEntity<List<User>> retrieveOnlineControllers(){
		
		List<User> controllers = 
				usersRepository.findAllOnlineControllers();
		
		if(controllers != null && !controllers.isEmpty()) {
			return ResponseEntity.ok(controllers);
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@PostMapping
	public ResponseEntity<HttpStatus> saveUser(@RequestBody User user) {
		
		try {
			
			usersRepository.save(user);
			return ResponseEntity.ok().build();
			
		}catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().build();
		}
		
	}
	
	@DeleteMapping("/{accountName}")
	public ResponseEntity<HttpStatus> deleteUser(
			@PathVariable String accountName) {
		
		usersRepository.deleteByAccountName(accountName);
		return ResponseEntity.ok().build();
		
	}
	
}

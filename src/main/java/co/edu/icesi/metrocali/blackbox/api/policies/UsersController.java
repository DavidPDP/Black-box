package co.edu.icesi.metrocali.blackbox.api.policies;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.metrocali.blackbox.dtos.OutUserMessage;
import co.edu.icesi.metrocali.blackbox.entities.policies.User;
import co.edu.icesi.metrocali.blackbox.repositories.policies.UsersRepository;
import co.edu.icesi.metrocali.blackbox.utils.EntitiesMapper;

@RestController
@RequestMapping("/access/users")
public class UsersController {
	
	private UsersRepository usersRepository;
	
	private EntitiesMapper entitiesMapper;
	
	@Autowired
	public UsersController(UsersRepository usersRepository,
			EntitiesMapper entitiesMapper) {
		this.usersRepository = usersRepository;
		this.entitiesMapper = entitiesMapper;
	}
	
	@GetMapping
	public ResponseEntity<List<OutUserMessage>> retrieveAllUsers(){
		Iterable<User> users = usersRepository.findAll();
		if(users.iterator().hasNext()) {
			return new ResponseEntity<List<OutUserMessage>>(
					StreamSupport.stream(users.spliterator(), false)
					.map(user -> {
						return entitiesMapper.convertUserEntity(user);
					})
					.collect(Collectors.toList()),
					HttpStatus.OK
			);
		}else {
			return new ResponseEntity<List<OutUserMessage>>(
					HttpStatus.INTERNAL_SERVER_ERROR
			);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<OutUserMessage> retrieveUser(@PathVariable int id) {
		Optional<User> user = usersRepository.findById(id);
		if(user.isPresent()) {
			return new ResponseEntity<OutUserMessage>(
					entitiesMapper.convertUserEntity(user.get()),
					HttpStatus.OK
			);
		}else {
			return new ResponseEntity<OutUserMessage>(
					HttpStatus.INTERNAL_SERVER_ERROR
			);
		}
	}
	
	@GetMapping("/accountName/{accountName}")
	public ResponseEntity<OutUserMessage> retrieveUser(@PathVariable String accountName) {
		Optional<User> user = usersRepository.findByAccountName(accountName);
		if(user.isPresent()) {
			return new ResponseEntity<OutUserMessage>(
					entitiesMapper.convertUserEntity(user.get()),
					HttpStatus.OK
			);
		}else {
			return new ResponseEntity<OutUserMessage>(
					HttpStatus.INTERNAL_SERVER_ERROR
			);
		}
	}
	
	@GetMapping("/controllers/active")
	public ResponseEntity<List<OutUserMessage>> retrieveOnlineControllers(){
		List<User> controllers = 
				usersRepository.findAllOnlineControllers();
		if(controllers != null && !controllers.isEmpty()) {
			return new ResponseEntity<List<OutUserMessage>>(
					controllers
					.stream()
					.map(controller -> {
						return entitiesMapper.convertUserEntity(controller);
					})
					.collect(Collectors.toList()),
					HttpStatus.OK
			);
		}else {
			return new ResponseEntity<List<OutUserMessage>>(
					HttpStatus.INTERNAL_SERVER_ERROR
			);
		}
	}
	
	@PostMapping
	public ResponseEntity<HttpStatus> saveUser(@RequestBody User user) {
		try {
			usersRepository.save(user);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}catch (IllegalArgumentException e) {
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{accountName}")
	public ResponseEntity<HttpStatus> deleteUser(
			@PathVariable String accountName) {
		try {
			usersRepository.deleteByAccountName(accountName);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}catch (IllegalArgumentException e) {
			e.printStackTrace();
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}

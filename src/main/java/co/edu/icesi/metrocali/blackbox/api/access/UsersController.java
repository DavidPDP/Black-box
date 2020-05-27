package co.edu.icesi.metrocali.blackbox.api.access;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.metrocali.blackbox.entities.policies.User;
import co.edu.icesi.metrocali.blackbox.repositories.policies.UsersRepository;

@RestController(value="/access")
public class UsersController {
	
	private UsersRepository usersRepository;
	
	@Autowired
	public UsersController(UsersRepository usersRepository) {
		this.usersRepository = usersRepository;
	}
	
	@GetMapping(value="/users")
	public List<User> findAllUsers(){
		return (List<User>) usersRepository.findAll();
	}
	
	@GetMapping(value="/users/user")
	public User findUserbyId(@RequestParam long userId) {
		return usersRepository.findById(userId).orElse(null);
	}
	
	@PostMapping(value="/users/user/s")
	public void saveUser(@RequestBody User user) {
		usersRepository.save(user);
	}
	
	@PostMapping(value="/users/user/d")
	public void deleteUserById(@RequestParam long userId) {
		usersRepository.deleteById(userId);
	}
	
	
}

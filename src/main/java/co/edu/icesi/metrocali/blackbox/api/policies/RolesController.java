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

import co.edu.icesi.metrocali.blackbox.entities.policies.Role;
import co.edu.icesi.metrocali.blackbox.repositories.policies.RolesRepository;

@RestController
@RequestMapping("/policies/roles")
public class RolesController {
	
	private RolesRepository rolesRepository;
	
	public RolesController(RolesRepository rolesRepository) {
		this.rolesRepository = rolesRepository;
	}
	
	@GetMapping
	public ResponseEntity<List<Role>> retrieveAllRoles(){
		
		List<Role> roles = rolesRepository.findAll();
		
		if(roles != null && !roles.isEmpty()) {
			return ResponseEntity.ok(rolesRepository.findAll());
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Role> retrieveRole(@PathVariable int id) {
		
		Optional<Role> role = rolesRepository.findById(id);
		
		if(role.isPresent()) {
			return ResponseEntity.ok(role.get());
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@GetMapping("/names/{name}")
	public ResponseEntity<Role> retrieveRole(@PathVariable String name) {
		
		Optional<Role> role = rolesRepository.findByName(name);
		
		if(role.isPresent()) {
			return ResponseEntity.ok(role.get());
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@PostMapping
	public ResponseEntity<HttpStatus> saveRole(@RequestBody Role role) {
		
		try {
			
			rolesRepository.save(role);
			return ResponseEntity.ok().build();
			
		}catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().build();
		}
		
	}
	
	@DeleteMapping("/{name}")
	public ResponseEntity<HttpStatus> deleteRole(@PathVariable String name) {
		
		rolesRepository.deleteByName(name);
		return ResponseEntity.ok().build();
		
	}
	
}

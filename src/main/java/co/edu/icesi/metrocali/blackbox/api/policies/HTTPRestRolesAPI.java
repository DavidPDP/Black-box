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
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.metrocali.blackbox.entities.policies.Role;
import co.edu.icesi.metrocali.blackbox.repositories.policies.RolesRepository;

@RestController
@RequestMapping("/policies/roles")
public class HTTPRestRolesAPI {
	
	private RolesRepository rolesRepository;
	
	public HTTPRestRolesAPI(RolesRepository rolesRepository) {
		this.rolesRepository = rolesRepository;
	}
	
	@GetMapping
	public ResponseEntity<List<Role>> retrieveAll(){
		
		List<Role> roles = rolesRepository.findAll();
		
		if(roles != null && !roles.isEmpty()) {
			return ResponseEntity.ok(rolesRepository.findAll());
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@GetMapping("/{name}")
	public ResponseEntity<Role> retrieve(
			@PathVariable @NotBlank String name) {
		
		Optional<Role> role = rolesRepository.findByName(name);
		
		if(role.isPresent()) {
			return ResponseEntity.ok(role.get());
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@PostMapping
	public ResponseEntity<Role> save(
			@RequestBody @NonNull Role role) {
					
		Role persistedRole = rolesRepository.save(role);
		return ResponseEntity.ok(persistedRole);
		
	}
	
	@DeleteMapping("/{name}")
	public ResponseEntity<HttpStatus> delete(
			@PathVariable @NotBlank String name) {
		
		rolesRepository.deleteByName(name);
		return ResponseEntity.ok().build();
		
	}
	
}

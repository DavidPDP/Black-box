package co.edu.icesi.metrocali.blackbox.api.policies;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.metrocali.blackbox.entities.policies.Role;
import co.edu.icesi.metrocali.blackbox.repositories.policies.RolesRepository;

@RestController
@RequestMapping("/access/roles")
public class RolesController {
	
	private RolesRepository rolesRepository;
	
	@Autowired
	public RolesController(RolesRepository rolesRepository) {
		this.rolesRepository = rolesRepository;
	}
	
	@GetMapping
	public List<Role> findAllRoles(){
		return (List<Role>) rolesRepository.findAll();
	}
	
	@GetMapping("/role/{id}")
	public Role findRoleById(@PathVariable long id) {
		return rolesRepository.findById(id).orElse(null);
	}
	
	@GetMapping("/role/{name}")
	public Role findRoleByName(@PathVariable String name) {
		return rolesRepository.findByName(name);
	}
	
}

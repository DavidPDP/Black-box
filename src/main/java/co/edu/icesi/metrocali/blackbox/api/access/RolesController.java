package co.edu.icesi.metrocali.blackbox.api.access;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.metrocali.blackbox.entities.policies.Role;
import co.edu.icesi.metrocali.blackbox.repositories.policies.RolesRepository;

@RestController(value="/access")
public class RolesController {
	
	private RolesRepository rolesRepository;
	
	@Autowired
	public RolesController(RolesRepository rolesRepository) {
		this.rolesRepository = rolesRepository;
	}
	
	@GetMapping(value="/roles/role")
	public Role findRoleById(@RequestParam long roleId) {
		return rolesRepository.findById(roleId).orElse(null);
	}
	
	@GetMapping(value="/roles")
	public List<Role> findAllRoles(){
		return (List<Role>) rolesRepository.findAll();
	}
	
	@GetMapping(value="/roles/role/n")
	public Role findRoleByName(@RequestParam String name) {
		return rolesRepository.findByName(name);
	}
	
}

package co.edu.icesi.metrocali.blackbox.repositories.policies;

import org.springframework.data.repository.CrudRepository;

import co.edu.icesi.metrocali.blackbox.entities.policies.Role;

public interface RolesRepository extends CrudRepository<Role, Long>{

	public Role findByName(String name);
	
}

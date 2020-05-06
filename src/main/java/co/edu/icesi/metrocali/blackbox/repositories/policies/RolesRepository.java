package co.edu.icesi.metrocali.blackbox.repositories.policies;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.icesi.metrocali.blackbox.entities.policies.Role;

@Repository
public interface RolesRepository extends CrudRepository<Role, Long>{

	public Role findByName(String name);
	
}

package co.edu.icesi.metrocali.blackbox.repositories.policies;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.icesi.metrocali.blackbox.entities.policies.Role;

@Repository
public interface RolesRepository extends CrudRepository<Role, Integer> {

	@Override
	public List<Role> findAll();
	
	public Optional<Role> findByName(String name);
	
	@Transactional
	public void deleteByName(String name);
	
}

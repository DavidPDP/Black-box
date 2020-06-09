package co.edu.icesi.metrocali.blackbox.repositories.policies;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.icesi.metrocali.blackbox.entities.policies.User;

@Repository
public interface UsersRepository extends CrudRepository<User, Long> {
	
	public Optional<User> findByAccountName(String accountName);
	
}

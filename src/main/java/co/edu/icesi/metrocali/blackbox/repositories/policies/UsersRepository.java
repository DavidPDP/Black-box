package co.edu.icesi.metrocali.blackbox.repositories.policies;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.icesi.metrocali.blackbox.entities.policies.User;

@Repository
public interface UsersRepository extends CrudRepository<User, Integer> {
	
	//CRUD ----------------------------------------
	@Override
	public List<User> findAll();
	
	public Optional<User> findByAccountName(String accountName);
	
	@Transactional
	public void deleteByAccountName(String accountName);
	//---------------------------------------------
	
	//Reports -------------------------------------
	@Query(value="select u.id, u.account_name, u.email, u.name, u.last_name, u.password " + 
			"from event_managment.t_002_users_track t " + 
			"join (select t.owner, max(start_time) last_date " + 
			"from event_managment.t_002_users_track t " + 
			"group by t.owner ) dates on t.owner = dates.owner and t.start_time = last_date " + 
			"join event_managment.t_002_states s on t.state = s.id " + 
			"join policies.t_002_users u on t.owner = u.id " + 
			"join policies.t_002_users_roles ur on u.id = ur.owner " + 
			"join policies.t_002_roles r on ur.role = r.id " + 
			"where s.name <> 'Offline' and r.name = 'Controller'", 
			nativeQuery = true)
	public List<User> findAllOnlineControllers();
	//---------------------------------------------
}

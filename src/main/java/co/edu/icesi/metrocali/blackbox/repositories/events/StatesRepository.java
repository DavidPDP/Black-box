package co.edu.icesi.metrocali.blackbox.repositories.events;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.icesi.metrocali.blackbox.entities.event_managment.State;

@Repository
public interface StatesRepository extends CrudRepository<State, Integer> {
	
	@Override
	public List<State> findAll();
	
	public Optional<State> findByName(String name);
	
	@Query(value="select ut.id, ut.state, ut.owner, ut.start_time, ut.end_time from event_managment.t_002_users_track ut join policies.t_002_users u on ut.owner = u.id join event_managment.t_002_states s on ut.state = s.id where u.account_name = 'Admin';", nativeQuery=true)
	public Optional<State> findByUser(String accountName);
	
	@Transactional
	public void deleteByName(String name);
}

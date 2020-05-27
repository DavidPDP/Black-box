package co.edu.icesi.metrocali.blackbox.repositories.events;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.icesi.metrocali.blackbox.entities.event_managment.State;

@Repository
public interface StatesRepository extends CrudRepository<State, Integer>{
	
	@Override
	public List<State> findAll();
	
	public Optional<State> findByName(String name);
	
}

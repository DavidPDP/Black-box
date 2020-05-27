package co.edu.icesi.metrocali.blackbox.repositories.events;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.icesi.metrocali.blackbox.entities.event_managment.StateType;

@Repository
public interface StatesTypesRepository extends CrudRepository<StateType, Integer>{

	@Override
	public List<StateType> findAll();
	
}

package co.edu.icesi.metrocali.blackbox.repositories.events;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.icesi.metrocali.blackbox.entities.event_managment.EventTrack;

@Repository
public interface EventTracksRepository extends CrudRepository<EventTrack, Long>{
	
	@Override
	public List<EventTrack> findAll();
	
	@Override
	public <S extends EventTrack> List<S> saveAll(Iterable<S> entities);
	
}

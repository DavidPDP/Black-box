package co.edu.icesi.metrocali.blackbox.repositories.events;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import co.edu.icesi.metrocali.blackbox.entities.event_managment.Event;

@Repository
public interface EventsRepository extends CrudRepository<Event, Long>{

//	@Query(value = "", nativeQuery = true)
//	public List<Event> findAllEvents(
//		@NonNull Timestamp startTime, @NonNull Timestamp endTime);
	
}

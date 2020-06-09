package co.edu.icesi.metrocali.blackbox.repositories.events;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import co.edu.icesi.metrocali.blackbox.entities.event_managment.Event;
import co.edu.icesi.metrocali.blackbox.entities.event_managment.EventTrack;

@Repository
public interface EventsRepository extends CrudRepository<Event, Long>{

	@Query(value="select e.id, e.code, e.title, e.description, e.creation, "
		+ "e.category, e.source, e.source_type "
		+ "from event_managment.t_002_events_track t join "
		+ "event_managment.t_002_events e on t.event = e.id "
		+ "where t.start_time > current_timestamp - "
		+ "(:interval)\\:\\:interval",
		nativeQuery=true)
	public List<Event> findLastEvents(
		@NonNull @Param("interval") String interval
	);
	
	@Query(value = "select e.id, e.code, e.title, e.description, e.creation, " 
		+ "e.category, e.source, e.source_type " 
		+ "from event_managment.t_002_events_track t join "
		+ "event_managment.t_002_events e on t.event = e.id join"
		+ "policies.t_002_users u on t.manager = u.id "
		+ "where u.account_name = :account_name and "
		+ "t.start_time > current_timestamp - "
		+ "(:interval)\\:\\:interval", nativeQuery = true)
	public List<EventTrack> findAllByUserAndDate(
		@NonNull @Param("account_name") String accountName,	
		@NonNull @Param("interval") String interval
	);
	
}

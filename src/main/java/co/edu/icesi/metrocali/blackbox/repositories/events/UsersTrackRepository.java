package co.edu.icesi.metrocali.blackbox.repositories.events;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.edu.icesi.metrocali.blackbox.entities.event_managment.UserTrack;

@Repository
public interface UsersTrackRepository extends CrudRepository<UserTrack, Long>{

	public <S extends UserTrack> List<S> saveAll(Iterable<S> entities);
	
	@Query(value="select t.id, t.state, t.owner, t.start_time, t.end_time "
			+ "from event_managment.t_002_users_track t "
			+ "join policies.t_002_users u on t.owner = u.id "
			+ "where u.account_name = :account_name and "
			+ "t.start_time > current_timestamp - (:interval)\\:\\:interval",
			nativeQuery=true)
	public List<UserTrack> findLastTracksByUser(
		@Param("account_name") String accountName,	
		@Param("interval") String interval
	);
	
}

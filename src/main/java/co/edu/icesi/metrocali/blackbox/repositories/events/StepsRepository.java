package co.edu.icesi.metrocali.blackbox.repositories.events;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.icesi.metrocali.blackbox.entities.event_managment.Step;

@Repository
public interface StepsRepository extends CrudRepository<Step, Integer> {
	
	public List<Step> findAll();
	
	public Optional<Step> findByCode(String code);
	
	@Transactional
	public void deleteByDescription(String description);
	
}

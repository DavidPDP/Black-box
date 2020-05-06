package co.edu.icesi.metrocali.blackbox.repositories.events;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.edu.icesi.metrocali.blackbox.entities.policies.Setting;

public interface SettingsRepository extends CrudRepository<Setting, Integer>{
	
	public List<Setting> findAll();
	
}

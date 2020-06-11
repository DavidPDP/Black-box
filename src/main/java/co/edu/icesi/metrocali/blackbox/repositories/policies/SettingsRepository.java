package co.edu.icesi.metrocali.blackbox.repositories.policies;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.icesi.metrocali.blackbox.entities.policies.Setting;

@Repository
public interface SettingsRepository extends CrudRepository<Setting, Integer>{

	@Override
	public List<Setting> findAll();
	
	public Optional<Setting> findByKey(String key);
	
	@Transactional
	public void deleteByKey(String key);
	
}

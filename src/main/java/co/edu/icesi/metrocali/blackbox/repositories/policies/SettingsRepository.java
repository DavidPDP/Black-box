package co.edu.icesi.metrocali.blackbox.repositories.policies;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import co.edu.icesi.metrocali.blackbox.entities.policies.Setting;

@Repository
public interface SettingsRepository extends CrudRepository<Setting, Integer>{

	@Override
	public List<Setting> findAll();
	
	@Transactional
	public void deleteByKey(@NonNull String key);
	
}

package co.edu.icesi.metrocali.blackbox.repositories.events;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import co.edu.icesi.metrocali.blackbox.entities.event_managment.Category;

@Repository
public interface CategoriesRepository extends CrudRepository<Category, Integer>{

	@Override
	public List<Category> findAll();
	
	public Optional<Category> findByName(@NonNull String name);
	
	@Transactional
	public void deleteByName(@NonNull String name);
}

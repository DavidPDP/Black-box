package co.edu.icesi.metrocali.blackbox.repositories.events;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.edu.icesi.metrocali.blackbox.entities.event_managment.Category;

public interface CategoriesRepository extends CrudRepository<Category, Integer>{

	@Override
	public List<Category> findAll();
	
}

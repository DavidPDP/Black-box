package co.edu.icesi.metrocali.blackbox.api.event_managment;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.metrocali.blackbox.dtos.OutCategoryMessage;
import co.edu.icesi.metrocali.blackbox.entities.event_managment.Category;
import co.edu.icesi.metrocali.blackbox.repositories.events.CategoriesRepository;
import co.edu.icesi.metrocali.blackbox.utils.EntitiesMapper;

@RestController
@RequestMapping("/event_managment/categories")
public class CategoriesController {

	private CategoriesRepository categoriesRepository;
	
	private EntitiesMapper entitiesMapper;
	
	@Autowired
	public CategoriesController(CategoriesRepository categoriesRepository,
			EntitiesMapper entitiesMapper) {
		this.categoriesRepository = categoriesRepository;
		this.entitiesMapper = entitiesMapper;
	}
	
	@GetMapping
	public ResponseEntity<List<Category>> retrieveAllCategories() {
		return new ResponseEntity<List<Category>>(
				categoriesRepository.findAll(),
				HttpStatus.OK
		);
	}
	
	@GetMapping("/{name}")
	public ResponseEntity<OutCategoryMessage> retrieveCategory(
			@PathVariable String name) {
		//return categoriesRepository.find
		return null;
	}
	
}

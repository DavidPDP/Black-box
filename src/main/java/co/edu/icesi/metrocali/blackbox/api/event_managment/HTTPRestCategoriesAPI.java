package co.edu.icesi.metrocali.blackbox.api.event_managment;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotBlank;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.metrocali.blackbox.entities.event_managment.Category;
import co.edu.icesi.metrocali.blackbox.repositories.events.CategoriesRepository;

@RestController
@RequestMapping("/event_managment/categories")
public class HTTPRestCategoriesAPI {

	private CategoriesRepository categoriesRepository;
	
	public HTTPRestCategoriesAPI(
			CategoriesRepository categoriesRepository) {
		this.categoriesRepository = categoriesRepository;
	}
	
	@GetMapping
	public ResponseEntity<List<Category>> retrieveAll() {
		
		List<Category> categories = categoriesRepository.findAll();
		
		if(categories != null && !categories.isEmpty()) {
			return ResponseEntity.ok(categories);
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@GetMapping("/{name}")
	public ResponseEntity<Category> retrieve(
			@PathVariable @NotBlank String name) {
		
		Optional<Category> category = 
				categoriesRepository.findByName(name);
		
		if(category.isPresent()) {
			return ResponseEntity.ok(category.get());
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@PostMapping
	public ResponseEntity<Category> save(
			@RequestBody @NonNull Category category) {

		Category persistedCategory = 
				categoriesRepository.save(category);
		return ResponseEntity.ok(persistedCategory);
		
	}
	
	@DeleteMapping
	public ResponseEntity<HttpStatus> delete(
			@PathVariable @NotBlank String name) {
		
		categoriesRepository.deleteByName(name);
		return ResponseEntity.ok().build();
		
	}
	
}

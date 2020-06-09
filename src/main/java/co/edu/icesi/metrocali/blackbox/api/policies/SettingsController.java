package co.edu.icesi.metrocali.blackbox.api.policies;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.metrocali.blackbox.entities.policies.Setting;
import co.edu.icesi.metrocali.blackbox.repositories.policies.SettingsRepository;

@RestController
@RequestMapping("/policies/settings")
public class SettingsController {
	
	private SettingsRepository settingsRepository;
	
	public SettingsController(SettingsRepository settingsRepository) {
		this.settingsRepository = settingsRepository;
	}
	
	@GetMapping
	public ResponseEntity<List<Setting>> retrieveAll() {
		
		List<Setting> settings = settingsRepository.findAll();
		
		if(settings != null && !settings.isEmpty()) {
			return new ResponseEntity<List<Setting>>(
				settings, HttpStatus.OK
			);
		}else {
			return new ResponseEntity<List<Setting>>(
				HttpStatus.INTERNAL_SERVER_ERROR
			);
		}
		
	}
	
	@PostMapping
	public ResponseEntity<HttpStatus> save(
			@RequestBody Setting setting) {
		
		settingsRepository.save(setting);
		return ResponseEntity.ok().build();
		
	}
	
	@DeleteMapping("/{key}")
	public ResponseEntity<HttpStatus> delete(
			@PathVariable String key) {
		
		settingsRepository.deleteByKey(key);
		return ResponseEntity.ok().build();
		
	}
	
}

package co.edu.icesi.metrocali.blackbox.api.policies;

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

import co.edu.icesi.metrocali.blackbox.entities.policies.Setting;
import co.edu.icesi.metrocali.blackbox.repositories.policies.SettingsRepository;

@RestController
@RequestMapping("/policies/settings")
public class HTTPRestSettingsAPI {
	
	private SettingsRepository settingsRepository;
	
	public HTTPRestSettingsAPI(SettingsRepository settingsRepository) {
		this.settingsRepository = settingsRepository;
	}
	
	@GetMapping
	public ResponseEntity<List<Setting>> retrieveAll() {
		
		List<Setting> settings = settingsRepository.findAll();
		
		if(settings != null && !settings.isEmpty()) {
			return ResponseEntity.ok(settings);
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@GetMapping("/{key}")
	public ResponseEntity<Setting> retrieve(
			@PathVariable @NotBlank String key) {
		
		Optional<Setting> setting = settingsRepository.findByKey(key);
		
		if(setting.isPresent()) {
			return ResponseEntity.ok(setting.get());
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@PostMapping
	public ResponseEntity<Setting> save(
			@RequestBody @NonNull Setting setting) {
		
		Setting persistedSetting = settingsRepository.save(setting);
		return ResponseEntity.ok(persistedSetting);
		
	}
	
	@DeleteMapping("/{key}")
	public ResponseEntity<HttpStatus> delete(
			@PathVariable @NotBlank String key) {
		
		settingsRepository.deleteByKey(key);
		return ResponseEntity.ok().build();
		
	}
	
}

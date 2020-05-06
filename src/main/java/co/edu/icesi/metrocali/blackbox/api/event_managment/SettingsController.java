package co.edu.icesi.metrocali.blackbox.api.event_managment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.icesi.metrocali.blackbox.entities.policies.Setting;
import co.edu.icesi.metrocali.blackbox.repositories.events.SettingsRepository;

@RestController
@RequestMapping("/event_managment/settings")
public class SettingsController {
	
	private SettingsRepository settingsRepository;
	
	@Autowired
	public SettingsController(SettingsRepository settingsRepository) {
		this.settingsRepository = settingsRepository;
	}
	
	@GetMapping
	public ResponseEntity<List<Setting>> retrieveAllSettings() {
		
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
}

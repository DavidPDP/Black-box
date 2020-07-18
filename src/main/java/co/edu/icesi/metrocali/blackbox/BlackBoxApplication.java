package co.edu.icesi.metrocali.blackbox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

import co.edu.icesi.metrocali.blackbox.entities.event_managment.Event;

@SpringBootApplication
@EnableConfigurationProperties
public class BlackBoxApplication {
	
	private Map<String, Event> events = new HashMap<>();
	private List<Event> listEvent = new ArrayList<>();
	
	public static void main(String[] args) {
		ConfigurableApplicationContext e = SpringApplication.run(BlackBoxApplication.class, args);
		BlackBoxApplication tt = e.getBean(BlackBoxApplication.class);
		tt.init();
		tt.init2();
		tt.init3();
	}

	public void init() {
		Event event = new Event();
		event.setCode("hola");
		event.setDescription("Holaaaa");
		events.put(event.getCode(), event);
		
		Event work = events.get("hola");
		listEvent.add(work);
		
		
		System.out.println("I1L - Id: " + work.getCode()
			+ " Description: " + work.getDescription());
	}
	
	public void init2() {
		Event event = events.get("hola");
		event.setDescription("buenas");
		
		Event work = listEvent.get(0);
		System.out.println("I2 - Id: " + work.getCode()
		+ " Description: " + work.getDescription());
		
	}
	
	public void init3() {
		Event event = listEvent.get(0);
		event.setDescription("jajajaja");
		
		Event work = events.get("hola");
		System.out.println("I3 - Id: " + work.getCode()
		+ " Description: " + work.getDescription());
	}
	
}

package co.edu.icesi.metrocali.blackbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class BlackBoxApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(BlackBoxApplication.class, args);
	}

}

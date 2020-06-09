package co.edu.icesi.metrocali.blackbox.confs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MiddlewareConf {

	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
	
	
}

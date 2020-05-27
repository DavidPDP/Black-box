package co.edu.icesi.metrocali.blackbox.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class OutStateMessage {

	private Integer id;

	private String name;
	
	private String stateTypeName;
	
}

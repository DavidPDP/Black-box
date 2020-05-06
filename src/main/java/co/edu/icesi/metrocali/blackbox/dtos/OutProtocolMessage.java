package co.edu.icesi.metrocali.blackbox.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class OutProtocolMessage {

	private Integer id;
	
	private Integer stepOrder;
	
	private String stepDescription;
	
	private String stepTypeName;
	
}

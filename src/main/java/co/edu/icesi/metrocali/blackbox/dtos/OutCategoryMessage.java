package co.edu.icesi.metrocali.blackbox.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class OutCategoryMessage {

	private Integer id;
	
	@JsonProperty("base_priority")
	private Integer basePriority;
	
	private String name;
	
	private List<OutProtocolMessage> protocols;
	
}

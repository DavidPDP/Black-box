package co.edu.icesi.metrocali.blackbox.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import co.edu.icesi.metrocali.blackbox.entities.policies.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class OutUserMessage {
	
	private Integer id;
	
	private String accountName;
	
	private String email;
	
	private String name;
	
	private String lastName;
	
	private String password;
	
	@JsonInclude(value=Include.NON_NULL)
	private List<Role> roles;
}

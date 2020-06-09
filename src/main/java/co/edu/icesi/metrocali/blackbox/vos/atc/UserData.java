package co.edu.icesi.metrocali.blackbox.vos.atc;

import java.io.Serializable;
import java.util.List;

import lombok.Value;

@Value
public class UserData implements Serializable{
	
	private static final long serialVersionUID = 3347963586442127346L;
	
	private Integer id;
	
	private String accountName;
	
	private Boolean isActive;
	
	private String password;
	
	private List<String> roles;
	
}

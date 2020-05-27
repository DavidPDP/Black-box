package co.edu.icesi.metrocali.blackbox.vos.atc;

import lombok.NonNull;
import lombok.Value;

@Value
public class AccessMessage {
	
	@NonNull
	private String subSystem;
	
	@NonNull
	private String accountName;
	
}

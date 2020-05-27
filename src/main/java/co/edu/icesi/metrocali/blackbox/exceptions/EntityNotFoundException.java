package co.edu.icesi.metrocali.blackbox.exceptions;

public class EntityNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -6841240137539414193L;

	public EntityNotFoundException(String message) {
		super(message);
	}
	
}

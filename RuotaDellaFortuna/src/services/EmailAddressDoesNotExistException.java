package services;

public class EmailAddressDoesNotExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EmailAddressDoesNotExistException() {
		super("Non e' stato possibile inviare la mail");
	}
}

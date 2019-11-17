package Services;

/**
 * Eccezione sollevata dal processo di registrazione quando non e' in grado di inviare una mail ad un determinata indirizzo email
 */
public class EmailAddressDoesNotExistException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmailAddressDoesNotExistException(){
        super("Non e' stato possibile inviare la mail");
    }
}

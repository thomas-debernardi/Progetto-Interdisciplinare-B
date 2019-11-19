package Email;

/**
 * Eccezione sollevata dal processo di registrazione quando non e' in grado di inviare una mail ad un determinata indirizzo email
 */
public class EmailAddressDoesNotExistException extends RuntimeException {
    public EmailAddressDoesNotExistException(){
        super("Non e' stato possibile inviare la mail");
    }
}

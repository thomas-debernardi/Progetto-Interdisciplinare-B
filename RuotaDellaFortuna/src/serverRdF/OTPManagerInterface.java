package serverRdF;

import java.rmi.Remote;
import java.rmi.RemoteException;

import services.ClientInterface;

public interface OTPManagerInterface extends Remote {
	/**
	 * Questo metodo determina se l'otp è corretto
	 * 
	 * @param otp identifica l'otp
	 * @param c   determina l'utente
	 * @return <code>true</code> se l'otp inserito è corretto, altrimenti
	 *         <code>false</code>
	 * @throws RemoteException In caso di errore di connessione con il client
	 */
	public boolean checkOTP(String otp, ClientInterface c) throws RemoteException;
}
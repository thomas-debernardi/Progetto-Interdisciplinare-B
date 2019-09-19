package serverRdF;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote{
	public static final String AUTH_FAILURE = "FAIL";
	public static final String REQUEST_CODE = "UNIQUE_CODE";
	public static final String USER_LOCKED = "USR_LOCKED";
	public static final String AUTH_OK = "OK";
	public static final String FIRST_ADMIN_REQUEST = "FST_ADMIN";
	
	//AUTENTICAZIONE,CONNESSIONE
}

package Server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface  extends Remote{

	public boolean checkEmail(String email) throws RemoteException;
	
	public boolean checkNickname(String nickname) throws RemoteException;
	
	public int signIn(Login form, Client c, boolean admin) throwa RemoteException;
}

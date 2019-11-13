package Services;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Client extends Remote {
	
	public String getNickname() throws RemoteException;
	
	public String getid() throws RemoteException;
	
	public String setNickname() throws RemoteException;
	
	public void setId(String id) throws RemoteException;

    public void setName(String name) throws RemoteException;

    public void setSurname(String surname) throws RemoteException;

    public void setEmail(String email) throws RemoteException;

    public void setGame(GamePlayerController e) throws RemoteException;
    
    public String getName() throws RemoteException;

    public String getSurname() throws RemoteException;

    public String getEmail() throws RemoteException;
	

}

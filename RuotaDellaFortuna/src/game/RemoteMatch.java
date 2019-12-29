package game;

import java.rmi.Remote;
import java.rmi.RemoteException;

import services.ClientInterface;

public interface RemoteMatch extends Remote {

	public int wheelSpin() throws RemoteException;

	public void giveConsonant(String letter, int amount) throws RemoteException;

	public void giveVocal(String letter) throws RemoteException;

	public void jolly() throws RemoteException;

	public void askForSolution() throws RemoteException;

	public void giveSolution(String solution) throws RemoteException;

	public void leaveMatchAsPlayer(ClientInterface c) throws RemoteException;

	public void leaveMatchAsObserver(ClientInterface c) throws RemoteException;

	public void askNotify(ClientInterface c) throws RemoteException;

	public void askForVocal() throws RemoteException;

	public void tryForStartMatch() throws RemoteException;
	
}

package Services;

import java.rmi.Remote;
import java.rmi.RemoteException;

import GUI.Game;
import GUI.OTPRegistration;

public interface Client extends Remote {

	public String getNickname() throws RemoteException;

	public String getId() throws RemoteException;

	public void setNickname(String nickname) throws RemoteException;

	public void setId(String id) throws RemoteException;

	public void setName(String name) throws RemoteException;

	public void setSurname(String surname) throws RemoteException;

	public void setEmail(String email) throws RemoteException;

	public void setGame(Game e) throws RemoteException;

	public void setOtpPane(OTPRegistration otp) throws RemoteException;

	public String getName() throws RemoteException;

	public String getSurname() throws RemoteException;

	public String getEmail() throws RemoteException;

	public void notifyServerError() throws RemoteException;

	public void notifyRegistrationResult(boolean success) throws RemoteException;

	public void notifyWrongOTP() throws RemoteException;

	public void notifyTooManyPlayers() throws RemoteException;

	public void notifyLeaver(String nickname) throws RemoteException;

	public void notifyMatchAbort(String reason) throws RemoteException;

	public void notifyMatchStart() throws RemoteException;

	public void notifyMancheVictory() throws RemoteException;

	public void notifyMancheResult(String winner) throws RemoteException;

	public void notifyNewManche(int numManche) throws RemoteException;

	public void setNewPhrase(String theme, String phrase) throws RemoteException;

	public void notifyNewTurn(String player) throws RemoteException;

	public void notifyYourTurn() throws RemoteException;

	public void notifyEndMatch(String winner) throws RemoteException;

	public void notifyMatchWin() throws RemoteException;

	public void notifyPlayerStats(int position, String name, int partialPoints, int points, int numJolly)
			throws RemoteException;

	public void updatePhrase(boolean[] phrase) throws RemoteException;

	public void updatePhrase(String letter) throws RemoteException;

	public void notifyTimeOut() throws RemoteException;

	public void notifyWheelResult(String risultato) throws RemoteException;

	public void askForJolly() throws RemoteException;

	public void notifyPlayerError(String name) throws RemoteException;

	public void notifyTryForSolution(String name) throws RemoteException;

	public void notifyTryVocal(String name) throws RemoteException;

	public void notifyJollyUsed(String name) throws RemoteException;

	public void notifyLetterCall(String name, String letter) throws RemoteException;

	public void notifyNoMoreConsonant() throws RemoteException;

	public void updateTimer(int num) throws RemoteException;

}

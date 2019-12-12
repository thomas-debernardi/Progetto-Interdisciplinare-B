package Services;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import GUI.Game;
import GUI.OTPRegistration;
import GUI.TabPane;

public class ClientImplementation extends UnicastRemoteObject implements Client, Serializable, Runnable {

	public static final long serialVersionUID = 1L;

	private Game game;
	private OTPRegistration otpRegistrationPane;
	private TabPane tab;
	private String id;
	private String nickname;
	private String name;
	private String surname;
	private String email;

	public ClientImplementation() throws RemoteException {
	}

	public void setGame(Game e) throws RemoteException {
		game = e;
	}

	public String getNickname() throws RemoteException {
		return nickname;
	}

	public String getId() throws RemoteException {
		return id;
	}

	public void setNickname(String nickname) throws RemoteException {
		this.nickname = nickname;
	}

	public void setId(String id) throws RemoteException {
		this.id = id;
	}

	public void setName(String name) throws RemoteException {
		this.name = name;
	}

	public void setSurname(String surname) throws RemoteException {
		this.surname = surname;
	}

	public void setEmail(String email) throws RemoteException {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getEmail() {
		return email;
	}

	public void notifyServerError() throws RemoteException {
		run();

	}

	public void notifyRegistrationResult(boolean success) throws RemoteException {
	}

	public void notifyWrongOTP() throws RemoteException {
		otpRegistrationPane.notifyWrongOTP();
	}

	public void notifyTooManyPlayers() throws RemoteException {
		tab.notifyTooManyPlayers();
	}

	public void notifyLeaver(String nickname) throws RemoteException {
		game.notifyLeaver(nickname);
	}

	public void notifyMatchAbort(String reason) throws RemoteException {
		game.notifyMatchAbort(reason);
	}

	public void notifyMatchStart() throws RemoteException {
		game.notifyMatchStart();
	}

	public void notifyMancheVictory() throws RemoteException {
		game.notifyMancheVictory();
	}

	public void notifyMancheResult(String winner) throws RemoteException {
		game.notifyMancheResult(winner);
	}

	public void notifyNewManche(int numManche) throws RemoteException {
		game.notifyNewManche(numManche);
	}

	public void setNewPhrase(String theme, String phrase) throws RemoteException {
		game.setNewPhrase(theme, phrase);
	}

	public void notifyNewTurn(String player) throws RemoteException {
		game.setTurn(player);
	}

	public void notifyYourTurn() throws RemoteException {
		game.notifyYourTurn();
	}

	public void notifyEndMatch(String winner) throws RemoteException {
		game.notifyEndMatch(winner);
	}

	public void notifyMatchWin() throws RemoteException {
		game.notifyMatchWin();
	}

	public void notifyPlayerStats(int position, String name, int partialPoints, int points, int numJolly)
			throws RemoteException {
		game.notifyPlayerStats(position, name, partialPoints, points, numJolly);
	}

	public void updatePhrase(boolean[] phrase) throws RemoteException {
		game.updatePhrase(phrase);
	}

	public void updatePhrase(String letter) throws RemoteException {
		game.updatePhrase(letter);
	}

	public void notifyTimeOut() throws RemoteException {
		game.notifyTimeOut();
	}

	public void notifyWheelResult(String risultato) throws RemoteException {
		game.wheelResult(risultato);
	}

	public void askForJolly() throws RemoteException {
		game.askForJolly();
	}

	public void notifyPlayerError(String name) throws RemoteException {
		game.notifyPlayerError(name);
	}

	public void setGameController(Game game) {
		this.game = game;
	}

	public void notifyTryForSolution(String name) throws RemoteException {
		game.callSolutionNotify(name);
	}

	public void notifyTryVocal(String name) throws RemoteException {
		game.vocalCallNotify(name);
	}

	public void notifyJollyUsed(String name) throws RemoteException {
		game.jollyNotify(name);
	}

	public void notifyLetterCall(String name, String letter) throws RemoteException {
		game.callLetterNotify(name, letter);
	}

	public void notifyNoMoreConsonant() throws RemoteException {
		game.notifyNoMoreConsonant();
	}

	public void setOtpPane(OTPRegistration otp) throws RemoteException {
		otpRegistrationPane = otp;
	}

	public void updateTimer(int num) throws RemoteException {
		game.updateTimer(num);
	}

	public String getid() throws RemoteException {
		return null;
	}

	public String setNickname() throws RemoteException {
		return null;
	}

	@Override
	public void run() {
		Notification.notify("ERROR", "Connection", true);

	}
}

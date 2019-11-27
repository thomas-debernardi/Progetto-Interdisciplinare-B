package Services;


import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import GUI.GamePlayerController;
import GUI.OTPRegistrationController;
import GUI.TabPaneController;

public class ClientImplementation extends UnicastRemoteObject implements Client, Serializable, Runnable {

    public static final long serialVersionUID = 1L;

    private GamePlayerController game;
    private OTPRegistrationController otpRegistrationPane;
    private TabPaneController tab;
    private String id;
    private String nickname;
    private String name;
    private String surname;
    private String email;

    public ClientImplementation() throws RemoteException {
    }

    public void setGame(GamePlayerController e) throws RemoteException {
        game = e;
    }

    @Override
    public String getNickname() throws RemoteException {
        return nickname;
    }

    @Override
    public String getId() throws RemoteException {
        return id;
    }

    @Override
    public void setNickname(String nickname) throws RemoteException {
        this.nickname = nickname;
    }

    @Override
    public void setId(String id) throws RemoteException {
        this.id = id;
    }

    @Override
    public void setName(String name) throws RemoteException {
        this.name = name;
    }

    @Override
    public void setSurname(String surname) throws RemoteException {
        this.surname = surname;
    }

    @Override
    public void setEmail(String email) throws RemoteException {
        this.email = email;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void notifyServerError() throws RemoteException {
run();
    
    }

    @Override
    public void notifyRegistrationResult(boolean success) throws RemoteException {
    }

    @Override
    public void notifyWrongOTP() throws RemoteException {
        otpRegistrationPane.notifyWrongOTP();
    }

    @Override
    public void notifyTooManyPlayers() throws RemoteException {
        tab.notifyTooManyPlayers();
    }

    @Override
    public void notifyLeaver(String nickname) throws RemoteException {
        game.notifyLeaver(nickname);
    }

    @Override
    public void notifyMatchAbort(String reason) throws RemoteException {
        game.notifyMatchAbort(reason);
    }

    @Override
    public void notifyMatchStart() throws RemoteException {
        game.notifyMatchStart();
    }

    @Override
    public void notifyMancheVictory() throws RemoteException {
        game.notifyMancheVictory();
    }

    @Override
    public void notifyMancheResult(String winner) throws RemoteException {
        game.notifyMancheResult(winner);
    }

    @Override
    public void notifyNewManche(int numManche) throws RemoteException {
        game.notifyNewManche(numManche);
    }

    @Override
    public void setNewPhrase(String theme, String phrase) throws RemoteException {
        game.setNewPhrase(theme, phrase);
    }

    @Override
    public void notifyNewTurn(String player) throws RemoteException {
        game.setTurn(player);
    }

    @Override
    public void notifyYourTurn() throws RemoteException {
        game.notifyYourTurn();
    }

    @Override
    public void notifyEndMatch(String winner) throws RemoteException {
        game.notifyEndMatch(winner);
    }

    @Override
    public void notifyMatchWin() throws RemoteException {
        game.notifyMatchWin();
    }

    @Override
    public void notifyPlayerStats(int position, String name, int partialPoints, int points, int numJolly) throws RemoteException {
        game.notifyPlayerStats(position, name, partialPoints, points, numJolly);
    }

    @Override
    public void updatePhrase(boolean[] phrase) throws RemoteException {
        game.updatePhrase(phrase);
    }

    @Override
    public void updatePhrase(String letter) throws RemoteException {
        game.updatePhrase(letter);
    }

    @Override
    public void notifyTimeOut() throws RemoteException {
        game.notifyTimeOut();
    }

    @Override
    public void notifyWheelResult(String risultato) throws RemoteException {
        game.wheelResult(risultato);
    }

    @Override
    public void askForJolly() throws RemoteException {
        game.askForJolly();
    }

    @Override
    public void notifyPlayerError(String name) throws RemoteException {
        game.notifyPlayerError(name);
    }

    public void setGameController(GamePlayerController game) {
        this.game = game;
    }

    @Override
    public void notifyTryForSolution(String name) throws RemoteException {
        game.callSolutionNotify(name);
    }

    @Override
    public void notifyTryVocal(String name) throws RemoteException {
        game.vocalCallNotify(name);
    }

    @Override
    public void notifyJollyUsed(String name) throws RemoteException {
        game.jollyNotify(name);
    }

    @Override
    public void notifyLetterCall(String name, String letter) throws RemoteException {
        game.callLetterNotify(name, letter);
    }

    @Override
    public void notifyNoMoreConsonant() throws RemoteException {
        game.notifyNoMoreConsonant();
    }

    @Override
    public void setOtpPane(OTPRegistrationController otp) throws RemoteException {
        otpRegistrationPane = otp;
    }

    @Override
    public void updateTimer(int num) throws RemoteException {
        game.updateTimer(num);
    }

	@Override
	public String getid() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String setNickname() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
        Notification.notify("Errore", "Errore di connessione al server", true);

	}
}

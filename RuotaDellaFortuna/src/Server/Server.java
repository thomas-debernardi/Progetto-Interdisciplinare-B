package Server;

import java.io.File;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import com.opencsv.exceptions.CsvValidationException;

import Game.RemoteMatch;
import Services.Client;
import Services.Login;
import Services.MatchData;
import Services.User;

public interface Server extends Remote {

	public boolean checkEMail(String email) throws RemoteException;

	public boolean checkNickname(String nickname) throws RemoteException;

	public OTPHelper signUp(User form, Client c, boolean admin) throws RemoteException;

	public int signIn(Login form, Client c, boolean admin) throws RemoteException;

	public ArrayList<MatchData> visualizeMatch(Client c) throws RemoteException;

	public RemoteMatch createMatch(Client c) throws RemoteException;

	public RemoteMatch joinMatch(Client c, String idMatch) throws RemoteException;

	public RemoteMatch observeMatch(Client c, String idMatch) throws RemoteException;

	public boolean addPhrases(File file) throws RemoteException, CsvValidationException;

	public boolean changePassword(String password, Client c) throws RemoteException;

	public String checkRecordPlayer() throws RemoteException;

	public String checkPerPlayer(String nickname) throws RemoteException;

	public String bestMove() throws RemoteException;

	public int averageManches() throws RemoteException;

	public boolean resetPassword(Client c, String mail) throws RemoteException;
}

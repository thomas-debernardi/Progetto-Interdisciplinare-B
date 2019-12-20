package serverRdF;


import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import com.opencsv.exceptions.CsvValidationException;

import database.DBManager;
import game.MatchManager;
import game.RemoteMatch;
import services.Client;
import services.EmailManager;
import services.Login;
import services.MatchData;
import services.User;


public class ServerImplementation extends UnicastRemoteObject implements Server {
    private DBManager dbManager;
    private EmailManager emailManager;
    private ProfileManager profileManager;
    private PhraseManager phraseManager;
    private MonitoringManager monitoringManager;
    private MatchVisualizer matchVisualizer;
    private MatchManager matchManager;
    private AutenticationManager autenticationManager;
    private RegistrationManager registrationManager;

    public ServerImplementation(DBManager dbmng, EmailManager emailmang) throws RemoteException {
        dbManager = dbmng;
        emailManager = emailmang;
        profileManager = ProfileManager.createProfileManager(dbManager, emailManager);
        phraseManager = PhraseManager.createPhraseManager(dbManager);
        monitoringManager = MonitoringManager.createMonitoringManager(dbManager);
        matchManager = MatchManager.createMatchManager(dbManager,emailManager);
        matchVisualizer = MatchVisualizer.createMatchVisualizer(matchManager);
        autenticationManager = AutenticationManager.createAutenticationManager(dbManager);
        registrationManager = RegistrationManager.createRegistrationManager(dbManager, emailManager);
    }

    public static void serverError(Client c) {
        if (c == null) {
            System.err.println("Server error");
        } else {
            try {
                c.notifyServerError();
            } catch (RemoteException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }

    public boolean checkEMail(String email) throws RemoteException {
        return registrationManager.checkEmail(email);
    }

    public boolean checkNickname(String nickname) throws RemoteException {
        return registrationManager.checkNickname(nickname);
    }

    public OTPHelper signUp(User form, Client c, boolean admin) throws RemoteException {
        return registrationManager.signUp(form,c,admin);
    }

    public int signIn(Login form, Client c, boolean admin) throws RemoteException {
        return autenticationManager.signIn(form,c,admin);
    }

    public ArrayList<MatchData> visualizeMatch(Client c) throws RemoteException {
        return matchVisualizer.visualizeMatch();
    }

    public RemoteMatch createMatch(Client c) throws RemoteException {
        return matchManager.createMatch(c);
    }

    public RemoteMatch joinMatch(Client c, String idMatch) throws RemoteException {
            return matchManager.joinMatch(c,idMatch);
    }

    public RemoteMatch observeMatch(Client c, String idMatch) throws RemoteException {
        return matchManager.observeMatch(c,idMatch);
    }

    public boolean addPhrases(File file) throws RemoteException, CsvValidationException {
        try {
            return phraseManager.addPhrases(file);
        }catch (IOException e){
            return false;
        }
    }


    public boolean changePassword(String password, Client c) throws RemoteException {
        String idUser = c.getId();
        return profileManager.changePassword(password, idUser);
    }

    public String checkRecordPlayer() throws RemoteException {
        return monitoringManager.bestStatsUsers();
    }

    public String checkPerPlayer(String nickname) throws RemoteException {
        return monitoringManager.perPlayerStats(nickname);
    }

    public String bestMove() throws RemoteException {
        return monitoringManager.getBestMove();
    }

    
    public int averageManches() throws RemoteException {
        return monitoringManager.averageMovesPerManches();
    }

    public boolean resetPassword(Client c,String mail) throws RemoteException {
        return profileManager.resetPassword(mail);
    }
    
    public boolean changeName(String name, Client c) throws RemoteException {
        String idUser = c.getId();
        return profileManager.changeName(name, idUser);
    }

    public boolean changeSurname(String surname, Client c) throws RemoteException {
        String idUser = c.getId();
        return profileManager.changeSurname(surname, idUser);
    }

    public boolean changeNickname(String nickname, Client c) throws RemoteException {
        String idUser = c.getId();
        return profileManager.changeNickname(nickname, idUser);
    }
    
    public boolean checkPassword(String nickname,String password, Client c) throws RemoteException{
        return registrationManager.checkPassword(nickname,password);
    }
}
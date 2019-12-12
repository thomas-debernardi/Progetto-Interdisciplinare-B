package Game;


import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;

import Database.DBManager;
import Email.EmailManager;
import Server.ServerImplementation;
import Services.Client;



public class MatchManager {
    private static HashMap<String, Match> matches;
    private static MatchManager matchManager = null;
    private DBManager dbManager;
    private EmailManager emailmng;

    private MatchManager(DBManager dbmng, EmailManager email) {
        dbManager = dbmng;
        matches = new HashMap<String, Match>();
        emailmng = email;
    }



    public static MatchManager createMatchManager(DBManager dbmng, EmailManager email) {
        if (matchManager == null) {
            matchManager = new MatchManager(dbmng, email);
            return matchManager;
        } else
            return matchManager;
    }



    public synchronized RemoteMatch createMatch(Client c) {
        String id = UUID.randomUUID().toString();
        LocalDateTime currentTime = LocalDateTime.now();
        Match match = null;
        try {
            boolean bool = dbManager.addMatch(id, currentTime);
            if (!bool) {
                try {
                    c.notifyServerError();
                    throw new RuntimeException();
                } catch (RemoteException ex) {
                    System.err.println(ex.getMessage());
                }
            }
            match = new Match(id, currentTime, dbManager, emailmng);
            match.addPlayer(c);
            matches.put(id, match);
            return match;
        } catch (RemoteException e) {
            ServerImplementation.serverError(c);
        } finally {
            return match;
        }
    }

    public RemoteMatch joinMatch(Client c, String idMatch) {
        Match match = matches.get(idMatch);
        if(match == null)
            return null;
        boolean full;
        try {
            full = match.addPlayer(c);
            if (full) {
                try {
                    c.notifyTooManyPlayers();
                    return null;
                } catch (RemoteException e) {
                    try {
                        c.notifyServerError();
                        return null;
                    } catch (RemoteException ex) {
                        System.err.println(ex.getMessage());
                        return null;
                    }
                }
            }
        } catch (RemoteException e) {
            try {
                c.notifyServerError();
                return null;
            } catch (RemoteException ex) {
                System.err.println(ex.getMessage());
                return null;
            }
        }
        return match;
    }


    public RemoteMatch observeMatch(Client c, String idMatch) {
        Match match = matches.get(idMatch);
        if(match == null)
            return null;
        try {
            match.addObserver(c);
        } catch (RemoteException e) {
            try {
                c.notifyServerError();
                return null;
            } catch (RemoteException ex) {
                System.err.println(ex.getMessage());
                return null;
            }
        }
        return match;
    }

    static void deleteMatch(String idMatch) {
        matches.remove(idMatch);
    }

    public HashMap<String, Match> getMatches() {
        return matches;
    }
}

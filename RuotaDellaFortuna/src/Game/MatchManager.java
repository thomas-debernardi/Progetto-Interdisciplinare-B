package Game;


import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;

import Database.DBManager;
import Services.Client;


/**
 * Questa e' la classe che si occupa della gestione delle partita in attesa di giocatori e in corso.
 * <p>
 * Permette la creazione e la partecipazione alle partite, le quali sono contenute in una tabella hash accessibile tramite l'apposito getter.
 */
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


    /**
     * @param dbmng il riferimento all'oggetto {@link DBManager}
     * @param email il riferimento all'oggetto {@link EmailManager}
     * @return il singleton di tipo {@link MatchManager}
     */
    public static MatchManager createMatchManager(DBManager dbmng, EmailManager email) {
        if (matchManager == null) {
            matchManager = new MatchManager(dbmng, email);
            return matchManager;
        } else
            return matchManager;
    }


    /**
     * Il client richiamera' questo metodo per creare una partita e ricevera' il riferemento all'oggetto che si occupera' della gestione del singolo Match
     *
     * @param c il riferimento del Client che sara' fornito all'oggetto remoto {@link Match} in modo da poter inviargli le notifiche (Observers design pattern)
     * @return match un riferimento all'oggetto remoto {@link RemoteMatch} della partita appena creata.
     */
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

    /**
     * @param c       il riferimento del Client che sara' fornito all'oggetto remoto {@link Match} in modo da poter inviargli le notifiche (Observer design pattern)
     * @param idMatch il nome del match al quale si vuole partecipare
     * @return un riferimento all'oggetto remoto {@link RemoteMatch} della partita a cui si ha appena partecipato, o null nel caso in cui la partita sia piena o ci siano stati problemi con la connessione al server
     */
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


    /**
     * @param c       il riferimento del Client che sara' fornito all'oggetto remoto {@link Match} in modo da poter inviargli le notifiche (Observer design pattern)
     * @param idMatch il nome del match al quale si vuole partecipare
     * @return match un riferimento all'oggetto remoto {@link RemoteMatch} della partita a cui si ha appena partecipato come osservatore, o null nel caso in cui ci siano stati problemi con la connessione al server
     */
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

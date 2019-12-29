package game;


import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;

import database.DBManager;
import serverRdF.ServerImplementation;
import services.Client;
import services.EmailManager;



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
     * Metodo per la creazione di un gestore di partita
     * 
     * @param dbmng contiene il gestore di database del sistema
     * @param email contiene il gestore di mail del sistema
     * @return ritorna il gestore di partita appena creato
     */
    public static MatchManager createMatchManager(DBManager dbmng, EmailManager email) {
        if (matchManager == null) {
            matchManager = new MatchManager(dbmng, email);
            return matchManager;
        } else
            return matchManager;
    }



    /**
     * Metodo per la creazione del match da parte di un utente
     * 
     * @param c contiene il Client del creatore del match
     * @return match ritorna un oggetto di tipo Match riferito alla partita che si sta creando
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
     * Metodo per la partecipazione ad un match già creato
     * 
     * @param c contiene il Client che vuole aggiungersi alla partita
     * @param idMatch contiene l'ID del match a cui ci si vuole aggiungere
     * @return ritorna il match se avviene la partecipazione, null altrimenti
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
     * Metodo per ottenere accesso al match come osservatore
     * 
     * @param c contiene il Client dell'utente interessato ad unirsi alla partita
     * @param idMatch contiene l'ID del match a cui si vuol partecipare
     * @return ritorna il match se avviene la partecipazione, null altrimenti
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

    /**
     * Metodo per la cancellazione di un match tramite ID
     * 
     * @param idMatch contiene l'ID del match da eliminare
     */
    static void deleteMatch(String idMatch) {
        matches.remove(idMatch);
    }

    /**
     * Metodo getter per ottenere l'HashMap dei match
     * 
     * @return ritorna i match attualmente validi
     */
    public HashMap<String, Match> getMatches() {
        return matches;
    }
}

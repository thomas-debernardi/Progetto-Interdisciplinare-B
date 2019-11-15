package Database;

import java.sql.SQLException;

/**
 * Interfaccia del Data Access Object che si occupa degli accessi al database relativi a chi ha partecipato ad una manche, sia come giocatore che osservatore
 */
public interface MancheJoinersDAO {
    String mancheJoinersTable = "Manchejoiners";
    String mancheJoinersIdMatchAttribute = "id";
    String mancheJoinersNumMancheAttribute = "number";
    String mancheJoinersIdPlayerAttribute = "idplayer";
    String mancheJoinersObserverAttribute = "observer";

    /**
     * Permette di aggiungere un partecipante al database
     *
     * @param idMatch id del match
     * @param numManche numero della manche
     * @param idPlayer id del giocatore
     * @param observer <code>true</code> se l'utente ha partecipato come osservatore, <code>false</code> se ha partecipato come giocatore
     * @return <code>true</code> se l'inserimento e' andato a buon fine, <code>false</code> altrimenti
     * @throws SQLException in caso di errore di connessione al database
     */
    boolean addMancheJoiner(String idMatch, int numManche, String idPlayer, boolean observer) throws SQLException;

    /**
     * Permette di ottenere il numero di manche giocate da un dato utente
     *
     * @param id l'id del giocatore
     * @return il numero di manche giocate
     * @throws SQLException in caso di errore di connessione al database
     */
    int getManchePlayedByUser(String id) throws SQLException;

    /**
     * Permette di ottenere il numero di partite vinte da un giocatore
     *
     * @param id id del giocatore
     * @return il numero di partite vinte
     * @throws SQLException in caso di errore di connessione al database
     */
    int getMatchesPlayedByUser(String id) throws SQLException;

    /**
     * Permette di ottenere il numero di manche osservate da un utente
     *
     * @param id id dell'utente
     * @return il numero di manche osservate
     * @throws SQLException in caso di errore di connessione al database
     */
    int getObservedManches(String id) throws SQLException;

    /**
     * Permette di ottenere il numero di partite osservate da un utente
     *
     * @param id id dell'utente
     * @return il numero di partite osservate
     * @throws SQLException in caso di errore di connessione al database
     */
    int getObservedMatches(String id) throws SQLException;
}

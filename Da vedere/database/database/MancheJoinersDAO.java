package database;

import java.sql.SQLException;


public interface MancheJoinersDAO {
    String mancheJoinersTable = "Manchejoiners";
    String mancheJoinersIdMatchAttribute = "id";
    String mancheJoinersNumMancheAttribute = "number";
    String mancheJoinersIdPlayerAttribute = "idplayer";
    String mancheJoinersObserverAttribute = "observer";

    /**
     * Metodo che permette l'aggiunta di un utente che si è unito a una partita al db
     * 
     * @param idMatch identificativo del match
     * @param numManche numero manche 
     * @param idPlayer identificativo del giocatore
     * @param observer <code>true</code> se era un osservatore, altrimenti <code>false</code>
     * @return <code>true</code> se l'inserimento è andato a buon fine, altrimenti <code>false</code>
     * @throws SQLException in caso di errore connesione al db
     */
    boolean addMancheJoiner(String idMatch, int numManche, String idPlayer, boolean observer) throws SQLException;


    /**
     * Metodo che permette di viualizzare il numero delle manche giocate da un utente
     * 
     * @param id identificativo utente
     * @return numero di manche giocate
     * @throws SQLException in caso di errore connesione al db
     */
    int getManchePlayedByUser(String id) throws SQLException;


    /**
     * Metodo che permette la visualizzazione del numero di match giocati da un utente
     * @param id identificativo utente
     * @return numero match giocati
     * @throws SQLException in caso di errore connesione al db
     */
    int getMatchesPlayedByUser(String id) throws SQLException;


    /**
     * Metodo che permette la visualizzazione del numero di manche osservate da un utente
     * 
     * @param id identificativo utente
     * @return numero di manche osservate
     * @throws SQLException in caso di errore connesione al db
     */
    int getObservedManches(String id) throws SQLException;


    /**
     * Metodo che permette la visualizzazione del numero di match osservati da un utente
     * @param id identificativo utente
     * @return numero di match osservati
     * @throws SQLException in caso di errore connesione al db
     */
    int getObservedMatches(String id) throws SQLException;
}

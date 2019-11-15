package Database;
import java.sql.SQLException;

/**
 * Interfaccia del Data Access Object che si occupa degli accessi al database relativi ai vincitori di una partita
 */
public interface MatchWinnersDAO {
    String matchWinnersTable = "matchwinners";
    String matchWinnersIdMatchAttribute  = "idmatch";
    String matchWinnersidPlayerAttribute = "idplayer";
    String matchWinnersAmountAttribute = "amount";

    /**
     * Metodo per l'aggiunta del vincitore di una partita nel database
     *
     * @param idMatch id del match vinto
     * @param idPlayer id del vincitore
     * @param amount La quantita' di punti acquisiti
     * @return <code>true</code> se l'inserimento va a buon fine, <code>false</code> altrimenti
     * @throws SQLException in caso di errore di connessione al database
     */
    boolean addMatchWinner(String idMatch, String idPlayer, int amount) throws SQLException;

    /**
     * Permette di individuare il numero di partite vinte da un dato giocatore
     *
     * @param id id del giocatore
     * @return il numero di partite vinte
     * @throws SQLException in caso di errore di connessione al database
     */
    int getWonMatchesByUser(String id) throws SQLException;

    /**
     * Permette di individuare il totale di punti vinti in tutte le partite da un dato giocatore
     *
     * @param id id del giocatore
     * @return il numero di punti vinti
     * @throws SQLException in caso di errore di connessione al database
     */
    int getTotalPointsByUser(String id) throws SQLException;
}

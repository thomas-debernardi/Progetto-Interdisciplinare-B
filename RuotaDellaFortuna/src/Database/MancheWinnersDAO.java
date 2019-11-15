package Database;
import java.sql.SQLException;

/**
 * Interfaccia del Data Access Object che si occupa degli accessi al database relativi ai vincitori delle singole manche
 */
public interface MancheWinnersDAO {
    String manchesWinnersTable = "manchewinners";
    String manchesWinnersidAttribute = "id";
    String manchesWinnersNumberAttribute = "number";
    String manchesWinnersidPlayerAttribute = "idplayer";
    String manchesWinnersAmountAttribute = "amount";

    /**
     * Permette di inserire il vincitore di una manche nel database
     *
     * @param idPlayer id del giocatore
     * @param manche un oggetto di tipo {@link ManchesDTO} contenente i dati della manche
     * @param amount la quantita' di punti acquisiti
     * @return <code>true</code> se l'inserimento e' andato a buon fine, <code>false</code> altrimenti
     * @throws SQLException in caso di errore di connessione al database
     */
    boolean addMancheWinner(String idPlayer, ManchesDTO manche, int amount) throws SQLException;

    /**
     * Permette di individuare il numero totale di manche vinte.
     *
     * @return il numero di tuple presenti nella tabella "MancheWinners"
     * @throws SQLException in caso di errore di connessione al database
     */
    int getNumWinnedManches() throws SQLException;

    /**
     * Permette di individuare il numero di manche vinte da un dato giocatore
     *
     * @param id id del giocatore
     * @return il numero di occorrenze del giocatore nella tabella "MancheWinners"
     * @throws SQLException in caso di errore di connessione al database
     */
    int getMancheWonByUser(String id) throws SQLException;
}

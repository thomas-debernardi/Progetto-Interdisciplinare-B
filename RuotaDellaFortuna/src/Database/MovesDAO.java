package Database;

import java.sql.SQLException;

import Game.Move;

/**
 * Interfaccia del Data Access Object che si occupa degli accessi al database relativi alle mosse
 */
public interface MovesDAO {
    String MovesTable = "moves";
    String MovesIdPlayerAttribute = "id";
    String MovesMoveTypeAttribute = "movetype";
    String MovesOutcomeAttribute = "outcome";
    String MovesIdMatchAttribute = "idmanche";
    String MovesMancheNumberAttribute = "number";
    String MovesMoveIdAttribute = "moveid";

    /**
     * Questo metodo aggiunge nel database una mossa
     *
     * @param move la mossa da inserire
     * @return <code>true</code> se l'inserimento va a buon fine, altrimenti <code>false</code>
     * @throws SQLException in caso di errore di connessione al database
     */
    boolean addMove(Move move) throws SQLException;

    /**
     * Questo metodo permette di individuare la mossa che ha fatto guadagnare la maggiore quantita' di punti
     *
     * @return Un oggetto di tipo {@link UsersDTO}
     * @throws SQLException in caso di errore di connessione al database
     */
    public MovesDTO getBestMove() throws SQLException;

    /**
     * Questo metodo permette di individuare il numero medio di mosse eseguite per indovinare una frase misteriosa
     *
     * @param numManche //todo
     * @return il numero medio di mosse eseguite per indovinare una frase misteriosa
     * @throws SQLException
     */
    public int getAverageMovesPerManche(int numManche) throws SQLException;

    int getAllPassedTurnByUser(String id) throws SQLException;

    int getAllLossesByUser(String id) throws SQLException;
}

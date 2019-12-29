package database;

import java.sql.SQLException;

import game.Move;

public interface MovesDAO {
	String MovesTable = "moves";
	String MovesIdPlayerAttribute = "id";
	String MovesMoveTypeAttribute = "movetype";
	String MovesOutcomeAttribute = "outcome";
	String MovesIdMatchAttribute = "idmanche";
	String MovesMancheNumberAttribute = "number";
	String MovesMoveIdAttribute = "moveid";

	/**
	 * Metodo per l'inserimento di una mossa nel db
	 * 
	 * @param move mossa da aggiungere
	 * @return <code>true</code> se avviene il corretto inserimento, altrimenti
	 *         <code>false</code>
	 * @throws SQLException gestione di errori di connessione al db
	 */
	boolean addMove(Move move) throws SQLException;

	/**
	 * Metodo che permette di visualizzare la mossa che ha portato ad avere un
	 * punteggio più alto
	 * 
	 * @return oggetto della classe MovesDTO
	 * @throws SQLException gestione errore di connessione al db
	 */
	public MovesDTO getBestMove() throws SQLException;

	/**
	 * Metodo che permette di visualizzare il numero medio di mosse, per indovinare
	 * una frase, che vengono eseguite per manche
	 * 
	 * @param numManche numero della manche
	 * @return numero medio delle mosse
	 * @throws SQLException gestione errore di connessione al db
	 */
	public int getAverageMovesPerManche(int numManche) throws SQLException;

	/**
	 * Metodo che permette di visualizzare il numero di tutti i turni passati da un
	 * giocatore
	 * 
	 * @param id identificativo del giocatore
	 * @return numero turni passati
	 * @throws SQLException gestione errore di connessione al db
	 */
	int getAllPassedTurnByUser(String id) throws SQLException;

	/**
	 * Metodo che permette di visualizzare il numero di tutte le volte che un utente
	 * ha perso un turno
	 * 
	 * @param id identificativo del giocatore
	 * @return numero di perde turno
	 * @throws SQLException gestione errore di connessione al db
	 */
	int getAllLossesByUser(String id) throws SQLException;
}

package database;

import java.sql.SQLException;

public interface MatchWinnersDAO {
	String matchWinnersTable = "matchwinners";
	String matchWinnersIdMatchAttribute = "idmatch";
	String matchWinnersidPlayerAttribute = "idplayer";
	String matchWinnersAmountAttribute = "amount";

	/**
	 * Metodo usato per l'inserimento di un vincitore dei match nel db
	 * 
	 * @param idMatch  identificativo del match
	 * @param idPlayer identificativo vincitore
	 * @param amount   punteggio del vincitore
	 * @return true se si verifica il corretto inserimento, altrimenti
	 *         false
	 * @throws SQLException
	 */
	boolean addMatchWinner(String idMatch, String idPlayer, int amount) throws SQLException;

	/**
	 * Metodo che restituisce il numero dei match vinti da un utente
	 * 
	 * @param id identificativo del vincitore
	 * @return numero di match vinti
	 * @throws SQLException gestione errori di connessione al db
	 */
	int getWonMatchesByUser(String id) throws SQLException;

	/**
	 * Metodo che restituisce il totale dei punti in tutti i match vinti da un
	 * utente
	 * 
	 * @param id identificativo vincitore
	 * @return punti guadagnati
	 * @throws SQLException
	 */
	int getTotalPointsByUser(String id) throws SQLException;
}

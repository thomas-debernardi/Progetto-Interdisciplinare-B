package database;

import java.sql.SQLException;

public interface MancheWinnersDAO {
	String manchesWinnersTable = "manchewinners";
	String manchesWinnersidAttribute = "id";
	String manchesWinnersNumberAttribute = "number";
	String manchesWinnersidPlayerAttribute = "idplayer";
	String manchesWinnersAmountAttribute = "amount";

	/**
	 * Metodo per aggiungere al db un vicintore della manche
	 * 
	 * @param idPlayer identificativo vincitore
	 * @param manche   manche vinta
	 * @param amount   punteggio con il quale si è vinta la manche
	 * @return <code>true</code> se l'inserimento è andato a buon fine, altrimenti
	 *         <code>false</code>
	 * @throws SQLException in caso di errore connessione al db
	 */
	boolean addMancheWinner(String idPlayer, ManchesDTO manche, int amount) throws SQLException;

	/**
	 * Metodo per visualizzare il numero delle manche vinte
	 * 
	 * @return il numero di manche
	 * @throws SQLException in caso di errore connessione al db
	 */
	int getNumWinnedManches() throws SQLException;

	/**
	 * Metodo per visualizzare il numero delle manche vinte da un utente
	 * 
	 * @param id identificatido del giocatore
	 * @return il numero di manche
	 * @throws SQLException in caso di errore connessione al db
	 */
	int getMancheWonByUser(String id) throws SQLException;
}

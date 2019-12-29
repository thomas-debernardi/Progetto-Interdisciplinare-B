package database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PhrasesDAO {
	String PhraseTable = "phrases";
	String PhrasePhraseAttribute = "phrase";
	String PhraseThemeAttribute = "theme";
	String PhraseIdAttribute = "id";

	/**
	 * Metodo per cercare all'interno del database cinque frasi che gli utenti non
	 * hanno mai visto
	 * 
	 * @param idPlayer1 identificativo giocatore 1
	 * @param idPlayer2 identificativo giocatore 2
	 * @param idPlayer3 identificativo giocatore 3
	 * @return lista delle cinque frasi, se non le trova resistuisce null
	 * @throws SQLException gestione errori di accesso al db
	 */
	List<PhrasesDTO> get5Phrases(String idPlayer1, String idPlayer2, String idPlayer3) throws SQLException;

	/**
	 * Metodo che permette l'inserimento delle frasi nel db
	 * 
	 * @param phrases lista di frasi da inserire
	 * @return <code>true</code> se le frasi sono inserite correttamente, altrimenti
	 *         <code>false>/code>
	 * @throws SQLException gestione errori di accesso al db
	 */
	boolean addPhrases(ArrayList<PhrasesDTO> phrases) throws SQLException;

	/**
	 * Metodo che definisce la lista di frasi all'interno del db
	 * 
	 * @return la lista delle frasi nel db, null se non ce ne sono
	 * @throws SQLException gestione errori di accesso al db
	 */
	List<PhrasesDTO> getAllPhrases() throws SQLException;

	/**
	 * Metodo per l'eliminazione di tutte le frasi nel db
	 * 
	 * @return <code>true</code> se vengono eliminate tutte le frasi, altrimenti
	 *         <code>false</code>
	 * @throws SQLException gestione errori di accesso al db
	 */
	boolean deleteAllPhrases() throws SQLException;

	/**
	 * Metodo che permette di eliminare frasi nel db
	 * 
	 * @param position posizione della frase
	 * @return <code>true</code> se la frase viene eliminata, altrimenti
	 *         <code>false</code>
	 * @throws SQLException gestione errori di accesso al db
	 */
	boolean deletePhrase(int position) throws SQLException;

	/**
	 * Metodo che permette di aggiornare frase nel db
	 * 
	 * @param DTO frase da aggiornare
	 * @return <code>true</code> se la frase viene aggiornata, altrimenti
	 *         <code>false</code>
	 * @throws SQLException gestione errori di accesso al db
	 */
	boolean uploadPhrase(PhrasesDTO DTO) throws SQLException;

	/**
	 * Metodo che permette di aggiungere frase nel db
	 * 
	 * @param DTO frase da aggiungere
	 * @return <code>true</code> se la frase viene aggiunta, altrimenti
	 *         <code>false</code>
	 * @throws SQLException gestione errori di accesso al db
	 */
	boolean addPhrase(PhrasesDTO DTO) throws SQLException;
}
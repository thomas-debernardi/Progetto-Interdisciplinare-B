package database;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import game.Move;
import services.User;

/**
 * Interfaccia utilizzata dai manager del server per accedere ai metodi del
 * database
 */
public interface DBManagerInterface {

	/**
	 * Metodo che permette di aggiungere una partita nel db
	 * 
	 * @param id   identificativo della partita
	 * @param time data e ora di creazione della partita
	 * @return <code>true</code> se l'inserimento partita va a buon fine, altrimenti
	 *         <code>false</code>
	 */
	boolean addMatch(String id, LocalDateTime time);

	/**
	 * Metodo per 'inserimento di un nuovo utente nel db
	 * 
	 * @param user    riferimento all'utente
	 * @param isAdmin <code>true</code> se l'utente è un admin, altrimenti
	 *                <code>false</code>
	 * @return <code>true</code> se l'inserimento utente va a buon fine, altrimenti
	 *         <code>false</code>
	 */
	boolean addUser(User user, boolean isAdmin);

	/**
	 * Metodo per la ricerca di un utente data la mail
	 * 
	 * @param email la mail dell'utente
	 * @return riferimento all'utente trovato, null altrimenti
	 */
	UsersDTO getUserByEmail(String email);

	/**
	 * Metodo per la ricerca di un utente dato il nickname
	 * 
	 * @param nickname nickname dell'utente
	 * @return riferimento all'utente trovato, null altrimenti
	 */
	UsersDTO getUserByNickname(String nickname);

	/**
	 * Metodo per la ricerca di un utente dato l'id
	 * 
	 * @param id identificativo dell'utente
	 * @return riferimento all'utente trovato, null altrimenti
	 */
	UsersDTO getUserById(String id);

	/**
	 * Metodo che fornisce una lista di tutti gli utenti admin
	 * 
	 * @return lista di tutti gli utenti admin
	 */
	List<UsersDTO> getAllAdmin();

	boolean getAnyAdmin();

	/**
	 * Metodo che permette di eliminare un utente nel db
	 * 
	 * @param id identificativo dell'utente
	 * @return <code>true</code> se l'utente è stato eliminato, altrimenti
	 *         <code>false</code>
	 */
	boolean deleteUser(String id);

	/**
	 * Metodo che permette di controllare la correttezza della mail e password,
	 * inoltre determina se l'utente è un admin
	 * 
	 * @param email    mail utente
	 * @param password password usata dall'utente
	 * @param admin    <code>true</code> se è adim, altrimenti <code>false</code>
	 * @return <code>-1</code> se la password o la mail (o entrambe) sono errate,
	 *         <code>0</code> se l'utente è un admin, <code>1</code> se l'utente non
	 *         lo è
	 */
	int checkLogin(String email, String password, boolean admin);

	/**
	 * Metodo che prende dal database cinque frasi che i giocatori non hanno mai
	 * visto
	 * 
	 * @param idPlayer1 identificativo del primo giocatore
	 * @param idPlayer2 identificativo del secondo giocatore
	 * @param idPlayer3 identificativo del terzo giocatore
	 * @return lista delle cinque frasi, altrimenti null
	 */
	List<PhrasesDTO> get5Phrases(String idPlayer1, String idPlayer2, String idPlayer3);

	/**
	 * Metodo per aggiungere delle frasi nel db
	 * 
	 * @param phrases lista delle frasi che si vogliono aggiungere nel db
	 * @return <code>true</code> se l'inserimento va a buon fine, altrimenti
	 *         <code>false</code>
	 */
	boolean addPhrases(ArrayList<PhrasesDTO> phrases);

	/**
	 * Metodo che prende tutte le frasi nel db
	 * 
	 * @return la lista delle frasi nel db
	 */
	List<PhrasesDTO> getAllPhrases();

	/**
	 * Metodo che permette di eliminare le frasi nel db
	 * 
	 * @param position posizione della frase che si vuole eliminare
	 * @return <code>true</code> se l'eliminazione è andata a buon fine, altrimenti
	 *         <code>false</code>
	 */
	boolean deletePhrase(int position);

	/**
	 * Metodo che permette l'eliminazione di un match nel db
	 * 
	 * @param idMatch identifica il match
	 * @return <code>true</code> se il match è stato eliminato, altrimenti
	 *         <code>false</code>
	 */
	boolean deleteMatch(String idMatch);

	/**
	 * Metodo per aggiungere una mossa nel db
	 * 
	 * @param move mossa da inserire
	 * @return <code>true</code> se la mossa è stata inserita, altrimenti
	 *         <code>false</code>
	 */
	boolean addMove(Move move);

	/**
	 * Metodo per aggiungere una manche nel db
	 * 
	 * @param manche manche che si vuole inserire
	 * @return <code>true</code> se l'inserimento è andato a buon fine, altrimenti
	 *         <code>false</code>
	 */
	boolean addManche(ManchesDTO manche);

	/**
	 * Metodo che permette di inserire il vicincitore della manche nel db
	 * 
	 * @param idPlayer identificativo del vincitore
	 * @param manche   manche che è stata vinta dal giocatore
	 * @param amount   punteggio del vincitore
	 * @return <code>true</code> se l'inserimento va a buon fine, altrimenti
	 *         <code>false</code>
	 */
	boolean addMancheWinner(String idPlayer, ManchesDTO manche, int amount);

	/**
	 * Metodo che permette di collegare gli utenti che hanno osservato o giocato una
	 * determinata manche
	 * 
	 * @param idMatch   identificativo del match
	 * @param numManche numero della manche
	 * @param userId    identificativo dell'utente
	 * @param observer  <code>true</code> se è un osservatore, se non lo è
	 *                  <code>false</code>
	 * @return <code>true</code> se l'inserimento è andato a buon fine, altrimenti
	 *         <code>false</code>
	 */
	boolean addMancheJoiner(String idMatch, int numManche, String userId, boolean observer);

	/**
	 * Metodo che permette l'inserimento di un vincitore del match nel db
	 * 
	 * @param idMatch  identificativo del match
	 * @param idPlayer identificativo del giocatore
	 * @param amount   punteggio del vincitore
	 * @return <code>true</code> se l'inserimento va a buon fine, altrimenti
	 *         <code>false</code>
	 */
	boolean addMatchWinner(String idMatch, String idPlayer, int amount);

	/**
	 * Metodo che permette di aggiornare la riga della tabella users
	 * 
	 * @param user utente che si vuole modificare
	 * @return <code>true</code> se la modifica viene eseguita, altrimenti
	 *         <code>false</code>
	 */
	boolean updateUser(UsersDTO user);

	/**
	 * Metodo che permette di visualizzare la miglior mossa eseguita (quella che ha
	 * fatto guadagnare più punti
	 * 
	 * @return un oggetto della classe UserDTO
	 */
	MovesDTO getBestMove();

	/**
	 * Metodo che permette di visualizzare, per manche, il numero medio di mosse per
	 * indovinare la frase
	 * 
	 * @return il numero medio di mosse per indovinare la frase
	 */
	int getAverageMovesPerManche();

	/**
	 * Metodo che permette di visualizzare le manche vinte da un giocatore
	 * 
	 * @param id identificativo del giocatore
	 * @return il numero di manche vinte
	 */
	int getWonManchesByUser(String id);

	/**
	 * Metodo che permette di visualizzare le manche giocate da un giocatore
	 * 
	 * @param id identificativo del giocatore
	 * @return numero delle manche giocate
	 */
	int getManchePlayedByUser(String id);

	/**
	 * Metodo che permette di visualizzare i match giocati da un giocatore
	 * 
	 * @param id identificativo del giocatore
	 * @return numero dei match giocate
	 */
	int getMatchesPlayedByUser(String id);

	/**
	 * Metodo che permette di visualizzare i match vinti da un giocatore
	 * 
	 * @param id identificativo del giocatore
	 * @return numero dei match vinti
	 */
	int getWonMatchesByUser(String id);

	/**
	 * Metodo che permette di visualizzare le manche osservate da un utente
	 * 
	 * @param id identificativo utente
	 * @return numero delle manche osservate
	 */
	int getObservedManchesByUser(String id);

	/**
	 * Metodo che permette di visualizzare i matches osservati da un utente
	 * 
	 * @param id identificativo utente
	 * @return numero di match osservati
	 */
	int getObservedMatchesByUser(String id);

	/**
	 * Metodo che permette di visualizzare il numero medio di punti fatti da un
	 * utente per partita
	 * 
	 * @param id identificativo del giocatore
	 * @return numero punteggio per partita
	 */
	int getAveragePointsWonByUser(String id);

	/**
	 * Metodo che permette di visualizzare il numero medio di turni passati da parte
	 * di un utente per manche
	 * 
	 * @param id identificativo del giocatore
	 * @return numero di turni passati per manche
	 */
	int getAveragePassedTurnPerMancheByUser(String id);

	/**
	 * Metodo che permette di visualizzare il numero medio di turni passati da parte
	 * di un utente per match
	 * 
	 * @param id identificativo del giocatore
	 * @return numero di turni passati per match
	 */
	int getAveragePassedTurnPerMatchByUser(String id);

	/**
	 * Metodo che permette di visualizzare il numero medio di volte che un utente ha
	 * perso tutto per manche
	 * 
	 * @param id identificativo del giocatore
	 * @return numero di punti persi per manche
	 */
	int getAverageLossPerMancheByUser(String id);

	/**
	 * Metodo che permette di visualizzare il numero medio di volte che un utente ha
	 * perso tutto per match
	 * 
	 * @param id identificativo del giocatore
	 * @return numero di punti persi per match
	 */
	int getAverageLossPerMatchByUser(String id);

	/**
	 * Metodo per visualizzare il miglior giocatore per manche
	 * 
	 * @return oggetto della classe UsersDTO che si riferisce al giocatore
	 */
	UsersDTO getBestUserForManche();

	/**
	 * Metodo per visualizzare il miglior giocatore per match
	 * 
	 * @return oggetto della classe UsersDTO che si riferisce al giocatore
	 */
	UsersDTO getBestUserForMatch();

	/**
	 * Metodo per visualizzare il giocatore che ha giocato più manche
	 * 
	 * @return oggetto della classe UsersDTO che si riferisce al giocatore
	 */
	UsersDTO getUserForMoreManchesPlayed();

	/**
	 * Metodo per visualizzare il giocatore con la media di punti più alta acquisiti
	 * per manche
	 * 
	 * @return oggetto della classe UsersDTO che si riferisce al giocatore
	 */
	UsersDTO getUserForBestMancheAverage();

	/**
	 * Metodo per visualizzare il giocatore che ha perso più turni in seguito a
	 * errori
	 * 
	 * @return oggetto della classe UsersDTO che si riferisce al giocatore
	 */
	UsersDTO getUserForMostLostTurn();

	/**
	 * Metodo per visualizzare il giocatore che ha subito più "perde" dalla ruota
	 * 
	 * @return oggetto della classe UsersDTO che si riferisce al giocatore
	 */
	UsersDTO getUserForMostLosses();
}

package Database;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import Game.Move;
import Services.User;

public interface DBManagerInterface {

    //i metodi ritornano false se non riescono a fare le query (per esempio a causa di SQLException) o null quando la query ritorna una tabella vuota. negli altri casi ritorna true/riferimento

    /**
     * Questo metodo permette di aggiungere una nuova partita nel database
     *
     * @param id   e' l'identificativo della partita
     * @param time rappresenta la data e ora di creazione della partita
     * @return <code>true</code> se l'inserimento va a buon fine, altrimenti <code>false</code>
     */
    boolean addMatch(String id, LocalDateTime time);

    /**
     * Questo metodo permette di aggiungere un utente all'interno del database
     *
     * @param user    riferimento all'utente
     * @param isAdmin <code>true</code> se l'utente è un amministratore, <code>false</code> se e' un player
     * @return <code>true</code> se l'inserimento e' andato a buon fine, altrimenti <code>false</code>
     */
    boolean addUser(User user, boolean isAdmin);

    /**
     * Questo metodo permette di ottenere le informazioni di un utente conoscendo la sua email
     *
     * @param email email dell'utente
     * @return riferimento all'utente trovato (null se non viene trovato)
     */
    UsersDTO getUserByEmail(String email);

    /**
     * Questo metodo permette di ottenere le informazioni di un utente conoscendo il suo nickname
     *
     * @param nickname nickname dell'utente
     * @return riferimento all'utente trovato (null se non viene trovato)
     */
    UsersDTO getUserByNickname(String nickname);

    /**
     * Questo metodo permette di ottenere le informazioni di un utente conoscendo il suo id
     *
     * @param id id dell'utente
     * @return riferimento all'utente trovato (null se non viene trovato)
     */
    UsersDTO getUserById(String id);

    /**
     * Questo metodo ritorna tutti gli admin registrati alla piattaforma
     *
     * @return La lista di tutti gli admin registrati
     */
    List<UsersDTO> getAllAdmin();

    boolean getAnyAdmin();

    /**
     * Questo metodo permette di eliminare un utente presente nel database
     *
     * @param id id dell'utente
     * @return <code>true</code> se l'inserimento e' andato a buon fine, altrimenti <code>false</code>
     
     */
    boolean deleteUser(String id);

    /**
     * Questo metodo controlla che la mail inserita e la password di un utente siano corrette e che l'utente sia o meno un admin
     *
     * @param email    la mail dell'utente
     * @param password la password dell'utente
     * @param admin    se e' un admin
     * @return <code>0</code> se i dati sono tutti esatti, <code>-1</code> se la mail non esiste o la mail esiste ma la password e' errata e <code>1</code> se email e password sono corrette
     * ma l'utente non e' un admin quando <code>admin = true</code> o viceversa
     */
    int checkLogin(String email, String password, boolean admin);

    /**
     * Questo metodo si occupa di cercare all'interno del database cinque frasi che i tre concorrenti non hanno mai visto sia in veste di osservatori che di concorrenti
     *
     * @param idPlayer1 id del primo concorrente iscritto alla partita
     * @param idPlayer2 id del secondo concorrente iscritto alla partita
     * @param idPlayer3 id del terzo concorrente iscritto alla partita
     * @return la lista delle cinque frasi, con relativo tema, se trovate, altrimenti <code>null</code>
     */
    List<PhrasesDTO> get5Phrases(String idPlayer1, String idPlayer2, String idPlayer3);

    /**
     * Questo metodo permette di aggiungere un insieme di frasi e temi all'interno del database
     *
     * @param phrases lista delle frasi che si vogliono inserire nella base di dati
     * @return <code>true</code> se l'inserimento va a buon fine, altrimenti <code>false</code>
     */
    boolean addPhrases(ArrayList<PhrasesDTO> phrases);

    /**
     * Questo metodo fornisce la lista delle frasi e temi presenti nella base di dati
     *
     * @return lista delle frasi, e relativi temi, oppure in caso di errori <code>null</code>
     */
    List<PhrasesDTO> getAllPhrases();


    /**
     * Questo metodo cancella una partita dal database
     *
     * @param idMatch id del match da cancellare
     * @return <code>true</code> se l'eliminazione va a buon fine, <code>false</code> altrimenti
     */
    boolean deleteMatch(String idMatch);

    /**
     * Questo metodo aggiunge nel database una mossa
     *
     * @param move la mossa da inserire
     * @return <code>true</code> se l'inserimento va a buon fine, altrimenti <code>false</code>
     */
    boolean addMove(Move move);


    /**
     * Questo metodo aggiunge nel database una manche giocata
     *
     * @param manche la manche da inserire
     * @return <code>true</code> se l'inserimento va a buon fine, altrimenti <code>false</code>
     */
    boolean addManche(ManchesDTO manche);

    /**
     * Questo metodo aggiunge nel database il vincitore di una manche giocata
     *
     * @param idPlayer l'id del vincitore
     * @param manche   la manche giocata
     * @param amount   il punteggio ottenuto nella manche
     * @return <code>true</code> se l'inserimento va a buon fine, altrimenti <code>false</code>
     */
    boolean addMancheWinner(String idPlayer, ManchesDTO manche, int amount);

    /**
     * Questo metodo collega un utente ad una manche che ha osservato o giocato
     *
     * @param idMatch   id del match giocato/osservato
     * @param numManche numero della manche
     * @param userId    id dell'utente
     * @param observer  <code>true</code> se l'utente era un osservatore, <code>false</code> altrimenti
     * @return <code>true</code> se l'inserimento va a buon fine, altrimenti <code>false</code>
     */
    boolean addMancheJoiner(String idMatch, int numManche, String userId, boolean observer);

    /**
     * Questo metodo aggiunge al database il vincitore di una partita
     *
     * @param idMatch  id del match vinto
     * @param idPlayer l'id del vincitore
     * @param amount   il punteggio ottenuto
     * @return <code>true</code> se l'inserimento va a buon fine, altrimenti <code>false</code>
     */
    boolean addMatchWinner(String idMatch, String idPlayer, int amount);

    /**
     * Questo metodo modifica una riga della tabella Users
     *
     * @param user L'utente a cui e' stato modificato un valore (Notare che la chiave primaria non viene mai cambiata)
     * @return <code>true</code> se la modifica va a buon fine, altrimenti <code>false</code>
     
     */
    boolean updateUser(UsersDTO user);

    /**
     * Questo metodo permette di individuare la mossa che ha fatto guadagnare la maggiore quantita' di punti
     *
     * @return Un oggetto di tipo {@link UsersDTO}
     */
    MovesDTO getBestMove();

    /**
     * Questo metodo permette di individuare il numero medio di mosse eseguite per indovinare una frase misteriosa
     *
     * @return il numero medio di mosse eseguite per indovinare una frase misteriosa
     */
    int getAverageMovesPerManche();

    /**
     * Permette di individuare il numero di manche vinte da un dato giocatore
     *
     * @param id id del giocatore
     * @return il numero di manche vinte
     */
    int getWonManchesByUser(String id);

    /**
     * Permette di individuare il numero di manche giocate da un dato giocatore
     *
     * @param id id del giocatore
     * @return il numero di manche giocate
     */
    int getManchePlayedByUser(String id);

    /**
     * Permette di individuare il numero di partite giocate da un dato giocatore
     *
     * @param id id del giocatore
     * @return il numero di partite giocate
     */
    int getMatchesPlayedByUser(String id);

    /**
     * Permette di individuare il numero di partite vinte da un dato giocatore
     *
     * @param id id del giocatore
     * @return il numero di partite vinte
     */
    int getWonMatchesByUser(String id);

    /**
     * Permette di individuare il numero di manche osservate da un dato utente
     *
     * @param id id dell'utente
     * @return il numero di manche osservate
     */
    int getObservedManchesByUser(String id);

    /**
     * Permette di individuare il numero di partite osservate da un dato utente
     *
     * @param id id dell'utente
     * @return il numero di partite osservate
     */
    int getObservedMatchesByUser(String id);

    /**
     * Permette di ottenere la quantita' media di punti vinti per partita
     *
     * @param id id del giocatore
     * @return la media di punti vinti per partita
     */
    int getAveragePointsWonByUser(String id);

    /**
     * Permette di ottenere il numero medio di turni passati per manche da un dato giocatore
     *
     * @param id id del giocatore
     * @return il numero medio di turni persi per manche
     */
    int getAveragePassedTurnPerMancheByUser(String id);

    /**
     * Permette di ottenere il numero medio di turni passati per partita da un dato giocatore
     *
     * @param id id del giocatore
     * @return il numero medio di turni persi per partita
     */
    int getAveragePassedTurnPerMatchByUser(String id);

    /**
     * Il numero medio di volte che un giocatore ha perso tutto per manche
     *
     * @param id id del giocatore
     * @return il numero medio di volte che ha perso tutto per manche
     */
    int getAverageLossPerMancheByUser(String id);

    /**
     * Il numero medio di volte che un giocatore ha perso tutto per partita
     *
     * @param id id del giocatore
     * @return il numero medio di volte che ha perso tutto per partita
     */
    int getAverageLossPerMatchByUser(String id);

    /**
     * Permette di ottenere l'utente che ha totalizzato la maggiore quantita' di punti in una manche
     *
     * @return un riferimento di tipo {@link UsersDTO} relativo al giocatore
     */
    UsersDTO getBestUserForManche();

    /**
     * Permette di ottenere l'utente che ha totalizzato la maggiore quantita' di punti in una partita
     *
     * @return un riferimento di tipo {@link UsersDTO} relativo al giocatore
     */
    UsersDTO getBestUserForMatch();

    /**
     * Permette di ottenere l'utente che ha giocato la maggiore quantita' di manche
     *
     * @return un riferimento di tipo {@link UsersDTO} relativo al giocatore
     */
    UsersDTO getUserForMoreManchesPlayed();

    /**
     * Permette di ottenere l'utente con la media piu' alta di punti acquisiti per manche
     *
     * @return un riferimento di tipo {@link UsersDTO} relativo al giocatore
     */
    UsersDTO getUserForBestMancheAverage();

    /**
     * Permette di ottenere l'utente che ha perso piu' volte il turno in seguito ad errori
     *
     * @return un riferimento di tipo {@link UsersDTO} relativo al giocatore
     */
    UsersDTO getUserForMostLostTurn();

    /**
     * Permette di ottenere l'utente che ha perso tutto piu' volte in un giro della ruota
     *
     * @return un riferimento di tipo {@link UsersDTO} relativo al giocatore
     */
    UsersDTO getUserForMostLosses();
}

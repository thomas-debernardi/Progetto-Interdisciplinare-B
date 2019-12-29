package serverRdF;

import java.io.File;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.exceptions.CsvValidationException;

import database.PhrasesDTO;
import game.RemoteMatch;
import server.registration.OTPHelper;
import services.Client;
import services.Login;
import services.MatchData;
import services.User;

public interface Server extends Remote {

	/**
     * Questo metodo controlla se c'e' gia' un utente registrato con la mail fornita
     *
     * @param email la mail dell'utente
     * @return <code>false</code> se la mail esiste gia', <code>true</code> altrimenti
     * @throws RemoteException nel caso in cui non sia possibile comunicare con il server
     */
	public boolean checkEMail(String email) throws RemoteException;

	/**
     * Questo metodo controlla se c'e' gia' un utente registrato con il nickname fornito
     *
     * @param nickname la mail dell'utente
     * @return <code>false</code> se il nickname esiste gia', <code>true</code> altrimenti
     * @throws RemoteException nel caso in cui non sia possibile comunicare con il server
     */
	public boolean checkNickname(String nickname) throws RemoteException;

	/**
     * Questo metodo permette al client di registrare un nuovo account
     *
     * @param form  un oggetto di tipo User contenente tutti i dati necessari
     * @param c     il riferimento al client
     * @param admin <code>true</code> se si prova a registrare un admin, <code>false</code> altrimenti
     * @return un oggetto remoto OTPHelper necessario ad ultimare la registrazione
     * @throws RemoteException nel caso in cui non sia possibile comunicare con il server
     */
	public OTPHelper signUp(User form, Client c, boolean admin) throws RemoteException;

	/**
     * Questo metodo permette al client di effettuare l'accesso alla piattaforma
     *
     * @param form  un oggetto di tipo Login contenente email e password
     * @param c     il riferimento al client
     * @param admin <code>true</code> se si prova ad accedere come admin, <code>false</code> altrimenti
     * @return 0 se il login va a buon fine, -1 se email o password sono sbagliati o 1 se le credenziali sono giuste ma si sta tentando l'accesso dalla piattaforma sbagliata
     * @throws RemoteException nel caso in cui non sia possibile comunicare con il server
     */
	public int signIn(Login form, Client c, boolean admin) throws RemoteException;

	/**
     * Questo metodo permette di visualizzare le informazioni dei match in attesa di giocatori e in corso
     *
     * @param c il riferimento al client
     * @return una lista di MatchData
     * @throws RemoteException nel caso in cui non sia possibile comunicare con il server
     */
	public ArrayList<MatchData> visualizeMatch(Client c) throws RemoteException;

	/**
     * Permette al client di creare un nuovo match e parteciparvi
     *
     * @param c il riferimento al client
     * @return il riferimento all'oggetto remoto RemoteMatch appena creato
     * @throws RemoteException nel caso in cui non sia possibile comunicare con il server
     */
	public RemoteMatch createMatch(Client c) throws RemoteException;

	/**
     * Questo metodo permette al client di partecipare ad una partita esistente come giocatore
     *
     * @param c il riferimento al client
     * @param idMatch l'id del match al quale si vuole partecipare
     * @return il riferimento all'oggetto remoto RemoteMatch appena creato
     * @throws RemoteException nel caso in cui non sia possibile comunicare con il server
     */
	public RemoteMatch joinMatch(Client c, String idMatch) throws RemoteException;

	/**
     * Questo metodo permette al client di partecipare ad una partita esistente come osservatore
     *
     * @param c il riferimento al client
     * @param idMatch l'id del match al quale si vuole partecipare
     * @return il riferimento all'oggetto remoto RemoteMatch appena creato
     * @throws RemoteException nel caso in cui non sia possibile comunicare con il server
     */
	public RemoteMatch observeMatch(Client c, String idMatch) throws RemoteException;

	/**
     * Questo metodo permette all'admin di aggiungere nuove frasi al database per mezzo di un file CSV
     *
     * @param file il file CSV dal quale recuperare le frasi
     * @return <code>true</code> se l'inserimento va a buon fine, altrimenti <code>false</code>
     * @throws RemoteException nel caso in cui non sia possibile comunicare con il server
     */
	public boolean addPhrases(File file) throws RemoteException, CsvValidationException;
	
	/**
	 * Questo metodo permette di inserire una frase
	 *  
	 * @param theme temi delle frasi
	 * @param phrase frasi che si vogliono inserire
	 * @return <code>true</code> se l'inserimento va a buon fine <code>false</code>
	 * @throws RemoteException nel caso in cui non sia possibile comunicare con il server
	 */
	public boolean addPhrase(String[] theme, String[] phrase) throws RemoteException;

	/**
     * Questo metodo permette ad un utente di cambiare la propria password
     *
     * @param password la nuova password da sostituire al vecchio
     * @param c il riferimento al client
     * @return <code>true</code> se la modifica e' andata a buon fine, <code>false</code> altrimenti
     * @throws RemoteException nel caso in cui non sia possibile comunicare con il server
     */
	public boolean changePassword(String password, Client c) throws RemoteException;

	/**
     * Permette di ottenere i nomi dei concorrenti che possiedono dei record della piattaforma
     *
     * @return una stringa contenente: i nickname dei giocatori che: detengono il punteggio piu' alto per manche, detengono il punteggio piu' alto per partita,
     * che ha giocato piu' manche in assoluto, con la media di punti acquisiti per manche piu' alta, che ha ceduto il turno piu' volte a causa di errori, che ha perso tutto il maggior numero di volte
     * @throws RemoteException nel caso in cui non sia possibile comunicare con il server
     */
	public String checkRecordPlayer() throws RemoteException;

	/**
     * Metodo utilizzato per ottenere le statistiche riguardanti il singolo utente
     *
     * @param nickname il nickname dell'utente
     * @return una stringa contenente: numero manche giocate, numero partite giocate, numero manche osservate, numero match osservati,
     * numero manche vinte, numero match vinti, punteggio medio vinto per partita, numero medio di volte che ha dovuto cedere il turno di gioco per manche, numero medio di volte che ha dovuto cedere il turno di gioco per match,
     * numero medio di volte che ha perso tutto per manche, numero medio di volte che ha perso tutto per match
     * @throws RemoteException nel caso in cui non sia possibile comunicare con il server
     */
	public String checkPerPlayer(String nickname) throws RemoteException;

	/**
     * Ritorna la mossa che ha fatto ottenere il maggior numero di punti
     *
     * @return Una stringa contenente: il nickname del giocatore, la consonante chiamata e la frase associata
     * @throws RemoteException nel caso in cui non sia possibile comunicare con il server
     */
	public String bestMove() throws RemoteException;

	/**
     * Metodo che permette di ottenere il numero medio di mosse necessarie a indovinare una frase misteriosa in una manche
     *
     * @return il numero medio di mosse
     * @throws RemoteException nel caso in cui non sia possibile comunicare con il server
     */
	public int averageManches() throws RemoteException;

	/**
     * Permette il reset della password. Viene inviata una mail all'inidirizzo indicato con la nuova password
     *
     * @param c riferimento al client
     * @param mail l'indirizzo email dell'account da resettare
     * @return <code>true</code> se il reset avviene con successo, <code>false</code> se l'indirizzo email non esiste nel database
     * @throws RemoteException nel caso in cui non sia possibile comunicare con il server
     */
	public boolean resetPassword(Client c, String mail) throws RemoteException;
	
	/**
	 * Questo metodo permette di controllare ( al momento del cambio password) se la password che si sta
	 * inserendo è la stessa password che si sta usando attualmente
	 *  
	 * @param nickname nickname del giocatore
	 * @param password password del giocatore
	 * @param c riferimento al client
	 * @return <code>false</code> se la password è già stata usata, altrimenti <code>true</code>
	 * @throws RemoteException nel caso in cui non sia possibile comunicare con il server
	 */
	public boolean checkPassword(String nickname, String password, Client c) throws RemoteException;

	/**
     * Questo metodo permette ad un utente di cambiare il proprio nickname
     *
     * @param nickname il nuovo cognome da sostituire al vecchio
     * @param c il riferimento al client
     * @return <code>true</code> se la modifica e' andata a buon fine, <code>false</code> se la modifica non e' stata possibile, ad esempio a causa dell'utilizzo di un nickname gia' esistente
     * @throws RemoteException nel caso in cui non sia possibile comunicare con il server
     */
	public boolean changeNickname(String nickname, Client c) throws RemoteException;

	/**
     * Questo metodo permette ad un utente di cambiare il proprio cognome
     *
     * @param surname il nuovo cognome da sostituire al vecchio
     * @param c il riferimento al client
     * @return <code>true</code> se la modifica e' andata a buon fine, <code>false</code> altrimenti
     * @throws RemoteException nel caso in cui non sia possibile comunicare con il server
     */
    public boolean changeSurname(String surname, Client c) throws RemoteException;

    /**
     * Questo metodo permette ad un utente di cambiare il proprio nome
     *
     * @param name il nuovo nome da sostituire al vecchio
     * @param c il riferimento al client
     * @return <code>true</code> se la modifica e' andata a buon fine, <code>false</code> altrimenti
     * @throws RemoteException nel caso in cui non sia possibile comunicare con il server
     */
    public boolean changeName(String name, Client c) throws RemoteException;
    
    /**
     * Questo metodo permette di visualizzare le frasi
     * 
     * @return la lista delle frasi
     * @throws RemoteException nel caso in cui non sia possibile comunicare con il server
     */
    public List<PhrasesDTO> getAllPhrases() throws RemoteException;
    
    /**
     * Questo metodo permette di cancellare tutte le frasi
     * 
     * @return <code>true</code> se la cancellazione delle frasi è andata a buon fine, altrimenti <code>false</code>
     * @throws RemoteException nel caso in cui non sia possibile comunicare con il server
     */
    public boolean deleteAllPhrases() throws RemoteException;
    
    /**
     * Questo metodo permette di eliminare una determinata frase
     * 
     * @param position posizione della frase da eliminare 
     * @return <code>true</code> se la cancellazione delle frasi è andata a buon fine, altrimenti <code>false</code>
     * @throws RemoteException nel caso in cui non sia possibile comunicare con il server
     */
    public boolean deletePhrase(int position) throws RemoteException;

}

package services;

import java.rmi.Remote;
import java.rmi.RemoteException;

import gui.Game;
import gui.OTPRegistration;

public interface ClientInterface extends Remote {

	/**
	 * Questo metodo permette di visualizzare il nickname del del giocatore
	 * @return nickname giocatore
	 * @throws RemoteException errore di connessione con il server
	 */
	public String getNickname() throws RemoteException;

	/**
	 * Questo metodo permette di visualizzare l'identificativo del giocatore
	 * 
	 * @return id giocatore
	 * @throws RemoteException errore di connessione con il server
	 */
	public String getId() throws RemoteException;

	/**
	 * Questo metodo permette di impostare nickname del giocatore  
	 * 
	 * @param nickname nome del giocatore
	 * @throws RemoteException errore di connessione con il server
	 */
	public void setNickname(String nickname) throws RemoteException;

	/**
	 * Questo metodo permette di impostare l'id del giocatore
	 * 
	 * @param id identificativo del giocatore
	 * @throws RemoteException errore di connessione con il server
	 */
	public void setId(String id) throws RemoteException;

	/**
	 * Questo metodo permette di impostare il nome del giocatore
	 * 
	 * @param name nome del giocatore
	 * @throws RemoteException errore di connessione  con il server
	 */
	public void setName(String name) throws RemoteException;

	/**
	 * Questo metodo permette di impostare il cognome del giocatore
	 * 
	 * @param surname cognome giocatore
	 * @throws RemoteException errore di connessione con il server
	 */
	public void setSurname(String surname) throws RemoteException;

	/**
	 * Questo metodo permette di impostare la mail del giocatore
	 * 
	 * @param email mail giocatore
	 * @throws RemoteException errore connessione con il server
	 */
	public void setEmail(String email) throws RemoteException;

	/**
	 * Questo metodo permette di inizializzare il gioco
	 * 
	 * @param e gioco che eseguirà l'inizializzazione
	 * @throws RemoteException 
	 */
	public void setGame(Game e) throws RemoteException;

	/**
	 * Questo metodo permette di inizializzare otp
	 * 
	 * @param otp otp che eseguirà l'inizializzazione
	 * @throws RemoteException errore connessione con il server
	 */
	public void setOtpPane(OTPRegistration otp) throws RemoteException;

	/**
	 * Questo metodo restituisce il nome del giocatore
	 * 
	 * @return nome del giocatore
	 * @throws RemoteException errore connessione con il server
	 */
	public String getName() throws RemoteException;

	/**
	 * Questo metodo restituisce il cognome del giocatore
	 * 
	 * @return cognome del giocatore
	 * @throws RemoteException errore connessione con il server
	 */
	public String getSurname() throws RemoteException;

	/**
	 * Questo metodo restituisce la mail del giocatore
	 * 
	 * @return mail del giocatore
	 * @throws RemoteException errore connessione con il server
	 */
	public String getEmail() throws RemoteException;

	/**
     * Notifica al client che c'e' stato un problema di connessione al server da parte del client o al database da parte del server
     *
     * @throws RemoteException In caso di errore di comunicazione con il server
     */
	public void notifyServerError() throws RemoteException;

	/**
     * Notifica se e' stato possibile concludere la registrazione
     *
     * @param success <code>true</code> se la registrazione e' avvenuta con successo, <code>false</code> altrimenti
     * @throws RemoteException In caso di errore di comunicazione con il server
     */
	public void notifyRegistrationResult(boolean success) throws RemoteException;

	/**
     * Notifica che il codice inserito per ultimare la registrazione e' errato
     *
     * @throws RemoteException In caso di errore di comunicazione con il server
     */
	public void notifyWrongOTP() throws RemoteException;

	/**
     * Notifica che non è stato possibile accedere alla partita poiche' e' piena
     *
     * @throws RemoteException In caso di errore di comunicazione con il server
     */
	public void notifyTooManyPlayers() throws RemoteException;

	/**
     * Notifica che uno degli altri giocatori ha abbandonato la partita
     *
     * @param nickname il nickname di chi ha abbandonato
     * @throws RemoteException In caso di errore di comunicazione con il server
     */
	public void notifyLeaver(String nickname) throws RemoteException;

	/**
     * Notifica che la partita e' stata annullata
     *
     * @param reason il motivo per cui la partita e' stata annullata
     * @throws RemoteException In caso di errore di comunicazione con il server
     */
	public void notifyMatchAbort(String reason) throws RemoteException;

	/**
     * Notifica l'inizio della partita
     *
     * @throws RemoteException In caso di errore di comunicazione con il server
     */
	public void notifyMatchStart() throws RemoteException;

	/**
     * Notifica che il client è colui che ha vinto la manche
     *
     * @throws RemoteException In caso di errore di comunicazione con il server
     */
	public void notifyMancheVictory() throws RemoteException;

	/**
     * Notifica il client che <code>winner</code> ha vinto la manche
     *
     * @param winner il nickname del vincitore della manche
     * @throws RemoteException In caso di errore di comunicazione con il server
     */
	public void notifyMancheResult(String winner) throws RemoteException;

	/**
     * Notifica al client l'inizio di una nuova manche
     *
     * @param numManche il numero dell manche appena iniziata
     * @throws RemoteException In caso di errore di comunicazione con il server
     */
	public void notifyNewManche(int numManche) throws RemoteException;

	/**
     * Questo metodo si occupa di aggiornare il tabellone con il nuovo tema e la nuova frase
     *
     * @param theme  il tema della nuova frase
     * @param phrase la nuova frase da indovinare
     * @throws RemoteException In caso di errore di comunicazione con il server
     */
	public void setNewPhrase(String theme, String phrase) throws RemoteException;

	/**
     * Notifica ai clients il nome del giocatore attivo
     *
     * @param player Il nickname del giocatore attivo
     * @throws RemoteException In caso di errore di comunicazione con il server
     */
	public void notifyNewTurn(String player) throws RemoteException;

	/**
     * Attiva i bottoni per poter effettuare le mosse
     *
     * @throws RemoteException In caso di errore di comunicazione con il server
     */
	public void notifyYourTurn() throws RemoteException;

	/**
     * Questo metodo notifica la conclusione del match e il nome del vincitore
     *
     * @param winner il nickname del vincitore
     * @throws RemoteException In caso di errore di comunicazione con il server
     */
	public void notifyEndMatch(String winner) throws RemoteException;

	 /**
     * Notifica al client che ha vinto la partita
     *
     * @throws RemoteException In caso di errore di comunicazione con il server
     */
	public void notifyMatchWin() throws RemoteException;

	/**
     * Metodo per far visualizzare un giocatore e i suoi punteggi
     *
     * @param position      e' un numero compreso tra <code>0</code> e <code>2</code> (<code>0</code> e' il giocatore piu' a sinistra)
     * @param name          il nickname del giocatore
     * @param partialPoints il punteggio parziale
     * @param points        il punteggio totale
     * @param numJolly      //todo
     * @throws RemoteException In caso di errore di comunicazione con il server
     */
	public void notifyPlayerStats(int position, String name, int partialPoints, int points, int numJolly)
			throws RemoteException;

	/**
     * Metodo per visualizzare solo le lettere che sono state chiamate. Per funzionare e' necessario aver richiamato precedentemente il metodo
     * {@link #setNewPhrase(String, String)} che si occupa di generare il tabellone adatto alla farse
     *
     * @param phrase un array di booleani che indica, senza considerare gli spazi, se un carattere nella frase sia stato rivelato o meno.
     *               Nel primo caso avra' valore <code>true</code>, nel secondo <code>false</code>.
     * @throws RemoteException In caso di errore di comunicazione con il server
     */
	public void updatePhrase(boolean[] phrase) throws RemoteException;

	/**
     * Una versione piu' semplice di {@link #updatePhrase(boolean[])} per aggiornare il tabellone senza dover utilizzare
     * l'array dell'intera frase e in piu' notifica quanti punti ha fatto guadagnare
     *
     * @param letter la lettera chiamata
     * @throws RemoteException In caso di errore di comunicazione con il server
     */
	public void updatePhrase(String letter) throws RemoteException;

	/**
     * Notifica al client che il tempo per concludere la mossa e' finito
     *
     * @throws RemoteException In caso di errore di comunicazione con il server
     */
	public void notifyTimeOut() throws RemoteException;

	/**
     * Questo metodo visualizza il risultato ottenuto da un giro di ruota
     *
     * @param risultato il risultato ottenuto
     * @throws RemoteException In caso di errore di comunicazione con il server
     */
	public void notifyWheelResult(String risultato) throws RemoteException;

	/**
     * Chiede al giocatore che ha effettuato un errore se vuole usare il jolly
     *
     * @throws RemoteException In caso di errore di comunicazione con il server
     */
	public void askForJolly() throws RemoteException;

	/**
     * Notifica che il giocatore attivo ha commesso un errore, come far scadere il timer o eseguire una mossa illegale
     *
     * @param name il nickname del giocatore
     * @throws RemoteException In caso di errore di comunicazione con il server
     */
	public void notifyPlayerError(String name) throws RemoteException;

	/**
     * Notifica che un giocatore intende provare a dare una soluzione
     *
     * @param name il nickname del giocatore
     * @throws RemoteException In caso di errore di comunicazione con il server
     */
	public void notifyTryForSolution(String name) throws RemoteException;

	/**
     * Notifica che un giocatore intende chiamare una vocale
     *
     * @param name il nickname del giocatore
     * @throws RemoteException In caso di errore di comunicazione con il server
     */
	public void notifyTryVocal(String name) throws RemoteException;

	/**
     * Notifica che un giocatore ha utilizzato un jolly
     *
     * @param name il nickname del giocatore
     * @throws RemoteException In caso di errore di comunicazione con il server
     */
	public void notifyJollyUsed(String name) throws RemoteException;

	/**
     * Notifica che un giocatore ha chiamato una lettera
     *
     * @param name il nickname del giocatore
     * @param letter la lettera chiamata
     * @throws RemoteException In caso di errore di comunicazione con il server
     */
	public void notifyLetterCall(String name, String letter) throws RemoteException;

	/**
     * Notifica che tutte le consonanti sono state chiamate
     *
     * @throws RemoteException In caso di errore di comunicazione con il server
     */
	public void notifyNoMoreConsonant() throws RemoteException;

	/**
     * Aggiorna il timer
     * 
     * @param num i secondi rimanenti
     * @throws RemoteException In caso di errore di comunicazione con il server
     */
	public void updateTimer(int num) throws RemoteException;

}

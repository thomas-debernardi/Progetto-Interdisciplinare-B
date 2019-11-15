package Game;


import java.rmi.Remote;
import java.rmi.RemoteException;

import Services.Client;

/**
 * Interfaccia remota utilizzata dai client per poter interagire, sia come giocatore che come osservatore, con una specifica partita
 */
public interface RemoteMatch extends Remote {

    /**
     * Metodo utilizzato dal client per girarare la ruota
     *
     * @return Il risultato ottenuto o <code>0</code> se non e' possibile chiamare una consonante (ad esempio ottenendo "PASSA")
     * @throws RemoteException nel caso in cui non sia possibile comunicare con il server
     */
    public int wheelSpin() throws RemoteException;

    /**
     * Metodo utilizzato dal client per inviare una consonante
     *
     * @param letter la consonante scelta
     * @param amount la quantita' di punti che spettano per ogni lettera rivelata
     * @throws RemoteException nel caso in cui non sia possibile comunicare con il server
     */
    public void giveConsonant(String letter, int amount) throws RemoteException;

    /**
     * Metodo utilizzato dal client per inviare una vocale
     *
     * @param letter la vocale scelta
     * @throws RemoteException nel caso in cui non sia possibile comunicare con il server
     */
    public void giveVocal(String letter) throws RemoteException;

    /**
     * Metodo per utilizzare un jolly
     *
     * @throws RemoteException nel caso in cui non sia possibile comunicare con il server
     */
    public void jolly() throws RemoteException;

    /**
     * Permette al client di dichiarare l'intenzione di dare la soluzione e far partire il timer da 10 secondi.
     *
     * @throws RemoteException nel caso in cui non sia possibile comunicare con il server
     */
    public void askForSolution() throws RemoteException;

    /**
     * Permette al giocatore di dare la soluzione
     *
     * @param solution la soluzione data
     * @throws RemoteException nel caso in cui non sia possibile comunicare con il server
     */
    public void giveSolution(String solution) throws RemoteException;

    /**
     * Permette al giocatore di abbandonare la partita, facendo terminare la partita
     *
     * @param c il riferimento al client
     * @throws RemoteException nel caso in cui non sia possibile comunicare con il server
     */
    public void leaveMatchAsPlayer(Client c) throws RemoteException;

    /**
     * Permette all'osservatore di abbandonare la parita
     *
     * @param c il riferimento al client
     * @throws RemoteException nel caso in cui non sia possibile comunicare con il server
     */
    public void leaveMatchAsObserver(Client c) throws RemoteException;

    /**
     * Viene richiamato dagli utenti che entrano in una partita per ottenere tutte le informazioni necessarie per aggiornare la schermata di gioco
     *
     * @param c il riferimento al client
     * @throws RemoteException nel caso in cui non sia possibile comunicare con il server
     */
    public void askNotify(Client c) throws RemoteException;

    /**
     * Permette al giocatore di comunicare l'intenzione di chiamare una vocale. Se non ha abbastanza punti verra' indicato l'errore
     *
     * @throws RemoteException nel caso in cui non sia possibile comunicare con il server
     */
    public void askForVocal() throws RemoteException;

    public void tryForStartMatch() throws RemoteException;
}

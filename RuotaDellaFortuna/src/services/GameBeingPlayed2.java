package services;

import game.RemoteMatch;
import serverRdF.ServerInterface;

public class GameBeingPlayed2 {
	String player1;
	String player2;
	String player3;
	private boolean join;
	private boolean observe;
	private ServerInterface server;
	private static ClientInterface client;
	private MatchData matchData;
	private static RemoteMatch match;
	public static boolean player;
	
	public GameBeingPlayed2() {
		
	}
	

	public GameBeingPlayed2(ServerInterface server, ClientInterface client, MatchData matchData) {
		this.server = server;
		this.client = client;
		this.matchData = matchData;

		player1 = matchData.getPlayer1();
		player2 = matchData.getPlayer2();
		player3 = matchData.getPlayer3();
	}
	

	/**
	 * Questo metodo visualizza il server
	 * @return server server
	 */
	public ServerInterface getServer() {
		return server;
	}

	/**
	 * Questo metodo permette di inizializzare il server
	 * @param server server utilizzato per l'inizializzazione
	 */
	public void setServer(ServerInterface server) {
		this.server = server;
	}

	/**
	 * Questo metodo visualizza il client
	 * @return oggetto client
	 */
	public static ClientInterface getClient() {
		return client;
	}

	/**
	 * Questo metodo serve a inizializzare il client
	 * @param client client utilizzato per l'inizializzazione
	 */
	public static void setClient(ClientInterface client) {
		GameBeingPlayed2.client = client;
	}

	/**
	 * Questo metodo visualizza i dati del match
	 * @return oggetto matchData
	 */
	public MatchData getMatchData() {
		return matchData;
	}

	/**
	 * Questo metodo inizializza i dati del match
	 * @param matchData oggetto MatchData
	 */
	public void setMatchData(MatchData matchData) {
		this.matchData = matchData;
	}

	/**
	 * Questo metodo visualizza il primo giocatore
	 * @return primo giocatore
	 */
	public String getPlayer1() {
		return player1;
	}

	/**
	 * Questo metodo inizializza il primo giocatore
	 * @param player1 nome giocatore
	 */
	public void setPlayer1(String player1) {
		this.player1 = player1;
	}


	/**
	 * Questo metodo visualizza il secondo giocatore
	 * @return secondo giocatore
	 */
	public String getPlayer2() {
		return player2;
	}

	/**
	 * Questo metodo inizializza il secondo giocatore
	 * @param player1 nome giocatore
	 */
	public void setPlayer2(String player2) {
		this.player2 = player2;
	}


	/**
	 * Questo metodo visualizza il terzo giocatore
	 * @return terzo giocatore
	 */
	public String getPlayer3() {
		return player3;
	}

	/**
	 * Questo metodo inizializza il terzo giocatore
	 * @param player1 nome giocatore
	 */
	public void setPlayer3(String player3) {
		this.player3 = player3;
	}


	/**
	 * Questo metodo notifica i join ad una partita
	 * @return <code>true</code> se è stata eseguita l'unione, altrimenti <code>false</code>
	 */
	public boolean isJoin() {
		return join;
	}
	/**
	 * Questo metodo permette di impostare i joiners
	 * @param join <code>true</code> se si è unito, altrimenti <code>false</code>
	 */
	public void setJoin(boolean join) {
		this.join = join;
	}
	
	/**
	 * Questo metodo notifica se l'utente è un osservatore
	 * @return <code>true</code> se è osservatore, altrimenti <code>false<code>
	 */
	public boolean isObserve() {
		return observe;
	}
	/**
	 * Questo metodo permette di impostare gli observer
	 * @param observe <code>true</code> se l'utente è osservatore, altrimenti <code>false</code>
	 */
	public void setObserve(boolean observe) {
		this.observe = observe;
	}
	
	

}

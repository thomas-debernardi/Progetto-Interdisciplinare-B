package Services;

import Game.RemoteMatch;
import Server.Server;

public class GameBeingPlayed2 {
	String player1;
	String player2;
	String player3;
	private boolean join;
	private boolean observe;
	private Server server;
	private static Client client;
	private MatchData matchData;
	private static RemoteMatch match;
	public static boolean player;
	
	public GameBeingPlayed2() {
		
	}
	

	public GameBeingPlayed2(Server server, Client client, MatchData matchData) {
		this.server = server;
		this.client = client;
		this.matchData = matchData;

		player1 = matchData.getPlayer1();
		player2 = matchData.getPlayer2();
		player3 = matchData.getPlayer3();
	}
	

	public Server getServer() {
		return server;
	}


	public void setServer(Server server) {
		this.server = server;
	}


	public static Client getClient() {
		return client;
	}


	public static void setClient(Client client) {
		GameBeingPlayed2.client = client;
	}


	public MatchData getMatchData() {
		return matchData;
	}


	public void setMatchData(MatchData matchData) {
		this.matchData = matchData;
	}


	public String getPlayer1() {
		return player1;
	}


	public void setPlayer1(String player1) {
		this.player1 = player1;
	}


	public String getPlayer2() {
		return player2;
	}


	public void setPlayer2(String player2) {
		this.player2 = player2;
	}


	public String getPlayer3() {
		return player3;
	}


	public void setPlayer3(String player3) {
		this.player3 = player3;
	}


	public boolean isJoin() {
		return join;
	}
	public void setJoin(boolean join) {
		this.join = join;
	}
	public boolean isObserve() {
		return observe;
	}
	public void setObserve(boolean observe) {
		this.observe = observe;
	}
	
	

}

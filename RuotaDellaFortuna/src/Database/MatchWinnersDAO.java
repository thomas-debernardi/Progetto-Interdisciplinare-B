package Database;

import java.sql.SQLException;

public interface MatchWinnersDAO {
	String matchWinnersTable = "matchwinners";
	String matchWinnersIdMatchAttribute = "idmatch";
	String matchWinnersidPlayerAttribute = "idplayer";
	String matchWinnersAmountAttribute = "amount";

	boolean addMatchWinner(String idMatch, String idPlayer, int amount) throws SQLException;

	int getWonMatchesByUser(String id) throws SQLException;

	int getTotalPointsByUser(String id) throws SQLException;
}

package Database;

import java.sql.SQLException;

public interface MatchesDAO {
	String MatchTable = "matches";
	String MatchIdAttribute = "id";
	String MatchDateAttribute = "date";
	String MatchTimeAttribute = "time";

	boolean addMatch(MatchesDTO matchesDTO) throws SQLException;

	boolean deleteMatch(String idMatch) throws SQLException;
}
package Database;

import java.sql.SQLException;


public interface MancheJoinersDAO {
    String mancheJoinersTable = "Manchejoiners";
    String mancheJoinersIdMatchAttribute = "id";
    String mancheJoinersNumMancheAttribute = "number";
    String mancheJoinersIdPlayerAttribute = "idplayer";
    String mancheJoinersObserverAttribute = "observer";

    boolean addMancheJoiner(String idMatch, int numManche, String idPlayer, boolean observer) throws SQLException;


    int getManchePlayedByUser(String id) throws SQLException;


    int getMatchesPlayedByUser(String id) throws SQLException;


    int getObservedManches(String id) throws SQLException;


    int getObservedMatches(String id) throws SQLException;
}

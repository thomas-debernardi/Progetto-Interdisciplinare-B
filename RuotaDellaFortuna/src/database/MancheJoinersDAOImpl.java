package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MancheJoinersDAOImpl implements MancheJoinersDAO {
    private Connection con;
    MancheJoinersDAOImpl(Connection c){con = c;}

    public boolean addMancheJoiner(String idMatch, int numManche, String idPlayer, boolean observer) throws SQLException {
        String queryAdd = "INSERT INTO "+mancheJoinersTable+" ("+mancheJoinersIdMatchAttribute+","+mancheJoinersNumMancheAttribute+","+mancheJoinersIdPlayerAttribute+","+mancheJoinersObserverAttribute+") " +
                "VALUES ('"+idMatch+"',"+numManche+",'"+idPlayer+"',"+observer+");";
        Statement stmt = con.createStatement();
        return stmt.executeUpdate(queryAdd) > 0;
    }

    public int getManchePlayedByUser(String id) throws SQLException {
        String queryGet = "SELECT COUNT(*) AS count FROM "+mancheJoinersTable+" WHERE "+mancheJoinersIdPlayerAttribute+" = '"+id+"' AND "+mancheJoinersObserverAttribute+" = false;";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(queryGet);
        if(rs.next()) {
            return rs.getInt("count");
        }else
            return 0;
    }

    public int getMatchesPlayedByUser(String id) throws SQLException{
        String queryGet = "SELECT COUNT(*) AS count FROM (SELECT id FROM "+mancheJoinersTable+" WHERE "+mancheJoinersIdPlayerAttribute+" = '"+id+"' AND "+mancheJoinersObserverAttribute+" = false GROUP BY "+mancheJoinersIdMatchAttribute+") as matches;";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(queryGet);
        if(rs.next()) {
            return rs.getInt("count");
        }else
            return 0;
    }

    public int getObservedManches(String id) throws SQLException {
        String queryGet = "SELECT COUNT(*) AS count FROM "+mancheJoinersTable+" WHERE "+mancheJoinersIdPlayerAttribute+" = '"+id+"' AND "+mancheJoinersObserverAttribute+" = true;";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(queryGet);
        if(rs.next()) {
            return rs.getInt("count");
        }else
            return 0;
    }

    public int getObservedMatches(String id) throws SQLException {
        String queryGet = "SELECT COUNT(*) AS count FROM (SELECT id FROM "+mancheJoinersTable+" WHERE "+mancheJoinersIdPlayerAttribute+" = '"+id+"' AND "+mancheJoinersObserverAttribute+" = true GROUP BY "+mancheJoinersIdMatchAttribute+") as matches;";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(queryGet);
        if(rs.next()) {
            return rs.getInt("count");
        }else
            return 0;
    }
}

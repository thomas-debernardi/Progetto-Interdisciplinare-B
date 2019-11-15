package Database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Implementazione dell'interfaccia {@link MatchWinnersDAO}
 */
public class MatchWinnersDAOImpl implements MatchWinnersDAO {
    private Connection con;
    MatchWinnersDAOImpl(Connection c){con = c;}

    @Override
    public boolean addMatchWinner(String idMatch, String idPlayer, int amount) throws SQLException {
        String queryAdd = "INSERT INTO "+matchWinnersTable+"("+matchWinnersIdMatchAttribute+","+matchWinnersidPlayerAttribute+","+matchWinnersAmountAttribute+") " +
                "VALUES ('"+idMatch+"','"+idPlayer+"',"+amount+");";
        Statement stmt = con.createStatement();
        return stmt.executeUpdate(queryAdd) > 0;
    }

    @Override
    public int getWonMatchesByUser(String id) throws SQLException{
        String queryGet = "SELECT COUNT(*) AS count FROM "+matchWinnersTable+" WHERE "+matchWinnersidPlayerAttribute+" = '"+id+"';";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(queryGet);
        if(rs.next()) {
            return rs.getInt("count");
        }else
            return 0;
    }

    @Override
    public int getTotalPointsByUser(String id) throws SQLException {
        String queryTot = "SELECT * FROM "+matchWinnersTable+" WHERE "+matchWinnersidPlayerAttribute+ " = '"+id+"';";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(queryTot);
        int result = 0;
        while(rs.next()){
            result += rs.getInt(matchWinnersAmountAttribute);
        }
        return result;
    }
}

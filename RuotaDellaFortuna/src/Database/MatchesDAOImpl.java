package Database;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Implementazione dell'interfaccia {@link MatchesDAO}
 */
public class MatchesDAOImpl implements MatchesDAO {
    private Connection con;
    MatchesDAOImpl(Connection c){
        this.con=c;
    }

    @Override
    public boolean addMatch(MatchesDTO matchesDTO) throws SQLException {
        String queryAdd = "INSERT INTO "+MatchTable+"("+MatchIdAttribute+","+MatchDateAttribute+","+MatchTimeAttribute+") " +
                "VALUES ('"+matchesDTO.getId()+"','"+matchesDTO.getDate()+"','"+matchesDTO.getTime()+"');";
        Statement stmt = con.createStatement();
        return stmt.executeUpdate(queryAdd) > 0;
    }

    @Override
    public boolean deleteMatch(String idMatch) throws SQLException{
        String queryAdd = "DELETE FROM " + MatchTable +
                        " WHERE " + MatchIdAttribute + " = '" + idMatch+"'";
        Statement stmt = con.createStatement();
        return stmt.executeUpdate(queryAdd) > 0;
    }
}

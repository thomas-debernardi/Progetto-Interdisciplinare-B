package Database;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Implementazione dell'interfaccia {@link ManchesDAO}
 */
public class ManchesDAOImpl implements ManchesDAO {
    private Connection con;
    ManchesDAOImpl(Connection c){
        con = c;
    }

    @Override
    public boolean addManche(ManchesDTO manche) throws SQLException {
        String queryAdd = "INSERT INTO "+ManchesTable+" ("+ManchesNumberAttribute+","+ManchesIdAttribute+","+ManchesPhraseAttribute+") " +
                "VALUES ("+manche.getNumber()+",'"+manche.getMatch().getId()+"','"+manche.getPhrase().getPhrase()+"');";
        Statement stmt = con.createStatement();
        return stmt.executeUpdate(queryAdd) > 0;
    }
}

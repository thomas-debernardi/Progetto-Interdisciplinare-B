package database;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class ManchesDAOImpl implements ManchesDAO {
    private Connection con;
    ManchesDAOImpl(Connection c){
        con = c;
    }

    public boolean addManche(ManchesDTO manche) throws SQLException {
        String queryAdd = "INSERT INTO "+ManchesTable+" ("+ManchesNumberAttribute+","+ManchesIdAttribute+","+ManchesPhraseAttribute+") " +
                "VALUES ("+manche.getNumber()+",'"+manche.getMatch().getId()+"','"+manche.getPhrase().getId()+"');";
        Statement stmt = con.createStatement();
        return stmt.executeUpdate(queryAdd) > 0;
    }
}

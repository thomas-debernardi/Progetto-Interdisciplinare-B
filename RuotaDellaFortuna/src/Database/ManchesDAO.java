package Database;
import java.sql.SQLException;


public interface ManchesDAO {
    String ManchesTable = "manches";
    String ManchesNumberAttribute = "number";
    String ManchesIdAttribute = "id";
    String ManchesPhraseAttribute = "phrase";

    boolean addManche(ManchesDTO manche) throws SQLException;
}

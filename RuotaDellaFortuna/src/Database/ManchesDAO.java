package Database;
import java.sql.SQLException;

/**
 * Interfaccia del Data Access Object che si occupa degli accessi al database relativi alle manche
 */
public interface ManchesDAO {
    String ManchesTable = "manches";
    String ManchesNumberAttribute = "number";
    String ManchesIdAttribute = "id";
    String ManchesPhraseAttribute = "phrase";

    /**
     * Questo metodo aggiunge nel database una manche
     *
     * @param manche la manche da inserire
     * @return <code>true</code> se l'inserimento va a buon fine, altrimenti <code>false</code>
     * @throws SQLException in caso di errore di connessione al database
     */
    boolean addManche(ManchesDTO manche) throws SQLException;
}

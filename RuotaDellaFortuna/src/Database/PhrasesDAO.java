package Database;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Interfaccia del Data Access Object che si occupa degli accessi al database relativi alle frasi misteriose
 */
public interface PhrasesDAO {
    String PhraseTable = "phrases";
    String PhrasePhraseAttribute = "phrase";
    String PhraseThemeAttribute = "theme";

    /**
     * Questo metodo si occupa di cercare all'interno del database cinque frasi che i tre concorrenti non hanno mai
     * visto sia in veste di osservatori che di concorrenti
     * @param idPlayer1 id del primo concorrente iscritto alla partita
     * @param idPlayer2 id del secondo concorrente iscritto alla partita
     * @param idPlayer3 id del terzo concorrente iscritto alla partita
     * @return la lista delle cinque frasi, con relativo tema, se trovate, altrimenti null
     * @throws SQLException in caso di errore di connessione al database
     */
    List<PhrasesDTO> get5Phrases(String idPlayer1, String idPlayer2, String idPlayer3) throws SQLException;

    /**
     * Questo metodo permette di aggiungere un insieme di frasi e temi all'interno del database
     * @param phrases lista delle frasi, con relativo tema, da aggiungere nel database
     * @return <code>true</code> se la query e' andata a buon fine, <code>false</code> altrimenti
     * @throws SQLException in caso di errore di connessione al database
     */
    boolean addPhrases(ArrayList<PhrasesDTO> phrases) throws SQLException;

    /**
     * Questo metodo fornisce la lista delle frasi e temi presenti nella base di dati
     * @return la lista di tutte le frasi, con relativo tema, presenti nel database. In caso di problemi si restituisce null
     * @throws SQLException in caso di errore di connessione al database
     */
    List<PhrasesDTO> getAllPhrases() throws SQLException;
}
package Database;

import java.sql.SQLException;
import java.util.List;

/**
 * Interfaccia del Data Access Object che si occupa degli accessi al database relativi agli utenti
 */
public interface UsersDAO {
    String UserTable = "users";
    String UserIdAttribute = "id";
    String UserTipoAttribute = "tipo";
    String UserNameAttribute  = "name";
    String UserSurnameAttribute = "surname";
    String UserNicknameAttribute = "nickname";
    String UserEmailAttribute = "email";
    String UserPasswordAttribute = "password";

    /**
     * Questo metodo permette di aggiungere un utente all'interno del database
     * @param user riferimento all'utente
     * @return <code>true</code> se l'inserimento e' andato a buon fine, altrimenti <code>false</code>
     * @throws SQLException in caso di errore di connessione al database
     */
    boolean addUser(UsersDTO user) throws SQLException;

    /**
     * Questo metodo permette di ottenere le informazioni di un utente conoscendo la sua email
     * @param email email dell'utente
     * @return riferimento all'utente trovato (null se non viene trovato)
     * @throws SQLException in caso di errore di connessione al database
     */
    UsersDTO getUserByEmail(String email) throws SQLException;

    /**
     * Questo metodo permette di ottenere le informazioni di un utente conoscendo il suo nickname
     * @param nickname nickname dell'utente
     * @return riferimento all'utente trovato (null se non viene trovato)
     * @throws SQLException in caso di errore di connessione al database
     */
    UsersDTO getUserByNickname(String nickname) throws SQLException;

    /**
     * Questo metodo permette di ottenere le informazioni di un utente conoscendo il suo id
     * @param id id dell'utente
     * @return riferimento all'utente trovato (null se non viene trovato)
     * @throws SQLException in caso di errore di connessione al database
     */
    UsersDTO getUserById(String id) throws SQLException;

    /**
     * Questo metodo permette di eliminare un utente presente nel database
     * @param id id dell'utente
     * @return <code>true</code> se l'inserimento e' andato a buon fine, altrimenti <code>false</code>
     * @throws SQLException in caso di errore di connessione al database
     */
    boolean deleteUser(String id) throws SQLException;

    /**
     * Questo metodo permette di ottenere la lista contenente tutti gli admin
     *
     * @return La lista di utenti registrati come amministratori
     * @throws SQLException in caso di errore di connessione al database
     */
    List<UsersDTO> getAllAdmin() throws SQLException;

    /**
     * Questo metodo permette di modificare una riga della tabella Users
     * @param user riferimento all'utente a cui e' stato modificato un valore
     * @return <code>true</code> se la modifica e' andata a buon fine, altrimenti <code>false</code>
     * @throws SQLException in caso di errore di connessione al database
     */
    boolean updateUser(UsersDTO user) throws SQLException;

    /**
     * Permette di individuare l'utente che ha ottenuto il punteggio piu' alto alla fine di una manche
     *
     * @return un oggetto di tipo {@link UsersDTO}
     * @throws SQLException in caso di errore di connessione al database
     */
    UsersDTO getBestPlayerByManche() throws SQLException;

    /**
     * Permette di individuare l'utente che ha ottenuto il punteggio piu' alto alla fine di una partita
     *
     * @return un oggetto di tipo {@link UsersDTO}
     * @throws SQLException in caso di errore di connessione al database
     */
    UsersDTO getBestPlayerByMatch() throws SQLException;

    /**
     * Permette di individuare l'utente che ha giocato piu' manche in assoluto
     *
     * @return un oggetto di tipo {@link UsersDTO}
     * @throws SQLException in caso di errore di connessione al database
     */
    UsersDTO getUserForMoreManchesPlayed() throws SQLException;

    /**
     * Permette di individuare l'utente con la media di punti acquisiti per manche piu' alta
     *
     * @return un oggetto di tipo {@link UsersDTO}
     * @throws SQLException in caso di errore di connessione al database
     */
    UsersDTO getUserForBestMancheAverage() throws SQLException;

    /**
     * Permette di individuare l'utente che ha ceduto di piu' il turno in seguito ad errori
     *
     * @return un oggetto di tipo {@link UsersDTO}
     * @throws SQLException in caso di errore di connessione al database
     */
    UsersDTO getUserForMostLostTurn() throws SQLException;

    /**
     * Permette di individuare l'utente che ha perso tutto dal giro di ruota piu' volte
     *
     * @return un oggetto di tipo {@link UsersDTO}
     * @throws SQLException in caso di errore di connessione al database
     */
    UsersDTO getUserForMostLosses() throws SQLException;
}

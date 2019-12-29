package serverRdF;


import java.rmi.RemoteException;

import database.DBManager;
import database.UsersDTO;
import services.ClientInterface;
import services.Login;


public class AutenticationManager {
    private DBManager dbManager;
    private static AutenticationManager autenticationManager;

    private AutenticationManager(DBManager dbmng) {
        dbManager = dbmng;
    }


    /**
     * Questo metodo crea il singleton di AutenticationManager
     *
     * @param dbManager il riferimento al gestore del db
     * @return autenticationManager, il singleton della classe
     */
    public static AutenticationManager createAutenticationManager(DBManager dbManager) {
        if (autenticationManager == null) {
            autenticationManager = new AutenticationManager(dbManager);
            return autenticationManager;
        } else
            return autenticationManager;
    }



    /**
     * Nel caso in cui ci siano problemi con la connessione al server, il client viene notificato
     *
     * @param form  contenente i dati necessari all'autenticazione
     * @param c     il riferimento al client
     * @param admin il boolean che indica se l'utente e' admin oppure no
     * @return 0 se l'autenticazione è andata a buon fine, 1 se l'utente si e' loggato ma non e' sulla piattaforma giusta, -1 altrimenti
     * @throws RemoteException In caso di errore di connessione con il client
     */
    public int signIn(Login form, ClientInterface c, boolean admin) throws RemoteException {
        String email = form.getEmail();
        String password = form.getPasswordC();
            int result = dbManager.checkLogin(email, password, admin);

            if (result == 0) {
                UsersDTO user = dbManager.getUserByEmail(email);
                c.setNickname(user.getNickname());
                c.setId(user.getId());
                c.setName(user.getName());
                c.setSurname(user.getSurname());
                c.setEmail(user.getEmail());
            }
            return result;
    }
}

package serverRdF;


import java.rmi.RemoteException;

import database.DBManager;
import database.UsersDTO;
import services.Client;
import services.Login;


public class AutenticationManager {
    private DBManager dbManager;
    private static AutenticationManager autenticationManager;

    private AutenticationManager(DBManager dbmng) {
        dbManager = dbmng;
    }


    public static AutenticationManager createAutenticationManager(DBManager dbManager) {
        if (autenticationManager == null) {
            autenticationManager = new AutenticationManager(dbManager);
            return autenticationManager;
        } else
            return autenticationManager;
    }



    public int signIn(Login form, Client c, boolean admin) throws RemoteException {
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

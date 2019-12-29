package serverRdF;


import java.rmi.RemoteException;

import database.DBManager;
import services.ClientInterface;
import services.User;



public class WaitingOTP extends Thread {
    private ClientInterface client;
    private DBManager dbManager;
    private User user;
    private boolean admin;

    public WaitingOTP(ClientInterface c, DBManager dbManager, User id, boolean admin) {
        client = c;
        this.dbManager = dbManager;
        user = id;
        this.admin = admin;
    }

    public void run() {
        int tenMininSec = 600000;
        try {
            sleep(tenMininSec);
        } catch (InterruptedException e) {
            boolean bool = dbManager.addUser(user, admin);
            if (!bool) {
                Server.serverError(client);
                e.printStackTrace();
            }
            try {
                client.notifyRegistrationResult(true);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }
    }
}

package Server;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import Services.Client;
import Services.CryptPassword;


public class OTPHelperImplementation extends UnicastRemoteObject implements OTPHelper {
    private String otp;
    private WaitingThread thread;

    public OTPHelperImplementation(WaitingThread t, String code) throws RemoteException {
        otp = code;
        thread = t;
    }

    public boolean checkOTP(String otp, Client c) throws RemoteException {
        String cryptedOTP = CryptPassword.encrypt(otp);
        if (cryptedOTP.equals(this.otp)) {
            thread.interrupt();
            UnicastRemoteObject.unexportObject(this,false);
            return true;
        } else {
            try {
                c.notifyWrongOTP();
            } catch (RemoteException e) {
                ServerImplementation.serverError(c);
                e.printStackTrace();
            }
            return false;
        }
    }
}

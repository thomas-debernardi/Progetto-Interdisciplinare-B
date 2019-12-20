package serverRdF;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import services.Client;
import services.CryptPassword;


public class OTPHelperImplementation extends UnicastRemoteObject implements OTPHelper {
    private String otp;
    private WaitingThread thread;

    public OTPHelperImplementation(WaitingThread t, String code) throws RemoteException {
        otp = code;
        thread = t;
    }

    public boolean checkOTP(String otp, Client c) throws RemoteException {
        String cryptedOTP = CryptPassword.crypt(otp);
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

package serverRdF;


import java.rmi.Remote;
import java.rmi.RemoteException;

import services.Client;


public interface OTPHelper extends Remote {


    public boolean checkOTP(String otp, Client c) throws RemoteException;
}

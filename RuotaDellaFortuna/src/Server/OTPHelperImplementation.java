package Server;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import Services.Client;

/**
 * Oggetto remoto utilizzato dal client per inviare l'OTP ricevuta via email. Se il codice è esatto permette a {@link WaitingThread} di completare la registrazione.
 */
public class OTPHelperImplementation extends UnicastRemoteObject implements OTPHelper {
    private String otp;
    private WaitingThread thread;

    public OTPHelperImplementation(WaitingThread t, String code) throws RemoteException {
        otp = code;
        thread = t;
    }

    /**
     * Controlla se l'otp inserito dal client corrisponde con quello generato dal server. Nel primo caso interrompera' {@link WaitingThread}, nel secondo
     * informera' il client dell'errore invitandolo a riprovare
     *
     * @param otp codice inviato dal client
     * @param c   riferimento al client
     * @return <code>true</code> se i codici corrispondono, <code>false</code> altrimenti
     * @throws RemoteException In caso di errori di connessione al server
     */
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

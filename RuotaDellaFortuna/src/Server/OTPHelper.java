package Server;


import java.rmi.Remote;
import java.rmi.RemoteException;

import Services.Client;

/**
 * Interfaccia remota dell'oggetto che si occupa di ricevere e controllare l'OTP utilizzato dal client per ultimare la registrazione
 */
public interface OTPHelper extends Remote {

    /**
     * Il client invia il codice per poi ricevere la conferma della registrazione, attraverso {@link WaitingThread}, oppure l'inserimento di un codice errato
     *
     * @param otp codice inviato dal client
     * @param c   riferimento al client
     * @return //todo
     * @throws RemoteException in caso di errore di connessione al server
     */
    public boolean checkOTP(String otp, Client c) throws RemoteException;
}

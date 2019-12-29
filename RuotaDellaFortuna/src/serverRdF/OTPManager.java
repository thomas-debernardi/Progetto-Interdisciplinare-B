package serverRdF;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import services.ClientInterface;
import services.OTPCrypt;

public class OTPManager extends UnicastRemoteObject implements OTPManagerInterface {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String otp;
	private WaitingOTP thread;

	public OTPManager(WaitingOTP t, String code) throws RemoteException {
		otp = code;
		thread = t;
	}

	public boolean checkOTP(String otp, ClientInterface c) throws RemoteException {
		String cryptedOTP = OTPCrypt.crypt(otp);
		if (cryptedOTP.equals(this.otp)) {
			thread.interrupt();
			UnicastRemoteObject.unexportObject(this, false);
			return true;
		} else {
			try {
				c.notifyWrongOTP();
			} catch (RemoteException e) {
				Server.serverError(c);
				e.printStackTrace();
			}
			return false;
		}
	}
}

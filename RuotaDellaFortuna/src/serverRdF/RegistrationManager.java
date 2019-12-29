package serverRdF;

import java.rmi.RemoteException;
import java.util.Random;

import database.DBManager;
import database.UsersDTO;
import services.ClientInterface;
import services.OTPCrypt;
import services.EmailAddressDoesNotExistException;
import services.EmailManager;
import services.User;

public class RegistrationManager {
	private DBManager dbManager;
	private EmailManager emailManager;
	private static RegistrationManager registrationManager = null;

	private RegistrationManager(DBManager dbManager, EmailManager emailManager) {
		this.dbManager = dbManager;
		this.emailManager = emailManager;
	}

	/**
	 * Metodo che consente di creare il singleton di RegistrationManager
	 * 
	 * @param dbManager    istanza del DatabaseManager
	 * @param emailManager istanza EmailManager
	 * @return registrationManager singleton di RegistrationManager
	 */
	public static RegistrationManager createRegistrationManager(DBManager dbManager, EmailManager emailManager) {
		if (registrationManager == null) {
			registrationManager = new RegistrationManager(dbManager, emailManager);
			return registrationManager;
		} else
			return registrationManager;
	}

	/**
	 * Questo metodo permette la registrazione di un nuovo account da parte del
	 * client
	 *
	 * @param form  un oggetto di tipo User contenente tutti i dati necessari
	 * @param c     il riferimento al client
	 * @param admin <code>true</code> se si registra un admin, <code>false</code>
	 *              altrimenti
	 * @return un oggetto remoto OTPHelper necessario ad ultimare la registrazione
	 * @throws RemoteException nel caso in cui non sia possibile comunicare con il
	 *                         server
	 */
	public OTPManagerInterface signUp(User form, ClientInterface c, boolean admin) throws RemoteException {
		String otp = generateOTP();
		String sub = "Confirm registration RDF";
		String text = "Insert " + otp + " in the app";
		try {
			emailManager.sendEmail(form.getEmail(), sub, text);
		} catch (EmailAddressDoesNotExistException e) {
			throw new EmailAddressDoesNotExistException();
		}
		WaitingOTP thread = new WaitingOTP(c, dbManager, form, admin);
		String cryptedOTP = OTPCrypt.crypt(otp);
		OTPManager otpHelper = new OTPManager(thread, cryptedOTP);
		thread.start();
		return otpHelper;
	}

	private String generateOTP() {
		Random rd = new Random();
		String res = "";
		for (int i = 0; i < 6; i++) {
			res += rd.nextInt(10);
		}
		return res;
	}

	/**
	 * Questo metodo permette di controllare l'univocità delle mail
	 * 
	 * @param email email da controllare
	 * @return <code>true</code> se la mail è unica, altromenti <code>false</code>
	 */
	public boolean checkEmail(String email) {
		UsersDTO user = dbManager.getUserByEmail(email);
		if (user != null) {
			return false;
		} else
			return true;
	}

	/**
	 * Questo metodo permette di controllare l'univocità dei nickname
	 * 
	 * @param nickname nickname da controllare
	 * @return <code>true</code> se il nickname è unica, altromenti
	 *         <code>false</code>
	 */
	public boolean checkNickname(String nickname) {
		UsersDTO user = dbManager.getUserByNickname(nickname);
		if (user != null) {
			return false;
		} else
			return true;
	}

	/**
	 * Questo metodo permette di controllare ( al momento del cambio password) se la
	 * password che si sta inserendo è la stessa password che si sta usando
	 * attualmente
	 * 
	 * @param nickname nickname dell'utente
	 * @param password password dell'utente
	 * @return <code>true</code> se la password è nuova, altrimenti
	 *         <code>false</code>
	 */
	public boolean checkPassword(String nickname, String password) {
		UsersDTO user = dbManager.getUserByNickname(nickname);
		if (user.getPassword().equals(password)) {
			return true;
		} else {
			return false;
		}

	}
}

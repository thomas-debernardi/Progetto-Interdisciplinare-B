package serverRdF;

import java.util.Random;

import database.DBManager;
import database.UsersDTO;
import services.OTPCrypt;
import services.EmailManager;

public class ProfileManager {
	private DBManager dbManager;
	private EmailManager emailManager;
	private static ProfileManager profileManager = null;

	private ProfileManager(DBManager dbmng, EmailManager emailManager) {
		dbManager = dbmng;
		this.emailManager = emailManager;
	}

	/**
	 * Questo metodo crea il singleton ProfileManager
	 * 
	 * @param dbmng        il riferimento a DBManager
	 * @param emailManager il riferimento a EmailManager
	 * @return Il riferimento al singleton di ProfileManager
	 */
	public static ProfileManager createProfileManager(DBManager dbmng, EmailManager emailManager) {
		if (profileManager == null) {
			profileManager = new ProfileManager(dbmng, emailManager);
			return profileManager;
		} else {
			return profileManager;
		}
	}

	/**
	 * Questo metodo modifica la password di un utente nel database
	 *
	 * @param password la nuova password da sostituire alla vecchia
	 * @param idUser   id dell'utente
	 * @return <code>true</code> se il cambiamento avviene con successo,
	 *         <code>false</code> altrimenti
	 */
	public boolean changePassword(String password, String idUser) {
		UsersDTO user = dbManager.getUserById(idUser);
		user.setPassword(password);
		String email = user.getEmail();
		String sub = "Password changed RDF";
		String txt = user.getName() + "Your password has been changed";
		emailManager.sendEmail(email, sub, txt);
		return dbManager.updateUser(user);
	}

	/**
	 * Questo metodo reimposta la password di un utente con una generata
	 * casualmente. Manda una mail all'utente contenente la password
	 *
	 * @param email l'indirizzo email dell'account da modificare
	 * @return <code>true</code> se il reset avviene con successo,
	 *         <code>false</code> altrimenti
	 */
	public boolean resetPassword(String email) {
		UsersDTO user = dbManager.getUserByEmail(email);
		if (user != null) {
			String password = generateRandomPassword();
			String sub = "Reset password RDF";
			String txt = user.getName() + " this is your new password:	" + password;
			emailManager.sendEmail(email, sub, txt);
			user.setPassword(password);
			dbManager.updateUser(user);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Questo metodo permette di generare una password casuale
	 * 
	 * @return la password generata
	 */
	private static String generateRandomPassword() {
		Random rnd = new Random();
		String result = "P";
		for (int i = 0; i < 8; i++) {
			int chars = rnd.nextInt(3);
			switch (chars) {
			case 0:
				result += rnd.nextInt(10);
				break;
			case 1:
				result += (char) (rnd.nextInt(26) + 65);
				break;
			case 2:
				result += (char) (rnd.nextInt(26) + 97);
				break;
			}
		}
		return result;
	}

	/**
	 * Questo metodo modifica il nome di un utente nel database
	 *
	 * @param name   il nuovo nome da sostituire al vecchio
	 * @param idUser id dell'utente
	 * @return <code>true</code> se il cambiamento avviene con successo,
	 *         <code>false</code> altrimenti
	 */
	public boolean changeName(String name, String idUser) {
		UsersDTO user = dbManager.getUserById(idUser);
		if (user != null) {
			user.setName(name);
			return dbManager.updateUser(user);
		} else
			return false;
	}

	/**
	 * Questo metodo modifica il cognome di un utente nel database
	 *
	 * @param surname il nuovo cognome da sostituire al vecchio
	 * @param idUser  id dell'utente
	 * @return <code>true</code> se il cambiamento avviene con successo,
	 *         <code>false</code> altrimenti
	 */
	public boolean changeSurname(String surname, String idUser) {
		UsersDTO user = dbManager.getUserById(idUser);
		user.setSurname(surname);
		return dbManager.updateUser(user);
	}

	/**
	 * Questo metodo modifica il nickname di un utente nel database
	 *
	 * @param nickname il nuovo nickname da sostituire al vecchio
	 * @param idUser   id dell'utente
	 * @return <code>true</code> se il cambiamento avviene con successo,
	 *         <code>false</code> altrimenti
	 */
	public boolean changeNickname(String nickname, String idUser) {
		UsersDTO user = dbManager.getUserById(idUser);
		user.setNickname(nickname);
		return dbManager.updateUser(user);
	}
}

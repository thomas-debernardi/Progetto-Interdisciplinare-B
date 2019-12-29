package services;

import java.io.Serializable;

public class Login implements Serializable {
	private static final long serialVersionUID = 1L;
	
	protected String passwordC;
	protected String email;
	
	/**
	 * Questo metodo permette il login da parte dell'utente
	 * @param email mail dell'utente
	 * @param password password dell'utente
	 */
	public Login(String email, String password) {
		this.email = email;
		this.passwordC = password;
	}
	
	/**
	 * Questo metodo visualizza la mail dell'utente
	 * 
	 * @return mail utente
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Questo metodo visualizza la SerialVersionUID
	 * 
	 * @return serialVersionUID
	 */
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	
	/**
	 * Questo metodo visualizza la password dell'utente
	 * 
	 * @return password utente
	 */
	public String getPasswordC() {
        return passwordC;
    }

}

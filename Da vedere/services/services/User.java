package services;

import java.io.Serializable;
import java.util.UUID;

public class User extends Login implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String surname;
	private String nickname;
	private String id;

	public User(String email, String password, String name, String surname, String nickname) {
		super(email, password);
		this.name = name;
		this.surname = surname;
		this.nickname = nickname;
		id = UUID.randomUUID().toString();
	}

	/**
	 * Questo metodo restutuisce il nome del giocatore
	 * @return nome giocatore
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Questo metodo restutuisce il cognome del giocatore
	 * @return cognome giocatore
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Questo metodo restutuisce il nickname del giocatore
	 * @return nickname giocatore
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * Questo metodo permette di impostare il nickname
	 * @param nickname nickname che si vuole impostare
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * Questo metodo restituisce l'id del giocatore
	 * @return id giocatore
	 */
	public String getId() {
		return id;
	}

}

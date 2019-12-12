package Services;

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

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getId() {
		return id;
	}

}

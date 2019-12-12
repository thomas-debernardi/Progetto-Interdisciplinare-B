package Database;

public class UsersDTO {
	private String id;
	private boolean isAdmin;
	private String name;
	private String surname;
	private String nickname;
	private String email;
	private String password;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean admin) {
		this.isAdmin = admin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UsersDTO() {
	}

	public UsersDTO(String id, boolean isAdmin, String name, String surname, String nickname, String email,
			String password) {
		this.id = id;
		this.isAdmin = isAdmin;
		this.name = name;
		this.surname = surname;
		this.nickname = nickname;
		this.email = email;
		this.password = password;
	}
}

package Services;

import java.io.Serializable;

public class Login implements Serializable {
	private static final long serialVersionUID = 1L;
	
	protected String passwordC;
	protected String email;
	
	public Login(String email, String password) {
		this.email = email;
		this.passwordC = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public String getPasswordC() {
        return passwordC;
    }

}

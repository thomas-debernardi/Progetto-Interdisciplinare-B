package serverRdF;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//AZIONI CHE AVVENGONO SUL DATABASE
public class Database {
	private static String USR_ADMIN = "postgres";
	private static String PSW_ADMIN = "admin";
	private static final String DBNAME = "RuotaDellaFortuna";
	private static String URL = "jdbc:postgresql://localhost/";

	private static Database db = null;

	private Database() {

	}
	
	//CONNESSIONE
	private Connection getConnection() throws SQLException{
		Connection c = DriverManager.getConnection(URL + DBNAME, USR_ADMIN, PSW_ADMIN );
		return c;
	}
	
	private void close(Connection c) throws SQLException{
		c.close();
	}
	
	public boolean checkAdminExistence() {
		boolean result = false;
		String sql = "SELECT * FROM admin";
		try {
			Connection c = this.getConnection();
			PreparedStatement stmt = c.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				result = true;
			}
			rs.close();
			this.close(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//MANIPOLAZIONE DATI
	//UTENTI
	public boolean addAdmin(String name, String surname, String email, String password) {
		boolean result = false;
		String sql = "INSERT INTO admin (nome , cognome , email , password ) " + 
				"VALUES ( ? , ? , ? , ? , ? )";
		try {
			Connection c = this.getConnection();
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setString(2, surname);
			stmt.setString(3, email);
			stmt.setString(4, password);
			stmt.executeUpdate();
			result = true;
			close(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean addUser(String name, String surname , String email, String password) {
		boolean result = false;
		String sql = "INSERT INTO utente (name , surname ,  email , password ) " + 
				"VALUES ( ? , ? , ? , ? )";
		try {
			Connection c = this.getConnection();
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setString(2, surname);
			stmt.setString(3, email);
			stmt.setString(4, password);		
			stmt.executeUpdate(sql);
			result = true;
			close(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

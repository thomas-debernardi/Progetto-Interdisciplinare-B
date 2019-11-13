package Database;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import Services.Notification;

public class DBBuilder {
	
	private boolean created = false;
	private String port;
	private String user;
	private String host;
	private final String DIR = "src/main/java/Database/Database_SQL.sql"; 
	private Statement statement;
	private StringBuilder sb;
	private Scanner scanner;

	private void createTable(String host, String port, String user, String password){
		try {
			Connection connection = DriverManager.getConnection("jdbc:postgresql://" + host+":"+port+"/dbRuotaDellaFortuna",user,password);
			try {
				Statement stmt = connection.createStatement();
				stmt.execute("CREATE DATABASE dbRuotaDellaFortuna");
			} catch (SQLException e) {
			System.out.println("DATABASE GIA' ESISTENTE");
		} finally {
			if(!created) {
				Connection connection2 = DriverManager.getConnection("jdbc:postgresql://" + host+":"+port+"/dbRuotaDellaFortuna",user,password);
				statement = connection2.createStatement();
				File sqlFile = new File(DIR);
				sb = new StringBuilder("");
				scanner = new Scanner(sqlFile);
				while(scanner.hasNextLine()) {
					sb.append(scanner.nextLine());
				}
				String string = sb.toString();
				statement.executeUpdate(string);
				Notification.notify("Successo", msg, error);
				created = true;
				connection.close();
			}
			else {
				Notification.notify("Database gia' creato", msg, error);
			}
		}
		} catch(SQLException ex) {
			Notification.notify(title, msg, error);
		} catch (FileNotFoundException ex) {
			Notification.notify(title, msg, error);
		}
		
	}
}

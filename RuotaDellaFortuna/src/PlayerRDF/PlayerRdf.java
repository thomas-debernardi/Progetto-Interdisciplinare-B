package PlayerRDF;

import javax.swing.JFrame;
import javax.swing.JTextField;
import GUI.WelcomePane;
import Server.Server;
import Services.AdminChecker;
import Services.Client;
import java.rmi.registry.Registry;

public class PlayerRdf {

	private JFrame frame;
	private JTextField textFieldHostName;
	private Registry registry;
	private static Server server;
	private static Client client;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		AdminChecker.setIsAdmin(false);
		WelcomePane wp = new WelcomePane();
	}

}

package adminRdF;

import javax.swing.JFrame;
import javax.swing.JTextField;

import gui.MainPane;
import gui.WelcomePane;
import serverRdF.Server;
import services.AdminChecker;
import services.Client;

import java.rmi.registry.Registry;

public class AdminRdf {

	private JFrame frame;
	private JTextField textFieldHostName;
	private Registry registry;
	private static Server server;
	private static Client client;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		AdminChecker.setIsAdmin(true);
		WelcomePane wp = new WelcomePane();
	}
}

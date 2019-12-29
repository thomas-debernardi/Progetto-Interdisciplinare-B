package playerRdF;

import javax.swing.JFrame;
import javax.swing.JTextField;

import gui.HostNameConnection;
import serverRdF.ServerInterface;
import services.AdminChecker;
import services.ClientInterface;

import java.rmi.registry.Registry;

public class PlayerRdf {

	private JFrame frame;
	private JTextField textFieldHostName;
	private Registry registry;
	private static ServerInterface server;
	private static ClientInterface client;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		AdminChecker.setIsAdmin(false);
		HostNameConnection wp = new HostNameConnection();
	}

}

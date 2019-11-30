package AdminRDF;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import GUI.MainPane;
import Server.Server;
import Services.AdminChecker;
import Services.Client;
import Services.Controller;
import Services.Notification;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.awt.event.ActionEvent;

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
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminRdf window = new AdminRdf();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AdminRdf() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		AdminChecker.setIsAdmin(true);
		frame = new JFrame("Admin RDF");
		frame.getContentPane().setBackground(Color.GRAY);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.setBounds(100, 100, 387, 193);
		frame.getContentPane().setLayout(null);

		JLabel lblHostName = new JLabel("Host Name");
		lblHostName.setForeground(Color.WHITE);
		lblHostName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblHostName.setBounds(39, 70, 74, 13);
		frame.getContentPane().add(lblHostName);

		textFieldHostName = new JTextField();
		textFieldHostName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldHostName.setColumns(10);
		textFieldHostName.setBounds(136, 69, 196, 19);
		frame.getContentPane().add(textFieldHostName);

		JButton btnConnect = new JButton("CONNECT");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startGameView();
			}
		});
		btnConnect.setForeground(Color.WHITE);
		btnConnect.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnConnect.setBackground(Color.GREEN);
		btnConnect.setBounds(39, 100, 196, 21);
		frame.getContentPane().add(btnConnect);

		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setForeground(Color.WHITE);
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnExit.setBackground(Color.RED);
		btnExit.setBounds(245, 100, 87, 21);
		frame.getContentPane().add(btnExit);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}

	public void startGameView() {
		String host = textFieldHostName.getText();
		try {
			registry = LocateRegistry.getRegistry(host, 1099);
			server = (Server) registry.lookup("SERVER");
			System.out.println(server);
			MainPane mp = new MainPane();
			Notification.notify("SUCCESS", "Connected to the host", true);
			frame.dispose();
		} catch (RemoteException | NotBoundException e) {
			Notification.notify("Connection Notification", "Connessione non riuscita \nriprovare", true);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
    public static void setController(MainPane c) {
        c.setServer(server);
        c.setAdmin(AdminChecker.isIsAdmin());
    }
}

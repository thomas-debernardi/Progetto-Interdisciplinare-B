package GUI;

import Server.Server;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Services.AdminChecker;
import Services.Client;
import Services.Controller;
import Services.Notification;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WelcomePane {

	private JFrame frame;
	private JTextField textFieldHostName;
	private Registry registry;
	private static Server server;
	private static Client client;

	
	public WelcomePane() {
		initialize();
	}
	public void initialize() {
		frame = new JFrame("Player RDF");
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
			MainPane mp = new MainPane();
			frame.dispose();
		} catch (RemoteException e) {
            Notification.notify("Connection Notification", "Connessione non riuscita \n riprovare", true);
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
	
 	   public static void setController(MainPane mainPane) {
	        mainPane.setServer(server);
	        mainPane.setAdmin(AdminChecker.isIsAdmin());
	    }
	
}

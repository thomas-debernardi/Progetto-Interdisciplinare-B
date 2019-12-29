package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import serverRdF.ServerInterface;
import services.AdminChecker;
import services.ClientInterface;
import services.Notification;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteObject;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.ActionEvent;

public class HostNameConnection {

	private JFrame frame;
	private JTextField textFieldHostName;
	private Registry registry;
	private static ServerInterface server;
	private static ClientInterface client;
	int posX = 0, posY = 0;

	public HostNameConnection() {
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

		frame.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				posX = e.getX();
				posY = e.getY();
			}
		});

		frame.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent evt) {
				// sets frame position when mouse dragged
				frame.setLocation(evt.getXOnScreen() - posX, evt.getYOnScreen() - posY);
			}
		});
	}

	public void startGameView() {
		String host = textFieldHostName.getText();
		try {
			registry = LocateRegistry.getRegistry(host, 1099);
			server = (ServerInterface) registry.lookup("SERVER");
			MainPane mp = new MainPane();
			frame.dispose();
		} catch (RemoteException e) {
			Notification.notify("ERROR", "Connection lost");
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

	public static void setController(MainPane mainPane) {
		mainPane.setServer(server);
		mainPane.setAdmin(AdminChecker.isIsAdmin());
	}

}

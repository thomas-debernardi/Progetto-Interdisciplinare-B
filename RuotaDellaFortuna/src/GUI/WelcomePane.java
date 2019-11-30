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

public class WelcomePane extends JFrame {

	private JPanel contentPane;
	private JTextField tfHostName;
    private Registry registry;    
    private static Server server;
    private static Client client;

	
	
	public WelcomePane() {
		JFrame frame = new JFrame();
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 405, 143);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel title = new JLabel("The Wheel Of Fortune");
		title.setBounds(35, 10, 330, 33);
		title.setForeground(Color.BLUE);
		title.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 25));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(title);
		
		JLabel lblHostName = new JLabel("Host Name");
		lblHostName.setBounds(61, 52, 74, 19);
		lblHostName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblHostName);
		
		tfHostName = new JTextField();
		tfHostName.setBounds(140, 52, 96, 19);
		tfHostName.setToolTipText("Host name");
		tfHostName.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(tfHostName);
		tfHostName.setColumns(10);
		JButton btnConfirm = new JButton("CONFIRM");
		btnConfirm.setBounds(241, 48, 99, 27);
		btnConfirm.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(btnConfirm);
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startGameView();
			}
		});
	}
	
	public void startGameView() {		
		String host = tfHostName.getText();
		try {
			registry = LocateRegistry.getRegistry(host, 1099);
			server = (Server) registry.lookup("SERVER");
			//FACCIO PARTIRE LA MAIN PANEL
			
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

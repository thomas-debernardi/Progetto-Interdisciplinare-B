package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Server.ServerInterface;
import Services.Client;
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
    
    
    private static ServerInterface server;
    private static Client client;

	
	
	public WelcomePane() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 405, 143);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel title = new JLabel("The Wheel Of Fortune");
		title.setForeground(Color.BLUE);
		title.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 25));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(title);
		
		JLabel lblHostName = new JLabel("Host Name");
		lblHostName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblHostName);
		
		tfHostName = new JTextField();
		tfHostName.setToolTipText("Host name");
		tfHostName.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(tfHostName);
		tfHostName.setColumns(10);
		JButton btnConnect = new JButton("CONNECT");
		
		btnConnect.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(btnConnect);
		
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	}
	
	public void startGUI() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WelcomePane frame = new WelcomePane();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		String host = tfHostName.getText();
		try {
			registry = LocateRegistry.getRegistry(host, 1099);
			server = (ServerInterface) registry.lookup("SERVER");
			//FACCIO PARTIRE LA MAIN PANEL
			
		} catch (RemoteException e) {
            Notification.notify("Connection Notification", "Connessione non riuscita \n riprovare", true);
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
	
}

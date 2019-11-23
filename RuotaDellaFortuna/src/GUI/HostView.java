package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Server.Server;

import javax.swing.JLabel;

public class HostView extends JFrame {

	private JPanel contentPane;
	private Registry r;
	private Server server;
	private JLabel lblHost;
	/**
	 * Create the frame.
	 */
	public HostView() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 450, 300);
		frame.setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		frame.setContentPane(contentPane);
		
		lblHost = new JLabel("HOST NAME");
		contentPane.add(lblHost, BorderLayout.CENTER);
	}
	
		public void initialize(URL location, ResourceBundle resources) {
			InsubriaLoginController.setHost(this);
			try {
				takeAddress();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public void takeAddress() throws Exception{
			r = LocateRegistry.createRegistry(1099);
			r.rebind("SERVER",server);
			InetAddress address = null;
			try {
				address = InetAddress.getLocalHost();
				lblHost.setText("Hostname: " + address.getHostName());
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}
		
		public Registry getR() {
			return r;
		}

		public Server getServer() {
			return server;
		}

		public void setR(Registry r) {
			this.r = r;
		}

		public void setServer(Server server) {
			this.server = server;
		}
		
		
	}


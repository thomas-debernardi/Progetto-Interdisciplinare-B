package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Database.DBManager;
import Services.Notification;

public class ServerMainPane extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldUser;
	private JPasswordField passwordField;
	private JTextField textFieldHostname;
	private JTextField textFieldPort;
	
    private DBManager manager;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerMainPane frame = new ServerMainPane();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ServerMainPane() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 450, 300);
		frame.setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblUser = new JLabel("User");
		lblUser.setBounds(10, 10, 46, 13);
		contentPane.add(lblUser);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 33, 46, 13);
		contentPane.add(lblPassword);
		
		JLabel lblHostname = new JLabel("Hostname");
		lblHostname.setBounds(10, 56, 46, 13);
		contentPane.add(lblHostname);
		
		JLabel lblPort = new JLabel("PORT");
		lblPort.setBounds(10, 79, 46, 13);
		contentPane.add(lblPort);
		
		textFieldUser = new JTextField();
		textFieldUser.setBounds(98, 7, 96, 19);
		contentPane.add(textFieldUser);
		textFieldUser.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(98, 30, 96, 19);
		contentPane.add(passwordField);
		
		textFieldHostname = new JTextField();
		textFieldHostname.setBounds(98, 53, 96, 19);
		contentPane.add(textFieldHostname);
		textFieldHostname.setColumns(10);
		
		textFieldPort = new JTextField();
		textFieldPort.setBounds(98, 76, 96, 19);
		contentPane.add(textFieldPort);
		textFieldPort.setColumns(10);
		
		JButton btnStart = new JButton("START");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		btnStart.setBounds(10, 136, 140, 44);
		contentPane.add(btnStart);
		
	}
	
	public void login() {
		 String user = textFieldUser.getText();
	     String password = passwordField.getText();
	     String hostname = textFieldHostname.getText();
	     String port = textFieldPort.getText();
	     
	     try {
	            String url = hostname + ":" + port;
	            manager = DBManager.createDBManager(url, user, password);
	            InsubriaLoginController.setDbManager(manager);
	            InsubriaLoginController insubria = new InsubriaLoginController();
	        } catch (SQLException e) {
	            Notification.notify("Connection Notification", "Connessione non riuscita \nriprovare", true);
	        }
	}

}

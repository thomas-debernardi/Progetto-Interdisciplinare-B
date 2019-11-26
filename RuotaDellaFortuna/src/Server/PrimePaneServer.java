package Server;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Database.DBManager;
import GUI.InsubriaLoginController;
import Services.Notification;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class PrimePaneServer extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldUser;
	private JPasswordField passwordField;
	private JTextField textFieldHostname;
	private JTextField textFieldPort;
    private DBManager manager;
	static PrimePaneServer frame = new PrimePaneServer();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public PrimePaneServer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 240, 207);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
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
				try {
					login();
				} catch (IOException e1) {
					
				}
				
			}
		});
		btnStart.setBounds(69, 139, 85, 21);
		contentPane.add(btnStart);
	}
	
    public void login() throws IOException {
        String user = textFieldUser.getText();
        String password = passwordField.getText();
        String hostname = textFieldHostname.getText();
        String port = textFieldPort.getText();
        try {
            String url = hostname + ":" + port;
            manager = DBManager.createDBManager(url, user, password);
            InsubriaLoginController.setDbManager(manager);
            InsubriaLoginController ilc = new InsubriaLoginController();     
            frame.dispose();
            Notification.notify("ACCESSO ESEGUITO", "", false);
        } catch (SQLException e) {
        	System.out.println(e);
            Notification.notify("Connection Notification", e.toString(), true);
        }

    }

	
}
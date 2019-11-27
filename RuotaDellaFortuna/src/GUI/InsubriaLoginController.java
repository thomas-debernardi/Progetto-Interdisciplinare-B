package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Database.DBManager;
import Email.EmailManager;
import Server.Server;
import Server.ServerImplementation;
import Services.Client;
import Services.ClientImplementation;
import Services.Controller;
import Services.Notification;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class InsubriaLoginController {

	private JPanel contentPane;
	private JTextField textFieldEmail;
	private JPasswordField passwordField;
	private EmailManager emailManager;
    private static DBManager dbManager;
    private static Registry registry;
    private static Server server;
    private static Client client;
    public static boolean forServer = false;
    JFrame frame;
	
    public InsubriaLoginController() {
		initialize();
	}
    
	/**
	 * Create the frame.
	 */
	public void initialize() {
		frame = new JFrame("INSUBRIA LOGIN CONTROLLER");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 450, 300);
		frame.setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		frame.setContentPane(contentPane);
		frame.getContentPane().setLayout(null);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 10, 46, 13);
		frame.getContentPane().add(lblEmail);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(10, 33, 96, 19);
		frame.getContentPane().add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 62, 46, 13);
		frame.getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(10, 85, 96, 19);
		frame.getContentPane().add(passwordField);
		
		JButton btnSend = new JButton("SEND");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
					try {
						loginManager();
					} catch (IOException e1) {
						System.out.println(e1);
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		btnSend.setBounds(35, 137, 85, 21);
		frame.getContentPane().add(btnSend);
		
	}
	
	
	 public void loginManager() throws IOException, RemoteException {
			String email = textFieldEmail.getText();
	        String password = passwordField.getText();
	        boolean logged = EmailManager.logIntoAccount(email, password);
	        if (logged) {
	            forServer = true;
	            emailManager = EmailManager.createEmailManager(email, password);
	            server = new ServerImplementation(dbManager, emailManager);
	            client = new ClientImplementation();
	            if (dbManager.getAnyAdmin()) {
	            	MainPane mp = new MainPane();
	            	frame.dispose();
	            } else {
	            	Registration reg = new Registration();
	                frame.dispose();
	            }
	        } else {
	            Notification.notify("Mail Notification", "E-mail o password errati \nimmettere nuova mail", true);
	        }
	    }
	
	public static void setDbManager(DBManager db) {
        dbManager = db;
    }

    public static Server getServer() {
        return server;
    }

    public static Registry getRegistry() {
        return registry;
    }

    public static void setReg(Registration r) {
        r.setServer(true);
        r.setServer(server);
        r.setAdmin(true);
        r.setClient(client);
    }

    public static void setController(Controller ctr) {
        ctr.setClient(client);
        ctr.setServer(server);
        ctr.setAdmin(true);
        ctr.setIsServer(true);
    }

    public static void setHost(HostView host) {
        host.setServer(server);
        host.setR(registry);
    }
}

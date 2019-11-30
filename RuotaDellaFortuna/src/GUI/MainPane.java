package GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Server.Server;
import Services.AdminChecker;
import Services.Client;
import Services.ClientImplementation;
import Services.Controller;
import Services.Login;
import Services.Notification;
import GUI.TabPaneController;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;

public class MainPane {

	private JFrame frame;
	private JTextField textFieldEmail;
	private JPasswordField passwordField;
	
    private static Server server;
    private static Client client;
    private static boolean admin;
    private static boolean isServer = false;

	/**
	 * Create the application.
	 */
	public MainPane() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		if (!InsubriaLoginController.forServer) {
            WelcomePane.setController(this);
            if (server == null) {
                InsubriaLoginController.setController(this);
            }
        } else
            InsubriaLoginController.setController(this);
        try {
            client = new ClientImplementation();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
		
		frame = new JFrame("MAIN PANE");
		frame.setVisible(true);
		frame.setBounds(100, 100, 508, 459);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(22, 29, 46, 13);
		frame.getContentPane().add(lblEmail);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(10, 52, 96, 19);
		frame.getContentPane().add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(237, 29, 46, 13);
		frame.getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(237, 52, 96, 19);
		frame.getContentPane().add(passwordField);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					login();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnLogin.setBounds(189, 107, 85, 21);
		frame.getContentPane().add(btnLogin);
		
		JLabel lblDontHaveAn = new JLabel("Don't have an account?");
		lblDontHaveAn.setBounds(22, 178, 192, 13);
		frame.getContentPane().add(lblDontHaveAn);
		
		JButton btnRegister = new JButton("REGISTER");
		btnRegister.setBounds(48, 201, 85, 21);
		frame.getContentPane().add(btnRegister);
		
		JLabel lblForgotPassword = new JLabel("Forgot password?");
		lblForgotPassword.setBounds(237, 178, 217, 13);
		frame.getContentPane().add(lblForgotPassword);
		
	}
	
	public void login() throws Exception {
        String email = textFieldEmail.getText();
        String password = passwordField.getText();
        if(email.equals("")) {
        	Notification.notify("ERRORE", "You didn't insert an email addres", false);
        }
        if(password.equals("")) {
        	Notification.notify("ERRORE", "You didn't insert a password", false);
        }
        if (!(email.equals("") || password.equals(""))) {
            Login login = new Login(email, password);
            int result = server.signIn(login, client, admin);
            System.out.println(result);
            if (result < 0) {
                Notification.notify("Mail Notification", "E-mail o password errati \nriprova!", true);
            } else if (result == 0) {
                if (!isServer) {
                	TabPaneController tpc = new TabPaneController();
                   frame.dispose();
                } else {
                	HostView hv = new HostView();
                	frame.dispose();
                }
            } else {
                Notification.notify("Mail Notification", "Si sta provando ad accedere alla piattaforma dal client sbagliato \nriprova!", true);
            }
        }
    }
	
	
    public static void setRegistration(Registration registration){
        registration.setClient(client);
        registration.setServer(server);
        registration.setServer(false);
        registration.setAdmin(AdminChecker.isIsAdmin());
    }

    public static void setServer(Server server) {
        MainPane.server = server;
    }

    public static void setClient(Client client) {
        MainPane.client = client;
    }

    public static void setAdmin(boolean admin) {
    	MainPane.admin = admin;
    }
    
    public static void setIsServer(boolean isS) {
        isServer = isS;
    }
    
    public static void setArgs(TabPaneController tb){
        tb.setClient(client);
        tb.setServer(server);
        tb.setAdmin(admin);
    }

}

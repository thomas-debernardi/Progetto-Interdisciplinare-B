package GUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Server.Server;
import Services.Client;
import Services.Login;
import Services.Notification;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginAdmin {

	private JPanel contentPane;
	private JTextField textFieldEmail;
	private JTextField textFieldPassword;
	private JFrame frame;
	
    private static Server server;
    private static Client client;
    private static boolean admin;
    private static boolean isServer = false;
    
	
	public LoginAdmin() {
		initialize();
	}

	/**
	 * Create the frame.
	 */
	public void initialize() {
		
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 544, 426);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWelcomeInWheel = new JLabel("Welcome in Wheel of Fortune Admin panel");
		lblWelcomeInWheel.setBounds(129, 10, 247, 13);
		contentPane.add(lblWelcomeInWheel);
		
		JLabel lblRegister = new JLabel("Register");
		lblRegister.setBounds(343, 324, 94, 13);
		contentPane.add(lblRegister);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(65, 64, 46, 13);
		contentPane.add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(65, 224, 46, 13);
		contentPane.add(lblPassword);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(54, 87, 96, 19);
		contentPane.add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setBounds(54, 247, 96, 19);
		contentPane.add(textFieldPassword);
		textFieldPassword.setColumns(10);
		
		JLabel lblForgotPassword = new JLabel("Forgot password?");
		lblForgotPassword.setBounds(275, 224, 137, 13);
		contentPane.add(lblForgotPassword);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnLogin.setBounds(155, 320, 85, 21);
		contentPane.add(btnLogin);
	}
	
	public void login() throws Exception {
        String mail = textFieldEmail.getText();
        String password = textFieldPassword.getText();
        if (!(mail.equals("") || password.equals(""))) {
            Login login = new Login(password, mail);
            int result = server.signIn(login, client, admin);
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

}

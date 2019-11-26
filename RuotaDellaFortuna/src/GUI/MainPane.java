package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class MainPane {

	private JFrame frame;
	private JTextField textFieldEmail;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPane window = new MainPane();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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
		frame = new JFrame();
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
}

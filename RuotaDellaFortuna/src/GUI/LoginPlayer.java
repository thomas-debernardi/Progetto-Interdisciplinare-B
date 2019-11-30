package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginPlayer extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldEmail;
	private JTextField textFieldPassword;

	/**
	 * Create the frame.
	 */
	public LoginPlayer() {
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 544, 426);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWelcomeInWheel = new JLabel("Welcome in Wheel of Fortune Player panel");
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
}

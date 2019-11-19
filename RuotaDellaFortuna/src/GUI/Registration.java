package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class Registration extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldName;
	private JTextField textFieldSurname;
	private JTextField textFieldNickname;
	private JTextField textFieldEmail;
	private JPasswordField passwordField;
	private JPasswordField passwordFieldRepeat;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registration frame = new Registration();
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
	public Registration() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 304, 375);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRegistration = new JLabel("REGISTRATION");
		lblRegistration.setBounds(67, 17, 161, 13);
		contentPane.add(lblRegistration);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(22, 40, 46, 13);
		contentPane.add(lblName);
		
		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setBounds(191, 40, 83, 13);
		contentPane.add(lblSurname);
		
		JLabel lblNickname = new JLabel("Nickname");
		lblNickname.setBounds(22, 107, 46, 13);
		contentPane.add(lblNickname);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(184, 107, 46, 13);
		contentPane.add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(22, 184, 46, 13);
		contentPane.add(lblPassword);
		
		JLabel lblRepeatPassword = new JLabel("Repeat Password");
		lblRepeatPassword.setBounds(182, 195, 145, 13);
		contentPane.add(lblRepeatPassword);
		
		JButton btnRegister = new JButton("REGISTER");
		btnRegister.setBounds(109, 275, 85, 21);
		contentPane.add(btnRegister);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(9, 63, 96, 19);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);
		
		textFieldSurname = new JTextField();
		textFieldSurname.setColumns(10);
		textFieldSurname.setBounds(181, 63, 96, 19);
		contentPane.add(textFieldSurname);
		
		textFieldNickname = new JTextField();
		textFieldNickname.setColumns(10);
		textFieldNickname.setBounds(9, 130, 96, 19);
		contentPane.add(textFieldNickname);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(181, 130, 96, 19);
		contentPane.add(textFieldEmail);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(9, 218, 96, 21);
		contentPane.add(passwordField);
		
		passwordFieldRepeat = new JPasswordField();
		passwordFieldRepeat.setBounds(178, 218, 96, 21);
		contentPane.add(passwordFieldRepeat);
	}
}
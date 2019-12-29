package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import serverRdF.OTPManagerInterface;
import serverRdF.ServerInterface;
import services.ClientInterface;
import services.Notification;
import services.User;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class Registration {

	private JPanel contentPane;
	private JTextField textFieldName;
	private JTextField textFieldSurname;
	private JTextField textFieldNickname;
	private JTextField textFieldEmail;
	private JPasswordField passwordField;
	private JPasswordField passwordFieldRepeat;

	private static ServerInterface server;
	private static ClientInterface client;
	private User user;
	private static boolean isServer;
	private static OTPManagerInterface otp;
	private boolean admin;
	int posX = 0, posY = 0;

	private JFrame frame;

	public Registration(ServerInterface server, ClientInterface client, boolean admin) {
		this.server = server;
		this.client = client;
		this.admin = admin;
		initialize();
	}

	/**
	 * Create the frame.
	 */
	public void initialize() {
		if (InsubriaLogin.forServer) {
			InsubriaLogin.setReg(this);
		} else {
			MainPane.setRegistration(this);
		}

		frame = new JFrame("Registration");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setBounds(100, 100, 292, 350);
		frame.setVisible(true);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblName = new JLabel("Name");
		lblName.setForeground(Color.WHITE);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblName.setBounds(34, 40, 46, 13);
		contentPane.add(lblName);

		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setForeground(Color.WHITE);
		lblSurname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSurname.setBounds(147, 40, 83, 13);
		contentPane.add(lblSurname);

		JLabel lblNickname = new JLabel("Nickname");
		lblNickname.setForeground(Color.WHITE);
		lblNickname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNickname.setBounds(34, 107, 83, 13);
		contentPane.add(lblNickname);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmail.setBounds(147, 107, 46, 13);
		contentPane.add(lblEmail);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(34, 172, 70, 13);
		contentPane.add(lblPassword);

		JLabel lblRepeatPassword = new JLabel("Repeat");
		lblRepeatPassword.setForeground(Color.WHITE);
		lblRepeatPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRepeatPassword.setBounds(147, 172, 145, 13);
		contentPane.add(lblRepeatPassword);

		JButton btnRegister = new JButton("REGISTER");
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnRegister.setBackground(Color.GREEN);
		btnRegister.setForeground(Color.WHITE);
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					confirm();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnRegister.setBounds(34, 245, 209, 21);
		contentPane.add(btnRegister);

		textFieldName = new JTextField();
		textFieldName.setForeground(Color.BLACK);
		textFieldName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldName.setBounds(34, 63, 96, 19);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);

		textFieldSurname = new JTextField();
		textFieldSurname.setForeground(Color.BLACK);
		textFieldSurname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldSurname.setColumns(10);
		textFieldSurname.setBounds(147, 63, 96, 19);
		contentPane.add(textFieldSurname);

		textFieldNickname = new JTextField();
		textFieldNickname.setForeground(Color.BLACK);
		textFieldNickname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldNickname.setColumns(10);
		textFieldNickname.setBounds(34, 130, 96, 19);
		contentPane.add(textFieldNickname);

		textFieldEmail = new JTextField();
		textFieldEmail.setForeground(Color.BLACK);
		textFieldEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(147, 130, 96, 19);
		contentPane.add(textFieldEmail);

		passwordField = new JPasswordField();
		passwordField.setForeground(Color.BLACK);
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passwordField.setBounds(34, 195, 96, 21);
		contentPane.add(passwordField);

		passwordFieldRepeat = new JPasswordField();
		passwordFieldRepeat.setForeground(Color.BLACK);
		passwordFieldRepeat.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passwordFieldRepeat.setBounds(147, 195, 96, 21);
		contentPane.add(passwordFieldRepeat);

		JButton btnBack = new JButton("<");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				MainPane.visible();
			}
		});
		btnBack.setBackground(Color.RED);
		btnBack.setForeground(Color.WHITE);
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnBack.setBounds(34, 276, 58, 21);
		contentPane.add(btnBack);

		frame.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				posX = e.getX();
				posY = e.getY();
			}
		});

		frame.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent evt) {
				frame.setLocation(evt.getXOnScreen() - posX, evt.getYOnScreen() - posY);
			}
		});
	}

	public void confirm() throws IOException {
		if (!(passwordFieldRepeat.getText().equals(passwordField.getText())))
			Notification.notify("ERROR", "password");

		else if (!(textFieldName.getText().equals("") || textFieldSurname.getText().equals("")
				|| textFieldNickname.getText().equals("") || textFieldEmail.getText().equals("")
				|| passwordField.getText().equals("") || passwordFieldRepeat.getText().equals(""))) {
			if (!server.checkEMail(textFieldEmail.getText())) {
				Notification.notify("ERROR", "email already registered");

			} else if (!server.checkNickname(textFieldNickname.getText())) {
				Notification.notify("ERROR", "nickname already exist");
			} else {
				String nameStr = textFieldName.getText();
				String surnameStr = textFieldSurname.getText();
				String nickStr = textFieldNickname.getText();
				String mailStr = textFieldEmail.getText();
				String passwordStr = passwordField.getText();
				user = new User(mailStr, passwordStr, nameStr, surnameStr, nickStr);
				//System.out.println(user.getEmail());
				// AGGIUNGERE TRY CATCH CON EMAIL NON ESISTENTE ECC
				otp = server.signUp(user, client, admin);
				OTPRegistration otpPane = new OTPRegistration();
				frame.dispose();
			}
		} else {
			Notification.notify("ERROR", "compile all field");
		}
	}

	public void back() throws IOException {
		if (!isServer) {
			HostNameConnection wp = new HostNameConnection();
		} else {
			InsubriaLogin ilc = new InsubriaLogin();
		}
	}

	public static void setServer(boolean server) {
		isServer = server;
	}

	public void setServer(ServerInterface server) {
		this.server = server;
	}

	public void setClient(ClientInterface client) {
		this.client = client;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public static void setOTP(OTPRegistration otpp) {
		otpp.setClient(client);
		otpp.setServer(server);
		otpp.setOtp(otp);
	}


	public void notifyIllegalEmailAddress() {
		Notification.notify("Errore", "L'indirizzo email inserito non\nè disponibile o non esiste.");
	}
}

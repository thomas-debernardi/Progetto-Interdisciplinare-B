package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import adminRdF.AdminRdf;
import gui.TabPane;
import playerRdF.PlayerRdf;
import serverRdF.Server;
import services.AdminChecker;
import services.Client;
import services.ClientImplementation;
import services.Login;
import services.Notification;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class MainPane implements MouseListener {

	private static JFrame frame;
	private JTextField textFieldEmail;
	private JPasswordField passwordField;

	private static Server server;
	private static Client client;
	private static boolean admin;
	private static boolean isServer = false;
	int posX = 0, posY = 0;

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
		if (!InsubriaLogin.forServer) {
			WelcomePane.setController(this);
			if (server == null) {
				InsubriaLogin.setController(this);
			}
		} else
			InsubriaLogin.setController(this);
		try {
			client = new ClientImplementation();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		frame = new JFrame("MAIN PANE");
		frame.setUndecorated(true);
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.GRAY);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setBounds(100, 100, 365, 228);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmail.setBounds(22, 29, 46, 13);
		frame.getContentPane().add(lblEmail);

		textFieldEmail = new JTextField();
		textFieldEmail.setForeground(Color.BLACK);
		textFieldEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldEmail.setBounds(22, 52, 192, 19);
		frame.getContentPane().add(textFieldEmail);
		textFieldEmail.setColumns(10);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(237, 29, 96, 13);
		frame.getContentPane().add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setForeground(Color.BLACK);
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passwordField.setBounds(237, 52, 103, 19);
		frame.getContentPane().add(passwordField);

		JButton btnLogin = new JButton("LOGIN");
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setBackground(Color.GREEN);
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 18));
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
		btnLogin.setBounds(22, 105, 318, 21);
		frame.getContentPane().add(btnLogin);

		JLabel lblDontHaveAn = new JLabel("Don't have an account?");
		lblDontHaveAn.setForeground(Color.WHITE);
		lblDontHaveAn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDontHaveAn.setBounds(22, 141, 192, 13);
		frame.getContentPane().add(lblDontHaveAn);

		JButton btnRegister = new JButton("REGISTER");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					register();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnRegister.setBackground(Color.CYAN);
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnRegister.setBounds(214, 136, 126, 21);
		frame.getContentPane().add(btnRegister);

		JLabel lblForgotPassword = new JLabel("Forgot password?");
		lblForgotPassword.setForeground(Color.WHITE);
		lblForgotPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblForgotPassword.setBounds(237, 81, 126, 13);
		frame.getContentPane().add(lblForgotPassword);

		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBackground(Color.RED);
		btnExit.setForeground(Color.WHITE);
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnExit.setBounds(261, 188, 78, 21);
		frame.getContentPane().add(btnExit);

		lblForgotPassword.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					reset();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		frame.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				posX = e.getX();
				posY = e.getY();
			}
		});

		frame.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent evt) {
				// sets frame position when mouse dragged
				frame.setLocation(evt.getXOnScreen() - posX, evt.getYOnScreen() - posY);
			}
		});

	}

	public void login() throws Exception {
		String email = textFieldEmail.getText();
		String password = passwordField.getText();
		if (email.equals("")) {
			Notification.notify("ERRORE", "You didn't insert an email addres", false);
		}
		if (password.equals("")) {
			Notification.notify("ERRORE", "You didn't insert a password", false);
		}
		if (!(email.equals("") || password.equals(""))) {
			Login login = new Login(email, password);
			int result = server.signIn(login, client, admin);
			if (result < 0) {
				Notification.notify("ERROR", "Incorrect email or password", true);
			} else if (result == 0) {
				if (!isServer) {
					TabPane tpc = new TabPane();
					frame.dispose();
				} else {
					HostView hv = new HostView();
					frame.dispose();
				}
			} else {
				Notification.notify("ERROR",
						"YOU HAVE TO USE THE OTHER PLATFORM", true);
			}
		}
	}

	public void register() throws IOException {
		InsubriaLogin.forServer = false;
		Registration r = new Registration(server, client, admin);
		frame.setVisible(false);
	}
	
	public static void visible() {
		frame.setVisible(true);
	}

	public static void setRegistration(Registration registration) {
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

	public static void setArgs(TabPane tb) {
		tb.setClient(client);
		tb.setServer(server);
		tb.setAdmin(admin);
	}

	public void reset() throws IOException {
		ForgottenPassword fp = new ForgottenPassword();
	}
	
    public static void setResetPanel(ForgottenPassword f){
        f.setClient(client);
        f.setServer(server);
    }

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}

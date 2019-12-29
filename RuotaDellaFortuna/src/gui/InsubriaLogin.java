package gui;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.DBManager;
import serverRdF.ServerInterface;
import serverRdF.Server;
import services.ClientInterface;
import services.ClientImplementation;
import services.EmailManager;
import services.Notification;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextPane;

public class InsubriaLogin {

	private JPanel contentPane;
	private JTextField textFieldEmail;
	private JPasswordField passwordField;
	private EmailManager emailManager;
    private static DBManager dbManager;
    private static Registry registry;
    private static ServerInterface server;
    private static ClientInterface client;
    public static boolean forServer = false;
    private JFrame frame;
    private JButton btnExit;
    
    int posX = 0, posY = 0;
   	
    public InsubriaLogin() {
		initialize();
	}
    
	/**
	 * Create the frame.
	 */
	public void initialize() {
		
		
		frame = new JFrame("RdF Server");
		frame.setUndecorated(true);
		frame.setBackground(Color.GRAY);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 263, 214);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		frame.getContentPane().setLayout(null);
		contentPane.setLayout(null);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmail.setBounds(10, 62, 65, 13);
		frame.getContentPane().add(lblEmail);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldEmail.setBounds(83, 59, 160, 19);
		frame.getContentPane().add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(10, 100, 71, 13);
		frame.getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passwordField.setBounds(83, 97, 160, 19);
		frame.getContentPane().add(passwordField);
		
		JButton btnSend = new JButton("SEND");
		btnSend.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnSend.setForeground(Color.WHITE);
		btnSend.setBackground(Color.GREEN);
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
		btnSend.setBounds(10, 143, 176, 21);
		frame.getContentPane().add(btnSend);
		
		btnExit = new JButton("X");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBackground(Color.RED);
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnExit.setBounds(196, 143, 47, 21);
		contentPane.add(btnExit);
		
		JTextPane lbl = new JTextPane();
		lbl.setForeground(Color.WHITE);
		lbl.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbl.setEditable(false);
		lbl.setBackground(Color.GRAY);
		lbl.setText("Insert your uninsubria email, this email will be used to automatically send messages to users.");
		lbl.setBounds(10, 10, 243, 36);
		contentPane.add(lbl);
		
		
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
	
	
	 public void loginManager() throws IOException, RemoteException {
			String email = textFieldEmail.getText();
	        @SuppressWarnings("deprecation")
			String password = passwordField.getText();
	        boolean logged = EmailManager.logIntoAccount(email, password);
	        if (logged) {
	            forServer = true;
	            emailManager = EmailManager.createEmailManager(email, password);
	            server = new Server(dbManager, emailManager);
	            client = new ClientImplementation();
	            if (dbManager.getAnyAdmin()) {
					MainPane mp = new MainPane();
	            	frame.dispose();
	            } else {
					Registration reg = new Registration(server, client, true);
	                frame.dispose();
	            }
	        } else {
	            Notification.notify("ERROR", "Incorrect email or password");
	        }
	    }
	
	public static void setDbManager(DBManager db) {
        dbManager = db;
    }

    public static ServerInterface getServer() {
        return server;
    }

    public static Registry getRegistry() {
        return registry;
    }

    @SuppressWarnings("static-access")
	public static void setReg(Registration r) {
        r.setServer(true);
        r.setServer(server);
        r.setAdmin(true);
        r.setClient(client);
    }

    public static void setController(MainPane mp) {
    	mp.setClient(client);
    	mp.setServer(server);
    	mp.setAdmin(true);
    	mp.setIsServer(true);
    }

    public static void setHost(HostView host) {
        host.setServer(server);
        host.setR(registry);
    }
}

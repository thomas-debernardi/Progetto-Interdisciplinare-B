package ServerRDF;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Database.DBManager;
import GUI.InsubriaLogin;
import Services.Notification;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class ServerRdf extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldUser;
	private JPasswordField passwordField;
	private JTextField textFieldHostname;
	private JTextField textFieldPort;
    private DBManager manager;
	static ServerRdf frame = new ServerRdf();
	int posX = 0, posY = 0;



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
	public ServerRdf() {
		setTitle("RdF SERVER");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 295, 190);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUser = new JLabel("User");
		lblUser.setForeground(Color.WHITE);
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUser.setBounds(10, 17, 108, 13);
		contentPane.add(lblUser);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(10, 46, 108, 13);
		contentPane.add(lblPassword);
		
		JLabel lblHostname = new JLabel("Hostname");
		lblHostname.setForeground(Color.WHITE);
		lblHostname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblHostname.setBounds(10, 75, 108, 13);
		contentPane.add(lblHostname);
		
		JLabel lblPort = new JLabel("Port");
		lblPort.setForeground(Color.WHITE);
		lblPort.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPort.setBounds(10, 104, 108, 13);
		contentPane.add(lblPort);
		
		textFieldUser = new JTextField();
		textFieldUser.setForeground(Color.BLACK);
		textFieldUser.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldUser.setBounds(145, 13, 126, 19);
		contentPane.add(textFieldUser);
		textFieldUser.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setForeground(Color.BLACK);
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passwordField.setBounds(145, 42, 126, 19);
		contentPane.add(passwordField);
		
		textFieldHostname = new JTextField();
		textFieldHostname.setText("localhost");
		textFieldHostname.setForeground(Color.BLACK);
		textFieldHostname.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldHostname.setBounds(145, 71, 126, 19);
		contentPane.add(textFieldHostname);
		textFieldHostname.setColumns(10);
		
		textFieldPort = new JTextField();
		textFieldPort.setText("5432");
		textFieldPort.setForeground(Color.BLACK);
		textFieldPort.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldPort.setBounds(145, 100, 126, 19);
		contentPane.add(textFieldPort);
		textFieldPort.setColumns(10);
		
		JButton btnStart = new JButton("START");
		btnStart.setBackground(Color.GREEN);
		btnStart.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					login();
				} catch (IOException e1) {
					
				}
				
			}
		});
		btnStart.setBounds(10, 129, 192, 21);
		contentPane.add(btnStart);
		
		JButton btnExit = new JButton("X");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBackground(Color.RED);
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnExit.setBounds(212, 129, 59, 21);
		contentPane.add(btnExit);
		
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				posX = e.getX();
				posY = e.getY();
			}
		});

		addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent evt) {
				// sets frame position when mouse dragged
				setLocation(evt.getXOnScreen() - posX, evt.getYOnScreen() - posY);
			}
		});
	}
	
    public void login() throws IOException {
        String user = textFieldUser.getText();
        String password = passwordField.getText();
        String hostname = textFieldHostname.getText();
        String port = textFieldPort.getText();
        try {
            String url = hostname + ":" + port;
            manager = DBManager.createDBManager(url, user, password);
            InsubriaLogin.setDbManager(manager);
            InsubriaLogin ilc = new InsubriaLogin();     
            Notification.notify("OK", "", false);
            frame.dispose();
        } catch (SQLException e) {
        	System.out.println(e);
            Notification.notify("ERROR", e.toString(), true);
        }

    }
}
package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Services.Notification;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Font;

public class DatabaseBuilder {

	private JPanel contentPane;
	private JTextField textFieldHost;
	private JTextField textFieldPort;
	private JTextField textFieldUsername;
	private final String PATH = "src/Database/Database_SQL.sql";
	private StringBuilder sb;
	private Statement statement;
	private Scanner scan;
	private JFrame frame;
	private String host;
	private String port;
	private String user;
	private boolean dbCreated = false;
	private JPasswordField textFieldPassword;
	int posX=0, posY=0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DatabaseBuilder dp = new DatabaseBuilder();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DatabaseBuilder() {
		initialize();
	}

	/**
	 * Create the frame.
	 */
	public void initialize() {
		frame = new JFrame("DATABASE BUILDER");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 310, 249);
		frame.setBackground(Color.GRAY);
		frame.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setForeground(Color.WHITE);
		frame.setVisible(true);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);

		textFieldHost = new JTextField();
		textFieldHost.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldHost.setText("localhost");
		textFieldHost.setBounds(28, 45, 96, 19);
		contentPane.add(textFieldHost);
		textFieldHost.setColumns(10);

		JLabel lblDbHostName = new JLabel("DB Host");
		lblDbHostName.setForeground(Color.WHITE);
		lblDbHostName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDbHostName.setBounds(28, 22, 135, 13);
		contentPane.add(lblDbHostName);

		textFieldPort = new JTextField();
		textFieldPort.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldPort.setText("5432");
		textFieldPort.setBounds(173, 45, 96, 19);
		contentPane.add(textFieldPort);
		textFieldPort.setColumns(10);

		JLabel lblPort = new JLabel("PORT");
		lblPort.setForeground(Color.WHITE);
		lblPort.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPort.setBounds(173, 22, 80, 13);
		contentPane.add(lblPort);

		textFieldUsername = new JTextField();
		textFieldUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldUsername.setBounds(28, 117, 96, 19);
		contentPane.add(textFieldUsername);
		textFieldUsername.setColumns(10);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsername.setBounds(28, 94, 80, 13);
		contentPane.add(lblUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(173, 94, 135, 13);
		contentPane.add(lblPassword);

		JButton btnCreate = new JButton("CREATE");
		btnCreate.setForeground(Color.WHITE);
		btnCreate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCreate.setBackground(Color.GREEN);
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				host = textFieldHost.getText();
				port = textFieldPort.getText();
				user = textFieldUsername.getText();
				createTable(host, port, user, textFieldPassword.getText());
			}
		});
		btnCreate.setBounds(28, 163, 241, 21);
		contentPane.add(btnCreate);

		textFieldPassword = new JPasswordField();
		textFieldPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldPassword.setBounds(173, 117, 96, 19);
		contentPane.add(textFieldPassword);
		
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

	private void createTable(String host, String port, String user, String password) {
		try (Connection connection = DriverManager.getConnection("jdbc:postgresql://" + host + ":" + port + "/", user,
				password)) {
			try {
				Statement stmt = connection.createStatement();
				stmt.execute("CREATE DATABASE dbrdf");
			} catch (SQLException e) {
				System.out.println("DATABASE GIA' ESISTENTE");
				dbCreated = true;
			} finally {
				if (!dbCreated) {
					Connection conn = DriverManager.getConnection("jdbc:postgresql://" + host + ":" + port + "/dbrdf",
							user, password);
					statement = conn.createStatement();
					File sqlCreator = new File(PATH);
					sb = new StringBuilder("");
					scan = new Scanner(sqlCreator);
					while (scan.hasNextLine()) {
						sb.append(scan.nextLine());
					}
					String s = sb.toString();
					statement.executeUpdate(s);
					Notification.notify("OK", "Database created", false);
					dbCreated = true;
					conn.close();
				} else {
					Notification.notify("DATABASE ALREADY EXIST", "", true);
				}
			}
		} catch (SQLException ex) {
			Notification.notify("ERROR", "Acces error", true);
			ex.printStackTrace();
		} catch (FileNotFoundException ex) {
			Notification.notify("ERORR", "FILE SQL NOT FOUND", true);
			ex.printStackTrace();
		}
	}
}

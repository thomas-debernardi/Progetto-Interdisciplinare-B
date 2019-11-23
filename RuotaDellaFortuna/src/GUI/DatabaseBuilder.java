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
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class DatabaseBuilder extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldHost;
	private JTextField textFieldPort;
	private JTextField textFieldUsername;
    private final String PATH = "src/Database/Database_SQL.sql";
    private StringBuilder sb;
    private Statement statement;
    private Scanner scan;



	
		private String host;
	    private String port;
	    private String user;
	    private boolean dbCreated = false;
	    private JPasswordField textFieldPassword;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DatabaseBuilder frame = new DatabaseBuilder();
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
	public DatabaseBuilder() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 507, 353);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFieldHost = new JTextField();
		textFieldHost.setText("localhost");
		textFieldHost.setBounds(28, 45, 96, 19);
		contentPane.add(textFieldHost);
		textFieldHost.setColumns(10);
		
		JLabel lblDbHostName = new JLabel("DB Host Name");
		lblDbHostName.setBounds(28, 22, 96, 13);
		contentPane.add(lblDbHostName);
		
		textFieldPort = new JTextField();
		textFieldPort.setText("5432");
		textFieldPort.setBounds(134, 45, 96, 19);
		contentPane.add(textFieldPort);
		textFieldPort.setColumns(10);
		
		JLabel lblPort = new JLabel("PORT");
		lblPort.setBounds(134, 22, 80, 13);
		contentPane.add(lblPort);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(28, 117, 96, 19);
		contentPane.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(28, 94, 80, 13);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(134, 94, 46, 13);
		contentPane.add(lblPassword);
		
		JButton btnCreate = new JButton("CREATE");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				host = textFieldHost.getText();
		        port = textFieldPort.getText();
		        user = textFieldUsername.getText();
		        createTable(host, port, user, textFieldPassword.getText());
			}
		});
		btnCreate.setBounds(77, 156, 85, 21);
		contentPane.add(btnCreate);
		
		textFieldPassword = new JPasswordField();
		textFieldPassword.setBounds(134, 117, 96, 19);
		contentPane.add(textFieldPassword);
		
		
	}
	
	private void createTable(String host, String port, String user, String password) {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://" + host + ":" + port + "/", user, password)) {
            try {
                Statement stmt = connection.createStatement();
                stmt.execute("CREATE DATABASE dbrdf");
            } catch (SQLException e) {
                System.out.println("DATABASE GIA' ESISTENTE");
            } finally {
                if (!dbCreated) {
                    Connection conn = DriverManager.getConnection("jdbc:postgresql://" + host + ":" + port + "/dbrdf", user, password);
                    statement = conn.createStatement();
                    File sqlCreator = new File(PATH);
                    sb = new StringBuilder("");
                    scan = new Scanner(sqlCreator);
                    while (scan.hasNextLine()) {
                        sb.append(scan.nextLine());
                    }
                    String s = sb.toString();
                    statement.executeUpdate(s);
                    Notification.notify("Successo", "Il database è stato creato con successo.", false);
                    dbCreated = true;
                    conn.close();
                } else {
                    Notification.notify("Database già creato", "Il database è già stato creato", true);
                }
            }
        } catch(SQLException ex){
                Notification.notify("Errore", "Non è stato possibile eseguire l'accesso.", true);
                ex.printStackTrace();
        } catch(FileNotFoundException ex){
                Notification.notify("Errore", "Non ho trovato il file per la creazione del database", true);
                ex.printStackTrace();
        }
	}
}

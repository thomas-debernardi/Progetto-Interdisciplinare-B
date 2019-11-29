package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Game.RemoteMatch;
import Server.Server;
import Services.Client;
import Services.MatchData;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class GameWheel {
	private JFrame frame;
	private JPanel contentPane;
	private JTextField text_wheel;
	private JTextField player1_name;
	private JTextField player2_name;
	private JTextField player3_name;
	private JTextField player_2_points;
	private JTextField player_one_points;
	private JTextField player_3_points;
	private Label safe1;
	private Label safe2;
	private Label safe3;
	private JTextField safe_player1;
	private JTextField safe_player2;
	private JTextField safe_player3;
	private JButton btnBuyVocal;
	private JButton button_solution;
    private static Client client;
    private static Server server;
    private static RemoteMatch match;
    private static MatchData matchData;
	/**
	 * Create the frame.
	 */
	public GameWheel(RemoteMatch match, Client client) {
		this.match = match;
		this.client = client;
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		text_wheel = new JTextField();
		text_wheel.setText("Here goes the wheel Result");
		text_wheel.setBounds(7, 44, 124, 57);
		contentPane.add(text_wheel);
		text_wheel.setColumns(10);
		
		JButton btnNewButton = new JButton("SPIN");
		btnNewButton.setBounds(7, 12, 114, 25);
		contentPane.add(btnNewButton);
		
		player1_name = new JTextField();
		player1_name.setText("Player n 1 (nome)");
		player1_name.setBounds(7, 113, 124, 19);
		contentPane.add(player1_name);
		player1_name.setColumns(10);
		
		player2_name = new JTextField();
		player2_name.setText("Player n 2 (nome)");
		player2_name.setBounds(161, 113, 124, 19);
		contentPane.add(player2_name);
		player2_name.setColumns(10);
		
		player3_name = new JTextField();
		player3_name.setText("Player n 3 (nome)");
		player3_name.setBounds(314, 113, 124, 19);
		contentPane.add(player3_name);
		player3_name.setColumns(10);
		
		player_2_points = new JTextField();
		player_2_points.setBounds(161, 169, 124, 19);
		contentPane.add(player_2_points);
		player_2_points.setColumns(10);
		
		player_one_points = new JTextField();
		player_one_points.setColumns(10);
		player_one_points.setBounds(7, 169, 124, 19);
		contentPane.add(player_one_points);
		
		player_3_points = new JTextField();
		player_3_points.setColumns(10);
		player_3_points.setBounds(314, 169, 124, 19);
		contentPane.add(player_3_points);
		
		Label points1 = new Label("Points");
		points1.setBounds(17, 138, 68, 21);
		contentPane.add(points1);
		
		Label points2 = new Label("Points");
		points2.setBounds(171, 138, 68, 21);
		contentPane.add(points2);
		
		Label points3 = new Label("Points");
		points3.setBounds(324, 138, 68, 21);
		contentPane.add(points3);
		
		safe1 = new Label("Safe");
		safe1.setBounds(17, 194, 68, 21);
		contentPane.add(safe1);
		
		safe2 = new Label("Safe");
		safe2.setBounds(171, 194, 68, 21);
		contentPane.add(safe2);
		
		safe3 = new Label("Safe");
		safe3.setBounds(324, 194, 68, 21);
		contentPane.add(safe3);
		
		safe_player1 = new JTextField();
		safe_player1.setBounds(7, 221, 124, 19);
		contentPane.add(safe_player1);
		safe_player1.setColumns(10);
		
		safe_player2 = new JTextField();
		safe_player2.setColumns(10);
		safe_player2.setBounds(161, 221, 124, 19);
		contentPane.add(safe_player2);
		
		safe_player3 = new JTextField();
		safe_player3.setColumns(10);
		safe_player3.setBounds(314, 221, 124, 19);
		contentPane.add(safe_player3);
		
		btnBuyVocal = new JButton("BUY VOCAL");
		btnBuyVocal.setBounds(158, 60, 114, 25);
		contentPane.add(btnBuyVocal);
		
		button_solution = new JButton("SOLUTION");
		button_solution.setBounds(314, 60, 114, 25);
		contentPane.add(button_solution);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					match.leaveMatchAsPlayer(client);
					frame.dispose();
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnExit.setBounds(314, 14, 85, 21);
		contentPane.add(btnExit);
	}
}

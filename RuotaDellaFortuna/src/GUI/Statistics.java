package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

public class Statistics extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Statistics frame = new Statistics();
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
	public Statistics() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblStatistics = new JLabel("Statistics");
		lblStatistics.setBounds(10, 10, 68, 13);
		contentPane.add(lblStatistics);
		
		JButton btnRecord = new JButton("Record");
		btnRecord.setBounds(10, 33, 154, 86);
		contentPane.add(btnRecord);
		
		JButton btnMatchesStatistics = new JButton("Matches Statistics");
		btnMatchesStatistics.setBounds(174, 33, 154, 86);
		contentPane.add(btnMatchesStatistics);
		
		JButton btnSearchUser = new JButton("Search User");
		btnSearchUser.setBounds(10, 129, 154, 86);
		contentPane.add(btnSearchUser);
		
		JButton btnBack = new JButton("BACK");
		btnBack.setBounds(235, 190, 85, 21);
		contentPane.add(btnBack);
	}

}

package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class PlayerMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlayerMenu frame = new PlayerMenu();
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
	public PlayerMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 419, 333);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnWatchAMatch = new JButton("Create a Match");
		btnWatchAMatch.setBounds(10, 10, 183, 119);
		contentPane.add(btnWatchAMatch);
		
		JButton btnShowMatches = new JButton("Show Matches");
		btnShowMatches.setBounds(215, 10, 183, 119);
		contentPane.add(btnShowMatches);
		
		JButton btnShowStatistics = new JButton("Show Statistics");
		btnShowStatistics.setBounds(10, 139, 183, 119);
		contentPane.add(btnShowStatistics);
		
		JButton btnAccount = new JButton("Account");
		btnAccount.setBounds(215, 139, 183, 119);
		contentPane.add(btnAccount);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.setBounds(10, 268, 390, 21);
		contentPane.add(btnExit);
	}
}

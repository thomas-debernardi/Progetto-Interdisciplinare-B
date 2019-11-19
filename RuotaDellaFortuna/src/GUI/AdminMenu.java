package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class AdminMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminMenu frame = new AdminMenu();
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
	public AdminMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 492, 516);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnWatchAMatch = new JButton("Watch a Match");
		btnWatchAMatch.setBounds(23, 83, 170, 112);
		contentPane.add(btnWatchAMatch);
		
		JButton btnAddMisteryPhrases = new JButton("Add Mistery Phrases");
		btnAddMisteryPhrases.setBounds(203, 83, 170, 112);
		contentPane.add(btnAddMisteryPhrases);
		
		JButton btnStatistics = new JButton("Statistics");
		btnStatistics.setBounds(23, 209, 170, 112);
		contentPane.add(btnStatistics);
		
		JButton btnAccountSettings = new JButton("Account Settings");
		btnAccountSettings.setBounds(203, 205, 170, 112);
		contentPane.add(btnAccountSettings);
	}
}

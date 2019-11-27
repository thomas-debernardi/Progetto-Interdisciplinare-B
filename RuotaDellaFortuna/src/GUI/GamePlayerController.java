package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;

public class GamePlayerController {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Create the application.
	 */
	public GamePlayerController() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1287, 667);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		textField = new JTextField();
		textField.setToolTipText("Insert the solution");
		textField.setBounds(44, 486, 627, 29);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnQuit = new JButton("QUIT");
		btnQuit.setBounds(1178, 599, 85, 21);
		frame.getContentPane().add(btnQuit);
		
		JButton btnSpin = new JButton("SPIN");
		btnSpin.setBounds(278, 581, 85, 21);
		frame.getContentPane().add(btnSpin);
		
		JLabel lblPlayer = new JLabel("Player 1");
		lblPlayer.setBounds(895, 47, 46, 13);
		frame.getContentPane().add(lblPlayer);
		
		JLabel lblPlayer_1 = new JLabel("Player 2");
		lblPlayer_1.setBounds(895, 211, 46, 13);
		frame.getContentPane().add(lblPlayer_1);
		
		JLabel lblPlayer_2 = new JLabel("Player 3");
		lblPlayer_2.setBounds(895, 393, 46, 13);
		frame.getContentPane().add(lblPlayer_2);
	}
}

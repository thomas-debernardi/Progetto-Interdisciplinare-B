package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;

import Game.RemoteMatch;
import Services.Client;

import java.awt.Frame;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;

public class Game {

	private JFrame frame;
	private static boolean isObserver;
	private int wheelResult;
	private static RemoteMatch match;
	private Client client;
	private boolean wheelButtonPressed;
	private boolean vowelButtonPressed;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Game window = new Game();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Game() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.GRAY);
		frame.setBackground(Color.GRAY);
		frame.setBounds(100, 100, 1487, 844);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btnSpin = new JButton("SPIN");
		btnSpin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wheelSpin();
			}
		});
		btnSpin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSpin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnSpin.setBackground(Color.GREEN);
		btnSpin.setForeground(Color.WHITE);
		btnSpin.setBounds(392, 371, 85, 21);
		frame.getContentPane().add(btnSpin);
		frame.setLocationRelativeTo(null);

		// FULLSCREEN
		// GraphicsEnvironment graphics =
		// GraphicsEnvironment.getLocalGraphicsEnvironment();
		// GraphicsDevice device = graphics.getDefaultScreenDevice();
		// frame.setUndecorated(false);
		// device.setFullScreenWindow(frame);

	}

	public void wheelSpin() {
		Thread t = new Thread() {
			public void run() {
				Wheel wheel = new Wheel();
				try {
					Thread.sleep(4 * 1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				wheel.dispose();
			}
		}; 				t.start();


		// wheelResult = match.wheelSpin();
		// wheelButtonPressed = true;

	}
}

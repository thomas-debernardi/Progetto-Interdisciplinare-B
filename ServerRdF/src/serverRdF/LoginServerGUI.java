package serverRdF;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.concurrent.Semaphore;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class LoginServerGUI extends JFrame {
	
	private RealServer server;
	private Semaphore lock;
	JFrame frame;
	
	public LoginServerGUI(RealServer server,Semaphore lock) {
		this.server = server;
		this.lock=lock;
		initialize();
	}

	/**
	 * Create the frame.
	 */
	public void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 450, 300);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		frame.setContentPane(contentPane);
	}

}

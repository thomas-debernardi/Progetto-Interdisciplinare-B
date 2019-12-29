package services;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

public class Notification extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public Notification() {
		
	} 
	
	
	/**
	 * Questo metodod permette di generare un jpanel contenente:
	 * 
	 * @param title titolo del jpanel
	 * @param msg messaggio del jpanel
	 */
	public static void notify(String title, String msg) {
		String message = msg;
		String header = title;
		JFrame frame = new JFrame();
		frame.setResizable(false);
		frame.setUndecorated(true);
		frame.setSize(300,125);
		frame.getContentPane().setLayout(new GridBagLayout());
		frame.getContentPane().setBackground(Color.GRAY);
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = 1.0f;
		constraints.weighty = 1.0f;
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.fill = GridBagConstraints.BOTH;
		JLabel headingLabel = new JLabel(header);
		headingLabel.setForeground(Color.WHITE);
		headingLabel.setOpaque(false);
		frame.getContentPane().add(headingLabel, constraints);
		constraints.gridx++;
		constraints.weightx = 0f;
		constraints.weighty = 0f;
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.NORTH;
		JButton cloesButton = new JButton(new AbstractAction("x") {
	        /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
	        public void actionPerformed(final ActionEvent e) {
	               frame.dispose();
	        }
	});
		cloesButton.setBackground(Color.RED);
		cloesButton.setMargin(new Insets(1, 4, 1, 4));
		cloesButton.setFocusable(false);
		frame.getContentPane().add(cloesButton, constraints);
		constraints.gridx = 0;
		constraints.gridy++;
		constraints.weightx = 1.0f;
		constraints.weighty = 1.0f;
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.fill = GridBagConstraints.BOTH;
		JLabel messageLabel = new JLabel("<HtMl>"+message);
		messageLabel.setForeground(Color.WHITE);
		frame.getContentPane().add(messageLabel, constraints);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
		Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();// size of the screen
		Insets toolHeight = Toolkit.getDefaultToolkit().getScreenInsets(frame.getGraphicsConfiguration());// height of the task bar
		frame.setLocation(scrSize.width - frame.getWidth(), scrSize.height - toolHeight.bottom - frame.getHeight());
		
		new Thread(){
		      @Override
		      public void run() {
		           try {
		                  Thread.sleep(3000); // time after which pop up will be disappeared.
		                  frame.dispose();
		           } catch (InterruptedException e) {
		                  e.printStackTrace();
		           }
		      };
		}.start();
		frame.setAlwaysOnTop(true);
	}	
}

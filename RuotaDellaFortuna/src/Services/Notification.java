package Services;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Notification extends JFrame {
	public Notification() {
		
	}
	
	public static void notify(String title, String msg, boolean error) {
		String message = msg;
		String header = title;
		JFrame frame = new JFrame();
		frame.setResizable(false);
		frame.setUndecorated(true);
		frame.setSize(300,125);
		frame.getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = 1.0f;
		constraints.weighty = 1.0f;
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.fill = GridBagConstraints.BOTH;
		JLabel headingLabel = new JLabel(header);
		//headingLabel .setIcon(headingIcon); // --- use image icon you want to be as heading image.
		headingLabel.setOpaque(false);
		frame.getContentPane().add(headingLabel, constraints);
		constraints.gridx++;
		constraints.weightx = 0f;
		constraints.weighty = 0f;
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.NORTH;
		JButton cloesButton = new JButton(new AbstractAction("x") {
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

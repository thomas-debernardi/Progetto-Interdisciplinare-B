package GUI;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JButton;

public class TabPane {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TabPane window = new TabPane();
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
	public TabPane() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1050, 683);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1034, 594);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("New tab", null, panel, null);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_1, null);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_2, null);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_3, null);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_4, null);
		
		JPanel panel_5 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_5, null);
		
		JButton btnRefresh = new JButton("REFRESH");
		btnRefresh.setBounds(333, 604, 85, 21);
		frame.getContentPane().add(btnRefresh);
		
		JButton btnCreateMatch = new JButton("CREATE MATCH");
		btnCreateMatch.setBounds(24, 604, 133, 21);
		frame.getContentPane().add(btnCreateMatch);
		

	}
}

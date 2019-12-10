package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Server.Server;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;

public class HostView {

	private JPanel contentPane;
	private Registry r;
	private Server server;
	private JLabel lblHost;
	int posX = 0, posY = 0;
	private JPanel panel;
	private JButton button;
	private JButton btnMinimize;

	public HostView() {
		initialize();
	}

	/**
	 * Create the frame.
	 */
	public void initialize() {
		JFrame frame = new JFrame("RdF SERVER");
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 406, 229);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		lblHost = new JLabel("HOST NAME");
		lblHost.setHorizontalAlignment(SwingConstants.CENTER);
		lblHost.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblHost.setForeground(Color.WHITE);
		contentPane.add(lblHost);

		panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));

		button = new JButton("EXIT");
		panel.add(button);
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button.setBackground(Color.RED);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		btnMinimize = new JButton("MINIMIZE");
		btnMinimize.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnMinimize.setBackground(Color.CYAN);
		panel.add(btnMinimize, BorderLayout.EAST);
		btnMinimize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setState(JFrame.ICONIFIED);
			}
		});

		InsubriaLoginController.setHost(this);
		try {
			takeAddress();
		} catch (Exception e) {
			e.printStackTrace();
		}

		frame.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				posX = e.getX();
				posY = e.getY();
			}
		});

		frame.addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent evt) {
				// sets frame position when mouse dragged
				frame.setLocation(evt.getXOnScreen() - posX, evt.getYOnScreen() - posY);
			}
		});

	}

	public void takeAddress() throws Exception {
		r = LocateRegistry.createRegistry(1099);
		r.rebind("SERVER", server);
		InetAddress address = null;
		try {
			address = InetAddress.getLocalHost();
			lblHost.setText("Hostname: " + address.getHostName());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	public Registry getR() {
		return r;
	}

	public Server getServer() {
		return server;
	}

	public void setR(Registry r) {
		this.r = r;
	}

	public void setServer(Server server) {
		this.server = server;
	}

}

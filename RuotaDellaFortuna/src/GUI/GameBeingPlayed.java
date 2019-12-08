package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;

import java.awt.Font;
import javax.swing.SwingConstants;

import Game.RemoteMatch;
import Server.Server;
import Services.AdminChecker;
import Services.Client;
import Services.CountryRender;
import Services.MatchData;
import Services.Notification;
import javafx.scene.control.ListCell;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;

public class GameBeingPlayed extends JDesktopPane{

	private JFrame frame;
	private JLabel lblPlayer;
	private JLabel lblPlayer_1;
	private JLabel lblPlayer_2;
	private JButton btnJoin;
	private JButton btnObserve;

	private Server server;
	private static Client client;
	private MatchData matchData;
	private static RemoteMatch match;
	public static boolean player;
	private JButton btnExit;
	private int posX = 0, posY = 0;

	/**
	 * Create the application.
	 */
	public GameBeingPlayed(Server server, Client client, MatchData matchData) {
		this.server = server;
		this.client = client;
		this.matchData = matchData;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setBackground(Color.GRAY);
		frame.setVisible(true);
		frame.setBounds(100, 100, 704, 98);
		frame.getContentPane().setLayout(new GridLayout(0, 6, 0, 0));
		

		lblPlayer = new JLabel("Player1");
		lblPlayer.setForeground(Color.WHITE);
		lblPlayer.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayer.setFont(new Font("Tahoma", Font.BOLD, 15));
		frame.getContentPane().add(lblPlayer);

		lblPlayer_1 = new JLabel("Player 2");
		lblPlayer_1.setForeground(Color.WHITE);
		lblPlayer_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayer_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		frame.getContentPane().add(lblPlayer_1);

		lblPlayer_2 = new JLabel("Player3");
		lblPlayer_2.setForeground(Color.WHITE);
		lblPlayer_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayer_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		frame.getContentPane().add(lblPlayer_2);

		btnJoin = new JButton("JOIN");
		btnJoin.setForeground(Color.WHITE);
		btnJoin.setBackground(Color.GREEN);
		btnJoin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(btnJoin);

		btnObserve = new JButton("OBSERVE");
		btnObserve.setBackground(Color.CYAN);
		btnObserve.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(btnObserve);
		
		
		
		if (AdminChecker.isIsAdmin())
			btnJoin.setEnabled(false);

		lblPlayer.setText(matchData.getPlayer1());
		lblPlayer_1.setText(matchData.getPlayer2());
		lblPlayer_2.setText(matchData.getPlayer3());
		
		btnExit = new JButton("CLOSE");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				CountryRender.setChosen(false);
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnExit.setBackground(Color.RED);
		frame.getContentPane().add(btnExit);
		btnJoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					player = true;
					match = server.joinMatch(client, matchData.getIdMatch());
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
				if (match == null) {
					Notification.notify("Notifica Partita", "Partita inesistente", true);
				} else {
					TabPaneController.creator = false;
					Game game = new Game(match, client);
					TabPaneController.setInvisible();

				}
			}
		});

		btnObserve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				player = false;
				try {
					match = server.observeMatch(client, matchData.getIdMatch());
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
				if (match == null) {
					Notification.notify("Notifica Partita", "Partita inesistente", true);
				} else {
					TabPaneController.creator = false;
					Game game = new Game(match, client);
					TabPaneController.setInvisible();
				}
			}
		});
		
		
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
		frame.setLocationRelativeTo(null);

	}

	protected void updateItem(MatchData item, boolean empty) {
		if (AdminChecker.isIsAdmin())
			btnJoin.setEnabled(false);

		lblPlayer.setText(item.getPlayer1());
		lblPlayer_1.setText(item.getPlayer2());
		lblPlayer_2.setText(item.getPlayer3());
		btnJoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					player = true;
					match = server.joinMatch(client, item.getIdMatch());
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
				if (match == null) {
					Notification.notify("Notifica Partita", "Partita inesistente", true);
				} else {
					TabPaneController.creator = false;
					Game game = new Game(match, client);
					TabPaneController.setInvisible();

				}
			}
		});

		btnObserve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				player = false;
				try {
					match = server.observeMatch(client, item.getIdMatch());
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
				if (match == null) {
					Notification.notify("Notifica Partita", "Partita inesistente", true);
				} else {
					TabPaneController.creator = false;
					Game game = new Game(match, client);
					TabPaneController.setInvisible();
				}
			}
		});
	}
	
	 private void setAviableLabel(boolean aviable) {
	        if (aviable) {
	            btnJoin.setEnabled(true);
	        } else {
	            btnJoin.setEnabled(false);
	        }
	    }
	
    public static void setGameControllerObserver(Game gpc) {
        gpc.setClient(client);
        gpc.setMatch(match);
        gpc.setObserver(!GameBeingPlayed.player);
    }
}

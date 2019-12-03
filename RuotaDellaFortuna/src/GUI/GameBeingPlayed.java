package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;

import Game.RemoteMatch;
import Server.Server;
import Services.AdminChecker;
import Services.Client;
import Services.MatchData;
import Services.Notification;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;

public class GameBeingPlayed {

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
		MatchData item;
		this.matchData = item;
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.GRAY);
		frame.setUndecorated(true);
		frame.setBounds(100, 100, 564, 115);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1, 5, 0, 0));

		lblPlayer = new JLabel("Player1");
		lblPlayer.setForeground(Color.WHITE);
		lblPlayer.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayer.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frame.getContentPane().add(lblPlayer);

		lblPlayer_1 = new JLabel("Player 2");
		lblPlayer_1.setForeground(Color.WHITE);
		lblPlayer_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayer_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frame.getContentPane().add(lblPlayer_1);

		lblPlayer_2 = new JLabel("Player3");
		lblPlayer_2.setForeground(Color.WHITE);
		lblPlayer_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayer_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frame.getContentPane().add(lblPlayer_2);

		btnJoin = new JButton("JOIN");
		btnJoin.setForeground(Color.WHITE);
		btnJoin.setBackground(Color.GREEN);
		btnJoin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(btnJoin);

		btnObserve = new JButton("OBSERVE");
		btnObserve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				player = false;
				try {
					match = server.observeMatch(client, item.getIdMatch());
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				if (match == null) {
					Notification.notify("Notifica Partita", "Partita inesistente", true);
				} else {
					TabPaneController.creator = false;
					//APRIRE NUOVA FINESTRA
					FXMLLoader loader = new FXMLLoader(
							getClass().getClassLoader().getResource("game_player_pane.fxml"));
					Parent root = null;
					try {
						root = loader.load();
					} catch (IOException e) {
						e.printStackTrace();
					}
					Stage primaryStage = new Stage();
					Scene scene = new Scene(root);
					primaryStage.setScene(scene);
					primaryStage.show();
					ApplicationCloser.setCloser(primaryStage);
					Stage oldStage = (Stage) observeButton.getScene().getWindow();
					oldStage.close();
				}
			}
			}
		});
		btnObserve.setBackground(Color.CYAN);
		btnObserve.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frame.getContentPane().add(btnObserve);
		
		if (AdminChecker.isIsAdmin())
			btnJoin.setVisible(false);

		lblPlayer.setText(item.getPlayer1());
		lblPlayer_1.setText(item.getPlayer2());
		lblPlayer_2.setText(item.getPlayer3());

		btnJoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					player = true;
					match = server.joinMatch(client, item.getIdMatch());
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				if (match == null) {
					Notification.notify("Notifica Partita", "Partita inesistente", true);
				} else {
					TabPaneController.creator = false;
					//APRIRE SCHERMATA PARTITA
					FXMLLoader loader = new FXMLLoader(
							getClass().getClassLoader().getResource("game_player_pane.fxml"));
					Parent root = null;
					
					Stage primaryStage = new Stage();
					Scene scene = new Scene(root);
					primaryStage.setScene(scene);
					primaryStage.show();
					//SETTARE NELLA SCHERMATA APERTA IL TASTO ESCI
					primaryStage.setOnCloseRequest((WindowEvent event1) -> {
						try {
							match.leaveMatchAsPlayer(client);
						} catch (RemoteException e) {
							e.printStackTrace();
						}
						System.exit(0);
					});

					Stage oldStage = (Stage) joinButton.getScene().getWindow();
					oldStage.close();
				}
			}
		});
	}

	});}

	protected void updateItem(MatchData item, boolean empty) {
		
//            setAviableLabel(!item.isOnGoing());

		observeButton.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
			@Override
			public void handle(javafx.event.ActionEvent event) {
				player = false;
				try {
					match = server.observeMatch(client, item.getIdMatch());
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				if (match == null) {
					Notification.notify("Notifica Partita", "Partita inesistente", true);
				} else {
					TabPaneController.creator = false;
					FXMLLoader loader = new FXMLLoader(
							getClass().getClassLoader().getResource("game_player_pane.fxml"));
					Parent root = null;
					try {
						root = loader.load();
					} catch (IOException e) {
						e.printStackTrace();
					}
					Stage primaryStage = new Stage();
					Scene scene = new Scene(root);
					primaryStage.setScene(scene);
					primaryStage.show();
					ApplicationCloser.setCloser(primaryStage);
					Stage oldStage = (Stage) observeButton.getScene().getWindow();
					oldStage.close();
				}
			}
		});

	}

}

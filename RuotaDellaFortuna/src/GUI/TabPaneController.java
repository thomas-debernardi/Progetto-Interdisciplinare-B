package GUI;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import Game.RemoteMatch;
import Server.Server;
import Services.Client;
import Services.Controller;
import Services.MatchData;
import Services.Notification;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class TabPaneController {

	private JFrame frame;
    private static Client client;
    private static Server server;
    private static RemoteMatch match;
    private static MatchData matchData;
    private boolean isAdmin;
    public static boolean creator = true;
    
	/**
	 * Create the application.
	 */
	public TabPaneController() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.GRAY);
		frame.setBounds(100, 100, 1050, 683);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panelMenu = new JPanel();
		panelMenu.setBackground(Color.DARK_GRAY);
		panelMenu.setBounds(0, 0, 284, 683);
		frame.getContentPane().add(panelMenu);
		panelMenu.setLayout(new GridLayout(6, 1, 0, 20));
		
		JButton btnGames = new JButton("GAMES");
		btnGames.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnGames.setBackground(Color.DARK_GRAY);
		btnGames.setForeground(Color.WHITE);
		panelMenu.add(btnGames);
		
		JButton btnUserStatistics = new JButton("USER STATISTICS");
		btnUserStatistics.setBackground(Color.DARK_GRAY);
		btnUserStatistics.setForeground(Color.WHITE);
		btnUserStatistics.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelMenu.add(btnUserStatistics);
		
		JButton btnGlobalStatistics = new JButton("GLOBAL STATISTICS");
		btnGlobalStatistics.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnGlobalStatistics.setBackground(Color.DARK_GRAY);
		btnGlobalStatistics.setForeground(Color.WHITE);
		panelMenu.add(btnGlobalStatistics);
		
		JButton btnAddPhrases = new JButton("ADD PHRASES");
		btnAddPhrases.setBackground(Color.DARK_GRAY);
		btnAddPhrases.setForeground(Color.WHITE);
		btnAddPhrases.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelMenu.add(btnAddPhrases);
		
		JButton btnProfile = new JButton("PROFILE");
		btnProfile.setForeground(Color.WHITE);
		btnProfile.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnProfile.setBackground(Color.DARK_GRAY);
		panelMenu.add(btnProfile);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		panelMenu.add(panel_1);
		panel_1.setLayout(new GridLayout(1, 2, 0, 0));
		
		JButton btnNewMatch = new JButton("NEW \r\nMATCH");
		btnNewMatch.setBackground(Color.DARK_GRAY);
		btnNewMatch.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewMatch.setForeground(Color.WHITE);
		panel_1.add(btnNewMatch);
		
		JButton btnRefresh = new JButton("REFRESH");
		btnRefresh.setBackground(Color.DARK_GRAY);
		btnRefresh.setForeground(Color.WHITE);
		btnRefresh.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_1.add(btnRefresh);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(282, 0, 754, 683);
		frame.getContentPane().add(panel);
		panel.setLayout(new CardLayout(0, 0));
		
		JPanel panelGames = new JPanel();
		panelGames.setBackground(Color.GRAY);
		panel.add(panelGames, "name_861668335796200");
		
		JPanel panelUsersStatistics = new JPanel();
		panelUsersStatistics.setBackground(Color.GRAY);
		panel.add(panelUsersStatistics, "name_861713845535800");
		panelUsersStatistics.setLayout(null);
		
		JLabel lblVictory = new JLabel("Victory");
		lblVictory.setHorizontalAlignment(SwingConstants.CENTER);
		lblVictory.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblVictory.setForeground(Color.WHITE);
		lblVictory.setBounds(10, 95, 772, 25);
		panelUsersStatistics.add(lblVictory);
		
		JLabel lblVictoryGames = new JLabel("Games...........................");
		lblVictoryGames.setForeground(Color.WHITE);
		lblVictoryGames.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblVictoryGames.setBounds(195, 130, 270, 22);
		panelUsersStatistics.add(lblVictoryGames);
		
		JLabel lblVictoryGamesValue = new JLabel("0");
		lblVictoryGamesValue.setHorizontalAlignment(SwingConstants.CENTER);
		lblVictoryGamesValue.setForeground(Color.WHITE);
		lblVictoryGamesValue.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblVictoryGamesValue.setBounds(411, 130, 180, 25);
		panelUsersStatistics.add(lblVictoryGamesValue);
		
		JLabel lblVictoryManches = new JLabel("Manches...........................");
		lblVictoryManches.setForeground(Color.WHITE);
		lblVictoryManches.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblVictoryManches.setBounds(195, 168, 270, 25);
		panelUsersStatistics.add(lblVictoryManches);
		
		JLabel lblVictoryManchesValue = new JLabel("0");
		lblVictoryManchesValue.setHorizontalAlignment(SwingConstants.CENTER);
		lblVictoryManchesValue.setForeground(Color.WHITE);
		lblVictoryManchesValue.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblVictoryManchesValue.setBounds(411, 174, 180, 19);
		panelUsersStatistics.add(lblVictoryManchesValue);
		
		JLabel lblGames = new JLabel("Games");
		lblGames.setHorizontalAlignment(SwingConstants.CENTER);
		lblGames.setForeground(Color.WHITE);
		lblGames.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGames.setBounds(69, 266, 270, 25);
		panelUsersStatistics.add(lblGames);
		
		JLabel lblGamesPlayed = new JLabel("Played...........................");
		lblGamesPlayed.setForeground(Color.WHITE);
		lblGamesPlayed.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGamesPlayed.setBounds(79, 301, 187, 13);
		panelUsersStatistics.add(lblGamesPlayed);
		
		JLabel lblGamesOsserved = new JLabel("Osserved...........................");
		lblGamesOsserved.setForeground(Color.WHITE);
		lblGamesOsserved.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGamesOsserved.setBounds(79, 324, 187, 13);
		panelUsersStatistics.add(lblGamesOsserved);
		
		JLabel lblGamesPlayedValue = new JLabel("0");
		lblGamesPlayedValue.setHorizontalAlignment(SwingConstants.CENTER);
		lblGamesPlayedValue.setForeground(Color.WHITE);
		lblGamesPlayedValue.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGamesPlayedValue.setBounds(276, 301, 63, 13);
		panelUsersStatistics.add(lblGamesPlayedValue);
		
		JLabel lblGamesOsservedValue = new JLabel("0");
		lblGamesOsservedValue.setHorizontalAlignment(SwingConstants.CENTER);
		lblGamesOsservedValue.setForeground(Color.WHITE);
		lblGamesOsservedValue.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGamesOsservedValue.setBounds(276, 324, 63, 13);
		panelUsersStatistics.add(lblGamesOsservedValue);
		
		JLabel lblManches = new JLabel("Manches");
		lblManches.setHorizontalAlignment(SwingConstants.CENTER);
		lblManches.setForeground(Color.WHITE);
		lblManches.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblManches.setBounds(442, 266, 270, 25);
		panelUsersStatistics.add(lblManches);
		
		JLabel lblManchesPlayed = new JLabel("Played...........................");
		lblManchesPlayed.setForeground(Color.WHITE);
		lblManchesPlayed.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblManchesPlayed.setBounds(452, 301, 187, 13);
		panelUsersStatistics.add(lblManchesPlayed);
		
		JLabel lblManchesOsserved = new JLabel("Osserved...........................");
		lblManchesOsserved.setForeground(Color.WHITE);
		lblManchesOsserved.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblManchesOsserved.setBounds(452, 324, 187, 13);
		panelUsersStatistics.add(lblManchesOsserved);
		
		JLabel lblManchesPlayedValue = new JLabel("0");
		lblManchesPlayedValue.setHorizontalAlignment(SwingConstants.CENTER);
		lblManchesPlayedValue.setForeground(Color.WHITE);
		lblManchesPlayedValue.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblManchesPlayedValue.setBounds(649, 301, 63, 13);
		panelUsersStatistics.add(lblManchesPlayedValue);
		
		JLabel lblManchesOsservedValue = new JLabel("0");
		lblManchesOsservedValue.setHorizontalAlignment(SwingConstants.CENTER);
		lblManchesOsservedValue.setForeground(Color.WHITE);
		lblManchesOsservedValue.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblManchesOsservedValue.setBounds(649, 324, 63, 13);
		panelUsersStatistics.add(lblManchesOsservedValue);
		
		JLabel lblAverageSoldShifts = new JLabel("Average sold shifts");
		lblAverageSoldShifts.setHorizontalAlignment(SwingConstants.CENTER);
		lblAverageSoldShifts.setForeground(Color.WHITE);
		lblAverageSoldShifts.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAverageSoldShifts.setBounds(69, 395, 270, 25);
		panelUsersStatistics.add(lblAverageSoldShifts);
		
		JLabel lblAverageShiftsGames = new JLabel("Games...........................");
		lblAverageShiftsGames.setForeground(Color.WHITE);
		lblAverageShiftsGames.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAverageShiftsGames.setBounds(79, 430, 187, 13);
		panelUsersStatistics.add(lblAverageShiftsGames);
		
		JLabel lblAverageShiftsManches = new JLabel("Manches...........................");
		lblAverageShiftsManches.setForeground(Color.WHITE);
		lblAverageShiftsManches.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAverageShiftsManches.setBounds(79, 453, 187, 13);
		panelUsersStatistics.add(lblAverageShiftsManches);
		
		JLabel lblAverageShiftsGamesValue = new JLabel("0");
		lblAverageShiftsGamesValue.setHorizontalAlignment(SwingConstants.CENTER);
		lblAverageShiftsGamesValue.setForeground(Color.WHITE);
		lblAverageShiftsGamesValue.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAverageShiftsGamesValue.setBounds(276, 430, 63, 13);
		panelUsersStatistics.add(lblAverageShiftsGamesValue);
		
		JLabel lblAverageShiftsManchesValue = new JLabel("0");
		lblAverageShiftsManchesValue.setHorizontalAlignment(SwingConstants.CENTER);
		lblAverageShiftsManchesValue.setForeground(Color.WHITE);
		lblAverageShiftsManchesValue.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAverageShiftsManchesValue.setBounds(276, 453, 63, 13);
		panelUsersStatistics.add(lblAverageShiftsManchesValue);
		
		JLabel lblAverageLostEverything = new JLabel("Average lost everything");
		lblAverageLostEverything.setHorizontalAlignment(SwingConstants.CENTER);
		lblAverageLostEverything.setForeground(Color.WHITE);
		lblAverageLostEverything.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAverageLostEverything.setBounds(442, 395, 270, 25);
		panelUsersStatistics.add(lblAverageLostEverything);
		
		JLabel lblAverageLostEverythingGames = new JLabel("Games...........................");
		lblAverageLostEverythingGames.setForeground(Color.WHITE);
		lblAverageLostEverythingGames.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAverageLostEverythingGames.setBounds(452, 430, 187, 13);
		panelUsersStatistics.add(lblAverageLostEverythingGames);
		
		JLabel lblAverageLostEverythingManches = new JLabel("Manches...........................");
		lblAverageLostEverythingManches.setForeground(Color.WHITE);
		lblAverageLostEverythingManches.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAverageLostEverythingManches.setBounds(452, 453, 187, 13);
		panelUsersStatistics.add(lblAverageLostEverythingManches);
		
		JLabel lblAverageLostEverythingGamesValue = new JLabel("0");
		lblAverageLostEverythingGamesValue.setHorizontalAlignment(SwingConstants.CENTER);
		lblAverageLostEverythingGamesValue.setForeground(Color.WHITE);
		lblAverageLostEverythingGamesValue.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAverageLostEverythingGamesValue.setBounds(649, 430, 63, 13);
		panelUsersStatistics.add(lblAverageLostEverythingGamesValue);
		
		JLabel lblAverageLostEverythingManchesValue = new JLabel("0");
		lblAverageLostEverythingManchesValue.setHorizontalAlignment(SwingConstants.CENTER);
		lblAverageLostEverythingManchesValue.setForeground(Color.WHITE);
		lblAverageLostEverythingManchesValue.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAverageLostEverythingManchesValue.setBounds(649, 453, 63, 13);
		panelUsersStatistics.add(lblAverageLostEverythingManchesValue);
		
		JLabel lblAveragePointsWon = new JLabel("Average points won");
		lblAveragePointsWon.setHorizontalAlignment(SwingConstants.CENTER);
		lblAveragePointsWon.setForeground(Color.WHITE);
		lblAveragePointsWon.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblAveragePointsWon.setBounds(10, 523, 772, 25);
		panelUsersStatistics.add(lblAveragePointsWon);
		
		JLabel label = new JLabel("0");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Tahoma", Font.BOLD, 15));
		label.setBounds(370, 558, 63, 13);
		panelUsersStatistics.add(label);
		
		JPanel panelGlobalStatistics = new JPanel();
		panel.add(panelGlobalStatistics, "name_863833183078700");
		panelGlobalStatistics.setBackground(Color.GRAY);
		
		JPanel panelAddPhrases = new JPanel();
		panel.add(panelAddPhrases, "name_861757544902700");
		
		JPanel panelProfile = new JPanel();
		panelProfile.setBackground(Color.GRAY);
		panel.add(panelProfile, "name_861780154478200");
		frame.setVisible(true);
	}
	
	/**
     * Metodo utilizzato per la creazione di un nuovo match. Carichera' anche la schermata di gioco
     *
     * @param actionEvent //todo
     * @throws RemoteException in caso di errore di comunicazione con il server
     */
    public void addMatch(ActionEvent actionEvent) throws RemoteException {
        try {
            match = server.createMatch(client);
            //ON CLOSE LASCIARE LA PARTITA NELLA FINESTRA CREATA
			GameWheel gw = new GameWheel(match, client);
			frame.dispose();
        } catch (RemoteException e) {
            client.notifyServerError();
        }

    }

    
    /**
     * Inizializza il controller caricando automaticamente le partite disponibili, le statistiche di utilizzo della piattaforma e le informazioni del proprio profilo
     */
    public void initialize(URL location, ResourceBundle resources) {
        MainPane.setArgs(this);
        creator = true;

        if (isAdmin) {
            createMatchButton.setVisible(false);
        }

        gameList.setItems(gameObservableList);
        ArrayList<MatchData> list = new ArrayList<>();
        try {
            list = server.visualizeMatch(client);
            gameObservableList.addAll(list);
            gameList.setItems(gameObservableList);
        } catch (RemoteException e) {
            Notification.notify("Errore", "Server offline", true);
        }
        for (MatchData matchData : list) {
            gameList.setCellFactory(e -> new GameViewController(server, client, matchData));
        }
        disableTab();
        try {
            setUserStat();
            setGlobalStats();
        } catch (RemoteException e) {
            Notification.notify("Errore", "Statistiche non caricate", true);
        }

        try {
            nicknameLabel.setText(client.getNickname());
            nameLabel.setText(client.getName());
            surnameLabel.setText(client.getSurname());
            emailLabel.setText(client.getEmail());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void disableTab() {
        if (!this.isAdmin) {
            tabs.getTabs().remove(phraseAdder);
            tabs.getTabs().remove(userStatisticsTabAdmin);
        } else {
            tabs.getTabs().remove(userStatisticsTab);
        }
    }

    @FXML
    /**
     * Carica le statistiche relative all'utente loggato.
     */
    public void setUserStat() throws RemoteException {
        String userStat = server.checkPerPlayer(client.getNickname());
//		String userStat = "";
        if (!(userStat == null)) {

            StringTokenizer stasts = new StringTokenizer(userStat, " ");
            numberManchesPlayedLabel.setText(stasts.nextToken());
            numberMatchesplayedLabel.setText(stasts.nextToken());
            String numMancheObserved = stasts.nextToken();
            numberManchesObservedLabel.setText(numMancheObserved);
            numberManchesObservedLabel1.setText(numMancheObserved);
            String numMatchObserved = stasts.nextToken();
            numberMatchesObservedLabel.setText(numMatchObserved);
            numberMatchesObservedLabel1.setText(numMatchObserved);
            numberOfManchesWonLabel.setText(stasts.nextToken());
            numberOfMatchesWonLabel.setText(stasts.nextToken());
            averagePointsWonLabel.setText(stasts.nextToken());
            averageTurnLostPerMancheLabel.setText(stasts.nextToken());
            averageTurnLostPerMatchLabel.setText(stasts.nextToken());
            averageLostAllPerMancheLabel.setText(stasts.nextToken());
            averageLostAllPerMatchLabel.setText(stasts.nextToken());
        } else {
            numberManchesPlayedLabel.setText("0");
            numberMatchesplayedLabel.setText("0");
            numberManchesObservedLabel.setText("0");
            numberMatchesObservedLabel.setText("0");
            numberOfManchesWonLabel.setText("0");
            numberOfMatchesWonLabel.setText("0");
            averagePointsWonLabel.setText("0");
            averageTurnLostPerMancheLabel.setText("0");
            averageTurnLostPerMatchLabel.setText("0");
            averageLostAllPerMancheLabel.setText("0");
            averageLostAllPerMatchLabel.setText("0");
        }

    }


    @FXML
    /**
     * Carica i record di utilizzo della piattaforma
     */
    public void setGlobalStats() throws RemoteException {
        try {
            String recordStatStr = server.checkRecordPlayer();

            int avSolManches = server.averageManches();

            String strBestMove = server.bestMove();
//           String recordStatStr = " ";
//           int avSolManches = 1;
//           String strBestMove = " ";
            StringTokenizer bestMove = new StringTokenizer(strBestMove, " ");
            StringTokenizer recordStat = new StringTokenizer(recordStatStr, " ");

            bestPointsWonMancheLabel.setText(recordStat.nextToken());
            bestPointsWonMatchLabel.setText(recordStat.nextToken());
            mostManchePlayedLabel.setText(recordStat.nextToken());
            averagePointPerMancheLabel.setText(recordStat.nextToken());
            mostTimeLostTurnLabel.setText(recordStat.nextToken());
            mostTimeLostAllLabel.setText(recordStat.nextToken());

            nickBestCallLabel.setText(bestMove.nextToken());
            letterCalledLabel.setText(bestMove.nextToken());
            String phrase = "";
            while (bestMove.hasMoreElements()) {
                phrase += bestMove.nextToken() + " ";
            }
            phraseAsociatedLabel.setText(phrase);

            averageMovesTillSolutionLabel.setText(String.valueOf(avSolManches));
        } catch (RemoteException e) {
            Notification.notify("Errore", "Server offline", true);
        }
    }

    /**
     * Notifica che la partita alla quale si ha provato a partecipare come giocatore e' piena
     */
    public void notifyTooManyPlayers() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Notification.notify("Giocatori", "Troppi giocatori", true);
            }
        });
    }

    /**
     * Ricarica la lista delle partite disponibili aggiornata
     */
    public void refresh() {
        gameObservableList = FXCollections.observableArrayList();
        gameList.setItems(gameObservableList);
        ArrayList<MatchData> list = new ArrayList<>();
        try {
            list = server.visualizeMatch(client);
            gameObservableList.addAll(list);
            gameList.setItems(gameObservableList);
        } catch (RemoteException e) {
            Notification.notify("Errore", "Non è stato possibile aggiornare la lista dei match\n Riprova", true);
        }
        for (MatchData matchData : list) {
            gameList.setCellFactory(e -> new GameViewController(server, client, matchData));
        }
    }

    /**
     * Legge il file .csv inserito per l'aggiunta di nuove frasi e lo invia al server
     */
    public void enterFilePhrase() {
        String phrases = filePhraseTextField.getText();
        String phrasesTrim = phrases.trim();
        File filePhrases = new File(phrasesTrim);
        try {
            boolean bool = server.addPhrases(filePhrases);
            if (bool) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Notification.notify("Successo", "Le frasi sono state aggiunte con successo", false);
                    }
                });

            } else {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Notification.notify("Errore", "Non è stato possibile aggiungere le nuove frasi\n Riprova", true);
                    }
                });
            }
        } catch (RemoteException e) {
            Notification.notify("Errore", "Server offline", true);
        }
    }

    /**
     * Permette di cambiare il proprio nome.
     *
     * @throws RemoteException In caso di errore di connessione al server
     */
    public void changeName() {
        String name = nameTextField.getText();
        if (!name.equals("")) {
            boolean bool = false;
            try {
                bool = server.changeName(name, client);
            } catch (RemoteException e) {
                Notification.notify("Errore", "Server offline", true);
            }
            if (bool) {
                nameLabel.setText(name);
                try {
                    client.setName(name);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                Notification.notify("Successo", "Il nome è stato modificato con successo", false);
            } else {
                Notification.notify("Errore", "Non è stato possibile modificare il nome", true);
            }
        }
    }

    /**
     * Permette di cambiare il proprio cognome.
     *
     * @throws RemoteException In caso di un errore di connesione al server
     */
    public void changeSurname() {
        String surname = surnameTextField.getText();
        if (!surname.equals("")) {
            boolean bool = false;
            try {
                bool = server.changeSurname(surname, client);
            } catch (RemoteException e) {
                Notification.notify("Errore", "Server offline", true);
            }
            if (bool) {
                surnameLabel.setText(surname);
                try {
                    client.setSurname(surname);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                Notification.notify("Successo", "Il cognome è stato modificato con successo", false);
            } else {
                Notification.notify("Errore", "Non è stato possibile modificare il cognome", true);
            }
        }
    }

    /**
     * Permette di cambiare il proprio nickname.
     *
     * @throws RemoteException In caso di errore di connessione al server
     */
    public void changeNickname() {
        String nickname = nicknameTextField.getText();
        if (!nickname.equals("")) {
            boolean bool = false;
            try {
                bool = server.changeNickname(nickname, client);
            } catch (RemoteException e) {
                Notification.notify("Errore", "Server offline", true);
            }
            if (bool) {
                try {
                    client.setNickname(nickname);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                nicknameLabel.setText(nickname);
                Notification.notify("Successo", "Il nickname è stato modificato con successo", false);
            } else {
                Notification.notify("Errore", "Non è stato possibile modificare il nickname oppure è già in uso", true);
            }
        }
    }

    /**
     * Permette di modificare la propria password
     *
     * @throws RemoteException In caso di errore di connesione al server
     */
    public void changePassword() {
        String password = passwordField.getText();
        if (!password.equals("")) {
            password = CryptPassword.encrypt(password);
            boolean bool = false;
            try {
                bool = server.changePassword(password, client);
            } catch (RemoteException e) {
                Notification.notify("Errore", "Server offline", true);
            }
            if (bool) {
                Notification.notify("Successo", "La password è stata modificata con successo", false);
            } else {
                Notification.notify("Errore", "Non è stato possibile modificare la password", true);
            }
        }
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    /**
     * Metodo utilizzato per passare le informazioni del client a {@link GamePlayerController}
     *
     * @param gpc il riferimento al controller {@link GamePlayerController}
     */
    public static void setGameControlle(GamePlayerController gpc) {
        gpc.setClient(client);
        gpc.setMatch(match);
        gpc.setObserver(false);
    }

    /**
     * Notifica che una partita e' stata annullata a causa di un problema
     *
     * @param reason il motivo per cui la partita e' stata annullata (ad esempio perche' non e' stato possibile scegliere le cinque frasi)
     */
    public static void notifyMatchAbort(String reason) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Notification.notify("Notifica di partita", reason, false);
            }
        });
    }

    /**
     * Notifica che la partita si e' conclusa
     *
     * @param message un messaggio contenente il vincitore della partita, o nessuno in caso in cui la partita si sia interrotta a meta'
     */
    public static void notifyMatchEnd(String message) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Notification.notify("Notifica di partita", message, false);
            }
        });
    }

    /**
     * Notifica che il client corrente e' il vincitore della partita
     */
    public static void notifyMatchWin() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Notification.notify("Notifica di partita", "HAI VINTO!!!", false);
            }
        });
    }

    /**
     * Notifica che un utente ha abbandonato la partita
     *
     * @param message il messaggio contenente il nickname di chi ha abbandonato
     */
    public static void notifyLeaver(String message){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Notification.notify("Notifica di partita", message, false);
            }
        });
    }
}

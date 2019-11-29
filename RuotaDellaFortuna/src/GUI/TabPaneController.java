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
		frame.setBounds(100, 100, 1050, 683);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
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
        Controller.setArgs(this);
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

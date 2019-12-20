package gui;

import javax.swing.JFrame;

import game.RemoteMatch;
import services.Client;
import services.Notification;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.StringTokenizer;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class Game {

	private JFrame frame;
	private static boolean isObserver;
	private int wheelResult;
	private static RemoteMatch match;
	private Client client;
	private boolean wheelButtonPressed;
	private boolean vowelButtonPressed;
	private int posX = 0, posY = 0;
	private JButton[][] letter;
	private JPanel panelPhrase;
	private JLabel lblTheme;
	private Color space = new Color(169, 209, 142);
	private Color find = new Color(255, 230, 153);
	private JTextField textFieldCharacter;
	private char[][] x = new char[4][15];
	private JTextField tfSolution;
	private JButton btnSendSolution;
	private JButton btnJolly;
	private JButton btnVocal;
	private JButton btnSpin;
	private JLabel lblWheelResult;
	private JLabel lblPlayer1;
	private JLabel lblPlayer2;
	private JLabel lblPlayer3;
	private JLabel lblTurn;
	private JLabel lblJolly1;
	private JLabel lblJolly2;
	private JLabel lblJolly3;
	private JLabel lblPartial1;
	private JLabel lblPartial2;
	private JLabel lblPartial3;
	private JLabel lblTotal1;
	private JLabel lblTotal2;
	private JLabel lblTotal3;
	private JLabel lblTime;
	private String phrase;
	private JButton btnConsonant;
	private JButton btnGiveSolution;

	/**
	 * Create the application.
	 */
	public Game(RemoteMatch match, Client client) {
		this.match = match;
		this.client = client;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		String nickname = null;
		try {
			nickname = client.getNickname();
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}

		frame = new JFrame("Rdf: " + nickname);
		frame.setResizable(false);
		frame.setUndecorated(true);
		frame.getContentPane().setBackground(Color.GRAY);
		frame.setBackground(Color.GRAY);
		frame.setVisible(true);
		frame.setBounds(100, 100, 1372, 689);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		lblPlayer1 = new JLabel("Player 1");
		lblPlayer1.setForeground(Color.WHITE);
		lblPlayer1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPlayer1.setBounds(1097, 10, 265, 21);
		frame.getContentPane().add(lblPlayer1);

		lblPlayer2 = new JLabel("Player 2");
		lblPlayer2.setForeground(Color.WHITE);
		lblPlayer2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPlayer2.setBounds(1097, 213, 265, 21);
		frame.getContentPane().add(lblPlayer2);

		lblPlayer3 = new JLabel("Player 3");
		lblPlayer3.setForeground(Color.WHITE);
		lblPlayer3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPlayer3.setBounds(1097, 395, 265, 21);
		frame.getContentPane().add(lblPlayer3);

		lblJolly1 = new JLabel("Jolly: ");
		lblJolly1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblJolly1.setForeground(Color.WHITE);
		lblJolly1.setBounds(1107, 41, 46, 13);
		frame.getContentPane().add(lblJolly1);

		lblJolly2 = new JLabel("Jolly: ");
		lblJolly2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblJolly2.setForeground(Color.WHITE);
		lblJolly2.setBounds(1097, 244, 46, 13);
		frame.getContentPane().add(lblJolly2);

		lblJolly3 = new JLabel("Jolly: ");
		lblJolly3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblJolly3.setForeground(Color.WHITE);
		lblJolly3.setBounds(1097, 426, 46, 13);
		frame.getContentPane().add(lblJolly3);

		lblPartial1 = new JLabel("Partial");
		lblPartial1.setForeground(Color.WHITE);
		lblPartial1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPartial1.setBounds(1182, 41, 85, 13);
		frame.getContentPane().add(lblPartial1);

		lblPartial2 = new JLabel("Partial");
		lblPartial2.setForeground(Color.WHITE);
		lblPartial2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPartial2.setBounds(1182, 242, 85, 13);
		frame.getContentPane().add(lblPartial2);

		lblPartial3 = new JLabel("Partial");
		lblPartial3.setForeground(Color.WHITE);
		lblPartial3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPartial3.setBounds(1182, 426, 85, 13);
		frame.getContentPane().add(lblPartial3);

		lblTotal1 = new JLabel("Total");
		lblTotal1.setForeground(Color.WHITE);
		lblTotal1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTotal1.setBounds(1277, 41, 85, 13);
		frame.getContentPane().add(lblTotal1);

		lblTotal2 = new JLabel("Total");
		lblTotal2.setForeground(Color.WHITE);
		lblTotal2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTotal2.setBounds(1277, 242, 85, 13);
		frame.getContentPane().add(lblTotal2);

		lblTotal3 = new JLabel("Total");
		lblTotal3.setForeground(Color.WHITE);
		lblTotal3.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTotal3.setBounds(1277, 426, 85, 13);
		frame.getContentPane().add(lblTotal3);

		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					exitMatch();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		btnExit.setForeground(Color.WHITE);
		btnExit.setBackground(Color.RED);
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnExit.setBounds(1277, 647, 85, 21);
		frame.getContentPane().add(btnExit);

		panelPhrase = new JPanel();
		panelPhrase.setBackground(Color.DARK_GRAY);
		panelPhrase.setBounds(10, 10, 1080, 429);
		frame.getContentPane().add(panelPhrase);
		panelPhrase.setLayout(new GridLayout(4, 15, 2, 2));

		JPanel panelAction = new JPanel();
		panelAction.setBackground(Color.DARK_GRAY);
		panelAction.setBounds(10, 486, 1080, 151);
		frame.getContentPane().add(panelAction);
		panelAction.setLayout(null);

		btnSpin = new JButton("SPIN");
		btnSpin.setBounds(962, 18, 96, 113);
		panelAction.add(btnSpin);
		btnSpin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wheelSpin();
			}
		});
		btnSpin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSpin.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSpin.setBackground(Color.GREEN);
		btnSpin.setForeground(Color.WHITE);

		textFieldCharacter = new JTextField();
		textFieldCharacter.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldCharacter.setFont(new Font("Tahoma", Font.BOLD, 20));
		textFieldCharacter.setBounds(676, 59, 263, 31);
		panelAction.add(textFieldCharacter);
		textFieldCharacter.setColumns(10);

		tfSolution = new JTextField();
		tfSolution.setHorizontalAlignment(SwingConstants.CENTER);
		tfSolution.setToolTipText("Insert the solution here");
		tfSolution.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfSolution.setBounds(10, 59, 520, 31);
		panelAction.add(tfSolution);
		tfSolution.setColumns(10);

		btnSendSolution = new JButton("SEND SOLUTION");
		btnSendSolution.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					confirmSolution();
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});
		btnSendSolution.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSendSolution.setBounds(10, 100, 520, 31);
		btnSendSolution.setEnabled(false);
		panelAction.add(btnSendSolution);

		btnVocal = new JButton("BUY VOCAL");
		btnVocal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnVocal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					giveVocal();
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});
		btnVocal.setBounds(809, 18, 130, 31);
		panelAction.add(btnVocal);

		btnGiveSolution = new JButton("GIVE SOLUTION");
		btnGiveSolution.setForeground(Color.WHITE);
		btnGiveSolution.setBackground(Color.CYAN);
		btnGiveSolution.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnGiveSolution.setBounds(10, 18, 520, 31);
		panelAction.add(btnGiveSolution);
		btnGiveSolution.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					giveSolution();
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
		});

		btnConsonant = new JButton("SEND LETTER");
		btnConsonant.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnConsonant.setEnabled(false);
		btnConsonant.setBounds(676, 18, 130, 31);
		panelAction.add(btnConsonant);

		btnJolly = new JButton("JOLLY");
		btnJolly.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnJolly.setBounds(676, 100, 263, 31);
		panelAction.add(btnJolly);

		lblTime = new JLabel("TIMER");
		lblTime.setBounds(540, 32, 130, 82);
		panelAction.add(lblTime);
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setForeground(Color.WHITE);
		lblTime.setFont(new Font("Tahoma", Font.BOLD, 45));
		btnJolly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					giveJolly();
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});
		btnConsonant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					onEnter();
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
		});

		lblTheme = new JLabel("");
		lblTheme.setForeground(Color.WHITE);
		lblTheme.setHorizontalAlignment(SwingConstants.CENTER);
		lblTheme.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTheme.setBounds(20, 449, 1070, 24);
		frame.getContentPane().add(lblTheme);

		lblWheelResult = new JLabel("New label");
		lblWheelResult.setHorizontalAlignment(SwingConstants.CENTER);
		lblWheelResult.setForeground(Color.WHITE);
		lblWheelResult.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblWheelResult.setBounds(1165, 486, 144, 151);
		frame.getContentPane().add(lblWheelResult);

		lblTurn = new JLabel("Is");
		lblTurn.setForeground(Color.WHITE);
		lblTurn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTurn.setBounds(10, 666, 235, 13);
		frame.getContentPane().add(lblTurn);

		letter = new JButton[4][15];

		frame.setLocationRelativeTo(null);
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

		// FULLSCREEN
		// GraphicsEnvironment graphics =
		// GraphicsEnvironment.getLocalGraphicsEnvironment();
		// GraphicsDevice device = graphics.getDefaultScreenDevice();
		// frame.setUndecorated(false);
		// device.setFullScreenWindow(frame);

		if (TabPane.creator) {
			TabPane.setGameControlle(this);
		} else {
			GameBeingPlayed.setGameControllerObserver(this);
			if (!GameBeingPlayed.player) {
				hideAll();
			}
		}

		try {
			client.setGame(this);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		createTableOfPhrase();
		try {
			match.askNotify(client);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		disableAll();
		try {
			if (GameBeingPlayed.player)
				match.tryForStartMatch();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	public void createTableOfPhrase() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 15; j++) {
				letter[i][j] = new JButton("");
				letter[i][j].setBackground(space);
				letter[i][j].setFont(new Font("Tahoma", Font.BOLD, 30));
				panelPhrase.add(letter[i][j]);
			}

		}
	}

	public void updatePhrase(String letters) {
		char c = letters.charAt(0);
		Thread t = new Thread() {
			public void run() {
				for (int i = 0; i < 4; i++) {
					for (int j = 0; j < 15; j++) {
						if (x[i][j] == c) {
							letter[i][j].setBackground(find);
							letter[i][j].setText(letters);
							panelPhrase.revalidate();
							panelPhrase.repaint();
						}
					}
				}
			}
		};
		t.start();
	}

	public void updatePhrase(boolean[] phrase2) {
		String s = phrase.replaceAll("\\s", "");
		Thread t = new Thread() {
			public void run() {
				char c;
				for (int i = 0; i < phrase2.length; i++) {
					if (phrase2[i]) {
						c = s.charAt(i);
						for (int ii = 0; ii < 4; ii++) {
							for (int jj = 0; jj < 15; jj++) {
								if (x[ii][jj] == c) {
									letter[ii][jj].setText(c + "");
								}
							}
						}
					}
				}
			}
		};
		t.start();

	}

	public void setNewPhrase(String theme2, String phrase2) {
		phrase = phrase2.toUpperCase();
		String theme = theme2;
		Thread t = new Thread() {
			public void run() {
				lblTheme.setText(theme);
				panelPhrase.removeAll();
				for (int i = 0; i < 4; i++) {
					for (int j = 0; j < 15; j++) {
						letter[i][j].setText("");
						letter[i][j].setBackground(space);
						x[i][j] = ' ';
						panelPhrase.add(letter[i][j]);
					}
				}
				int column = 0;
				int row = 0;
				char car;
				StringTokenizer st = new StringTokenizer(phrase, " ',!?.:;\"/()\\^<>-+*0123456789");
				String s;
				int pointer = 0;

				while (st.hasMoreTokens()) {
					s = st.nextToken();
					if (s.length() < 15 - column) {
						for (int i = 0; i < s.length(); i++) {
							x[row][column] = s.charAt(i);
							// letter[row][column].setText(""+s.charAt(i));
							letter[row][column].setBackground(find);
							column++;
						}
						pointer += s.length();
						if (column < 14) {
							x[row][column] = ' '; // HO TOLTO IL PIU UNO
							// letter[row][column].setText(" ");
							letter[row][column].setBackground(space);
							column++;
						} else {
							column = 0;
							row++;
							pointer = 0;
						}
					} else {
						row++;
						column = 0;
						pointer = 0;
						for (int i = 0; i < s.length(); i++) {
							x[row][column] = s.charAt(i);
							// letter[row][column].setText(""+s.charAt(i));
							letter[row][column].setBackground(find);
							column++;
						}
						pointer += s.length();
						if (column < 14) {
							x[row][column] = ' ';
							// letter[row][column].setText(" ");
							letter[row][column].setBackground(space);
							column++;
						} else {
							column = 0;
							row++;
							pointer = 0;
						}
					}
				}
				panelPhrase.revalidate();
				panelPhrase.repaint();
			}
		};
		t.start();

	}

	public void hideAll() {
		btnJolly.setVisible(false);
		btnVocal.setVisible(false);
		btnSpin.setVisible(false);
		btnSendSolution.setVisible(false);
		tfSolution.setVisible(false);
		textFieldCharacter.setVisible(false);
		btnConsonant.setVisible(false);
		btnGiveSolution.setVisible(false);
	}

	public void disableAll() {
		btnJolly.setEnabled(false);
		btnVocal.setEnabled(false);
		btnSpin.setEnabled(false);
		btnSendSolution.setEnabled(false);
		tfSolution.setEnabled(false);
		textFieldCharacter.setEnabled(false);
		btnConsonant.setEnabled(false);
		btnGiveSolution.setEnabled(false);
	}

	public void activeAll() {
		btnJolly.setEnabled(true);
		btnVocal.setEnabled(true);
		btnSpin.setEnabled(true);
		tfSolution.setEnabled(true);
		textFieldCharacter.setEnabled(true);
		btnConsonant.setEnabled(true);
		btnGiveSolution.setEnabled(true);
	}

	public void giveSolution() throws RemoteException {
		match.askForSolution();
		btnSendSolution.setEnabled(true);
	}

	public void confirmSolution() throws RemoteException {
		String solution = tfSolution.getText();
		solution = solution.trim();
		solution = solution.toUpperCase();
		match.giveSolution(solution);
		tfSolution.setText("");
	}

	public void wheelSpin() {
		Thread t = new Thread() {
			public void run() {
				try {
					wheelResult = match.wheelSpin();
					wheelButtonPressed = true;
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		};
		t.start();
	}

	public void wheelResult(String result) {
		Thread t = new Thread() {
			public void run() {
				lblWheelResult.setText(result);
			}
		};
		t.start();
	}

	public void onEnter() throws RemoteException {
		String letter = textFieldCharacter.getText();
		letter = letter.trim();
		letter = letter.toUpperCase();
		if (wheelButtonPressed) {
			match.giveConsonant(letter, wheelResult);
			wheelButtonPressed = false;
		} else if (vowelButtonPressed) {
			match.giveVocal(letter);
			vowelButtonPressed = false;
		}
		textFieldCharacter.setText("");
	}

	public void giveVocal() throws RemoteException {
		match.askForVocal();
		vowelButtonPressed = true;
	}

	public void giveJolly() throws RemoteException {
		match.jolly();
	}

	public void setTurn(String nickName) throws RemoteException {
		Thread t = new Thread() {
			public void run() {
				if (nickName.equals(lblPlayer1.getText())) {
					lblTurn.setText("Is " + nickName + " turn");
					lblPlayer1.setForeground(Color.GREEN);
					lblPlayer2.setForeground(Color.BLACK);
					lblPlayer3.setForeground(Color.BLACK);
					lblWheelResult.setText("Spin the wheel");
				} else if (nickName.equals(lblPlayer2.getText())) {
					lblTurn.setText("Is " + nickName + " turn");
					lblPlayer1.setForeground(Color.BLACK);
					lblPlayer2.setForeground(Color.GREEN);
					lblPlayer3.setForeground(Color.BLACK);
					lblWheelResult.setText("Spin the wheel");

				} else if (nickName.equals(lblPlayer3.getText())) {
					lblTurn.setText("Is " + nickName + " turn");
					lblPlayer1.setForeground(Color.BLACK);
					lblPlayer2.setForeground(Color.BLACK);
					lblPlayer3.setForeground(Color.GREEN);
					lblWheelResult.setText("Spin the wheel");

				}
				try {
					if (!nickName.equals(client.getNickname())) {
						disableAll();
					}
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		};
		t.start();
	}

	public void yourTurn() {
		activeAll();
	}

	public void exitMatch() throws IOException {
		try {
			if (isObserver) {
				match.leaveMatchAsObserver(client);
			} else {
				match.leaveMatchAsPlayer(client);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		} finally {
			match = null;
			TabPane.setVisible();
			frame.dispose();
		}
	}

	public void notifyPlayerStats(int pos, String nickname, int partial, int total, int numJolly) {
		Thread t = new Thread() {
			public void run() {
				switch (pos) {
				case 0:
					lblPlayer1.setText(nickname);
					lblPartial1.setText(String.valueOf(partial));
					lblTotal1.setText(String.valueOf(total));
					lblJolly1.setText(String.valueOf(numJolly));
					break;
				case 1:
					lblPlayer2.setText(nickname);
					lblPartial2.setText(String.valueOf(partial));
					lblTotal2.setText(String.valueOf(total));
					lblJolly2.setText(String.valueOf(numJolly));
					break;
				case 2:
					lblPlayer3.setText(nickname);
					lblPartial3.setText(String.valueOf(partial));
					lblTotal3.setText(String.valueOf(total));
					lblJolly3.setText(String.valueOf(numJolly));
					break;
				}
			}
		};
		t.start();
	}

	public void vocalCallNotify(String nickname) {
		Thread t = new Thread() {
			public void run() {
				String message = nickname + " ha chiamato la vocale";
				Notification.notify("Notifica di partita", message, false);
			}
		};
		t.start();
	}

	public void callSolutionNotify(String nickname) {
		Thread t = new Thread() {
			public void run() {
				String message = nickname + " da la soluzione ";
				Notification.notify("Notifica di partita", message, false);

			}
		};
		t.start();
	}

	public void jollyNotify(String nickname) {
		Thread t = new Thread() {
			public void run() {
				String message = nickname + " ha giocato il jolly";
				Notification.notify("Notifica di partita", message, false);
			}
		};
		t.start();
	}

	public void callLetterNotify(String nickname, String letter) {
		Thread t = new Thread() {
			public void run() {
				String message = nickname + " ha scelto la lettera " + letter;
				Notification.notify("Notifica di partita", message, false);

			}
		};
		t.start();
	}

	public void setMatch(RemoteMatch matc) {
		match = matc;
	}

	public void setObserver(boolean observer) {
		isObserver = observer;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public void notifyLeaver(String nickname) {
		Thread t = new Thread() {
			public void run() {
				String message = nickname + "\nha lasciato la partita";
				TabPane.notifyLeaver(message);
			}
		};
		t.start();
	}

	public void notifyMatchAbort(String reason) {
		Thread t = new Thread() {
			public void run() {
				TabPane.setVisible();
				frame.dispose();
				TabPane.notifyMatchAbort(reason);
			}
		};
		t.start();
	}

	public void notifyMatchStart() {
		Thread t = new Thread() {
			public void run() {
				String message = "Partita iniziata";
				Notification.notify("Notifica di partita", message, false);
			}
		};
		t.start();
	}

	public void notifyMancheVictory() {
		Thread t = new Thread() {
			public void run() {
				Notification.notify("Notifica di partita", "HAI VINTO LA MANCHE!!!", false);
			}
		};
		t.start();
	}

	public void notifyMancheResult(String winner) {
		Thread t = new Thread() {
			@Override
			public void run() {
				String message = winner + "\nha vinto la manche ";
				Notification.notify("Notifica di partita", message, false);
			}
		};
		t.start();
	}

	public void notifyNewManche(int numManche) {
		Thread t = new Thread() {
			public void run() {
				String message = "la manche numero " + numManche + "\nsta per cominciare";
				Notification.notify("Notifica di partita", message, false);
			}
		};
		t.start();
	}

	public void notifyYourTurn() {
		Thread t = new Thread() {
			public void run() {
				Notification.notify("Notifica di partita", "è il tuo turno", false);
			}
		};
		t.start();
		yourTurn();
	}

	public void notifyEndMatch(String winner) {
		Thread t = new Thread() {
			@Override
			public void run() {
				String message = winner + "\nha vinto la partita ";
				match = null;
				TabPane.setVisible();
				frame.dispose();
				TabPane.notifyMatchEnd(message);
			}
		};
		t.start();
	}

	public void notifyMatchWin() {
		Thread t = new Thread() {
			public void run() {
				match = null;
				TabPane.setVisible();
				frame.dispose();
				TabPane.notifyMatchWin();
			}
		};
		t.start();
	}

	public void notifyTimeOut() {
		Thread t = new Thread() {
			public void run() {
				Notification.notify("Notifica di partita", "Tempo scaduto", false);
			}
		};
		t.start();
	}

	public void askForJolly() {
		Thread t = new Thread() {
			public void run() {
				Notification.notify("Notifica di partita", "Hai fatto un errore\nVuoi usare il jolly?", false);
			}
		};
		t.start();
	}

	public void notifyPlayerError(String name) {
		Thread t = new Thread() {
			public void run() {
				String message = name + "\nha commesso un errore";
				Notification.notify("Notifica di partita", message, false);
			}
		};
		t.start();
	}

	public void notifyNoMoreConsonant() {
		Thread t = new Thread() {
			public void run() {
				Notification.notify("Notifica di partita", "sono state chiamate tutte le consonanti", false);
			}
		};
		t.start();
	}

	public void updateTimer(int time) {
		Thread t = new Thread() {
			public void run() {
				lblTime.setText("" + time);
			}
		};
		t.start();
	}
}

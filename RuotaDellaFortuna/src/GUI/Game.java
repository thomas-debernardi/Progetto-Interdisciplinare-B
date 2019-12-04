package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;

import Game.RemoteMatch;
import Services.Client;
import Services.Notification;
import javafx.collections.ObservableList;

import java.awt.Frame;
import java.awt.Label;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Game window = new Game();
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
	public Game() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.getContentPane().setBackground(Color.GRAY);
		frame.setBackground(Color.GRAY);
		frame.setBounds(100, 100, 1487, 844);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		lblPlayer1 = new JLabel("Player 1");
		lblPlayer1.setForeground(Color.WHITE);
		lblPlayer1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPlayer1.setBounds(1117, 28, 85, 13);
		frame.getContentPane().add(lblPlayer1);

		lblPlayer2 = new JLabel("Player 2");
		lblPlayer2.setForeground(Color.WHITE);
		lblPlayer2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPlayer2.setBounds(1117, 132, 85, 13);
		frame.getContentPane().add(lblPlayer2);

		lblPlayer3 = new JLabel("Player 3");
		lblPlayer3.setForeground(Color.WHITE);
		lblPlayer3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPlayer3.setBounds(1117, 224, 85, 13);
		frame.getContentPane().add(lblPlayer3);

		lblJolly1 = new JLabel("Jolly: ");
		lblJolly1.setForeground(Color.WHITE);
		lblJolly1.setBounds(1117, 51, 46, 13);
		frame.getContentPane().add(lblJolly1);

		lblJolly2 = new JLabel("Jolly: ");
		lblJolly2.setForeground(Color.WHITE);
		lblJolly2.setBounds(1117, 155, 46, 13);
		frame.getContentPane().add(lblJolly2);

		lblJolly3 = new JLabel("Jolly: ");
		lblJolly3.setForeground(Color.WHITE);
		lblJolly3.setBounds(1117, 247, 46, 13);
		frame.getContentPane().add(lblJolly3);

		lblPartial1 = new JLabel("Partial");
		lblPartial1.setForeground(Color.WHITE);
		lblPartial1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPartial1.setBounds(1266, 51, 85, 13);
		frame.getContentPane().add(lblPartial1);

		lblPartial2 = new JLabel("Partial");
		lblPartial2.setForeground(Color.WHITE);
		lblPartial2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPartial2.setBounds(1266, 155, 85, 13);
		frame.getContentPane().add(lblPartial2);

		lblPartial3 = new JLabel("Partial");
		lblPartial3.setForeground(Color.WHITE);
		lblPartial3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPartial3.setBounds(1266, 247, 85, 13);
		frame.getContentPane().add(lblPartial3);

		lblTotal1 = new JLabel("Total");
		lblTotal1.setForeground(Color.WHITE);
		lblTotal1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTotal1.setBounds(1378, 51, 85, 13);
		frame.getContentPane().add(lblTotal1);

		lblTotal2 = new JLabel("Total");
		lblTotal2.setForeground(Color.WHITE);
		lblTotal2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTotal2.setBounds(1378, 155, 85, 13);
		frame.getContentPane().add(lblTotal2);

		lblTotal3 = new JLabel("Total");
		lblTotal3.setForeground(Color.WHITE);
		lblTotal3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTotal3.setBounds(1378, 247, 85, 13);
		frame.getContentPane().add(lblTotal3);

		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		btnExit.setForeground(Color.WHITE);
		btnExit.setBackground(Color.RED);
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnExit.setBounds(1388, 779, 85, 21);
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
		btnSpin.setBounds(997, 60, 73, 31);
		panelAction.add(btnSpin);
		btnSpin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wheelSpin();
			}
		});
		btnSpin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSpin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnSpin.setBackground(Color.GREEN);
		btnSpin.setForeground(Color.WHITE);

		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setNewPhrase("Calcio", "Leo Messi ha vinto il pallone di oro");
			}
		});
		btnNewButton.setBounds(610, 100, 85, 21);
		panelAction.add(btnNewButton);

		textFieldCharacter = new JTextField();
		textFieldCharacter.setBounds(259, 101, 96, 19);
		panelAction.add(textFieldCharacter);
		textFieldCharacter.setColumns(10);

		JButton btnCharacter = new JButton("SEND");
		btnCharacter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (textFieldCharacter.getText().equals("") || textFieldCharacter.getText().length() > 1) {
					Notification.notify("ATTENTION", "INSERT E CHARACTER", true);
				} else {
					updatePhrase(textFieldCharacter.getText().toUpperCase());
					textFieldCharacter.setText("");
				}

			}
		});
		btnCharacter.setBounds(365, 100, 85, 21);
		panelAction.add(btnCharacter);

		tfSolution = new JTextField();
		tfSolution.setHorizontalAlignment(SwingConstants.CENTER);
		tfSolution.setToolTipText("Insert the solution here");
		tfSolution.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tfSolution.setBounds(10, 10, 520, 31);
		panelAction.add(tfSolution);
		tfSolution.setColumns(10);

		btnSendSolution = new JButton("SEND SOLUTION");
		btnSendSolution.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					giveSolution();
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});
		btnSendSolution.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSendSolution.setBounds(540, 10, 155, 31);
		btnSendSolution.setEnabled(false);
		panelAction.add(btnSendSolution);

		btnJolly = new JButton("JOLLY");
		btnJolly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				giveJolly();
			}
		});
		btnJolly.setBounds(10, 57, 85, 21);
		panelAction.add(btnJolly);

		btnVocal = new JButton("VOCAL");
		btnVocal.setBounds(10, 87, 85, 21);
		panelAction.add(btnVocal);

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
		lblWheelResult.setBounds(1266, 404, 144, 181);
		frame.getContentPane().add(lblWheelResult);

		lblTurn = new JLabel("Is");
		lblTurn.setForeground(Color.WHITE);
		lblTurn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTurn.setBounds(10, 810, 169, 13);
		frame.getContentPane().add(lblTurn);

		letter = new JButton[4][15];

		for (int i = 0; i < 4; i++) {
			int x = i;

			for (int j = 0; j < 15; j++) {
				int z = j;
				letter[i][j] = new JButton();
				letter[i][j].setBackground(space);
				letter[i][j].setFont(new Font("Tahoma", Font.BOLD, 30));
				panelPhrase.add(letter[i][j]);
			}

		}

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

	}

	public void wheelSpin() {
		Thread t = new Thread() {
			public void run() {
				Wheel wheel = new Wheel();
				try {
					Thread.sleep(4 * 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				wheel.dispose();
			}
		};
		t.start();

		try {
			wheelResult = match.wheelSpin();
			wheelButtonPressed = true;
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	public void wheelResult(String result) {
		Thread t = new Thread() {
			public void run() {
				lblWheelResult.setText(result);
			}
		};
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
				} else if (nickName.equals(lblPlayer2.getText())) {
					lblTurn.setText("Is " + nickName + " turn");
					lblPlayer1.setForeground(Color.BLACK);
					lblPlayer2.setForeground(Color.GREEN);
					lblPlayer3.setForeground(Color.BLACK);
				} else if (nickName.equals(lblPlayer3.getText())) {
					lblTurn.setText("Is " + nickName + " turn");
					lblPlayer1.setForeground(Color.BLACK);
					lblPlayer2.setForeground(Color.BLACK);
					lblPlayer3.setForeground(Color.GREEN);
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
			TabPaneController.setVisible();
			frame.dispose();
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

	public void setNewPhrase(String theme2, String phrase2) {
		final String phrase = phrase2.toUpperCase();
		String theme = theme2;
		Thread t = new Thread() {
			public void run() {
				panelPhrase.removeAll();
				for (int i = 0; i < 4; i++) {
					for (int j = 0; j < 15; j++) {
						letter[i][j].setText("");
						letter[i][j].setBackground(space);
						panelPhrase.add(letter[i][j]);
					}
				}

				lblTheme.setText(theme);

				int column = 0;
				int row = 0;
				char car;
				StringTokenizer st = new StringTokenizer(phrase, " ',!?.:;\"/()\\^<>-+*0123456789");
				String s;
				int pointer = 0;

				while (st.hasMoreTokens()) {
					s = st.nextToken();
					System.out.println(s);
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

	public void hideAll() {
		btnJolly.setVisible(false);
		btnVocal.setVisible(false);
		btnSpin.setVisible(false);
		btnSendSolution.setVisible(false);
		tfSolution.setVisible(false);
		textFieldCharacter.setVisible(false);
	}

	public void disableAll() {
		btnJolly.setEnabled(false);
		btnVocal.setEnabled(false);
		btnSpin.setEnabled(false);
		btnSendSolution.setEnabled(false);
		tfSolution.setEnabled(false);
		textFieldCharacter.setEnabled(false);
	}

	public void activeAll() {
		btnJolly.setEnabled(true);
		btnVocal.setEnabled(true);
		btnSpin.setEnabled(true);
		btnSendSolution.setEnabled(true);
		tfSolution.setEnabled(true);
		textFieldCharacter.setEnabled(true);
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
					lblPartial1.setText(String.valueOf(partial));
					lblTotal1.setText(String.valueOf(total));
					lblJolly1.setText(String.valueOf(numJolly));
					break;
				case 2:
					lblPlayer3.setText(nickname);
					lblPartial1.setText(String.valueOf(partial));
					lblTotal1.setText(String.valueOf(total));
					lblJolly1.setText(String.valueOf(numJolly));
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

	
}
package GUI;

import java.io.IOException;
import java.rmi.RemoteException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Server.OTPHelper;
import Server.Server;
import Services.Client;
import Services.Notification;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

public class OTPRegistration {

	private JPanel contentPane;
	private JTextField textField;
	private int timeSeconds = 0;
	private int timeMinutes = 10;
	private Server server;
	private Client client;
	private OTPHelper otp;
	private JFrame frame;
	private JLabel lblTimer;

	static int milliseconds;
	static int seconds;
	static int minutes;
	static boolean state = true;
	
	int posX = 0, posY = 0;

	public OTPRegistration() {
		initialize();
	}

	/**
	 * Create the frame.
	 */
	public void initialize() {
		frame = new JFrame();
		frame.setBackground(Color.GRAY);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 314, 251);
		frame.setVisible(true);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField.setBounds(91, 88, 96, 19);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblInsertYourCode = new JLabel("Insert your code here");
		lblInsertYourCode.setForeground(Color.WHITE);
		lblInsertYourCode.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblInsertYourCode.setBounds(65, 65, 151, 13);
		contentPane.add(lblInsertYourCode);

		lblTimer = new JLabel("TIMER");
		lblTimer.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimer.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTimer.setForeground(Color.WHITE);
		lblTimer.setBackground(Color.WHITE);
		lblTimer.setBounds(10, 191, 96, 13);
		contentPane.add(lblTimer);

		JButton btnSend = new JButton("SEND");
		btnSend.setForeground(Color.WHITE);
		btnSend.setBackground(Color.GREEN);
		btnSend.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					enter();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSend.setBounds(65, 117, 151, 21);
		contentPane.add(btnSend);

		Registration.setOTP(this);
		runCountdown();
		try {
			client.setOtpPane(this);
		} catch (RemoteException e) {
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

	public void enter() throws IOException {
		if (textField.getText().equals(""))
			Notification.notify("ERROR", "NO OTP CODE", false);
		String otpStr = textField.getText();
		boolean check = false;
		try {
			check = otp.checkOTP(otpStr, client);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		if (!check) {
			Notification.notify("ERRROR", "INVALID OTP", true);
		} else {
			Notification.notify("OK", "CODE ACCEPTED", true);
			MainPane mp = new MainPane();
			// CHIUDERE APPLICAZIONE
			frame.dispose();
		}
	}

	public void notifyWrongOTP() {
		Notification.notify("ERRROR", "INVALID OTP", true);
	}

	public void setServer(Server server) {
		this.server = server;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public void setOtp(OTPHelper otp) {
		this.otp = otp;
	}

	public void runCountdown() {
		lblTimer.setText(String.valueOf(timeMinutes) + ":0" + String.valueOf(timeSeconds));
		Thread t = new Thread() {
			public void run() {
				for (;;) {
					if (state == true) {
						try {
							sleep(1);
							if (milliseconds > 1000) {
								milliseconds = 0;
								seconds++;
							}
							if (seconds > 60) {
								milliseconds = 0;
								seconds = 0;
								minutes++;
							}
							if (minutes > 10) {
								milliseconds = 0;
								seconds = 0;
								minutes = 10;
								Notification.notify("ERROR",
										"TIME OUT", false);
								state = false;
								break;
							}
							lblTimer.setText("" + minutes + ":" + seconds);
							milliseconds++;
						} catch (Exception e) {

						}
					}
				}
			}
		};
		t.start();
	}

}

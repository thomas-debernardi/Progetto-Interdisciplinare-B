package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
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
import java.awt.event.ActionEvent;

public class OTPRegistrationPane extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
    private int timeSeconds = 0;
    private int timeMinutes = 10;
    private Server server;
    private Client client;
    private OTPHelper otp;
    private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new OTPRegistrationPane();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public OTPRegistrationPane() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 450, 300);
		frame.setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(144, 115, 96, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblInsertYourCode = new JLabel("Insert your code here");
		lblInsertYourCode.setBounds(112, 65, 172, 13);
		contentPane.add(lblInsertYourCode);
		
		JButton btnSend = new JButton("SEND");
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
		btnSend.setBounds(261, 114, 85, 21);
		contentPane.add(btnSend);
		
		JLabel lblTimer = new JLabel("TIMER");
		lblTimer.setBounds(154, 194, 46, 13);
		contentPane.add(lblTimer);
	}
	
	public void enter() throws IOException {
		if(textField.getText().equals(""))
			Notification.notify("OTP Notification", "Nessun codice OTP inserito", false);
        String otpStr = textField.getText();
        boolean check = false;
        try {
            check = otp.checkOTP(otpStr, client);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        if (!check) {
            Notification.notify("OTP Notification", "CodiceOTP non valido\n", true);
        } else {
           LoginPlayer login = new LoginPlayer(); 
           //CHIUDERE APPLICAZIONE
           frame.setVisible(false);
           
        }
    }
	
	 public void notifyWrongOTP() {
	        Notification.notify("OTP Notification", "OTP inserito errato \nriprova", true);
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
	
	
	
	
}

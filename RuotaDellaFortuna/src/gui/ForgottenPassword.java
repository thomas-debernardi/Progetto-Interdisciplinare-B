package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.JTextField;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import net.miginfocom.swing.MigLayout;
import serverRdF.Server;
import services.Client;
import services.Notification;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;

public class ForgottenPassword {

	private JFrame frame;
	private JTextField textField;
    private Server server;
    private Client client;
    int posX = 0, posY = 0;

	/**
	 * Create the application.
	 */
	public ForgottenPassword() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
        MainPane.setResetPanel(this);

		frame = new JFrame();
		frame.setUndecorated(true);
		frame.getContentPane().setBackground(Color.GRAY);
		frame.setVisible(true);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField.setColumns(10);
		
		JLabel lblInsertYourEmail = new JLabel("Insert your Email");
		lblInsertYourEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblInsertYourEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblInsertYourEmail.setForeground(Color.WHITE);
		
		JButton btnSend = new JButton("SEND");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					enter();
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSend.setForeground(Color.WHITE);
		btnSend.setBackground(Color.GREEN);
		btnSend.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnBack.setForeground(Color.WHITE);
		btnBack.setBackground(Color.RED);
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(119)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(textField, Alignment.LEADING)
								.addComponent(lblInsertYourEmail, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(171)
							.addComponent(btnSend, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(355)
							.addComponent(btnBack)))
					.addContainerGap(18, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(78, Short.MAX_VALUE)
					.addComponent(lblInsertYourEmail, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnSend)
					.addGap(65)
					.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		frame.getContentPane().setLayout(groupLayout);
		frame.setBackground(Color.GRAY);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
	}
	
	   public void enter() throws RemoteException {
	        String mail = textField.getText();
	        boolean bool = server.resetPassword(client, mail);
	        if (!bool) {
	            Notification.notify("ERROR", "Email doesn't exist", true);
	        } else {
	            Notification.notify("OK", "Email with new password sended", false);
	            frame.dispose();
	        }
	    }

	    public void setServer(Server server) {
	        this.server = server;
	    }

	    public void setClient(Client client) {
	        this.client = client;
	    }
}

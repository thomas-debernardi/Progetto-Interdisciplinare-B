package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ConfirmRegistration extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldCode;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConfirmRegistration frame = new ConfirmRegistration();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ConfirmRegistration() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInsertTheCode = new JLabel("Insert the code sended by email");
		lblInsertTheCode.setBounds(115, 10, 163, 13);
		contentPane.add(lblInsertTheCode);
		
		textFieldCode = new JTextField();
		textFieldCode.setBounds(10, 41, 416, 13);
		contentPane.add(textFieldCode);
		textFieldCode.setColumns(10);
		
		JButton btnSend = new JButton("SEND");
		btnSend.setBounds(173, 115, 85, 21);
		contentPane.add(btnSend);
	}
}

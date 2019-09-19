package serverRdF;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.concurrent.Semaphore;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ServerGUI {
	
	private RealServer server;
	private Semaphore lock;

	private JPanel contentPane;
	
	public ServerGUI(RealServer server, Semaphore lock) {
		this.server = server;
		this.lock = lock;
	}
	
	//Lancia LogIn frame
		public void startLogin() {
			
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						LoginServerGUI window = new LoginServerGUI(server, lock);
						window.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		
		//Lancia registrazione
		public void startRegistration() {
			
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							RegisterServerGUI window = new RegisterServerGUI(server,lock);
							window.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
		}
		
		//Lancia pannello comandi
		public void startCommandPanel() {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						ServerPanelGUI window = new ServerPanelGUI(server,lock);
						window.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}

}

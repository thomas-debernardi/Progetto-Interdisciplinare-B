package serverRdF;

import java.rmi.registry.Registry;
import java.util.concurrent.Semaphore;

public class Main extends Thread {
	private Database db;
	private RealServer server;
	private ServerGUI gui;
	private Semaphore lock;
	
	private static Registry registry;
	private static ServerInterface stub;

	public static void main(String[] args) {
		Main main = new Main();
		main.start();
	}
	
	public void run() {
		server = new RealServer();
		lock = new Semaphore(1);
		try {
			lock.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		gui = new ServerGUI(server, lock);
		login();
	
		if(!server.checkAdminExistence()) {
			register();
		}
		gui.startCommandPanel();
	}
	
	private synchronized void login() {
		gui.startLogin();
		try {
			lock.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private synchronized void register() {
		gui.startRegistration();
		try {
			lock.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}

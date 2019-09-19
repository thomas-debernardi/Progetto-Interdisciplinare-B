package serverRdF;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RealServer implements ServerInterface {
	private Database db;
	
	private static RealServer server = null;
	private static Registry registry;
	private static ServerInterface stub;
	
	public RealServer() {
	}
	
	public void setDatabase(Database db) {
		this.db = db;
	}
	
	public boolean startServer() {
		boolean result = false;
		try {
			stub = (ServerInterface) UnicastRemoteObject.exportObject(server, 0);
			registry = LocateRegistry.createRegistry(8080);
			registry.rebind("server", stub);
			System.out.println("Server started.");
			result = true;
		} catch (RemoteException e) {
			System.out.println("An error has occured. Please try again.");
		}
		return result;
	}
	
	public void stopServer() {
		try {
			registry.unbind("server");
			stub = null;
			System.out.println("Server stopped.");
		} catch (RemoteException | NotBoundException e) {
		}
	}
	
	
	
	public boolean checkAdminExistence() {
		return db.checkAdminExistence();
	}
	
}

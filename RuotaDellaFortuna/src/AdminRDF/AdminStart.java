package AdminRDF;

import javax.swing.JFrame;

import GUI.WelcomePane;
import Services.AdminChecker;

public class AdminStart extends JFrame {
	public AdminStart() {
	}

	public static void main(String[] args) throws Exception{
        AdminChecker.setIsAdmin(true);
        WelcomePane connectionGUI = new WelcomePane();
        connectionGUI.startGameView();
	}
}

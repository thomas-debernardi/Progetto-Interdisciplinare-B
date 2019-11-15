package AdminRDF;

import javax.swing.JFrame;

import GUI.WelcomePane;
import Services.AdminCheck;

public class AdminStart extends JFrame {
	public AdminStart() {
	}

	public static void main(String[] args) throws Exception{
        AdminCheck.setIsAdmin(true);
        WelcomePane connectionGUI = new WelcomePane();
        connectionGUI.startGUI();
	}
}

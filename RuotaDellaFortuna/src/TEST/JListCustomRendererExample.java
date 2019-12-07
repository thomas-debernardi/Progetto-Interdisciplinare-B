package TEST;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

import GUI.GameBeingPlayed;
import Server.Server;
import Services.Client;
import Services.MatchData;

public class JListCustomRendererExample extends JFrame {
	private static Client client;
	private static Server server;

	public JListCustomRendererExample(ArrayList<MatchData> list2, Client client, Server server) {
		this.client = client;
		this.server = server;
		DefaultListModel<GameBeingPlayed2> listModel = new DefaultListModel<>();

		for (MatchData matchData : list2) {
			listModel.addElement(new GameBeingPlayed2(server, client, matchData));
		}


		// create the list
		JList<GameBeingPlayed2> countryList = new JList<GameBeingPlayed2>(listModel);
		countryList.setCellRenderer(new CountryRender());
		add(new JScrollPane(countryList));

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("JList Renderer Example");
		this.setSize(300, 200);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		setResizable(false);
	}

}

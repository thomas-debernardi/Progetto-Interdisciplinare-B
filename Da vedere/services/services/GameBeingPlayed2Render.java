package services;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import gui.GameBeingPlayed;

/**
 * Custom renderer to display a country's flag alongside its name
 *
 * @author wwww.codejava.net
 */
public class GameBeingPlayed2Render extends JLabel implements ListCellRenderer<GameBeingPlayed2> {
	private static boolean chosen2 = false;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Component getListCellRendererComponent(JList<? extends GameBeingPlayed2> list, GameBeingPlayed2 country,
			int index, boolean isSelected, boolean cellHasFocus) {
		setText("                    " + country.getPlayer1() + "                    " + country.getPlayer2()
				+ "                    " + country.getPlayer3());
		setForeground(Color.WHITE);
		setFont(new Font("Tahoma", Font.PLAIN, 18));
		if (chosen2 == false)
			if (isSelected) {
				setForeground(list.getSelectionForeground());
				new GameBeingPlayed(list.getSelectedValue().getServer(), list.getSelectedValue().getClient(),
						list.getSelectedValue().getMatchData());
				list.clearSelection();
				chosen2 = true;
			} else {
				chosen2 = false;
			}
		return this;
	}

	public static void setChosen(boolean chosen) {
		chosen2 = chosen;
	}


}

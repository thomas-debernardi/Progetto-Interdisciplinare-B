package TEST;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import GUI.GameBeingPlayed;

/**
 * Custom renderer to display a country's flag alongside its name
 *
 * @author wwww.codejava.net
 */
public class CountryRender extends JLabel implements ListCellRenderer<GameBeingPlayed2> {
	private boolean chosen = false;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Component getListCellRendererComponent(JList<? extends GameBeingPlayed2> list, GameBeingPlayed2 country,
			int index, boolean isSelected, boolean cellHasFocus) {

		setText(country.getPlayer1() + "                    " + country.getPlayer2() + "                    " + country.getPlayer3());
		if (chosen == false)
			if (isSelected) {
				setForeground(list.getSelectionForeground());
				new GameBeingPlayed(list.getSelectedValue().getServer(), list.getSelectedValue().getClient(),
						list.getSelectedValue().getMatchData());
				chosen = true;
			} else {
				setBackground(list.getBackground());
				setForeground(list.getForeground());
				chosen = false;
			}
		return this;
	}

}

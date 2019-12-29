package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import services.Notification;

public class PhrasesDAOImpl implements PhrasesDAO {
	private Connection con;

	PhrasesDAOImpl(Connection c) {
		this.con = c;
	}

	@Override
	public List<PhrasesDTO> get5Phrases(String idPlayer1, String idPlayer2, String idPlayer3) throws SQLException {
		String query5Phrases = "SELECT * FROM " + PhraseTable + " WHERE " + PhraseIdAttribute + " NOT IN " + "(SELECT "
				+ PhrasePhraseAttribute
				+ " FROM Manches M JOIN MancheJoiners MJ ON M.id=MJ.id AND M.number = MJ.number " + "WHERE idPlayer = '"
				+ idPlayer1 + "' OR idPlayer = '" + idPlayer2 + "' OR idPlayer = '" + idPlayer3 + "' " + "GROUP BY "
				+ PhrasePhraseAttribute + ");";
		Statement stmt = con.createStatement();
		ResultSet resultSet = stmt.executeQuery(query5Phrases);
		if (resultSet == null)
			return null;
		ArrayList<PhrasesDTO> pDTO = new ArrayList<>();
		int i = 0;
		while (resultSet.next() && i < 5) {
			pDTO.add(new PhrasesDTO(Integer.parseInt(resultSet.getString(PhraseIdAttribute)),
					resultSet.getString(PhraseThemeAttribute), resultSet.getString(PhrasePhraseAttribute)));
			i++;
		}
		return pDTO;
	}

	public boolean addPhrases(ArrayList<PhrasesDTO> phrases) throws SQLException {
		String queryAdd = "";
		for (PhrasesDTO phrasesDTO : phrases) {
			queryAdd = "INSERT INTO " + PhraseTable + " (" + PhraseThemeAttribute + "," + PhrasePhraseAttribute
					+ ") VALUES ";
			queryAdd += "('" + phrasesDTO.getTheme() + "','" + phrasesDTO.getPhrase() + "');";
			Statement stmt = con.createStatement();
			try {
				stmt.executeUpdate(queryAdd);
			} catch (SQLException e) {
				System.err.println("Phrases not added, maybe it's just in the database");
			} finally {
				queryAdd = "";
			}
		}
		return true;
	}

	public List<PhrasesDTO> getAllPhrases() throws SQLException {
		String query = "SELECT * FROM " + PhraseTable + ";";
		Statement stmt = con.createStatement();
		ResultSet resultSet = stmt.executeQuery(query);
		if (resultSet == null)
			return null;
		ArrayList<PhrasesDTO> pDTO = new ArrayList<>();
		while (resultSet.next())
			pDTO.add(new PhrasesDTO(Integer.parseInt(resultSet.getString(PhraseIdAttribute)),
					resultSet.getString(PhraseThemeAttribute), resultSet.getString(PhrasePhraseAttribute)));
		return pDTO;
	}

	public boolean deleteAllPhrases() throws SQLException {
		String query = "DELETE FROM " + PhraseTable + ";";
		Statement stmt = con.createStatement();
		try {
			stmt.execute(query);
		} catch (SQLException e) {
			System.err.println("Phrases not deleted");
			System.out.print(e);
		} finally {
			query = "";
		}
		return true;

	}

	public boolean deletePhrase(int position) throws SQLException {
		Integer pos = position;
		String query = "DELETE FROM " + PhraseTable + " WHERE " + PhraseIdAttribute + " = " + pos + ";";
		Statement stmt = con.createStatement();
		try {
			stmt.execute(query);
		} catch (SQLException e) {
			System.err.println("Phrases not deleted because it was used in a manche");
		} finally {
			query = "";
		}
		return true;
	}

	public boolean addPhrase(PhrasesDTO DTO) throws SQLException {
		String query = "INSERT INTO " + PhraseTable + " (" + PhraseThemeAttribute + "," + PhrasePhraseAttribute
				+ ") VALUES ";
		query += "('" + DTO.getTheme() + "','" + DTO.getPhrase() + "');";
		Statement stmt = con.createStatement();
		try {
			stmt.execute(query);
			Notification.notify("SUCCESS", "Phrase added");
		} catch (SQLException e) {
			System.err.println("Phrase not added");
		} finally {
			query = "";
		}
		return true;
	}

	public boolean uploadPhrase(PhrasesDTO DTO) throws SQLException {
		String query = "UPDATE " + PhraseTable + " SET " + PhraseThemeAttribute + " = '" + DTO.getTheme() + "', "
				+ PhrasePhraseAttribute + " = '" + DTO.getPhrase() + "' WHERE " + PhraseIdAttribute + " = '" + DTO.getId()
				+ "';";
		Statement stmt = con.createStatement();
		try {
			stmt.execute(query);
		} catch (SQLException e) {
			System.err.println("Phrase not uploaded");
		} finally {
			query = "";
		}
		return true;
	}
}

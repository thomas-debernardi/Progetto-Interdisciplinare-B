package serverRdF;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JList;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import database.DBManager;
import database.PhrasesDTO;

public class PhraseManager {
	private DBManager dbManager;
	private static PhraseManager phraseManager = null;

	private PhraseManager(DBManager dbManager) {
		this.dbManager = dbManager;
	}

	public static PhraseManager createPhraseManager(DBManager dbmng) {
		if (phraseManager == null) {
			phraseManager = new PhraseManager(dbmng);
			return phraseManager;
		} else {
			return phraseManager;
		}
	}

	public boolean addPhrases(File file) throws IOException, CsvValidationException {
		CSVReader reader = new CSVReader(new FileReader(file));
		ArrayList<PhrasesDTO> phrases = new ArrayList<>();
		String[] nextLine;
		String theme = "";
		String phrase = "";
		while ((nextLine = reader.readNext()) != null) {
			if (!nextLine[0].equals("")) {
				for (int i = 0; i < nextLine[0].length(); i++) {
					char c = nextLine[0].charAt(i);
					theme += c;
					if (c == '\'') {
						theme += "'";
					}
				}
				nextLine[1] = nextLine[1].trim();
				for (int i = 0; i < nextLine[1].length(); i++) {
					char c = nextLine[1].charAt(i);
					phrase += c;
					if (c == '\'') {
						phrase += "'";
					}
				}
				phrases.add(new PhrasesDTO(theme, phrase));
				theme = "";
				phrase = "";
			}
		}
		return dbManager.addPhrases(phrases);
	}
	
	public List<PhrasesDTO> getAllPhrases(){
		return dbManager.getAllPhrases();
	}
	
	public boolean addPhrase(String[] theme, String[] phrase) {
		ArrayList<PhrasesDTO> phrases = new ArrayList<>();
		for(int i = 0; i < theme.length; i++) {
			phrases.add(new PhrasesDTO(theme[i], phrase[i]));
		}
		return dbManager.addPhrases(phrases);
	}
	
	public boolean deleteAllPhrases() {
		return dbManager.deleteAllPhrases();
	}
	
	public boolean deletePhrase(int position) {
		return dbManager.deletePhrase(position);
	}
	
	
	
}

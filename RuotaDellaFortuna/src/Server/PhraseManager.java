package Server;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import Database.DBManager;
import Database.PhrasesDTO;

/**
 * Questa classe gestisce l'aggiunta di nuove frasi all'interno del database
 */
public class PhraseManager {
	private DBManager dbManager;
	private static PhraseManager phraseManager = null;

	private PhraseManager(DBManager dbManager) {
		this.dbManager = dbManager;
	}

	/**
	 * @param dbmng il riferimento a {@link DBManager}
	 * @return Il riferimento al singleton di {@link PhraseManager}
	 */
	public static PhraseManager createPhraseManager(DBManager dbmng) {
		if (phraseManager == null) {
			phraseManager = new PhraseManager(dbmng);
			return phraseManager;
		} else {
			return phraseManager;
		}
	}

	/**
	 * Questo metodo aggiunge al database le frasi ottenute attraverso un file di
	 * tipo .csv
	 *
	 * @param file il file da leggere
	 * @return <code>true</code> se l'inserimento avviene con successo,
	 *         <code>false</code> altrimenti
	 * @throws IOException            in caso di errori nella lettura del file
	 * @throws CsvValidationException
	 */
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
}

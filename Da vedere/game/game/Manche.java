package game;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import database.DBManager;
import database.ManchesDTO;
import database.MatchesDTO;
import database.PhrasesDTO;

public class Manche {

	private Turn turns;

	private List<PhrasesDTO> phrases;
	private int numManche;
	private DBManager dbManager;
	private String match;
	private LocalDateTime matchTime;
	private ArrayList<String> calledConsonant;

	/**
	 * Questo costruttore crea un oggetto di tipo Manche che contiene i relativi dati
	 * 
	 * @param dbmng contiene il gestore del database
	 * @param id contiene l'identificativo univoco della manche
	 * @param time contiene la durata della manche corrente
	 */
	public Manche(DBManager dbmng, String id, LocalDateTime time) {
		turns = new Turn(this);
		phrases = new ArrayList<PhrasesDTO>();
		numManche = 0;
		dbManager = dbmng;
		this.match = id;
		matchTime = time;
		calledConsonant = new ArrayList<>();
	}

	/**
	 * Metodo getter per ottenere il turno corrente
	 * 
	 * @return turns contiene il turno corrente che viene restituito
	 */
	public Turn getTurns() {
		return turns;
	}

	/**
	 * Metodo setter che inizializza il turno inserito come parametro
	 * 
	 * @param turns è il turno che si desidera inizializzare
	 */
	public void setTurns(Turn turns) {
		this.turns = turns;
	}

	/**
	 * Metodo getter per ottenere la lista di frasi per la partita corrente
	 * 
	 * @return phrases contiene la lista di frasi attualmente inserita
	 */
	public List<PhrasesDTO> getPhrases() {
		return phrases;
	}

	/**
	 * Metodo setter per inizializzare la lista di frasi per la partita corrente
	 * 
	 * @param phrases è la lista di frasi che si vuole inserire nella partita
	 */
	public void setPhrases(List<PhrasesDTO> phrases) {
		this.phrases = phrases;
	}

	/**
	 * Metodo getter per ottenere il numero di manche corrente
	 * 
	 * @return numManche contiene il numero della manche
	 */
	public int getNumManche() {
		return numManche;
	}

	/**
	 * Metodo setter per inizializzare il numero di manche corrente
	 * 
	 * @param numManche è il numero della manche che sta per iniziare
	 */
	public void setNumManche(int numManche) {
		this.numManche = numManche;
	}

	/**
	 * Metodo getter per ottenere il gestore del database
	 * 
	 * @return dbManager contiene il gestore del database del sistema
	 */
	public DBManager getDbManager() {
		return dbManager;
	}

	/**
	 * Metodo setter per inizializzare il gestore di database con quello di sistema
	 * 
	 * @param dbManager è il gestore di database che si vuole inserire
	 */
	public void setDbManager(DBManager dbManager) {
		this.dbManager = dbManager;
	}

	/**
	 * Metodo getter per ottenere l'oggetto di tipo match corrente
	 * 
	 * @return match è l'oggetto di tipo Match che contiene la partita corrente
	 */
	public String getMatch() {
		return match;
	}

	/**
	 * Metodo setter per inizializzare l'oggetto di tipo match attuale
	 * 
	 * @param match contiene il match a cui si deve giungere
	 */
	public void setMatch(String match) {
		this.match = match;
	}

	/**
	 * Metodo getter per ottenere la frase per la manche attuale
	 * 
	 * @return phrases.get(numManche - 1) ritorna la frase utilizzata nella manche attuale
	 */
	public PhrasesDTO getCurrentPhrase() {
		return phrases.get(numManche - 1);
	}

	/**
	 * Metodo per la verifica della correttezza della consonante proposta dall'utente
	 * 
	 * @param givenLetter contiene la lettera scelta dall'utente
	 * @return ritorna true se la lettera inserita non è presente nel registro delle lettere già dette, false altrimenti
	 */
	public boolean checkConsonant(String givenLetter) {
		if (calledConsonant.isEmpty())
			return true;
		else {
			for (String letter : calledConsonant) {
				if (letter.equals(givenLetter)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Metodo per tenere traccia delle lettere nominate dai concorrenti
	 * 
	 * @param letter contiene la lettera scelta dall'utente
	 * @return ritorna true se sono state dette tutte le lettere, false altrimenti
	 */
	public boolean addConsonant(String letter) {
		calledConsonant.add(letter);
		if (calledConsonant.size() == 26)
			return true;
		else
			return false;
	}

	/**
	 * Metodo per terminare la manche in caso di vittoria di un concorrente
	 * 
	 * @param winner contiene l'oggetto di tipo Player del vincitore
	 * @return ritorna true se sta per iniziare una nuova manche, false altrimenti
	 */
	public boolean endManche(Player winner) {
		if (numManche != 0) {
			ManchesDTO manche = new ManchesDTO();
			manche.setMatch(new MatchesDTO(match, matchTime));
			if (winner != null) {
				manche.setNumber(numManche);
				PhrasesDTO newPhrase = getCurrentPhrase();
				String them = Match.prepareStringForDB(newPhrase.getTheme());
				String phras = Match.prepareStringForDB(newPhrase.getPhrase());
				manche.setPhrase(new PhrasesDTO(newPhrase.getId(),them, phras));
				dbManager.addMancheWinner(winner.getIdPlayer(), manche, winner.getPartialPoints());
			}
			setNumManche(numManche + 1);
			manche.setNumber(numManche);
			if (numManche < 6) {
				PhrasesDTO newPhrase = getCurrentPhrase();
				String theme = Match.prepareStringForDB(newPhrase.getTheme());
				String phrase = Match.prepareStringForDB(newPhrase.getPhrase());
				manche.setPhrase(new PhrasesDTO(newPhrase.getId(),theme, phrase));
				calledConsonant = new ArrayList<String>();
				boolean tur = turns.saveMoves(dbManager);
				boolean man = true;
				dbManager.addManche(manche);
				if (man && tur) {
					return true;
				} else
					return false;
			} else
				return false;
		} else
			return true;
	}
}

package Game;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import Database.DBManager;
import Database.ManchesDTO;
import Database.MatchesDTO;
import Database.PhrasesDTO;

/**
 * Classe che gestisce le manche e i turni.
 */
public class Manche {

    private Turn turns;
    /*
    La frase "attiva" è quella in posizione numManche-1 (dato che le manche sono numerate da 1 a 5)
     */
    private List<PhrasesDTO> phrases;
    private int numManche;
    private DBManager dbManager;
    private String match;
    private LocalDateTime matchTime;
    private ArrayList<String> calledConsonant;

    public Manche(DBManager dbmng, String id, LocalDateTime time) {
        turns = new Turn(this);
        phrases = new ArrayList<PhrasesDTO>();
        numManche = 0;
        dbManager = dbmng;
        this.match = id;
        matchTime = time;
        calledConsonant = new ArrayList<>();
    }

    public Turn getTurns() {
        return turns;
    }

    public void setTurns(Turn turns) {
        this.turns = turns;
    }

    public List<PhrasesDTO> getPhrases() {
        return phrases;
    }

    public void setPhrases(List<PhrasesDTO> phrases) {
        this.phrases = phrases;
    }

    public int getNumManche() {
        return numManche;
    }

    public void setNumManche(int numManche) {
        this.numManche = numManche;
    }

    public DBManager getDbManager() {
        return dbManager;
    }

    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    public String getMatch() {
        return match;
    }

    public void setMatch(String match) {
        this.match = match;
    }

    public PhrasesDTO getCurrentPhrase() {
        return phrases.get(numManche - 1);
    }

    /**
     * Controlla se la consonante chiamata e' gia' stata scelta
     * @param givenLetter la consonante chiamata
     * @return true se non e' stata chiamata, false altrimenti
     */
    public boolean checkConsonant(String givenLetter){
        if(calledConsonant.isEmpty())
            return true;
        else{
            for(String letter : calledConsonant){
                if(letter.equals(givenLetter)){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean addConsonant(String letter){
        calledConsonant.add(letter);
        if(calledConsonant.size() == 26)
            return true;
        else
            return false;
    }


    /**
     * Questo metodo aggiorna l'oggetto per permettere l'inizo della manche successiva. La lista di mosse viene svuotata e le mosse vengono salvate nel database
     *
     * @param winner l'eventuale vincitore della manche
     * @return <code>true</code> se il metodo viene eseguito con successo, <code>false</code> altrimenti
     */
    public boolean endManche(Player winner) {
        if(numManche != 0) {
            ManchesDTO manche = new ManchesDTO();
            manche.setMatch(new MatchesDTO(match, matchTime));
            if (winner != null) {
                manche.setNumber(numManche);
                PhrasesDTO newPhras = getCurrentPhrase();
                String them = Match.prepareStringForDB(newPhras.getTheme());
                String phras = Match.prepareStringForDB(newPhras.getPhrase());
                manche.setPhrase(new PhrasesDTO(them, phras));
                dbManager.addMancheWinner(winner.getIdPlayer(), manche, winner.getPartialPoints());
            }
            setNumManche(numManche + 1);
            manche.setNumber(numManche);
            if(numManche < 6) {
                PhrasesDTO newPhrase = getCurrentPhrase();
                String theme = Match.prepareStringForDB(newPhrase.getTheme());
                String phrase = Match.prepareStringForDB(newPhrase.getPhrase());
                manche.setPhrase(new PhrasesDTO(theme, phrase));
                calledConsonant = new ArrayList<String>();
                boolean tur = turns.saveMoves(dbManager);
                boolean man = true;
                dbManager.addManche(manche);
                if (man && tur) {
                    return true;
                } else
                    return false;
            }else
                return false;
        }else
            return true;
    }
}

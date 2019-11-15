package Database;
/**
 * La classe dei Data Transfer Objects relativi alle tuple della tabella "Manches". I suoi metodi permettono la costruzione e l'ottenimento dei campi dell'oggetto
 */
public class ManchesDTO {
    private int number;
    private MatchesDTO match;
    private PhrasesDTO phrase;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public MatchesDTO getMatch() {
        return match;
    }

    public void setMatch(MatchesDTO match) {
        this.match = match;
    }

    public PhrasesDTO getPhrase() {
        return phrase;
    }

    public void setPhrase(PhrasesDTO phrase) {
        this.phrase = phrase;
    }

    public ManchesDTO(int number, MatchesDTO match, PhrasesDTO phrase) {
        this.number = number;
        this.match = match;
        this.phrase = phrase;
    }

    public ManchesDTO(){

    }
}


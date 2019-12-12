package Database;

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


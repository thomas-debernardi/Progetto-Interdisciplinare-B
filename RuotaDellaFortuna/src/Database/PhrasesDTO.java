package Database;
/**
 * La classe dei Data Transfer Objects relativi alle tuple della tabella "Phrases". I suoi metodi permettono la costruzione e l'ottenimento dei campi dell'oggetto
 */
public class PhrasesDTO {
    private String theme;
    private String phrase;

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public PhrasesDTO(String theme, String phrase) {
        this.theme = theme;
        this.phrase = phrase;
    }

    public PhrasesDTO() {
    }
}

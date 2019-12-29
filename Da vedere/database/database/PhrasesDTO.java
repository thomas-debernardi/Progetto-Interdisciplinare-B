package database;

import java.io.Serializable;

public class PhrasesDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	private String theme;
    private String phrase;
    private int id;

    public String getTheme() {
        return theme;
    }
    
    public void setId(int id) {
    	this.id = id;
    }
    
    public int getId() {
    	return this.id;
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

    public PhrasesDTO(int id, String theme, String phrase) {
    	this.id = id;
        this.theme = theme;
        this.phrase = phrase;
    }
    
    public PhrasesDTO(String theme, String phrase) {
        this.theme = theme;
        this.phrase = phrase;
    }

    public PhrasesDTO() {
    }
    
    
}

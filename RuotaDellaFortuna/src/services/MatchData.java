package services;


import java.io.Serializable;

public class MatchData implements Serializable {
    public static final long serialVersionUID = 1L;

    private String Player1;
    private String Player2;
    private String Player3;
    private String date;
    private String time;
    private int numManche;
    private boolean onGoing;
    private String idMatch;

    /**
     * Questo metodo visualizza il primo giocatore
     * 
     * @return nome giocatore
     */
    public String getPlayer1() {
        return Player1;
    }

    /**
     * Questo metodo visualizza il secondo giocatore
     * 
     * @return nome giocatore
     */
    public String getPlayer2() {
        return Player2;
    }

    /**
     * Questo metodo visualizza il terzo giocatore
     * 
     * @return nome giocatore
     */
    public String getPlayer3() {
        return Player3;
    }

    /**
     * Questo metodo visualizza la data della partita
     * 
     * @return data
     */
    public String getDate() {
        return date;
    }

    /**
     * Questo metodo visualizza il tempo della partita
     * 
     * @return tempo partita
     */
    public String getTime() {
        return time;
    }

    /**
     * Questo metodo visualizza il numero della manche attuale
     * 
     * @return numero manche
     */
    public int getNumManche() {
        return numManche;
    }

    /**
     * Questo metodo visualizza se la partita è in corso
     * 
     * @return <code>true</code> se è attualmente in corso, altrimenti <code>false</code>
     */
    public boolean isOnGoing() {
        return onGoing;
    }

    /**
     * Questo metodo permette di inizializzare il primo giocatore 
     * 
     * @param player1 nome giocatore
     */
    public void setPlayer1(String player1) {
        Player1 = player1;
    }

    /**
     * Questo metodo permette di inizializzare il secondo giocatore 
     * 
     * @param player1 nome giocatore
     */
    public void setPlayer2(String player2) {
        Player2 = player2;
    }

    /**
     * Questo metodo permette di inizializzare il terzo giocatore 
     * 
     * @param player1 nome giocatore
     */
    public void setPlayer3(String player3) {
        Player3 = player3;
    }

    /**
     * Questo metodo permette di inizializzare la data del match
     * 
     * @param date data da inserire
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Questo metodo permette di inserire il tempo del match
     * @param time tempo da inserire
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Questo metodo permette di inserie il numero di manche attuali
     * @param numManche numero della manche 
     */
    public void setNumManche(int numManche) {
        this.numManche = numManche;
    }

    /**
     * Questo metodo permette di definire se un match è in corso
     * @param onGoing <code>true</code> se è in corso, altrimenti <code>false</code>
     */
    public void setOnGoing(boolean onGoing) {
        this.onGoing = onGoing;
    }

    /**
     * Questo metodo permette di visualizzare l'identificativo del match
     * @return id match
     */
    public String getIdMatch() {
        return idMatch;
    }

    /**
     * Questo metodo permette di inserire l'identificativo del match
     * @param idMatch identificativo match
     */
    public void setIdMatch(String idMatch) {
        this.idMatch = idMatch;
    }

    public MatchData(){}
}

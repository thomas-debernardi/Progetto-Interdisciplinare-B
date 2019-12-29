package game;

import java.util.UUID;

public class Move {
    private String idPlayer;
    private String moveType;
    private int outCome;
    private String idMatch;
    private int numManche;
    private String moveId;

    /**
     * Metodo Getter per ottenere l'ID di un giocatore
     * 
     * @return idplayer ritorna il valore dell'ID
     */
    public String getPlayer() {
        return idPlayer;
    }

    /**
     * Metodo Setter per inizializzare l'ID del giocatore
     * 
     * @param player contiene l'ID del giocatore da inserire
     */
    public void setPlayer(String player) {
        this.idPlayer = player;
    }

    /**
     * Metodo Getter per ottenere il tipo di mossa eseguita
     * 
     * @return moveType ritorna il tipo di mossa
     */
    public String getMoveType() {
        return moveType;
    }

    /**
     * Metodo Setter per inizializzare il tipo di Mossa
     * 
     * @param moveType contiene il tipo di mossa da inserire
     */
    public void setMoveType(String moveType) {
        this.moveType = moveType;
    }

    /**
     * Metodo Getter per ottenere il risultato della mossa
     * 
     * @return outCome ottiene il risultato intero della mossa
     */
    public int getOutCome() {
        return outCome;
    }

    /**
     * Metodo Setter per inizializzare il risultato della mossa
     * 
     * @param outCome contiene il risultato da inserire
     */
    public void setOutCome(int outCome) {
        this.outCome = outCome;
    }

    /**
     * Metodo Getter per ottenere l'ID del match corrente
     * 
     * @return idMatch ritorna l'ID del match
     */
    public String getIdMatch() {
        return idMatch;
    }

    /**
     * Metodo Setter per inizializzare l'ID del match
     * 
     * @param idMatch contiene l'ID da inserire
     */
    public void setIdMatch(String idMatch) {
        this.idMatch = idMatch;
    }

    /**
     * Metodo Getter per ottenere il numero della manche corrente
     * 
     * @return numManche ritorna il numero di manche
     */
    public int getNumManche() {
        return numManche;
    }

    /**
     * Metodo Setter per inizializzare il numero di manche
     * 
     * @param numManche contiene il numero di manche da inserire
     */
    public void setNumManche(int numManche) {
        this.numManche = numManche;
    }

    /**
     * Metodo Getter per ottenere l'ID della mossa eseguita
     * 
     * @return moveId ritorna l'ID della mossa
     */
    public String getMoveId() {
        return moveId;
    }

    /**
     *  Metodo Setter per inizializzare l'ID della mossa
     * 
     * @param moveId contiene l'ID da inserire
     */
    public void setMoveId(String moveId) {
        this.moveId = moveId;
    }

    public Move(String player, String moveType, int outCome, String idMatch, int numManche) {
        this.idPlayer = player;
        this.moveType = moveType;
        this.outCome = outCome;
        this.idMatch = idMatch;
        this.numManche = numManche;
        moveId = UUID.randomUUID().toString();
    }

    public Move(){}
}

package Game;

import java.util.UUID;

/**
 * Classe di supporto per tenere traccia delle mosse prima che vengano salvate nel database alla conclusione di una manche. Contiene l'id del match, il numero della manche,
 * l'id del giocatore che ha effettuato la mossa, il tipo di mossa eseguita e il relativo risultato. I metodi permettono di accedere e modificare tali informazioni
 */
public class Move {
    private String idPlayer;
    private String moveType;
    private int outCome;
    private String idMatch;
    private int numManche;
    private String moveId;

    public String getPlayer() {
        return idPlayer;
    }

    public void setPlayer(String player) {
        this.idPlayer = player;
    }

    public String getMoveType() {
        return moveType;
    }

    public void setMoveType(String moveType) {
        this.moveType = moveType;
    }

    public int getOutCome() {
        return outCome;
    }

    public void setOutCome(int outCome) {
        this.outCome = outCome;
    }

    public String getIdMatch() {
        return idMatch;
    }

    public void setIdMatch(String idMatch) {
        this.idMatch = idMatch;
    }

    public int getNumManche() {
        return numManche;
    }

    public void setNumManche(int numManche) {
        this.numManche = numManche;
    }

    public String getMoveId() {
        return moveId;
    }

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

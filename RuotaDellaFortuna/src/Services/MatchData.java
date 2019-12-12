package Services;


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

    public String getPlayer1() {
        return Player1;
    }

    public String getPlayer2() {
        return Player2;
    }

    public String getPlayer3() {
        return Player3;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public int getNumManche() {
        return numManche;
    }

    public boolean isOnGoing() {
        return onGoing;
    }

    public void setPlayer1(String player1) {
        Player1 = player1;
    }

    public void setPlayer2(String player2) {
        Player2 = player2;
    }

    public void setPlayer3(String player3) {
        Player3 = player3;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setNumManche(int numManche) {
        this.numManche = numManche;
    }

    public void setOnGoing(boolean onGoing) {
        this.onGoing = onGoing;
    }

    public String getIdMatch() {
        return idMatch;
    }

    public void setIdMatch(String idMatch) {
        this.idMatch = idMatch;
    }

    public MatchData(){}
}

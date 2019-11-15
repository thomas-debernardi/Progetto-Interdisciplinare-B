package Database;

/**
 * La classe dei Data Transfer Objects relativi alle tuple della tabella "Moves". I suoi metodi permettono la costruzione e l'ottenimento dei campi dell'oggetto
 */
public class MovesDTO {
    private UsersDTO player;
    private String moveType;
    private int outcome;
    private ManchesDTO manche;

    public UsersDTO getPlayer(){
        return player;
    }

    public String getMoveType(){
        return moveType;
    }

    public int getOutcome(){
        return outcome;
    }

    public void setPlayer(UsersDTO p){
        player = p;
    }

    public void setMoveType(String move){
        moveType = move;
    }

    public void setOutcome(int res){
        outcome = res;
    }

    public ManchesDTO getManche() {
        return manche;
    }

    public void setManche(ManchesDTO manche) {
        this.manche = manche;
    }

    public MovesDTO(UsersDTO user, String move, int res, ManchesDTO manche){
        player = user;
        moveType = move;
        outcome = res;
        this.manche = manche;
    }

    public MovesDTO(){}
}

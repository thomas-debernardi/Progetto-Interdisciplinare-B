package Game;

        import java.util.ArrayList;
        import java.util.List;
        import java.util.UUID;

import Database.DBManager;


/**
 * Questa classe contiene tutte le mosse eseguite in un turno
  */
public class Turn {

    private List<Move> moves;
    private Manche manche;

    public Turn(Manche manche){
        moves = new ArrayList<>();
        this.manche = manche;
    }

    /**
     * Questo metodo si occupa di salvare tutte le mosse eseguite da un giocatore in suo turno alla fine della manche e svuota la lista
     *
     * @param dbManager riferimento all'oggetto di tipo {@link DBManager} per l'accesso al database
     * @return <code>true</code> se l'operazione va a buon fine, <code>false</code> se almeno una delle mosse non e' stata salvata correttamente
     */
    public boolean saveMoves(DBManager dbManager){
        boolean success = true;
        for(Move move : moves){
                if(success == true) {
                    success = dbManager.addMove(move);
                }else{
                    dbManager.addMove(move);
                }
        }
        for(int i=moves.size()-1; i>=0; i--)
            moves.remove(i);
        return success;
    }

    /**
     * Questo metodo aggiunge una mossa appena eseguita all lista.
     *
     * @param idPlayer l'id del giocatore che ha eseguito la mossa
     * @param moveType il tipo di mossa eseguita
     * @param outcome il risultato della mossa
     */
    public void addMove(String idPlayer, String moveType, int outcome){
        Move move = new Move();
        move.setIdMatch(manche.getMatch());
        move.setNumManche(manche.getNumManche());
        move.setPlayer(idPlayer);
        move.setMoveType(moveType);
        move.setOutCome(outcome);
        move.setMoveId(UUID.randomUUID().toString());

        moves.add(move);
    }

    public Move getLastMove(){
        return moves.get(moves.size()-1);
    }
}

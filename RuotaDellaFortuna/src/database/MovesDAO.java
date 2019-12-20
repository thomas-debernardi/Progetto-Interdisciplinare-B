package database;

import java.sql.SQLException;

import game.Move;


public interface MovesDAO {
    String MovesTable = "moves";
    String MovesIdPlayerAttribute = "id";
    String MovesMoveTypeAttribute = "movetype";
    String MovesOutcomeAttribute = "outcome";
    String MovesIdMatchAttribute = "idmanche";
    String MovesMancheNumberAttribute = "number";
    String MovesMoveIdAttribute = "moveid";


    boolean addMove(Move move) throws SQLException;

  
    public MovesDTO getBestMove() throws SQLException;


    public int getAverageMovesPerManche(int numManche) throws SQLException;

    int getAllPassedTurnByUser(String id) throws SQLException;

    int getAllLossesByUser(String id) throws SQLException;
}

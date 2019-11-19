package Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Game.Move;

/**
 * Implementazione dell'interfaccia {@link MovesDAO}
 */
public class MovesDAOImpl implements MovesDAO {
    private Connection con;

    MovesDAOImpl(Connection c){
        con = c;
    }

    @Override
    public boolean addMove(Move move) throws SQLException{
        String queryAdd = "INSERT INTO " + MovesTable + "(" + MovesIdPlayerAttribute + "," + MovesMoveTypeAttribute + "," + MovesOutcomeAttribute + "," + MovesIdMatchAttribute + "," + MovesMancheNumberAttribute + ","+MovesMoveIdAttribute+") " +
                            "VALUES ('" + move.getPlayer() + "','" + move.getMoveType() + "'," + move.getOutCome() + ",'" + move.getIdMatch() + "'," + move.getNumManche() + ",'"+ move.getMoveId()+"')";
        Statement stmt = con.createStatement();
        return stmt.executeUpdate(queryAdd) > 0;
    }

    @Override
    public MovesDTO getBestMove() throws SQLException{
        String queryGet = "SELECT * FROM "+UsersDAO.UserTable+" M JOIN "+MovesTable+" U ON M.id = U.id JOIN "+ManchesDAO.ManchesTable+" MT ON U.idmanche = MT.id AND U.number = MT.number "+
                "JOIN "+PhrasesDAO.PhraseTable+" PT ON MT.phrase = PT.phrase WHERE U.outcome = (SELECT MAX("+MovesOutcomeAttribute+") FROM "+MovesTable+");";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(queryGet);
        if(rs.next()) {
            MovesDTO result = new MovesDTO();
            UsersDTO user = new UsersDTO();
            user.setNickname(rs.getString(UsersDAO.UserNicknameAttribute));
            PhrasesDTO phrase = new PhrasesDTO(rs.getString(PhrasesDAO.PhraseThemeAttribute), rs.getString(PhrasesDAO.PhrasePhraseAttribute));
            result.setManche(new ManchesDTO(rs.getInt(MovesMancheNumberAttribute), null, phrase));
            result.setPlayer(user);
            result.setMoveType(rs.getString(MovesMoveTypeAttribute));
            result.setOutcome(rs.getInt(MovesOutcomeAttribute));
            return result;
        }else
            return null;
    }

    @Override
    public int getAverageMovesPerManche(int numManche) throws SQLException{
        if(numManche == 0) {
            return 0;
        }else{
            String queryGet = "SELECT COUNT(*) AS count FROM "+MovesTable+" MT JOIN "+ManchesDAO.ManchesTable+" MAN ON MT.idmanche = MAN.id AND MT.number = MAN.number JOIN "+MancheWinnersDAO.manchesWinnersTable+" " +
                    "MW ON MAN.id = MW.id AND MAN.number = MW.number";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(queryGet);
            if(rs.next()) {
                int result = (rs.getInt("count") / numManche);
                return result;
            }else{
                return 0;
            }
        }
    }

    @Override
    public int getAllPassedTurnByUser(String id) throws SQLException {
        String queryGet ="SELECT COUNT(*) AS count FROM "+MovesTable+ " WHERE "+MovesIdPlayerAttribute +" = '"+id+"' AND "+MovesOutcomeAttribute+" = 0;";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(queryGet);
        if(rs.next()) {
            return rs.getInt("count");
        }else
            return 0;
    }

    @Override
    public int getAllLossesByUser(String id) throws SQLException {
        String queryGet ="SELECT COUNT(*) AS count FROM "+MovesTable+ " WHERE "+MovesIdPlayerAttribute +" = '"+id+"' AND "+MovesMoveTypeAttribute+" = 'perde';";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(queryGet);
        if(rs.next()) {
            return rs.getInt("count");
        }else
            return 0;
    }
}

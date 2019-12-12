package Database;
import java.sql.SQLException;


public interface MancheWinnersDAO {
    String manchesWinnersTable = "manchewinners";
    String manchesWinnersidAttribute = "id";
    String manchesWinnersNumberAttribute = "number";
    String manchesWinnersidPlayerAttribute = "idplayer";
    String manchesWinnersAmountAttribute = "amount";


    boolean addMancheWinner(String idPlayer, ManchesDTO manche, int amount) throws SQLException;


    int getNumWinnedManches() throws SQLException;

    int getMancheWonByUser(String id) throws SQLException;
}

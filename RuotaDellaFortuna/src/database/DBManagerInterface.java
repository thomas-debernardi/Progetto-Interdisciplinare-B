package database;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import game.Move;
import services.User;

public interface DBManagerInterface {

    boolean addMatch(String id, LocalDateTime time);


    boolean addUser(User user, boolean isAdmin);

    UsersDTO getUserByEmail(String email);

    UsersDTO getUserByNickname(String nickname);


    UsersDTO getUserById(String id);



    List<UsersDTO> getAllAdmin();

    boolean getAnyAdmin();


    boolean deleteUser(String id);


    int checkLogin(String email, String password, boolean admin);


    List<PhrasesDTO> get5Phrases(String idPlayer1, String idPlayer2, String idPlayer3);


    boolean addPhrases(ArrayList<PhrasesDTO> phrases);

    List<PhrasesDTO> getAllPhrases();


    boolean deleteMatch(String idMatch);


    boolean addMove(Move move);



    boolean addManche(ManchesDTO manche);


    boolean addMancheWinner(String idPlayer, ManchesDTO manche, int amount);


    boolean addMancheJoiner(String idMatch, int numManche, String userId, boolean observer);


    boolean addMatchWinner(String idMatch, String idPlayer, int amount);


    boolean updateUser(UsersDTO user);


    MovesDTO getBestMove();


    int getAverageMovesPerManche();


    int getWonManchesByUser(String id);


    int getManchePlayedByUser(String id);

    int getMatchesPlayedByUser(String id);

    int getWonMatchesByUser(String id);

    int getObservedManchesByUser(String id);


    int getObservedMatchesByUser(String id);


    int getAveragePointsWonByUser(String id);


    int getAveragePassedTurnPerMancheByUser(String id);


    int getAveragePassedTurnPerMatchByUser(String id);


    int getAverageLossPerMancheByUser(String id);


    int getAverageLossPerMatchByUser(String id);


    UsersDTO getBestUserForManche();


    UsersDTO getBestUserForMatch();


    UsersDTO getUserForMoreManchesPlayed();


    UsersDTO getUserForBestMancheAverage();


    UsersDTO getUserForMostLostTurn();


    UsersDTO getUserForMostLosses();
}

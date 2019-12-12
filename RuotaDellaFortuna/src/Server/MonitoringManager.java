package Server;

import Database.DBManager;
import Database.MovesDTO;
import Database.UsersDTO;


public class MonitoringManager {
    private DBManager dbManager;
    private static MonitoringManager monitoringManager;
    private MonitoringManager(DBManager dbmng){
        dbManager = dbmng;
    }

    public static MonitoringManager createMonitoringManager(DBManager dbmng){
        if(monitoringManager == null){
            monitoringManager = new MonitoringManager(dbmng);
            return monitoringManager;
        }else{
            return monitoringManager;
        }
    }



    public String getBestMove(){
        MovesDTO bestMove = dbManager.getBestMove();
        String result;
        if(bestMove != null) {
            result = "" + bestMove.getPlayer().getNickname() + " " + bestMove.getMoveType() + " " + bestMove.getManche().getPhrase().getPhrase();
            return result;
        }else
            return "- - -";
    }


    public int averageMovesPerManches(){
        return dbManager.getAverageMovesPerManche();
    }


    public String perPlayerStats(String nickname){
        UsersDTO user = dbManager.getUserByNickname(nickname);
        if(user != null){
            String idUser = user.getId();
            int numManchePlayed = dbManager.getManchePlayedByUser(idUser);
            int numMatchPlayed = dbManager.getMatchesPlayedByUser(idUser);
            int numMancheWon = dbManager.getWonManchesByUser(idUser);
            int numMatchWon = dbManager.getWonMatchesByUser(idUser);
            int numMancheObserved = dbManager.getObservedManchesByUser(idUser);
            int numMatchObserved = dbManager.getObservedMatchesByUser(idUser);
            int averageScore = dbManager.getAveragePointsWonByUser(idUser);
            int averagePassedTurnPerManche = dbManager.getAveragePassedTurnPerMancheByUser(idUser);
            int averagePassedTurnPerMatch = dbManager.getAveragePassedTurnPerMatchByUser(idUser);
            int averageLossPerManche = dbManager.getAverageLossPerMancheByUser(idUser);
            int averageLossPerMatch = dbManager.getAverageLossPerMatchByUser(idUser);

            String result = ""+numManchePlayed+" "+numMatchPlayed+" "+numMancheObserved+" "+numMatchObserved+" "+numMancheWon+" "+numMatchWon+" "+averageScore+" "+averagePassedTurnPerManche+
                    " "+averagePassedTurnPerMatch+" "+averageLossPerManche+" "+averageLossPerMatch;
            return result;
        }else
            return null;
    }


    public String bestStatsUsers(){
        String result = "";
        UsersDTO user;
        user = dbManager.getBestUserForManche();
        if(user != null){
            result += user.getNickname() +" ";
        }else{
            result += "- ";
        }
        user = dbManager.getBestUserForMatch();
        if(user != null){
            result += user.getNickname()+" ";
        }else{
            result += "- ";
        }
        user = dbManager.getUserForMoreManchesPlayed();
        if(user != null){
            result += user.getNickname() + " ";
        }else{
            result += "- ";
        }
        user = dbManager.getUserForBestMancheAverage();
        if(user != null){
            result += user.getNickname()+" ";
        }else{
            result += "- ";
        }
        user = dbManager.getUserForMostLostTurn();
        if(user != null){
            result += user.getNickname() + " ";
        }else{
            result += "- ";
        }
        user = dbManager.getUserForMostLosses();
        if(user != null){
            result += user.getNickname() +" ";
        }else{
            result += "- ";
        }
        return result;
    }
}

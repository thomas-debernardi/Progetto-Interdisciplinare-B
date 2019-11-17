package server;

import server.dbComm.DBManager;
import server.dbComm.MovesDTO;
import server.dbComm.UsersDTO;

/**
 * Questa classe si occupa dell'accesso al database al fine di ottenere le statistiche di utilizzo della piattaforma
 */
public class MonitoringManager {
    private DBManager dbManager;
    private static MonitoringManager monitoringManager;
    private MonitoringManager(DBManager dbmng){
        dbManager = dbmng;
    }

    /**
     *
     * @param dbmng il riferimento al gestore del db
     * @return il singleton della classe {@link MonitoringManager}
     */
    public static MonitoringManager createMonitoringManager(DBManager dbmng){
        if(monitoringManager == null){
            monitoringManager = new MonitoringManager(dbmng);
            return monitoringManager;
        }else{
            return monitoringManager;
        }
    }


    /**
     * Questo metodo permette di individuare la chiamata di consonante che ha permesso di ottenere la maggiore quantita' di punti
     *
     * @return la stringa contenente, divisi da spazi: il nickname del giocatore, la consonante chiamata e la frase associata
     */
    public String getBestMove(){
        MovesDTO bestMove = dbManager.getBestMove();
        String result;
        if(bestMove != null) {
            result = "" + bestMove.getPlayer().getNickname() + " " + bestMove.getMoveType() + " " + bestMove.getManche().getPhrase().getPhrase();
            return result;
        }else
            return "- - -";
    }

    /**
     * Questo metodo permette di individuare la media di mosse necessarie per indovinare la soluzione
     *
     * @return il numero medio di mosse
     */
    public int averageMovesPerManches(){
        return dbManager.getAverageMovesPerManche();
    }

    /**
     * Questo metodo permette di individuare le statistiche relative ad un singolo giocatore
     *
     * @param nickname il nickname del giocatore
     * @return una stringa contenente, divisi da spazi: numero manche giocate, numero partite giocate, numero manche osservate, numero match osservati,
     * numero manche vinte, numero match vinti, punteggio medio vinto per partita, numero medio di volte che ha dovuto cedere il turno di gioco per manche, numero medio di volte che ha dovuto cedere il turno di gioco per match,
     * numero medio di volte che ha perso tutto per manche, numero medio di volte che ha perso tutto per match
     */
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

    /**
     * Permette di individuare i record della piattaforma
     *
     * @return una stringa contenente, divisi da spazi, i nickname dei giocatori che: detengono il punteggio piu' alto per manche, detengono il punteggio piu' alto per partita,
     * che ha giocato piu' manche in assoluto, con la media di punti acquisiti per manche piu' alta, che ha ceduto il turno piu' volte a causa di errori, che ha perso tutto il maggior numero di volte
     */
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

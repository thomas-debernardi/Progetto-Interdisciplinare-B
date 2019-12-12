package Game;


import java.rmi.RemoteException;

import Server.ServerImplementation;
import Services.Client;


public class MoveTimer extends Thread {
    private int time;
    private Match match;
    private boolean isThisForJolly;
    private boolean isThisForSolution;
    private boolean isThisForVocal;

    public MoveTimer(int time, Match match, boolean jolly, boolean solution, boolean vocal){
        this.time = time;
        this.match = match;
        isThisForJolly = jolly;
        isThisForSolution = solution;
        isThisForVocal = vocal;
    }

    public boolean isThisForSolution() {
        return isThisForSolution;
    }

    public boolean isThisForJolly() {
        return isThisForJolly;
    }

    public boolean isThisForVocal() {
        return isThisForVocal;
    }


    public void run(){
        try{
            int seconds = time/1000;
            for(int i=seconds; i>=0; i--){
                for(Client c : match.getObservers()) {
                    try {
                        c.updateTimer(i);
                    } catch (RemoteException e) {
                        try {
                            match.leaveMatchAsObserver(c);
                        } catch (RemoteException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                for(Player p : match.getPlayers()) {
                    try {
                        p.getClient().updateTimer(i);
                    } catch (RemoteException e) {
                        try {
                            match.leaveMatchAsPlayer(p);
                        } catch (RemoteException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                sleep(10000); //deve essere 5000
            }
//            sleep(time);
            if(!match.isMatchEnded()) {
                for (Client c : match.getObservers()) {
                    try {
                        c.notifyTimeOut();
                    } catch (RemoteException e) {
                        try {
                            match.leaveMatchAsObserver(c);
                        } catch (RemoteException ex) {
                            ServerImplementation.serverError(null);
                        }
                    }
                }
                for (Player p : match.getPlayers()) {
                    try {
                        p.getClient().notifyTimeOut();
                    } catch (RemoteException e) {
                        try {
                            match.leaveMatchAsPlayer(p);
                        } catch (RemoteException ex) {
                            ServerImplementation.serverError(null);
                        }
                    }
                }
                if (isThisForJolly) {
                    match.errorInTurn(false, true);
                } else {
                    match.errorInTurn(true, false);
                }
            }
        }catch(InterruptedException e){
            return;
        }
    }
}

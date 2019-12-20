package game;


import java.rmi.RemoteException;

import services.Client;


public class Player {

    private Client client;
    private String nickname;
    private int points;
    private int partialPoints;
    private int numJolly;
    private String idPlayer;

    public Player(Client c) throws RemoteException{
        client = c;
        points = 0;
        partialPoints = 0;
        numJolly = 0;
        nickname = c.getNickname();
        idPlayer = c.getId();
    }

    public Client getClient(){
        return client;
    }

    public int getPoints(){
        return points;
    }

    public int getPartialPoints(){
        return partialPoints;
    }

    public int getNumJolly(){
        return numJolly;
    }

    public void updatePoints(int score){
        points += score;
    }

    public void updatePartialPoints(int score){
        partialPoints += score;
    }

    public void addJolly(){
        numJolly += 1;
    }

    public void removeJolly(){
        numJolly -= 1;
    }

    public void partialPointsToZero(){
        partialPoints = 0;
    }

    public String getNickname(){
        return nickname;
    }

    public String getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(String idPlayer) {
        this.idPlayer = idPlayer;
    }
}

package game;


import java.rmi.RemoteException;

import services.ClientInterface;


public class Player {

    private ClientInterface client;
    private String nickname;
    private int points;
    private int partialPoints;
    private int numJolly;
    private String idPlayer;

    public Player(ClientInterface c) throws RemoteException{
        client = c;
        points = 0;
        partialPoints = 0;
        numJolly = 0;
        nickname = c.getNickname();
        idPlayer = c.getId();
    }

    /**
     * Metodo getter per l'ottenimento del Client
     * 
     * @return client contiene il Client di interesse
     */
    public ClientInterface getClient(){
        return client;
    }

    /**
     * Metodo getter per l'ottenimento dei punti del giocatore
     * 
     * @return points contiene i punti del giocatore di interesse
     */
    public int getPoints(){
        return points;
    }

    /**
     * Metodo getter per l'ottenimento dei punti parziali del giocatore
     * 
     * @return partialPoints contiene l'ammontare attuale di punti del giocatore di interesse
     */
    public int getPartialPoints(){
        return partialPoints;
    }

    /**
     * Metodo getter per l'ottenimento del jolly del giocatore
     * 
     * @return numJolly contiene il jolly del giocatore di interesse
     */
    public int getNumJolly(){
        return numJolly;
    }

    /**
     * Metodo per aggiornare il totale di punti del giocatore di interesse
     * 
     * @param score contiene il numero di punti da aggiungere
     */
    public void updatePoints(int score){
        points += score;
    }

     /**
     * Metodo per aggiornare il parziale di punti del giocatore di interesse
     * 
     * @param score contiene il numero di punti da aggiungere
     */
    public void updatePartialPoints(int score){
        partialPoints += score;
    }

     /**
     * Metodo per aggiungere un jolly al giocatore di interesse
     */
    public void addJolly(){
        numJolly += 1;
    }

    /**
     * Metodo per togliere un jolly al giocatore di interesse
     */
    public void removeJolly(){
        numJolly -= 1;
    }

    /**
     * Metodo per resettare il parziale di punti del giocatore di interesse
     */
    public void partialPointsToZero(){
        partialPoints = 0;
    }

    /**
     * Metodo getter per l'ottenimento del nickname del giocatore
     * 
     * @return nickname contiene il nickname del giocatore di interesse
     */
    public String getNickname(){
        return nickname;
    }

    /**
     * Metodo getter per l'ottenimento dell'ID del giocatore
     * 
     * @return points contiene l'ID del giocatore di interesse
     */
    public String getIdPlayer() {
        return idPlayer;
    }

    /**
     * Metodo setter per inizializzare l'ID del giocatore
     * 
     * @param idPlayer contiene l'ID da inserire
     */
    public void setIdPlayer(String idPlayer) {
        this.idPlayer = idPlayer;
    }
}

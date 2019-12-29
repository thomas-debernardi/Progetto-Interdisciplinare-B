package serverRdF;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import game.Match;
import game.MatchManager;
import server.MatchVisualizer;
import services.MatchData;



public class MatchVisualizer {
    private static MatchVisualizer matchVisualizer = null;
    private MatchManager matchManager;

    private MatchVisualizer(MatchManager matchmng) {
        matchManager = matchmng;
    }

    /**
     * Questo metodo crea il singleton di MatchVisualizer
     * 
     * @param matchmng il riferimento al manager MatchManager
     * @return il singleton di tipo MatchVisualizer
     */
    public static MatchVisualizer createMatchVisualizer(MatchManager matchmng) {
        if (matchVisualizer == null) {
            matchVisualizer = new MatchVisualizer(matchmng);
            return matchVisualizer;
        } else
            return matchVisualizer;
    }

    /**
     * @return una lista contenente i dati dei match da visulizzare
     */
    public ArrayList<MatchData> visualizeMatch() {
        HashMap<String, Match> hash = matchManager.getMatches();
        Collection<Match> collection = hash.values();
        ArrayList<MatchData> list = new ArrayList<>();
        for (Match match : collection) {
            list.add(match.createMatchData());
        }
        return list;
    }
}

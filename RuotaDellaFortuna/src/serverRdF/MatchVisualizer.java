package serverRdF;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import game.Match;
import game.MatchManager;
import services.MatchData;



public class MatchVisualizer {
    private static MatchVisualizer matchVisualizer = null;
    private MatchManager matchManager;

    private MatchVisualizer(MatchManager matchmng) {
        matchManager = matchmng;
    }


    public static MatchVisualizer createMatchVisualizer(MatchManager matchmng) {
        if (matchVisualizer == null) {
            matchVisualizer = new MatchVisualizer(matchmng);
            return matchVisualizer;
        } else
            return matchVisualizer;
    }


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

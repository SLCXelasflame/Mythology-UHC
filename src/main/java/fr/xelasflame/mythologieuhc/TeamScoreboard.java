package fr.xelasflame.mythologieuhc;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.*;

public class TeamScoreboard {
    public static Scoreboard displayScoreboard(){
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        return board;
    }
}

package fr.xelasflame.mythologieuhc;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;

public class Start {

    public static ArrayList<Player> game = new ArrayList<>();
    public  static File file = new File(Main.getPlugin(Main.class).getDataFolder(), "playersinfo.yml");
    public static YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
    public static String winner;


    public static void sendTitle(Player player, String title) {
        String command = "title " + player.getName() + " title " + "{\"text\":\"" + title + "\"}";

        player.getServer().dispatchCommand(player.getServer().getConsoleSender(), command);
    }


        public static void sendTitleToAllPlayers(String title) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                sendTitle(player, title);
            }
        }


    public static ArrayList<Player> teamplayer(String team){
    ArrayList<Player> playerlist = new ArrayList<>();
    setTabGame();
    for (Player player : game) {
        if (config.contains(player.getName())) {
            if (config.get(player.getName()).equals(team)) {
                playerlist.add(player);
            }
        }
    }
    return playerlist;
}

public static void setTabGame(){
    for (Player player : Bukkit.getOnlinePlayers()) {
        if (player.getGameMode().equals(GameMode.SURVIVAL)){
            game.add(player);
        }
    }
}
public static void  dead(Player player){
    game.remove(player);
    if(game.size() == 1){
        TimerGame.end = true;
    }
    String team = null;
    for (Player vivant : game) {
        if (team == null){
            team = RoleManager.getTeam(vivant);
        }
        else if(!team.equalsIgnoreCase(RoleManager.getTeam(vivant))){
            TimerGame.end = false;
        }
        else {TimerGame.end = true;}
    winner = vivant.getName();
    }


    }

public static String getWinner(){
        return winner;
}
}


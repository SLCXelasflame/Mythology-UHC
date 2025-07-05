package fr.xelasflame.mythologieuhc;

import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.io.File;

public class ManagerCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String index, String[] strings) {
        if( sender instanceof Player){
            Player player = ((Player) sender).getPlayer();
            if (player.hasPermission("mythologieuhc.host")) {
                if (index.equalsIgnoreCase("host") && strings.length > 0){
                    if(strings[0].equalsIgnoreCase("start")) {
                        startCountdown();
                    }

                else if (strings[0].equalsIgnoreCase("load")) {
                    Location pasteLoc = player.getLocation();
                    Vector loc = new Vector(pasteLoc.getX(), pasteLoc.getY(), pasteLoc.getZ());
                    File file = new File(Main.getPlugin(Main.class).getDataFolder().getAbsolutePath()  + "/NetherChunk.schematic");
                    BukkitWorld world = new BukkitWorld(pasteLoc.getWorld());
                    WorldEditHook.pasteSchematic(file, world);
                }
                else if (strings[0].equalsIgnoreCase("forcerole")) {
                    TimerGame.globaltimer = 19*60+50;
                }}
                else {
                    player.sendMessage("Il y a erreur dans la commande tapée");
                }
            }
            else {
                player.sendMessage("Vous ne pouvez pas executer cette commande ou la commande est erronee");
            }
        }
        return false;
    }



    private void startCountdown() {

        new BukkitRunnable() {
            int count = 6;

            @Override
            public void run() {
                if (count == 6){
                    Start.sendTitleToAllPlayers("La game va bientôt se lancée");
                    count --;
                }
                else if (count > 0) {
                    Start.sendTitleToAllPlayers( String.valueOf(count));
                    count--;
                } else {
                    Start.sendTitleToAllPlayers("Bonne gamme a tous");
                    TimerGame.secondsRemaining = 3600;
                    TimerGame.start();
                    GameManager.gamestart();
                    cancel();
                }
            }
        }.runTaskTimer(Main.getPlugin(Main.class), 0L, 20L);

    }


}



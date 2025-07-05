package fr.xelasflame.mythologieuhc;

import com.sk89q.worldedit.*;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.world.DataException;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class InGameCommands implements CommandExecutor {

        @Override
        public boolean onCommand(CommandSender sender, Command command, String index, String[] label) {
            if( sender instanceof Player){
                Player player = ((Player) sender).getPlayer();
                    if (index.equalsIgnoreCase("m")) {
                        if(label[0].equalsIgnoreCase("role")){
                            player.sendMessage(RoleManager.getdescription(player));
                        }
                        else {
                        if (label.length != 2){
                            player.sendMessage("La commande est incomplète");
                            return false;
                        }
                        Player player1 = Bukkit.getPlayer(label[1]);
                        if (player1 == null){
                            player.sendMessage("Le joueur " + label[1] + " n existe pas");
                            return false;
                        }


                else {
                    player.sendMessage("Vous ne pouvez pas executer cette commande");
                }
            }
            return false;
        }
}return false;}

    }

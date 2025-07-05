package fr.xelasflame.mythologieuhc;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.util.logging.Logger.getLogger;

public class RoleManager {

    public  static File file = new File(Main.getPlugin(Main.class).getDataFolder(), "roles.yml");
    public static YamlConfiguration roleinfo = YamlConfiguration.loadConfiguration(file);


    public  static File file2 = new File(Main.getPlugin(Main.class).getDataFolder(), "playersinfo.yml");
    public static YamlConfiguration info = YamlConfiguration.loadConfiguration(file2);

    public static void setRole(Player player, String role){
        info.set(player.getName() + ".role", role);
        try {
            info.save(file2);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void setTeam(Player player, String team){
        info.set(player.getName() + ".team", team);
        try {
            info.save(file2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String getRole(Player player){
        return info.getString(player.getName() + ".role") ;
    }

    public static String getTeam(Player player){
        return info.getString(player.getName() + ".team") ;
    }


    public static String getdescription(Player player){
        String role = getRole(player);
        return roleinfo.getString("roles." + role+ ".description");
    }



    public static void setGlobalRole(Player player, String role){
        ConfigurationSection roleliste = roleinfo.getConfigurationSection("roles");

                ConfigurationSection roleSection = roleliste.getConfigurationSection(role);
                List<String> itemNames = roleSection.getStringList("items");
                List<String> effectStrings = roleSection.getStringList("effects");
                String description = roleSection.getString("description");
                String camp = roleSection.getString("team");

                player.sendMessage("Vous etes désormais le sujet de " + role);
                player.sendMessage(description);
                player.sendMessage(camp);
                setRole(player, role);

            for (String itemName : itemNames) {
                player.getInventory().addItem(ItemManager.itemliste.get(itemName));
            }

            for (String effectType : effectStrings) {
                    PotionEffectType potionEffectType = PotionEffectType.getByName(effectType);
                    player.addPotionEffect(new PotionEffect(potionEffectType, 999999999, 0));


            }
        }}


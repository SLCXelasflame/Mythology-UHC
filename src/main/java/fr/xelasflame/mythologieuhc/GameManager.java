package fr.xelasflame.mythologieuhc;

import org.bukkit.*;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import java.io.File;
import java.util.Random;

public class GameManager {

    public static File file = new File(Main.getPlugin(Main.class).getDataFolder(), "playersinfo.yml");

    public static void giveStuff(Player player){
        player.getInventory().clear();
        clearAllEffects(player);
        player.setMaxHealth(20.0);
        player.setHealth(20.0);
        player.setFoodLevel(20);
        Inventory inv = player.getInventory();
        //starter kit
        inv.setItem(0, new ItemStack(Material.BAKED_POTATO, 64));
        inv.setItem(1, new ItemStack(Material.BOOK, 3));
        inv.setItem(2, new ItemStack(Material.APPLE, 10));
        inv.setItem(3, new ItemStack(Material.BOAT, 1));
        player.setGameMode(GameMode.SURVIVAL);

    }
    public static void clearAllEffects(Player player) {
        for (PotionEffect effect : player.getActivePotionEffects()) {
            player.removePotionEffect(effect.getType());
        }
    }
    public static  Location generateRandomLocation() {
        World world = Bukkit.getWorld("world");
        Random random = new Random();

        double x = random.nextDouble() * 4080 - 2040;
        double z = random.nextDouble() * 4080 - 2040;

        double y = world.getHighestBlockYAt((int) x, (int) z);

        return new Location(world, x, y, z);
    }

    public static void gamestart() {
        ItemEvent.cooldown.clear();
        Bukkit.getWorld("world").setTime(0);
        for (Player player : Bukkit.getOnlinePlayers()) {
            giveStuff(player);
            player.teleport(generateRandomLocation());
        }

    }}

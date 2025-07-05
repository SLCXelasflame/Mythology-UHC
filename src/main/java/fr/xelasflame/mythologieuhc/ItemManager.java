package fr.xelasflame.mythologieuhc;


import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;


public class ItemManager {

    public static HashMap<String, ItemStack> itemliste = new HashMap<>();
    public static ArrayList<ItemStack> switchitem = new ArrayList<>();
    public static ItemStack teamselector;





    public static void init() {
        createTeamSelector();

    }
    private static void createTeamSelector(){
        ItemStack item = new ItemStack(Material.NETHER_STAR);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("Select Team");
        item.setItemMeta(meta);
        teamselector = item;
    }


}
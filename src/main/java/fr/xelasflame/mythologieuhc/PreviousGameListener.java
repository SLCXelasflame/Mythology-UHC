package fr.xelasflame.mythologieuhc;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class PreviousGameListener implements Listener {


    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        player.sendMessage("Bienvenue dans le Mythologie UHC");
        TimerGame.updateScoreboard();
        player.getInventory().clear();
        player.setGameMode(GameMode.SURVIVAL);
        player.setHealth(20);
        player.setFoodLevel(20);
        player.getInventory().addItem(ItemManager.teamselector);
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        player.setScoreboard(scoreboard);
    }


    @EventHandler
    public void onClick(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if(event.getItem() == null){
            return;
        }
        String itemname = event.getItem().getItemMeta().getDisplayName();

        if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            if(itemname.equalsIgnoreCase("Select Team")) {
                openTeamGUI(player);
                event.setCancelled(true);
            }
        }


    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().getName().equalsIgnoreCase("Choose Your Team") && event.getWhoClicked() instanceof Player) {
            event.setCancelled(true);
            if (event.getCurrentItem() == null || event.getCurrentItem().getType() != Material.WOOL) {
                return;
            }

            Player player = (Player) event.getWhoClicked();
            ItemStack clickedItem = event.getCurrentItem();
            String teamName = ChatColor.stripColor(clickedItem.getItemMeta().getDisplayName()).split(" ")[0];
            Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
            Team team = scoreboard.getTeam(teamName);
            if (team != null) {
                team.addEntry(player.getName());
                player.sendMessage(ChatColor.valueOf(teamName.toUpperCase() )+ "You have joined the " + teamName + " Team!");
                RoleManager.setTeam(player, teamName);
                player.setScoreboard(scoreboard);
            }

            player.closeInventory();
        }
    }


    private void openTeamGUI(Player player) {
        Inventory gui = Bukkit.createInventory(null, 9, "Choose Your Team");

        gui.addItem(createGuiItem(ChatColor.RED + "Red Team", (short) 14));
        gui.addItem(createGuiItem(ChatColor.BLUE + "Blue Team", (short) 11));
        gui.addItem(createGuiItem(ChatColor.GREEN + "Green Team", (short) 13));
        gui.addItem(createGuiItem(ChatColor.YELLOW + "Yellow Team", (short) 4));
        gui.addItem(createGuiItem(ChatColor.GOLD + "Gold Team", (short) 1));
        gui.addItem(createGuiItem(ChatColor.WHITE + "White Team", (short) 0));
        gui.addItem(createGuiItem(ChatColor.BLACK + "Black Team", (short) 15));
        gui.addItem(createGuiItem(ChatColor.DARK_PURPLE + "Dark_purple Team", (short) 10));
        player.openInventory(gui);
    }

    private ItemStack createGuiItem(String name, short color) {
        Material material = Material.WOOL;
        ItemStack item = new ItemStack(material, 1, color);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        return item;
    }
}

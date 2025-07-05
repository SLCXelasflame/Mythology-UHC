package fr.xelasflame.mythologieuhc;


import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;


public class GameEvent implements Listener {


    @EventHandler
    public void onCraft(CraftItemEvent e) {
        ItemStack item = e.getRecipe().getResult();
        if(item.getType() == Material.NETHER_STAR) {


           e.getWhoClicked().sendMessage("Désormais vous êtes un sujet de " + ChatColor.GOLD + ChatColor.BOLD + item.getItemMeta().getDisplayName() + ChatColor.RESET + ChatColor.ITALIC + ChatColor.AQUA + " .\n Vous pouvez vous attirer ses grâces à l'aide d'un nouveau craft (/m grace)");
            e.setResult(null);
           e.setCancelled(true);
            e.getInventory().clear();
            e.getWhoClicked().closeInventory();
        }
    }



    @EventHandler
    public void findDeath(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player p = (Player) event.getEntity();
            if(event.getDamage() >= p.getHealth()) {
                Location loc = p.getLocation();
                event.setCancelled(true);
                p.setHealth(p.getMaxHealth());
                    Bukkit.broadcastMessage(ChatColor.GREEN + p.getDisplayName() + " est mort il etait " + RoleManager.getRole(p));
                    Start.sendTitle(p, "Ton aventure s'arrete ici");
                    p.setGameMode(GameMode.SPECTATOR);
                    for (ItemStack item: p.getInventory()
                         ) {
                        if (!ItemManager.itemliste.containsValue(item) && item != null) {
                            Bukkit.getWorld("world").dropItem(loc, item);
                        }
                    }
                    Start.dead(p);
                    Player ares = (Player) p.getLastDamageCause().getEntity();
                    if (RoleManager.getRole(ares).equalsIgnoreCase("Ares")){
                        ares.sendMessage("Votre folie meurtrière continue, quelqu'un pourra-t-il vous arreter ...");
                        ares.setMaxHealth(ares.getMaxHealth()+1.0);

                }
            }
        }
    }}




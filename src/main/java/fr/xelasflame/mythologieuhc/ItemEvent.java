package fr.xelasflame.mythologieuhc;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;

public class ItemEvent implements Listener {
    public static HashMap<Integer, Integer> cooldown = new HashMap<>();

    @EventHandler
    public void onClick(PlayerInteractEvent event){
        ItemStack item = event.getItem();
        Integer id = ItemManager.switchitem.indexOf(item);
        if (item != null && (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK))){
            if (ItemManager.switchitem.contains(item)) {
                if (!cooldown.containsKey(id)){cooldown.put(id, 0);}
                if (cooldown.get(id) > TimerGame.globaltimer){
                    event.getPlayer().sendMessage("Votre item est en cooldown, il reste : "+ ChatColor.YELLOW + (cooldown.get(id) - TimerGame.globaltimer)+" secondes");
                    event.setCancelled(true);

                }
                else {
                    Player player = event.getPlayer();
                    switch (id) {
                        case 0:
                            //gon
                            break;

                        case 1:
                            //kirua
                            break;

                        case 2:
                            //netero
                            break;

                        case 3:
                            //morau
                            break;

                        case 4:
                            //biscuit
                            break;
                        case 5:
                            //razor
                            player.getInventory().addItem(item);
                            break;
                        case 6:
                            //kurapika
                            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 1*60*20, 0, true, false));
                            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 5*60*20, 0, true, false));
                            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 5*60*20, 1, true, false));
                            player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 5*60*20, 1, true, false));
                            player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 5*60*20, 0, true, false));
                            cooldown.put(id, TimerGame.globaltimer + 20 * 60);
                            break;


                    }
                }
            }
        }}

}







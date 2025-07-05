package fr.xelasflame.mythologieuhc;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.io.File;

public class Main extends JavaPlugin {

        @Override
        public void onEnable() {
            for (Team team : Bukkit.getScoreboardManager().getMainScoreboard().getTeams()) {
                team.unregister();
            }
            Bukkit.getWorld("world").setPVP(false);
            Bukkit.getWorld("world").getPopulators().add(new MineraiGen());
            getLogger().info("Le plugin mythologie uhc a ete active !");
            Bukkit.getWorld("world").getWorldBorder().setSize(4096);
            getCommand("host").setExecutor(new ManagerCommands());
            getCommand("m").setExecutor(new InGameCommands());
            this.getServer().getPluginManager().registerEvents(new ItemEvent(), this);
            this.getServer().getPluginManager().registerEvents(new GameEvent(), this);
            this.getServer().getPluginManager().registerEvents(new PreviousGameListener(), this);
            ItemManager.init();
            setupTeams();
            setupRecipe();
            TimerGame.loadobjective();
            if (getDataFolder().exists()){
                for (File file:getDataFolder().listFiles()
                     ) {
                    if (file.exists()){
                        file.delete();
                    }
                }
                getDataFolder().delete();

            }
                saveResource("roles.yml", false);
                getLogger().info("Le fichier roles.yml a été copié depuis les ressources.");
                saveResource("NetherChunk.schematic", false);
                getLogger().info("Le fichier NetherChunk.schematic a été copié depuis les ressources.");


        }

        @Override
        public void onDisable() {
            getLogger().info("Le plugin mythologie uhc a ete desactive!");


        }

    private void setupTeams() {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        createTeam(scoreboard, "Red", ChatColor.RED);
        createTeam(scoreboard, "Blue", ChatColor.BLUE);
        createTeam(scoreboard, "White", ChatColor.WHITE);
        createTeam(scoreboard, "Yellow", ChatColor.YELLOW);
        createTeam(scoreboard, "Green", ChatColor.GREEN);
        createTeam(scoreboard, "Black", ChatColor.BLACK);
        createTeam(scoreboard, "Gold", ChatColor.GOLD);
        createTeam(scoreboard, "Dark_purple", ChatColor.DARK_PURPLE);
    }

    private void createTeam(Scoreboard scoreboard, String teamName, ChatColor color) {
        Team team = scoreboard.getTeam(teamName);
        if (team == null) {
            team = scoreboard.registerNewTeam(teamName);
            team.setPrefix(color + "[" + teamName + "] ");

        }
    }

    private  void setupRecipe(){
        ItemStack customItem = new ItemStack(Material.NETHER_STAR);
        ItemMeta meta = customItem.getItemMeta();
        meta.setDisplayName("Zeus");
        customItem.setItemMeta(meta);
        ShapedRecipe customRecipe = new ShapedRecipe(customItem);
        customRecipe.shape(" D ", " D ", " S ");
        customRecipe.setIngredient('D', Material.NETHER_STAR);
        customRecipe.setIngredient('S', Material.STICK);
        Bukkit.addRecipe(customRecipe);
    }
    }

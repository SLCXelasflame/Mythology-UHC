package fr.xelasflame.mythologieuhc;

import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.world.DataException;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

public class WorldEditHook {

    public static void pasteSchematic(File schematicFile, BukkitWorld bukkitWorld) {
        World world = Bukkit.getWorld(bukkitWorld.getName());
        Vector pasteLocation = new Vector(0, world.getHighestBlockYAt(0, 0)
, 0);
        try (FileInputStream ignored = new FileInputStream(schematicFile)) {
            EditSession editSession = new EditSession(bukkitWorld, -1);

            CuboidClipboard clipboard = CuboidClipboard.loadSchematic(schematicFile);

            // Paste the clipboard to the specified location
            clipboard.paste(editSession, pasteLocation, false, true);
            Bukkit.broadcastMessage("Le fichier est bien généré");
        } catch (IOException | DataException | MaxChangedBlocksException e) {
            e.printStackTrace();
            Bukkit.broadcastMessage("Le schem ne marche pas");
        }
    }







    }


package fr.xelasflame.mythologieuhc;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;

import java.util.Random;

public class MineraiGen extends BlockPopulator {
    @Override
    public void populate(World world, Random random, Chunk source) {
        for (int y = 0; y < 61; y+=2){
            for (int x = 0; x < 16; x+=2){
                for (int z = 0; z < 16; z+=2){
                    Block current = source.getBlock(x, y, z);
                    if (current.getType().equals(Material.STONE)) {
                        generateOre(current, source);
                    }
                }
            }
        }
    }

    private void generateOre(Block block, Chunk source){
        Material ore = getOreType();
        Random generateChance = new Random();
        int chance = generateChance.nextInt(50)+1;

        if (chance > 45) {
            block.setType(ore);
            for (int y = block.getY(); y < block.getY()+2; y++){
                for (int x = block.getX(); x < block.getX()+2; x++){
                    for (int z = block.getZ(); z < block.getZ()+2; z++){
                        if (source.getBlock(x, y, z).getType().equals(Material.STONE)){
                            source.getBlock(x, y, z).setType(ore);
                        }
                    }
                }
            }
        }
    }

    private Material getOreType(){
        Random oreType = new Random();
        if (oreType.nextInt(10) == 1) {
            return Material.NETHER_BRICK;
        }
        return Material.STONE;

    }

}
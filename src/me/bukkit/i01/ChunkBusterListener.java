package me.bukkit.i01;

import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.IBlockData;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class ChunkBusterListener implements Listener {
    Main plugin;

    public ChunkBusterListener(Main plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin = plugin;
    }

    @EventHandler
    public void onEnchant(CraftItemEvent e) {
        Player player = (Player) e.getWhoClicked();
        ItemStack craftedItem = e.getCurrentItem();
        if (craftedItem.equals(Main.CreateChunkBuster(1))) {
            if (player.hasPermission("chunkbusters.craft")) {
                player.sendMessage(Main.color(Main.Chunk_Buster_Craft_Success));
            } else {
                player.sendMessage(Main.color(Main.Chunk_Buster_Craft_No_Permission));
                e.setCancelled(true);

            }
        }

    }

    @EventHandler
    public void onChunkBuster(BlockPlaceEvent e) {

        if ((e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(Main.color(Main.Chunk_Buster_Name)) && (e.getBlock().getType().toString().equalsIgnoreCase(Main.Chunk_Buster_Item_Material.toUpperCase())))) {
            if (!e.getPlayer().hasPermission("chunkbuster.place")) {
                e.getPlayer().sendMessage(Main.color(Main.Chunk_Buster_Place_No_Permission));
                e.setCancelled(true);
            } else {
                ArrayList<String> initArrayOfBlockedMaterials = Main.Chunk_Buster_Ignored_Materials;
                ArrayList<String> ArrayOfBlockedMaterials = new ArrayList<>();
                for (int f = 0; f < initArrayOfBlockedMaterials.size(); f++) {
                    ArrayOfBlockedMaterials.add(initArrayOfBlockedMaterials.get(f).toUpperCase());
                }
                if (Main.Chunk_Buster_Effects_Enabled) {
                    e.getPlayer().getWorld().playSound(e.getBlockPlaced().getLocation(), Sound.EXPLODE, 1, 1);
                    e.getPlayer().getWorld().strikeLightningEffect(e.getBlockPlaced().getLocation());
                }
                e.getBlockPlaced().setType(Material.AIR);
                int totalBlocksBroken = 0;
                Chunk chunk = e.getBlockPlaced().getLocation().getChunk();
                int ChunkX = chunk.getX() * 16;
                int ChunkZ = chunk.getZ() * 16;
                for (int y = 255; y > 0; y--) {
                    for (int z = 0; z < 16; z++) {
                        for (int x = 0; x < 16; x++) {
                            Location current = new Location(chunk.getWorld(), ChunkX + x, y, ChunkZ + z);
                            if (!current.getBlock().getType().equals(Material.AIR)) {
                                if (!ArrayOfBlockedMaterials.contains(current.getBlock().getType().toString().toUpperCase()))
                                    current.getBlock().setType(Material.AIR);
                                if (Main.Chunk_Buster_Effects_Enabled) {
                                    current.getWorld().playEffect(current, Effect.FIREWORKS_SPARK, 4000);
                                }
                                totalBlocksBroken++;
                            }
                        }
                    }
                }
                String new_Chunk_Buster_Place_Success = Main.Chunk_Buster_Place_Success;
                if (Main.Chunk_Buster_Place_Success.contains("{amount}")) {
                    new_Chunk_Buster_Place_Success = new_Chunk_Buster_Place_Success.replace("{amount}", totalBlocksBroken + "");
                }
                e.getPlayer().sendMessage(Main.color(new_Chunk_Buster_Place_Success));

            }

        }
    }

    //Unused Fast place method, Maybe use in the future.
    public void setBlockFast(World world, int x, int y, int z, int blockId, byte data) {
        net.minecraft.server.v1_8_R3.World w = ((CraftWorld) world).getHandle();
        net.minecraft.server.v1_8_R3.Chunk chunk = w.getChunkAt(x >> 4, z >> 4);
        BlockPosition bp = new BlockPosition(x, y, z);
        int combined = blockId + (data << 12);
        IBlockData ibd = net.minecraft.server.v1_8_R3.Block.getByCombinedId(combined);
        chunk.a(bp, ibd);
    }


}

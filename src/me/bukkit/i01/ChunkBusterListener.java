package me.bukkit.i01;

import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.ps.PS;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;


public class ChunkBusterListener implements Listener {

    @EventHandler
    public void onEnchant(CraftItemEvent e) {
        Player player = (Player) e.getWhoClicked();
        ItemStack craftedItem = e.getCurrentItem();
        if (craftedItem.equals(ChunkBusters.CreateChunkBuster(1))) {
            if (player.hasPermission("chunkbusters.craft")) {
                player.sendMessage(ChunkBusters.color(ChunkBusters.Chunk_Buster_Craft_Success));
            } else {
                player.sendMessage(ChunkBusters.color(ChunkBusters.Chunk_Buster_Craft_No_Permission));
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onChunkBuster(BlockPlaceEvent e) {

        if ((e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChunkBusters.color(ChunkBusters.Chunk_Buster_Name)) && (e.getBlock().getType().toString().equalsIgnoreCase(ChunkBusters.Chunk_Buster_Item_Material.toUpperCase())))) {
            if (!e.getPlayer().hasPermission("chunkbuster.place")) {
                e.getPlayer().sendMessage(ChunkBusters.color(ChunkBusters.Chunk_Buster_Place_No_Permission));
                e.setCancelled(true);
            } else {
                if (ChunkBusters.Hook_Into_MassiveCraft_Factions_Enabled) {
                    MPlayer mPlayer = MPlayer.get(e.getPlayer());
                    Location currentFLocal = e.getBlock().getLocation();
                    Faction faction = BoardColl.get().getFactionAt(PS.valueOf(currentFLocal));
                    if (!ChunkBusters.Allow_Placing_In_Wilderness_Enabled) {
                        if (faction.isNone()) {
                            if (!mPlayer.isOverriding()) {
                                e.getPlayer().sendMessage(ChunkBusters.color(ChunkBusters.Chunk_Buster_Place_In_Wilderness));
                                e.setCancelled(true);
                                return;
                            }
                        }
                    }
                    if (!ChunkBusters.Allow_Placing_In_Other_Factions_Enabled) {
                        if (!mPlayer.getFaction().equals(faction)) {
                            if (!mPlayer.isOverriding()) {
                                e.getPlayer().sendMessage(ChunkBusters.color(ChunkBusters.Chunk_Buster_Place_In_Not_Own_Faction));
                                e.setCancelled(true);
                                return;
                            }
                        }
                    }
                }
                
                ArrayList<String> initArrayOfBlockedMaterials = ChunkBusters.Chunk_Buster_Ignored_Materials;
                ArrayList<String> ArrayOfBlockedMaterials = new ArrayList<>();
                for (String initArrayOfBlockedMaterial : initArrayOfBlockedMaterials) {
                    ArrayOfBlockedMaterials.add(initArrayOfBlockedMaterial.toUpperCase());
                }
                if (ChunkBusters.Chunk_Buster_Effects_Enabled) {
                    e.getPlayer().getWorld().playSound(e.getBlockPlaced().getLocation(), Sound.EXPLODE, 1, 1);
                    e.getPlayer().getWorld().strikeLightningEffect(e.getBlockPlaced().getLocation());
                }
                e.getBlockPlaced().breakNaturally();
                Chunk chunk = e.getBlockPlaced().getLocation().getChunk();
                int ChunkX = chunk.getX() * 16;
                int ChunkZ = chunk.getZ() * 16;
                int totalBlocksBroken = 0;
                for (int y = 255; y > 0; y--) {
                    for (double z = 0; z < (16); z++) {
                        for (int x = 0; x < (16); x++) {
                            Location current = new Location(chunk.getWorld(), ChunkX + x, y, ChunkZ + z);
                            if (!current.getBlock().getType().equals(Material.AIR)) {
                                if (!ArrayOfBlockedMaterials.contains(current.getBlock().getType().toString().toUpperCase())) {
                                    current.getBlock().setType(Material.AIR);
                                    totalBlocksBroken++;
                                    if (ChunkBusters.Chunk_Buster_Effects_Enabled) {
                                        current.getWorld().playEffect(current, Effect.FIREWORKS_SPARK, 4000);
                                    }
                                }
                            }
                        }
                    }
                }
                String new_Chunk_Buster_Place_Success = ChunkBusters.Chunk_Buster_Place_Success;
                if (ChunkBusters.Chunk_Buster_Place_Success.contains("{amount}")) {
                    new_Chunk_Buster_Place_Success = new_Chunk_Buster_Place_Success.replace("{amount}", totalBlocksBroken + "");
                }
                e.getPlayer().sendMessage(ChunkBusters.color(new_Chunk_Buster_Place_Success));
            }

        }
    }
}

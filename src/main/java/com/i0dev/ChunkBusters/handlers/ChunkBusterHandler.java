package com.i0dev.ChunkBusters.handlers;

import com.i0dev.ChunkBusters.Heart;
import com.i0dev.ChunkBusters.config.GeneralConfig;
import com.i0dev.ChunkBusters.config.MessageConfig;
import com.i0dev.ChunkBusters.hooks.MCoreFactionsHook;
import com.i0dev.ChunkBusters.managers.ChunkBusterManager;
import com.i0dev.ChunkBusters.templates.AbstractListener;
import com.i0dev.ChunkBusters.utility.MsgUtil;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.Chunk;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ChunkBusterHandler extends AbstractListener {
    public ChunkBusterHandler(Heart heart) {
        super(heart);
    }

    GeneralConfig cnf;

    @Override
    public void initialize() {
        cnf = heart.cnf();
    }

    @Override
    public void deinitialize() {
        cnf = null;
    }

    @EventHandler
    public void onChunkBuster(BlockPlaceEvent e) {
        if (e.getBlockPlaced() == null) return;
        if (e.getItemInHand() == null) return;
        NBTItem nbti = new NBTItem(e.getItemInHand());
        if (!nbti.hasKey("chunkbuster")) return;

        // CHECK FAC

        if (Heart.usingMCoreFactions) {
            if (!MCoreFactionsHook.isWilderness(e.getBlockPlaced().getLocation()) && cnf.isAllowWildernessPlacement()) {
                if (!MCoreFactionsHook.isOwn(e.getBlockPlaced().getLocation(), e.getPlayer())) {
                    MsgUtil.msg(e.getPlayer(), heart.getConfig(MessageConfig.class).getOnlyInOwnLand());
                    e.setCancelled(true);
                    return;
                }
            }
        }

        ArrayList<Location> blocksToRemove = new ArrayList<>();
        ArrayList<Location> priorityBlocks = new ArrayList<>();
        Chunk chunk = e.getBlockPlaced().getLocation().getChunk();
        int ChunkX = chunk.getX() * 16;
        int ChunkZ = chunk.getZ() * 16;
        for (int y = 255; y > 0; y--) {
            for (int z = 0; z < 16; z++) {
                for (int x = 0; x < 16; x++) {
                    Location current = new Location(chunk.getWorld(), ChunkX + x, y, ChunkZ + z);
                    if (!current.getBlock().getType().equals(Material.AIR)) {
                        if (!cnf.getIgnoredMaterials().contains(current.getBlock().getType().toString().toUpperCase())) {
                            if (cnf.getPriorityMaterials().contains(current.getBlock().getType().toString().toUpperCase())) {
                                priorityBlocks.add(current);
                            } else {
                                if (!heart.getManager(ChunkBusterManager.class).getBlockQueue().contains(current)) {
                                    blocksToRemove.add(current);
                                }
                            }
                        }
                    }
                }
            }
        }
        priorityBlocks.forEach(location -> {
            location.getBlock().setType(Material.AIR);
        });
        priorityBlocks.clear();
        e.getBlockPlaced().getLocation().getWorld().playEffect(e.getBlock().getLocation(), Effect.EXPLOSION_HUGE, 10, 10);
        Collections.shuffle(blocksToRemove);
        e.getBlockPlaced().setType(Material.AIR);
        heart.getManager(ChunkBusterManager.class).getBlockQueue().addAll(blocksToRemove);
        MsgUtil.msg(e.getPlayer(), heart.getConfig(MessageConfig.class).getYouPlaced());
    }
}

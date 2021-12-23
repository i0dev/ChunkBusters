package com.i0dev.ChunkBusters.managers;

import com.i0dev.ChunkBusters.Heart;
import com.i0dev.ChunkBusters.config.GeneralConfig;
import com.i0dev.ChunkBusters.templates.AbstractManager;
import com.i0dev.ChunkBusters.utility.Utility;
import de.tr7zw.changeme.nbtapi.NBTItem;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ChunkBusterManager extends AbstractManager {
    public ChunkBusterManager(Heart heart) {
        super(heart);
    }

    GeneralConfig cnf;
    BukkitTask task;

    @Override
    public void initialize() {
        cnf = heart.getConfig(GeneralConfig.class);
        task = Bukkit.getScheduler().runTaskTimer(heart, taskExecuteBlockQueue, 5 * 20L, 1L);
    }

    @Override
    public void deinitialize() {
        cnf = null;
        if (task != null)
            task.cancel();
        task = null;
    }

    public ItemStack getChunkBusterItem(int amount) {
        ItemStack item = Utility.makeItemStackFromConfigItem(cnf.getChunkBuster());

        item.setAmount(amount);

        NBTItem nbti = new NBTItem(item);
        nbti.setBoolean("chunkbuster", true);
        item = nbti.getItem();

        return item;
    }

    @Getter
    ArrayList<Location> blockQueue = new ArrayList<>();

    AtomicInteger randomizer = new AtomicInteger(0);
    public Runnable taskExecuteBlockQueue = () -> {
        if (blockQueue.size() == 0) return;
        for (Location firstX : getFirstX(blockQueue, cnf.getBlocksToBreakPerTick())) {
            breakBlock(firstX);
        }
        randomizer.getAndIncrement();
        if (randomizer.get() >= 15) {
            randomizer.set(0);
            Collections.shuffle(blockQueue);
        }
    };

    public List<Location> getFirstX(List<Location> locations, int x) {
        List<Location> ret = new ArrayList<>();
        List<Location> toRemove = new ArrayList<>();
        for (int i = 0; i < locations.size(); i++) {
            if (i >= x) break;
            ret.add(locations.get(i));
            toRemove.add(locations.get(i));
        }
        locations.removeAll(toRemove);
        return ret;
    }

    public void breakBlock(Location loc) {
        loc.getBlock().setType(Material.AIR);
    }


}

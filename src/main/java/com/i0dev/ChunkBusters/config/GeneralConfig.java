package com.i0dev.ChunkBusters.config;

import com.i0dev.ChunkBusters.Heart;
import com.i0dev.ChunkBusters.objects.ConfigItem;
import com.i0dev.ChunkBusters.templates.AbstractConfiguration;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class GeneralConfig extends AbstractConfiguration {

    public GeneralConfig(Heart heart, String path) {
        this.path = path;
        this.heart = heart;
    }

    int blocksToBreakPerTick = 500;
    boolean allowWildernessPlacement = true;

    ConfigItem chunkBuster = new ConfigItem("&c&lChunkBuster", 1, (short) 0, "ENDER_PORTAL_FRAME", Arrays.asList(
            "",
            "&cPlace this to break &4ALL&c the blocks in the current chunk.",
            "&cWill not break important blocks, but still be careful!",
            ""
    ), true);

    List<String> ignoredMaterials = Arrays.asList(
            "BEDROCK",
            "MOB_SPAWNER",
            "BEACON",
            "BARRIER",
            "ENDER_PORTAL_FRAME",
            "ENDER_PORTAL",
            "PORTAL",
            "ENDER_CHEST",
            "CHSET",
            "TRAPPED_CHEST",
            "HOPPER",
            "ENCHANTMENT_TABLE",
            "DISPENSER",
            "DROPPER",
            "BREWING_STAND",
            "BURNING_FURNACE",
            "FURNACE",
            "ANVIL"
    );

    List<String> priorityMaterials = Arrays.asList(
            "GRAVEL",
            "REDSTONE_COMPARATOR_ON",
            "REDSTONE_COMPARATOR_OFF",
            "REDSTONE_WIRE",
            "REDSTONE_TORCH_ON",
            "REDSTONE_TORCH_OFF",
            "SAND",
            "LAVA",
            "WATER",
            "STATIONARY_LAVA",
            "STATIONARY_WATER",
            "DOUBLE_PLANT",
            "TALL_GRASS",
            "DEAD_BUSH",
            "YELLOW_FLOWER",
            "RED_ROSE",
            "BROWN_MUSHROOM",
            "RED_MUSHROOM",
            "TORCH",
            "SUGAR_CANE",
            "LONG_GRASS",
            "SAPLING",
            "AZURE_BLUET",
            "RED_TULIP",
            "ORANGE_TULIP",
            "WHITE_TULIP",
            "PINK_TULIP",
            "POPPY",
            "DANDELION",
            "SUNFLOWER",
            "LILAC",
            "ROSE_BUSH",
            "PEONY",
            "CACTUS",
            "ALLIUM",
            "ORCHID",
            "BLUE_ORCHID",
            "FERN",
            "VINE",
            "LILY_PAD",
            "LARGE_FERN",
            "CARPET",
            "SIGN",
            "PAINTING",
            "WALL_SIGN",
            "ITEM_FRAME",
            "BUTTON",
            "TRIPWIRE_HOOK",
            "TRAPDOOR",
            "IRON_TRAPDOOR",
            "PRESSURE_PLATE_LIGHT",
            "PRESSURE_PLATE_HEAVY",
            "LEVER",
            "DOOR_WOOD",
            "DOOR_IRON",
            "DOOR_SPRUCE",
            "DOOR_BIRCH",
            "DOOR_JUNGLE",
            "DOOR_ACACIA",
            "DOOR_DARK_OAK",
            "COMPARATOR",
            "REDSTONE_COMPARATOR",
            "REDSTONE_COMPARATOR_OFF",
            "REDSTONE_COMPARATOR_ON",
            "REPEATER_OFF",
            "REPEATER_ON",
            "STONE_BUTTON",
            "WOOD_BUTTON",
            "RAIL",
            "ACTIVATOR_RAIL",
            "DETECTOR_RAIL",
            "LADDER"
    );

}

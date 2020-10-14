package me.bukkit.i01;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;

public class Main extends JavaPlugin {


    public static String Reload_Successful;
    public static String Reload_Usage;
    public static String Reload_No_Permission;
    public static String Chunk_Buster_Received;

    public static String Chunk_Buster_Give_Usage;
    public static String Chunk_Buster_Give_Unknown_Member_Error;
    public static String Chunk_Buster_Give_Amount_Error;
    public static String Chunk_Buster_Give_Success;
    public static String Chunk_Buster_Give_No_Permission;

    public static String Chunk_Buster_Place_Success;
    public static String Chunk_Buster_Place_No_Permission;

    public static ArrayList<String> Chunk_Buster_Lore = new ArrayList<>();
    public static ArrayList<String> Chunk_Buster_Ignored_Materials = new ArrayList<>();
    public static String Chunk_Buster_Item_Material;
    public static boolean Chunk_Buster_Item_Glow_Enabled;
    public static String Chunk_Buster_Name;


    public static String Crafting_Recipe_Slot_1;
    public static String Crafting_Recipe_Slot_2;
    public static String Crafting_Recipe_Slot_3;
    public static String Crafting_Recipe_Slot_4;
    public static String Crafting_Recipe_Slot_5;
    public static String Crafting_Recipe_Slot_6;
    public static String Crafting_Recipe_Slot_7;
    public static String Crafting_Recipe_Slot_8;
    public static String Crafting_Recipe_Slot_9;


    public static boolean Chunk_Buster_Craft_Enabled;

    public static String generalConfigPrefix = "GeneralConfig.";
    public static String messagingConfigPrefix = "Messaging.";
    public static String craftingRecipePrefix = "CraftingRecipe.";

    public void loadConfig() {
        if (!new File(getDataFolder(), "config.yml").exists()) {
            saveDefaultConfig();
        }
        reloadConfig();
        Chunk_Buster_Name = getConfig().getString(generalConfigPrefix + "Chunk_Buster_Name");
        Chunk_Buster_Item_Material = getConfig().getString(generalConfigPrefix + "Chunk_Buster_Item_Material");
        Chunk_Buster_Lore = (ArrayList<String>) getConfig().getList(generalConfigPrefix + "Chunk_Buster_Lore");
        Chunk_Buster_Ignored_Materials = (ArrayList<String>) getConfig().getList(generalConfigPrefix + "Chunk_Buster_Ignored_Materials");
        Chunk_Buster_Ignored_Materials = (ArrayList<String>) getConfig().getList(generalConfigPrefix + "Chunk_Buster_Ignored_Materials");
        Chunk_Buster_Craft_Enabled = getConfig().getBoolean(generalConfigPrefix + "Chunk_Buster_Craft_Enabled");
        Chunk_Buster_Item_Glow_Enabled = getConfig().getBoolean(generalConfigPrefix + "Chunk_Buster_Item_Glow_Enabled");

        Reload_Successful = getConfig().getString(messagingConfigPrefix + "Reload_Successful");
        Reload_Usage = getConfig().getString(messagingConfigPrefix + "Reload_Usage");
        Reload_No_Permission = getConfig().getString(messagingConfigPrefix + "Reload_No_Permission");

        Chunk_Buster_Give_Usage = getConfig().getString(messagingConfigPrefix + "Chunk_Buster_Give_Usage");
        Chunk_Buster_Give_Unknown_Member_Error = getConfig().getString(messagingConfigPrefix + "Chunk_Buster_Give_Unknown_Member_Error");
        Chunk_Buster_Give_No_Permission = getConfig().getString(messagingConfigPrefix + "Chunk_Buster_Give_No_Permission");
        Chunk_Buster_Give_Amount_Error = getConfig().getString(messagingConfigPrefix + "Chunk_Buster_Give_Amount_Error");
        Chunk_Buster_Give_Success = getConfig().getString(messagingConfigPrefix + "Chunk_Buster_Give_Success");
        Chunk_Buster_Received = getConfig().getString(messagingConfigPrefix + "Chunk_Buster_Received");
        Chunk_Buster_Place_Success = getConfig().getString(messagingConfigPrefix + "Chunk_Buster_Place_Success");
        Chunk_Buster_Place_No_Permission = getConfig().getString(messagingConfigPrefix + "Chunk_Buster_Place_No_Permission");

        Crafting_Recipe_Slot_1 = getConfig().getString(craftingRecipePrefix + "Crafting_Recipe_Slot_1");
        Crafting_Recipe_Slot_2 = getConfig().getString(craftingRecipePrefix + "Crafting_Recipe_Slot_2");
        Crafting_Recipe_Slot_3 = getConfig().getString(craftingRecipePrefix + "Crafting_Recipe_Slot_3");
        Crafting_Recipe_Slot_4 = getConfig().getString(craftingRecipePrefix + "Crafting_Recipe_Slot_4");
        Crafting_Recipe_Slot_5 = getConfig().getString(craftingRecipePrefix + "Crafting_Recipe_Slot_5");
        Crafting_Recipe_Slot_6 = getConfig().getString(craftingRecipePrefix + "Crafting_Recipe_Slot_6");
        Crafting_Recipe_Slot_7 = getConfig().getString(craftingRecipePrefix + "Crafting_Recipe_Slot_7");
        Crafting_Recipe_Slot_8 = getConfig().getString(craftingRecipePrefix + "Crafting_Recipe_Slot_8");
        Crafting_Recipe_Slot_9 = getConfig().getString(craftingRecipePrefix + "Crafting_Recipe_Slot_9");
        if (Chunk_Buster_Craft_Enabled) {
            getServer().clearRecipes();
            getServer().addRecipe(customRecipe());
        }
        saveConfig();
    }

    @Override
    public void onEnable() {

        getLogger().info(color("\u001B[32mEnabling Chunk Busters!\u001B[0m"));
        new ChunkBusterListener(this);
        getConfig().options().copyDefaults(true);
        saveConfig();
        loadConfig();
    }

    public static String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static ArrayList<String> colorList(ArrayList<String> Strings) {
        ArrayList<String> newStrings = new ArrayList<>();
        for (String string : Strings) {
            newStrings.add(color(string));
        }
        return newStrings;
    }

    @Override
    public void onDisable() {
        getServer().getScheduler().cancelTasks(this);
        getLogger().info(color("\u001B[31mDisabling Chunk Busters!\u001B[0m"));
        reloadConfig();
        saveConfig();
    }

    public ShapedRecipe customRecipe() {
        ShapedRecipe ChunkBusterRecipe = new ShapedRecipe(CreateChunkBuster(1));

        String individual_1 = "1";
        String individual_2 = "2";
        String individual_3 = "3";
        String individual_4 = "4";
        String individual_5 = "5";
        String individual_6 = "6";
        String individual_7 = "7";
        String individual_8 = "8";
        String individual_9 = "9";

        if (Crafting_Recipe_Slot_1.equalsIgnoreCase("air")) {
            individual_1 = " ";
        }
        if (Crafting_Recipe_Slot_2.equalsIgnoreCase("air")) {
            individual_2 = " ";
        }
        if (Crafting_Recipe_Slot_3.equalsIgnoreCase("air")) {
            individual_3 = " ";
        }
        if (Crafting_Recipe_Slot_4.equalsIgnoreCase("air")) {
            individual_4 = " ";
        }
        if (Crafting_Recipe_Slot_5.equalsIgnoreCase("air")) {
            individual_5 = " ";
        }
        if (Crafting_Recipe_Slot_6.equalsIgnoreCase("air")) {
            individual_6 = " ";
        }
        if (Crafting_Recipe_Slot_7.equalsIgnoreCase("air")) {
            individual_7 = " ";
        }
        if (Crafting_Recipe_Slot_8.equalsIgnoreCase("air")) {
            individual_8 = " ";
        }
        if (Crafting_Recipe_Slot_9.equalsIgnoreCase("air")) {
            individual_9 = " ";
        }
        ChunkBusterRecipe.shape(individual_1 + individual_2 + individual_3, individual_4 + individual_5 + individual_6, individual_7 + individual_8 + individual_9);


        if (!Crafting_Recipe_Slot_1.equalsIgnoreCase("air")) {
            ChunkBusterRecipe.setIngredient('1', Material.getMaterial(Crafting_Recipe_Slot_1.toUpperCase()));
        }
        if (!Crafting_Recipe_Slot_2.equalsIgnoreCase("air")) {
            ChunkBusterRecipe.setIngredient('2', Material.getMaterial(Crafting_Recipe_Slot_2.toUpperCase()));
        }
        if (!Crafting_Recipe_Slot_3.equalsIgnoreCase("air")) {
            ChunkBusterRecipe.setIngredient('3', Material.getMaterial(Crafting_Recipe_Slot_3.toUpperCase()));
        }
        if (!Crafting_Recipe_Slot_4.equalsIgnoreCase("air")) {
            ChunkBusterRecipe.setIngredient('4', Material.getMaterial(Crafting_Recipe_Slot_4.toUpperCase()));
        }
        if (!Crafting_Recipe_Slot_5.equalsIgnoreCase("air")) {
            ChunkBusterRecipe.setIngredient('5', Material.getMaterial(Crafting_Recipe_Slot_5.toUpperCase()));
        }
        if (!Crafting_Recipe_Slot_6.equalsIgnoreCase("air")) {
            ChunkBusterRecipe.setIngredient('6', Material.getMaterial(Crafting_Recipe_Slot_6.toUpperCase()));
        }
        if (!Crafting_Recipe_Slot_7.equalsIgnoreCase("air")) {
            ChunkBusterRecipe.setIngredient('7', Material.getMaterial(Crafting_Recipe_Slot_7.toUpperCase()));
        }
        if (!Crafting_Recipe_Slot_8.equalsIgnoreCase("air")) {
            ChunkBusterRecipe.setIngredient('8', Material.getMaterial(Crafting_Recipe_Slot_8.toUpperCase()));
        }
        if (!Crafting_Recipe_Slot_9.equalsIgnoreCase("air")) {
            ChunkBusterRecipe.setIngredient('9', Material.getMaterial(Crafting_Recipe_Slot_9.toUpperCase()));
        }

        return ChunkBusterRecipe;
    }


    public ItemStack CreateChunkBuster(int amount) {
        ItemStack chunkBuster = new ItemStack(Material.getMaterial(Chunk_Buster_Item_Material.toUpperCase()), amount);
        ItemMeta ChunkBusterMeta = chunkBuster.getItemMeta();
        ChunkBusterMeta.setDisplayName(color(Chunk_Buster_Name));
        if (Chunk_Buster_Item_Glow_Enabled) {
            ChunkBusterMeta.addEnchant(Enchantment.DURABILITY, 100, true);
        }
        ChunkBusterMeta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
        ChunkBusterMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        ChunkBusterMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        ChunkBusterMeta.setLore(colorList(Chunk_Buster_Lore));
        chunkBuster.setItemMeta(ChunkBusterMeta);
        return chunkBuster;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = null;
        try {
            player = (Player) sender;
        } catch (Exception e) {
            player = (Player) getServer().getConsoleSender();

        }

        if (cmd.getName().equalsIgnoreCase("ChunkBuster")) {
            if (args.length == 0) {
                sender.sendMessage(color(Reload_Usage));
                sender.sendMessage(color(Chunk_Buster_Give_Usage));
                return true;

            }
            if (args.length == 1 && args[0].equalsIgnoreCase("give")) {
                sender.sendMessage(color(Chunk_Buster_Give_Usage));
                return true;

            }
            if (args.length == 2 && args[0].equalsIgnoreCase("give")) {
                try {
                    Player player2give = getServer().getPlayer(args[1]);
                    int GiveAmount = 1;
                    player2give.getInventory().addItem(CreateChunkBuster(1));
                    String new_Chunk_Buster_Received = Chunk_Buster_Received;
                    String new_Chunk_Buster_Give_Success = Chunk_Buster_Give_Success;
                    if (new_Chunk_Buster_Give_Success.contains("{amount}")) {
                        new_Chunk_Buster_Give_Success = new_Chunk_Buster_Give_Success.replace("{amount}", GiveAmount + "");
                    }
                    if (new_Chunk_Buster_Give_Success.contains("{player}")) {
                        new_Chunk_Buster_Give_Success = new_Chunk_Buster_Give_Success.replace("{player}", player2give.getName() + "");
                    }

                    if (new_Chunk_Buster_Received.contains("{amount}")) {
                        new_Chunk_Buster_Received = new_Chunk_Buster_Received.replace("{amount}", GiveAmount + "");
                    }
                    if (new_Chunk_Buster_Received.contains("{player}")) {
                        new_Chunk_Buster_Received = new_Chunk_Buster_Received.replace("{player}", player.getName() + "");
                    }
                    player2give.sendMessage(color(new_Chunk_Buster_Received));
                    sender.sendMessage(color(new_Chunk_Buster_Give_Success));

                } catch (Exception error) {
                    sender.sendMessage(color(Chunk_Buster_Give_Unknown_Member_Error));
                    return true;

                }
                return true;

            }
            if (args.length == 3 && args[0].equalsIgnoreCase("give")) {
                if (!sender.hasPermission("chunkbuster.give")) {
                    sender.sendMessage(color(Chunk_Buster_Give_No_Permission));
                    return true;
                } else {
                    String GiveAmount = "1";
                    try {
                        GiveAmount = args[2];
                        int UnUsed_ToDetectIfPlayerExists = Integer.parseInt(GiveAmount);
                    } catch (Exception error) {
                        sender.sendMessage(color(Chunk_Buster_Give_Amount_Error));
                        return true;
                    }
                    try {
                        Player player2give = getServer().getPlayer(args[1]);
                        player2give.getInventory().addItem(CreateChunkBuster(Integer.parseInt(GiveAmount)));
                        String new_Chunk_Buster_Received = Chunk_Buster_Received;
                        String new_Chunk_Buster_Give_Success = Chunk_Buster_Give_Success;
                        if (new_Chunk_Buster_Give_Success.contains("{amount}")) {
                            new_Chunk_Buster_Give_Success = new_Chunk_Buster_Give_Success.replace("{amount}", GiveAmount + "");
                        }
                        if (new_Chunk_Buster_Give_Success.contains("{player}")) {
                            new_Chunk_Buster_Give_Success = new_Chunk_Buster_Give_Success.replace("{player}", player2give.getName() + "");
                        }

                        if (new_Chunk_Buster_Received.contains("{amount}")) {
                            new_Chunk_Buster_Received = new_Chunk_Buster_Received.replace("{amount}", GiveAmount + "");
                        }
                        if (new_Chunk_Buster_Received.contains("{player}")) {
                            new_Chunk_Buster_Received = new_Chunk_Buster_Received.replace("{player}", player.getName() + "");
                        }


                        player2give.sendMessage(color(new_Chunk_Buster_Received));
                        sender.sendMessage(color(new_Chunk_Buster_Give_Success));

                    } catch (Exception error) {
                        sender.sendMessage(color(Chunk_Buster_Give_Unknown_Member_Error));
                        return true;

                    }
                    return true;
                }
            }

            if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
                if (sender.hasPermission("chunkbuster.reload")) {
                    sender.sendMessage(color(Reload_Successful));
                    loadConfig();
                    return true;

                } else {
                    sender.sendMessage(color(Reload_No_Permission));
                    return true;
                }

            }
            return true;
        }
        return false;
    }

}

package com.i0dev.ChunkBusters.commands;

import com.i0dev.ChunkBusters.Heart;
import com.i0dev.ChunkBusters.config.GeneralConfig;
import com.i0dev.ChunkBusters.config.MessageConfig;
import com.i0dev.ChunkBusters.managers.ChunkBusterManager;
import com.i0dev.ChunkBusters.templates.AbstractCommand;
import com.i0dev.ChunkBusters.utility.MsgUtil;
import com.i0dev.ChunkBusters.utility.Utility;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.rmi.CORBA.Util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CmdChunkBusters extends AbstractCommand {

    MessageConfig msg;
    GeneralConfig config;
    ChunkBusterManager cbm;

    public CmdChunkBusters(Heart heart, String command) {
        super(heart, command);
    }

    @Override
    public void initialize() {
        msg = getHeart().getConfig(MessageConfig.class);
        config = getHeart().getConfig(GeneralConfig.class);
        cbm = getHeart().getManager(ChunkBusterManager.class);
    }

    @Override
    public void deinitialize() {
        msg = null;
        config = null;
        cbm = null;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
            MsgUtil.msg(sender, msg.getHelpPageHeader());
            MsgUtil.msg(sender, msg.getHelpUsage());
            MsgUtil.msg(sender, msg.getReloadUsage());
            MsgUtil.msg(sender, msg.getGiveUsage());
            return;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            if (!sender.hasPermission("ChunkBusters.reload.cmd")) {
                MsgUtil.msg(sender, msg.getNoPermission());
                return;
            }
            getHeart().reload();
            MsgUtil.msg(sender, msg.getReloadedConfig());
            return;
        }
        if (args[0].equalsIgnoreCase("give")) {
            if (!sender.hasPermission("ChunkBusters.give.cmd")) {
                MsgUtil.msg(sender, msg.getNoPermission());
                return;
            }
            getHeart().reload();
            if (args.length < 2) {
                MsgUtil.msg(sender, msg.getGiveUsage());
                return;
            }
            Player player = MsgUtil.getPlayer(args[1]);
            if (player == null) {
                MsgUtil.msg(sender, msg.getCantFindPlayer());
                return;
            }
            Integer amount = 1;
            if (args.length == 3) {
                amount = Utility.parseIntOrNull(args[2]);
                if (amount == null) {
                    MsgUtil.msg(sender, msg.getInvalidNumber());
                    return;
                }
            }

            ItemStack item = cbm.getChunkBusterItem(amount);
            player.getInventory().addItem(item);

            MsgUtil.msg(sender, msg.getYouGave(), new MsgUtil.Pair<>("{player}", player.getName()), new MsgUtil.Pair<>("{amount}", amount.toString()));
            MsgUtil.msg(player, msg.getReceived(), new MsgUtil.Pair<>("{amount}", amount.toString()));
            return;
        }
    }

    List<String> blank = new ArrayList<>();

    @Override
    public List<String> tabComplete(CommandSender sender, String[] args) {
        if (args.length == 1) return tabCompleteHelper(args[0], Arrays.asList("reload", "help", "give"));
        if (args.length == 2 && args[0].equalsIgnoreCase("give")) return null;
        if (args.length == 3 && args[0].equalsIgnoreCase("give"))
            return tabCompleteHelper(args[2], Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10"));
        return blank;
    }
}

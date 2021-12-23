package com.i0dev.ChunkBusters.config;

import com.i0dev.ChunkBusters.Heart;
import com.i0dev.ChunkBusters.templates.AbstractConfiguration;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MessageConfig extends AbstractConfiguration {


    String helpPageHeader = "&7&m_______&r&8[&r &c&lChunkBusters &8]&m_______";
    String reloadUsage = " &c* &7/ChunkBusters reload";
    String helpUsage = " &c* &7/ChunkBusters help";
    String giveUsage = " &c* &7/ChunkBusters give <player> [amount]";


    String received = "&7You have received &c{amount}&7 chunkbuster(s)";
    String youGave = "&7You gave &c{player} &7{amount} chunkbuster(s)";

    String youPlaced = "&7You successfully placed a chunkbuster.";
    String onlyInOwnLand = "&cYou can only place a chunkbuster in your own land.";

    String reloadedConfig = "&7You have&a reloaded&7 the configuration.";
    String noPermission = "&cYou don not have permission to run that command.";
    String cantFindPlayer = "&cThe player: &f{player}&c cannot be found!";
    String invalidNumber = "&cThe number &f{num} &cis invalid! Try again.";
    String cantRunAsConsole = "&cYou cannot run this command from console.";

    public MessageConfig(Heart heart, String path) {
        this.path = path;
        this.heart = heart;
    }
}

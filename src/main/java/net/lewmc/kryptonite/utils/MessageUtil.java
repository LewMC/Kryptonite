package net.lewmc.kryptonite.utils;

import net.lewmc.kryptonite.Kryptonite;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class MessageUtil {
    private final CommandSender cs;
    private final Kryptonite plugin;

    public MessageUtil(CommandSender cs, Kryptonite plugin) {
        this.cs = cs;
        this.plugin = plugin;
    }

    public void PrivateMessage(String message) {
        this.cs.sendMessage(ChatColor.BOLD+""+ChatColor.GREEN+"[Kryptonite] "+ChatColor.RESET+message);
    }
}

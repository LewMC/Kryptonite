package net.lewmc.kryptonite.utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class MessageUtil {
    private final CommandSender cs;

    public MessageUtil(CommandSender cs) {
        this.cs = cs;
    }

    public void Success(String message) {
        this.cs.sendMessage(ChatColor.DARK_GREEN+"[KR] "+ChatColor.GREEN+message);
    }

    public void Info(String message) {
        this.cs.sendMessage(ChatColor.BLUE+"[KR] "+ChatColor.RESET+message);
    }

    public void Warning(String message) {
        this.cs.sendMessage(ChatColor.GOLD+"[KR] "+ChatColor.YELLOW+message);
    }

    public void Error(String message) {
        this.cs.sendMessage(ChatColor.DARK_RED+"[KR] "+ChatColor.RED+message);
    }
}

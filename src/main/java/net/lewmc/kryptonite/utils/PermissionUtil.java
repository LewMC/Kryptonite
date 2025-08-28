package net.lewmc.kryptonite.utils;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

/**
 * @deprecated
 */
@Deprecated
public class PermissionUtil {
    public boolean isOperator(CommandSender commandSender) {
        if (commandSender instanceof Player) {
            String player = commandSender.getName();
            return Objects.requireNonNull(Bukkit.getServer().getPlayer(player)).isOp();
        } else {
            return true;
        }
    }
}

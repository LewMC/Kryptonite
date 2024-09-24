package net.lewmc.kryptonite.commands;

import net.lewmc.kryptonite.Kryptonite;
import net.lewmc.kryptonite.kos.gui.KOS_MainGui;
import net.lewmc.kryptonite.utils.MessageUtil;
import net.lewmc.kryptonite.utils.PermissionUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class OptimiseCommand implements CommandExecutor {
    private final Kryptonite plugin;

    /**
     * Constructor for the Optimise class.
     * @param plugin References to the main plugin class.
     */
    public OptimiseCommand(Kryptonite plugin) {
        this.plugin = plugin;
    }

    /**
     * /kryptonite command handler.
     * @param commandSender Information about who sent the command - player or console.
     * @param command Information about what command was sent.
     * @param s Command label - not used here.
     * @param args The command's arguments.
     * @return boolean true/false - was the command accepted and processed or not?
     */
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        MessageUtil message = new MessageUtil(commandSender);
        PermissionUtil perm = new PermissionUtil();

        if (perm.isOperator(commandSender)) {
            if (commandSender instanceof Player) {
                KOS_MainGui gui = new KOS_MainGui(this.plugin, commandSender);
                gui.show();
            } else {
                message.Info("Please run this command in-game.");
            }
        } else {
            message.Error("You do not have the required permissions to run this command.");
            message.Error("Please visit https://wiki.lewmc.net/kr-commands.html for more information.");
        }
        return true;
    }
}

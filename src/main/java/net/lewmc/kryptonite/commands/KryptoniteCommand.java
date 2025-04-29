package net.lewmc.kryptonite.commands;

import net.lewmc.kryptonite.Kryptonite;
import net.lewmc.kryptonite.utils.MessageUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Objects;

public class KryptoniteCommand implements CommandExecutor {
    private final Kryptonite plugin;

    /**
     * Constructor for the Kryptonite class.
     * @param plugin References to the main plugin class.
     */
    public KryptoniteCommand(Kryptonite plugin) {
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

        if (args.length >= 1) {
            if (Objects.equals(args[0], "help")) {
                message.Info("§6-------------- Kryptonite Help --------------");
                message.Info("/kr - Main command.");
                message.Info("/kr report - Run a server report.");
                message.Info("/kos - Run the Kryptonite Optimisation System.");
                message.Info("/edb check - Check your server for exploits.");
                message.Info("/edb patch - Patch your server's exploits.");
                message.Info("§6----------------- Page 1/1 ------------------");
            } else {
                message.Error("Unknown command. Use /kr help for help.");
            }
        } else {
            message.Info("Kryptonite version "+this.plugin.getDescription().getVersion()+ " by LewMC.");
            message.Info("You must be an operator to run Kryptonite");
            message.Info("commands.");
            message.Info("");
            message.Info("Feedback: https://github.com/lewmc/Kryptonite");
            message.Info("");
            message.Info("Help: /kr help");
        }
        return true;
    }
}

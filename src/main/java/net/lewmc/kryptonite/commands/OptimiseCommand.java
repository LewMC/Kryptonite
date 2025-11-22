package net.lewmc.kryptonite.commands;

import net.lewmc.foundry.Permissions;
import net.lewmc.kryptonite.Kryptonite;
import net.lewmc.kryptonite.kos.AutoKOS;
import net.lewmc.kryptonite.kos.gui.KOS_MainGui;
import net.lewmc.kryptonite.utils.MessageUtil;
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

        if (new Permissions(commandSender).isOp()) {
            if (commandSender instanceof Player) {
                KOS_MainGui gui = new KOS_MainGui(this.plugin, commandSender);
                gui.show();
            } else {
                message.Success("KOS is better in-game.");
                message.Success("You can access more features through our in-game GUI.");
                message.Info("Starting KOS CLI...");
                message.Info("");
                if (args.length == 0) {
                    message.Warning("Please specify a profile file to use.");
                    message.Warning("For example: /kos YouHaveTrouble.kos");
                } else if (args.length == 1) {
                    message.Warning("Please specify if your server world is pre-generated.");
                    message.Warning("For example: /kos "+args[0]+" true");
                    message.Warning("For example: /kos "+args[0]+" false");
                } else {
                    AutoKOS ak = new AutoKOS(this.plugin, commandSender);
                    if (Objects.equals(args[1], "true") || Objects.equals(args[1], "yes") || Objects.equals(args[1], "y")) {
                        ak.run(true, args[0]);
                    } else if (Objects.equals(args[1], "false") || Objects.equals(args[1], "no") || Objects.equals(args[1], "n")) {
                        ak.run(false, args[0]);
                    } else {
                        message.Warning("Please specify if your server world is pre-generated.");
                        message.Warning("For example: /kos "+args[0]+" true");
                        message.Warning("For example: /kos "+args[0]+" false");
                    }
                }
            }
        } else {
            message.Error("You do not have the required permissions to run this command.");
            message.Error("Please visit https://wiki.lewmc.net/kr-commands.html for more information.");
        }
        return true;
    }
}

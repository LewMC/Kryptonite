package net.lewmc.kryptonite.commands;

import net.lewmc.kryptonite.Kryptonite;
import net.lewmc.kryptonite.kos.KOS;
import net.lewmc.kryptonite.kos.gui.KosMainGui;
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
                KosMainGui gui = new KosMainGui(this.plugin, commandSender);
                gui.show();
            } else {
                if (args.length == 1) {
                    if (Objects.equals(args[0].toLowerCase(), "yes")) {
                        message.Info("Kryptonite will now run it's optimisation system.");
                        message.Info("You should backup your server before running Kryptonite.");
                        message.Info("You'll need to restart the server after completion for changes to be made.");
                        message.Info("");
                        message.Info("Using profile '" + this.plugin.getConfig().getString("kos.default-profile") + "'");

                        KOS kos = new KOS(commandSender, this.plugin, this.plugin.getConfig().getString("kos.default-profile"));
                        kos.runDefault(true);
                    } else if (Objects.equals(args[0].toLowerCase(), "no") && perm.isOperator(commandSender)) {
                        message.Info("Kryptonite will now run it's optimisation system.");
                        message.Info("You should backup your server before running Kryptonite.");
                        message.Info("You'll need to restart the server after completion for changes to be made.");
                        message.Info("");
                        message.Info("Using profile '" + this.plugin.getConfig().getString("kos.default-profile") + "'");

                        KOS kos = new KOS(commandSender, this.plugin, this.plugin.getConfig().getString("kos.default-profile"));
                        kos.runDefault(false);
                    } else {
                        message.Error("Unknown command. Use /kos for help.");
                    }
                } else {
                    message.Info("--- KRYPTONITE OPTIMISATION SYSTEM ---");
                    message.Info("KOS will use the kos.default-profile value set in Kryptonite.yml");
                    message.Info("Please ensure this is correct before continuing.");
                    message.Info("Current profile: " + this.plugin.getConfig().getString("kos.default-profile"));
                    message.Info("");
                    message.Info("Have you pregenerated your world and set a vanilla world border?");
                    message.Info("This will affect which optimisations can be applied.");
                    message.Info("");
                    message.Info("To continue enter '/kos yes' if you have pregenerated");
                    message.Info("or '/kos no' if you have not.");
                }
            }
        } else {
            message.Error("You do not have the required permissions to run this command.");
            message.Error("Please visit https://wiki.lewmc.net/index.php/Kryptonite_Commands for more information.");
        }
        return true;
    }
}

package net.lewmc.kryptonite.commands;

import de.themoep.inventorygui.InventoryGui;
import de.themoep.inventorygui.StaticGuiElement;
import net.lewmc.kryptonite.Kryptonite;
import net.lewmc.kryptonite.kos.KOS;
import net.lewmc.kryptonite.kos.gui.KosMainGui;
import net.lewmc.kryptonite.utils.MessageUtil;
import net.lewmc.kryptonite.utils.PermissionUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

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
            if (args.length == 1) {
                if (Objects.equals(args[0].toLowerCase(), "yes")) {
                    message.Info("Kryptonite will now run it's optimisation system.");
                    message.Info("You should backup your server before running Kryptonite.");
                    message.Info("You'll need to restart the server after completion for changes to be made.");
                    message.Info("");

                    KOS kos = new KOS(commandSender, this.plugin, this.plugin.getConfig().getString("kos.profile"));
                    kos.runDefault(true);
                } else if (Objects.equals(args[0].toLowerCase(), "no") && perm.isOperator(commandSender)) {
                    message.Info("Kryptonite will now run it's optimisation system.");
                    message.Info("You should backup your server before running Kryptonite.");
                    message.Info("You'll need to restart the server after completion for changes to be made.");
                    message.Info("");

                    KOS kos = new KOS(commandSender, this.plugin, this.plugin.getConfig().getString("kos.profile"));
                    kos.runDefault(false);
                } else {
                    message.Error("Unknown command. Use /kos for help.");
                }
            } else {
                if (commandSender instanceof Player) {
                    KosMainGui gui = new KosMainGui(this.plugin, commandSender);
                    gui.show();
                } else {
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

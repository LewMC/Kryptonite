package net.lewmc.kryptonite.kos.gui;

import de.themoep.inventorygui.InventoryGui;
import de.themoep.inventorygui.StaticGuiElement;
import net.lewmc.kryptonite.Kryptonite;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

/**
 * KOS Main GUI
 */
public class KOS_MainGui {
    private final Kryptonite plugin;
    private final CommandSender user;
    private InventoryGui gui;

    /**
     * Constructor for the KOS Main GUI
     * @param plugin Kryptonite - Reference to the main plugin class.
     * @param user CommandSender - The user who sent the command.
     */
    public KOS_MainGui(Kryptonite plugin, CommandSender user) {
        this.plugin = plugin;
        this.user = user;
    }

    /**
     * Shows the KOS Main GUI
     */
    public void show() {
        InventoryHolder holder = this.user.getServer().getPlayer(this.user.getName());
        this.gui = new InventoryGui(this.plugin, holder, "Kryptonite Optimisation System", this.getElements());
        this.addElements();

        this.gui.build();
        this.gui.show((Player) this.user);
    }

    /**
     * Adds pre-programmed elements to the GUI
     */
    private void addElements() {
        if (!this.plugin.SupportedConfigurations.isEmpty()) {
            this.gui.addElement(new StaticGuiElement('a',
                    new ItemStack(Material.COMMAND_BLOCK),
                    1,
                    click -> {
                        click.getGui().close();
                        return true;
                    },
                    ChatColor.BLUE + "Automatic",
                    ChatColor.AQUA + "Automatically optimise your server."
            ));
            this.gui.addElement(new StaticGuiElement('m',
                    new ItemStack(Material.CRAFTING_TABLE),
                    1,
                    click -> {
                        click.getGui().close();
                        KOS_ManualGUI manualGUI = new KOS_ManualGUI(this.plugin, this.user);
                        manualGUI.show();
                        return true;
                    },
                    ChatColor.BLUE + "Manual",
                    ChatColor.AQUA + "Manually optimise your server."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement('a',
                    new ItemStack(Material.BARRIER),
                    1,
                    click -> true,
                    ChatColor.DARK_RED + "Automatic",
                    ChatColor.RED + "Automatically optimise your server.",
                    ChatColor.RED + "Your server does not support this."
            ));
            this.gui.addElement(new StaticGuiElement('m',
                    new ItemStack(Material.BARRIER),
                    1,
                    click -> true,
                    ChatColor.DARK_RED + "Manual",
                    ChatColor.RED + "Manually optimise your server.",
                    ChatColor.RED + "Your server does not support this."
            ));
        }

        this.gui.addElement(new StaticGuiElement('e',
                new ItemStack(Material.OAK_DOOR),
                1,
                click -> {
                    click.getGui().close();
                    return true;
                },
                ChatColor.DARK_RED + "Exit"
        ));

        this.gui.addElement(new StaticGuiElement('h',
                new ItemStack(Material.BOOK),
                1,
                click -> {
                    click.getWhoClicked().sendMessage(ChatColor.YELLOW + "https://wiki.lewmc.net/kr-kos-guide.html");
                    click.getGui().close();
                    return true;
                },
                ChatColor.DARK_GREEN + "Help",
                ChatColor.GREEN + "Not sure what to do? Click here for",
                ChatColor.GREEN + "a link to our Wiki for help."
        ));
    }

    /**
     * Determines where the elements should be positioned.
     * @return String[] - The position of the elements.
     */
    private String[] getElements() {

        return new String[]{
                "   a m   ",
                "         ",
                "   h e   "
        };
    }
}

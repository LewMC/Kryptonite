package net.lewmc.kryptonite.kos.gui;

import de.themoep.inventorygui.InventoryGui;
import de.themoep.inventorygui.StaticGuiElement;
import net.lewmc.kryptonite.Kryptonite;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Values that should always be the same across GUIs.
 */
public class KOS_GuiConstants 
{
    private final Kryptonite plugin;
    private final InventoryGui gui;

    /**
     * Constructor for the KOS_GuiConstants class.
     * @param plugin Kryptonite - Reference to the main plugin class.
     * @param gui InventoryGui - The GUI to add the elements to.
     */
    public KOS_GuiConstants(Kryptonite plugin, InventoryGui gui) {
        this.plugin = plugin;
        this.gui = gui;
    }

    /**
     * Adds the 'w', 'x', and 'y' (help, alerts, and exit) positions to the selected GUI.
     */
    public void addConstants() {
        this.gui.addElement(new StaticGuiElement('t',
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

        if (plugin.restartRequired) {
            this.gui.addElement(new StaticGuiElement('u',
                    new ItemStack(Material.YELLOW_CONCRETE),
                    1,
                    click -> true,
                    ChatColor.GOLD + "Alerts",
                    ChatColor.YELLOW + "Restart Required - You must restart your",
                    ChatColor.YELLOW + "server for changes to be applied."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement('x',
                    new ItemStack(Material.GREEN_CONCRETE),
                    1,
                    click -> true,
                    ChatColor.DARK_GREEN + "No pending alerts."
            ));
        }

        this.gui.addElement(new StaticGuiElement('v',
                new ItemStack(Material.OAK_DOOR),
                1,
                click -> {
                    click.getGui().close();
                    return true;
                },
                ChatColor.DARK_RED + "Exit"
        ));
    }
}

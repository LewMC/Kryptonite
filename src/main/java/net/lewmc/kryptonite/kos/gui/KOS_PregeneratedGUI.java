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

import java.io.File;
import java.io.IOException;

/**
 * KOS Auto GUI
 * @deprecated
 */
@Deprecated
public class KOS_PregeneratedGUI {
    private final Kryptonite plugin;
    private final CommandSender user;
    private InventoryGui gui;

    /**
     * Constructor for the KOS Auto GUI
     * @param plugin Kryptonite - Reference to the main plugin class.
     * @param user CommandSender - The user who sent the command.
     */
    public KOS_PregeneratedGUI(Kryptonite plugin, CommandSender user) {
        this.plugin = plugin;
        this.user = user;
    }

    /**
     * Shows the KOS Auto GUI
     */
    public void show() {
        InventoryHolder holder = this.user.getServer().getPlayer(this.user.getName());
        this.gui = new InventoryGui(this.plugin, holder, "Is your world pregenerated?", this.getElements());
        this.addElements();

        this.gui.build();
        this.gui.show((Player) this.user);
    }

    /**
     * Adds pre-programmed elements to the GUI
     */
    private void addElements() {
        this.gui.addElement(new StaticGuiElement('a',
                new ItemStack(Material.PAPER),
                1,
                click -> true,
                ChatColor.BLUE + "Is your world pre-generated?",
                ChatColor.AQUA + "If you've used a plugin such as Chunky to",
                ChatColor.AQUA + "pre-generate your world, click yes.",
                ChatColor.AQUA + "",
                ChatColor.AQUA + "This helps with performance, and means more",
                ChatColor.AQUA + "features will be enabled if you use KOS's",
                ChatColor.AQUA + "automatic mode."
        ));

        this.gui.addElement(new StaticGuiElement('b',
                new ItemStack(Material.RED_CONCRETE),
                1,
                click -> {
                    this.plugin.getConfig().set("kos.world-is-pregenerated", "1");
                    try {
                        this.plugin.getConfig().save(new File(this.plugin.getDataFolder()+"/config.yml"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    this.gui.close();

                    KOS_AutoGUI ag = new KOS_AutoGUI(this.plugin, user);
                    ag.show();
                    return true;
                },
                ChatColor.DARK_RED + "NO",
                ChatColor.RED + "My world IS NOT pre-generated."
        ));

        this.gui.addElement(new StaticGuiElement('c',
                new ItemStack(Material.GREEN_CONCRETE),
                1,
                click -> {
                    this.plugin.getConfig().set("kos.world-is-pregenerated", "2");
                    try {
                        this.plugin.getConfig().save(new File(this.plugin.getDataFolder()+"/config.yml"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    this.gui.close();

                    KOS_AutoGUI ag = new KOS_AutoGUI(this.plugin, user);
                    ag.show();
                    return true;
                },
                ChatColor.DARK_GREEN + "YES",
                ChatColor.GREEN + "My world IS pre-generated."
        ));

        KOS_GuiConstants consts = new KOS_GuiConstants(this.plugin, this.gui);
        consts.addConstants();
    }

    /**
     * Determines where the elements should be positioned.
     * @return String[] - The position of the elements.
     */
    private String[] getElements() {
        return new String[]{
                "    a    ",
                "   b c   ",
                "  w x y  "
        };
    }
}

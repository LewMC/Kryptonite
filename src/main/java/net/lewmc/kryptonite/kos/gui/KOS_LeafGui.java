package net.lewmc.kryptonite.kos.gui;

import de.themoep.inventorygui.GuiElement;
import de.themoep.inventorygui.InventoryGui;
import de.themoep.inventorygui.StaticGuiElement;
import net.lewmc.kryptonite.Kryptonite;
import net.lewmc.kryptonite.kos.config.Leaf;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

/**
 * KOS Leaf GUI
 */
public class KOS_LeafGui {
    private final Kryptonite plugin;
    private final CommandSender user;
    private InventoryGui gui;
    private final Leaf leaf;

    /**
     * Constructor for the KOS Pufferfish GUI
     * @param plugin Kryptonite - Reference to the main plugin class.
     * @param user CommandSender - The user who sent the command.
     */
    public KOS_LeafGui(Kryptonite plugin, CommandSender user) {
        this.plugin = plugin;
        this.user = user;

        this.leaf = new Leaf(plugin, user);
    }

    /**
     * Shows the KOS Pufferfish GUI
     */
    public void show() {
        InventoryHolder holder = this.user.getServer().getPlayer(this.user.getName());
        this.gui = new InventoryGui(this.plugin, holder, "KOS - Leaf Configuration", this.getElements());
        this.addElements();

        this.gui.build();
        this.gui.show((Player) this.user);
    }

    /**
     * Adds pre-programmed elements to the GUI
     */
    private void addElements() {
        this.parallelWorldTracingEnabled('a');

        KOS_GuiConstants consts = new KOS_GuiConstants(this.plugin, this.gui);
        consts.addConstants();
    }

    /**
     * Determines where the elements should be positioned.
     * @return String[] - The position of the elements.
     */
    private String[] getElements() {

        return new String[]{
                "a        ",
                "         ",
                "  w x y  "
        };
    }

    private void parallelWorldTracingEnabled(char id) {
        boolean value = this.leaf.getBoolean(Leaf.Key.PARALLEL_WORLD_TRACING_ENABLED);
        if (value) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> { this.leaf.setBoolean(Leaf.Key.PARALLEL_WORLD_TRACING_ENABLED, false); return true; },
                    ChatColor.GOLD + "Parallel World Tracing",
                    ChatColor.YELLOW + "true",
                    ChatColor.YELLOW + "Experimental feature, potentially unsafe.",
                    ChatColor.YELLOW + "Potential performance gain.",
                    ChatColor.YELLOW + "May cause issues with some plugins.",
                    ChatColor.BLUE + "Click to toggle true/false."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> { this.leaf.setBoolean(Leaf.Key.PARALLEL_WORLD_TRACING_ENABLED, true); return true; },
                    ChatColor.GOLD + "Parallel World Tracing",
                    ChatColor.YELLOW + "false",
                    ChatColor.YELLOW + "Experimental feature, potentially unsafe.",
                    ChatColor.YELLOW + "Potential performance gain.",
                    ChatColor.YELLOW + "May cause issues with some plugins.",
                    ChatColor.BLUE + "Click to toggle true/false."
            ));
        }
    }

    private boolean setInt(Leaf.Key key, GuiElement.Click click, int value, boolean canBeDefault) {
        click.getGui().close();
        if (click.getType() == ClickType.RIGHT) {
            this.leaf.setInt(key, value + 1);
        } else if (click.getType() == ClickType.SHIFT_RIGHT) {
            this.leaf.setInt(key, value + 10);
        } else if (click.getType() == ClickType.LEFT && value != 0) {
            this.leaf.setInt(key, value - 1);
        } else if (click.getType() == ClickType.SHIFT_LEFT && value >= 10) {
            this.leaf.setInt(key, value - 10);
        } else if ((click.getType() == ClickType.LEFT || click.getType() == ClickType.SHIFT_LEFT) && value == 0 && canBeDefault) {
            this.leaf.setString(key, "default");
        }

        this.show();
        return true;
    }
}

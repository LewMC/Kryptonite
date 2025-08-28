package net.lewmc.kryptonite.kos.gui;

import de.themoep.inventorygui.GuiElement;
import de.themoep.inventorygui.InventoryGui;
import de.themoep.inventorygui.StaticGuiElement;
import net.lewmc.kryptonite.Kryptonite;
import net.lewmc.kryptonite.kos.config.Spigot;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

/**
 * KOS Spigot GUI (Page 2/2)
 * @deprecated
 */
@Deprecated
public class KOS_SpigotGui_2 {
    private final Kryptonite plugin;
    private final CommandSender user;
    private InventoryGui gui;
    private final Spigot spigot;

    /**
     * Constructor for the KOS Spigot GUI (Page 2/2)
     * @param plugin Kryptonite - Reference to the main plugin class.
     * @param user CommandSender - The user who sent the command.
     */
    public KOS_SpigotGui_2(Kryptonite plugin, CommandSender user) {
        this.plugin = plugin;
        this.user = user;

        this.spigot = new Spigot(plugin, user);
    }

    /**
     * Shows the KOS Spigot GUI (Page 2/2)
     */
    public void show() {
        InventoryHolder holder = this.user.getServer().getPlayer(this.user.getName());
        this.gui = new InventoryGui(this.plugin, holder, "KOS - Spigot Configuration (2/2)", this.getElements());
        this.addElements();

        this.gui.build();
        this.gui.show((Player) this.user);
    }

    /**
     * Adds pre-programmed elements to the GUI
     */
    private void addElements() {
        this.ticksPerHopperCheck('a');

        KOS_GuiConstants consts = new KOS_GuiConstants(this.plugin, this.gui);
        consts.addConstants();

        this.gui.addElement(new StaticGuiElement('v',
                    new ItemStack(Material.OAK_SIGN),
                1,
                click -> {
                    click.getGui().close();
                    KOS_SpigotGui_1 spigotGui1 = new KOS_SpigotGui_1(this.plugin, this.user);
                    spigotGui1.show();
                    return true;
                },
                ChatColor.WHITE + "Previous page"
        ));
    }

    /**
     * Determines where the elements should be positioned.
     * @return String[] - The position of the elements.
     */
    private String[] getElements() {

        return new String[]{
                "a        ",
                "         ",
                "v w x y  "
        };
    }

    private void ticksPerHopperCheck(char id) {
        int value = this.spigot.getInt(Spigot.Key.TICKS_PER_HOPPER_CHECK);
        if (value == 8) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(Spigot.Key.TICKS_PER_HOPPER_CHECK, click, value, false),
                    ChatColor.DARK_GREEN + "Ticks per Hopper Check",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Ideal value.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value > 8) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(Spigot.Key.TICKS_PER_HOPPER_CHECK, click, value, false),
                    ChatColor.GOLD + "Ticks per Hopper Check",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too high - impact to player experience.",
                    ChatColor.YELLOW + "Hopper-based clocks and item sorting systems may break.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(Spigot.Key.TICKS_PER_HOPPER_CHECK, click, value, false),
                    ChatColor.DARK_RED + "Ticks per Hopper Check",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too low - impact to performance.",
                    ChatColor.RED + "Hopper-based clocks and item sorting systems may also break.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private boolean setInt(Spigot.Key key, GuiElement.Click click, int value, boolean canBeDefault) {
        click.getGui().close();
        if (click.getType() == ClickType.RIGHT) {
            this.spigot.setInt(key, value + 1);
        } else if (click.getType() == ClickType.SHIFT_RIGHT) {
            this.spigot.setInt(key, value + 10);
        } else if (click.getType() == ClickType.LEFT && value != 0) {
            this.spigot.setInt(key, value - 1);
        } else if (click.getType() == ClickType.SHIFT_LEFT && value >= 10) {
            this.spigot.setInt(key, value - 10);
        } else if ((click.getType() == ClickType.LEFT || click.getType() == ClickType.SHIFT_LEFT) && value == 0 && canBeDefault) {
            this.spigot.setString(key, "default");
        }

        this.show();
        return true;
    }
}

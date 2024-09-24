package net.lewmc.kryptonite.kos.gui;

import de.themoep.inventorygui.GuiElement;
import de.themoep.inventorygui.InventoryGui;
import de.themoep.inventorygui.StaticGuiElement;
import net.lewmc.kryptonite.Kryptonite;
import net.lewmc.kryptonite.kos.config.Bukkit;
import net.lewmc.kryptonite.kos.config.ServerProperties;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

/**
 * KOS Bukkit GUI
 */
public class KOS_BukkitGui {
    private final Kryptonite plugin;
    private final CommandSender user;
    private InventoryGui gui;
    private Bukkit bukkit;

    /**
     * Constructor for the KOS Bukkit GUI
     * @param plugin Kryptonite - Reference to the main plugin class.
     * @param user CommandSender - The user who sent the command.
     */
    public KOS_BukkitGui(Kryptonite plugin, CommandSender user) {
        this.plugin = plugin;
        this.user = user;

        this.bukkit = new Bukkit(plugin, user);
    }

    /**
     * Shows the KOS Bukkit GUI
     */
    public void show() {
        InventoryHolder holder = this.user.getServer().getPlayer(this.user.getName());
        this.gui = new InventoryGui(this.plugin, holder, "KOS - Server Configuration", this.getElements());
        this.addElements();

        this.gui.build();
        this.gui.show((Player) this.user);
    }

    /**
     * Adds pre-programmed elements to the GUI
     */
    private void addElements() {
        this.spawnLimitMonsters('a');

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

    private void spawnLimitMonsters(char id) {
        int value = this.bukkit.getInt(Bukkit.Key.SPAWN_LIMITS_MONSTERS);
        if (value == 256) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(Bukkit.Key.SPAWN_LIMITS_MONSTERS, click, value),
                    ChatColor.DARK_GREEN + "Spawn Limit (Monsters)",
                    ChatColor.GREEN + "256",
                    ChatColor.GREEN + "Ideal value",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value < 256) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(Bukkit.Key.SPAWN_LIMITS_MONSTERS, click, value),
                    ChatColor.DARK_RED + "Spawn Limit (Monsters)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too low.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(Bukkit.Key.SPAWN_LIMITS_MONSTERS, click, value),
                    ChatColor.DARK_RED + "Spawn Limit (Monsters)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private boolean setInt(Bukkit.Key key, GuiElement.Click click, int value) {
        click.getGui().close();
        if (click.getType() == ClickType.RIGHT) {
            this.bukkit.setInt(key, value + 1);
        } else if (click.getType() == ClickType.LEFT) {
            this.bukkit.setInt(key, value - 1);
        }
        this.show();
        return true;
    }
}

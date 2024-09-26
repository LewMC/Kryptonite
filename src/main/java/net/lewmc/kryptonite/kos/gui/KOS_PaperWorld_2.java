package net.lewmc.kryptonite.kos.gui;

import de.themoep.inventorygui.GuiElement;
import de.themoep.inventorygui.InventoryGui;
import de.themoep.inventorygui.StaticGuiElement;
import net.lewmc.kryptonite.Kryptonite;
import net.lewmc.kryptonite.kos.config.PaperWorld;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

/**
 * KOS Paper World GUI (Page 2/5)
 */
public class KOS_PaperWorld_2 {
    private final Kryptonite plugin;
    private final CommandSender user;
    private final PaperWorld paperWorld;
    private InventoryGui gui;

    /**
     * Constructor for the KOS Paper World GUI (Page 2/5)
     * @param plugin Kryptonite - Reference to the main plugin class.
     * @param user CommandSender - The user who sent the command.
     */
    public KOS_PaperWorld_2(Kryptonite plugin, CommandSender user) {
        this.plugin = plugin;
        this.user = user;
        this.paperWorld = new PaperWorld(plugin, user);
    }

    /**
     * Shows the KOS Paper World GUI (Page 2/5)
     */
    public void show() {
        InventoryHolder holder = this.user.getServer().getPlayer(this.user.getName());
        this.gui = new InventoryGui(this.plugin, holder, "KOS - Paper World Configuration (2/5)", this.getElements());
        this.addElements();

        this.gui.build();
        this.gui.show((Player) this.user);
    }

    /**
     * Adds pre-programmed elements to the GUI
     */
    private void addElements() {
        this.mobSpawnRange('a');

        KOS_GuiConstants consts = new KOS_GuiConstants(this.plugin, this.gui);
        consts.addConstants();

        this.gui.addElement(new StaticGuiElement('v',
                    new ItemStack(Material.OAK_SIGN),
                1,
                click -> {
                    click.getGui().close();
                    KOS_PaperWorld_1 prevGui = new KOS_PaperWorld_1(this.plugin, this.user);
                    prevGui.show();
                    return true;
                },
                ChatColor.WHITE + "Previous page"
        ));

        this.gui.addElement(new StaticGuiElement('z',
                    new ItemStack(Material.OAK_SIGN),
                1,
                click -> {
                    click.getGui().close();
                    KOS_PaperWorld_3 nextGui = new KOS_PaperWorld_3(this.plugin, this.user);
                    nextGui.show();
                    return true;
                },
                ChatColor.WHITE + "Next page"
        ));
    }

    /**
     * Determines where the elements should be positioned.
     * @return String[] - The position of the elements.
     */
    private String[] getElements() {

        return new String[]{
                "abcdefghi",
                "jklmnopqr",
                "v w x y z"
        };
    }

    private void mobSpawnRange(char id) {
        /*
        NOT VALID OR COMPLETED

        int value = this.spigot.getInt(Spigot.Key.MOB_SPAWN_RANGE);
        if (value >= 3 && value <= 8) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(Spigot.Key.MOB_SPAWN_RANGE, click, value, false),
                    ChatColor.DARK_GREEN + "Mob Spawn Range",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value < 3) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(Spigot.Key.MOB_SPAWN_RANGE, click, value, false),
                    ChatColor.GOLD + "Delay Chunk Unloads By",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.DELAY_CHUNK_UNLOADS_BY, click, value, false),
                    ChatColor.DARK_RED + "Delay Chunk Unloads By",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
        */
    }

    private boolean setInt(PaperWorld.Key key, GuiElement.Click click, int value, boolean canBeDefault) {
        click.getGui().close();
        if (click.getType() == ClickType.RIGHT) {
            this.paperWorld.setInt(key, value + 1);
        } else if (click.getType() == ClickType.SHIFT_RIGHT) {
            this.paperWorld.setInt(key, value + 10);
        } else if (click.getType() == ClickType.LEFT && value != 0) {
            this.paperWorld.setInt(key, value - 1);
        } else if (click.getType() == ClickType.SHIFT_LEFT && value >= 10) {
            this.paperWorld.setInt(key, value - 10);
        } else if ((click.getType() == ClickType.LEFT || click.getType() == ClickType.SHIFT_LEFT) && value == 0 && canBeDefault) {
            this.paperWorld.setString(key, "default");
        }

        this.show();
        return true;
    }
}

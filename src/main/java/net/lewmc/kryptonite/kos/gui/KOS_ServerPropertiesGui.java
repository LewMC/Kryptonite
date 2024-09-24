package net.lewmc.kryptonite.kos.gui;

import de.themoep.inventorygui.GuiElement;
import de.themoep.inventorygui.InventoryGui;
import de.themoep.inventorygui.StaticGuiElement;
import net.lewmc.kryptonite.Kryptonite;
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
 * KOS Main GUI
 */
public class KOS_ServerPropertiesGui {
    private final Kryptonite plugin;
    private final CommandSender user;
    private InventoryGui gui;
    private ServerProperties properties;

    /**
     * Constructor for the KOS Main GUI
     * @param plugin Kryptonite - Reference to the main plugin class.
     * @param user CommandSender - The user who sent the command.
     */
    public KOS_ServerPropertiesGui(Kryptonite plugin, CommandSender user) {
        this.plugin = plugin;
        this.user = user;

        this.properties = new ServerProperties(this.plugin);
    }

    /**
     * Shows the KOS Main GUI
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
        this.networkCompressionThreshold('a');
        this.viewDistance('b');
        this.simulationDistance('c');
        this.syncChunkWrites('d');

        KOS_GuiConstants consts = new KOS_GuiConstants(this.plugin, this.gui);
        consts.addConstants();
    }

    /**
     * Determines where the elements should be positioned.
     * @return String[] - The position of the elements.
     */
    private String[] getElements() {

        return new String[]{
                " a b c d ",
                "         ",
                "  w x y  "
        };
    }

    private void networkCompressionThreshold(char id) {
        int value = this.properties.getInt(ServerProperties.Config.NETWORK_COMPRESSION_THRESHOLD);
        if (value == 256) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(ServerProperties.Config.NETWORK_COMPRESSION_THRESHOLD, click, value),
                    ChatColor.DARK_GREEN + "Network Compression Threshold",
                    ChatColor.GREEN + "256",
                    ChatColor.GREEN + "Ideal value",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value < 256) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(ServerProperties.Config.NETWORK_COMPRESSION_THRESHOLD, click, value),
                    ChatColor.DARK_RED + "Network Compression Threshold",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too low.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(ServerProperties.Config.NETWORK_COMPRESSION_THRESHOLD, click, value),
                    ChatColor.DARK_RED + "Network Compression Threshold",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void viewDistance(char id) {
        int value = this.properties.getInt(ServerProperties.Config.VIEW_DISTANCE);

        if (value < 5) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(ServerProperties.Config.VIEW_DISTANCE, click, value),
                    ChatColor.DARK_RED + "View distance",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too low - impact to player experience.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value > 10) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(ServerProperties.Config.VIEW_DISTANCE, click, value),
                    ChatColor.DARK_RED + "View distance",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - large impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value > 8) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(ServerProperties.Config.VIEW_DISTANCE, click, value),
                    ChatColor.DARK_RED + "View distance",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "High - moderate impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(ServerProperties.Config.VIEW_DISTANCE, click, value),
                    ChatColor.DARK_GREEN + "View distance",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void simulationDistance(char id) {
        int viewDistance = this.properties.getInt(ServerProperties.Config.VIEW_DISTANCE);
        int simuDistance = this.properties.getInt(ServerProperties.Config.SIMULATION_DISTANCE);

        if (simuDistance < 5) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(ServerProperties.Config.SIMULATION_DISTANCE, click, simuDistance),
                    ChatColor.DARK_RED + "Simulation Distance",
                    ChatColor.RED + String.valueOf(simuDistance),
                    ChatColor.RED + "Too low - impact to player experience.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (simuDistance <= viewDistance) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(ServerProperties.Config.SIMULATION_DISTANCE, click, simuDistance),
                    ChatColor.DARK_GREEN + "Simulation Distance",
                    ChatColor.GREEN + String.valueOf(simuDistance),
                    ChatColor.GREEN + "Lower or equal to view distance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(ServerProperties.Config.SIMULATION_DISTANCE, click, simuDistance),
                    ChatColor.DARK_RED + "Simulation Distance",
                    ChatColor.RED + String.valueOf(simuDistance),
                    ChatColor.RED + "Higher than view distance - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void syncChunkWrites(char id) {
        String value = this.properties.getString(ServerProperties.Config.SYNC_CHUNK_WRITES);

        if (Objects.equals(value, "false")) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        this.properties.set(ServerProperties.Config.SYNC_CHUNK_WRITES, "true");
                        this.show();
                        return true;
                    },
                    ChatColor.DARK_RED + "Sync Chunk Writes",
                    ChatColor.RED + "false",
                    ChatColor.RED + "False - impact to player experience.",
                    ChatColor.BLUE + "Click to toggle true/false"
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        this.properties.set(ServerProperties.Config.SYNC_CHUNK_WRITES, "false");
                        this.show();
                        return true;
                    },
                    ChatColor.DARK_GREEN + "Sync Chunk Writes",
                    ChatColor.GREEN + "true",
                    ChatColor.GREEN + "Ideal value.",
                    ChatColor.BLUE + "Click to toggle true/false"
            ));
        }
    }

    private boolean setInt(ServerProperties.Config key, GuiElement.Click click, int value) {
        click.getGui().close();
        if (click.getType() == ClickType.RIGHT) {
            this.properties.set(key, String.valueOf(value + 1));
        } else if (click.getType() == ClickType.LEFT) {
            this.properties.set(key, String.valueOf(value - 1));
        }
        this.show();
        return true;
    }
}

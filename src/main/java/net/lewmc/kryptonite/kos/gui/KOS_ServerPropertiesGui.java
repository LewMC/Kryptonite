package net.lewmc.kryptonite.kos.gui;

import de.themoep.inventorygui.InventoryGui;
import de.themoep.inventorygui.StaticGuiElement;
import net.lewmc.kryptonite.Kryptonite;
import net.lewmc.kryptonite.kos.config.ServerProperties;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
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

        this.properties = new ServerProperties();
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
                    click.getWhoClicked().sendMessage(ChatColor.YELLOW + "https://wiki.lewmc.net/kr-kos-guide.html#manual");
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
                " a b c d ",
                "         ",
                "   h e   "
        };
    }

    private void networkCompressionThreshold(char id) {
        int value = this.properties.getInt(ServerProperties.Config.NETWORK_COMPRESSION_THRESHOLD);
        if (value == 256) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        return true;
                    },
                    ChatColor.DARK_GREEN + "Network Compression Threshold",
                    ChatColor.GREEN + "256",
                    ChatColor.GREEN + "Ideal value"
            ));
        } else if (value < 256) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        return true;
                    },
                    ChatColor.DARK_RED + "Network Compression Threshold",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too low."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        return true;
                    },
                    ChatColor.DARK_RED + "Network Compression Threshold",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high."
            ));
        }
    }

    private void viewDistance(char id) {
        int value = this.properties.getInt(ServerProperties.Config.VIEW_DISTANCE);

        if (value < 5) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        return true;
                    },
                    ChatColor.DARK_RED + "View distance",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too low - impact to player experience."
            ));
        } else if (value > 10) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        return true;
                    },
                    ChatColor.DARK_RED + "View distance",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - large impact to performance."
            ));
        } else if (value > 8) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        return true;
                    },
                    ChatColor.DARK_RED + "View distance",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "High - moderate impact to performance."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        return true;
                    },
                    ChatColor.DARK_GREEN + "View distance",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range."
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
                    click -> {
                        click.getGui().close();
                        return true;
                    },
                    ChatColor.DARK_RED + "Simulation Distance",
                    ChatColor.RED + String.valueOf(simuDistance),
                    ChatColor.RED + "Too low - impact to player experience."
            ));
        } else if (simuDistance <= viewDistance) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        return true;
                    },
                    ChatColor.DARK_GREEN + "Simulation Distance",
                    ChatColor.GREEN + String.valueOf(simuDistance),
                    ChatColor.GREEN + "Lower or equal to view distance."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        return true;
                    },
                    ChatColor.DARK_RED + "Simulation Distance",
                    ChatColor.RED + String.valueOf(simuDistance),
                    ChatColor.RED + "Higher than view distance - impact to performance."
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
                        return true;
                    },
                    ChatColor.DARK_RED + "Sync Chunk Writes",
                    ChatColor.RED + "false",
                    ChatColor.RED + "False - impact to player experience."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        return true;
                    },
                    ChatColor.DARK_GREEN + "Simulation Distance",
                    ChatColor.GREEN + "true",
                    ChatColor.GREEN + "Ideal value."
            ));
        }
    }
}

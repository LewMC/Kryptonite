package net.lewmc.kryptonite.kos.gui;

import de.themoep.inventorygui.GuiElement;
import de.themoep.inventorygui.InventoryGui;
import de.themoep.inventorygui.StaticGuiElement;
import net.lewmc.kryptonite.Kryptonite;
import net.lewmc.kryptonite.config.LeafConfig;
import net.lewmc.kryptonite.config.MinecraftConfig;
import net.lewmc.kryptonite.utils.config.*;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * KOS Config Item GUI
 */
public class KOS_ConfigItemGui {
    private final Kryptonite plugin;
    private final CommandSender user;
    private final ConfigCollection config;
    private InventoryGui gui;

    /**
     * Constructor for the KOS Config Item GUI
     * @param plugin Kryptonite - Reference to the main plugin class.
     * @param user CommandSender - The user who sent the command.
     * @param type Kryptonite.ConfigurationOptions - The type of GUI to display.
     */
    public KOS_ConfigItemGui(Kryptonite plugin, CommandSender user, Kryptonite.ConfigurationOptions type) {
        this.plugin = plugin;
        this.user = user;

        if (type == Kryptonite.ConfigurationOptions.MINECRAFT) {
            this.config = new MinecraftConfig(plugin);
        } else if (type == Kryptonite.ConfigurationOptions.LEAF) {
            this.config = new LeafConfig(plugin);
        } else {
            this.config = null;
        }
    }

    /**
     * Shows the KOS Server Properties GUI
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
        if (this.config != null) {
            int index = 0;
            for (Map.Entry<String, GenericConfigItem> entry : this.config.values.entrySet()) {
                GenericConfigItem<?> config = entry.getValue();
                char id = (char) ('a' + index);

                ItemStack display = buildDisplayItem(config);

                this.gui.addElement(new StaticGuiElement(
                        id,
                        display,
                        1,
                        click -> {
                            handleClick(config, click);
                            return true;
                        }
                ));

                index++;
            }

            KOS_GuiConstants consts = new KOS_GuiConstants(this.plugin, this.gui);
            consts.addConstants();
        }
    }

    private ItemStack buildDisplayItem(GenericConfigItem<?> config) {
        boolean ideal = config.isIdeal();
        boolean dependencyIsEnabled = config.dependencyIsEnabled();

        Material material;

        if (config.isValid()) {
            material = (ideal) ? Material.LIME_CONCRETE : Material.RED_CONCRETE;
        } else {
            material = Material.BLACK_CONCRETE;
        }

        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();

        String idealValue = config.getIdealValue();
        if (config.isValid()) {
            meta.setDisplayName(((ideal) ? ChatColor.DARK_GREEN : ChatColor.DARK_RED) + config.getName());
            lore.add(((ideal) ? ChatColor.DARK_GREEN : ChatColor.DARK_RED) + String.valueOf(config.getValue()));
            if (config.getDescription() != null) {
                lore.addAll(config.getDescription().stream()
                        .map(line -> ((ideal) ? ChatColor.GREEN : ChatColor.RED) + line)
                        .toList());
            }
            lore.add(ChatColor.WHITE + "Ideal value: " + ((idealValue != null) ? idealValue : "Any"));
        } else {
            meta.setDisplayName(ChatColor.DARK_GRAY + config.getName());
            lore.add(ChatColor.GRAY + String.valueOf(config.getValue()));
            if (config.getDescription() != null) {
                lore.addAll(config.getDescription().stream()
                        .map(line -> ChatColor.GRAY + line)
                        .toList());
            }
            if (!config.dependencyIsEnabled()) {
                lore.add(ChatColor.WHITE + "This feature requires another feature that");
                lore.add(ChatColor.WHITE + "is currently disabled. Please enable it to");
                lore.add(ChatColor.WHITE + "setup this item.");
            } else {
                lore.add(ChatColor.WHITE + "Invalid value.");
                lore.add(ChatColor.WHITE + "Ideal value: " + ((idealValue != null) ? idealValue : "Any"));
            }
        }

        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }

    private void handleClick(GenericConfigItem<?> config, GuiElement.Click click) {
        click.getGui().close();

        if (config instanceof BooleanConfigItem boolItem) {
            if (boolItem.willBeValid(!boolItem.getValue())) {
                boolItem.setValue(!boolItem.getValue());
            }
        } else if (config instanceof IntegerConfigItem intItem) {
            int current = intItem.getValue();
            if (click.getType() == ClickType.RIGHT) {
                if (intItem.willBeValid(intItem.getValue() + 1)) {
                    intItem.setValue(current + 1);
                }
            } else if (click.getType() == ClickType.SHIFT_RIGHT) {
                if (intItem.willBeValid(intItem.getValue() + 10)) {
                    intItem.setValue(current + 10);
                }
            } else if (click.getType() == ClickType.LEFT) {
                if (intItem.willBeValid(intItem.getValue() - 1)) {
                    intItem.setValue(current - 1);
                }
            } else if (click.getType() == ClickType.SHIFT_LEFT) {
                if (intItem.willBeValid(intItem.getValue() - 10)) {
                    intItem.setValue(current - 10);
                }
            }
        } else if (config instanceof DoubleConfigItem doubleItem) {
            Double current = doubleItem.getValue();
            if (click.getType() == ClickType.RIGHT) {
                if (doubleItem.willBeValid(doubleItem.getValue() + 1.0)) {
                    doubleItem.setValue(current + 1.0);
                }
            } else if (click.getType() == ClickType.SHIFT_RIGHT) {
                if (doubleItem.willBeValid(doubleItem.getValue() + 10.0)) {
                    doubleItem.setValue(current + 10.0);
                }
            } else if (click.getType() == ClickType.LEFT) {
                if (doubleItem.willBeValid(doubleItem.getValue() - 1.0)) {
                    doubleItem.setValue(current - 1.0);
                }
            } else if (click.getType() == ClickType.SHIFT_LEFT) {
                if (doubleItem.willBeValid(doubleItem.getValue() - 10.0)) {
                    doubleItem.setValue(current - 10.0);
                }
            }
        } else if (config instanceof StringConfigItem stringItem) {
            // TODO: Implement strings.
        }

        this.show();
    }



    /**
     * Determines where the elements should be positioned.
     * @return String[] - The position of the elements.
     */
    private String[] getElements() {
        if (this.config != null && this.config.values != null) {
            int size = this.config.values.size();
            int rows = (int) Math.ceil(size / 9.0);

            // +1 row for the constant footer row
            String[] layout = new String[rows + 1];

            int index = 0;
            for (int r = 0; r < rows; r++) {
                StringBuilder row = new StringBuilder("         ");
                for (int c = 0; c < 9 && index < size; c++) {
                    char id = (char) ('a' + index);
                    row.setCharAt(c, id);
                    index++;
                }
                layout[r] = row.toString();
            }

            layout[rows] = "  t u v  ";

            return layout;
        } else {
            return new String[] {"","","  t u v  "};
        }
    }

}

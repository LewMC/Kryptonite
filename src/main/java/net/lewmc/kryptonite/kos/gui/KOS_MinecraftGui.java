package net.lewmc.kryptonite.kos.gui;

import de.themoep.inventorygui.GuiElement;
import de.themoep.inventorygui.InventoryGui;
import de.themoep.inventorygui.StaticGuiElement;
import net.lewmc.kryptonite.Kryptonite;
import net.lewmc.kryptonite.config.Minecraft;
import net.lewmc.kryptonite.utils.config.BooleanConfigItem;
import net.lewmc.kryptonite.utils.config.GenericConfigItem;
import net.lewmc.kryptonite.utils.config.IntegerConfigItem;
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
 * KOS Minecraft (server.properties) GUI
 */
public class KOS_MinecraftGui {
    private final Kryptonite plugin;
    private final CommandSender user;
    private final Minecraft config;
    private InventoryGui gui;

    /**
     * Constructor for the KOS Server Properties GUI
     * @param plugin Kryptonite - Reference to the main plugin class.
     * @param user CommandSender - The user who sent the command.
     */
    public KOS_MinecraftGui(Kryptonite plugin, CommandSender user) {
        this.plugin = plugin;
        this.user = user;

        this.config = new Minecraft(plugin);
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

        // Keep your constant controls
        KOS_GuiConstants consts = new KOS_GuiConstants(this.plugin, this.gui);
        consts.addConstants();
    }

    private ItemStack buildDisplayItem(GenericConfigItem<?> config) {
        boolean ideal = config.isIdeal();
        Material material = ideal ? Material.LIME_CONCRETE : Material.RED_CONCRETE;

        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName((ideal ? ChatColor.DARK_GREEN : ChatColor.DARK_RED) + config.getName());

        List<String> lore = new ArrayList<>();
        lore.add((ideal ? ChatColor.GREEN : ChatColor.RED) + "Current: " + config.getCurrentValue());

        String idealValue = config.getIdealValue();
        if (idealValue != null) {
            lore.add(ChatColor.GRAY + "Ideal: " + idealValue);
        } else {
            lore.add(ChatColor.GRAY + "Any value is fine");
        }

        if (config.getDescription() != null) {
            lore.addAll(config.getDescription().stream()
                    .map(line -> ChatColor.BLUE + line)
                    .toList());
        }

        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }

    private void handleClick(GenericConfigItem<?> config, GuiElement.Click click) {
        click.getGui().close();

        if (config instanceof BooleanConfigItem boolItem) {
            boolItem.setCurrentValue(!boolItem.getCurrentValue());
        } else if (config instanceof IntegerConfigItem intItem) {
            int current = intItem.getCurrentValue();
            if (click.getType() == ClickType.RIGHT) {
                intItem.setCurrentValue(current + 1);
            } else if (click.getType() == ClickType.SHIFT_RIGHT) {
                intItem.setCurrentValue(current + 10);
            } else if (click.getType() == ClickType.LEFT && current != 0) {
                intItem.setCurrentValue(current - 1);
            } else if (click.getType() == ClickType.SHIFT_LEFT && current >= 10) {
                intItem.setCurrentValue(current - 10);
            }
        }
        this.show();
    }



    /**
     * Determines where the elements should be positioned.
     * @return String[] - The position of the elements.
     */
    private String[] getElements() {
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
    }

}

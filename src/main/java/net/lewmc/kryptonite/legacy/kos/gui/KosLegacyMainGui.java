package net.lewmc.kryptonite.legacy.kos.gui;

import de.themoep.inventorygui.InventoryGui;
import de.themoep.inventorygui.StaticGuiElement;
import net.lewmc.kryptonite.Kryptonite;
import net.lewmc.kryptonite.utils.ConfigurationUtil;
import net.lewmc.kryptonite.utils.MessageUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.io.File;

/**
 * The main menu GUI for KOS.
 * @deprecated
 */
@Deprecated
public class KosLegacyMainGui {
    private final CommandSender commandSender;
    private final Kryptonite plugin;
    private InventoryGui gui;

    /**
     * Constructor for the KosMainGui class.
     * @param plugin Kryptonite - Reference to the main plugin class.
     * @param commandSender CommandSender - the player who sent the command.
     */
    public KosLegacyMainGui(Kryptonite plugin, CommandSender commandSender) {
        this.plugin = plugin;
        this.commandSender = commandSender;
    }

    /**
     * Displays the GUI to the user.
     */
    public void show() {
        InventoryHolder holder = this.commandSender.getServer().getPlayer(this.commandSender.getName());
        char[] chars = this.addElements(false);

        this.gui = new InventoryGui(this.plugin, holder, "KOS - Select a Profile", this.getElements(chars));

        this.addElements(true);

        this.gui.setFiller(new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1));

        this.gui.build();
        this.gui.show((Player) this.commandSender);
    }

    /**
     * Adds pre-programmed elements to the GUI
     */
    private char[] addElements(boolean addToGui) {
        File dir = new File(this.plugin.getDataFolder() + "/profiles");
        File[] directoryListing = dir.listFiles();
        char firstChar = 'a';
        char[] chars = new char[directoryListing.length];
        if (directoryListing != null) {
            int index = 0;
            for (File child : directoryListing) {
                if (child.getName().contains(".kos")) {
                    ConfigurationUtil cfg = new ConfigurationUtil(this.plugin, commandSender);
                    YamlConfiguration item = cfg.load(this.plugin.getDataFolder() + "/profiles/"+child.getName());
                    chars[index] = firstChar;
                    if (addToGui) {
                        this.gui.addElement(new StaticGuiElement(firstChar,
                                new ItemStack(Material.OAK_SIGN),
                                1,
                                click -> {
                                    click.getGui().close();
                                    KosLegacyPregeneratedGui kpg = new KosLegacyPregeneratedGui(this.plugin, this.commandSender, child.getName().replace(".kos", ""));
                                    kpg.show();
                                    return true;
                                },
                                child.getName().replace(".kos", ""),
                                item.getString("meta.description"),
                                "Created by " + item.getString("meta.author")
                        ));
                    }
                    index++;
                    firstChar++;
                }
            }
        } else {
            MessageUtil msg = new MessageUtil(this.commandSender);
            msg.Error("Unable to find any installed KOS profiles.");
        }

        if (addToGui) {
            this.gui.addElement(new StaticGuiElement('h',
                    new ItemStack(Material.PAPER),
                    1,
                    click -> {
                        click.getWhoClicked().sendMessage(ChatColor.YELLOW + "https://wiki.lewmc.net/kr-kryptonite-optimisation-system.html");
                        click.getGui().close();
                        return true;
                    },
                    "Need help?",
                    "Click here to get a link to our Wiki."
            ));
        }

        return chars;
    }

    /**
     * Determines where the elements should be positioned.
     * @return chars[] - The characters used.
     */
    private String[] getElements(char[] chars) {
        StringBuilder line1 = new StringBuilder("         ");
        StringBuilder line2 = new StringBuilder("         ");
        String line3 = "    h    ";

        int i = 0;
        for (; i < chars.length && i < 9; i++) {
            line1.setCharAt(i, chars[i]);
        }

        for (int j = 0; i < chars.length && j < 9; i++, j++) {
            line2.setCharAt(j, chars[i]);
        }

        return new String[]{
                line1.toString(),
                line2.toString(),
                line3
        };
    }
}

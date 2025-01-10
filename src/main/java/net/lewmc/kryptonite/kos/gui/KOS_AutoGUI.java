package net.lewmc.kryptonite.kos.gui;

import de.themoep.inventorygui.InventoryGui;
import de.themoep.inventorygui.StaticGuiElement;
import net.lewmc.kryptonite.Kryptonite;
import net.lewmc.kryptonite.kos.AutoKOS;
import net.lewmc.kryptonite.utils.ConfigurationUtil;
import net.lewmc.kryptonite.utils.LogUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.io.File;

/**
 * KOS Auto GUI
 */
public class KOS_AutoGUI {
    private final Kryptonite plugin;
    private final CommandSender user;
    private final net.lewmc.kryptonite.kos.AutoKOS AutoKOS;
    private InventoryGui gui;

    /**
     * Constructor for the KOS Auto GUI
     * @param plugin Kryptonite - Reference to the main plugin class.
     * @param user CommandSender - The user who sent the command.
     */
    public KOS_AutoGUI(Kryptonite plugin, CommandSender user) {
        this.plugin = plugin;
        this.user = user;
        this.AutoKOS = new AutoKOS(plugin, user);
    }

    /**
     * Shows the KOS Auto GUI
     */
    public void show() {
        InventoryHolder holder = this.user.getServer().getPlayer(this.user.getName());
        this.gui = new InventoryGui(this.plugin, holder, "KOS - Automatic", this.getElements());
        this.addElements();

        this.gui.build();
        this.gui.show((Player) this.user);
    }

    /**
     * Adds pre-programmed elements to the GUI
     */
    private void addElements() {
        LogUtil log = new LogUtil(this.plugin);

        File folder = new File(this.plugin.getDataFolder(), "profiles");

        char currentLetter = 'a';

        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();

            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && file.getName().toLowerCase().endsWith(".kos")) {
                        if (currentLetter != 'r') {
                            ConfigurationUtil cu = new ConfigurationUtil(this.plugin, this.user);
                            cu.load(file.getAbsolutePath());

                            if (file.getName().equals("YouHaveTrouble.kos") || file.getName().equals("FarmFriendly.kos")) {
                                this.gui.addElement(new StaticGuiElement(currentLetter,
                                        new ItemStack(Material.REPEATING_COMMAND_BLOCK),
                                        1,
                                        click -> {
                                            AutoKOS ak = new AutoKOS(this.plugin, this.user);
                                            if (this.plugin.getConfig().get("kos.world-is-pregenerated") == "2") {
                                                ak.run(true, file.getName());
                                            } else {
                                                ak.run(false, file.getName());
                                            }
                                            this.gui.close();
                                            return true;
                                        },
                                        ChatColor.DARK_PURPLE + "★ Official Profile ★",
                                        ChatColor.DARK_GREEN + file.getName(),
                                        ChatColor.GREEN + cu.getString("meta.description"),
                                        ChatColor.GREEN + "By " + cu.getString("meta.author")
                                ));
                                currentLetter++;
                            } else {
                                this.gui.addElement(new StaticGuiElement(currentLetter,
                                        new ItemStack(Material.CHAIN_COMMAND_BLOCK),
                                        1,
                                        click -> {
                                            AutoKOS ak = new AutoKOS(this.plugin, this.user);
                                            if (this.plugin.getConfig().get("kos.world-is-pregenerated") == "2") {
                                                ak.run(true, file.getName());
                                            } else {
                                                ak.run(false, file.getName());
                                            }
                                            this.gui.close();
                                            return true;
                                        },
                                        ChatColor.DARK_GREEN + file.getName(),
                                        ChatColor.GREEN + cu.getString("meta.description"),
                                        ChatColor.GREEN + "By " + cu.getString("meta.author")
                                ));
                                currentLetter++;
                            }
                        }
                    }
                }
            }
        } else {
            log.warn("The specified path is not a valid directory.");
        }

        KOS_GuiConstants consts = new KOS_GuiConstants(this.plugin, this.gui);
        consts.addConstants();
    }

    /**
     * Determines where the elements should be positioned.
     * @return String[] - The position of the elements.
     */
    private String[] getElements() {
        return new String[]{
                "abcdefghi",
                "jklmnopqr",
                "  w x y  "
        };
    }
}

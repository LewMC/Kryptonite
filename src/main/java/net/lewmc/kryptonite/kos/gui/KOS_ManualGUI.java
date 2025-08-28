package net.lewmc.kryptonite.kos.gui;

import de.themoep.inventorygui.InventoryGui;
import de.themoep.inventorygui.StaticGuiElement;
import net.lewmc.kryptonite.Kryptonite;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

/**
 * KOS Manual GUI
 */
public class KOS_ManualGUI {
    private final Kryptonite plugin;
    private final CommandSender user;
    private InventoryGui gui;

    /**
     * Constructor for the KOS Manual GUI
     * @param plugin Kryptonite - Reference to the main plugin class.
     * @param user CommandSender - The user who sent the command.
     */
    public KOS_ManualGUI(Kryptonite plugin, CommandSender user) {
        this.plugin = plugin;
        this.user = user;
    }

    /**
     * Shows the KOS Manual GUI
     */
    public void show() {
        InventoryHolder holder = this.user.getServer().getPlayer(this.user.getName());
        this.gui = new InventoryGui(this.plugin, holder, "KOS - Manual", this.getElements());
        this.addElements();

        this.gui.build();
        this.gui.show((Player) this.user);
    }

    /**
     * Adds pre-programmed elements to the GUI
     */
    private void addElements() {
        if (this.plugin.SupportedConfigurations.contains(Kryptonite.ConfigurationOptions.MINECRAFT)) {
            this.gui.addElement(new StaticGuiElement('p',
                    new ItemStack(Material.COMMAND_BLOCK_MINECART),
                    1,
                    click -> {
                        click.getGui().close();
                        new KOS_ConfigItemGui(this.plugin, this.user, Kryptonite.ConfigurationOptions.MINECRAFT).show();
                        return true;
                    },
                    ChatColor.BLUE + "Minecraft",
                    ChatColor.AQUA + "Manage Minecraft's configuration."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement('p',
                    new ItemStack(Material.BARRIER),
                    1,
                    click -> true,
                    ChatColor.DARK_RED + "Minecraft",
                    ChatColor.RED + "Manage Minecraft's configuration.",
                    ChatColor.RED + "Your server does not support this, but it",
                    ChatColor.RED + "should. Please contact LewMC for help at",
                    ChatColor.RED + "lewmc.net/help"
            ));
        }

        if (this.plugin.SupportedConfigurations.contains(Kryptonite.ConfigurationOptions.BUKKIT)) {
            this.gui.addElement(new StaticGuiElement('b',
                    new ItemStack(Material.BUCKET),
                    1,
                    click -> {
                        click.getGui().close();
                        KOS_BukkitGui bGui = new KOS_BukkitGui(this.plugin, this.user);
                        bGui.show();
                        return true;
                    },
                    ChatColor.BLUE + "Bukkit",
                    ChatColor.AQUA + "Manage the Bukkit configuration."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement('b',
                    new ItemStack(Material.BARRIER),
                    1,
                    click -> true,
                    ChatColor.DARK_RED + "Bukkit",
                    ChatColor.RED + "Manage the Bukkit configuration.",
                    ChatColor.RED + "Your server does not support this."
            ));
        }

        if (this.plugin.SupportedConfigurations.contains(Kryptonite.ConfigurationOptions.SPIGOT)) {
            this.gui.addElement(new StaticGuiElement('s',
                    new ItemStack(Material.WATER_BUCKET),
                    1,
                    click -> {
                        click.getGui().close();
                        KOS_SpigotGui_1 spigGui = new KOS_SpigotGui_1(this.plugin, this.user);
                        spigGui.show();
                        return true;
                    },
                    ChatColor.BLUE + "Spigot",
                    ChatColor.AQUA + "Manage the Spigot configuration."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement('s',
                    new ItemStack(Material.BARRIER),
                    1,
                    click -> true,
                    ChatColor.DARK_RED + "Spigot",
                    ChatColor.RED + "Manage the Spigot configuration.",
                    ChatColor.RED + "Your server does not support this."
            ));
        }

        if (this.plugin.SupportedConfigurations.contains(Kryptonite.ConfigurationOptions.PAPER_GLOBAL)) {
            this.gui.addElement(new StaticGuiElement('g',
                    new ItemStack(Material.MAP),
                    1,
                    click -> {
                        click.getWhoClicked().sendMessage(ChatColor.DARK_RED + "There are no options that can be changed for this configuration file.");
                        return true;
                    },
                    ChatColor.BLUE + "Paper Global",
                    ChatColor.AQUA + "Manage the Paper Global configuration."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement('g',
                    new ItemStack(Material.BARRIER),
                    1,
                    click -> true,
                    ChatColor.DARK_RED + "Paper Global",
                    ChatColor.RED + "Manage the Paper Global configuration.",
                    ChatColor.RED + "Your server does not support this."
            ));
        }

        if (this.plugin.SupportedConfigurations.contains(Kryptonite.ConfigurationOptions.PAPER_WORLD)) {
            this.gui.addElement(new StaticGuiElement('o',
                    new ItemStack(Material.FILLED_MAP),
                    1,
                    click -> {
                        click.getGui().close();
                        KOS_PaperWorld_1 paperWorldGui = new KOS_PaperWorld_1(this.plugin, this.user);
                        paperWorldGui.show();
                        return true;
                    },
                    ChatColor.BLUE + "Paper World",
                    ChatColor.AQUA + "Manage the Paper World configuration."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement('o',
                    new ItemStack(Material.BARRIER),
                    1,
                    click -> true,
                    ChatColor.DARK_RED + "Paper World",
                    ChatColor.RED + "Manage the Paper World configuration.",
                    ChatColor.RED + "Your server does not support this."
            ));
        }

        if (this.plugin.SupportedConfigurations.contains(Kryptonite.ConfigurationOptions.PURPUR)) {
            this.gui.addElement(new StaticGuiElement('u',
                    new ItemStack(Material.PURPUR_BLOCK),
                    1,
                    click -> {
                        click.getGui().close();
                        KOS_PurpurGui purpurGui = new KOS_PurpurGui(this.plugin, this.user);
                        purpurGui.show();
                        return true;
                    },
                    ChatColor.BLUE + "Purpur",
                    ChatColor.AQUA + "Manage the Purpur configuration."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement('u',
                    new ItemStack(Material.BARRIER),
                    1,
                    click -> true,
                    ChatColor.DARK_RED + "Purpur",
                    ChatColor.RED + "Manage the Purpur configuration.",
                    ChatColor.RED + "Your server does not support this."
            ));
        }

        if (this.plugin.SupportedConfigurations.contains(Kryptonite.ConfigurationOptions.PUFFERFISH)) {
            this.gui.addElement(new StaticGuiElement('f',
                    new ItemStack(Material.PUFFERFISH),
                    1,
                    click -> {
                        click.getGui().close();
                        KOS_PufferfishGui pufferfishGui = new KOS_PufferfishGui(this.plugin, this.user);
                        pufferfishGui.show();
                        return true;
                    },
                    ChatColor.BLUE + "Pufferfish",
                    ChatColor.AQUA + "Manage the Pufferfish configuration."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement('f',
                    new ItemStack(Material.BARRIER),
                    1,
                    click -> true,
                    ChatColor.DARK_RED + "Pufferfish",
                    ChatColor.RED + "Manage the Pufferfish configuration.",
                    ChatColor.RED + "Your server does not support this."
            ));
        }

        if (this.plugin.SupportedConfigurations.contains(Kryptonite.ConfigurationOptions.LEAF)) {
            this.gui.addElement(new StaticGuiElement('l',
                    new ItemStack(Material.OAK_LEAVES),
                    1,
                    click -> {
                        click.getGui().close();
                        new KOS_ConfigItemGui(this.plugin, this.user, Kryptonite.ConfigurationOptions.LEAF).show();
                        return true;
                    },
                    ChatColor.BLUE + "Leaf",
                    ChatColor.AQUA + "Manage the Leaf configuration."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement('l',
                    new ItemStack(Material.BARRIER),
                    1,
                    click -> true,
                    ChatColor.DARK_RED + "Leaf",
                    ChatColor.RED + "Manage the Leaf configuration.",
                    ChatColor.RED + "Your server does not support this."
            ));
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
                " p b s g ",
                " o u f l ",
                "  w x y  "
        };
    }
}

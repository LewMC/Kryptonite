package net.lewmc.kryptonite.edb.gui;

import de.themoep.inventorygui.InventoryGui;
import de.themoep.inventorygui.StaticGuiElement;
import net.lewmc.kryptonite.Kryptonite;
import net.lewmc.kryptonite.edb.Check;
import net.lewmc.kryptonite.kos.gui.KOS_GuiConstants;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

/**
 * EDB Main GUI
 */
public class EDB_MainGui {
    private final Kryptonite plugin;
    private final CommandSender user;
    private InventoryGui gui;
    private net.lewmc.kryptonite.edb.Check Check;

    /**
     * Constructor for the EDB Main GUI
     * @param plugin Kryptonite - Reference to the main plugin class.
     * @param user CommandSender - The user who sent the command.
     */
    public EDB_MainGui(Kryptonite plugin, CommandSender user) {
        this.plugin = plugin;
        this.user = user;
    }

    /**
     * Shows the EDB Main GUI
     */
    public void show() {
        this.Check = new Check(this.plugin, this.user);

        InventoryHolder holder = this.user.getServer().getPlayer(this.user.getName());
        this.gui = new InventoryGui(this.plugin, holder, "Exploit Database", this.getElements());
        this.addElements();

        this.gui.build();
        this.gui.show((Player) this.user);
    }

    /**
     * Adds pre-programmed elements to the GUI
     */
    private void addElements() {
        if (this.Check.edb1()) {
            this.gui.addElement(new StaticGuiElement('a',
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        return true;
                    },
                    ChatColor.DARK_GREEN + "EDB-1: Armour stand lag machines",
                    ChatColor.GREEN + "Passed - Your server is safe!"
            ));
        } else {
            this.gui.addElement(new StaticGuiElement('a',
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        return true;
                    },
                    ChatColor.DARK_RED + "EDB-1: Armour stand lag machines",
                    ChatColor.RED + "Failed - Your server is exploitable!"
            ));
        }

        if (this.Check.edb2()) {
            this.gui.addElement(new StaticGuiElement('b',
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        return true;
                    },
                    ChatColor.DARK_GREEN + "EDB-2: Book Exploits",
                    ChatColor.GREEN + "Passed - Your server is safe!"
            ));
        } else {
            this.gui.addElement(new StaticGuiElement('b',
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        return true;
                    },
                    ChatColor.DARK_RED + "EDB-2: Book Exploits",
                    ChatColor.RED + "Failed - Your server is exploitable!"
            ));
        }

        if (this.Check.edb3()) {
            this.gui.addElement(new StaticGuiElement('c',
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        return true;
                    },
                    ChatColor.DARK_GREEN + "EDB-3: Collision Lag Machines",
                    ChatColor.GREEN + "Passed - Your server is safe!"
            ));
        } else {
            this.gui.addElement(new StaticGuiElement('c',
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        return true;
                    },
                    ChatColor.DARK_RED + "EDB-3: Collision Lag Machines",
                    ChatColor.RED + "Failed - Your server is exploitable!"
            ));
        }

        if (this.Check.edb4()) {
            this.gui.addElement(new StaticGuiElement('d',
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        return true;
                    },
                    ChatColor.DARK_GREEN + "EDB-4: Command suggestion packet spam attack",
                    ChatColor.GREEN + "Passed - Your server is safe!"
            ));
        } else {
            this.gui.addElement(new StaticGuiElement('d',
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        return true;
                    },
                    ChatColor.DARK_RED + "EDB-4: Command suggestion packet spam attack",
                    ChatColor.RED + "Failed - Your server is exploitable!"
            ));
        }

        if (this.Check.edb5()) {
            this.gui.addElement(new StaticGuiElement('e',
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        return true;
                    },
                    ChatColor.DARK_GREEN + "EDB-5: Command spam attack",
                    ChatColor.GREEN + "Passed - Your server is safe!"
            ));
        } else {
            this.gui.addElement(new StaticGuiElement('e',
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        return true;
                    },
                    ChatColor.DARK_RED + "EDB-5: Command spam attack",
                    ChatColor.RED + "Failed - Your server is exploitable!"
            ));
        }

        if (this.Check.edb6()) {
            this.gui.addElement(new StaticGuiElement('f',
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        return true;
                    },
                    ChatColor.DARK_GREEN + "EDB-6: Join spam attack",
                    ChatColor.GREEN + "Passed - Your server is safe!"
            ));
        } else {
            this.gui.addElement(new StaticGuiElement('f',
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        return true;
                    },
                    ChatColor.DARK_RED + "EDB-6: Join spam attack",
                    ChatColor.RED + "Failed - Your server is exploitable!"
            ));
        }

        if (this.Check.edb7()) {
            this.gui.addElement(new StaticGuiElement('g',
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        return true;
                    },
                    ChatColor.DARK_GREEN + "EDB-7: Neighbor update lag machines",
                    ChatColor.GREEN + "Passed - Your server is safe!"
            ));
        } else {
            this.gui.addElement(new StaticGuiElement('g',
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        return true;
                    },
                    ChatColor.DARK_RED + "EDB-7: Neighbor update lag machines",
                    ChatColor.RED + "Failed - Your server is exploitable!"
            ));
        }

        if (this.Check.edb8()) {
            this.gui.addElement(new StaticGuiElement('h',
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        return true;
                    },
                    ChatColor.DARK_GREEN + "EDB-8: Projectile suspension",
                    ChatColor.GREEN + "Passed - Your server is safe!"
            ));
        } else {
            this.gui.addElement(new StaticGuiElement('h',
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        return true;
                    },
                    ChatColor.DARK_RED + "EDB-8: Projectile suspension",
                    ChatColor.RED + "Failed - Your server is exploitable!"
            ));
        }

        if (this.Check.edb9()) {
            this.gui.addElement(new StaticGuiElement('i',
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        return true;
                    },
                    ChatColor.DARK_GREEN + "EDB-9: Recipe book spam attack",
                    ChatColor.GREEN + "Passed - Your server is safe!"
            ));
        } else {
            this.gui.addElement(new StaticGuiElement('i',
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        return true;
                    },
                    ChatColor.DARK_RED + "EDB-9: Recipe book spam attack",
                    ChatColor.RED + "Failed - Your server is exploitable!"
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
                "abcdefghi",
                "jk       ",
                "  w x y  "
        };
    }
}

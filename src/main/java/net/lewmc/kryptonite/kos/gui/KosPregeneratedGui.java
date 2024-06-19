package net.lewmc.kryptonite.kos.gui;

import de.themoep.inventorygui.InventoryGui;
import de.themoep.inventorygui.StaticGuiElement;
import net.lewmc.kryptonite.Kryptonite;
import net.lewmc.kryptonite.kos.KOS;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

/**
 * The "have you pregenerated?" menu GUI for KOS.
 */
public class KosPregeneratedGui {
    private final CommandSender commandSender;
    private final Kryptonite plugin;
    private InventoryGui gui;
    private String profile;

    /**
     * Constructor for the KosPregeneratedGui class.
     * @param plugin Kryptonite - Reference to the main plugin class.
     * @param commandSender CommandSender - the player who sent the command.
     */
    public KosPregeneratedGui(Kryptonite plugin, CommandSender commandSender, String profile) {
        this.plugin = plugin;
        this.commandSender = commandSender;
        this.profile = profile;
    }

    /**
     * Displays the GUI to the user.
     */
    public void show() {
        InventoryHolder holder = this.commandSender.getServer().getPlayer(this.commandSender.getName());
        this.gui = new InventoryGui(this.plugin, holder, "KOS - "+this.profile, this.getElements());

        this.gui.setFiller(new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1));
        this.addElements();

        this.gui.build();
        this.gui.show((Player) this.commandSender);
    }

    /**
     * Adds pre-programmed elements to the GUI
     */
    private void addElements() {
        this.gui.addElement(new StaticGuiElement('y',
                new ItemStack(Material.GREEN_CONCRETE),
                1,
                click -> {
                    click.getGui().close();
                    KOS kos = new KOS(this.commandSender, this.plugin, this.profile);
                    kos.runDefault(true);
                    return true;
                },
                "World IS pre-generated.",
                "Click here if you've used a plugin such",
                "as chunky to pre-generate your world's",
                "chunks."
        ));

        this.gui.addElement(new StaticGuiElement('n',
                new ItemStack(Material.RED_CONCRETE),
                1,
                click -> {
                    click.getGui().close();
                    KOS kos = new KOS(this.commandSender, this.plugin, this.profile);
                    kos.runDefault(false);
                    return true;
                },
                "World IS NOT pre-genereated.",
                "Click here if you've used a plugin such",
                "as chunky to pre-generate your world's",
                "chunks."
        ));

        this.gui.addElement(new StaticGuiElement('h',
                new ItemStack(Material.COMMAND_BLOCK),
                1,
                click -> {
                    click.getWhoClicked().sendMessage(ChatColor.YELLOW + "https://wiki.lewmc.net/kr-kryptonite-optimisation-system.html");
                    click.getGui().close();
                    return true;
                },
                "Help",
                "Not sure what to do? Click here for a link to",
                "our Wiki."
        ));
    }

    /**
     * Determines where the elements should be positioned.
     * @return String[] - The position of the elements.
     */
    private String[] getElements() {

        return new String[]{
                "   y n   ",
                "         ",
                "    h    "
        };
    }
}

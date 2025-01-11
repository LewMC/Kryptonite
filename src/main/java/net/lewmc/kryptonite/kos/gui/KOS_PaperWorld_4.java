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
 * KOS Paper World GUI (Page 4/5)
 */
public class KOS_PaperWorld_4 {
    private final Kryptonite plugin;
    private final CommandSender user;
    private final PaperWorld paperWorld;
    private final boolean isAltItemDespawnRateEnabled;
    private InventoryGui gui;

    /**
     * Constructor for the KOS Paper World GUI (Page 4/5)
     * @param plugin Kryptonite - Reference to the main plugin class.
     * @param user CommandSender - The user who sent the command.
     */
    public KOS_PaperWorld_4(Kryptonite plugin, CommandSender user) {
        this.plugin = plugin;
        this.user = user;
        this.paperWorld = new PaperWorld(plugin, user);

        this.isAltItemDespawnRateEnabled = this.paperWorld.getBoolean(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_ENABLED);
    }

    /**
     * Shows the KOS Paper World GUI (Page 4/5)
     */
    public void show() {
        InventoryHolder holder = this.user.getServer().getPlayer(this.user.getName());
        this.gui = new InventoryGui(this.plugin, holder, "KOS - Paper World Configuration (4/5)", this.getElements());
        this.addElements();

        this.gui.build();
        this.gui.show((Player) this.user);
    }

    /**
     * Adds pre-programmed elements to the GUI
     */
    private void addElements() {
        this.altItemDespawnRateDirt('a');
        this.altItemDespawnRateShortGrass('b');
        this.altItemDespawnRatePumpkin('c');
        this.altItemDespawnRateMelonSlice('d');
        this.altItemDespawnRateKelp('e');
        this.altItemDespawnRateBamboo('f');
        this.altItemDespawnRateSugarCane('g');
        this.altItemDespawnRateTwistingVines('h');
        this.altItemDespawnRateWeepingVines('i');
        this.altItemDespawnRateOakLeaves('j');
        this.altItemDespawnRateBirchLeaves('k');
        this.altItemDespawnRateSpruceLeaves('l');
        this.altItemDespawnRateJungleLeaves('m');
        this.altItemDespawnRateAcaciaLeaves('n');
        this.altItemDespawnRateDarkOakLeaves('o');
        this.altItemDespawnRateMangroveLeaves('p');
        this.altItemDespawnRateCactus('q');
        this.altItemDespawnRateDiorite('r');

        KOS_GuiConstants consts = new KOS_GuiConstants(this.plugin, this.gui);
        consts.addConstants();

        this.gui.addElement(new StaticGuiElement('v',
                    new ItemStack(Material.OAK_SIGN),
                1,
                click -> {
                    click.getGui().close();
                    KOS_PaperWorld_3 prevGui = new KOS_PaperWorld_3(this.plugin, this.user);
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
                    KOS_PaperWorld_5 nextGui = new KOS_PaperWorld_5(this.plugin, this.user);
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

    private void altItemDespawnRateDirt(char id) {
        int value = this.paperWorld.getInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_DIRT);
        if (!isAltItemDespawnRateEnabled) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.WHITE_CONCRETE),
                    1,
                    click -> true,
                    ChatColor.WHITE + "Alt Item Despawn Rate - Dirt",
                    ChatColor.GRAY + "The Alt Item Despawn Rate feature is disabled.",
                    ChatColor.GRAY + "Please enable it to modify this value."
            ));
        } else if (value >= 300 && value <= 600) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_DIRT, click, value, false),
                    ChatColor.DARK_GREEN + "Alt Item Despawn Rate - Dirt",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value > 600) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_DIRT, click, value, false),
                    ChatColor.DARK_RED + "Alt Item Despawn Rate - Dirt",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_DIRT, click, value, false),
                    ChatColor.GOLD + "Alt Item Despawn Rate - Dirt",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.YELLOW + "Items may despawn too quickly.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void altItemDespawnRateShortGrass(char id) {
        int value = this.paperWorld.getInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_SHORT_GRASS);
        if (!isAltItemDespawnRateEnabled) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.WHITE_CONCRETE),
                    1,
                    click -> true,
                    ChatColor.WHITE + "Alt Item Despawn Rate - Short Grass",
                    ChatColor.GRAY + "The Alt Item Despawn Rate feature is disabled.",
                    ChatColor.GRAY + "Please enable it to modify this value."
            ));
        } else if (value >= 300 && value <= 600) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_SHORT_GRASS, click, value, false),
                    ChatColor.DARK_GREEN + "Alt Item Despawn Rate - Short Grass",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value > 600) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_SHORT_GRASS, click, value, false),
                    ChatColor.DARK_RED + "Alt Item Despawn Rate - Short Grass",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_SHORT_GRASS, click, value, false),
                    ChatColor.GOLD + "Alt Item Despawn Rate - Short Grass",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.YELLOW + "Items may despawn too quickly.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void altItemDespawnRatePumpkin(char id) {
        int value = this.paperWorld.getInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_PUMPKIN);
        if (!isAltItemDespawnRateEnabled) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.WHITE_CONCRETE),
                    1,
                    click -> true,
                    ChatColor.WHITE + "Alt Item Despawn Rate - Pumpkin",
                    ChatColor.GRAY + "The Alt Item Despawn Rate feature is disabled.",
                    ChatColor.GRAY + "Please enable it to modify this value."
            ));
        } else if (value >= 300 && value <= 600) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_PUMPKIN, click, value, false),
                    ChatColor.DARK_GREEN + "Alt Item Despawn Rate - Pumpkin",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value > 600) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_PUMPKIN, click, value, false),
                    ChatColor.DARK_RED + "Alt Item Despawn Rate - Pumpkin",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_PUMPKIN, click, value, false),
                    ChatColor.GOLD + "Alt Item Despawn Rate - Pumpkin",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.YELLOW + "Items may despawn too quickly.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void altItemDespawnRateMelonSlice(char id) {
        int value = this.paperWorld.getInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_MELON_SLICE);
        if (!isAltItemDespawnRateEnabled) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.WHITE_CONCRETE),
                    1,
                    click -> true,
                    ChatColor.WHITE + "Alt Item Despawn Rate - Melon Slice",
                    ChatColor.GRAY + "The Alt Item Despawn Rate feature is disabled.",
                    ChatColor.GRAY + "Please enable it to modify this value."
            ));
        } else if (value >= 300 && value <= 600) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_MELON_SLICE, click, value, false),
                    ChatColor.DARK_GREEN + "Alt Item Despawn Rate - Melon Slice",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value > 600) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_MELON_SLICE, click, value, false),
                    ChatColor.DARK_RED + "Alt Item Despawn Rate - Melon Slice",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_MELON_SLICE, click, value, false),
                    ChatColor.GOLD + "Alt Item Despawn Rate - Melon Slice",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.YELLOW + "Items may despawn too quickly.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void altItemDespawnRateKelp(char id) {
        int value = this.paperWorld.getInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_KELP);
        if (!isAltItemDespawnRateEnabled) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.WHITE_CONCRETE),
                    1,
                    click -> true,
                    ChatColor.WHITE + "Alt Item Despawn Rate - Kelp",
                    ChatColor.GRAY + "The Alt Item Despawn Rate feature is disabled.",
                    ChatColor.GRAY + "Please enable it to modify this value."
            ));
        } else if (value >= 300 && value <= 600) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_KELP, click, value, false),
                    ChatColor.DARK_GREEN + "Alt Item Despawn Rate - Kelp",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value > 600) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_KELP, click, value, false),
                    ChatColor.DARK_RED + "Alt Item Despawn Rate - Kelp",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_KELP, click, value, false),
                    ChatColor.GOLD + "Alt Item Despawn Rate - Kelp",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.YELLOW + "Items may despawn too quickly.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void altItemDespawnRateBamboo(char id) {
        int value = this.paperWorld.getInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_BAMBOO);
        if (!isAltItemDespawnRateEnabled) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.WHITE_CONCRETE),
                    1,
                    click -> true,
                    ChatColor.WHITE + "Alt Item Despawn Rate - Bamboo",
                    ChatColor.GRAY + "The Alt Item Despawn Rate feature is disabled.",
                    ChatColor.GRAY + "Please enable it to modify this value."
            ));
        } else if (value >= 300 && value <= 600) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_BAMBOO, click, value, false),
                    ChatColor.DARK_GREEN + "Alt Item Despawn Rate - Bamboo",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value > 600) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_BAMBOO, click, value, false),
                    ChatColor.DARK_RED + "Alt Item Despawn Rate - Bamboo",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_BAMBOO, click, value, false),
                    ChatColor.GOLD + "Alt Item Despawn Rate - Bamboo",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.YELLOW + "Items may despawn too quickly.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void altItemDespawnRateSugarCane(char id) {
        int value = this.paperWorld.getInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_SUGAR_CANE);
        if (!isAltItemDespawnRateEnabled) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.WHITE_CONCRETE),
                    1,
                    click -> true,
                    ChatColor.WHITE + "Alt Item Despawn Rate - Sugar Cane",
                    ChatColor.GRAY + "The Alt Item Despawn Rate feature is disabled.",
                    ChatColor.GRAY + "Please enable it to modify this value."
            ));
        } else if (value >= 300 && value <= 600) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_SUGAR_CANE, click, value, false),
                    ChatColor.DARK_GREEN + "Alt Item Despawn Rate - Sugar Cane",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value > 600) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_SUGAR_CANE, click, value, false),
                    ChatColor.DARK_RED + "Alt Item Despawn Rate - Sugar Cane",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_SUGAR_CANE, click, value, false),
                    ChatColor.GOLD + "Alt Item Despawn Rate - Sugar Cane",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.YELLOW + "Items may despawn too quickly.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void altItemDespawnRateTwistingVines(char id) {
        int value = this.paperWorld.getInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_TWISTING_VINES);
        if (!isAltItemDespawnRateEnabled) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.WHITE_CONCRETE),
                    1,
                    click -> true,
                    ChatColor.WHITE + "Alt Item Despawn Rate - Twisting Vines",
                    ChatColor.GRAY + "The Alt Item Despawn Rate feature is disabled.",
                    ChatColor.GRAY + "Please enable it to modify this value."
            ));
        } else if (value >= 300 && value <= 600) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_TWISTING_VINES, click, value, false),
                    ChatColor.DARK_GREEN + "Alt Item Despawn Rate - Twisting Vines",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value > 600) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_TWISTING_VINES, click, value, false),
                    ChatColor.DARK_RED + "Alt Item Despawn Rate - Twisting Vines",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_TWISTING_VINES, click, value, false),
                    ChatColor.GOLD + "Alt Item Despawn Rate - Twisting Vines",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.YELLOW + "Items may despawn too quickly.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void altItemDespawnRateWeepingVines(char id) {
        int value = this.paperWorld.getInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_WEEPING_VINES);
        if (!isAltItemDespawnRateEnabled) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.WHITE_CONCRETE),
                    1,
                    click -> true,
                    ChatColor.WHITE + "Alt Item Despawn Rate - Weeping Vines",
                    ChatColor.GRAY + "The Alt Item Despawn Rate feature is disabled.",
                    ChatColor.GRAY + "Please enable it to modify this value."
            ));
        } else if (value >= 300 && value <= 600) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_WEEPING_VINES, click, value, false),
                    ChatColor.DARK_GREEN + "Alt Item Despawn Rate - Weeping Vines",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value > 600) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_WEEPING_VINES, click, value, false),
                    ChatColor.DARK_RED + "Alt Item Despawn Rate - Weeping Vines",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_WEEPING_VINES, click, value, false),
                    ChatColor.GOLD + "Alt Item Despawn Rate - Weeping Vines",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.YELLOW + "Items may despawn too quickly.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void altItemDespawnRateOakLeaves(char id) {
        int value = this.paperWorld.getInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_OAK_LEAVES);
        if (!isAltItemDespawnRateEnabled) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.WHITE_CONCRETE),
                    1,
                    click -> true,
                    ChatColor.WHITE + "Alt Item Despawn Rate - Oak Leaves",
                    ChatColor.GRAY + "The Alt Item Despawn Rate feature is disabled.",
                    ChatColor.GRAY + "Please enable it to modify this value."
            ));
        } else if (value >= 300 && value <= 600) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_OAK_LEAVES, click, value, false),
                    ChatColor.DARK_GREEN + "Alt Item Despawn Rate - Oak Leaves",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value > 600) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_OAK_LEAVES, click, value, false),
                    ChatColor.DARK_RED + "Alt Item Despawn Rate - Oak Leaves",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_OAK_LEAVES, click, value, false),
                    ChatColor.GOLD + "Alt Item Despawn Rate - Oak Leaves",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.YELLOW + "Items may despawn too quickly.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void altItemDespawnRateSpruceLeaves(char id) {
        int value = this.paperWorld.getInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_SPRUCE_LEAVES);
        if (!isAltItemDespawnRateEnabled) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.WHITE_CONCRETE),
                    1,
                    click -> true,
                    ChatColor.WHITE + "Alt Item Despawn Rate - Spruce Leaves",
                    ChatColor.GRAY + "The Alt Item Despawn Rate feature is disabled.",
                    ChatColor.GRAY + "Please enable it to modify this value."
            ));
        } else if (value >= 300 && value <= 600) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_SPRUCE_LEAVES, click, value, false),
                    ChatColor.DARK_GREEN + "Alt Item Despawn Rate - Spruce Leaves",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value > 600) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_SPRUCE_LEAVES, click, value, false),
                    ChatColor.DARK_RED + "Alt Item Despawn Rate - Spruce Leaves",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_SPRUCE_LEAVES, click, value, false),
                    ChatColor.GOLD + "Alt Item Despawn Rate - Spruce Leaves",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.YELLOW + "Items may despawn too quickly.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void altItemDespawnRateBirchLeaves(char id) {
        int value = this.paperWorld.getInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_BIRCH_LEAVES);
        if (!isAltItemDespawnRateEnabled) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.WHITE_CONCRETE),
                    1,
                    click -> true,
                    ChatColor.WHITE + "Alt Item Despawn Rate - Birch Leaves",
                    ChatColor.GRAY + "The Alt Item Despawn Rate feature is disabled.",
                    ChatColor.GRAY + "Please enable it to modify this value."
            ));
        } else if (value >= 300 && value <= 600) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_BIRCH_LEAVES, click, value, false),
                    ChatColor.DARK_GREEN + "Alt Item Despawn Rate - Birch Leaves",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value > 600) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_BIRCH_LEAVES, click, value, false),
                    ChatColor.DARK_RED + "Alt Item Despawn Rate - Birch Leaves",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_BIRCH_LEAVES, click, value, false),
                    ChatColor.GOLD + "Alt Item Despawn Rate - Birch Leaves",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.YELLOW + "Items may despawn too quickly.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void altItemDespawnRateJungleLeaves(char id) {
        int value = this.paperWorld.getInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_JUNGLE_LEAVES);
        if (!isAltItemDespawnRateEnabled) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.WHITE_CONCRETE),
                    1,
                    click -> true,
                    ChatColor.WHITE + "Alt Item Despawn Rate - Jungle Leaves",
                    ChatColor.GRAY + "The Alt Item Despawn Rate Jungle is disabled.",
                    ChatColor.GRAY + "Please enable it to modify this value."
            ));
        } else if (value >= 300 && value <= 600) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_JUNGLE_LEAVES, click, value, false),
                    ChatColor.DARK_GREEN + "Alt Item Despawn Rate - Jungle Leaves",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value > 600) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_JUNGLE_LEAVES, click, value, false),
                    ChatColor.DARK_RED + "Alt Item Despawn Rate - Jungle Leaves",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_JUNGLE_LEAVES, click, value, false),
                    ChatColor.GOLD + "Alt Item Despawn Rate - Jungle Leaves",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.YELLOW + "Items may despawn too quickly.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void altItemDespawnRateAcaciaLeaves(char id) {
        int value = this.paperWorld.getInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_ACACIA_LEAVES);
        if (!isAltItemDespawnRateEnabled) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.WHITE_CONCRETE),
                    1,
                    click -> true,
                    ChatColor.WHITE + "Alt Item Despawn Rate - Acacia Leaves",
                    ChatColor.GRAY + "The Alt Item Despawn Rate feature is disabled.",
                    ChatColor.GRAY + "Please enable it to modify this value."
            ));
        } else if (value >= 300 && value <= 600) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_ACACIA_LEAVES, click, value, false),
                    ChatColor.DARK_GREEN + "Alt Item Despawn Rate - Acacia Leaves",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value > 600) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_ACACIA_LEAVES, click, value, false),
                    ChatColor.DARK_RED + "Alt Item Despawn Rate - Acacia Leaves",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_ACACIA_LEAVES, click, value, false),
                    ChatColor.GOLD + "Alt Item Despawn Rate - Acacia Leaves",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.YELLOW + "Items may despawn too quickly.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void altItemDespawnRateDarkOakLeaves(char id) {
        int value = this.paperWorld.getInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_DARK_OAK_LEAVES);
        if (!isAltItemDespawnRateEnabled) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.WHITE_CONCRETE),
                    1,
                    click -> true,
                    ChatColor.WHITE + "Alt Item Despawn Rate - Dark Oak Leaves",
                    ChatColor.GRAY + "The Alt Item Despawn Rate feature is disabled.",
                    ChatColor.GRAY + "Please enable it to modify this value."
            ));
        } else if (value >= 300 && value <= 600) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_DARK_OAK_LEAVES, click, value, false),
                    ChatColor.DARK_GREEN + "Alt Item Despawn Rate - Dark Oak Leaves",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value > 600) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_DARK_OAK_LEAVES, click, value, false),
                    ChatColor.DARK_RED + "Alt Item Despawn Rate - Dark Oak Leaves",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_DARK_OAK_LEAVES, click, value, false),
                    ChatColor.GOLD + "Alt Item Despawn Rate - Dark Oak Leaves",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.YELLOW + "Items may despawn too quickly.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void altItemDespawnRateMangroveLeaves(char id) {
        int value = this.paperWorld.getInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_MANGROVE_LEAVES);
        if (!isAltItemDespawnRateEnabled) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.WHITE_CONCRETE),
                    1,
                    click -> true,
                    ChatColor.WHITE + "Alt Item Despawn Rate - Mangrove Leaves",
                    ChatColor.GRAY + "The Alt Item Despawn Rate Jungle is disabled.",
                    ChatColor.GRAY + "Please enable it to modify this value."
            ));
        } else if (value >= 300 && value <= 600) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_MANGROVE_LEAVES, click, value, false),
                    ChatColor.DARK_GREEN + "Alt Item Despawn Rate - Mangrove Leaves",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value > 600) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_MANGROVE_LEAVES, click, value, false),
                    ChatColor.DARK_RED + "Alt Item Despawn Rate - Mangrove Leaves",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_MANGROVE_LEAVES, click, value, false),
                    ChatColor.GOLD + "Alt Item Despawn Rate - Mangrove Leaves",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.YELLOW + "Items may despawn too quickly.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void altItemDespawnRateCactus(char id) {
        int value = this.paperWorld.getInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_CACTUS);
        if (!isAltItemDespawnRateEnabled) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.WHITE_CONCRETE),
                    1,
                    click -> true,
                    ChatColor.WHITE + "Alt Item Despawn Rate - Cactus",
                    ChatColor.GRAY + "The Alt Item Despawn Rate feature is disabled.",
                    ChatColor.GRAY + "Please enable it to modify this value."
            ));
        } else if (value >= 300 && value <= 600) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_CACTUS, click, value, false),
                    ChatColor.DARK_GREEN + "Alt Item Despawn Rate - Cactus",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value > 600) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_CACTUS, click, value, false),
                    ChatColor.DARK_RED + "Alt Item Despawn Rate - Cactus",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_CACTUS, click, value, false),
                    ChatColor.GOLD + "Alt Item Despawn Rate - Cactus",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.YELLOW + "Items may despawn too quickly.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void altItemDespawnRateDiorite(char id) {
        int value = this.paperWorld.getInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_DIORITE);
        if (!isAltItemDespawnRateEnabled) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.WHITE_CONCRETE),
                    1,
                    click -> true,
                    ChatColor.WHITE + "Alt Item Despawn Rate - Diorite",
                    ChatColor.GRAY + "The Alt Item Despawn Rate feature is disabled.",
                    ChatColor.GRAY + "Please enable it to modify this value."
            ));
        } else if (value >= 300 && value <= 600) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_DIORITE, click, value, false),
                    ChatColor.DARK_GREEN + "Alt Item Despawn Rate - Diorite",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value > 600) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_DIORITE, click, value, false),
                    ChatColor.DARK_RED + "Alt Item Despawn Rate - Diorite",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_DIORITE, click, value, false),
                    ChatColor.GOLD + "Alt Item Despawn Rate - Diorite",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.YELLOW + "Items may despawn too quickly.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
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

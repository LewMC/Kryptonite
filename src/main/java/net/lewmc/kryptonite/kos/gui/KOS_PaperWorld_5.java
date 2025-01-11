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
 * KOS Paper World GUI (Page 5/5)
 */
public class KOS_PaperWorld_5 {
    private final Kryptonite plugin;
    private final CommandSender user;
    private final PaperWorld paperWorld;
    private final boolean isAltItemDespawnRateEnabled;
    private InventoryGui gui;

    /**
     * Constructor for the KOS Paper World GUI (Page 5/5)
     * @param plugin Kryptonite - Reference to the main plugin class.
     * @param user CommandSender - The user who sent the command.
     */
    public KOS_PaperWorld_5(Kryptonite plugin, CommandSender user) {
        this.plugin = plugin;
        this.user = user;
        this.paperWorld = new PaperWorld(plugin, user);

        this.isAltItemDespawnRateEnabled = this.paperWorld.getBoolean(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_ENABLED);
    }

    /**
     * Shows the KOS Paper World GUI (Page 5/5)
     */
    public void show() {
        InventoryHolder holder = this.user.getServer().getPlayer(this.user.getName());
        this.gui = new InventoryGui(this.plugin, holder, "KOS - Paper World Configuration (5/5)", this.getElements());
        this.addElements();

        this.gui.build();
        this.gui.show((Player) this.user);
    }

    /**
     * Adds pre-programmed elements to the GUI
     */
    private void addElements() {
        this.altItemDespawnRateGranite('a');
        this.altItemDespawnRateAndesite('b');
        this.altItemDespawnRateScaffolding('c');
        this.altItemDespawnRateEgg('d');
        this.redstoneImplementation('e');
        this.hopperDisableMoveEvent('f');
        this.hopperIgnoreOccludingBlocks('g');
        this.tickRateMobSpawner('h');
        this.optimizeExplosions('i');
        this.treasureMapsEnabled('j');
        this.treasureMapsFindAlreadyDiscoveredLootTables('k');
        this.treasureMapsFindAlreadyDiscoveredVillagerTrade('l');
        this.tickRateGrassSpread('m');
        this.tickRateContainerUpdate('n');
        this.arrowDespawnRateNonPlayer('o');
        this.arrowDespawnRateCreative('p');
        this.nerfedSpawnerMobsShouldJump('q');

        KOS_GuiConstants consts = new KOS_GuiConstants(this.plugin, this.gui);
        consts.addConstants();

        this.gui.addElement(new StaticGuiElement('v',
                    new ItemStack(Material.OAK_SIGN),
                1,
                click -> {
                    click.getGui().close();
                    KOS_PaperWorld_4 prevGui = new KOS_PaperWorld_4(this.plugin, this.user);
                    prevGui.show();
                    return true;
                },
                ChatColor.WHITE + "Previous page"
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

    private void altItemDespawnRateGranite(char id) {
        int value = this.paperWorld.getInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_GRANITE);
        if (!isAltItemDespawnRateEnabled) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.WHITE_CONCRETE),
                    1,
                    click -> true,
                    ChatColor.WHITE + "Alt Item Despawn Rate - Granite",
                    ChatColor.GRAY + "The Alt Item Despawn Rate feature is disabled.",
                    ChatColor.GRAY + "Please enable it to modify this value."
            ));
        } else if (value >= 300 && value <= 600) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.GREEN_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_GRANITE, click, value, false),
                    ChatColor.DARK_GREEN + "Alt Item Despawn Rate - Granite",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value > 600) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_GRANITE, click, value, false),
                    ChatColor.DARK_RED + "Alt Item Despawn Rate - Granite",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_GRANITE, click, value, false),
                    ChatColor.GOLD + "Alt Item Despawn Rate - Granite",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.YELLOW + "Items may despawn too quickly.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void altItemDespawnRateAndesite(char id) {
        int value = this.paperWorld.getInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_ANDESITE);
        if (!isAltItemDespawnRateEnabled) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.WHITE_CONCRETE),
                    1,
                    click -> true,
                    ChatColor.WHITE + "Alt Item Despawn Rate - Andesite",
                    ChatColor.GRAY + "The Alt Item Despawn Rate feature is disabled.",
                    ChatColor.GRAY + "Please enable it to modify this value."
            ));
        } else if (value >= 300 && value <= 600) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.GREEN_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_ANDESITE, click, value, false),
                    ChatColor.DARK_GREEN + "Alt Item Despawn Rate - Andesite",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value > 600) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_ANDESITE, click, value, false),
                    ChatColor.DARK_RED + "Alt Item Despawn Rate - Andesite",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_ANDESITE, click, value, false),
                    ChatColor.GOLD + "Alt Item Despawn Rate - Andesite",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.YELLOW + "Items may despawn too quickly.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void altItemDespawnRateScaffolding(char id) {
        int value = this.paperWorld.getInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_SCAFFOLDING);
        if (!isAltItemDespawnRateEnabled) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.WHITE_CONCRETE),
                    1,
                    click -> true,
                    ChatColor.WHITE + "Alt Item Despawn Rate - Scaffolding",
                    ChatColor.GRAY + "The Alt Item Despawn Rate feature is disabled.",
                    ChatColor.GRAY + "Please enable it to modify this value."
            ));
        } else if (value >= 300 && value <= 600) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.GREEN_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_SCAFFOLDING, click, value, false),
                    ChatColor.DARK_GREEN + "Alt Item Despawn Rate - Scaffolding",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value > 600) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_SCAFFOLDING, click, value, false),
                    ChatColor.DARK_RED + "Alt Item Despawn Rate - Scaffolding",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_SCAFFOLDING, click, value, false),
                    ChatColor.GOLD + "Alt Item Despawn Rate - Scaffolding",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.YELLOW + "Items may despawn too quickly.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void altItemDespawnRateEgg(char id) {
        int value = this.paperWorld.getInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_EGG);
        if (!isAltItemDespawnRateEnabled) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.WHITE_CONCRETE),
                    1,
                    click -> true,
                    ChatColor.WHITE + "Alt Item Despawn Rate - Egg",
                    ChatColor.GRAY + "The Alt Item Despawn Rate feature is disabled.",
                    ChatColor.GRAY + "Please enable it to modify this value."
            ));
        } else if (value >= 300 && value <= 600) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.GREEN_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_EGG, click, value, false),
                    ChatColor.DARK_GREEN + "Alt Item Despawn Rate - Egg",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value > 600) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_EGG, click, value, false),
                    ChatColor.DARK_RED + "Alt Item Despawn Rate - Egg",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_EGG, click, value, false),
                    ChatColor.GOLD + "Alt Item Despawn Rate - Egg",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.YELLOW + "Items may despawn too quickly.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void redstoneImplementation(char id) {
        String value = this.paperWorld.getString(PaperWorld.Key.REDSTONE_IMPLEMENTATION);
        if (value.equalsIgnoreCase("vanilla")) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> {
                        this.paperWorld.setString(PaperWorld.Key.REDSTONE_IMPLEMENTATION, "VANILLA");
                        return true;
                    },
                    ChatColor.DARK_RED + "Redstone Implementation",
                    ChatColor.RED + value,
                    ChatColor.RED + "Significant impact to performance.",
                    ChatColor.BLUE + "Click to cycle through implementations."
            ));
        } else if (value.equalsIgnoreCase("eigencraft")) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> {
                        this.paperWorld.setString(PaperWorld.Key.REDSTONE_IMPLEMENTATION, "EIGENCRAFT");
                        return true;
                    },
                    ChatColor.DARK_RED + "Redstone Implementation",
                    ChatColor.RED + value,
                    ChatColor.RED + "Slight impact to performance.",
                    ChatColor.BLUE + "Click to cycle through implementations."
            ));
        } else if (value.equalsIgnoreCase("alternate-current")) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> {
                        this.paperWorld.setString(PaperWorld.Key.REDSTONE_IMPLEMENTATION, "ALTERNATE-CURRENT");
                        return true;
                    },
                    ChatColor.DARK_GREEN + "Redstone Implementation",
                    ChatColor.GREEN + value,
                    ChatColor.GREEN + "Ideal value.",
                    ChatColor.BLUE + "Click to cycle through implementations."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.WHITE_CONCRETE),
                    1,
                    click -> {
                        this.paperWorld.setString(PaperWorld.Key.REDSTONE_IMPLEMENTATION, "VANILLA");
                        return true;
                    },
                    ChatColor.WHITE + "Redstone Implementation",
                    ChatColor.GRAY + value,
                    ChatColor.GRAY + "Kryptonite doesn't know what this implementation is.",
                    ChatColor.GRAY + "Click to reset."
            ));
        }
    }

    private void hopperDisableMoveEvent(char id) {
        boolean value = this.paperWorld.getBoolean(PaperWorld.Key.HOPPER_DISABLE_MOVE_EVENT);
        if (!value) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        this.paperWorld.setBoolean(PaperWorld.Key.HOPPER_DISABLE_MOVE_EVENT, true);
                        this.show();
                        return true;
                    },
                    ChatColor.DARK_GREEN + "Hopper Disable Move Event",
                    ChatColor.GREEN + "false",
                    ChatColor.GREEN + "Ideal value.",
                    ChatColor.GREEN + "Warning: may break some plugins that listen to.",
                    ChatColor.GREEN + "InventoryMoveItemEvent on hoppers.",
                    ChatColor.BLUE + "Click to toggle true/false."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        this.paperWorld.setBoolean(PaperWorld.Key.HOPPER_DISABLE_MOVE_EVENT, false);
                        this.show();
                        return true;
                    },
                    ChatColor.DARK_RED + "Hopper Disable Move Event",
                    ChatColor.RED + "true",
                    ChatColor.RED + "Impact to performance.",
                    ChatColor.BLUE + "Click to toggle true/false."
            ));
        }
    }

    private void hopperIgnoreOccludingBlocks(char id) {
        boolean value = this.paperWorld.getBoolean(PaperWorld.Key.HOPPER_IGNORE_OCCLUDING_BLOCKS);
        if (value) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        this.paperWorld.setBoolean(PaperWorld.Key.HOPPER_IGNORE_OCCLUDING_BLOCKS, false);
                        this.show();
                        return true;
                    },
                    ChatColor.DARK_GREEN + "Hopper Ignore Occluding Blocks",
                    ChatColor.GREEN + "true",
                    ChatColor.GREEN + "Ideal value.",
                    ChatColor.GREEN + "Warning: May break some redstone contraptions",
                    ChatColor.GREEN + "that use this feature.",
                    ChatColor.BLUE + "Click to toggle true/false."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        this.paperWorld.setBoolean(PaperWorld.Key.HOPPER_IGNORE_OCCLUDING_BLOCKS, true);
                        this.show();
                        return true;
                    },
                    ChatColor.DARK_RED + "Hopper Ignore Occluding Blocks",
                    ChatColor.RED + "false",
                    ChatColor.RED + "Impact to performance.",
                    ChatColor.BLUE + "Click to toggle true/false."
            ));
        }
    }

    private void tickRateMobSpawner(char id) {
        int value = this.paperWorld.getInt(PaperWorld.Key.TICK_RATE_MOB_SPAWNER);
        if (value >= 2 && value <= 10) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.GREEN_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.TICK_RATE_MOB_SPAWNER, click, value, false),
                    ChatColor.DARK_GREEN + "Tick Rate - Mob Spawner",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value > 10) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.TICK_RATE_MOB_SPAWNER, click, value, false),
                    ChatColor.GOLD + "Tick Rate - Mob Spawner",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too high - impact to player experience.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.TICK_RATE_MOB_SPAWNER, click, value, false),
                    ChatColor.DARK_RED + "Tick Rate - Mob Spawner",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too low - impact to performance.",
                    ChatColor.RED + "Items may despawn too quickly.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void optimizeExplosions(char id) {
        boolean value = this.paperWorld.getBoolean(PaperWorld.Key.OPTIMIZE_EXPLOSIONS);
        if (value) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        this.paperWorld.setBoolean(PaperWorld.Key.OPTIMIZE_EXPLOSIONS, false);
                        this.show();
                        return true;
                    },
                    ChatColor.DARK_GREEN + "Optimize Explosions",
                    ChatColor.GREEN + "true",
                    ChatColor.GREEN + "Ideal value.",
                    ChatColor.BLUE + "Click to toggle true/false."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        this.paperWorld.setBoolean(PaperWorld.Key.OPTIMIZE_EXPLOSIONS, true);
                        this.show();
                        return true;
                    },
                    ChatColor.DARK_RED + "Optimize Explosions",
                    ChatColor.RED + "false",
                    ChatColor.RED + "Impact to performance.",
                    ChatColor.BLUE + "Click to toggle true/false."
            ));
        }
    }

    private void treasureMapsEnabled(char id) {
        boolean value = this.paperWorld.getBoolean(PaperWorld.Key.TREASURE_MAPS_ENABLED);
        if (!value) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        this.paperWorld.setBoolean(PaperWorld.Key.TREASURE_MAPS_ENABLED, true);
                        this.show();
                        return true;
                    },
                    ChatColor.GOLD + "Treasure Maps Enabled",
                    ChatColor.YELLOW + "false",
                    ChatColor.YELLOW + "Impact to player experience.",
                    ChatColor.BLUE + "Click to toggle true/false."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        this.paperWorld.setBoolean(PaperWorld.Key.TREASURE_MAPS_ENABLED, false);
                        this.show();
                        return true;
                    },
                    ChatColor.DARK_RED + "Treasure Maps Enabled",
                    ChatColor.RED + "true",
                    ChatColor.RED + "Impact to performance.",
                    ChatColor.RED + "ONLY IF WORLD IS NOT PRE-GENERATED.",
                    ChatColor.BLUE + "Click to toggle true/false."
            ));
        }
    }

    private void treasureMapsFindAlreadyDiscoveredLootTables(char id) {
        boolean value = this.paperWorld.getBoolean(PaperWorld.Key.TREASURE_MAPS_FIND_ALREADY_DISCOVERED_LOOT_TABLES);
        if (value) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        this.paperWorld.setBoolean(PaperWorld.Key.TREASURE_MAPS_FIND_ALREADY_DISCOVERED_LOOT_TABLES, false);
                        this.show();
                        return true;
                    },
                    ChatColor.GOLD + "Treasure Maps Find Already Discovered Loot Tables",
                    ChatColor.YELLOW + "true",
                    ChatColor.YELLOW + "Impact to player experience.",
                    ChatColor.BLUE + "Click to toggle true/false."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        this.paperWorld.setBoolean(PaperWorld.Key.TREASURE_MAPS_FIND_ALREADY_DISCOVERED_LOOT_TABLES, true);
                        this.show();
                        return true;
                    },
                    ChatColor.DARK_RED + "Treasure Maps Find Already Discovered Loot Tables",
                    ChatColor.RED + "false",
                    ChatColor.RED + "Impact to performance.",
                    ChatColor.BLUE + "Click to toggle true/false."
            ));
        }
    }

    private void treasureMapsFindAlreadyDiscoveredVillagerTrade(char id) {
        boolean value = this.paperWorld.getBoolean(PaperWorld.Key.TREASURE_MAPS_FIND_ALREADY_DISCOVERED_VILLAGER_TRADE);
        if (value) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        this.paperWorld.setBoolean(PaperWorld.Key.TREASURE_MAPS_FIND_ALREADY_DISCOVERED_VILLAGER_TRADE, false);
                        this.show();
                        return true;
                    },
                    ChatColor.GOLD + "Treasure Maps Find Already Discovered Villager Trade",
                    ChatColor.YELLOW + "true",
                    ChatColor.YELLOW + "Impact to player experience.",
                    ChatColor.BLUE + "Click to toggle true/false."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        this.paperWorld.setBoolean(PaperWorld.Key.TREASURE_MAPS_FIND_ALREADY_DISCOVERED_VILLAGER_TRADE, true);
                        this.show();
                        return true;
                    },
                    ChatColor.DARK_RED + "Treasure Maps Find Already Discovered Villager Trade",
                    ChatColor.RED + "false",
                    ChatColor.RED + "Impact to performance.",
                    ChatColor.BLUE + "Click to toggle true/false."
            ));
        }
    }

    private void tickRateGrassSpread(char id) {
        int value = this.paperWorld.getInt(PaperWorld.Key.TICK_RATE_GRASS_SPREAD);
        if (value >= 1 && value <= 6) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.GREEN_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.TICK_RATE_GRASS_SPREAD, click, value, false),
                    ChatColor.DARK_GREEN + "Tick Rate - Grass Spread",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value > 6) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.TICK_RATE_GRASS_SPREAD, click, value, false),
                    ChatColor.GOLD + "Tick Rate - Grass Spread",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too high - impact to player experience.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.TICK_RATE_GRASS_SPREAD, click, value, false),
                    ChatColor.DARK_RED + "Tick Rate - Grass Spread",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too low - impact to performance.",
                    ChatColor.RED + "Grass may not spread fast enough.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void tickRateContainerUpdate(char id) {
        int value = this.paperWorld.getInt(PaperWorld.Key.TICK_RATE_CONTAINER_UPDATE);
        if (value >= 1 && value <= 2) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.GREEN_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.TICK_RATE_CONTAINER_UPDATE, click, value, false),
                    ChatColor.DARK_GREEN + "Tick Rate - Container Update",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value > 2) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.TICK_RATE_CONTAINER_UPDATE, click, value, false),
                    ChatColor.GOLD + "Tick Rate - Container Update",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too high - impact to player experience.",
                    ChatColor.YELLOW + "May cause ghost items.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.TICK_RATE_CONTAINER_UPDATE, click, value, false),
                    ChatColor.DARK_RED + "Tick Rate - Container Update",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too low - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void arrowDespawnRateNonPlayer(char id) {
        int value = this.paperWorld.getInt(PaperWorld.Key.ARROW_DESPAWN_RATE_NON_PLAYER);
        if (value >= 10 && value <= 30) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.GREEN_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ARROW_DESPAWN_RATE_NON_PLAYER, click, value, false),
                    ChatColor.DARK_GREEN + "Arrow Despawn Rate - Non-Player",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value > 30) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ARROW_DESPAWN_RATE_NON_PLAYER, click, value, false),
                    ChatColor.DARK_RED + "Arrow Despawn Rate - Non-Player",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.RED + "Items may despawn too quickly.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ARROW_DESPAWN_RATE_NON_PLAYER, click, value, false),
                    ChatColor.GOLD + "Arrow Despawn Rate - Non-Player",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void arrowDespawnRateCreative(char id) {
        int value = this.paperWorld.getInt(PaperWorld.Key.ARROW_DESPAWN_RATE_CREATIVE);
        if (value >= 10 && value <= 30) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.GREEN_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ARROW_DESPAWN_RATE_CREATIVE, click, value, false),
                    ChatColor.DARK_GREEN + "Arrow Despawn Rate - Creative",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value > 30) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ARROW_DESPAWN_RATE_CREATIVE, click, value, false),
                    ChatColor.DARK_RED + "Arrow Despawn Rate - Creative",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.RED + "Items may despawn too quickly.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ARROW_DESPAWN_RATE_CREATIVE, click, value, false),
                    ChatColor.GOLD + "Arrow Despawn Rate - Creative",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void nerfedSpawnerMobsShouldJump(char id) {
        boolean value = this.paperWorld.getBoolean(PaperWorld.Key.NERFED_SPAWNER_MOBS_SHOULD_JUMP);
        if (!value) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        this.paperWorld.setBoolean(PaperWorld.Key.NERFED_SPAWNER_MOBS_SHOULD_JUMP, true);
                        this.show();
                        return true;
                    },
                    ChatColor.GOLD + "Nerfed Spawner Mobs Should Jump",
                    ChatColor.YELLOW + "false",
                    ChatColor.YELLOW + "Impact to player experience.",
                    ChatColor.BLUE + "Click to toggle true/false."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        this.paperWorld.setBoolean(PaperWorld.Key.NERFED_SPAWNER_MOBS_SHOULD_JUMP, false);
                        this.show();
                        return true;
                    },
                    ChatColor.DARK_GREEN + "Nerfed Spawner Mobs Should Jump",
                    ChatColor.GREEN + "true",
                    ChatColor.GREEN + "Ideal value.",
                    ChatColor.BLUE + "Click to toggle true/false."
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

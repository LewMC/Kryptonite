package net.lewmc.kryptonite.kos.gui;

import de.themoep.inventorygui.GuiElement;
import de.themoep.inventorygui.InventoryGui;
import de.themoep.inventorygui.StaticGuiElement;
import net.lewmc.kryptonite.Kryptonite;
import net.lewmc.kryptonite.kos.config.PaperWorld;
import net.lewmc.kryptonite.utils.SoftwareUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

/**
 * KOS Paper World GUI (Page 3/5)
 */
public class KOS_PaperWorld_3 {
    private final Kryptonite plugin;
    private final CommandSender user;
    private final PaperWorld paperWorld;
    private final boolean dabEnabled;
    private InventoryGui gui;
    private boolean isAltItemDespawnRateEnabled;

    /**
     * Constructor for the KOS Paper World GUI (Page 3/5)
     * @param plugin Kryptonite - Reference to the main plugin class.
     * @param user CommandSender - The user who sent the command.
     */
    public KOS_PaperWorld_3(Kryptonite plugin, CommandSender user) {
        this.plugin = plugin;
        this.user = user;
        this.paperWorld = new PaperWorld(plugin, user);

        SoftwareUtil sw = new SoftwareUtil(this.plugin);
        this.dabEnabled = sw.dabEnabled(this.user);

        this.isAltItemDespawnRateEnabled = this.paperWorld.getBoolean(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_ENABLED);
    }

    /**
     * Shows the KOS Paper World GUI (Page 3/5)
     */
    public void show() {
        InventoryHolder holder = this.user.getServer().getPlayer(this.user.getName());
        this.gui = new InventoryGui(this.plugin, holder, "KOS - Paper World Configuration (3/5)", this.getElements());
        this.addElements();

        this.gui.build();
        this.gui.show((Player) this.user);
    }

    /**
     * Adds pre-programmed elements to the GUI
     */
    private void addElements() {
        this.perPlayerMobSpawns('a');
        this.maxEntityCollisions('b');
        this.updatePathfindingOnBlockUpdate('c');
        this.fixClimbingBypassingCrammingRule('d');
        this.armorStandsTick('e');
        this.armorStandsDoCollisionEntityLookups('e');
        this.tickRateVillagerValidatesNearbyPoi('f');
        this.tickRateVillagerAcquirePoi('g');
        this.tickRateVillagerSensorNearestBed('h');
        this.tickRateVillagerSensorSecondaryPoi('i');
        this.tickRateVillagerSensorVillagerBabies('j');
        this.tickRateVillagerSensorPlayer('k');
        this.tickRateVillagerSensorNearestLivingEntity('l');
        this.altItemDespawnRateEnabled('m');
        this.altItemDespawnRateCobblestone('n');
        this.altItemDespawnRateNetherrack('o');
        this.altItemDespawnRateSand('p');
        this.altItemDespawnRateRedSand('q');
        this.altItemDespawnRateGravel('r');

        KOS_GuiConstants consts = new KOS_GuiConstants(this.plugin, this.gui);
        consts.addConstants();

        this.gui.addElement(new StaticGuiElement('v',
                    new ItemStack(Material.OAK_SIGN),
                1,
                click -> {
                    click.getGui().close();
                    KOS_PaperWorld_2 prevGui = new KOS_PaperWorld_2(this.plugin, this.user);
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
                    KOS_PaperWorld_4 nextGui = new KOS_PaperWorld_4(this.plugin, this.user);
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

    private void perPlayerMobSpawns(char id) {
        boolean value = this.paperWorld.getBoolean(PaperWorld.Key.PER_PLAYER_MOB_SPAWNS);
        if (value) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        this.paperWorld.setBoolean(PaperWorld.Key.PER_PLAYER_MOB_SPAWNS, false);
                        this.show();
                        return true;
                    },
                    ChatColor.DARK_GREEN + "Per Player Mob Spawns",
                    ChatColor.GREEN + "true",
                    ChatColor.GREEN + "Ideal value.",
                    ChatColor.BLUE + "Click to toggle true/false."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        this.paperWorld.setBoolean(PaperWorld.Key.PER_PLAYER_MOB_SPAWNS, true);
                        this.show();
                        return true;
                    },
                    ChatColor.GOLD + "Per Player Mob Spawns",
                    ChatColor.YELLOW + "false",
                    ChatColor.YELLOW + "Not ideal value - impact to player experience.",
                    ChatColor.BLUE + "Click to toggle true/false."
            ));
        }
    }

    private void maxEntityCollisions(char id) {
        int value = this.paperWorld.getInt(PaperWorld.Key.MAX_ENTITY_COLLISIONS);
        if (value >= 3 && value <= 8) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.MAX_ENTITY_COLLISIONS, click, value, false, false),
                    ChatColor.DARK_GREEN + "Max Entity Collisions",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Ideal value.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value < 3) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.MAX_ENTITY_COLLISIONS, click, value, false, false),
                    ChatColor.GOLD + "Max Entity Collisions",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.MAX_ENTITY_COLLISIONS, click, value, false, false),
                    ChatColor.DARK_RED + "Max Entity Collisions",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void updatePathfindingOnBlockUpdate(char id) {
        boolean value = this.paperWorld.getBoolean(PaperWorld.Key.UPDATE_PATHFINDING_ON_BLOCK_UPDATE);
        if (value) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        this.paperWorld.setBoolean(PaperWorld.Key.UPDATE_PATHFINDING_ON_BLOCK_UPDATE, false);
                        this.show();
                        return true;
                    },
                    ChatColor.DARK_RED + "Update Pathfinding on Block Update",
                    ChatColor.RED + "true",
                    ChatColor.RED + "Impact to performance.",
                    ChatColor.BLUE + "Click to toggle true/false."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        this.paperWorld.setBoolean(PaperWorld.Key.UPDATE_PATHFINDING_ON_BLOCK_UPDATE, true);
                        this.show();
                        return true;
                    },
                    ChatColor.GOLD + "Update Pathfinding on Block Update",
                    ChatColor.YELLOW + "false",
                    ChatColor.YELLOW + "Impact to player experience - mobs may appear laggy.",
                    ChatColor.BLUE + "Click to toggle true/false."
            ));
        }
    }

    private void fixClimbingBypassingCrammingRule(char id) {
        boolean value = this.paperWorld.getBoolean(PaperWorld.Key.FIX_CLIMBING_BYPASSING_CRAMMING_RULE);
        if (value) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        this.paperWorld.setBoolean(PaperWorld.Key.FIX_CLIMBING_BYPASSING_CRAMMING_RULE, false);
                        this.show();
                        return true;
                    },
                    ChatColor.DARK_GREEN + "Fix Climbing Bypassing Cramming Rule",
                    ChatColor.GREEN + "true",
                    ChatColor.GREEN + "Ideal value.",
                    ChatColor.BLUE + "Click to toggle true/false."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        this.paperWorld.setBoolean(PaperWorld.Key.FIX_CLIMBING_BYPASSING_CRAMMING_RULE, true);
                        this.show();
                        return true;
                    },
                    ChatColor.GOLD + "Fix Climbing Bypassing Cramming Rule",
                    ChatColor.YELLOW + "false",
                    ChatColor.YELLOW + "Impact to player experience - buggy behaviour.",
                    ChatColor.BLUE + "Click to toggle true/false."
            ));
        }
    }

    private void armorStandsTick(char id) {
        boolean value = this.paperWorld.getBoolean(PaperWorld.Key.ARMOR_STANDS_TICK);
        if (value) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        this.paperWorld.setBoolean(PaperWorld.Key.ARMOR_STANDS_TICK, false);
                        this.show();
                        return true;
                    },
                    ChatColor.DARK_RED + "Armor Stands Tick",
                    ChatColor.RED + "true",
                    ChatColor.RED + "Impact to performance.",
                    ChatColor.BLUE + "Click to toggle true/false."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        this.paperWorld.setBoolean(PaperWorld.Key.ARMOR_STANDS_TICK, true);
                        this.show();
                        return true;
                    },
                    ChatColor.GOLD + "Armor Stands Tick",
                    ChatColor.YELLOW + "false",
                    ChatColor.YELLOW + "Impact to player experience - non-vanilla behaviour.",
                    ChatColor.YELLOW + "If you have problems with plugins that use armor stands, enable this.",
                    ChatColor.BLUE + "Click to toggle true/false."
            ));
        }
    }

    private void armorStandsDoCollisionEntityLookups(char id) {
        boolean value = this.paperWorld.getBoolean(PaperWorld.Key.ARMOR_STANDS_DO_COLLISION_ENTITY_LOOKUPS);
        if (value) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        this.paperWorld.setBoolean(PaperWorld.Key.ARMOR_STANDS_DO_COLLISION_ENTITY_LOOKUPS, false);
                        this.show();
                        return true;
                    },
                    ChatColor.DARK_RED + "Armor Stands Do Collision Entity Lookups",
                    ChatColor.RED + "true",
                    ChatColor.RED + "Impact to performance.",
                    ChatColor.BLUE + "Click to toggle true/false."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        this.paperWorld.setBoolean(PaperWorld.Key.ARMOR_STANDS_DO_COLLISION_ENTITY_LOOKUPS, true);
                        this.show();
                        return true;
                    },
                    ChatColor.GOLD + "Armor Stands Do Collision Entity Lookups",
                    ChatColor.YELLOW + "false",
                    ChatColor.YELLOW + "Impact to player experience - non-vanilla behaviour.",
                    ChatColor.YELLOW + "If you have problems with plugins that use armor stands, enable this.",
                    ChatColor.BLUE + "Click to toggle true/false."
            ));
        }
    }

    private void tickRateVillagerValidatesNearbyPoi(char id) {
        int value = this.paperWorld.getInt(PaperWorld.Key.TICK_RATE_VILLAGER_VALIDATES_NEARBY_POI);
        if (this.dabEnabled && value == -1) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.WHITE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.TICK_RATE_VILLAGER_VALIDATES_NEARBY_POI, click, value, false, true),
                    ChatColor.WHITE + "Tick Rate - Villager Validates Nearby Poi",
                    ChatColor.GRAY + String.valueOf(value),
                    ChatColor.GRAY + "This should not be changed when DAB is enabled.",
                    ChatColor.GRAY + "This has not been changed.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (this.dabEnabled) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.WHITE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.TICK_RATE_VILLAGER_VALIDATES_NEARBY_POI, click, value, false, true),
                    ChatColor.WHITE + "Tick Rate - Villager Validates Nearby Poi",
                    ChatColor.GRAY + String.valueOf(value),
                    ChatColor.GRAY + "This should not be changed when DAB is enabled.",
                    ChatColor.GRAY + "This has been changed.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value >= 60 && value <= 120) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.TICK_RATE_VILLAGER_VALIDATES_NEARBY_POI, click, value, false, true),
                    ChatColor.DARK_GREEN + "Tick Rate - Villager Validates Nearby Poi",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value > 120) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.TICK_RATE_VILLAGER_VALIDATES_NEARBY_POI, click, value, false, true),
                    ChatColor.GOLD + "Tick Rate - Villager Validates Nearby Poi",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too high - impact to player experience.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.TICK_RATE_VILLAGER_VALIDATES_NEARBY_POI, click, value, false, true),
                    ChatColor.DARK_RED + "Tick Rate - Villager Validates Nearby Poi",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too low - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void tickRateVillagerAcquirePoi(char id) {
        int value = this.paperWorld.getInt(PaperWorld.Key.TICK_RATE_VILLAGER_ACQUIRE_POI);
        if (this.dabEnabled && value == -1) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.WHITE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.TICK_RATE_VILLAGER_ACQUIRE_POI, click, value, false, true),
                    ChatColor.WHITE + "Tick Rate - Villager Validates Nearby Poi",
                    ChatColor.GRAY + String.valueOf(value),
                    ChatColor.GRAY + "This should not be changed when DAB is enabled.",
                    ChatColor.GRAY + "This has not been changed.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (this.dabEnabled) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.WHITE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.TICK_RATE_VILLAGER_ACQUIRE_POI, click, value, false, true),
                    ChatColor.WHITE + "Tick Rate - Villager Validates Nearby Poi",
                    ChatColor.GRAY + String.valueOf(value),
                    ChatColor.GRAY + "This should not be changed when DAB is enabled.",
                    ChatColor.GRAY + "This has been changed. Please reset to '-1'",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value >= 120 && value <= 240) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.TICK_RATE_VILLAGER_ACQUIRE_POI, click, value, false, true),
                    ChatColor.DARK_GREEN + "Tick Rate - Villager Validates Nearby Poi",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value > 240) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.TICK_RATE_VILLAGER_ACQUIRE_POI, click, value, false, true),
                    ChatColor.GOLD + "Tick Rate - Villager Validates Nearby Poi",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too high - impact to player experience.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.TICK_RATE_VILLAGER_ACQUIRE_POI, click, value, false, true),
                    ChatColor.DARK_RED + "Tick Rate - Villager Validates Nearby Poi",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too low - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void tickRateVillagerSensorNearestBed(char id) {
        int value = this.paperWorld.getInt(PaperWorld.Key.TICK_RATE_VILLAGER_SENSOR_NEAREST_BED);
        if (value >= 40 && value <= 80) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.TICK_RATE_VILLAGER_SENSOR_NEAREST_BED, click, value, false, false),
                    ChatColor.DARK_GREEN + "Tick Rate - Villager Sensor Nearest Bed",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value > 80) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.TICK_RATE_VILLAGER_SENSOR_NEAREST_BED, click, value, false, false),
                    ChatColor.GOLD + "Tick Rate - Villager Sensor Nearest Bed",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too high - impact to player experience.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.TICK_RATE_VILLAGER_SENSOR_NEAREST_BED, click, value, false, false),
                    ChatColor.DARK_RED + "Tick Rate - Villager Sensor Nearest Bed",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too low - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void tickRateVillagerSensorSecondaryPoi(char id) {
        int value = this.paperWorld.getInt(PaperWorld.Key.TICK_RATE_VILLAGER_SENSOR_SECONDARY_POI);
        if (value >= 40 && value <= 80) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.TICK_RATE_VILLAGER_SENSOR_SECONDARY_POI, click, value, false, false),
                    ChatColor.DARK_GREEN + "Tick Rate - Villager Sensor Secondary POI",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value > 80) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.TICK_RATE_VILLAGER_SENSOR_SECONDARY_POI, click, value, false, false),
                    ChatColor.GOLD + "Tick Rate - Villager Sensor Secondary POI",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too high - impact to player experience.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.TICK_RATE_VILLAGER_SENSOR_SECONDARY_POI, click, value, false, false),
                    ChatColor.DARK_RED + "Tick Rate - Villager Sensor Secondary POI",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too low - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void tickRateVillagerSensorVillagerBabies(char id) {
        int value = this.paperWorld.getInt(PaperWorld.Key.TICK_RATE_VILLAGER_SENSOR_VILLAGER_BABIES);
        if (value >= 20 && value <= 40) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.TICK_RATE_VILLAGER_SENSOR_VILLAGER_BABIES, click, value, false, false),
                    ChatColor.DARK_GREEN + "Tick Rate - Villager Sensor Villager Babies",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value > 40) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.TICK_RATE_VILLAGER_SENSOR_VILLAGER_BABIES, click, value, false, false),
                    ChatColor.GOLD + "Tick Rate - Villager Sensor Villager Babies",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too high - impact to player experience.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.TICK_RATE_VILLAGER_SENSOR_VILLAGER_BABIES, click, value, false, false),
                    ChatColor.DARK_RED + "Tick Rate - Villager Sensor Villager Babies",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too low - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void tickRateVillagerSensorPlayer(char id) {
        int value = this.paperWorld.getInt(PaperWorld.Key.TICK_RATE_VILLAGER_SENSOR_PLAYER);
        if (value >= 20 && value <= 40) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.TICK_RATE_VILLAGER_SENSOR_PLAYER, click, value, false, false),
                    ChatColor.DARK_GREEN + "Tick Rate - Villager Sensor Player",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value > 40) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.TICK_RATE_VILLAGER_SENSOR_PLAYER, click, value, false, false),
                    ChatColor.GOLD + "Tick Rate - Villager Sensor Player",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too high - impact to player experience.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.TICK_RATE_VILLAGER_SENSOR_PLAYER, click, value, false, false),
                    ChatColor.DARK_RED + "Tick Rate - Villager Sensor Player",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too low - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void tickRateVillagerSensorNearestLivingEntity(char id) {
        int value = this.paperWorld.getInt(PaperWorld.Key.TICK_RATE_VILLAGER_SENSOR_NEAREST_LIVING_ENTITY);
        if (value >= 20 && value <= 40) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.TICK_RATE_VILLAGER_SENSOR_NEAREST_LIVING_ENTITY, click, value, false, false),
                    ChatColor.DARK_GREEN + "Tick Rate - Villager Sensor Nearest Living Entity",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value > 40) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.TICK_RATE_VILLAGER_SENSOR_NEAREST_LIVING_ENTITY, click, value, false, false),
                    ChatColor.GOLD + "Tick Rate - Villager Sensor Nearest Living Entity",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too high - impact to player experience.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.TICK_RATE_VILLAGER_SENSOR_NEAREST_LIVING_ENTITY, click, value, false, false),
                    ChatColor.DARK_RED + "Tick Rate - Villager Sensor Nearest Living Entity",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too low - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void altItemDespawnRateEnabled(char id) {
        if (this.isAltItemDespawnRateEnabled) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        this.paperWorld.setBoolean(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_ENABLED, false);
                        this.show();
                        return true;
                    },
                    ChatColor.DARK_GREEN + "Alt Item Despawn Rate Enabled",
                    ChatColor.GREEN + "true",
                    ChatColor.GREEN + "Ideal value.",
                    ChatColor.GREEN + "Note: Some items may despawn quicker than usual.",
                    ChatColor.BLUE + "Click to toggle true/false."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        this.paperWorld.setBoolean(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_ENABLED, true);
                        this.show();
                        return true;
                    },
                    ChatColor.DARK_RED + "Alt Item Despawn Rate Enabled",
                    ChatColor.RED + "false",
                    ChatColor.RED + "Impact to performance.",
                    ChatColor.BLUE + "Click to toggle true/false."
            ));
        }
    }

    private void altItemDespawnRateCobblestone(char id) {
        int value = this.paperWorld.getInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_COBBLESTONE);
        if (!isAltItemDespawnRateEnabled) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.WHITE_CONCRETE),
                    1,
                    click -> true,
                    ChatColor.WHITE + "Alt Item Despawn Rate - Cobblestone",
                    ChatColor.GRAY + "The Alt Item Despawn Rate feature is disabled.",
                    ChatColor.GRAY + "Please enable it to modify this value."
            ));
        } else if (value >= 300 && value <= 600) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_COBBLESTONE, click, value, false, false),
                    ChatColor.DARK_GREEN + "Alt Item Despawn Rate - Cobblestone",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value > 600) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_COBBLESTONE, click, value, false, false),
                    ChatColor.DARK_RED + "Alt Item Despawn Rate - Cobblestone",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_COBBLESTONE, click, value, false, false),
                    ChatColor.GOLD + "Alt Item Despawn Rate - Cobblestone",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.YELLOW + "Items may despawn too quickly.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void altItemDespawnRateNetherrack(char id) {
        int value = this.paperWorld.getInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_NETHERRACK);
        if (!isAltItemDespawnRateEnabled) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.WHITE_CONCRETE),
                    1,
                    click -> true,
                    ChatColor.WHITE + "Alt Item Despawn Rate - Netherrack",
                    ChatColor.GRAY + "The Alt Item Despawn Rate feature is disabled.",
                    ChatColor.GRAY + "Please enable it to modify this value."
            ));
        } else if (value >= 300 && value <= 600) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_NETHERRACK, click, value, false, false),
                    ChatColor.DARK_GREEN + "Alt Item Despawn Rate - Netherrack",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value > 600) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_NETHERRACK, click, value, false, false),
                    ChatColor.DARK_RED + "Alt Item Despawn Rate - Netherrack",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_NETHERRACK, click, value, false, false),
                    ChatColor.GOLD + "Alt Item Despawn Rate - Netherrack",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.YELLOW + "Items may despawn too quickly.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void altItemDespawnRateSand(char id) {
        int value = this.paperWorld.getInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_SAND);
        if (!isAltItemDespawnRateEnabled) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.WHITE_CONCRETE),
                    1,
                    click -> true,
                    ChatColor.WHITE + "Alt Item Despawn Rate - Sand",
                    ChatColor.GRAY + "The Alt Item Despawn Rate feature is disabled.",
                    ChatColor.GRAY + "Please enable it to modify this value."
            ));
        } else if (value >= 300 && value <= 600) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_SAND, click, value, false, false),
                    ChatColor.DARK_GREEN + "Alt Item Despawn Rate - Sand",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value > 600) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_SAND, click, value, false, false),
                    ChatColor.DARK_RED + "Alt Item Despawn Rate - Sand",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_SAND, click, value, false, false),
                    ChatColor.GOLD + "Alt Item Despawn Rate - Sand",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.YELLOW + "Items may despawn too quickly.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void altItemDespawnRateRedSand(char id) {
        int value = this.paperWorld.getInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_RED_SAND);
        if (!isAltItemDespawnRateEnabled) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.WHITE_CONCRETE),
                    1,
                    click -> true,
                    ChatColor.WHITE + "Alt Item Despawn Rate - Red Sand",
                    ChatColor.GRAY + "The Alt Item Despawn Rate feature is disabled.",
                    ChatColor.GRAY + "Please enable it to modify this value."
            ));
        } else if (value >= 300 && value <= 600) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_RED_SAND, click, value, false, false),
                    ChatColor.DARK_GREEN + "Alt Item Despawn Rate - Red Sand",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value > 600) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_RED_SAND, click, value, false, false),
                    ChatColor.DARK_RED + "Alt Item Despawn Rate - Red Sand",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_RED_SAND, click, value, false, false),
                    ChatColor.GOLD + "Alt Item Despawn Rate - Red Sand",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.YELLOW + "Items may despawn too quickly.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void altItemDespawnRateGravel(char id) {
        int value = this.paperWorld.getInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_GRAVEL);
        if (!isAltItemDespawnRateEnabled) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.WHITE_CONCRETE),
                    1,
                    click -> true,
                    ChatColor.WHITE + "Alt Item Despawn Rate - Gravel",
                    ChatColor.GRAY + "The Alt Item Despawn Rate feature is disabled.",
                    ChatColor.GRAY + "Please enable it to modify this value."
            ));
        } else if (value >= 300 && value <= 600) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_GRAVEL, click, value, false, false),
                    ChatColor.DARK_GREEN + "Alt Item Despawn Rate - Gravel",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value > 600) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_GRAVEL, click, value, false, false),
                    ChatColor.DARK_RED + "Alt Item Despawn Rate - Gravel",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_GRAVEL, click, value, false, false),
                    ChatColor.GOLD + "Alt Item Despawn Rate - Gravel",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.YELLOW + "Items may despawn too quickly.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private boolean setInt(PaperWorld.Key key, GuiElement.Click click, int value, boolean canBeDefault, boolean canBeNegativeOne) {
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
        } else if ((click.getType() == ClickType.LEFT || click.getType() == ClickType.SHIFT_LEFT) && value == 0 && canBeNegativeOne) {
            this.paperWorld.setInt(key, -1);
        }

        this.show();
        return true;
    }
}

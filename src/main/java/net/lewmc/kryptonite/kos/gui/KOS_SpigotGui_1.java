package net.lewmc.kryptonite.kos.gui;

import de.themoep.inventorygui.GuiElement;
import de.themoep.inventorygui.InventoryGui;
import de.themoep.inventorygui.StaticGuiElement;
import net.lewmc.kryptonite.Kryptonite;
import net.lewmc.kryptonite.kos.config.ServerProperties;
import net.lewmc.kryptonite.kos.config.Spigot;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

/**
 * KOS Spigot GUI (Page 1/2)
 */
public class KOS_SpigotGui_1 {
    private final Kryptonite plugin;
    private final CommandSender user;
    private final int simulationDistance;
    private InventoryGui gui;
    private final Spigot spigot;

    /**
     * Constructor for the KOS Spigot GUI (Page 1/2)
     * @param plugin Kryptonite - Reference to the main plugin class.
     * @param user CommandSender - The user who sent the command.
     */
    public KOS_SpigotGui_1(Kryptonite plugin, CommandSender user) {
        this.plugin = plugin;
        this.user = user;
        this.simulationDistance = (new ServerProperties(plugin).getInt(ServerProperties.Key.SIMULATION_DISTANCE) -1)*16;

        this.spigot = new Spigot(plugin, user);
    }

    /**
     * Shows the KOS Spigot GUI (Page 1/2)
     */
    public void show() {
        InventoryHolder holder = this.user.getServer().getPlayer(this.user.getName());
        this.gui = new InventoryGui(this.plugin, holder, "KOS - Spigot Configuration (1/2)", this.getElements());
        this.addElements();

        this.gui.build();
        this.gui.show((Player) this.user);
    }

    /**
     * Adds pre-programmed elements to the GUI
     */
    private void addElements() {
        this.viewDistance('a');
        this.mobSpawnRange('b');
        this.entityActivationRangeAnimals('c');
        this.entityActivationRangeMonsters('d');
        this.entityActivationRangeRaiders('e');
        this.entityActivationRangeMisc('f');
        this.entityActivationRangeWater('g');
        this.entityActivationRangeVillagers('h');
        this.entityActivationRangeFlyingMonsters('i');
        this.entityTrackingRangePlayers('j');
        this.entityTrackingRangeAnimals('k');
        this.entityTrackingRangeMonsters('l');
        this.entityTrackingRangeMisc('m');
        this.entityTrackingRangeOther('n');
        this.entityTrackingRangeDisplay('o');
        this.tickInactiveVillagers('p');
        this.nerfSpawnerMobs('q');
        this.ticksPerHopperTransfer('r');

        KOS_GuiConstants consts = new KOS_GuiConstants(this.plugin, this.gui);
        consts.addConstants();

        this.gui.addElement(new StaticGuiElement('z',
                    new ItemStack(Material.OAK_SIGN),
                1,
                click -> {
                    click.getGui().close();
                    KOS_SpigotGui_2 spigotGui2 = new KOS_SpigotGui_2(this.plugin, this.user);
                    spigotGui2.show();
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
                "  w x y z"
        };
    }

    private void viewDistance(char id) {
        Object value = this.spigot.getObject(Spigot.Key.VIEW_DISTANCE);
        if (value instanceof String) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(Spigot.Key.VIEW_DISTANCE, click, 0, true),
                    ChatColor.DARK_GREEN + "View Distance",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Ideal.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value instanceof Integer) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.WHITE_CONCRETE),
                    1,
                    click -> this.setInt(Spigot.Key.VIEW_DISTANCE, click, (int) value, true),
                    ChatColor.WHITE + "View Distance",
                    ChatColor.GRAY + String.valueOf(value),
                    ChatColor.GRAY + "This value is ambiguous with other configuration options.",
                    ChatColor.GRAY + "Recommended value is default (lowest value), and manage.",
                    ChatColor.GRAY + "through the Server configuration menu instead.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.BARRIER),
                    1,
                    click -> true,
                    ChatColor.DARK_RED + "Spawn Limit (Monsters)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Kryptonite cannot recognise this value, and therefore cannot edit it.",
                    ChatColor.RED + "Please send a screenshot of this error to github.com/lewmc/kryptonite"
            ));
        }
    }

    private void mobSpawnRange(char id) {
        int value = this.spigot.getInt(Spigot.Key.MOB_SPAWN_RANGE);
        if (value >= 3 && value <= 8) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(Spigot.Key.MOB_SPAWN_RANGE, click, value, false),
                    ChatColor.DARK_GREEN + "Mob Spawn Range",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value < 3) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(Spigot.Key.MOB_SPAWN_RANGE, click, value, false),
                    ChatColor.GOLD + "Mob Spawn Range",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(Spigot.Key.MOB_SPAWN_RANGE, click, value, false),
                    ChatColor.DARK_RED + "Mob Spawn Range",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void entityActivationRangeAnimals(char id) {
        int value = this.spigot.getInt(Spigot.Key.ENTITY_ACTIVATION_RANGE_ANIMALS);
        if (value >= 16 && value <= simulationDistance) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(Spigot.Key.ENTITY_ACTIVATION_RANGE_ANIMALS, click, value, false),
                    ChatColor.DARK_GREEN + "Entity Activation Range (Animals)",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value < 16) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(Spigot.Key.ENTITY_ACTIVATION_RANGE_ANIMALS, click, value, false),
                    ChatColor.GOLD + "Entity Activation Range (Animals)",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(Spigot.Key.ENTITY_ACTIVATION_RANGE_ANIMALS, click, value, false),
                    ChatColor.DARK_RED + "Entity Activation Range (Animals)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void entityActivationRangeMonsters(char id) {
        int value = this.spigot.getInt(Spigot.Key.ENTITY_ACTIVATION_RANGE_MONSTERS);
        if (value >= 16 && value <= simulationDistance) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(Spigot.Key.ENTITY_ACTIVATION_RANGE_MONSTERS, click, value, false),
                    ChatColor.DARK_GREEN + "Entity Activation Range (Monsters)",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value < 16) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(Spigot.Key.ENTITY_ACTIVATION_RANGE_MONSTERS, click, value, false),
                    ChatColor.GOLD + "Entity Activation Range (Monsters)",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(Spigot.Key.ENTITY_ACTIVATION_RANGE_MONSTERS, click, value, false),
                    ChatColor.DARK_RED + "Entity Activation Range (Monsters)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void entityActivationRangeRaiders(char id) {
        int value = this.spigot.getInt(Spigot.Key.ENTITY_ACTIVATION_RANGE_RAIDERS);
        if (value >= 16 && value <= simulationDistance) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(Spigot.Key.ENTITY_ACTIVATION_RANGE_RAIDERS, click, value, false),
                    ChatColor.DARK_GREEN + "Entity Activation Range (Raiders)",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value < 16) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(Spigot.Key.ENTITY_ACTIVATION_RANGE_RAIDERS, click, value, false),
                    ChatColor.GOLD + "Entity Activation Range (Raiders)",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(Spigot.Key.ENTITY_ACTIVATION_RANGE_RAIDERS, click, value, false),
                    ChatColor.DARK_RED + "Entity Activation Range (Raiders)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void entityActivationRangeMisc(char id) {
        int value = this.spigot.getInt(Spigot.Key.ENTITY_ACTIVATION_RANGE_MISC);
        if (value >= 16 && value <= simulationDistance) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(Spigot.Key.ENTITY_ACTIVATION_RANGE_MISC, click, value, false),
                    ChatColor.DARK_GREEN + "Entity Activation Range (Misc)",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value < 16) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(Spigot.Key.ENTITY_ACTIVATION_RANGE_MISC, click, value, false),
                    ChatColor.GOLD + "Entity Activation Range (Misc)",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(Spigot.Key.ENTITY_ACTIVATION_RANGE_MISC, click, value, false),
                    ChatColor.DARK_RED + "Entity Activation Range (Misc)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void entityActivationRangeWater(char id) {
        int value = this.spigot.getInt(Spigot.Key.ENTITY_ACTIVATION_RANGE_WATER);
        if (value >= 16 && value <= simulationDistance) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(Spigot.Key.ENTITY_ACTIVATION_RANGE_WATER, click, value, false),
                    ChatColor.DARK_GREEN + "Entity Activation Range (Water)",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value < 16) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(Spigot.Key.ENTITY_ACTIVATION_RANGE_WATER, click, value, false),
                    ChatColor.GOLD + "Entity Activation Range (Water)",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(Spigot.Key.ENTITY_ACTIVATION_RANGE_WATER, click, value, false),
                    ChatColor.DARK_RED + "Entity Activation Range (Water)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void entityActivationRangeVillagers(char id) {
        int value = this.spigot.getInt(Spigot.Key.ENTITY_ACTIVATION_RANGE_VILLAGERS);
        if (value >= 16 && value <= simulationDistance) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(Spigot.Key.ENTITY_ACTIVATION_RANGE_VILLAGERS, click, value, false),
                    ChatColor.DARK_GREEN + "Entity Activation Range (Villagers)",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value < 16) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(Spigot.Key.ENTITY_ACTIVATION_RANGE_VILLAGERS, click, value, false),
                    ChatColor.GOLD + "Entity Activation Range (Villagers)",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(Spigot.Key.ENTITY_ACTIVATION_RANGE_VILLAGERS, click, value, false),
                    ChatColor.DARK_RED + "Entity Activation Range (Villagers)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void entityActivationRangeFlyingMonsters(char id) {
        int value = this.spigot.getInt(Spigot.Key.ENTITY_ACTIVATION_RANGE_FLYING_MONSTERS);
        if (value >= 16 && value <= simulationDistance) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(Spigot.Key.ENTITY_ACTIVATION_RANGE_FLYING_MONSTERS, click, value, false),
                    ChatColor.DARK_GREEN + "Entity Activation Range (Flying Monsters)",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value < 16) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(Spigot.Key.ENTITY_ACTIVATION_RANGE_FLYING_MONSTERS, click, value, false),
                    ChatColor.GOLD + "Entity Activation Range (Flying Monsters)",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(Spigot.Key.ENTITY_ACTIVATION_RANGE_FLYING_MONSTERS, click, value, false),
                    ChatColor.DARK_RED + "Entity Activation Range (Flying Monsters)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void entityTrackingRangePlayers(char id) {
        int value = this.spigot.getInt(Spigot.Key.ENTITY_TRACKING_RANGE_PLAYERS);
        if (value >= 6 && value <= simulationDistance) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(Spigot.Key.ENTITY_TRACKING_RANGE_PLAYERS, click, value, false),
                    ChatColor.DARK_GREEN + "Entity Tracking Range (Players)",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value < 6) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(Spigot.Key.ENTITY_TRACKING_RANGE_PLAYERS, click, value, false),
                    ChatColor.GOLD + "Entity Tracking Range (Players)",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(Spigot.Key.ENTITY_TRACKING_RANGE_PLAYERS, click, value, false),
                    ChatColor.DARK_RED + "Entity Tracking Range (Players)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void entityTrackingRangeAnimals(char id) {
        int value = this.spigot.getInt(Spigot.Key.ENTITY_TRACKING_RANGE_ANIMALS);
        if (value >= 6 && value <= simulationDistance) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(Spigot.Key.ENTITY_TRACKING_RANGE_ANIMALS, click, value, false),
                    ChatColor.DARK_GREEN + "Entity Tracking Range (Animals)",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value < 6) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(Spigot.Key.ENTITY_TRACKING_RANGE_ANIMALS, click, value, false),
                    ChatColor.GOLD + "Entity Tracking Range (Animals)",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(Spigot.Key.ENTITY_TRACKING_RANGE_ANIMALS, click, value, false),
                    ChatColor.DARK_RED + "Entity Tracking Range (Animals)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void entityTrackingRangeMonsters(char id) {
        int value = this.spigot.getInt(Spigot.Key.ENTITY_TRACKING_RANGE_MONSTERS);
        if (value >= 6 && value <= simulationDistance) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(Spigot.Key.ENTITY_TRACKING_RANGE_MONSTERS, click, value, false),
                    ChatColor.DARK_GREEN + "Entity Tracking Range (Monsters)",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value < 6) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(Spigot.Key.ENTITY_TRACKING_RANGE_MONSTERS, click, value, false),
                    ChatColor.GOLD + "Entity Tracking Range (Monsters)",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(Spigot.Key.ENTITY_TRACKING_RANGE_MONSTERS, click, value, false),
                    ChatColor.DARK_RED + "Entity Tracking Range (Monsters)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void entityTrackingRangeMisc(char id) {
        int value = this.spigot.getInt(Spigot.Key.ENTITY_TRACKING_RANGE_MISC);
        if (value >= 6 && value <= simulationDistance) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(Spigot.Key.ENTITY_TRACKING_RANGE_MISC, click, value, false),
                    ChatColor.DARK_GREEN + "Entity Tracking Range (Misc)",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value < 6) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(Spigot.Key.ENTITY_TRACKING_RANGE_MISC, click, value, false),
                    ChatColor.GOLD + "Entity Tracking Range (Misc)",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(Spigot.Key.ENTITY_TRACKING_RANGE_MISC, click, value, false),
                    ChatColor.DARK_RED + "Entity Tracking Range (Misc)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void entityTrackingRangeOther(char id) {
        int value = this.spigot.getInt(Spigot.Key.ENTITY_TRACKING_RANGE_OTHER);
        if (value >= 6 && value <= simulationDistance) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(Spigot.Key.ENTITY_TRACKING_RANGE_OTHER, click, value, false),
                    ChatColor.DARK_GREEN + "Entity Tracking Range (Other)",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value < 6) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(Spigot.Key.ENTITY_TRACKING_RANGE_OTHER, click, value, false),
                    ChatColor.GOLD + "Entity Tracking Range (Other)",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(Spigot.Key.ENTITY_TRACKING_RANGE_OTHER, click, value, false),
                    ChatColor.DARK_RED + "Entity Tracking Range (Other)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void entityTrackingRangeDisplay(char id) {
        int value = this.spigot.getInt(Spigot.Key.ENTITY_TRACKING_RANGE_DISPLAY);
        if (value >= 6 && value <= simulationDistance) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(Spigot.Key.ENTITY_TRACKING_RANGE_DISPLAY, click, value, false),
                    ChatColor.DARK_GREEN + "Entity Tracking Range (Display)",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value < 6) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(Spigot.Key.ENTITY_TRACKING_RANGE_DISPLAY, click, value, false),
                    ChatColor.GOLD + "Entity Tracking Range (Display)",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(Spigot.Key.ENTITY_TRACKING_RANGE_DISPLAY, click, value, false),
                    ChatColor.DARK_RED + "Entity Tracking Range (Display)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void tickInactiveVillagers(char id) {
        boolean value = this.spigot.getBoolean(Spigot.Key.TICK_INACTIVE_VILLAGERS);
        if (value) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        this.spigot.setBoolean(Spigot.Key.TICK_INACTIVE_VILLAGERS, false);
                        this.show();
                        return true;
                    },
                    ChatColor.DARK_RED + "Tick Inactive Villagers",
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
                        this.spigot.setBoolean(Spigot.Key.TICK_INACTIVE_VILLAGERS, true);
                        this.show();
                        return true;
                    },
                    ChatColor.GOLD + "Tick Inactive Villagers",
                    ChatColor.YELLOW + "false",
                    ChatColor.YELLOW + "Impact to player experience - villagers and iron farms",
                    ChatColor.YELLOW + "may not work as expected without players nearby.",
                    ChatColor.BLUE + "Click to toggle true/false."
            ));
        }
    }

    private void nerfSpawnerMobs(char id) {
        boolean value = this.spigot.getBoolean(Spigot.Key.NERF_SPAWNER_MOBS);
        if (value) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        this.spigot.setBoolean(Spigot.Key.NERF_SPAWNER_MOBS, false);
                        this.show();
                        return true;
                    },
                    ChatColor.GOLD + "Nerf Spawner Mobs",
                    ChatColor.YELLOW + "false",
                    ChatColor.YELLOW + "Impact to player experience - mobs spawned by",
                    ChatColor.YELLOW + "spawners will have no AI and do nothing.",
                    ChatColor.BLUE + "Click to toggle true/false."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        this.spigot.setBoolean(Spigot.Key.NERF_SPAWNER_MOBS, true);
                        this.show();
                        return true;
                    },
                    ChatColor.DARK_RED + "Nerf Spawner Mobs",
                    ChatColor.RED + "true",
                    ChatColor.RED + "Impact to performance if players can move spawners.",
                    ChatColor.BLUE + "Click to toggle true/false."
            ));
        }
    }

    private void ticksPerHopperTransfer(char id) {
        int value = this.spigot.getInt(Spigot.Key.TICKS_PER_HOPPER_TRANSFER);
        if (value == 8) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(Spigot.Key.TICKS_PER_HOPPER_TRANSFER, click, value, false),
                    ChatColor.DARK_GREEN + "Ticks per Hopper Transfer",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Ideal value.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value > 8) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(Spigot.Key.TICKS_PER_HOPPER_TRANSFER, click, value, false),
                    ChatColor.GOLD + "Ticks per Hopper Transfer",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too high - impact to player experience.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(Spigot.Key.ENTITY_TRACKING_RANGE_DISPLAY, click, value, false),
                    ChatColor.DARK_RED + "Ticks per Hopper Transfer",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too low - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private boolean setInt(Spigot.Key key, GuiElement.Click click, int value, boolean canBeDefault) {
        click.getGui().close();
        if (click.getType() == ClickType.RIGHT) {
            this.spigot.setInt(key, value + 1);
        } else if (click.getType() == ClickType.SHIFT_RIGHT) {
            this.spigot.setInt(key, value + 10);
        } else if (click.getType() == ClickType.LEFT && value != 0) {
            this.spigot.setInt(key, value - 1);
        } else if (click.getType() == ClickType.SHIFT_LEFT && value >= 10) {
            this.spigot.setInt(key, value - 10);
        } else if ((click.getType() == ClickType.LEFT || click.getType() == ClickType.SHIFT_LEFT) && value == 0 && canBeDefault) {
            this.spigot.setString(key, "default");
        }

        this.show();
        return true;
    }
}

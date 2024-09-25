package net.lewmc.kryptonite.kos.gui;

import de.themoep.inventorygui.GuiElement;
import de.themoep.inventorygui.InventoryGui;
import de.themoep.inventorygui.StaticGuiElement;
import net.lewmc.kryptonite.Kryptonite;
import net.lewmc.kryptonite.kos.config.Purpur;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

/**
 * KOS Purpur GUI
 */
public class KOS_PurpurGui {
    private final Kryptonite plugin;
    private final CommandSender user;
    private final Purpur purpur;
    private InventoryGui gui;

    /**
     * Constructor for the KOS Purpur GUI
     * @param plugin Kryptonite - Reference to the main plugin class.
     * @param user CommandSender - The user who sent the command.
     */
    public KOS_PurpurGui(Kryptonite plugin, CommandSender user) {
        this.plugin = plugin;
        this.user = user;

        this.purpur = new Purpur(plugin, user);
    }

    /**
     * Shows the KOS Purpur GUI
     */
    public void show() {
        InventoryHolder holder = this.user.getServer().getPlayer(this.user.getName());
        this.gui = new InventoryGui(this.plugin, holder, "KOS - Purpur Configuration", this.getElements());
        this.addElements();

        this.gui.build();
        this.gui.show((Player) this.user);
    }

    /**
     * Adds pre-programmed elements to the GUI
     */
    private void addElements() {
        this.useAlternateKeepalive('a');
        this.zombieAggressiveTowardsVillagerWhenLagging('b');
        this.entitiesCanUsePortals('c');
        this.villagerIsLobotomized('d');
        this.villagerSearchRadiusAcquirePoi('e');
        this.villagerSearchRadiusNearestBedSensor('f');
        this.dolphinDisableTreasureSearching('g');
        this.teleportIfOutsideBorder('h');
        this.laggingThreshold('i');

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
                "         ",
                "  w x y  "
        };
    }

    private void useAlternateKeepalive(char id) {
        boolean value = this.purpur.getBoolean(Purpur.Key.USE_ALTERNATE_KEEPALIVE);
        if (value) {
            if (this.plugin.getConfig().getBoolean("using-tcpshield")) {
                this.gui.addElement(new StaticGuiElement(id,
                        new ItemStack(Material.ORANGE_CONCRETE),
                        1,
                        click -> {
                            click.getGui().close();
                            this.purpur.setBoolean(Purpur.Key.USE_ALTERNATE_KEEPALIVE, false);
                            this.show();
                            return true;
                        },
                        ChatColor.GOLD + "Use Alternate Keepalive",
                        ChatColor.YELLOW + "true",
                        ChatColor.YELLOW + "You've indicated that you're using TCPShield, this feature",
                        ChatColor.YELLOW + "has a known compatability issue and may not work correctly.",
                        ChatColor.BLUE + "Click to toggle true/false."
                ));
            } else {
                this.gui.addElement(new StaticGuiElement(id,
                        new ItemStack(Material.LIME_CONCRETE),
                        1,
                        click -> {
                            click.getGui().close();
                            this.purpur.setBoolean(Purpur.Key.USE_ALTERNATE_KEEPALIVE, false);
                            this.show();
                            return true;
                        },
                        ChatColor.DARK_GREEN + "Use Alternate Keepalive",
                        ChatColor.GREEN + "true",
                        ChatColor.GREEN + "Ideal value.",
                        ChatColor.BLUE + "Click to toggle true/false."
                ));
            }
        } else {
            if (this.plugin.getConfig().getBoolean("using-tcpshield")) {
                this.gui.addElement(new StaticGuiElement(id,
                        new ItemStack(Material.LIME_CONCRETE),
                        1,
                        click -> {
                            click.getGui().close();
                            this.purpur.setBoolean(Purpur.Key.USE_ALTERNATE_KEEPALIVE, true);
                            this.show();
                            return true;
                        },
                        ChatColor.DARK_GREEN + "Use Alternate Keepalive",
                        ChatColor.GREEN + "false",
                        ChatColor.GREEN + "Ideal value.",
                        ChatColor.GREEN + "You've indicated that you're using TCPShield, this feature",
                        ChatColor.GREEN + "has a known compatability issue and may not work correctly.",
                        ChatColor.BLUE + "Click to toggle true/false."
                ));
            } else {
                this.gui.addElement(new StaticGuiElement(id,
                        new ItemStack(Material.ORANGE_CONCRETE),
                        1,
                        click -> {
                            click.getGui().close();
                            this.purpur.setBoolean(Purpur.Key.USE_ALTERNATE_KEEPALIVE, true);
                            this.show();
                            return true;
                        },
                        ChatColor.GOLD + "Use Alternate Keepalive",
                        ChatColor.YELLOW + "false",
                        ChatColor.YELLOW + "Impact to player experience.",
                        ChatColor.YELLOW + "Enabling this prevents players from timing out as often.",
                        ChatColor.BLUE + "Click to toggle true/false."
                ));
            }
        }
    }

    private void zombieAggressiveTowardsVillagerWhenLagging(char id) {
        boolean value = this.purpur.getBoolean(Purpur.Key.ZOMBIE_AGGRESSIVE_TOWARDS_VILLAGER_WHEN_LAGGING);
        if (value) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        this.purpur.setBoolean(Purpur.Key.ZOMBIE_AGGRESSIVE_TOWARDS_VILLAGER_WHEN_LAGGING, false);
                        this.show();
                        return true;
                    },
                    ChatColor.GOLD + "Zombie Aggressive Towards Villager When Lagging",
                    ChatColor.YELLOW + "true",
                    ChatColor.YELLOW + "Impact to player experience - vanilla behaviour will be",
                    ChatColor.YELLOW + "lost if the server's TPS is below the lag threshold.",
                    ChatColor.BLUE + "Click to toggle true/false."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        this.purpur.setBoolean(Purpur.Key.ZOMBIE_AGGRESSIVE_TOWARDS_VILLAGER_WHEN_LAGGING, true);
                        this.show();
                        return true;
                    },
                    ChatColor.DARK_RED + "Zombie Aggressive Towards Villager When Lagging",
                    ChatColor.RED + "false",
                    ChatColor.RED + "Impact to performance.",
                    ChatColor.BLUE + "Click to toggle true/false."
            ));
        }
    }

    private void entitiesCanUsePortals(char id) {
        boolean value = this.purpur.getBoolean(Purpur.Key.ENTITIES_CAN_USE_PORTALS);
        if (value) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        this.purpur.setBoolean(Purpur.Key.ENTITIES_CAN_USE_PORTALS, false);
                        this.show();
                        return true;
                    },
                    ChatColor.DARK_RED + "Entities can use Portals",
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
                        this.purpur.setBoolean(Purpur.Key.ENTITIES_CAN_USE_PORTALS, true);
                        this.show();
                        return true;
                    },
                    ChatColor.GOLD + "Entities can use Portals",
                    ChatColor.YELLOW + "false",
                    ChatColor.YELLOW + "Impact to player experience - entities can",
                    ChatColor.YELLOW + "not use portals.",
                    ChatColor.BLUE + "Click to toggle true/false."
            ));
        }
    }

    private void villagerIsLobotomized(char id) {
        boolean value = this.purpur.getBoolean(Purpur.Key.VILLAGER_IS_LOBOTOMIZED);
        if (value) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        this.purpur.setBoolean(Purpur.Key.VILLAGER_IS_LOBOTOMIZED, false);
                        this.show();
                        return true;
                    },
                    ChatColor.DARK_RED + "Villagers are Lobotomized",
                    ChatColor.RED + "true",
                    ChatColor.RED + "Impact to performance - ONLY ENABLE THIS IF",
                    ChatColor.RED + "YOU'VE TRIED EVERYTHING ELSE TO REDUCE LAG.",
                    ChatColor.BLUE + "Click to toggle true/false."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        this.purpur.setBoolean(Purpur.Key.VILLAGER_IS_LOBOTOMIZED, true);
                        this.show();
                        return true;
                    },
                    ChatColor.GOLD + "Villagers are Lobotomized",
                    ChatColor.YELLOW + "false",
                    ChatColor.YELLOW + "Impact to player experience - villagers will have",
                    ChatColor.YELLOW + "no AI and not work as expected. ONLY ENABLE THIS",
                    ChatColor.YELLOW + "IF YOU'VE TRIED EVERYTHING ELSE TO REDUCE LAG.",
                    ChatColor.BLUE + "Click to toggle true/false."
            ));
        }
    }

    private void villagerSearchRadiusAcquirePoi(char id) {
        int value = this.purpur.getInt(Purpur.Key.VILLAGER_SEARCH_RADIUS_ACQUIRE_POI);
        if (value >= 16 && value <= 32) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(Purpur.Key.VILLAGER_SEARCH_RADIUS_ACQUIRE_POI, click, value, false),
                    ChatColor.DARK_GREEN + "Villager Search Radius (Acquire POI)",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value < 16) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(Purpur.Key.VILLAGER_SEARCH_RADIUS_ACQUIRE_POI, click, value, false),
                    ChatColor.GOLD + "Villager Search Radius (Acquire POI)",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Impact to player experience - villagers may",
                    ChatColor.YELLOW + "struggle to detect job sites or beds.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(Purpur.Key.VILLAGER_SEARCH_RADIUS_ACQUIRE_POI, click, value, false),
                    ChatColor.DARK_RED + "Villager Search Radius (Acquire POI)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void villagerSearchRadiusNearestBedSensor(char id) {
        int value = this.purpur.getInt(Purpur.Key.VILLAGER_SEARCH_RADIUS_NEAREST_BED_SENSOR);
        if (value >= 16 && value <= 32) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(Purpur.Key.VILLAGER_SEARCH_RADIUS_NEAREST_BED_SENSOR, click, value, false),
                    ChatColor.DARK_GREEN + "Villager Search Radius (Nearest Bed Sensor)",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value < 16) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(Purpur.Key.VILLAGER_SEARCH_RADIUS_NEAREST_BED_SENSOR, click, value, false),
                    ChatColor.GOLD + "Villager Search Radius (Nearest Bed Sensor)",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Impact to player experience - villagers may",
                    ChatColor.YELLOW + "struggle to detect beds.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(Purpur.Key.VILLAGER_SEARCH_RADIUS_NEAREST_BED_SENSOR, click, value, false),
                    ChatColor.DARK_RED + "Villager Search Radius (Nearest Bed Sensor)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void dolphinDisableTreasureSearching(char id) {
        boolean value = this.purpur.getBoolean(Purpur.Key.DOLPHIN_DISABLE_TREASURE_SEARCHING);
        if (value) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        this.purpur.setBoolean(Purpur.Key.DOLPHIN_DISABLE_TREASURE_SEARCHING, false);
                        this.show();
                        return true;
                    },
                    ChatColor.GOLD + "Dolphin Disable Treasure Searching",
                    ChatColor.YELLOW + "true",
                    ChatColor.YELLOW + "Impact to player experience - dolphins won't",
                    ChatColor.YELLOW + "perform structure searches.",
                    ChatColor.YELLOW + "Only enable if you have pre-generated your world.",
                    ChatColor.BLUE + "Click to toggle true/false."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        this.purpur.setBoolean(Purpur.Key.DOLPHIN_DISABLE_TREASURE_SEARCHING, true);
                        this.show();
                        return true;
                    },
                    ChatColor.DARK_RED + "Dolphin Disable Treasure Searching",
                    ChatColor.RED + "false",
                    ChatColor.RED + "Impact to performance.",
                    ChatColor.RED + "Only enable if you have pre-generated your world.",
                    ChatColor.BLUE + "Click to toggle true/false."
            ));
        }
    }

    private void teleportIfOutsideBorder(char id) {
        boolean value = this.purpur.getBoolean(Purpur.Key.TELEPORT_IF_OUTSIDE_BORDER);
        if (value) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        this.purpur.setBoolean(Purpur.Key.TELEPORT_IF_OUTSIDE_BORDER, false);
                        this.show();
                        return true;
                    },
                    ChatColor.DARK_GREEN + "Teleport if Outside Border",
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
                        this.purpur.setBoolean(Purpur.Key.TELEPORT_IF_OUTSIDE_BORDER, true);
                        this.show();
                        return true;
                    },
                    ChatColor.GOLD + "Teleport if Outside Border",
                    ChatColor.YELLOW + "false",
                    ChatColor.YELLOW + "Impact to player experience - may allow for exploits.",
                    ChatColor.BLUE + "Click to toggle true/false."
            ));
        }
    }

    private void laggingThreshold(char id) {
        int value = this.purpur.getInt(Purpur.Key.LAGGING_THRESHOLD);
        if (value > 15 && value < 20) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(Purpur.Key.LAGGING_THRESHOLD, click, value, false),
                    ChatColor.DARK_GREEN + "Lagging Threshold",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range - If player experience is",
                    ChatColor.GREEN + "being impacted you may wish to lower this.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value == 20) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(Purpur.Key.LAGGING_THRESHOLD, click, value, false),
                    ChatColor.GOLD + "Lagging Threshold",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Impact to player experience - this may break a",
                    ChatColor.YELLOW + "number of in-game functions.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(Purpur.Key.LAGGING_THRESHOLD, click, value, false),
                    ChatColor.DARK_RED + "Lagging Threshold",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too low - may cause more lag. If player experience is",
                    ChatColor.RED + "being impacted you may wish to lower this",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private boolean setInt(Purpur.Key key, GuiElement.Click click, int value, boolean canBeDefault) {
        click.getGui().close();
        if (click.getType() == ClickType.RIGHT) {
            this.purpur.setInt(key, value + 1);
        } else if (click.getType() == ClickType.SHIFT_RIGHT) {
            this.purpur.setInt(key, value + 10);
        } else if (click.getType() == ClickType.LEFT && value != 0) {
            this.purpur.setInt(key, value - 1);
        } else if (click.getType() == ClickType.SHIFT_LEFT && value >= 10) {
            this.purpur.setInt(key, value - 10);
        } else if ((click.getType() == ClickType.LEFT || click.getType() == ClickType.SHIFT_LEFT) && value == 0 && canBeDefault) {
            this.purpur.setString(key, "default");
        }

        this.show();
        return true;
    }
}

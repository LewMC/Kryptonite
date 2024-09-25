package net.lewmc.kryptonite.kos.gui;

import de.themoep.inventorygui.GuiElement;
import de.themoep.inventorygui.InventoryGui;
import de.themoep.inventorygui.StaticGuiElement;
import net.lewmc.kryptonite.Kryptonite;
import net.lewmc.kryptonite.kos.config.Pufferfish;
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
 * KOS Pufferfish GUI
 */
public class KOS_PufferfishGui {
    private final Kryptonite plugin;
    private final CommandSender user;
    private final int simulationDistance;
    private InventoryGui gui;
    private Pufferfish pufferfish;

    /**
     * Constructor for the KOS Pufferfish GUI
     * @param plugin Kryptonite - Reference to the main plugin class.
     * @param user CommandSender - The user who sent the command.
     */
    public KOS_PufferfishGui(Kryptonite plugin, CommandSender user) {
        this.plugin = plugin;
        this.user = user;
        this.simulationDistance = (new ServerProperties(plugin).getInt(ServerProperties.Key.SIMULATION_DISTANCE) -1)*16;

        this.pufferfish = new Pufferfish(plugin, user);
    }

    /**
     * Shows the KOS Pufferfish GUI
     */
    public void show() {
        InventoryHolder holder = this.user.getServer().getPlayer(this.user.getName());
        this.gui = new InventoryGui(this.plugin, holder, "KOS - Pufferfish Configuration", this.getElements());
        this.addElements();

        this.gui.build();
        this.gui.show((Player) this.user);
    }

    /**
     * Adds pre-programmed elements to the GUI
     */
    private void addElements() {
        this.maxLoadsPerProjectile('a');
        this.dabEnabled('b');
        this.dabMaxTickFreq('c');
        this.dabActivationDistMod('d');
        this.enableAsyncMobSpawning('e');
        this.enableSuffocationOptimization('f');
        this.inactiveGoalSelectorThrottle('g');
        this.disableMethodProfiler('h');

        KOS_GuiConstants consts = new KOS_GuiConstants(this.plugin, this.gui);
        consts.addConstants();
    }

    /**
     * Determines where the elements should be positioned.
     * @return String[] - The position of the elements.
     */
    private String[] getElements() {

        return new String[]{
                "abcdefgh ",
                "         ",
                "  w x y  "
        };
    }

    private void maxLoadsPerProjectile(char id) {
        int value = this.pufferfish.getInt(Pufferfish.Key.MAX_LOADS_PER_PROJECTILE);
        if (value >= 8 && value <= 12) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(Pufferfish.Key.MAX_LOADS_PER_PROJECTILE, click, value, false),
                    ChatColor.DARK_GREEN + "Max Loads per Projectile",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value < 8) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(Pufferfish.Key.MAX_LOADS_PER_PROJECTILE, click, value, false),
                    ChatColor.GOLD + "Max Loads per Projectile",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.YELLOW + "May cause issues with tridents and ender pearls.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(Pufferfish.Key.MAX_LOADS_PER_PROJECTILE, click, value, false),
                    ChatColor.DARK_RED + "Max Loads per Projectile",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.RED + "Hopper-based clocks and item sorting systems may also break.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void dabEnabled(char id) {
        boolean value = this.pufferfish.getBoolean(Pufferfish.Key.DAB_ENABLED);
        if (value) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        this.pufferfish.setBoolean(Pufferfish.Key.DAB_ENABLED, true);
                        this.show();
                        return true;
                    },
                    ChatColor.DARK_GREEN + "DAB Enabled",
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
                        this.pufferfish.setBoolean(Pufferfish.Key.DAB_ENABLED, true);
                        this.show();
                        return true;
                    },
                    ChatColor.DARK_RED + "Max Loads per Projectile",
                    ChatColor.RED + "false",
                    ChatColor.RED + "Impact to performance.",
                    ChatColor.BLUE + "Click to toggle true/false."
            ));
        }
    }

    private void dabMaxTickFreq(char id) {
        int value = this.pufferfish.getInt(Pufferfish.Key.DAB_MAX_TICK_FREQ);
        if (value == 20) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(Pufferfish.Key.DAB_MAX_TICK_FREQ, click, value, false),
                    ChatColor.DARK_GREEN + "DAB Max Tick Frequency",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Ideal value.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value < 20) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(Pufferfish.Key.DAB_MAX_TICK_FREQ, click, value, false),
                    ChatColor.DARK_RED + "DAB Max Tick Frequency",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too low - impact to performance.",
                    ChatColor.RED + "If DAB is breaking mob farms, having this decreased will",
                    ChatColor.RED + "improve your player experience, but may cost performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(Pufferfish.Key.DAB_MAX_TICK_FREQ, click, value, false),
                    ChatColor.GOLD + "DAB Max Tick Frequency",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too high - impact to player experience.",
                    ChatColor.YELLOW + "May break mob farms or nerf mob behaviour.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void dabActivationDistMod(char id) {
        int value = this.pufferfish.getInt(Pufferfish.Key.DAB_ACTIVATION_DIST_MOD);
        if (value == 8 || value == 7) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(Pufferfish.Key.DAB_ACTIVATION_DIST_MOD, click, value, false),
                    ChatColor.DARK_GREEN + "DAB Activation Distance Modifier",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value > 8) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(Pufferfish.Key.DAB_ACTIVATION_DIST_MOD, click, value, false),
                    ChatColor.DARK_RED + "DAB Activation Distance Modifier",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.RED + "If DAB is breaking mob farms, having this increased will",
                    ChatColor.RED + "improve your player experience, but may cost performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(Pufferfish.Key.DAB_ACTIVATION_DIST_MOD, click, value, false),
                    ChatColor.GOLD + "DAB Activation Distance Modifier",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.YELLOW + "May break mob farms or nerf mob behaviour.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void enableAsyncMobSpawning(char id) {
        boolean value = this.pufferfish.getBoolean(Pufferfish.Key.ENABLE_ASYNC_MOB_SPAWNING);
        if (value) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        this.pufferfish.setBoolean(Pufferfish.Key.ENABLE_ASYNC_MOB_SPAWNING, true);
                        this.show();
                        return true;
                    },
                    ChatColor.DARK_GREEN + "Enable Async Mob Spawning",
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
                        this.pufferfish.setBoolean(Pufferfish.Key.ENABLE_ASYNC_MOB_SPAWNING, true);
                        this.show();
                        return true;
                    },
                    ChatColor.DARK_RED + "Enable Async Mob Spawning",
                    ChatColor.RED + "false",
                    ChatColor.RED + "Impact to performance.",
                    ChatColor.BLUE + "Click to toggle true/false."
            ));
        }
    }

    private void enableSuffocationOptimization(char id) {
        boolean value = this.pufferfish.getBoolean(Pufferfish.Key.ENABLE_SUFFOCATION_OPTIMIZATION);
        if (value) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        this.pufferfish.setBoolean(Pufferfish.Key.ENABLE_SUFFOCATION_OPTIMIZATION, true);
                        this.show();
                        return true;
                    },
                    ChatColor.DARK_GREEN + "Enable Suffocation Optimization",
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
                        this.pufferfish.setBoolean(Pufferfish.Key.ENABLE_SUFFOCATION_OPTIMIZATION, true);
                        this.show();
                        return true;
                    },
                    ChatColor.DARK_RED + "Enable Suffocation Optimization",
                    ChatColor.RED + "false",
                    ChatColor.RED + "Impact to performance.",
                    ChatColor.BLUE + "Click to toggle true/false."
            ));
        }
    }

    private void inactiveGoalSelectorThrottle(char id) {
        boolean value = this.pufferfish.getBoolean(Pufferfish.Key.INACTIVE_GOAL_SELECTOR_THROTTLE);
        if (value) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        this.pufferfish.setBoolean(Pufferfish.Key.INACTIVE_GOAL_SELECTOR_THROTTLE, true);
                        this.show();
                        return true;
                    },
                    ChatColor.GOLD + "Inactive Goal Selector Throttle",
                    ChatColor.YELLOW + "true",
                    ChatColor.YELLOW + "Impact to player experience.",
                    ChatColor.YELLOW + "May have minor gameplay implications.",
                    ChatColor.BLUE + "Click to toggle true/false."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        this.pufferfish.setBoolean(Pufferfish.Key.INACTIVE_GOAL_SELECTOR_THROTTLE, true);
                        this.show();
                        return true;
                    },
                    ChatColor.DARK_RED + "Inactive Goal Selector Throttle",
                    ChatColor.RED + "false",
                    ChatColor.RED + "Impact to performance.",
                    ChatColor.BLUE + "Click to toggle true/false."
            ));
        }
    }

    private void disableMethodProfiler(char id) {
            boolean value = this.pufferfish.getBoolean(Pufferfish.Key.DISABLE_METHOD_PROFILER);
        if (value) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        this.pufferfish.setBoolean(Pufferfish.Key.DISABLE_METHOD_PROFILER, true);
                        this.show();
                        return true;
                    },
                    ChatColor.DARK_GREEN + "Disable Method Profiler",
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
                        this.pufferfish.setBoolean(Pufferfish.Key.DISABLE_METHOD_PROFILER, true);
                        this.show();
                        return true;
                    },
                    ChatColor.DARK_RED + "Disable Method Profiler",
                    ChatColor.RED + "false",
                    ChatColor.RED + "Impact to performance - this is not necessary in",
                    ChatColor.RED + "production environments.",
                    ChatColor.BLUE + "Click to toggle true/false."
            ));
        }
    }

    private boolean setInt(Pufferfish.Key key, GuiElement.Click click, int value, boolean canBeDefault) {
        click.getGui().close();
        if (click.getType() == ClickType.RIGHT) {
            this.pufferfish.setInt(key, value + 1);
        } else if (click.getType() == ClickType.SHIFT_RIGHT) {
            this.pufferfish.setInt(key, value + 10);
        } else if (click.getType() == ClickType.LEFT && value != 0) {
            this.pufferfish.setInt(key, value - 1);
        } else if (click.getType() == ClickType.SHIFT_LEFT && value >= 10) {
            this.pufferfish.setInt(key, value - 10);
        } else if ((click.getType() == ClickType.LEFT || click.getType() == ClickType.SHIFT_LEFT) && value == 0 && canBeDefault) {
            this.pufferfish.setString(key, "default");
        }

        this.show();
        return true;
    }
}

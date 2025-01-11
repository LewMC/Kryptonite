package net.lewmc.kryptonite.kos.gui;

import de.themoep.inventorygui.GuiElement;
import de.themoep.inventorygui.InventoryGui;
import de.themoep.inventorygui.StaticGuiElement;
import net.lewmc.kryptonite.Kryptonite;
import net.lewmc.kryptonite.kos.config.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

/**
 * KOS Bukkit GUI
 */
public class KOS_BukkitGui {
    private final Kryptonite plugin;
    private final CommandSender user;
    private InventoryGui gui;
    private Bukkit bukkit;

    /**
     * Constructor for the KOS Bukkit GUI
     * @param plugin Kryptonite - Reference to the main plugin class.
     * @param user CommandSender - The user who sent the command.
     */
    public KOS_BukkitGui(Kryptonite plugin, CommandSender user) {
        this.plugin = plugin;
        this.user = user;

        this.bukkit = new Bukkit(plugin, user);
    }

    /**
     * Shows the KOS Bukkit GUI
     */
    public void show() {
        InventoryHolder holder = this.user.getServer().getPlayer(this.user.getName());
        this.gui = new InventoryGui(this.plugin, holder, "KOS - Bukkit Configuration", this.getElements());
        this.addElements();

        this.gui.build();
        this.gui.show((Player) this.user);
    }

    /**
     * Adds pre-programmed elements to the GUI
     */
    private void addElements() {
        this.spawnLimitMonsters('a');
        this.spawnLimitAnimals('b');
        this.spawnLimitWaterAnimals('c');
        this.spawnLimitWaterAmbient('d');
        this.spawnLimitWaterUndergroundCreature('e');
        this.spawnLimitAxolotls('f');
        this.spawnLimitAmbient('g');
        this.ticksPerMonsterSpawns('h');
        this.ticksPerAnimalSpawns('i');

        this.ticksPerWaterSpawns('j');
        this.ticksPerWaterAmbientSpawns('k');
        this.ticksPerWaterUndergroundCreatureSpawns('l');
        this.ticksPerAxolotlSpawns('m');
        this.ticksPerAmbientSpawns('n');
        this.chunkGcPeriodInTicks('o');

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
                "jklmno   ",
                "  w x y  "
        };
    }

    private void spawnLimitMonsters(char id) {
        int value = this.bukkit.getInt(Bukkit.Key.SPAWN_LIMITS_MONSTERS);
        if (value >= 20 && value <= 60) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(Bukkit.Key.SPAWN_LIMITS_MONSTERS, click, value),
                    ChatColor.DARK_GREEN + "Spawn Limit (Monsters)",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value < 20) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(Bukkit.Key.SPAWN_LIMITS_MONSTERS, click, value),
                    ChatColor.GOLD + "Spawn Limit (Monsters)",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(Bukkit.Key.SPAWN_LIMITS_MONSTERS, click, value),
                    ChatColor.DARK_RED + "Spawn Limit (Monsters)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void spawnLimitAnimals(char id) {
        int value = this.bukkit.getInt(Bukkit.Key.SPAWN_LIMITS_ANIMALS);
        if (value >= 5 && value <= 10) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(Bukkit.Key.SPAWN_LIMITS_ANIMALS, click, value),
                    ChatColor.DARK_GREEN + "Spawn Limit (Animals)",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value < 5) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(Bukkit.Key.SPAWN_LIMITS_ANIMALS, click, value),
                    ChatColor.GOLD + "Spawn Limit (Animals)",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(Bukkit.Key.SPAWN_LIMITS_ANIMALS, click, value),
                    ChatColor.DARK_RED + "Spawn Limit (Animals)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void spawnLimitWaterAnimals(char id) {
        int value = this.bukkit.getInt(Bukkit.Key.SPAWN_LIMITS_WATER_ANIMALS);
        if (value >= 2 && value <= 5) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(Bukkit.Key.SPAWN_LIMITS_WATER_ANIMALS, click, value),
                    ChatColor.DARK_GREEN + "Spawn Limit (Water Animals)",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value < 2) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(Bukkit.Key.SPAWN_LIMITS_WATER_ANIMALS, click, value),
                    ChatColor.GOLD + "Spawn Limit (Water Animals)",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(Bukkit.Key.SPAWN_LIMITS_WATER_ANIMALS, click, value),
                    ChatColor.DARK_RED + "Spawn Limit (Water Animals)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void spawnLimitWaterAmbient(char id) {
        int value = this.bukkit.getInt(Bukkit.Key.SPAWN_LIMITS_WATER_AMBIENT);
        if (value >= 2 && value <= 10) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(Bukkit.Key.SPAWN_LIMITS_WATER_AMBIENT, click, value),
                    ChatColor.DARK_GREEN + "Spawn Limit (Water Ambient)",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value < 2) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(Bukkit.Key.SPAWN_LIMITS_WATER_AMBIENT, click, value),
                    ChatColor.GOLD + "Spawn Limit (Water Ambient)",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(Bukkit.Key.SPAWN_LIMITS_WATER_AMBIENT, click, value),
                    ChatColor.DARK_RED + "Spawn Limit (Water Ambient)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void spawnLimitWaterUndergroundCreature(char id) {
        int value = this.bukkit.getInt(Bukkit.Key.SPAWN_LIMITS_WATER_UNDERGROUND_CREATURE);
        if (value >= 3 && value <= 5) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(Bukkit.Key.SPAWN_LIMITS_WATER_UNDERGROUND_CREATURE, click, value),
                    ChatColor.DARK_GREEN + "Spawn Limit (Water Ambient)",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value < 3) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(Bukkit.Key.SPAWN_LIMITS_WATER_UNDERGROUND_CREATURE, click, value),
                    ChatColor.GOLD + "Spawn Limit (Water Ambient)",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(Bukkit.Key.SPAWN_LIMITS_WATER_UNDERGROUND_CREATURE, click, value),
                    ChatColor.DARK_RED + "Spawn Limit (Water Ambient)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void spawnLimitAxolotls(char id) {
        int value = this.bukkit.getInt(Bukkit.Key.SPAWN_LIMITS_AXOLOTLS);
        if (value >= 3 && value <= 5) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(Bukkit.Key.SPAWN_LIMITS_AXOLOTLS, click, value),
                    ChatColor.DARK_GREEN + "Spawn Limit (Axolotls)",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value < 3) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(Bukkit.Key.SPAWN_LIMITS_AXOLOTLS, click, value),
                    ChatColor.GOLD + "Spawn Limit (Axolotls)",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(Bukkit.Key.SPAWN_LIMITS_AXOLOTLS, click, value),
                    ChatColor.DARK_RED + "Spawn Limit (Axolotls)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void spawnLimitAmbient(char id) {
        int value = this.bukkit.getInt(Bukkit.Key.SPAWN_LIMITS_AMBIENT);
        if (value >= 1 && value <= 10) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(Bukkit.Key.SPAWN_LIMITS_AMBIENT, click, value),
                    ChatColor.DARK_GREEN + "Spawn Limit (Ambient)",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value < 1) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(Bukkit.Key.SPAWN_LIMITS_AMBIENT, click, value),
                    ChatColor.GOLD + "Spawn Limit (Ambient)",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(Bukkit.Key.SPAWN_LIMITS_AMBIENT, click, value),
                    ChatColor.DARK_RED + "Spawn Limit (Ambient)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void ticksPerMonsterSpawns(char id) {
        int value = this.bukkit.getInt(Bukkit.Key.TICKS_PER_MONSTER_SPAWNS);
        if (value >= 5 && value <= 15) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(Bukkit.Key.TICKS_PER_MONSTER_SPAWNS, click, value),
                    ChatColor.DARK_GREEN + "Ticks Per Monster Spawns",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value < 5) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(Bukkit.Key.TICKS_PER_MONSTER_SPAWNS, click, value),
                    ChatColor.DARK_RED + "Ticks Per Monster Spawns",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too low - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(Bukkit.Key.TICKS_PER_MONSTER_SPAWNS, click, value),
                    ChatColor.GOLD + "Ticks Per Monster Spawns",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too high - impact to player experience.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void ticksPerAnimalSpawns(char id) {
        int value = this.bukkit.getInt(Bukkit.Key.TICKS_PER_ANIMAL_SPAWNS);
        if (value == 400) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(Bukkit.Key.TICKS_PER_ANIMAL_SPAWNS, click, value),
                    ChatColor.DARK_GREEN + "Ticks Per Monster Spawns",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Ideal value.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value < 400) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(Bukkit.Key.TICKS_PER_ANIMAL_SPAWNS, click, value),
                    ChatColor.DARK_RED + "Ticks Per Monster Spawns",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too low - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(Bukkit.Key.TICKS_PER_ANIMAL_SPAWNS, click, value),
                    ChatColor.GOLD + "Ticks Per Monster Spawns",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too high - impact to player experience.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void ticksPerWaterSpawns(char id) {
        int value = this.bukkit.getInt(Bukkit.Key.TICKS_PER_WATER_SPAWNS);
        if (value >= 100 && value <= 400) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(Bukkit.Key.TICKS_PER_WATER_SPAWNS, click, value),
                    ChatColor.DARK_GREEN + "Ticks Per Water Spawns",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value < 5) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(Bukkit.Key.TICKS_PER_WATER_SPAWNS, click, value),
                    ChatColor.DARK_RED + "Ticks Per Water Spawns",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too low - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(Bukkit.Key.TICKS_PER_WATER_SPAWNS, click, value),
                    ChatColor.GOLD + "Ticks Per Water Spawns",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too high - impact to player experience.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void ticksPerWaterAmbientSpawns(char id) {
        int value = this.bukkit.getInt(Bukkit.Key.TICKS_PER_WATER_AMBIENT_SPAWNS);
        if (value >= 100 && value <= 400) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(Bukkit.Key.TICKS_PER_WATER_AMBIENT_SPAWNS, click, value),
                    ChatColor.DARK_GREEN + "Ticks Per Water Ambient Spawns",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value < 5) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(Bukkit.Key.TICKS_PER_WATER_AMBIENT_SPAWNS, click, value),
                    ChatColor.DARK_RED + "Ticks Per Water Ambient Spawns",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too low - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(Bukkit.Key.TICKS_PER_WATER_AMBIENT_SPAWNS, click, value),
                    ChatColor.GOLD + "Ticks Per Water Ambient Spawns",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too high - impact to player experience.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void ticksPerWaterUndergroundCreatureSpawns(char id) {
        int value = this.bukkit.getInt(Bukkit.Key.TICKS_PER_WATER_UNDERGROUND_CREATURE_SPAWNS);
        if (value >= 100 && value <= 400) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(Bukkit.Key.TICKS_PER_WATER_UNDERGROUND_CREATURE_SPAWNS, click, value),
                    ChatColor.DARK_GREEN + "Ticks Per Water Ambient Spawns",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value < 5) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(Bukkit.Key.TICKS_PER_WATER_UNDERGROUND_CREATURE_SPAWNS, click, value),
                    ChatColor.DARK_RED + "Ticks Per Water Ambient Spawns",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too low - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(Bukkit.Key.TICKS_PER_WATER_UNDERGROUND_CREATURE_SPAWNS, click, value),
                    ChatColor.GOLD + "Ticks Per Water Ambient Spawns",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too high - impact to player experience.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void ticksPerAxolotlSpawns(char id) {
        int value = this.bukkit.getInt(Bukkit.Key.TICKS_PER_AXOLOTL_SPAWNS);
        if (value >= 100 && value <= 400) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(Bukkit.Key.TICKS_PER_AXOLOTL_SPAWNS, click, value),
                    ChatColor.DARK_GREEN + "Ticks Per Axolotl Spawns",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value < 5) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(Bukkit.Key.TICKS_PER_AXOLOTL_SPAWNS, click, value),
                    ChatColor.DARK_RED + "Ticks Per Axolotl Spawns",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too low - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(Bukkit.Key.TICKS_PER_AXOLOTL_SPAWNS, click, value),
                    ChatColor.GOLD + "Ticks Per Axolotl Spawns",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too high - impact to player experience.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void ticksPerAmbientSpawns(char id) {
        int value = this.bukkit.getInt(Bukkit.Key.TICKS_PER_AMBIENT_SPAWNS);
        if (value >= 100 && value <= 400) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(Bukkit.Key.TICKS_PER_AMBIENT_SPAWNS, click, value),
                    ChatColor.DARK_GREEN + "Ticks Per Ambient Spawns",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value < 5) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(Bukkit.Key.TICKS_PER_AMBIENT_SPAWNS, click, value),
                    ChatColor.DARK_RED + "Ticks Per Ambient Spawns",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too low - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(Bukkit.Key.TICKS_PER_AMBIENT_SPAWNS, click, value),
                    ChatColor.GOLD + "Ticks Per Ambient Spawns",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too high - impact to player experience.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void chunkGcPeriodInTicks(char id) {
        int value = this.bukkit.getInt(Bukkit.Key.CHUNK_GC_PERIOD_IN_TICKS);
        if (value >= 400 && value <= 600) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(Bukkit.Key.CHUNK_GC_PERIOD_IN_TICKS, click, value),
                    ChatColor.DARK_GREEN + "Chunk GC Period (In Ticks)",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value < 5) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(Bukkit.Key.CHUNK_GC_PERIOD_IN_TICKS, click, value),
                    ChatColor.DARK_RED + "Chunk GC Period (In Ticks)",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(Bukkit.Key.CHUNK_GC_PERIOD_IN_TICKS, click, value),
                    ChatColor.DARK_RED + "Chunk GC Period (In Ticks)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private boolean setInt(Bukkit.Key key, GuiElement.Click click, int value) {
        click.getGui().close();
        if (click.getType() == ClickType.RIGHT) {
            this.bukkit.setInt(key, value + 1);
        } else if (click.getType() == ClickType.SHIFT_RIGHT) {
            this.bukkit.setInt(key, value + 10);
        } else if (click.getType() == ClickType.LEFT && value != 0) {
            this.bukkit.setInt(key, value - 1);
        } else if (click.getType() == ClickType.SHIFT_LEFT && value >= 10) {
            this.bukkit.setInt(key, value - 10);
        }

        this.show();
        return true;
    }
}

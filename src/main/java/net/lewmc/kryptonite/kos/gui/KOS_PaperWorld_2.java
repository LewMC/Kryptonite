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
 * KOS Paper World GUI (Page 2/5)
 * @deprecated
 */
@Deprecated
public class KOS_PaperWorld_2 {
    private final Kryptonite plugin;
    private final CommandSender user;
    private final PaperWorld paperWorld;
    private InventoryGui gui;

    /**
     * Constructor for the KOS Paper World GUI (Page 2/5)
     * @param plugin Kryptonite - Reference to the main plugin class.
     * @param user CommandSender - The user who sent the command.
     */
    public KOS_PaperWorld_2(Kryptonite plugin, CommandSender user) {
        this.plugin = plugin;
        this.user = user;
        this.paperWorld = new PaperWorld(plugin, user);
    }

    /**
     * Shows the KOS Paper World GUI (Page 2/5)
     */
    public void show() {
        InventoryHolder holder = this.user.getServer().getPlayer(this.user.getName());
        this.gui = new InventoryGui(this.plugin, holder, "KOS - Paper World Configuration (2/5)", this.getElements());
        this.addElements();

        this.gui.build();
        this.gui.show((Player) this.user);
    }

    /**
     * Adds pre-programmed elements to the GUI
     */
    private void addElements() {
        this.entityPerChunkSaveLimitTrident('a');
        this.entityPerChunkSaveLimitWitherSkull('b');
        this.entityDespawnRangesAmbientHard('c');
        this.entityDespawnRangesAmbientSoft('d');
        this.entityDespawnRangesAxolotlsHard('e');
        this.entityDespawnRangesAxolotlsSoft('f');
        this.entityDespawnRangesCreatureHard('g');
        this.entityDespawnRangesCreatureSoft('h');
        this.entityDespawnRangesMiscHard('i');
        this.entityDespawnRangesMiscSoft('j');
        this.entityDespawnRangesMonsterHard('k');
        this.entityDespawnRangesMonsterSoft('l');
        this.entityDespawnRangesUndergroundWaterCreatureHard('m');
        this.entityDespawnRangesUndergroundWaterCreatureSoft('n');
        this.entityDespawnRangesWaterAmbientHard('o');
        this.entityDespawnRangesWaterAmbientSoft('p');
        this.entityDespawnRangesWaterCreatureHard('q');
        this.entityDespawnRangesWaterCreatureSoft('r');

        KOS_GuiConstants consts = new KOS_GuiConstants(this.plugin, this.gui);
        consts.addConstants();

        this.gui.addElement(new StaticGuiElement('v',
                    new ItemStack(Material.OAK_SIGN),
                1,
                click -> {
                    click.getGui().close();
                    KOS_PaperWorld_1 prevGui = new KOS_PaperWorld_1(this.plugin, this.user);
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
                    KOS_PaperWorld_3 nextGui = new KOS_PaperWorld_3(this.plugin, this.user);
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

    private void entityPerChunkSaveLimitTrident(char id) {
        int value = this.paperWorld.getInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_TRIDENT);
        if (value < 11) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_TRIDENT, click, value, false),
                    ChatColor.DARK_GREEN + "Entity Per Chunk Save Limit (Trident)",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_TRIDENT, click, value, false),
                    ChatColor.DARK_RED + "Entity Per Chunk Save Limit (Trident)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to chunk loading performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void entityPerChunkSaveLimitWitherSkull(char id) {
        int value = this.paperWorld.getInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_TRIDENT);
        if (value < 11) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_WITHER_SKULL, click, value, false),
                    ChatColor.DARK_GREEN + "Entity Per Chunk Save Limit (Wither Skull)",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_WITHER_SKULL, click, value, false),
                    ChatColor.DARK_RED + "Entity Per Chunk Save Limit (Wither Skull)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to chunk loading performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void entityDespawnRangesAmbientHard(char id) {
        Object value = this.paperWorld.getObject(PaperWorld.Key.ENTITY_DESPAWN_RANGES_AMBIENT_HARD);
        if (value instanceof String) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_AMBIENT_HARD, click, 0, true),
                    ChatColor.DARK_GREEN + "Entity Despawn Ranges - Ambient (Hard)",
                    ChatColor.GREEN + "default (128)",
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value instanceof Integer && (int) value <= 20) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_AMBIENT_HARD, click, (int) value, true),
                    ChatColor.GOLD + "Entity Despawn Ranges - Ambient (Hard)",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value instanceof Integer && (int) value <= 128) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_AMBIENT_HARD, click, (int) value, true),
                    ChatColor.DARK_GREEN + "Entity Despawn Ranges - Ambient (Hard)",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value instanceof Integer) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_AMBIENT_HARD, click, (int) value, true),
                    ChatColor.DARK_RED + "Entity Despawn Ranges - Ambient (Hard)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.BARRIER),
                    1,
                    click -> true,
                    ChatColor.DARK_RED + "Entity Despawn Ranges - Ambient (Hard)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Kryptonite cannot recognise this value, and therefore cannot edit it.",
                    ChatColor.RED + "Please send a screenshot of this error to github.com/lewmc/kryptonite"
            ));
        }
    }

    private void entityDespawnRangesAmbientSoft(char id) {
        Object value = this.paperWorld.getObject(PaperWorld.Key.ENTITY_DESPAWN_RANGES_AMBIENT_SOFT);
        if (value instanceof String) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_AMBIENT_SOFT, click, 0, true),
                    ChatColor.DARK_GREEN + "Entity Despawn Ranges - Ambient (Soft)",
                    ChatColor.GREEN + "default (32)",
                    ChatColor.GREEN + "Ideal value.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value instanceof Integer && (int) value <= 10) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_AMBIENT_SOFT, click, (int) value, true),
                    ChatColor.GOLD + "Entity Despawn Ranges - Ambient (Soft)",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value instanceof Integer && (int) value <= 32) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_AMBIENT_SOFT, click, (int) value, true),
                    ChatColor.DARK_GREEN + "Entity Despawn Ranges - Ambient (Soft)",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value instanceof Integer) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_AMBIENT_SOFT, click, (int) value, true),
                    ChatColor.DARK_RED + "Entity Despawn Ranges - Ambient (Soft)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.BARRIER),
                    1,
                    click -> true,
                    ChatColor.DARK_RED + "Entity Despawn Ranges - Ambient (Soft)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Kryptonite cannot recognise this value, and therefore cannot edit it.",
                    ChatColor.RED + "Please send a screenshot of this error to github.com/lewmc/kryptonite"
            ));
        }
    }

    private void entityDespawnRangesAxolotlsHard(char id) {
        Object value = this.paperWorld.getObject(PaperWorld.Key.ENTITY_DESPAWN_RANGES_AXOLOTLS_HARD);
        if (value instanceof String) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_AXOLOTLS_HARD, click, 0, true),
                    ChatColor.DARK_GREEN + "Entity Despawn Ranges - Axolotls (Hard)",
                    ChatColor.GREEN + "default (128)",
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value instanceof Integer && (int) value <= 20) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_AXOLOTLS_HARD, click, (int) value, true),
                    ChatColor.GOLD + "Entity Despawn Ranges - Axolotls (Hard)",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value instanceof Integer && (int) value <= 128) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_AXOLOTLS_HARD, click, (int) value, true),
                    ChatColor.DARK_GREEN + "Entity Despawn Ranges - Axolotls (Hard)",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value instanceof Integer) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_AXOLOTLS_HARD, click, (int) value, true),
                    ChatColor.DARK_RED + "Entity Despawn Ranges - Axolotls (Hard)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.BARRIER),
                    1,
                    click -> true,
                    ChatColor.DARK_RED + "Entity Despawn Ranges - Axolotls (Hard)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Kryptonite cannot recognise this value, and therefore cannot edit it.",
                    ChatColor.RED + "Please send a screenshot of this error to github.com/lewmc/kryptonite"
            ));
        }
    }

    private void entityDespawnRangesAxolotlsSoft(char id) {
        Object value = this.paperWorld.getObject(PaperWorld.Key.ENTITY_DESPAWN_RANGES_AXOLOTLS_SOFT);
        if (value instanceof String) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_AXOLOTLS_SOFT, click, 0, true),
                    ChatColor.DARK_GREEN + "Entity Despawn Ranges - Axolotls (Soft)",
                    ChatColor.GREEN + "default (32)",
                    ChatColor.GREEN + "Ideal value.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value instanceof Integer && (int) value <= 10) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_AXOLOTLS_SOFT, click, (int) value, true),
                    ChatColor.GOLD + "Entity Despawn Ranges - Axolotls (Soft)",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value instanceof Integer && (int) value <= 32) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_AXOLOTLS_SOFT, click, (int) value, true),
                    ChatColor.DARK_GREEN + "Entity Despawn Ranges - Axolotls (Soft)",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value instanceof Integer) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_AXOLOTLS_SOFT, click, (int) value, true),
                    ChatColor.DARK_RED + "Entity Despawn Ranges - Axolotls (Soft)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.BARRIER),
                    1,
                    click -> true,
                    ChatColor.DARK_RED + "Entity Despawn Ranges - Axolotls (Soft)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Kryptonite cannot recognise this value, and therefore cannot edit it.",
                    ChatColor.RED + "Please send a screenshot of this error to github.com/lewmc/kryptonite"
            ));
        }
    }

    private void entityDespawnRangesCreatureHard(char id) {
        Object value = this.paperWorld.getObject(PaperWorld.Key.ENTITY_DESPAWN_RANGES_CREATURE_HARD);
        if (value instanceof String) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_CREATURE_HARD, click, 0, true),
                    ChatColor.DARK_GREEN + "Entity Despawn Ranges - Creature (Hard)",
                    ChatColor.GREEN + "default (128)",
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value instanceof Integer && (int) value <= 20) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_CREATURE_HARD, click, (int) value, true),
                    ChatColor.GOLD + "Entity Despawn Ranges - Creature (Hard)",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value instanceof Integer && (int) value <= 128) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_CREATURE_HARD, click, (int) value, true),
                    ChatColor.DARK_GREEN + "Entity Despawn Ranges - Creature (Hard)",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value instanceof Integer) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_CREATURE_HARD, click, (int) value, true),
                    ChatColor.DARK_RED + "Entity Despawn Ranges - Creature (Hard)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.BARRIER),
                    1,
                    click -> true,
                    ChatColor.DARK_RED + "Entity Despawn Ranges - Creature (Hard)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Kryptonite cannot recognise this value, and therefore cannot edit it.",
                    ChatColor.RED + "Please send a screenshot of this error to github.com/lewmc/kryptonite"
            ));
        }
    }

    private void entityDespawnRangesCreatureSoft(char id) {
        Object value = this.paperWorld.getObject(PaperWorld.Key.ENTITY_DESPAWN_RANGES_CREATURE_SOFT);
        if (value instanceof String) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_CREATURE_SOFT, click, 0, true),
                    ChatColor.DARK_GREEN + "Entity Despawn Ranges - Creature (Soft)",
                    ChatColor.GREEN + "default (32)",
                    ChatColor.GREEN + "Ideal value.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value instanceof Integer && (int) value <= 10) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_CREATURE_SOFT, click, (int) value, true),
                    ChatColor.GOLD + "Entity Despawn Ranges - Creature (Soft)",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value instanceof Integer && (int) value <= 32) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_CREATURE_SOFT, click, (int) value, true),
                    ChatColor.DARK_GREEN + "Entity Despawn Ranges - Creature (Soft)",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value instanceof Integer) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_CREATURE_SOFT, click, (int) value, true),
                    ChatColor.DARK_RED + "Entity Despawn Ranges - Creature (Soft)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.BARRIER),
                    1,
                    click -> true,
                    ChatColor.DARK_RED + "Entity Despawn Ranges - Creature (Soft)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Kryptonite cannot recognise this value, and therefore cannot edit it.",
                    ChatColor.RED + "Please send a screenshot of this error to github.com/lewmc/kryptonite"
            ));
        }
    }

    private void entityDespawnRangesMiscHard(char id) {
        Object value = this.paperWorld.getObject(PaperWorld.Key.ENTITY_DESPAWN_RANGES_MISC_HARD);
        if (value instanceof String) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_MISC_HARD, click, 0, true),
                    ChatColor.DARK_GREEN + "Entity Despawn Ranges - Misc (Hard)",
                    ChatColor.GREEN + "default (128)",
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value instanceof Integer && (int) value <= 20) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_MISC_HARD, click, (int) value, true),
                    ChatColor.GOLD + "Entity Despawn Ranges - Misc (Hard)",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value instanceof Integer && (int) value <= 128) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_MISC_HARD, click, (int) value, true),
                    ChatColor.DARK_GREEN + "Entity Despawn Ranges - Misc (Hard)",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value instanceof Integer) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_MISC_HARD, click, (int) value, true),
                    ChatColor.DARK_RED + "Entity Despawn Ranges - Misc (Hard)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.BARRIER),
                    1,
                    click -> true,
                    ChatColor.DARK_RED + "Entity Despawn Ranges - Misc (Hard)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Kryptonite cannot recognise this value, and therefore cannot edit it.",
                    ChatColor.RED + "Please send a screenshot of this error to github.com/lewmc/kryptonite"
            ));
        }
    }

    private void entityDespawnRangesMiscSoft(char id) {
        Object value = this.paperWorld.getObject(PaperWorld.Key.ENTITY_DESPAWN_RANGES_MISC_SOFT);
        if (value instanceof String) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_MISC_SOFT, click, 0, true),
                    ChatColor.DARK_GREEN + "Entity Despawn Ranges - Misc (Soft)",
                    ChatColor.GREEN + "default (32)",
                    ChatColor.GREEN + "Ideal value.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value instanceof Integer && (int) value <= 10) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_MISC_SOFT, click, (int) value, true),
                    ChatColor.GOLD + "Entity Despawn Ranges - Misc (Soft)",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value instanceof Integer && (int) value <= 32) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_MISC_SOFT, click, (int) value, true),
                    ChatColor.DARK_GREEN + "Entity Despawn Ranges - Misc (Soft)",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value instanceof Integer) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_MISC_SOFT, click, (int) value, true),
                    ChatColor.DARK_RED + "Entity Despawn Ranges - Misc (Soft)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.BARRIER),
                    1,
                    click -> true,
                    ChatColor.DARK_RED + "Entity Despawn Ranges - Misc (Soft)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Kryptonite cannot recognise this value, and therefore cannot edit it.",
                    ChatColor.RED + "Please send a screenshot of this error to github.com/lewmc/kryptonite"
            ));
        }
    }

    private void entityDespawnRangesMonsterHard(char id) {
        Object value = this.paperWorld.getObject(PaperWorld.Key.ENTITY_DESPAWN_RANGES_MONSTER_HARD);
        if (value instanceof String) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_MONSTER_HARD, click, 0, true),
                    ChatColor.DARK_GREEN + "Entity Despawn Ranges - Monster (Hard)",
                    ChatColor.GREEN + "default (128)",
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value instanceof Integer && (int) value <= 20) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_MONSTER_HARD, click, (int) value, true),
                    ChatColor.GOLD + "Entity Despawn Ranges - Monster (Hard)",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value instanceof Integer && (int) value <= 128) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_MONSTER_HARD, click, (int) value, true),
                    ChatColor.DARK_GREEN + "Entity Despawn Ranges - Monster (Hard)",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value instanceof Integer) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_MONSTER_HARD, click, (int) value, true),
                    ChatColor.DARK_RED + "Entity Despawn Ranges - Monster (Hard)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.BARRIER),
                    1,
                    click -> true,
                    ChatColor.DARK_RED + "Entity Despawn Ranges - Monster (Hard)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Kryptonite cannot recognise this value, and therefore cannot edit it.",
                    ChatColor.RED + "Please send a screenshot of this error to github.com/lewmc/kryptonite"
            ));
        }
    }

    private void entityDespawnRangesMonsterSoft(char id) {
        Object value = this.paperWorld.getObject(PaperWorld.Key.ENTITY_DESPAWN_RANGES_MONSTER_SOFT);
        if (value instanceof String) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_MONSTER_SOFT, click, 0, true),
                    ChatColor.DARK_GREEN + "Entity Despawn Ranges - Monster (Soft)",
                    ChatColor.GREEN + "default (32)",
                    ChatColor.GREEN + "Ideal value.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value instanceof Integer && (int) value <= 10) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_MONSTER_SOFT, click, (int) value, true),
                    ChatColor.GOLD + "Entity Despawn Ranges - Monster (Soft)",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value instanceof Integer && (int) value <= 32) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_MONSTER_SOFT, click, (int) value, true),
                    ChatColor.DARK_GREEN + "Entity Despawn Ranges - Monster (Soft)",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value instanceof Integer) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_MONSTER_SOFT, click, (int) value, true),
                    ChatColor.DARK_RED + "Entity Despawn Ranges - Monster (Soft)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.BARRIER),
                    1,
                    click -> true,
                    ChatColor.DARK_RED + "Entity Despawn Ranges - Monster (Soft)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Kryptonite cannot recognise this value, and therefore cannot edit it.",
                    ChatColor.RED + "Please send a screenshot of this error to github.com/lewmc/kryptonite"
            ));
        }
    }

    private void entityDespawnRangesUndergroundWaterCreatureHard(char id) {
        Object value = this.paperWorld.getObject(PaperWorld.Key.ENTITY_DESPAWN_RANGES_UNDERGROUND_WATER_CREATURE_HARD);
        if (value instanceof String) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_UNDERGROUND_WATER_CREATURE_HARD, click, 0, true),
                    ChatColor.DARK_GREEN + "Entity Despawn Ranges - Underground Water Creature (Hard)",
                    ChatColor.GREEN + "default (128)",
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value instanceof Integer && (int) value <= 20) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_UNDERGROUND_WATER_CREATURE_HARD, click, (int) value, true),
                    ChatColor.GOLD + "Entity Despawn Ranges - Underground Water Creature (Hard)",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value instanceof Integer && (int) value <= 128) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_UNDERGROUND_WATER_CREATURE_HARD, click, (int) value, true),
                    ChatColor.DARK_GREEN + "Entity Despawn Ranges - Underground Water Creature (Hard)",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value instanceof Integer) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_UNDERGROUND_WATER_CREATURE_HARD, click, (int) value, true),
                    ChatColor.DARK_RED + "Entity Despawn Ranges - Underground Water Creature (Hard)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.BARRIER),
                    1,
                    click -> true,
                    ChatColor.DARK_RED + "Entity Despawn Ranges - Underground Water Creature (Hard)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Kryptonite cannot recognise this value, and therefore cannot edit it.",
                    ChatColor.RED + "Please send a screenshot of this error to github.com/lewmc/kryptonite"
            ));
        }
    }

    private void entityDespawnRangesUndergroundWaterCreatureSoft(char id) {
        Object value = this.paperWorld.getObject(PaperWorld.Key.ENTITY_DESPAWN_RANGES_UNDERGROUND_WATER_CREATURE_SOFT);
        if (value instanceof String) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_UNDERGROUND_WATER_CREATURE_SOFT, click, 0, true),
                    ChatColor.DARK_GREEN + "Entity Despawn Ranges - Underground Water Creature (Soft)",
                    ChatColor.GREEN + "default (32)",
                    ChatColor.GREEN + "Ideal value.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value instanceof Integer && (int) value <= 10) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_UNDERGROUND_WATER_CREATURE_SOFT, click, (int) value, true),
                    ChatColor.GOLD + "Entity Despawn Ranges - Underground Water Creature (Soft)",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value instanceof Integer && (int) value <= 32) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_UNDERGROUND_WATER_CREATURE_SOFT, click, (int) value, true),
                    ChatColor.DARK_GREEN + "Entity Despawn Ranges - Underground Water Creature (Soft)",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value instanceof Integer) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_UNDERGROUND_WATER_CREATURE_SOFT, click, (int) value, true),
                    ChatColor.DARK_RED + "Entity Despawn Ranges - Underground Water Creature (Soft)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.BARRIER),
                    1,
                    click -> true,
                    ChatColor.DARK_RED + "Entity Despawn Ranges - Underground Water Creature (Soft)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Kryptonite cannot recognise this value, and therefore cannot edit it.",
                    ChatColor.RED + "Please send a screenshot of this error to github.com/lewmc/kryptonite"
            ));
        }
    }

    private void entityDespawnRangesWaterAmbientHard(char id) {
        Object value = this.paperWorld.getObject(PaperWorld.Key.ENTITY_DESPAWN_RANGES_WATER_AMBIENT_HARD);
        if (value instanceof String) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_WATER_AMBIENT_HARD, click, 0, true),
                    ChatColor.DARK_GREEN + "Entity Despawn Ranges - Water Ambient (Hard)",
                    ChatColor.GREEN + "default (128)",
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value instanceof Integer && (int) value <= 15) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_WATER_AMBIENT_HARD, click, (int) value, true),
                    ChatColor.GOLD + "Entity Despawn Ranges - Water Ambient (Hard)",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value instanceof Integer && (int) value <= 64) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_WATER_AMBIENT_HARD, click, (int) value, true),
                    ChatColor.DARK_GREEN + "Entity Despawn Ranges - Water Ambient (Hard)",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value instanceof Integer) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_WATER_AMBIENT_HARD, click, (int) value, true),
                    ChatColor.DARK_RED + "Entity Despawn Ranges - Water Ambient (Hard)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.BARRIER),
                    1,
                    click -> true,
                    ChatColor.DARK_RED + "Entity Despawn Ranges - Water Ambient (Hard)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Kryptonite cannot recognise this value, and therefore cannot edit it.",
                    ChatColor.RED + "Please send a screenshot of this error to github.com/lewmc/kryptonite"
            ));
        }
    }

    private void entityDespawnRangesWaterAmbientSoft(char id) {
        Object value = this.paperWorld.getObject(PaperWorld.Key.ENTITY_DESPAWN_RANGES_WATER_AMBIENT_SOFT);
        if (value instanceof String) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_WATER_AMBIENT_SOFT, click, 0, true),
                    ChatColor.DARK_GREEN + "Entity Despawn Ranges - Water Ambient (Soft)",
                    ChatColor.GREEN + "default (32)",
                    ChatColor.GREEN + "Ideal value.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value instanceof Integer && (int) value <= 5) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_WATER_AMBIENT_SOFT, click, (int) value, true),
                    ChatColor.GOLD + "Entity Despawn Ranges - Water Ambient (Soft)",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value instanceof Integer && (int) value <= 32) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_WATER_AMBIENT_SOFT, click, (int) value, true),
                    ChatColor.DARK_GREEN + "Entity Despawn Ranges - Water Ambient (Soft)",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value instanceof Integer) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_WATER_AMBIENT_SOFT, click, (int) value, true),
                    ChatColor.DARK_RED + "Entity Despawn Ranges - Water Ambient (Soft)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.BARRIER),
                    1,
                    click -> true,
                    ChatColor.DARK_RED + "Entity Despawn Ranges - Water Ambient (Soft)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Kryptonite cannot recognise this value, and therefore cannot edit it.",
                    ChatColor.RED + "Please send a screenshot of this error to github.com/lewmc/kryptonite"
            ));
        }
    }

    private void entityDespawnRangesWaterCreatureHard(char id) {
        Object value = this.paperWorld.getObject(PaperWorld.Key.ENTITY_DESPAWN_RANGES_WATER_CREATURE_HARD);
        if (value instanceof String) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_WATER_CREATURE_HARD, click, 0, true),
                    ChatColor.DARK_GREEN + "Entity Despawn Ranges - Water Creature (Hard)",
                    ChatColor.GREEN + "default (128)",
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value instanceof Integer && (int) value <= 20) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_WATER_CREATURE_HARD, click, (int) value, true),
                    ChatColor.GOLD + "Entity Despawn Ranges - Water Creature (Hard)",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value instanceof Integer && (int) value <= 128) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_WATER_CREATURE_HARD, click, (int) value, true),
                    ChatColor.DARK_GREEN + "Entity Despawn Ranges - Water Creature (Hard)",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value instanceof Integer) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_WATER_CREATURE_HARD, click, (int) value, true),
                    ChatColor.DARK_RED + "Entity Despawn Ranges - Water Creature (Hard)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.BARRIER),
                    1,
                    click -> true,
                    ChatColor.DARK_RED + "Entity Despawn Ranges - Water Creature (Hard)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Kryptonite cannot recognise this value, and therefore cannot edit it.",
                    ChatColor.RED + "Please send a screenshot of this error to github.com/lewmc/kryptonite"
            ));
        }
    }

    private void entityDespawnRangesWaterCreatureSoft(char id) {
        Object value = this.paperWorld.getObject(PaperWorld.Key.ENTITY_DESPAWN_RANGES_WATER_CREATURE_SOFT);
        if (value instanceof String) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_WATER_CREATURE_SOFT, click, 0, true),
                    ChatColor.DARK_GREEN + "Entity Despawn Ranges - Water Creature (Soft)",
                    ChatColor.GREEN + "default (32)",
                    ChatColor.GREEN + "Ideal value.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value instanceof Integer && (int) value <= 10) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.ORANGE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_WATER_CREATURE_SOFT, click, (int) value, true),
                    ChatColor.GOLD + "Entity Despawn Ranges - Water Creature (Soft)",
                    ChatColor.YELLOW + String.valueOf(value),
                    ChatColor.YELLOW + "Too low - impact to player experience.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value instanceof Integer && (int) value <= 32) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_WATER_CREATURE_SOFT, click, (int) value, true),
                    ChatColor.DARK_GREEN + "Entity Despawn Ranges - Water Creature (Soft)",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value instanceof Integer) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_WATER_CREATURE_SOFT, click, (int) value, true),
                    ChatColor.DARK_RED + "Entity Despawn Ranges - Water Creature (Soft)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.BARRIER),
                    1,
                    click -> true,
                    ChatColor.DARK_RED + "Entity Despawn Ranges - Water Creature (Soft)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Kryptonite cannot recognise this value, and therefore cannot edit it.",
                    ChatColor.RED + "Please send a screenshot of this error to github.com/lewmc/kryptonite"
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

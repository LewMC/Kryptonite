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
 * KOS Paper World GUI (Page 1/5)
 * @deprecated
 */
@Deprecated
public class KOS_PaperWorld_1 {
    private final Kryptonite plugin;
    private final CommandSender user;
    private final PaperWorld paperWorld;
    private InventoryGui gui;

    /**
     * Constructor for the KOS Paper World GUI (Page 1/5)
     * @param plugin Kryptonite - Reference to the main plugin class.
     * @param user CommandSender - The user who sent the command.
     */
    public KOS_PaperWorld_1(Kryptonite plugin, CommandSender user) {
        this.plugin = plugin;
        this.user = user;
        this.paperWorld = new PaperWorld(plugin, user);
    }

    /**
     * Shows the KOS Paper World GUI (Page 1/5)
     */
    public void show() {
        InventoryHolder holder = this.user.getServer().getPlayer(this.user.getName());
        this.gui = new InventoryGui(this.plugin, holder, "KOS - Paper World Configuration (1/5)", this.getElements());
        this.addElements();

        this.gui.build();
        this.gui.show((Player) this.user);
    }

    /**
     * Adds pre-programmed elements to the GUI
     */
    private void addElements() {
        this.delayChunkUnloadsBy('a');
        this.maxAutosaveChunksPerTick('b');
        this.preventMovingIntoUnloadedChucks('c');
        this.entityPerChunkSaveLimitAreaEffectCloud('d');
        this.entityPerChunkSaveLimitArrow('e');
        this.entityPerChunkSaveLimitDragonFireball('f');
        this.entityPerChunkSaveLimitEgg('g');
        this.entityPerChunkSaveLimitEnderPearl('h');
        this.entityPerChunkSaveLimitExperienceBottle('i');
        this.entityPerChunkSaveLimitExperienceOrb('j');
        this.entityPerChunkSaveLimitEyeOfEnder('k');
        this.entityPerChunkSaveLimitFireball('l');
        this.entityPerChunkSaveLimitLlamaSpit('m');
        this.entityPerChunkSaveLimitPotion('n');
        this.entityPerChunkSaveLimitShulkerBullet('o');
        this.entityPerChunkSaveLimitSmallFireball('p');
        this.entityPerChunkSaveLimitSnowball('q');
        this.entityPerChunkSaveLimitSpectralArrow('r');

        KOS_GuiConstants consts = new KOS_GuiConstants(this.plugin, this.gui);
        consts.addConstants();

        this.gui.addElement(new StaticGuiElement('z',
                    new ItemStack(Material.OAK_SIGN),
                1,
                click -> {
                    click.getGui().close();
                    KOS_PaperWorld_2 nextGui = new KOS_PaperWorld_2(this.plugin, this.user);
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
                "  w x y z"
        };
    }

    private void delayChunkUnloadsBy(char id) {
        Object value = this.paperWorld.getObject(PaperWorld.Key.DELAY_CHUNK_UNLOADS_BY);
        if (value instanceof String && value == "default") {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.DELAY_CHUNK_UNLOADS_BY, click, 0, true),
                    ChatColor.DARK_GREEN + "Delay Chunk Unloads By",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Ideal value.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value instanceof String && value != "default") {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.WHITE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.DELAY_CHUNK_UNLOADS_BY, click, (int) value, true),
                    ChatColor.WHITE + "Delay Chunk Unloads By",
                    ChatColor.GRAY + String.valueOf(value),
                    ChatColor.GRAY + "'default' (lowest value) is ideal for most servers.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else if (value instanceof Integer) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.WHITE_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.DELAY_CHUNK_UNLOADS_BY, click, (int) value, true),
                    ChatColor.WHITE + "Delay Chunk Unloads By",
                    ChatColor.GRAY + String.valueOf(value),
                    ChatColor.GRAY + "'default' (lowest value) is ideal for most servers.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.BARRIER),
                    1,
                    click -> true,
                    ChatColor.DARK_RED + "Delay Chunk Unloads By",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Kryptonite cannot recognise this value, and therefore cannot edit it.",
                    ChatColor.RED + "Please send a screenshot of this error to github.com/lewmc/kryptonite"
            ));
        }
    }

    private void maxAutosaveChunksPerTick(char id) {
        int value = this.paperWorld.getInt(PaperWorld.Key.MAX_AUTOSAVE_CHUNKS_PER_TICK);
        if (value == 24) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.MAX_AUTOSAVE_CHUNKS_PER_TICK, click, value, false),
                    ChatColor.DARK_GREEN + "Max Autosave Chunks Per Tick",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Ideal value.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.MAX_AUTOSAVE_CHUNKS_PER_TICK, click, value, false),
                    ChatColor.DARK_RED + "Max Autosave Chunks Per Tick",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Advanced players only - may cause performance issues if set incorrectly.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void preventMovingIntoUnloadedChucks(char id) {
        boolean value = this.paperWorld.getBoolean(PaperWorld.Key.PREVENT_MOVING_INTO_UNLOADED_CHUNKS);
        if (value) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> {
                        click.getGui().close();
                        this.paperWorld.setBoolean(PaperWorld.Key.PREVENT_MOVING_INTO_UNLOADED_CHUNKS, false);
                        this.show();
                        return true;
                    },
                    ChatColor.DARK_GREEN + "Prevent Moving into Unloaded Chunks",
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
                        this.paperWorld.setBoolean(PaperWorld.Key.PREVENT_MOVING_INTO_UNLOADED_CHUNKS, true);
                        this.show();
                        return true;
                    },
                    ChatColor.DARK_RED + "Prevent Moving into Unloaded Chunks",
                    ChatColor.RED + "false",
                    ChatColor.RED + "Heavy impact to performance.",
                    ChatColor.BLUE + "Click to toggle true/false."
            ));
        }
    }

    private void entityPerChunkSaveLimitAreaEffectCloud(char id) {
        int value = this.paperWorld.getInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_AREA_EFFECT_CLOUD);
        if (value < 11) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_AREA_EFFECT_CLOUD, click, value, false),
                    ChatColor.DARK_GREEN + "Entity Per Chunk Save Limit (Area Effect Cloud)",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_AREA_EFFECT_CLOUD, click, value, false),
                    ChatColor.DARK_RED + "Entity Per Chunk Save Limit (Area Effect Cloud)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to chunk loading performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void entityPerChunkSaveLimitArrow(char id) {
        int value = this.paperWorld.getInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_ARROW);
        if (value < 20) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_ARROW, click, value, false),
                    ChatColor.DARK_GREEN + "Entity Per Chunk Save Limit (Arrow)",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_ARROW, click, value, false),
                    ChatColor.DARK_RED + "Entity Per Chunk Save Limit (Arrow)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to chunk loading performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void entityPerChunkSaveLimitDragonFireball(char id) {
        int value = this.paperWorld.getInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_DRAGON_FIREBALL);
        if (value < 6) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_DRAGON_FIREBALL, click, value, false),
                    ChatColor.DARK_GREEN + "Entity Per Chunk Save Limit (Dragon Fireball)",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_DRAGON_FIREBALL, click, value, false),
                    ChatColor.DARK_RED + "Entity Per Chunk Save Limit (Dragon Fireball)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to chunk loading performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void entityPerChunkSaveLimitEgg(char id) {
        int value = this.paperWorld.getInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_EGG);
        if (value < 9) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_EGG, click, value, false),
                    ChatColor.DARK_GREEN + "Entity Per Chunk Save Limit (Egg)",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_EGG, click, value, false),
                    ChatColor.DARK_RED + "Entity Per Chunk Save Limit (Egg)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to chunk loading performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void entityPerChunkSaveLimitEnderPearl(char id) {
        int value = this.paperWorld.getInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_ENDER_PEARL);
        if (value < 9) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_ENDER_PEARL, click, value, false),
                    ChatColor.DARK_GREEN + "Entity Per Chunk Save Limit (Ender Pearl)",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_ENDER_PEARL, click, value, false),
                    ChatColor.DARK_RED + "Entity Per Chunk Save Limit (Ender Pearl)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to chunk loading performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void entityPerChunkSaveLimitExperienceBottle(char id) {
        int value = this.paperWorld.getInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_EXPERIENCE_BOTTLE);
        if (value < 4) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_EXPERIENCE_BOTTLE, click, value, false),
                    ChatColor.DARK_GREEN + "Entity Per Chunk Save Limit (Experience Bottle)",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_EXPERIENCE_BOTTLE, click, value, false),
                    ChatColor.DARK_RED + "Entity Per Chunk Save Limit (Experience Bottle)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to chunk loading performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void entityPerChunkSaveLimitExperienceOrb(char id) {
        int value = this.paperWorld.getInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_EXPERIENCE_ORB);
        if (value < 21) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_EXPERIENCE_ORB, click, value, false),
                    ChatColor.DARK_GREEN + "Entity Per Chunk Save Limit (Experience Orb)",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_EXPERIENCE_ORB, click, value, false),
                    ChatColor.DARK_RED + "Entity Per Chunk Save Limit (Experience Orb)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to chunk loading performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void entityPerChunkSaveLimitEyeOfEnder(char id) {
        int value = this.paperWorld.getInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_EYE_OF_ENDER);
        if (value < 11) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_EYE_OF_ENDER, click, value, false),
                    ChatColor.DARK_GREEN + "Entity Per Chunk Save Limit (Eye of Ender)",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_EYE_OF_ENDER, click, value, false),
                    ChatColor.DARK_RED + "Entity Per Chunk Save Limit (Eye of Ender)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to chunk loading performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void entityPerChunkSaveLimitFireball(char id) {
        int value = this.paperWorld.getInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_FIREBALL);
        if (value < 11) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_FIREBALL, click, value, false),
                    ChatColor.DARK_GREEN + "Entity Per Chunk Save Limit (Fireball)",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_FIREBALL, click, value, false),
                    ChatColor.DARK_RED + "Entity Per Chunk Save Limit (Fireball)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to chunk loading performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void entityPerChunkSaveLimitLlamaSpit(char id) {
        int value = this.paperWorld.getInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_LLAMA_SPIT);
        if (value < 6) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_LLAMA_SPIT, click, value, false),
                    ChatColor.DARK_GREEN + "Entity Per Chunk Save Limit (Llama Spit)",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_LLAMA_SPIT, click, value, false),
                    ChatColor.DARK_RED + "Entity Per Chunk Save Limit (Llama Spit)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to chunk loading performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void entityPerChunkSaveLimitPotion(char id) {
        int value = this.paperWorld.getInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_POTION);
        if (value < 6) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_POTION, click, value, false),
                    ChatColor.DARK_GREEN + "Entity Per Chunk Save Limit (Potion)",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_POTION, click, value, false),
                    ChatColor.DARK_RED + "Entity Per Chunk Save Limit (Potion)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to chunk loading performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void entityPerChunkSaveLimitShulkerBullet(char id) {
        int value = this.paperWorld.getInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_SHULKER_BULLET);
        if (value < 9) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_SHULKER_BULLET, click, value, false),
                    ChatColor.DARK_GREEN + "Entity Per Chunk Save Limit (Shulker Bullet)",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_SHULKER_BULLET, click, value, false),
                    ChatColor.DARK_RED + "Entity Per Chunk Save Limit (Shulker Bullet)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to chunk loading performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void entityPerChunkSaveLimitSmallFireball(char id) {
        int value = this.paperWorld.getInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_SMALL_FIREBALL);
        if (value < 11) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_SMALL_FIREBALL, click, value, false),
                    ChatColor.DARK_GREEN + "Entity Per Chunk Save Limit (Small Fireball)",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_SMALL_FIREBALL, click, value, false),
                    ChatColor.DARK_RED + "Entity Per Chunk Save Limit (Small Fireball)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to chunk loading performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void entityPerChunkSaveLimitSnowball(char id) {
        int value = this.paperWorld.getInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_SNOWBALL);
        if (value < 21) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_SNOWBALL, click, value, false),
                    ChatColor.DARK_GREEN + "Entity Per Chunk Save Limit (Snowball)",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_SNOWBALL, click, value, false),
                    ChatColor.DARK_RED + "Entity Per Chunk Save Limit (Snowball)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to chunk loading performance.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        }
    }

    private void entityPerChunkSaveLimitSpectralArrow(char id) {
        int value = this.paperWorld.getInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_SPECTRAL_ARROW);
        if (value < 6) {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.LIME_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_SPECTRAL_ARROW, click, value, false),
                    ChatColor.DARK_GREEN + "Entity Per Chunk Save Limit (Spectral Arrow)",
                    ChatColor.GREEN + String.valueOf(value),
                    ChatColor.GREEN + "Within ideal range.",
                    ChatColor.BLUE + "Right click to increase - left click to decrease."
            ));
        } else {
            this.gui.addElement(new StaticGuiElement(id,
                    new ItemStack(Material.RED_CONCRETE),
                    1,
                    click -> this.setInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_SPECTRAL_ARROW, click, value, false),
                    ChatColor.DARK_RED + "Entity Per Chunk Save Limit (Spectral Arrow)",
                    ChatColor.RED + String.valueOf(value),
                    ChatColor.RED + "Too high - impact to chunk loading performance.",
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

package net.lewmc.kryptonite.kos.config;

import net.lewmc.kryptonite.Kryptonite;
import net.lewmc.kryptonite.utils.ConfigurationUtil;
import net.lewmc.kryptonite.utils.LogUtil;
import org.bukkit.command.CommandSender;

/**
 * The PaperWorld class manages the paper-world-defaults.yml configuration file.
 * @deprecated
 */
@Deprecated
public class PaperWorld {
    private final Kryptonite plugin;
    private final CommandSender user;

    /**
     * Constructor for the PaperWorld class.
     * @param plugin Kryptonite - Reference to the main plugin class.
     * @param user CommandSender - The user who sent the command.
     */
    public PaperWorld(Kryptonite plugin, CommandSender user) {
        this.plugin = plugin;
        this.user = user;
    }

    /**
     * Configuration values supported by this format.
     */
    public enum Key {
        DELAY_CHUNK_UNLOADS_BY {
            @Override public String toString() { return "chunks.delay-chunk-unloads-by"; }
        },
        MAX_AUTOSAVE_CHUNKS_PER_TICK {
            @Override public String toString() { return "chunks.max-auto-save-chunks-per-tick"; }
        },
        PREVENT_MOVING_INTO_UNLOADED_CHUNKS {
            @Override public String toString() { return "chunks.prevent-moving-into-unloaded-chunks"; }
        },
        ENTITY_PER_CHUNK_SAVE_LIMIT_AREA_EFFECT_CLOUD {
            @Override public String toString() { return "chunks.entity-per-chunk-save-limit.area_effect_cloud"; }
        },
        ENTITY_PER_CHUNK_SAVE_LIMIT_ARROW {
            @Override public String toString() { return "chunks.entity-per-chunk-save-limit.arrow"; }
        },
        ENTITY_PER_CHUNK_SAVE_LIMIT_DRAGON_FIREBALL {
            @Override public String toString() { return "chunks.entity-per-chunk-save-limit.dragon_fireball"; }
        },
        ENTITY_PER_CHUNK_SAVE_LIMIT_EGG {
            @Override public String toString() { return "chunks.entity-per-chunk-save-limit.egg"; }
        },
        ENTITY_PER_CHUNK_SAVE_LIMIT_ENDER_PEARL {
            @Override public String toString() { return "chunks.entity-per-chunk-save-limit.ender_pearl"; }
        },
        ENTITY_PER_CHUNK_SAVE_LIMIT_EXPERIENCE_BOTTLE {
            @Override public String toString() { return "chunks.entity-per-chunk-save-limit.experience_bottle"; }
        },
        ENTITY_PER_CHUNK_SAVE_LIMIT_EXPERIENCE_ORB {
            @Override public String toString() { return "chunks.entity-per-chunk-save-limit.experience_orb"; }
        },
        ENTITY_PER_CHUNK_SAVE_LIMIT_EYE_OF_ENDER {
            @Override public String toString() { return "chunks.entity-per-chunk-save-limit.eye_of_ender"; }
        },
        ENTITY_PER_CHUNK_SAVE_LIMIT_FIREBALL {
            @Override public String toString() { return "chunks.entity-per-chunk-save-limit.fireball"; }
        },
        ENTITY_PER_CHUNK_SAVE_LIMIT_LLAMA_SPIT {
            @Override public String toString() { return "chunks.entity-per-chunk-save-limit.llama_spit"; }
        },
        ENTITY_PER_CHUNK_SAVE_LIMIT_POTION {
            @Override public String toString() { return "chunks.entity-per-chunk-save-limit.potion"; }
        },
        ENTITY_PER_CHUNK_SAVE_LIMIT_SHULKER_BULLET {
            @Override public String toString() { return "chunks.entity-per-chunk-save-limit.shulker_bullet"; }
        },
        ENTITY_PER_CHUNK_SAVE_LIMIT_SMALL_FIREBALL {
            @Override public String toString() { return "chunks.entity-per-chunk-save-limit.small_fireball"; }
        },
        ENTITY_PER_CHUNK_SAVE_LIMIT_SNOWBALL {
            @Override public String toString() { return "chunks.entity-per-chunk-save-limit.snowball"; }
        },
        ENTITY_PER_CHUNK_SAVE_LIMIT_SPECTRAL_ARROW {
            @Override public String toString() { return "chunks.entity-per-chunk-save-limit.spectral_arrow"; }
        },
        ENTITY_PER_CHUNK_SAVE_LIMIT_TRIDENT {
            @Override public String toString() { return "chunks.entity-per-chunk-save-limit.trident"; }
        },
        ENTITY_PER_CHUNK_SAVE_LIMIT_WITHER_SKULL {
            @Override public String toString() { return "chunks.entity-per-chunk-save-limit.wither_skull"; }
        },
        ENTITY_DESPAWN_RANGES_AMBIENT_HARD {
            @Override public String toString() { return "entities.spawning.despawn-ranges.ambient.hard"; }
        },
        ENTITY_DESPAWN_RANGES_AMBIENT_SOFT {
            @Override public String toString() { return "entities.spawning.despawn-ranges.ambient.soft"; }
        },
        ENTITY_DESPAWN_RANGES_AXOLOTLS_HARD {
            @Override public String toString() { return "entities.spawning.despawn-ranges.axolotls.hard"; }
        },
        ENTITY_DESPAWN_RANGES_AXOLOTLS_SOFT {
            @Override public String toString() { return "entities.spawning.despawn-ranges.axolotls.soft"; }
        },
        ENTITY_DESPAWN_RANGES_CREATURE_HARD {
            @Override public String toString() { return "entities.spawning.despawn-ranges.creature.hard"; }
        },
        ENTITY_DESPAWN_RANGES_CREATURE_SOFT {
            @Override public String toString() { return "entities.spawning.despawn-ranges.creature.soft"; }
        },
        ENTITY_DESPAWN_RANGES_MISC_HARD {
            @Override public String toString() { return "entities.spawning.despawn-ranges.misc.hard"; }
        },
        ENTITY_DESPAWN_RANGES_MISC_SOFT {
            @Override public String toString() { return "entities.spawning.despawn-ranges.misc.soft"; }
        },
        ENTITY_DESPAWN_RANGES_MONSTER_HARD {
            @Override public String toString() { return "entities.spawning.despawn-ranges.monster.hard"; }
        },
        ENTITY_DESPAWN_RANGES_MONSTER_SOFT {
            @Override public String toString() { return "entities.spawning.despawn-ranges.monster.soft"; }
        },
        ENTITY_DESPAWN_RANGES_UNDERGROUND_WATER_CREATURE_HARD {
            @Override public String toString() { return "entities.spawning.despawn-ranges.underground_water_creature.hard"; }
        },
        ENTITY_DESPAWN_RANGES_UNDERGROUND_WATER_CREATURE_SOFT {
            @Override public String toString() { return "entities.spawning.despawn-ranges.underground_water_creature.soft"; }
        },
        ENTITY_DESPAWN_RANGES_WATER_AMBIENT_HARD {
            @Override public String toString() { return "entities.spawning.despawn-ranges.water_ambient.hard"; }
        },
        ENTITY_DESPAWN_RANGES_WATER_AMBIENT_SOFT {
            @Override public String toString() { return "entities.spawning.despawn-ranges.water_ambient.soft"; }
        },
        ENTITY_DESPAWN_RANGES_WATER_CREATURE_HARD {
            @Override public String toString() { return "entities.spawning.despawn-ranges.water_creature.hard"; }
        },
        ENTITY_DESPAWN_RANGES_WATER_CREATURE_SOFT {
            @Override public String toString() { return "entities.spawning.despawn-ranges.water_creature.soft"; }
        },
        PER_PLAYER_MOB_SPAWNS {
            @Override public String toString() { return "entities.spawning.per-player-mob-spawns"; }
        },
        MAX_ENTITY_COLLISIONS {
            @Override public String toString() { return "collisions.max-entity-collisions"; }
        },
        UPDATE_PATHFINDING_ON_BLOCK_UPDATE {
            @Override public String toString() { return "misc.update-pathfinding-on-block-update"; }
        },
        FIX_CLIMBING_BYPASSING_CRAMMING_RULE {
            @Override public String toString() { return "collisions.fix-climbing-bypassing-cramming-rule"; }
        },
        ARMOR_STANDS_TICK {
            @Override public String toString() { return "entities.armor-stands.tick"; }
        },
        ARMOR_STANDS_DO_COLLISION_ENTITY_LOOKUPS {
            @Override public String toString() { return "entities.armor-stands.do-collision-entity-lookups"; }
        },
        TICK_RATE_VILLAGER_VALIDATES_NEARBY_POI {
            @Override public String toString() { return "tick-rates.behavior.villager.validatenearbypoi"; }
        },
        TICK_RATE_VILLAGER_ACQUIRE_POI {
            @Override public String toString() { return "tick-rates.behavior.villager.acquirepoi"; }
        },
        TICK_RATE_VILLAGER_SENSOR_NEAREST_BED {
            @Override public String toString() { return "tick-rates.sensor.villager.nearestbedsensor"; }
        },
        TICK_RATE_VILLAGER_SENSOR_SECONDARY_POI {
            @Override public String toString() { return "tick-rates.sensor.villager.secondarypoisensor"; }
        },
        TICK_RATE_VILLAGER_SENSOR_VILLAGER_BABIES {
            @Override public String toString() { return "tick-rates.sensor.villager.villagerbabiessensor"; }
        },
        TICK_RATE_VILLAGER_SENSOR_PLAYER {
            @Override public String toString() { return "tick-rates.sensor.villager.playersensor"; }
        },
        TICK_RATE_VILLAGER_SENSOR_NEAREST_LIVING_ENTITY {
            @Override public String toString() { return "tick-rates.sensor.villager.nearestlivingentitysensor"; }
        },
        ALT_ITEM_DESPAWN_RATE_ENABLED {
            @Override public String toString() { return "entities.spawning.alt-item-despawn-rate.enabled"; }
        },
        ALT_ITEM_DESPAWN_RATE_COBBLESTONE {
            @Override public String toString() { return "entities.spawning.alt-item-despawn-rate.items.cobblestone"; }
        },
        ALT_ITEM_DESPAWN_RATE_NETHERRACK {
            @Override public String toString() { return "entities.spawning.alt-item-despawn-rate.items.netherrack"; }
        },
        ALT_ITEM_DESPAWN_RATE_SAND {
            @Override public String toString() { return "entities.spawning.alt-item-despawn-rate.items.sand"; }
        },
        ALT_ITEM_DESPAWN_RATE_RED_SAND {
            @Override public String toString() { return "entities.spawning.alt-item-despawn-rate.items.red_sand"; }
        },
        ALT_ITEM_DESPAWN_RATE_GRAVEL {
            @Override public String toString() { return "entities.spawning.alt-item-despawn-rate.items.gravel"; }
        },
        ALT_ITEM_DESPAWN_RATE_DIRT {
            @Override public String toString() { return "entities.spawning.alt-item-despawn-rate.items.dirt"; }
        },
        ALT_ITEM_DESPAWN_RATE_SHORT_GRASS {
            @Override public String toString() { return "entities.spawning.alt-item-despawn-rate.items.short_grass"; }
        },
        ALT_ITEM_DESPAWN_RATE_PUMPKIN {
            @Override public String toString() { return "entities.spawning.alt-item-despawn-rate.items.pumpkin"; }
        },
        ALT_ITEM_DESPAWN_RATE_MELON_SLICE {
            @Override public String toString() { return "entities.spawning.alt-item-despawn-rate.items.melon_slice"; }
        },
        ALT_ITEM_DESPAWN_RATE_KELP {
            @Override public String toString() { return "entities.spawning.alt-item-despawn-rate.items.kelp"; }
        },
        ALT_ITEM_DESPAWN_RATE_BAMBOO {
            @Override public String toString() { return "entities.spawning.alt-item-despawn-rate.items.bamboo"; }
        },
        ALT_ITEM_DESPAWN_RATE_SUGAR_CANE {
            @Override public String toString() { return "entities.spawning.alt-item-despawn-rate.items.sugar_cane"; }
        },
        ALT_ITEM_DESPAWN_RATE_TWISTING_VINES {
            @Override public String toString() { return "entities.spawning.alt-item-despawn-rate.items.twisting_vines"; }
        },
        ALT_ITEM_DESPAWN_RATE_WEEPING_VINES {
            @Override public String toString() { return "entities.spawning.alt-item-despawn-rate.items.weeping_vines"; }
        },
        ALT_ITEM_DESPAWN_RATE_OAK_LEAVES {
            @Override public String toString() { return "entities.spawning.alt-item-despawn-rate.items.oak_leaves"; }
        },
        ALT_ITEM_DESPAWN_RATE_SPRUCE_LEAVES {
            @Override public String toString() { return "entities.spawning.alt-item-despawn-rate.items.spruce_leaves"; }
        },
        ALT_ITEM_DESPAWN_RATE_BIRCH_LEAVES {
            @Override public String toString() { return "entities.spawning.alt-item-despawn-rate.items.birch_leaves"; }
        },
        ALT_ITEM_DESPAWN_RATE_JUNGLE_LEAVES {
            @Override public String toString() { return "entities.spawning.alt-item-despawn-rate.items.jungle_leaves"; }
        },
        ALT_ITEM_DESPAWN_RATE_ACACIA_LEAVES {
            @Override public String toString() { return "entities.spawning.alt-item-despawn-rate.items.acacia_leaves"; }
        },
        ALT_ITEM_DESPAWN_RATE_DARK_OAK_LEAVES {
            @Override public String toString() { return "entities.spawning.alt-item-despawn-rate.items.dark_oak_leaves"; }
        },
        ALT_ITEM_DESPAWN_RATE_MANGROVE_LEAVES {
            @Override public String toString() { return "entities.spawning.alt-item-despawn-rate.items.mangrove_leaves"; }
        },
        ALT_ITEM_DESPAWN_RATE_CACTUS {
            @Override public String toString() { return "entities.spawning.alt-item-despawn-rate.items.cactus"; }
        },
        ALT_ITEM_DESPAWN_RATE_DIORITE {
            @Override public String toString() { return "entities.spawning.alt-item-despawn-rate.items.diorite"; }
        },
        ALT_ITEM_DESPAWN_RATE_GRANITE {
            @Override public String toString() { return "entities.spawning.alt-item-despawn-rate.items.granite"; }
        },
        ALT_ITEM_DESPAWN_RATE_ANDESITE {
            @Override public String toString() { return "entities.spawning.alt-item-despawn-rate.items.andesite"; }
        },
        ALT_ITEM_DESPAWN_RATE_SCAFFOLDING {
            @Override public String toString() { return "entities.spawning.alt-item-despawn-rate.items.scaffolding"; }
        },
        ALT_ITEM_DESPAWN_RATE_EGG {
            @Override public String toString() { return "entities.spawning.alt-item-despawn-rate.items.egg"; }
        },
        REDSTONE_IMPLEMENTATION {
            @Override public String toString() { return "misc.redstone-implementation"; }
        },
        HOPPER_DISABLE_MOVE_EVENT {
            @Override public String toString() { return "hopper.disable-move-event"; }
        },
        HOPPER_IGNORE_OCCLUDING_BLOCKS {
            @Override public String toString() { return "hopper.ignore-occluding-blocks"; }
        },
        TICK_RATE_MOB_SPAWNER {
            @Override public String toString() { return "tick-rates.mob-spawner"; }
        },
        OPTIMIZE_EXPLOSIONS {
            @Override public String toString() { return "environment.optimize-explosions"; }
        },
        TREASURE_MAPS_ENABLED {
            @Override public String toString() { return "environment.treasure-maps.enabled"; }
        },
        TREASURE_MAPS_FIND_ALREADY_DISCOVERED_LOOT_TABLES {
            @Override public String toString() { return "environment.treasure-maps.find-already-discovered.loot-tables"; }
        },
        TREASURE_MAPS_FIND_ALREADY_DISCOVERED_VILLAGER_TRADE {
            @Override public String toString() { return "environment.treasure-maps.find-already-discovered.villager-trade"; }
        },
        TICK_RATE_GRASS_SPREAD {
            @Override public String toString() { return "tick-rates.grass-spread"; }
        },
        TICK_RATE_CONTAINER_UPDATE {
            @Override public String toString() { return "tick-rates.container-update"; }
        },
        ARROW_DESPAWN_RATE_NON_PLAYER {
            @Override public String toString() { return "entities.spawning.non-player-arrow-despawn-rate"; }
        },
        ARROW_DESPAWN_RATE_CREATIVE {
            @Override public String toString() { return "entities.spawning.creative-arrow-despawn-rate"; }
        },
        NERFED_SPAWNER_MOBS_SHOULD_JUMP {
            @Override public String toString() { return "spawner-nerfed-mobs-should-jump"; }
        }
    }

    /**
     * Sets a requested key to a requested value.
     * @param key Key - The requested key.
     * @param value int - The requested value.
     */
    public void setInt(Key key, int value) {
        this.plugin.restartRequired = true;
        ConfigurationUtil cfg = new ConfigurationUtil(this.plugin, this.user);
        cfg.load("config/paper-world-defaults.yml");
        cfg.set(key.toString(), value);
        cfg.save();

        LogUtil log = new LogUtil(this.plugin);
        log.veboseInfo("KOS>config/paper-world-defaults.yml set '" + key + "' to '" + value + "'");
    }

    /**
     * Gets a requested key's value.
     * @param key Key - The requested key.
     */
    public int getInt(Key key) {
        ConfigurationUtil cfg = new ConfigurationUtil(this.plugin, this.user);
        cfg.load("config/paper-world-defaults.yml");
        return cfg.getInt(key.toString());
    }

    /**
     * Sets a requested key to a requested value.
     * @param key Key - The requested key.
     * @param value int - The requested value.
     */
    public void setBoolean(Key key, boolean value) {
        this.plugin.restartRequired = true;
        ConfigurationUtil cfg = new ConfigurationUtil(this.plugin, this.user);
        cfg.load("config/paper-world-defaults.yml");
        cfg.set(key.toString(), value);
        cfg.save();

        LogUtil log = new LogUtil(this.plugin);
        log.veboseInfo("KOS>config/paper-world-defaults.yml set '" + key + "' to '" + value + "'");
    }

    /**
     * Gets a requested key's value.
     * @param key Key - The requested key.
     */
    public boolean getBoolean(Key key) {
        ConfigurationUtil cfg = new ConfigurationUtil(this.plugin, this.user);
        cfg.load("config/paper-world-defaults.yml");
        return cfg.getBoolean(key.toString());
    }

    /**
     * Gets a requested key's value.
     * @param key Key - The requested key.
     */
    public Object getObject(Key key) {
        ConfigurationUtil cfg = new ConfigurationUtil(this.plugin, this.user);
        cfg.load("config/paper-world-defaults.yml");
        return cfg.get(key.toString());
    }

    /**
     * Gets a requested key's value.
     * @param key Key - The requested key.
     */
    public String getString(Key key) {
        ConfigurationUtil cfg = new ConfigurationUtil(this.plugin, this.user);
        cfg.load("config/paper-world-defaults.yml");
        return cfg.getString(key.toString());
    }

    /**
     * Sets a requested key to a requested value.
     * @param key Key - The requested key.
     * @param value int - The requested value.
     */
    public void setString(Key key, String value) {
        this.plugin.restartRequired = true;
        ConfigurationUtil cfg = new ConfigurationUtil(this.plugin, this.user);
        cfg.load("config/paper-world-defaults.yml");
        cfg.set(key.toString(), value);
        cfg.save();

        LogUtil log = new LogUtil(this.plugin);
        log.veboseInfo("KOS>config/paper-world-defaults.yml set '" + key + "' to '" + value + "'");
    }
}

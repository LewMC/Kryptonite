package net.lewmc.kryptonite.config;

import net.lewmc.kryptonite.Kryptonite;
import net.lewmc.kryptonite.utils.config.BooleanConfigItem;
import net.lewmc.kryptonite.utils.config.ConfigCollection;
import net.lewmc.kryptonite.utils.config.IntegerConfigItem;
import net.lewmc.kryptonite.utils.config.StringConfigItem;

import java.util.List;

/**
 * Configuration data for config/paper-world-defaults.yml
 * @since 2.1.0
 */
public class PaperWorldConfig extends ConfigCollection {
    /**
     * Constructs the config/paper-world-defaults data.
     * @param plugin Kryptonite - Reference to the main Kryptonite class.
     */
    public PaperWorldConfig(Kryptonite plugin) {
        String file = "config/paper-world-defaults";

        values.put(Key.DELAY_CHUNK_UNLOADS_BY.toString(), new StringConfigItem(
                file,
                Key.DELAY_CHUNK_UNLOADS_BY.toString(),
                "Delay Chunk Unloads By",
                List.of("Delays chunk unloads by the specified time.",
                        "Recommended value: 'default' (click to lowest)"),
                true,
                List.of("default","1s","2s","3s","4s","5s","6s","7s","8s","9s","10s","11s","12s","13s","14s","15s","16s","17s","18s","19s","20s","21s","22s","23s","24s","25s","26s","27s","28s","29s","30s","1m","5m","10m","30m","1h","3h","6h","12h","1d"),
                List.of("default"),
                plugin
        ));

        values.put(Key.MAX_AUTOSAVE_CHUNKS_PER_TICK.toString(), new IntegerConfigItem(
                file,
                Key.MAX_AUTOSAVE_CHUNKS_PER_TICK.toString(),
                "Max Autosave Chunks Per Tick",
                List.of("The maximum number of chunks the auto-save system",
                        "will save in a single tick."),
                true,
                0,
                100,
                "24",
                plugin
        ));

        values.put(Key.PREVENT_MOVING_INTO_UNLOADED_CHUNKS.toString(), new BooleanConfigItem(
                file,
                Key.PREVENT_MOVING_INTO_UNLOADED_CHUNKS.toString(),
                "Prevent Moving into Unloaded Chunks",
                List.of("Sets whether the server will prevent players from",
                        "moving into unloaded chunks or not. Can reduce",
                        "server lag, but players may stutter when travelling,",
                        "especially at high speeds."),
                true,
                true,
                plugin
        ));

        values.put(Key.ENTITY_PER_CHUNK_SAVE_LIMIT_AREA_EFFECT_CLOUD.toString(), new IntegerConfigItem(
                file,
                Key.ENTITY_PER_CHUNK_SAVE_LIMIT_AREA_EFFECT_CLOUD.toString(),
                "Entity Per Chunk Save Limit (Area Effect Cloud)",
                List.of("Limits the number of area effect cloud entities that",
                        "will be saved and loaded per chunk. A value of -1 ",
                        "disables the limit."),
                true,
                0,
                255,
                "<12",
                plugin
        ));

        values.put(Key.ENTITY_PER_CHUNK_SAVE_LIMIT_ARROW.toString(), new IntegerConfigItem(
                file,
                Key.ENTITY_PER_CHUNK_SAVE_LIMIT_ARROW.toString(),
                "Entity Per Chunk Save Limit (Arrow)",
                List.of("Limits the number of arrows that will be saved and",
                        "loaded per chunk. A value of -1 disables the limit."),
                true,
                -1,
                256,
                "16-20",
                plugin
        ));

        values.put(Key.ENTITY_PER_CHUNK_SAVE_LIMIT_DRAGON_FIREBALL.toString(), new IntegerConfigItem(
                file,
                Key.ENTITY_PER_CHUNK_SAVE_LIMIT_DRAGON_FIREBALL.toString(),
                "Entity Per Chunk Save Limit (Dragon Fireball)",
                List.of("Limits the number of dragon fireball entities that",
                        "will be saved and loaded per chunk. A value of -1 ",
                        "disables the limit."),
                true,
                -1,
                256,
                "3-5",
                plugin
        ));

        values.put(Key.ENTITY_PER_CHUNK_SAVE_LIMIT_EGG.toString(), new IntegerConfigItem(
                file,
                Key.ENTITY_PER_CHUNK_SAVE_LIMIT_EGG.toString(),
                "Entity Per Chunk Save Limit (Egg)",
                List.of("Limits the number of eggs that will be saved and",
                        "loaded per chunk. A value of -1 disables the limit."),
                true,
                -1,
                256,
                "8-20",
                plugin
        ));

        values.put(Key.ENTITY_PER_CHUNK_SAVE_LIMIT_ENDER_PEARL.toString(), new IntegerConfigItem(
                file,
                Key.ENTITY_PER_CHUNK_SAVE_LIMIT_ENDER_PEARL.toString(),
                "Entity Per Chunk Save Limit (Ender Pearl)",
                List.of("Limits the number of ender pearls that will be",
                        "saved and loaded per chunk. A value of -1 disables",
                        "the limit."),
                true,
                -1,
                256,
                "8-20",
                plugin
        ));

        values.put(Key.ENTITY_PER_CHUNK_SAVE_LIMIT_EXPERIENCE_BOTTLE.toString(), new IntegerConfigItem(
                file,
                Key.ENTITY_PER_CHUNK_SAVE_LIMIT_EXPERIENCE_BOTTLE.toString(),
                "Entity Per Chunk Save Limit (Experience Bottle)",
                List.of("Limits the number of experience bottles that will be",
                        "saved and loaded per chunk. A value of -1 disables",
                        "the limit."),
                true,
                -1,
                256,
                "3-5",
                plugin
        ));

        values.put(Key.ENTITY_PER_CHUNK_SAVE_LIMIT_EXPERIENCE_ORB.toString(), new IntegerConfigItem(
                file,
                Key.ENTITY_PER_CHUNK_SAVE_LIMIT_EXPERIENCE_ORB.toString(),
                "Entity Per Chunk Save Limit (Experience Orb)",
                List.of("Limits the number of experience orbs that will be",
                        "saved and loaded per chunk. A value of -1 disables",
                        "the limit."),
                true,
                -1,
                256,
                "16-50",
                plugin
        ));

        values.put(Key.ENTITY_PER_CHUNK_SAVE_LIMIT_EYE_OF_ENDER.toString(), new IntegerConfigItem(
                file,
                Key.ENTITY_PER_CHUNK_SAVE_LIMIT_EYE_OF_ENDER.toString(),
                "Entity Per Chunk Save Limit (Eye of Ender)",
                List.of("Limits the number of eyes of ender that will be",
                        "saved and loaded per chunk. A value of -1 disables",
                        "the limit."),
                true,
                -1,
                256,
                "8-20",
                plugin
        ));

        values.put(Key.ENTITY_PER_CHUNK_SAVE_LIMIT_FIREBALL.toString(), new IntegerConfigItem(
                file,
                Key.ENTITY_PER_CHUNK_SAVE_LIMIT_FIREBALL.toString(),
                "Entity Per Chunk Save Limit (Fireball)",
                List.of("Limits the number of fireballs that will be saved and",
                        "loaded per chunk. A value of -1 disables the limit."),
                true,
                -1,
                256,
                "8-10",
                plugin
        ));

        values.put(Key.ENTITY_PER_CHUNK_SAVE_LIMIT_LLAMA_SPIT.toString(), new IntegerConfigItem(
                file,
                Key.ENTITY_PER_CHUNK_SAVE_LIMIT_LLAMA_SPIT.toString(),
                "Entity Per Chunk Save Limit (Llama Spit)",
                List.of("Limits the number of llama spit entities that will",
                        "be saved and loaded per chunk. A value of -1",
                        "disables the limit."),
                true,
                -1,
                256,
                "3-5",
                plugin
        ));

        values.put(Key.ENTITY_PER_CHUNK_SAVE_LIMIT_POTION.toString(), new IntegerConfigItem(
                file,
                Key.ENTITY_PER_CHUNK_SAVE_LIMIT_POTION.toString(),
                "Entity Per Chunk Save Limit (Splash Potion)",
                List.of("Limits the number of splash potion clouds that",
                        "will be saved and loaded per chunk. A value of",
                        "-1 disables the limit."),
                true,
                -1,
                256,
                "8-10",
                plugin
        ));

        values.put(Key.ENTITY_PER_CHUNK_SAVE_LIMIT_SHULKER_BULLET.toString(), new IntegerConfigItem(
                file,
                Key.ENTITY_PER_CHUNK_SAVE_LIMIT_SHULKER_BULLET.toString(),
                "Entity Per Chunk Save Limit (Shulker Bullet)",
                List.of("Limits the number of shulker bullets that will",
                        "be saved and loaded per chunk. A value of -1",
                        "disables the limit."),
                true,
                -1,
                256,
                "8",
                plugin
        ));

        values.put(Key.ENTITY_PER_CHUNK_SAVE_LIMIT_SMALL_FIREBALL.toString(), new IntegerConfigItem(
                file,
                Key.ENTITY_PER_CHUNK_SAVE_LIMIT_SMALL_FIREBALL.toString(),
                "Entity Per Chunk Save Limit (Small Fireball)",
                List.of("Limits the number of small fireballs that will",
                        "be saved and loaded per chunk. A value of -1",
                        "disables the limit."),
                true,
                -1,
                256,
                "8",
                plugin
        ));

        values.put(Key.ENTITY_PER_CHUNK_SAVE_LIMIT_SNOWBALL.toString(), new IntegerConfigItem(
                file,
                Key.ENTITY_PER_CHUNK_SAVE_LIMIT_SNOWBALL.toString(),
                "Entity Per Chunk Save Limit (Snowball)",
                List.of("Limits the number of snowball entities that will",
                        "be saved and loaded per chunk. A value of -1",
                        "disables the limit."),
                true,
                -1,
                256,
                "8-20",
                plugin
        ));

        values.put(Key.ENTITY_PER_CHUNK_SAVE_LIMIT_SPECTRAL_ARROW.toString(), new IntegerConfigItem(
                file,
                Key.ENTITY_PER_CHUNK_SAVE_LIMIT_SPECTRAL_ARROW.toString(),
                "Entity Per Chunk Save Limit (Spectral Arrow)",
                List.of("Limits the number of spectral arrows that will",
                        "be saved and loaded per chunk. A value of -1",
                        "disables the limit."),
                true,
                -1,
                256,
                "5-16",
                plugin
        ));

        values.put(Key.ENTITY_PER_CHUNK_SAVE_LIMIT_TRIDENT.toString(), new IntegerConfigItem(
                file,
                Key.ENTITY_PER_CHUNK_SAVE_LIMIT_TRIDENT.toString(),
                "Entity Per Chunk Save Limit (Trident)",
                List.of("Limits the number of trident entities that will",
                        "be saved and loaded per chunk. A value of -1",
                        "disables the limit."),
                true,
                -1,
                256,
                "10-16",
                plugin
        ));

        values.put(Key.ENTITY_PER_CHUNK_SAVE_LIMIT_WITHER_SKULL.toString(), new IntegerConfigItem(
                file,
                Key.ENTITY_PER_CHUNK_SAVE_LIMIT_WITHER_SKULL.toString(),
                "Entity Per Chunk Save Limit (Wither Skull)",
                List.of("Limits the number of wither skulls that will",
                        "be saved and loaded per chunk. A value of -1",
                        "disables the limit."),
                true,
                -1,
                256,
                "72",
                plugin
        ));
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
            @Override public String toString() { return "chunks.entity-per-chunk-save-limit.splash_potion"; }
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
}
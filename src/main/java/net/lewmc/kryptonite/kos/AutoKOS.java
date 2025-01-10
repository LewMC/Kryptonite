package net.lewmc.kryptonite.kos;

import net.lewmc.kryptonite.Kryptonite;
import net.lewmc.kryptonite.kos.config.*;
import net.lewmc.kryptonite.utils.ConfigurationUtil;
import net.lewmc.kryptonite.utils.LogUtil;
import net.lewmc.kryptonite.utils.MessageUtil;
import net.lewmc.kryptonite.utils.SoftwareUtil;
import org.bukkit.command.CommandSender;

import java.io.File;

/**
 * The AutoKOS class.
 */
public class AutoKOS {
    private final Kryptonite plugin;
    private final LogUtil log;
    private final SoftwareUtil softwareUtil;
    private final MessageUtil message;
    private final CommandSender user;
    private ConfigurationUtil patches;

    /**
     * AutoKOS constructor.
     * @param plugin Kryptonite - Reference to the main plugin class.
     */
    public AutoKOS(Kryptonite plugin, CommandSender user) {
        this.plugin = plugin;
        this.log = new LogUtil(plugin);
        this.softwareUtil = new SoftwareUtil(plugin);
        this.message = new MessageUtil(user);
        this.user = user;
    }

    /**
     * Runs KOS.
     * @param pregeneratedWorld boolean - Is the world pregenerated?
     * @param profile string - The profile's filename (e.g. YouHaveTrouble.kos)
     */
    public void run(boolean pregeneratedWorld, String profile) {
        File f = new File(this.plugin.getDataFolder()+"/profiles/"+profile);
        if (f.exists()) {
            this.message.Success("Running the Kryptonite Optimisation System using the '"+profile+"' profile.");

            this.patches = new ConfigurationUtil(this.plugin, this.user);
            this.patches.load(this.plugin.getDataFolder()+"/profiles/"+profile);

            this.runVanilla();
            this.runCraftBukkit();
            this.runSpigot();
            this.runPaper(pregeneratedWorld);
            this.runPurpur(pregeneratedWorld);
            this.runPufferfish();

            this.message.Success("Done!");
            this.message.Info("See your server console for more logs.");
            this.message.Warning("You must restart your server for changes to be applied.");

            if (!this.softwareUtil.supportsPaperWorld()) {
                this.message.Error("");
                this.message.Error("You are using an unoptimised server jar!");
                this.message.Error("This is a problem that Kryptonite can't fix.");
                this.message.Error("");
                this.message.Error("We HIGHLY recommend using Paper for your server software.");
                this.message.Error("You are missing out on over 50 optimisations by not using Paper.");
                this.message.Error("You can download paper from papermc.io");
                this.message.Error("");
            }
        } else {
            this.log.severe("Unable to load the '"+profile+"' profile.");
            this.log.severe("Please verify that the file exists and try again.");
            this.log.severe("plugins/Kryptonite/profiles/"+profile);
        }
    }

    private void runVanilla() {
        if (this.softwareUtil.supportsServerProperties()) {
            this.log.info("[KOS] 1/6 - Running Vanilla optimisations");

            ServerProperties properties = new ServerProperties(this.plugin);

            properties.set(ServerProperties.Key.NETWORK_COMPRESSION_THRESHOLD, this.patches.getString("server.network-compression-threshold"));
            properties.set(ServerProperties.Key.SIMULATION_DISTANCE, this.patches.getString("server.distance.simulation"));
            properties.set(ServerProperties.Key.VIEW_DISTANCE, this.patches.getString("server.distance.view"));
            properties.set(ServerProperties.Key.SYNC_CHUNK_WRITES, this.patches.getString("server.sync-chunk-writes"));
        } else {
            this.log.info("[KOS] 2/6 - Server does not support Server Properties, skipping...");
            this.log.warn("[KOS] 2/6 - This shouldn't happen, please open an issue at github.com/lewmc/kryptonite");
        }
    }

    private void runCraftBukkit() {
        if (this.softwareUtil.supportsCraftBukkit()) {
            this.log.info("[KOS] 2/6 - Running CraftBukkit optimisations");

            Bukkit bukkit = new Bukkit(this.plugin, user);

            bukkit.setInt(Bukkit.Key.SPAWN_LIMITS_MONSTERS, this.patches.getInt("craftbukkit.spawn-limits.monsters"));
            bukkit.setInt(Bukkit.Key.SPAWN_LIMITS_ANIMALS, this.patches.getInt("craftbukkit.spawn-limits.animals"));
            bukkit.setInt(Bukkit.Key.SPAWN_LIMITS_WATER_ANIMALS, this.patches.getInt("craftbukkit.spawn-limits.water.animals"));
            bukkit.setInt(Bukkit.Key.SPAWN_LIMITS_WATER_AMBIENT, this.patches.getInt("craftbukkit.spawn-limits.water.ambient"));
            bukkit.setInt(Bukkit.Key.SPAWN_LIMITS_WATER_UNDERGROUND_CREATURE, this.patches.getInt("craftbukkit.spawn-limits.water.underground.creature"));
            bukkit.setInt(Bukkit.Key.SPAWN_LIMITS_AXOLOTLS, this.patches.getInt("craftbukkit.spawn-limits.axolotls"));
            bukkit.setInt(Bukkit.Key.SPAWN_LIMITS_AMBIENT, this.patches.getInt("craftbukkit.spawn-limits.ambient"));

            bukkit.setInt(Bukkit.Key.TICKS_PER_MONSTER_SPAWNS, this.patches.getInt("craftbukkit.ticks-per.monsters"));
            bukkit.setInt(Bukkit.Key.TICKS_PER_ANIMAL_SPAWNS, this.patches.getInt("craftbukkit.ticks-per.animals"));
            bukkit.setInt(Bukkit.Key.TICKS_PER_WATER_SPAWNS, this.patches.getInt("craftbukkit.ticks-per.water.animals"));
            bukkit.setInt(Bukkit.Key.TICKS_PER_WATER_AMBIENT_SPAWNS, this.patches.getInt("craftbukkit.ticks-per.water.ambient"));
            bukkit.setInt(Bukkit.Key.TICKS_PER_WATER_UNDERGROUND_CREATURE_SPAWNS, this.patches.getInt("craftbukkit.ticks-per.water.underground-creature"));
            bukkit.setInt(Bukkit.Key.TICKS_PER_AXOLOTL_SPAWNS, this.patches.getInt("craftbukkit.ticks-per.axolotls"));
            bukkit.setInt(Bukkit.Key.TICKS_PER_AMBIENT_SPAWNS, this.patches.getInt("craftbukkit.ticks-per.ambient"));

            bukkit.setInt(Bukkit.Key.CHUNK_GC_PERIOD_IN_TICKS, this.patches.getInt("craftbukkit.chunk-gc-period-in-ticks"));
        } else {
            this.log.info("[KOS] 2/6 - Server does not support CraftBukkit configurations, skipping...");
            this.log.warn("[KOS] 2/6 - This shouldn't happen, please open an issue at github.com/lewmc/kryptonite");
        }
    }

    private void runSpigot() {
        if (this.softwareUtil.supportsSpigot()) {
            this.log.info("[KOS] 3/6 - Running Spigot optimisations");

            Spigot spigot = new Spigot(this.plugin, this.user);

            spigot.setInt(Spigot.Key.VIEW_DISTANCE, this.patches.getInt("spigot.view-distance"));
            spigot.setInt(Spigot.Key.MOB_SPAWN_RANGE, this.patches.getInt("spigot.mob-spawn-range"));
            spigot.setInt(Spigot.Key.ENTITY_ACTIVATION_RANGE_ANIMALS, this.patches.getInt("spigot.entities.activation-range.animals"));
            spigot.setInt(Spigot.Key.ENTITY_ACTIVATION_RANGE_MONSTERS, this.patches.getInt("spigot.entities.activation-range.monsters"));
            spigot.setInt(Spigot.Key.ENTITY_ACTIVATION_RANGE_RAIDERS, this.patches.getInt("spigot.entities.activation-range.raiders"));
            spigot.setInt(Spigot.Key.ENTITY_ACTIVATION_RANGE_MISC, this.patches.getInt("spigot.entities.activation-range.misc"));
            spigot.setInt(Spigot.Key.ENTITY_ACTIVATION_RANGE_WATER, this.patches.getInt("spigot.entities.activation-range.water"));
            spigot.setInt(Spigot.Key.ENTITY_ACTIVATION_RANGE_VILLAGERS, this.patches.getInt("spigot.entities.activation-range.villagers"));
            spigot.setInt(Spigot.Key.ENTITY_ACTIVATION_RANGE_FLYING_MONSTERS, this.patches.getInt("spigot.entities.activation-range.flying"));
            spigot.setInt(Spigot.Key.ENTITY_TRACKING_RANGE_PLAYERS, this.patches.getInt("spigot.entities.tracking-range.players"));
            spigot.setInt(Spigot.Key.ENTITY_TRACKING_RANGE_ANIMALS, this.patches.getInt("spigot.entities.tracking-range.animals"));
            spigot.setInt(Spigot.Key.ENTITY_TRACKING_RANGE_MONSTERS, this.patches.getInt("spigot.entities.tracking-range.monsters"));
            spigot.setInt(Spigot.Key.ENTITY_TRACKING_RANGE_MISC, this.patches.getInt("spigot.entities.tracking-range.misc"));
            spigot.setInt(Spigot.Key.ENTITY_TRACKING_RANGE_OTHER, this.patches.getInt("spigot.entities.tracking-range.other"));
            spigot.setBoolean(Spigot.Key.TICK_INACTIVE_VILLAGERS, this.patches.getBoolean("spigot.entities.tick-inactive-villagers"));
            spigot.setBoolean(Spigot.Key.NERF_SPAWNER_MOBS, this.patches.getBoolean("spigot.entities.spawner-mobs-nerfed"));
            spigot.setInt(Spigot.Key.TICKS_PER_HOPPER_TRANSFER, this.patches.getInt("spigot.hopper.transfer"));
            spigot.setInt(Spigot.Key.TICKS_PER_HOPPER_CHECK, this.patches.getInt("spigot.hopper.check"));
        } else {
            log.info("[KOS] 3/6 - Server does not support Spigot configurations, skipping...");
        }
    }

    private void runPaper(boolean pregeneratedWorld) {
        if (this.softwareUtil.supportsPaperWorld()) {
            this.log.info("[KOS] 4/6 - Running Paper optimisations");

            PaperWorld pw = new PaperWorld(this.plugin, this.user);

            pw.setInt(PaperWorld.Key.DELAY_CHUNK_UNLOADS_BY, this.patches.getInt("paper.chunks.delay-unloads"));
            pw.setInt(PaperWorld.Key.MAX_AUTOSAVE_CHUNKS_PER_TICK, this.patches.getInt("paper.chunks.max-autosave-per-tick"));
            pw.setBoolean(PaperWorld.Key.PREVENT_MOVING_INTO_UNLOADED_CHUNKS, this.patches.getBoolean("paper.chunks.prevent-moving-into-unloaded"));

            pw.setInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_AREA_EFFECT_CLOUD, this.patches.getInt("paper.chunks.entity-save-limit.area-effect-cloud"));
            pw.setInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_ARROW, this.patches.getInt("paper.chunks.entity-save-limit.arrow"));
            pw.setInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_DRAGON_FIREBALL, this.patches.getInt("paper.chunks.entity-save-limit.dragon-fireball"));
            pw.setInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_EGG, this.patches.getInt("paper.chunks.entity-save-limit.egg"));
            pw.setInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_ENDER_PEARL, this.patches.getInt("paper.chunks.entity-save-limit.ender-pearl"));
            pw.setInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_EXPERIENCE_BOTTLE, this.patches.getInt("paper.chunks.entity-save-limit.experience-bottle"));
            pw.setInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_EXPERIENCE_ORB, this.patches.getInt("paper.chunks.entity-save-limit.experience-orb"));
            pw.setInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_EYE_OF_ENDER, this.patches.getInt("paper.chunks.entity-save-limit.eye-of-ender"));
            pw.setInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_FIREBALL, this.patches.getInt("paper.chunks.entity-save-limit.fireball"));
            pw.setInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_LLAMA_SPIT, this.patches.getInt("paper.chunks.entity-save-limit.llama-spit"));
            pw.setInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_POTION, this.patches.getInt("paper.chunks.entity-save-limit.potion"));
            pw.setInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_SHULKER_BULLET, this.patches.getInt("paper.chunks.entity-save-limit.shulker-bullet"));
            pw.setInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_SMALL_FIREBALL, this.patches.getInt("paper.chunks.entity-save-limit.small-fireball"));
            pw.setInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_SNOWBALL, this.patches.getInt("paper.chunks.entity-save-limit.snowball"));
            pw.setInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_SPECTRAL_ARROW, this.patches.getInt("paper.chunks.entity-save-limit.spectral-arrow"));
            pw.setInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_TRIDENT, this.patches.getInt("paper.chunks.entity-save-limit.trident"));
            pw.setInt(PaperWorld.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_WITHER_SKULL, this.patches.getInt("paper.chunks.entity-save-limit.wither-skull"));


            pw.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_AMBIENT_HARD, this.patches.getInt("paper.despawn-ranges.ambient.hard"));
            pw.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_AMBIENT_SOFT, this.patches.getInt("paper.despawn-ranges.ambient.soft"));

            pw.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_AXOLOTLS_HARD, this.patches.getInt("paper.despawn-ranges.axolotl.hard"));
            pw.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_AXOLOTLS_SOFT, this.patches.getInt("paper.despawn-ranges.axolotl.soft"));

            pw.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_CREATURE_HARD, this.patches.getInt("paper.despawn-ranges.creature.hard"));
            pw.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_CREATURE_SOFT, this.patches.getInt("paper.despawn-ranges.creature.soft"));

            pw.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_MISC_HARD, this.patches.getInt("paper.despawn-ranges.misc.hard"));
            pw.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_MISC_SOFT, this.patches.getInt("paper.despawn-ranges.misc.soft"));

            pw.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_MONSTER_HARD, this.patches.getInt("paper.despawn-ranges.monster.hard"));
            pw.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_MONSTER_SOFT, this.patches.getInt("paper.despawn-ranges.monster.soft"));

            pw.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_UNDERGROUND_WATER_CREATURE_HARD, this.patches.getInt("paper.despawn-ranges.water.underground-creature.hard"));
            pw.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_UNDERGROUND_WATER_CREATURE_SOFT, this.patches.getInt("paper.despawn-ranges.water.underground-creature.soft"));

            pw.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_WATER_AMBIENT_HARD, this.patches.getInt("paper.despawn-ranges.water.ambient.hard"));
            pw.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_WATER_AMBIENT_SOFT, this.patches.getInt("paper.despawn-ranges.water.ambient.soft"));

            pw.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_WATER_CREATURE_HARD, this.patches.getInt("paper.despawn-ranges.water.creature.hard"));
            pw.setInt(PaperWorld.Key.ENTITY_DESPAWN_RANGES_WATER_CREATURE_SOFT, this.patches.getInt("paper.despawn-ranges.water.creature.soft"));


            pw.setBoolean(PaperWorld.Key.PER_PLAYER_MOB_SPAWNS, this.patches.getBoolean("paper.per-player-mob-spawns"));
            pw.setInt(PaperWorld.Key.MAX_ENTITY_COLLISIONS, this.patches.getInt("paper.max-entity-collisions"));
            pw.setBoolean(PaperWorld.Key.UPDATE_PATHFINDING_ON_BLOCK_UPDATE, this.patches.getBoolean("paper.update-pathfinding-on-block-update"));
            pw.setBoolean(PaperWorld.Key.FIX_CLIMBING_BYPASSING_CRAMMING_RULE, this.patches.getBoolean("paper.fix-climbing-bypass-cramming-rule"));
            pw.setBoolean(PaperWorld.Key.ARMOR_STANDS_TICK, this.patches.getBoolean("paper.armor-stands.tick"));
            pw.setBoolean(PaperWorld.Key.ARMOR_STANDS_DO_COLLISION_ENTITY_LOOKUPS, this.patches.getBoolean("paper.armor-stands.do-collision-entity-lookups"));
            pw.setBoolean(PaperWorld.Key.NERFED_SPAWNER_MOBS_SHOULD_JUMP, this.patches.getBoolean("paper.nerfed-spawner-mobs-can-jump"));

            if (!this.softwareUtil.supportsPufferfish()) {
                pw.setInt(PaperWorld.Key.TICK_RATE_VILLAGER_VALIDATES_NEARBY_POI, this.patches.getInt("paper.tick-rates.villager.behaviour.nearby-poi"));
                pw.setInt(PaperWorld.Key.TICK_RATE_VILLAGER_ACQUIRE_POI, this.patches.getInt("paper.tick-rates.villager.behaviour.acquire-poi"));

                pw.setInt(PaperWorld.Key.TICK_RATE_VILLAGER_SENSOR_SECONDARY_POI, this.patches.getInt("paper.tick-rates.villager.sensor.secondary-poi"));
                pw.setInt(PaperWorld.Key.TICK_RATE_VILLAGER_SENSOR_NEAREST_BED, this.patches.getInt("paper.tick-rates.villager.sensor.nearest-bed"));
                pw.setInt(PaperWorld.Key.TICK_RATE_VILLAGER_SENSOR_VILLAGER_BABIES, this.patches.getInt("paper.tick-rates.villager.sensor.villager-babies"));
                pw.setInt(PaperWorld.Key.TICK_RATE_VILLAGER_SENSOR_PLAYER, this.patches.getInt("paper.tick-rates.villager.sensor.player"));
                pw.setInt(PaperWorld.Key.TICK_RATE_VILLAGER_SENSOR_NEAREST_LIVING_ENTITY, this.patches.getInt("paper.tick-rates.villager.sensor.nearest-living-entity"));

            } else {
                this.log.info("[KOS][4/6] You're running Pufferfish, skipping some steps due to incompatibility...");
            }

            pw.setBoolean(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_ENABLED, this.patches.getBoolean("paper.optimised-despawn.enabled"));

            pw.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_COBBLESTONE, this.patches.getInt("paper.optimised-despawn.cobblestone"));
            pw.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_NETHERRACK, this.patches.getInt("paper.optimised-despawn.netherrack"));
            pw.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_SAND, this.patches.getInt("paper.optimised-despawn.sand"));
            pw.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_RED_SAND, this.patches.getInt("paper.optimised-despawn.red-sand"));
            pw.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_GRAVEL, this.patches.getInt("paper.optimised-despawn.gravel"));
            pw.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_DIRT, this.patches.getInt("paper.optimised-despawn.dirt"));
            pw.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_SHORT_GRASS, this.patches.getInt("paper.optimised-despawn.short-grass"));
            pw.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_PUMPKIN, this.patches.getInt("paper.optimised-despawn.pumpkin"));
            pw.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_MELON_SLICE, this.patches.getInt("paper.optimised-despawn.melon-slice"));
            pw.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_KELP, this.patches.getInt("paper.optimised-despawn.kelp"));
            pw.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_BAMBOO, this.patches.getInt("paper.optimised-despawn.bamboo"));
            pw.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_SUGAR_CANE, this.patches.getInt("paper.optimised-despawn.sugar-cane"));
            pw.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_TWISTING_VINES, this.patches.getInt("paper.optimised-despawn.twisting-vines"));
            pw.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_WEEPING_VINES, this.patches.getInt("paper.optimised-despawn.weeping-vines"));
            pw.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_OAK_LEAVES, this.patches.getInt("paper.optimised-despawn.oak-leaves"));
            pw.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_SPRUCE_LEAVES, this.patches.getInt("paper.optimised-despawn.spruce-leaves"));
            pw.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_BIRCH_LEAVES, this.patches.getInt("paper.optimised-despawn.birch-leaves"));
            pw.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_JUNGLE_LEAVES, this.patches.getInt("paper.optimised-despawn.jungle-leaves"));
            pw.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_ACACIA_LEAVES, this.patches.getInt("paper.optimised-despawn.acacia-leaves"));
            pw.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_DARK_OAK_LEAVES, this.patches.getInt("paper.optimised-despawn.dark-oak-leaves"));
            pw.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_MANGROVE_LEAVES, this.patches.getInt("paper.optimised-despawn.mangrove-leaves"));
            pw.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_CACTUS, this.patches.getInt("paper.optimised-despawn.cactus"));
            pw.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_DIORITE, this.patches.getInt("paper.optimised-despawn.diorite"));
            pw.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_GRANITE, this.patches.getInt("paper.optimised-despawn.granite"));
            pw.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_ANDESITE, this.patches.getInt("paper.optimised-despawn.andesite"));
            pw.setInt(PaperWorld.Key.ALT_ITEM_DESPAWN_RATE_SCAFFOLDING, this.patches.getInt("paper.optimised-despawn.scaffolding"));


            pw.setString(PaperWorld.Key.REDSTONE_IMPLEMENTATION, this.patches.getString("paper.redstone-implementation"));
            pw.setBoolean(PaperWorld.Key.HOPPER_DISABLE_MOVE_EVENT, this.patches.getBoolean("paper.hoppers.disable-move-event"));
            pw.setBoolean(PaperWorld.Key.HOPPER_IGNORE_OCCLUDING_BLOCKS, this.patches.getBoolean("paper.hoppers.ignore-occluding-blocks"));
            pw.setInt(PaperWorld.Key.TICK_RATE_MOB_SPAWNER, this.patches.getInt("paper.tick-rates.mob-spawner"));
            pw.setBoolean(PaperWorld.Key.OPTIMIZE_EXPLOSIONS, this.patches.getBoolean("paper.optimise-explosions"));

            if (pregeneratedWorld) {
                this.log.info("[KOS][4/6] World is pregenerated, enabling treasure maps...");
                pw.setBoolean(PaperWorld.Key.TREASURE_MAPS_ENABLED, true);
            } else {
                if (this.plugin.getConfig().getBoolean("kos.override-pregenerated-world-protections")) {
                    pw.setBoolean(PaperWorld.Key.TREASURE_MAPS_ENABLED, true);
                    this.log.warn("[KOS][4/6] override-pregenerated-world-protections is TRUE, enabling treasure maps. This may cause lag.");
                } else {
                    pw.setBoolean(PaperWorld.Key.TREASURE_MAPS_ENABLED, false);
                    this.log.info("[KOS][4/6] World not pregenerated, disabling treasure maps...");
                    this.message.Warning("Treasure maps have been disabled, please pre-generate your world to re-enable them.");
                }
            }

            pw.setBoolean(PaperWorld.Key.TREASURE_MAPS_FIND_ALREADY_DISCOVERED_LOOT_TABLES, this.patches.getBoolean("paper.find-already-discovered-loot-tables"));
            pw.setBoolean(PaperWorld.Key.TREASURE_MAPS_FIND_ALREADY_DISCOVERED_VILLAGER_TRADE, this.patches.getBoolean("paper.find-already-discovered-villager-trade"));
            pw.setInt(PaperWorld.Key.TICK_RATE_GRASS_SPREAD, this.patches.getInt("paper.tick-rates.grass-spread"));
            pw.setInt(PaperWorld.Key.TICK_RATE_CONTAINER_UPDATE, this.patches.getInt("paper.tick-rates.container-update"));
            pw.setInt(PaperWorld.Key.ARROW_DESPAWN_RATE_NON_PLAYER, this.patches.getInt("paper.optimised-despawn.arrow.non-player"));
            pw.setInt(PaperWorld.Key.ARROW_DESPAWN_RATE_CREATIVE, this.patches.getInt("paper.optimised-despawn.arrow.creative"));
        } else {
            log.info("[KOS] 4/6 - Server does not support Paper World configurations, skipping...");
        }
    }

    private void runPurpur(boolean pregeneratedWorld) {
        if (this.softwareUtil.supportsPurpur()) {
            this.log.info("[KOS] 5/6 - Running Purpur optimisations");

            Purpur purpur = new Purpur(this.plugin, this.user);
            if (this.plugin.getConfig().getBoolean("kos.using-tcpshield")) {
                this.log.info("[KOS] 5/6 - You're using TCPShield, disabling use-alternative-keepalive.");
                purpur.setBoolean(Purpur.Key.USE_ALTERNATE_KEEPALIVE, false);
            } else {
                purpur.setBoolean(Purpur.Key.USE_ALTERNATE_KEEPALIVE, this.patches.getBoolean("purpur.use-alternative-keepalive"));
            }

            purpur.setBoolean(Purpur.Key.ZOMBIE_AGGRESSIVE_TOWARDS_VILLAGER_WHEN_LAGGING, this.patches.getBoolean("purpur.entities.zombie.aggressive-towards-villager-when-lagging"));
            purpur.setBoolean(Purpur.Key.ENTITIES_CAN_USE_PORTALS, this.patches.getBoolean("purpur.entities.all.can-use-portals"));
            purpur.setBoolean(Purpur.Key.VILLAGER_IS_LOBOTOMIZED, this.patches.getBoolean("purpur.entities.villager.lobotomized"));
            purpur.setInt(Purpur.Key.VILLAGER_SEARCH_RADIUS_ACQUIRE_POI, this.patches.getInt("purpur.entities.villager.search-radius.acquire-poi"));
            purpur.setInt(Purpur.Key.VILLAGER_SEARCH_RADIUS_NEAREST_BED_SENSOR, this.patches.getInt("purpur.entities.villager.search-radius.nearest-bed-sensor"));

            if (pregeneratedWorld) {
                this.log.info("[KOS][4/6] World is pregenerated, enabling dolphin treasure searching...");
                purpur.setBoolean(Purpur.Key.DOLPHIN_DISABLE_TREASURE_SEARCHING, false);
            } else {
                if (this.plugin.getConfig().getBoolean("kos.override-pregenerated-world-protections")) {
                    purpur.setBoolean(Purpur.Key.DOLPHIN_DISABLE_TREASURE_SEARCHING, false);
                    this.log.warn("[KOS][4/6] override-pregenerated-world-protections is TRUE, enabling dolphin treasure searching. This may cause lag.");
                } else {
                    purpur.setBoolean(Purpur.Key.DOLPHIN_DISABLE_TREASURE_SEARCHING, true);
                    this.log.info("[KOS][4/6] World not pregenerated, disabling dolphin treasure searching...");
                    this.message.Warning("Dolphin treasure searching has been disabled, please pre-generate your world to re-enable this.");
                }
            }

            purpur.setBoolean(Purpur.Key.TELEPORT_IF_OUTSIDE_BORDER, this.patches.getBoolean("purpur.teleport-if-outside-worldborder"));
            purpur.setInt(Purpur.Key.LAGGING_THRESHOLD, this.patches.getInt("purpur.lagging-tps-threshold"));

        } else {
            this.log.info("[KOS] 5/6 - Server does not support Purpur configurations, skipping...");
        }
    }

    private void runPufferfish() {
        if (this.softwareUtil.supportsPufferfish()) {
            this.log.info("[KOS] 6/6 - Running Pufferfish optimisations");
            Pufferfish pufferfish = new Pufferfish(this.plugin, this.user);

            pufferfish.setInt(Pufferfish.Key.MAX_LOADS_PER_PROJECTILE, this.patches.getInt("pufferfish.max-loads-per-projectile"));
            pufferfish.setBoolean(Pufferfish.Key.DAB_ENABLED, this.patches.getBoolean("pufferfish.entities.dynamic-activation-of-brain.enabled"));
            pufferfish.setInt(Pufferfish.Key.DAB_MAX_TICK_FREQ, this.patches.getInt("pufferfish.entities.dynamic-activation-of-brain.max-tick-freq"));
            pufferfish.setInt(Pufferfish.Key.DAB_ACTIVATION_DIST_MOD, this.patches.getInt("pufferfish.entities.dynamic-activation-of-brain.activation-distance-modifier"));
            pufferfish.setBoolean(Pufferfish.Key.ENABLE_ASYNC_MOB_SPAWNING, this.patches.getBoolean("pufferfish.entities.async-mob-spawning"));
            pufferfish.setBoolean(Pufferfish.Key.ENABLE_SUFFOCATION_OPTIMIZATION, this.patches.getBoolean("pufferfish.entities.suffocation-optimisation"));
            pufferfish.setBoolean(Pufferfish.Key.INACTIVE_GOAL_SELECTOR_THROTTLE, this.patches.getBoolean("pufferfish.entities.inactive-goal-selector-throttle"));
            pufferfish.setBoolean(Pufferfish.Key.DISABLE_METHOD_PROFILER, this.patches.getBoolean("pufferfish.disable-method-profiler"));
        } else {
            this.log.info("[KOS] 6/6 - Server does not support Pufferfish configurations, skipping...");
        }
    }
}

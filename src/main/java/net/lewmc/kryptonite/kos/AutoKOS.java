package net.lewmc.kryptonite.kos;

import net.lewmc.foundry.Logger;
import net.lewmc.kryptonite.Kryptonite;
import net.lewmc.kryptonite.config.*;
import net.lewmc.kryptonite.kos.config.*;
import net.lewmc.kryptonite.utils.ConfigurationUtil;
import net.lewmc.kryptonite.utils.MessageUtil;
import net.lewmc.kryptonite.utils.SoftwareUtil;
import net.lewmc.kryptonite.utils.config.BooleanConfigItem;
import net.lewmc.kryptonite.utils.config.IntegerConfigItem;
import net.lewmc.kryptonite.utils.config.StringConfigItem;
import org.bukkit.command.CommandSender;

import java.io.File;

/**
 * The AutoKOS class.
 */
public class AutoKOS {
    private final Kryptonite plugin;
    private final Logger log;
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
        this.log = new Logger(plugin.foundryConfig);
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
        if (!profile.endsWith(".kos")) {
            profile = profile + ".kos";
        }

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
        if (this.softwareUtil.supportsMinecraft()) {
            this.log.info("[KOS] 1/6 - Running Vanilla optimisations");

            MinecraftConfig m = new MinecraftConfig(this.plugin);

            ((IntegerConfigItem)m.values.get(MinecraftConfig.Key.NETWORK_COMPRESSION_THRESHOLD.toString())).setValue(this.patches.getInt("server.network-compression-threshold"));
            ((IntegerConfigItem)m.values.get(MinecraftConfig.Key.SIMULATION_DISTANCE.toString())).setValue(this.patches.getInt("server.distance.simulation"));
            ((IntegerConfigItem)m.values.get(MinecraftConfig.Key.VIEW_DISTANCE.toString())).setValue(this.patches.getInt("server.distance.view"));
            ((BooleanConfigItem)m.values.get(MinecraftConfig.Key.SYNC_CHUNK_WRITES.toString())).setValue(this.patches.getBoolean("server.sync-chunk-writes"));
            ((BooleanConfigItem)m.values.get(MinecraftConfig.Key.ALLOW_FLIGHT.toString())).setValue(this.patches.getBoolean("server.allow-flight"));
        } else {
            this.log.info("[KOS] 1/6 - Server does not support Server Properties, skipping...");
            this.log.warn("[KOS] 1/6 - This shouldn't happen, please contact LewMC for help at lewmc.net/help");
        }
    }

    private void runCraftBukkit() {
        if (this.softwareUtil.supportsCraftBukkit()) {
            this.log.info("[KOS] 2/6 - Running CraftBukkit optimisations");

            BukkitConfig bukkit = new BukkitConfig(this.plugin);

            ((IntegerConfigItem)bukkit.values.get(BukkitConfig.Key.SPAWN_LIMITS_MONSTERS.toString())).setValue(this.patches.getInt("craftbukkit.spawn-limits.monsters"));
            ((IntegerConfigItem)bukkit.values.get(BukkitConfig.Key.SPAWN_LIMITS_ANIMALS.toString())).setValue(this.patches.getInt("craftbukkit.spawn-limits.animals"));
            ((IntegerConfigItem)bukkit.values.get(BukkitConfig.Key.SPAWN_LIMITS_WATER_AMBIENT.toString())).setValue(this.patches.getInt("craftbukkit.spawn-limits.water.ambient"));
            ((IntegerConfigItem)bukkit.values.get(BukkitConfig.Key.SPAWN_LIMITS_WATER_UNDERGROUND_CREATURE.toString())).setValue(this.patches.getInt("craftbukkit.spawn-limits.water.underground.creature"));
            ((IntegerConfigItem)bukkit.values.get(BukkitConfig.Key.SPAWN_LIMITS_AXOLOTLS.toString())).setValue(this.patches.getInt("craftbukkit.spawn-limits.axolotls"));
            ((IntegerConfigItem)bukkit.values.get(BukkitConfig.Key.SPAWN_LIMITS_AMBIENT.toString())).setValue(this.patches.getInt("craftbukkit.spawn-limits.ambient"));

            ((IntegerConfigItem)bukkit.values.get(BukkitConfig.Key.TICKS_PER_MONSTER_SPAWNS.toString())).setValue(this.patches.getInt("craftbukkit.ticks-per.monsters"));
            ((IntegerConfigItem)bukkit.values.get(BukkitConfig.Key.TICKS_PER_ANIMAL_SPAWNS.toString())).setValue(this.patches.getInt("craftbukkit.ticks-per.animals"));
            ((IntegerConfigItem)bukkit.values.get(BukkitConfig.Key.TICKS_PER_WATER_SPAWNS.toString())).setValue(this.patches.getInt("craftbukkit.ticks-per.water.animals"));
            ((IntegerConfigItem)bukkit.values.get(BukkitConfig.Key.TICKS_PER_WATER_AMBIENT_SPAWNS.toString())).setValue(this.patches.getInt("craftbukkit.ticks-per.water.ambient"));
            ((IntegerConfigItem)bukkit.values.get(BukkitConfig.Key.TICKS_PER_WATER_UNDERGROUND_CREATURE_SPAWNS.toString())).setValue(this.patches.getInt("craftbukkit.ticks-per.water.underground-creature"));
            ((IntegerConfigItem)bukkit.values.get(BukkitConfig.Key.TICKS_PER_AXOLOTL_SPAWNS.toString())).setValue(this.patches.getInt("craftbukkit.ticks-per.axolotls"));
            ((IntegerConfigItem)bukkit.values.get(BukkitConfig.Key.TICKS_PER_AMBIENT_SPAWNS.toString())).setValue(this.patches.getInt("craftbukkit.ticks-per.ambient"));

            ((IntegerConfigItem)bukkit.values.get(BukkitConfig.Key.CHUNK_GC_PERIOD_IN_TICKS.toString())).setValue(this.patches.getInt("craftbukkit.chunk-gc-period-in-ticks"));
        } else {
            this.log.info("[KOS] 2/6 - Server does not support CraftBukkit configurations, skipping...");
            this.log.warn("[KOS] 2/6 - This shouldn't happen, please open an issue at github.com/lewmc/kryptonite");
        }
    }

    private void runSpigot() {
        if (this.softwareUtil.supportsSpigot()) {
            this.log.info("[KOS] 3/6 - Running Spigot optimisations");

            SpigotConfig spigot = new SpigotConfig(this.plugin);

            ((StringConfigItem)spigot.values.get(SpigotConfig.Key.VIEW_DISTANCE.toString())).setValue(this.patches.getString("spigot.view-distance"));
            ((IntegerConfigItem)spigot.values.get(SpigotConfig.Key.MOB_SPAWN_RANGE.toString())).setValue(this.patches.getInt("spigot.mob-spawn-range"));
            ((IntegerConfigItem)spigot.values.get(SpigotConfig.Key.ENTITY_ACTIVATION_RANGE_ANIMALS.toString())).setValue(this.patches.getInt("spigot.entities.activation-range.animals"));
            ((IntegerConfigItem)spigot.values.get(SpigotConfig.Key.ENTITY_ACTIVATION_RANGE_MONSTERS.toString())).setValue(this.patches.getInt("spigot.entities.activation-range.monsters"));
            ((IntegerConfigItem)spigot.values.get(SpigotConfig.Key.ENTITY_ACTIVATION_RANGE_RAIDERS.toString())).setValue(this.patches.getInt("spigot.entities.activation-range.raiders"));
            ((IntegerConfigItem)spigot.values.get(SpigotConfig.Key.ENTITY_ACTIVATION_RANGE_MISC.toString())).setValue(this.patches.getInt("spigot.entities.activation-range.misc"));
            ((IntegerConfigItem)spigot.values.get(SpigotConfig.Key.ENTITY_ACTIVATION_RANGE_WATER.toString())).setValue(this.patches.getInt("spigot.entities.activation-range.water"));
            ((IntegerConfigItem)spigot.values.get(SpigotConfig.Key.ENTITY_ACTIVATION_RANGE_VILLAGERS.toString())).setValue(this.patches.getInt("spigot.entities.activation-range.villagers"));
            ((IntegerConfigItem)spigot.values.get(SpigotConfig.Key.ENTITY_ACTIVATION_RANGE_FLYING_MONSTERS.toString())).setValue(this.patches.getInt("spigot.entities.activation-range.flying"));
            ((IntegerConfigItem)spigot.values.get(SpigotConfig.Key.ENTITY_TRACKING_RANGE_PLAYERS.toString())).setValue(this.patches.getInt("spigot.entities.tracking-range.players"));
            ((IntegerConfigItem)spigot.values.get(SpigotConfig.Key.ENTITY_TRACKING_RANGE_ANIMALS.toString())).setValue(this.patches.getInt("spigot.entities.tracking-range.animals"));
            ((IntegerConfigItem)spigot.values.get(SpigotConfig.Key.ENTITY_TRACKING_RANGE_MONSTERS.toString())).setValue(this.patches.getInt("spigot.entities.tracking-range.monsters"));
            ((IntegerConfigItem)spigot.values.get(SpigotConfig.Key.ENTITY_TRACKING_RANGE_MISC.toString())).setValue(this.patches.getInt("spigot.entities.tracking-range.misc"));
            ((IntegerConfigItem)spigot.values.get(SpigotConfig.Key.ENTITY_TRACKING_RANGE_OTHER.toString())).setValue(this.patches.getInt("spigot.entities.tracking-range.other"));
            ((BooleanConfigItem)spigot.values.get(SpigotConfig.Key.TICK_INACTIVE_VILLAGERS.toString())).setValue(this.patches.getBoolean("spigot.entities.tick-inactive-villagers"));
            ((BooleanConfigItem)spigot.values.get(SpigotConfig.Key.NERF_SPAWNER_MOBS.toString())).setValue(this.patches.getBoolean("spigot.entities.spawner-mobs-nerfed"));
            ((IntegerConfigItem)spigot.values.get(SpigotConfig.Key.TICKS_PER_HOPPER_TRANSFER.toString())).setValue(this.patches.getInt("spigot.hopper.transfer"));
            ((IntegerConfigItem)spigot.values.get(SpigotConfig.Key.TICKS_PER_HOPPER_CHECK.toString())).setValue(this.patches.getInt("spigot.hopper.check"));
        } else {
            log.info("[KOS] 3/6 - Server does not support Spigot configurations, skipping...");
        }
    }

    private void runPaper(boolean pregeneratedWorld) {
        if (this.softwareUtil.supportsPaperWorld()) {
            this.log.info("[KOS] 4/6 - Running Paper optimisations");

            PaperWorldConfig pw = new PaperWorldConfig(this.plugin);

            ((IntegerConfigItem)pw.values.get(PaperWorldConfig.Key.DELAY_CHUNK_UNLOADS_BY.toString())).setValue(this.patches.getInt("paper.chunks.delay-unloads"));
            ((IntegerConfigItem)pw.values.get(PaperWorldConfig.Key.MAX_AUTOSAVE_CHUNKS_PER_TICK.toString())).setValue(this.patches.getInt("paper.chunks.max-autosave-per-tick"));
            ((BooleanConfigItem)pw.values.get(PaperWorldConfig.Key.PREVENT_MOVING_INTO_UNLOADED_CHUNKS.toString())).setValue(this.patches.getBoolean("paper.chunks.prevent-moving-into-unloaded"));

            ((IntegerConfigItem)pw.values.get(PaperWorldConfig.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_AREA_EFFECT_CLOUD.toString())).setValue(this.patches.getInt("paper.chunks.entity-save-limit.area-effect-cloud"));
            ((IntegerConfigItem)pw.values.get(PaperWorldConfig.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_ARROW.toString())).setValue(this.patches.getInt("paper.chunks.entity-save-limit.arrow"));
            ((IntegerConfigItem)pw.values.get(PaperWorldConfig.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_DRAGON_FIREBALL.toString())).setValue(this.patches.getInt("paper.chunks.entity-save-limit.dragon-fireball"));
            ((IntegerConfigItem)pw.values.get(PaperWorldConfig.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_EGG.toString())).setValue(this.patches.getInt("paper.chunks.entity-save-limit.egg"));
            ((IntegerConfigItem)pw.values.get(PaperWorldConfig.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_ENDER_PEARL.toString())).setValue(this.patches.getInt("paper.chunks.entity-save-limit.ender-pearl"));
            ((IntegerConfigItem)pw.values.get(PaperWorldConfig.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_EXPERIENCE_BOTTLE.toString())).setValue(this.patches.getInt("paper.chunks.entity-save-limit.experience-bottle"));
            ((IntegerConfigItem)pw.values.get(PaperWorldConfig.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_EXPERIENCE_ORB.toString())).setValue(this.patches.getInt("paper.chunks.entity-save-limit.experience-orb"));
            ((IntegerConfigItem)pw.values.get(PaperWorldConfig.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_EYE_OF_ENDER.toString())).setValue(this.patches.getInt("paper.chunks.entity-save-limit.eye-of-ender"));
            ((IntegerConfigItem)pw.values.get(PaperWorldConfig.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_FIREBALL.toString())).setValue(this.patches.getInt("paper.chunks.entity-save-limit.fireball"));
            ((IntegerConfigItem)pw.values.get(PaperWorldConfig.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_LLAMA_SPIT.toString())).setValue(this.patches.getInt("paper.chunks.entity-save-limit.llama-spit"));
            ((IntegerConfigItem)pw.values.get(PaperWorldConfig.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_POTION.toString())).setValue(this.patches.getInt("paper.chunks.entity-save-limit.potion"));
            ((IntegerConfigItem)pw.values.get(PaperWorldConfig.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_SHULKER_BULLET.toString())).setValue(this.patches.getInt("paper.chunks.entity-save-limit.shulker-bullet"));
            ((IntegerConfigItem)pw.values.get(PaperWorldConfig.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_SMALL_FIREBALL.toString())).setValue(this.patches.getInt("paper.chunks.entity-save-limit.small-fireball"));
            ((IntegerConfigItem)pw.values.get(PaperWorldConfig.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_SNOWBALL.toString())).setValue(this.patches.getInt("paper.chunks.entity-save-limit.snowball"));
            ((IntegerConfigItem)pw.values.get(PaperWorldConfig.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_SPECTRAL_ARROW.toString())).setValue(this.patches.getInt("paper.chunks.entity-save-limit.spectral-arrow"));
            ((IntegerConfigItem)pw.values.get(PaperWorldConfig.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_TRIDENT.toString())).setValue(this.patches.getInt("paper.chunks.entity-save-limit.trident"));
            ((IntegerConfigItem)pw.values.get(PaperWorldConfig.Key.ENTITY_PER_CHUNK_SAVE_LIMIT_WITHER_SKULL.toString())).setValue(this.patches.getInt("paper.chunks.entity-save-limit.wither-skull"));

            ((StringConfigItem)pw.values.get(PaperWorldConfig.Key.ENTITY_DESPAWN_RANGES_AMBIENT_HARD.toString())).setValue(this.patches.getString("paper.despawn-ranges.ambient.hard"));
            ((StringConfigItem)pw.values.get(PaperWorldConfig.Key.ENTITY_DESPAWN_RANGES_AMBIENT_SOFT.toString())).setValue(this.patches.getString("paper.despawn-ranges.ambient.soft"));

            ((StringConfigItem)pw.values.get(PaperWorldConfig.Key.ENTITY_DESPAWN_RANGES_AXOLOTLS_HARD.toString())).setValue(this.patches.getString("paper.despawn-ranges.axolotl.hard"));
            ((StringConfigItem)pw.values.get(PaperWorldConfig.Key.ENTITY_DESPAWN_RANGES_AXOLOTLS_SOFT.toString())).setValue(this.patches.getString("paper.despawn-ranges.axolotl.soft"));

            ((StringConfigItem)pw.values.get(PaperWorldConfig.Key.ENTITY_DESPAWN_RANGES_CREATURE_HARD.toString())).setValue(this.patches.getString("paper.despawn-ranges.creature.hard"));
            ((StringConfigItem)pw.values.get(PaperWorldConfig.Key.ENTITY_DESPAWN_RANGES_CREATURE_SOFT.toString())).setValue(this.patches.getString("paper.despawn-ranges.creature.soft"));

            ((StringConfigItem)pw.values.get(PaperWorldConfig.Key.ENTITY_DESPAWN_RANGES_MISC_HARD.toString())).setValue(this.patches.getString("paper.despawn-ranges.misc.hard"));
            ((StringConfigItem)pw.values.get(PaperWorldConfig.Key.ENTITY_DESPAWN_RANGES_MISC_SOFT.toString())).setValue(this.patches.getString("paper.despawn-ranges.misc.soft"));

            ((StringConfigItem)pw.values.get(PaperWorldConfig.Key.ENTITY_DESPAWN_RANGES_MONSTER_HARD.toString())).setValue(this.patches.getString("paper.despawn-ranges.monster.hard"));
            ((StringConfigItem)pw.values.get(PaperWorldConfig.Key.ENTITY_DESPAWN_RANGES_MONSTER_SOFT.toString())).setValue(this.patches.getString("paper.despawn-ranges.monster.soft"));

            ((StringConfigItem)pw.values.get(PaperWorldConfig.Key.ENTITY_DESPAWN_RANGES_UNDERGROUND_WATER_CREATURE_HARD.toString())).setValue(this.patches.getString("paper.despawn-ranges.water.underground-creature.hard"));
            ((StringConfigItem)pw.values.get(PaperWorldConfig.Key.ENTITY_DESPAWN_RANGES_UNDERGROUND_WATER_CREATURE_SOFT.toString())).setValue(this.patches.getString("paper.despawn-ranges.water.underground-creature.soft"));

            ((StringConfigItem)pw.values.get(PaperWorldConfig.Key.ENTITY_DESPAWN_RANGES_WATER_AMBIENT_HARD.toString())).setValue(this.patches.getString("paper.despawn-ranges.water.ambient.hard"));
            ((StringConfigItem)pw.values.get(PaperWorldConfig.Key.ENTITY_DESPAWN_RANGES_WATER_AMBIENT_SOFT.toString())).setValue(this.patches.getString("paper.despawn-ranges.water.ambient.soft"));

            ((StringConfigItem)pw.values.get(PaperWorldConfig.Key.ENTITY_DESPAWN_RANGES_WATER_CREATURE_HARD.toString())).setValue(this.patches.getString("paper.despawn-ranges.water.creature.hard"));
            ((StringConfigItem)pw.values.get(PaperWorldConfig.Key.ENTITY_DESPAWN_RANGES_WATER_CREATURE_SOFT.toString())).setValue(this.patches.getString("paper.despawn-ranges.water.creature.soft"));

            ((BooleanConfigItem)pw.values.get(PaperWorldConfig.Key.PER_PLAYER_MOB_SPAWNS.toString())).setValue(this.patches.getBoolean("paper.per-player-mob-spawns"));
            ((IntegerConfigItem)pw.values.get(PaperWorldConfig.Key.MAX_ENTITY_COLLISIONS.toString())).setValue(this.patches.getInt("paper.max-entity-collisions"));
            ((BooleanConfigItem)pw.values.get(PaperWorldConfig.Key.UPDATE_PATHFINDING_ON_BLOCK_UPDATE.toString())).setValue(this.patches.getBoolean("paper.update-pathfinding-on-block-update"));
            ((BooleanConfigItem)pw.values.get(PaperWorldConfig.Key.FIX_CLIMBING_BYPASSING_CRAMMING_RULE.toString())).setValue(this.patches.getBoolean("paper.fix-climbing-bypass-cramming-rule"));
            ((BooleanConfigItem)pw.values.get(PaperWorldConfig.Key.ARMOR_STANDS_TICK.toString())).setValue(this.patches.getBoolean("paper.armor-stands.tick"));
            ((BooleanConfigItem)pw.values.get(PaperWorldConfig.Key.ARMOR_STANDS_DO_COLLISION_ENTITY_LOOKUPS.toString())).setValue(this.patches.getBoolean("paper.armor-stands.do-collision-entity-lookups"));
            ((BooleanConfigItem)pw.values.get(PaperWorldConfig.Key.NERFED_SPAWNER_MOBS_SHOULD_JUMP.toString())).setValue(this.patches.getBoolean("paper.nerfed-spawner-mobs-can-jump"));

            if (!this.softwareUtil.supportsPufferfish()) {
                ((IntegerConfigItem)pw.values.get(PaperWorldConfig.Key.TICK_RATE_VILLAGER_VALIDATES_NEARBY_POI.toString())).setValue(this.patches.getInt("paper.tick-rates.villager.behaviour.nearby-poi"));
                ((IntegerConfigItem)pw.values.get(PaperWorldConfig.Key.TICK_RATE_VILLAGER_ACQUIRE_POI.toString())).setValue(this.patches.getInt("paper.tick-rates.villager.behaviour.acquire-poi"));

                ((IntegerConfigItem)pw.values.get(PaperWorldConfig.Key.TICK_RATE_VILLAGER_SENSOR_SECONDARY_POI.toString())).setValue(this.patches.getInt("paper.tick-rates.villager.sensor.secondary-poi"));
                ((IntegerConfigItem)pw.values.get(PaperWorldConfig.Key.TICK_RATE_VILLAGER_SENSOR_NEAREST_BED.toString())).setValue(this.patches.getInt("paper.tick-rates.villager.sensor.nearest-bed"));
                ((IntegerConfigItem)pw.values.get(PaperWorldConfig.Key.TICK_RATE_VILLAGER_SENSOR_VILLAGER_BABIES.toString())).setValue(this.patches.getInt("paper.tick-rates.villager.sensor.villager-babies"));
                ((IntegerConfigItem)pw.values.get(PaperWorldConfig.Key.TICK_RATE_VILLAGER_SENSOR_PLAYER.toString())).setValue(this.patches.getInt("paper.tick-rates.villager.sensor.player"));
                ((IntegerConfigItem)pw.values.get(PaperWorldConfig.Key.TICK_RATE_VILLAGER_SENSOR_NEAREST_LIVING_ENTITY.toString())).setValue(this.patches.getInt("paper.tick-rates.villager.sensor.nearest-living-entity"));
            } else {
                this.log.info("[KOS][4/6] You're running Pufferfish, skipping some steps due to incompatibility...");
            }

            ((BooleanConfigItem)pw.values.get(PaperWorldConfig.Key.ALT_ITEM_DESPAWN_RATE_ENABLED.toString())).setValue(this.patches.getBoolean("paper.optimised-despawn.enabled"));

            ((IntegerConfigItem)pw.values.get(PaperWorldConfig.Key.ALT_ITEM_DESPAWN_RATE_COBBLESTONE.toString())).setValue(this.patches.getInt("paper.optimised-despawn.cobblestone"));
            ((IntegerConfigItem)pw.values.get(PaperWorldConfig.Key.ALT_ITEM_DESPAWN_RATE_NETHERRACK.toString())).setValue(this.patches.getInt("paper.optimised-despawn.netherrack"));
            ((IntegerConfigItem)pw.values.get(PaperWorldConfig.Key.ALT_ITEM_DESPAWN_RATE_SAND.toString())).setValue(this.patches.getInt("paper.optimised-despawn.sand"));
            ((IntegerConfigItem)pw.values.get(PaperWorldConfig.Key.ALT_ITEM_DESPAWN_RATE_RED_SAND.toString())).setValue(this.patches.getInt("paper.optimised-despawn.red-sand"));
            ((IntegerConfigItem)pw.values.get(PaperWorldConfig.Key.ALT_ITEM_DESPAWN_RATE_GRAVEL.toString())).setValue(this.patches.getInt("paper.optimised-despawn.gravel"));
            ((IntegerConfigItem)pw.values.get(PaperWorldConfig.Key.ALT_ITEM_DESPAWN_RATE_DIRT.toString())).setValue(this.patches.getInt("paper.optimised-despawn.dirt"));
            ((IntegerConfigItem)pw.values.get(PaperWorldConfig.Key.ALT_ITEM_DESPAWN_RATE_SHORT_GRASS.toString())).setValue(this.patches.getInt("paper.optimised-despawn.short-grass"));
            ((IntegerConfigItem)pw.values.get(PaperWorldConfig.Key.ALT_ITEM_DESPAWN_RATE_PUMPKIN.toString())).setValue(this.patches.getInt("paper.optimised-despawn.pumpkin"));
            ((IntegerConfigItem)pw.values.get(PaperWorldConfig.Key.ALT_ITEM_DESPAWN_RATE_MELON_SLICE.toString())).setValue(this.patches.getInt("paper.optimised-despawn.melon-slice"));
            ((IntegerConfigItem)pw.values.get(PaperWorldConfig.Key.ALT_ITEM_DESPAWN_RATE_KELP.toString())).setValue(this.patches.getInt("paper.optimised-despawn.kelp"));
            ((IntegerConfigItem)pw.values.get(PaperWorldConfig.Key.ALT_ITEM_DESPAWN_RATE_BAMBOO.toString())).setValue(this.patches.getInt("paper.optimised-despawn.bamboo"));
            ((IntegerConfigItem)pw.values.get(PaperWorldConfig.Key.ALT_ITEM_DESPAWN_RATE_SUGAR_CANE.toString())).setValue(this.patches.getInt("paper.optimised-despawn.sugar-cane"));
            ((IntegerConfigItem)pw.values.get(PaperWorldConfig.Key.ALT_ITEM_DESPAWN_RATE_TWISTING_VINES.toString())).setValue(this.patches.getInt("paper.optimised-despawn.twisting-vines"));
            ((IntegerConfigItem)pw.values.get(PaperWorldConfig.Key.ALT_ITEM_DESPAWN_RATE_WEEPING_VINES.toString())).setValue(this.patches.getInt("paper.optimised-despawn.weeping-vines"));
            ((IntegerConfigItem)pw.values.get(PaperWorldConfig.Key.ALT_ITEM_DESPAWN_RATE_OAK_LEAVES.toString())).setValue(this.patches.getInt("paper.optimised-despawn.oak-leaves"));
            ((IntegerConfigItem)pw.values.get(PaperWorldConfig.Key.ALT_ITEM_DESPAWN_RATE_SPRUCE_LEAVES.toString())).setValue(this.patches.getInt("paper.optimised-despawn.spruce-leaves"));
            ((IntegerConfigItem)pw.values.get(PaperWorldConfig.Key.ALT_ITEM_DESPAWN_RATE_BIRCH_LEAVES.toString())).setValue(this.patches.getInt("paper.optimised-despawn.birch-leaves"));
            ((IntegerConfigItem)pw.values.get(PaperWorldConfig.Key.ALT_ITEM_DESPAWN_RATE_JUNGLE_LEAVES.toString())).setValue(this.patches.getInt("paper.optimised-despawn.jungle-leaves"));
            ((IntegerConfigItem)pw.values.get(PaperWorldConfig.Key.ALT_ITEM_DESPAWN_RATE_ACACIA_LEAVES.toString())).setValue(this.patches.getInt("paper.optimised-despawn.acacia-leaves"));
            ((IntegerConfigItem)pw.values.get(PaperWorldConfig.Key.ALT_ITEM_DESPAWN_RATE_DARK_OAK_LEAVES.toString())).setValue(this.patches.getInt("paper.optimised-despawn.dark-oak-leaves"));
            ((IntegerConfigItem)pw.values.get(PaperWorldConfig.Key.ALT_ITEM_DESPAWN_RATE_MANGROVE_LEAVES.toString())).setValue(this.patches.getInt("paper.optimised-despawn.mangrove-leaves"));
            ((IntegerConfigItem)pw.values.get(PaperWorldConfig.Key.ALT_ITEM_DESPAWN_RATE_CACTUS.toString())).setValue(this.patches.getInt("paper.optimised-despawn.cactus"));
            ((IntegerConfigItem)pw.values.get(PaperWorldConfig.Key.ALT_ITEM_DESPAWN_RATE_DIORITE.toString())).setValue(this.patches.getInt("paper.optimised-despawn.diorite"));
            ((IntegerConfigItem)pw.values.get(PaperWorldConfig.Key.ALT_ITEM_DESPAWN_RATE_GRANITE.toString())).setValue(this.patches.getInt("paper.optimised-despawn.granite"));
            ((IntegerConfigItem)pw.values.get(PaperWorldConfig.Key.ALT_ITEM_DESPAWN_RATE_ANDESITE.toString())).setValue(this.patches.getInt("paper.optimised-despawn.andesite"));
            ((IntegerConfigItem)pw.values.get(PaperWorldConfig.Key.ALT_ITEM_DESPAWN_RATE_SCAFFOLDING.toString())).setValue(this.patches.getInt("paper.optimised-despawn.scaffolding"));

            ((StringConfigItem)pw.values.get(PaperWorldConfig.Key.REDSTONE_IMPLEMENTATION.toString())).setValue(this.patches.getString("paper.redstone-implementation"));
            ((BooleanConfigItem)pw.values.get(PaperWorldConfig.Key.HOPPER_DISABLE_MOVE_EVENT.toString())).setValue(this.patches.getBoolean("paper.hoppers.disable-move-event"));
            ((BooleanConfigItem)pw.values.get(PaperWorldConfig.Key.HOPPER_IGNORE_OCCLUDING_BLOCKS.toString())).setValue(this.patches.getBoolean("paper.hoppers.ignore-occluding-blocks"));
            ((IntegerConfigItem)pw.values.get(PaperWorldConfig.Key.TICK_RATE_MOB_SPAWNER.toString())).setValue(this.patches.getInt("paper.tick-rates.mob-spawner"));
            ((BooleanConfigItem)pw.values.get(PaperWorldConfig.Key.OPTIMIZE_EXPLOSIONS.toString())).setValue(this.patches.getBoolean("paper.optimise-explosions"));

            if (pregeneratedWorld) {
                this.log.info("[KOS][4/6] World is pregenerated, enabling treasure maps...");
                ((BooleanConfigItem)pw.values.get(PaperWorldConfig.Key.TREASURE_MAPS_ENABLED.toString())).setValue(true);
            } else {
                if (this.plugin.getConfig().getBoolean("kos.override-pregenerated-world-protections")) {
                    ((BooleanConfigItem)pw.values.get(PaperWorldConfig.Key.TREASURE_MAPS_ENABLED.toString())).setValue(true);
                    this.log.warn("[KOS][4/6] override-pregenerated-world-protections is TRUE, enabling treasure maps. This may cause lag.");
                } else {
                    ((BooleanConfigItem)pw.values.get(PaperWorldConfig.Key.TREASURE_MAPS_ENABLED.toString())).setValue(false);
                    this.log.info("[KOS][4/6] World not pregenerated, disabling treasure maps...");
                    this.message.Warning("Treasure maps have been disabled, please pre-generate your world to re-enable them.");
                }
            }

            ((BooleanConfigItem)pw.values.get(PaperWorldConfig.Key.TREASURE_MAPS_FIND_ALREADY_DISCOVERED_LOOT_TABLES.toString())).setValue(this.patches.getBoolean("paper.find-already-discovered-loot-tables"));
            ((BooleanConfigItem)pw.values.get(PaperWorldConfig.Key.TREASURE_MAPS_FIND_ALREADY_DISCOVERED_VILLAGER_TRADE.toString())).setValue(this.patches.getBoolean("paper.find-already-discovered-villager-trade"));
            ((IntegerConfigItem)pw.values.get(PaperWorldConfig.Key.TICK_RATE_GRASS_SPREAD.toString())).setValue(this.patches.getInt("paper.tick-rates.grass-spread"));
            ((IntegerConfigItem)pw.values.get(PaperWorldConfig.Key.TICK_RATE_CONTAINER_UPDATE.toString())).setValue(this.patches.getInt("paper.tick-rates.container-update"));
            ((IntegerConfigItem)pw.values.get(PaperWorldConfig.Key.ARROW_DESPAWN_RATE_NON_PLAYER.toString())).setValue(this.patches.getInt("paper.optimised-despawn.arrow.non-player"));
            ((IntegerConfigItem)pw.values.get(PaperWorldConfig.Key.ARROW_DESPAWN_RATE_CREATIVE.toString())).setValue(this.patches.getInt("paper.optimised-despawn.arrow.creative"));
        } else {
            log.info("[KOS] 4/6 - Server does not support Paper World configurations, skipping...");
        }
    }

    private void runPurpur(boolean pregeneratedWorld) {
        if (this.softwareUtil.supportsPurpur()) {
            this.log.info("[KOS] 5/6 - Running Purpur optimisations");

            PurpurConfig purpur = new PurpurConfig(this.plugin);
            if (this.plugin.getConfig().getBoolean("kos.using-tcpshield")) {
                this.log.info("[KOS] 5/6 - You're using TCPShield, disabling use-alternative-keepalive.");
                ((BooleanConfigItem)purpur.values.get(PurpurConfig.Key.USE_ALTERNATE_KEEPALIVE.toString())).setValue(false);
            } else {
                ((BooleanConfigItem)purpur.values.get(PurpurConfig.Key.USE_ALTERNATE_KEEPALIVE.toString())).setValue(this.patches.getBoolean("purpur.use-alternative-keepalive"));
            }

            ((BooleanConfigItem)purpur.values.get(PurpurConfig.Key.ZOMBIE_AGGRESSIVE_TOWARDS_VILLAGER_WHEN_LAGGING.toString())).setValue(this.patches.getBoolean("purpur.entities.zombie.aggressive-towards-villager-when-lagging"));
            ((BooleanConfigItem)purpur.values.get(PurpurConfig.Key.ENTITIES_CAN_USE_PORTALS.toString())).setValue(this.patches.getBoolean("purpur.entities.all.can-use-portals"));
            ((BooleanConfigItem)purpur.values.get(PurpurConfig.Key.VILLAGER_IS_LOBOTOMIZED.toString())).setValue(this.patches.getBoolean("purpur.entities.villager.lobotomized"));
            ((IntegerConfigItem)purpur.values.get(PurpurConfig.Key.VILLAGER_SEARCH_RADIUS_ACQUIRE_POI.toString())).setValue(this.patches.getInt("purpur.entities.villager.search-radius.acquire-poi"));
            ((IntegerConfigItem)purpur.values.get(PurpurConfig.Key.VILLAGER_SEARCH_RADIUS_NEAREST_BED_SENSOR.toString())).setValue(this.patches.getInt("purpur.entities.villager.search-radius.nearest-bed-sensor"));

            if (pregeneratedWorld) {
                this.log.info("[KOS][4/6] World is pregenerated, enabling dolphin treasure searching...");
                ((BooleanConfigItem)purpur.values.get(PurpurConfig.Key.DOLPHIN_DISABLE_TREASURE_SEARCHING.toString())).setValue(false);
            } else {
                if (this.plugin.getConfig().getBoolean("kos.override-pregenerated-world-protections")) {
                    ((BooleanConfigItem)purpur.values.get(PurpurConfig.Key.DOLPHIN_DISABLE_TREASURE_SEARCHING.toString())).setValue(false);
                    this.log.warn("[KOS][4/6] override-pregenerated-world-protections is TRUE, enabling dolphin treasure searching. This may cause lag.");
                } else {
                    ((BooleanConfigItem)purpur.values.get(PurpurConfig.Key.DOLPHIN_DISABLE_TREASURE_SEARCHING.toString())).setValue(true);
                    this.log.info("[KOS][4/6] World not pregenerated, disabling dolphin treasure searching...");
                    this.message.Warning("Dolphin treasure searching has been disabled, please pre-generate your world to re-enable this.");
                }
            }

            ((BooleanConfigItem)purpur.values.get(PurpurConfig.Key.TELEPORT_IF_OUTSIDE_BORDER.toString())).setValue(this.patches.getBoolean("purpur.teleport-if-outside-worldborder"));
            ((IntegerConfigItem)purpur.values.get(PurpurConfig.Key.LAGGING_THRESHOLD.toString())).setValue(this.patches.getInt("purpur.lagging-tps-threshold"));
        } else {
            this.log.info("[KOS] 5/6 - Server does not support Purpur configurations, skipping...");
        }
    }

    private void runPufferfish() {
        if (this.softwareUtil.supportsPufferfish()) {
            this.log.info("[KOS] 6/6 - Running Pufferfish optimisations");
            PufferfishConfig pufferfish = new PufferfishConfig(this.plugin);

            ((IntegerConfigItem)pufferfish.values.get(PufferfishConfig.Key.MAX_LOADS_PER_PROJECTILE.toString())).setValue(this.patches.getInt("pufferfish.max-loads-per-projectile"));
            ((BooleanConfigItem)pufferfish.values.get(PufferfishConfig.Key.DAB_ENABLED.toString())).setValue(this.patches.getBoolean("pufferfish.entities.dynamic-activation-of-brain.enabled"));
            ((IntegerConfigItem)pufferfish.values.get(PufferfishConfig.Key.DAB_MAX_TICK_FREQ.toString())).setValue(this.patches.getInt("pufferfish.entities.dynamic-activation-of-brain.max-tick-freq"));
            ((IntegerConfigItem)pufferfish.values.get(PufferfishConfig.Key.DAB_ACTIVATION_DIST_MOD.toString())).setValue(this.patches.getInt("pufferfish.entities.dynamic-activation-of-brain.activation-distance-modifier"));
            ((BooleanConfigItem)pufferfish.values.get(PufferfishConfig.Key.ENABLE_ASYNC_MOB_SPAWNING.toString())).setValue(this.patches.getBoolean("pufferfish.entities.async-mob-spawning"));
            ((BooleanConfigItem)pufferfish.values.get(PufferfishConfig.Key.ENABLE_SUFFOCATION_OPTIMIZATION.toString())).setValue(this.patches.getBoolean("pufferfish.entities.suffocation-optimisation"));
            ((BooleanConfigItem)pufferfish.values.get(PufferfishConfig.Key.INACTIVE_GOAL_SELECTOR_THROTTLE.toString())).setValue(this.patches.getBoolean("pufferfish.entities.inactive-goal-selector-throttle"));
            ((BooleanConfigItem)pufferfish.values.get(PufferfishConfig.Key.DISABLE_METHOD_PROFILER.toString())).setValue(this.patches.getBoolean("pufferfish.disable-method-profiler"));
        } else {
            this.log.info("[KOS] 6/6 - Server does not support Pufferfish configurations, skipping...");
        }
    }
}

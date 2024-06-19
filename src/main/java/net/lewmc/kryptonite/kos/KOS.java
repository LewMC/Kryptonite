package net.lewmc.kryptonite.kos;

import net.lewmc.kryptonite.Kryptonite;
import net.lewmc.kryptonite.utils.ConfigurationUtil;
import net.lewmc.kryptonite.utils.LogUtil;
import net.lewmc.kryptonite.utils.MessageUtil;
import net.lewmc.kryptonite.utils.SoftwareUtil;
import net.lewmc.kryptonite.kos.config.*;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class KOS {
    private final Kryptonite plugin;
    private final MessageUtil message;
    private final SoftwareUtil softwareUtil;
    private final LogUtil log;
    private final YamlConfiguration kosconfig;
    private YamlConfiguration patches;

    public KOS(CommandSender cs, Kryptonite plugin) {
        this.plugin = plugin;
        this.message = new MessageUtil(cs);
        this.log = new LogUtil(this.plugin);
        this.softwareUtil = new SoftwareUtil(this.plugin);

        ConfigurationUtil config = new ConfigurationUtil(this.plugin, cs);

        this.kosconfig = config.load("plugins/Kryptonite/config.yml");

        File f = new File("plugins/Kryptonite/profiles/"+kosconfig.getString("kos.profile")+".kos");
        if (f.exists()) {
            this.patches = config.load("plugins/Kryptonite/profiles/" + kosconfig.getString("kos.profile") + ".kos");
        }
    }

    public void runDefault(boolean pregeneratedWorld) {
        File f = new File("plugins/Kryptonite/profiles/"+kosconfig.getString("kos.profile")+".kos");
        if (f.exists()) {
            this.message.Success("Running the Kryptonite Optimisation System using the '"+kosconfig.getString("kos.profile")+"' profile.");

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

            try {
                this.plugin.getConfig().load("plugins/Kryptonite/config.yml");
            } catch (IOException | InvalidConfigurationException e) {
                this.cantOpenConfig(e);
            }
        } else {
            this.log.severe("Unable to load the '"+kosconfig.getString("kos.profile")+"' profile.");
            this.log.severe("Please verify that the file exists and try again.");
            this.log.severe("plugins/Kryptonite/profiles/"+kosconfig.getString("kos.profile")+".kos");
        }
    }

    private void runVanilla() {
        if (this.softwareUtil.supportsServerProperties()) {
            this.log.info("[KOS] 1/6 - Running Vanilla optimisations");

            ServerProperties properties = new ServerProperties();

            properties.networkCompressionThreshold(this.patches.getInt("server.network-compression-threshold") + "");
            properties.simulationDistance(this.patches.getInt("server.distance.simulation") + "");
            properties.viewDistance(this.patches.getInt("server.distance.view") + "");
            properties.syncChunkWrites(this.patches.getBoolean("server.sync-chunk-writes"));

            properties.save();
        } else {
            this.log.info("[KOS] 2/6 - Server does not support Server Properties, skipping...");
            this.log.warn("[KOS] 2/6 - This shouldn't happen, please open an issue at github.com/lewmc/kryptonite");
        }
    }

    private void runCraftBukkit() {
        if (this.softwareUtil.supportsCraftBukkit()) {
            this.log.info("[KOS] 2/6 - Running CraftBukkit optimisations");

            Bukkit bukkit = new Bukkit(this.plugin);

            bukkit.spawnLimits(
                    this.patches.getInt("craftbukkit.spawn-limits.monsters"),
                    this.patches.getInt("craftbukkit.spawn-limits.animals"),
                    this.patches.getInt("craftbukkit.spawn-limits.water.animals"),
                    this.patches.getInt("craftbukkit.spawn-limits.water.ambient"),
                    this.patches.getInt("craftbukkit.spawn-limits.water.underground-creature"),
                    this.patches.getInt("craftbukkit.spawn-limits.axolotls"),
                    this.patches.getInt("craftbukkit.spawn-limits.ambient")
            );

            bukkit.ticksPer(
                    this.patches.getInt("craftbukkit.ticks-per.monsters"),
                    this.patches.getInt("craftbukkit.ticks-per.animals"),
                    this.patches.getInt("craftbukkit.ticks-per.water.animals"),
                    this.patches.getInt("craftbukkit.ticks-per.water.ambient"),
                    this.patches.getInt("craftbukkit.ticks-per.water.underground-creature"),
                    this.patches.getInt("craftbukkit.ticks-per.axolotls"),
                    this.patches.getInt("craftbukkit.ticks-per.ambient")
            );

            bukkit.chunkGcPeriodInTicks(this.patches.getInt("craftbukkit.chunk-gc-period-in-ticks"));

            bukkit.save();
        } else {
            this.log.info("[KOS] 2/6 - Server does not support CraftBukkit configurations, skipping...");
            this.log.warn("[KOS] 2/6 - This shouldn't happen, please open an issue at github.com/lewmc/kryptonite");
        }
    }

    private void runSpigot() {
        if (this.softwareUtil.supportsSpigot()) {
            this.log.info("[KOS] 3/6 - Running Spigot optimisations");

            Spigot spigot = new Spigot(this.plugin);

            spigot.viewDistance(this.patches.get("spigot.view-distance"));
            spigot.mobSpawnRange(this.patches.getInt("spigot.mob-spawn-range"));
            spigot.entityActivationRange(
                    this.patches.getInt("spigot.entities.activation-range.animals"),
                    this.patches.getInt("spigot.entities.activation-range.monsters"),
                    this.patches.getInt("spigot.entities.activation-range.raiders"),
                    this.patches.getInt("spigot.entities.activation-range.misc"),
                    this.patches.getInt("spigot.entities.activation-range.water"),
                    this.patches.getInt("spigot.entities.activation-range.villagers"),
                    this.patches.getInt("spigot.entities.activation-range.flying")
            );
            spigot.entityTrackingRange(
                    this.patches.getInt("spigot.entities.tracking-range.players"),
                    this.patches.getInt("spigot.entities.tracking-range.animals"),
                    this.patches.getInt("spigot.entities.tracking-range.monsters"),
                    this.patches.getInt("spigot.entities.tracking-range.misc"),
                    this.patches.getInt("spigot.entities.tracking-range.other")
            );
            spigot.tickInacativeVillagers(this.patches.getBoolean("spigot.entities.tick-inactive-villagers"));
            spigot.nerfSpawnerMobs(this.patches.getBoolean("spigot.entities.spawner-mobs-nerfed"));
            spigot.mergeRadius(
                    this.patches.getDouble("spigot.entities.merge-radius.item"),
                    this.patches.getDouble("spigot.entities.merge-radius.exp")
            );
            spigot.hopperTransfer(this.patches.getInt("spigot.hopper.transfer"));
            spigot.hopperCheck(this.patches.getInt("spigot.hopper.check"));

            spigot.save();
        } else {
            log.info("[KOS] 3/6 - Server does not support Spigot configurations, skipping...");
        }
    }

    private void runPaper(boolean pregeneratedWorld) {
        if (this.softwareUtil.supportsPaperWorld()) {
            this.log.info("[KOS] 4/6 - Running Paper optimisations");

            PaperWorld pw = new PaperWorld(this.plugin);
            pw.delayChunkUnloads(this.patches.getInt("paper.chunks.delay-unloads"));
            pw.maxAutosaveChunksPerTick(this.patches.getInt("paper.chunks.max-autosave-per-tick"));
            pw.preventMovingIntoUnloadedChunks(this.patches.getBoolean("paper.chunks.prevent-moving-into-unloaded"));
            pw.entityPerChunkSaveLimit(
                    this.patches.getInt("paper.chunks.entity-save-limit.area-effect-cloud"),
                    this.patches.getInt("paper.chunks.entity-save-limit.arrow"),
                    this.patches.getInt("paper.chunks.entity-save-limit.dragon-fireball"),
                    this.patches.getInt("paper.chunks.entity-save-limit.egg"),
                    this.patches.getInt("paper.chunks.entity-save-limit.ender-pearl"),
                    this.patches.getInt("paper.chunks.entity-save-limit.experience-bottle"),
                    this.patches.getInt("paper.chunks.entity-save-limit.experience-orb"),
                    this.patches.getInt("paper.chunks.entity-save-limit.eye-of-ender"),
                    this.patches.getInt("paper.chunks.entity-save-limit.fireball"),
                    this.patches.getInt("paper.chunks.entity-save-limit.llama-spit"),
                    this.patches.getInt("paper.chunks.entity-save-limit.potion"),
                    this.patches.getInt("paper.chunks.entity-save-limit.shulker-bullet"),
                    this.patches.getInt("paper.chunks.entity-save-limit.small-fireball"),
                    this.patches.getInt("paper.chunks.entity-save-limit.snowball"),
                    this.patches.getInt("paper.chunks.entity-save-limit.spectral-arrow"),
                    this.patches.getInt("paper.chunks.entity-save-limit.trident"),
                    this.patches.getInt("paper.chunks.entity-save-limit.wither-skull")
            );

            pw.ambientDespawnRanges(
                    this.patches.getInt("paper.despawn-ranges.ambient.hard"),
                    this.patches.getInt("paper.despawn-ranges.ambient.soft")
            );
            pw.axolotlsDespawnRanges(
                    this.patches.getInt("paper.despawn-ranges.axolotl.hard"),
                    this.patches.getInt("paper.despawn-ranges.axolotl.soft")
            );
            pw.creatureDespawnRanges(
                    this.patches.getInt("paper.despawn-ranges.creature.hard"),
                    this.patches.getInt("paper.despawn-ranges.creature.soft")
            );
            pw.miscDespawnRanges(
                    this.patches.getInt("paper.despawn-ranges.misc.hard"),
                    this.patches.getInt("paper.despawn-ranges.misc.soft")
            );
            pw.monsterDespawnRanges(
                    this.patches.getInt("paper.despawn-ranges.monster.hard"),
                    this.patches.getInt("paper.despawn-ranges.monster.soft")
            );
            pw.undergroundWaterCreatureDespawnRanges(
                    this.patches.getInt("paper.despawn-ranges.water.underground-creature.hard"),
                    this.patches.getInt("paper.despawn-ranges.water.underground-creature.soft")
            );
            pw.waterAmbientDespawnRanges(
                    this.patches.getInt("paper.despawn-ranges.water.ambient.hard"),
                    this.patches.getInt("paper.despawn-ranges.water.ambient.soft")
            );
            pw.waterCreatureDespawnRanges(
                    this.patches.getInt("paper.despawn-ranges.water.creature.hard"),
                    this.patches.getInt("paper.despawn-ranges.water.creature.soft")
            );

            pw.perPlayerMobSpawns(this.patches.getBoolean("paper.per-player-mob-spawns"));
            pw.maxEntityCollisions(this.patches.getInt("paper.max-entity-collisions"));
            pw.updatePathfindingOnBlockUpdate(this.patches.getBoolean("paper.update-pathfinding-on-block-update"));
            pw.fixClimbingBypassingCrammingRule(this.patches.getBoolean("paper.fix-climbing-bypass-cramming-rule"));
            pw.armorStandsTick(this.patches.getBoolean("paper.armor-stands.tick"));
            pw.armorStandsDoCollisionEntityLookups(this.patches.getBoolean("paper.armor-stands.do-collision-entity-lookups"));
            pw.spawnerNerfedMobsShouldJump(this.patches.getBoolean("entities.nerfed-spawner-mobs-can-jump"));

            if (!this.softwareUtil.supportsPufferfish()) {
                pw.villagerBehaviourTickRates(
                        this.patches.getInt("paper.tick-rates.villager.behaviour.nearby-poi"),
                        this.patches.getInt("paper.tick-rates.villager.behaviour.acquire-poi")
                );
                pw.villagerSensorTickRates(
                        this.patches.getInt("paper.tick-rates.villager.sensor.secondary-poi"),
                        this.patches.getInt("paper.tick-rates.villager.sensor.nearest-bed"),
                        this.patches.getInt("paper.tick-rates.villager.sensor.villager-babies"),
                        this.patches.getInt("paper.tick-rates.villager.sensor.player"),
                        this.patches.getInt("paper.tick-rates.villager.sensor.nearest-living-entity")
                        );
            } else {
                this.log.info("[KOS][4/6] You're running Pufferfish, skipping some steps due to incompatibility...");
            }

            pw.altItemDespawnRate(
                    this.patches.getBoolean("paper.optimised-despawn.enabled"),
                    this.patches.getInt("paper.optimised-despawn.cobblestone"),
                    this.patches.getInt("paper.optimised-despawn.netherrack"),
                    this.patches.getInt("paper.optimised-despawn.sand"),
                    this.patches.getInt("paper.optimised-despawn.red-sand"),
                    this.patches.getInt("paper.optimised-despawn.gravel"),
                    this.patches.getInt("paper.optimised-despawn.dirt"),
                    this.patches.getInt("paper.optimised-despawn.short-grass"),
                    this.patches.getInt("paper.optimised-despawn.pumpkin"),
                    this.patches.getInt("paper.optimised-despawn.melon-slice"),
                    this.patches.getInt("paper.optimised-despawn.kelp"),
                    this.patches.getInt("paper.optimised-despawn.bamboo"),
                    this.patches.getInt("paper.optimised-despawn.sugar-cane"),
                    this.patches.getInt("paper.optimised-despawn.twisting-vines"),
                    this.patches.getInt("paper.optimised-despawn.weeping-vines"),
                    this.patches.getInt("paper.optimised-despawn.oak-leaves"),
                    this.patches.getInt("paper.optimised-despawn.spruce-leaves"),
                    this.patches.getInt("paper.optimised-despawn.birch-leaves"),
                    this.patches.getInt("paper.optimised-despawn.jungle-leaves"),
                    this.patches.getInt("paper.optimised-despawn.acacia-leaves"),
                    this.patches.getInt("paper.optimised-despawn.dark-oak-leaves"),
                    this.patches.getInt("paper.optimised-despawn.mangrove-leaves"),
                    this.patches.getInt("paper.optimised-despawn.cactus"),
                    this.patches.getInt("paper.optimised-despawn.diorite"),
                    this.patches.getInt("paper.optimised-despawn.granite"),
                    this.patches.getInt("paper.optimised-despawn.andesite"),
                    this.patches.getInt("paper.optimised-despawn.scaffolding")
            );

            pw.redstoneImplementation(this.patches.getString("paper.redstone-implementation"));
            pw.hopperDisableMoveEvent(this.patches.getBoolean("paper.hoppers.disable-move-event"));
            pw.hopperIgnoreOccludingBlocks(this.patches.getBoolean("paper.hoppers.ignore-occluding-blocks"));
            pw.tickRatesMobSpawner(this.patches.getInt("paper.tick-rates.mob-spawner"));
            pw.optimizeExplosions(this.patches.getBoolean("paper.optimise-explosions"));

            if (pregeneratedWorld) {
                this.log.info("[KOS][4/6] World is pregenerated, enabling treasure maps...");
                pw.treasureMapsEnabled(true);
            } else {
                if (this.kosconfig.getBoolean("kos.override-pregenerated-world-protections")) {
                    pw.treasureMapsEnabled(true);
                    this.log.warn("[KOS][4/6] override-pregenerated-world-protections is TRUE, enabling treasure maps. This may cause lag.");
                } else {
                    pw.treasureMapsEnabled(false);
                    this.log.info("[KOS][4/6] World not pregenerated, disabling treasure maps...");
                    this.message.Warning("Treasure maps have been disabled, please pre-generate your world to re-enable them.");
                }
            }

            pw.treasureMapsFindAlreadyDiscovered(this.patches.getBoolean("paper.find-already-discovered-treasure-maps"));
            pw.grassSpreadTickRates(this.patches.getInt("paper.tick-rates.grass-spread"));
            pw.containerUpdateTickRates(this.patches.getInt("paper.tick-rates.container-update"));
            pw.nonPlayerArrowDespawnRate(this.patches.getInt("paper.optimised-despawn.arrow.non-player"));
            pw.creativeArrowDespawnRate(this.patches.getInt("paper.optimised-despawn.arrow.creative"));

            pw.save();
        } else {
            log.info("[KOS] 4/6 - Server does not support Paper World configurations, skipping...");
        }
    }

    private void runPurpur(boolean pregeneratedWorld) {
        if (this.softwareUtil.supportsPurpur()) {
            this.log.info("[KOS] 5/6 - Running Purpur optimisations");

            Purpur purpur = new Purpur(this.plugin);
            if (this.kosconfig.getBoolean("kos.using-tcpshield")) {
                this.log.info("[KOS] 5/6 - You're using TCPShield, disabling use-alternative-keepalive.");
                purpur.useAlternativeKeepalive(false);
            } else {
                purpur.useAlternativeKeepalive(this.patches.getBoolean("purpur.use-alternative-keepalive"));
            }

            purpur.zombieAggressiveTowardsVillagerWhenLagging(this.patches.getBoolean("purpur.entities.zombie.aggressive-towards-villager-when-lagging"));
            purpur.entitiesCanUsePortals(this.patches.getBoolean("purpur.entities.all.can-use-portals"));
            purpur.villagerIsLobotomized(this.patches.getBoolean("purpur.entities.villager.lobotomized"));
            purpur.villagerSearchRadiusAcquirePoi(this.patches.getInt("purpur.entities.villager.search-radius.acquire-poi"));
            purpur.villagerSearchRadiusNearestBedSensor(this.patches.getInt("purpur.entities.villager.search-radius.nearest-bed-sensor"));
            if (pregeneratedWorld) {
                this.log.info("[KOS][4/6] World is pregenerated, enabling dolphin treasure searching...");
                purpur.dolphinDisableTreasureSearching(false);
            } else {
                if (this.kosconfig.getBoolean("kos.override-pregenerated-world-protections")) {
                    purpur.dolphinDisableTreasureSearching(false);
                    this.log.warn("[KOS][4/6] override-pregenerated-world-protections is TRUE, enabling dolphin treasure searching. This may cause lag.");
                } else {
                    purpur.dolphinDisableTreasureSearching(true);
                    this.log.info("[KOS][4/6] World not pregenerated, disabling dolphin treasure searching...");
                    this.message.Warning("Dolphin treasure searching has been disabled, please pre-generate your world to re-enable this.");
                }}
            purpur.teleportIfOutsideBorder(this.patches.getBoolean("purpur.teleport-if-outside-worldborder"));
            purpur.laggingThreshold(this.patches.getDouble("purpur.lagging-tps-threshold"));

            purpur.save();
        } else {
            this.log.info("[KOS] 5/6 - Server does not support Purpur configurations, skipping...");
        }
    }

    private void runPufferfish() {
        if (this.softwareUtil.supportsPufferfish()) {
            this.log.info("[KOS] 6/6 - Running Pufferfish optimisations");
            Pufferfish pufferfish = new Pufferfish(this.plugin);

            pufferfish.maxLoadsPerProjectile(this.patches.getInt("pufferfish.max-loads-per-projectile"));
            pufferfish.dabEnabled(this.patches.getBoolean("pufferfish.entities.dynamic-activation-of-brain.enabled"));
            pufferfish.dabMaxTickFreq(this.patches.getInt("pufferfish.entities.dynamic-activation-of-brain.max-tick-freq"));
            pufferfish.dabActivationDistMod(this.patches.getInt("pufferfish.entities.dynamic-activation-of-brain.activation-distance-modifier"));
            pufferfish.enableAsyncMobSpawning(this.patches.getBoolean("pufferfish.entities.async-mob-spawning"));
            pufferfish.enableSuffocationOptimization(this.patches.getBoolean("pufferfish.entities.suffocation-optimisation"));
            pufferfish.inactiveGoalSelectorThrottle(this.patches.getBoolean("pufferfish.entities.inactive-goal-selector-throttle"));
            pufferfish.disableMethodProfiler(this.patches.getBoolean("pufferfish.disable-method-profiler"));

            pufferfish.save();
        } else {
            this.log.info("[KOS] 6/6 - Server does not support Pufferfish configurations, skipping...");
        }
    }

    private void cantOpenConfig(Exception e) {
        this.message.Error("Unable to open configuration, see console for more information.");
        this.message.Error("Kryptonite Optimisation System Aborted.");
        this.log.severe(e.getMessage());
    }
}

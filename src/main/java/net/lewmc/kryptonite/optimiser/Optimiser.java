package net.lewmc.kryptonite.optimiser;

import net.lewmc.kryptonite.Kryptonite;
import net.lewmc.kryptonite.utils.LogUtil;
import net.lewmc.kryptonite.utils.MessageUtil;
import net.lewmc.kryptonite.utils.SoftwareUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;

import java.io.IOException;

public class Optimiser {
    private final Kryptonite plugin;
    private final MessageUtil message;
    private final SoftwareUtil softwareUtil;
    private final LogUtil log;
    private static final String KRYPTONITE_CONFIG = "plugins/Kryptonite/patches.yml";

    public Optimiser(CommandSender cs, Kryptonite plugin) {
        this.plugin = plugin;
        this.message = new MessageUtil(cs);
        this.log = new LogUtil(this.plugin);
        this.softwareUtil = new SoftwareUtil(this.plugin);
    }

    public void runDefault(boolean pregeneratedWorld) {

        this.message.Success("Running the Kryptonite Optimisation System...");

        this.runVanilla();
        this.runCraftBukkit();
        this.runSpigot();
        this.runPaper(pregeneratedWorld);
        this.runPurpur(pregeneratedWorld);
        this.runPufferfish();

        this.message.Success("Done!");
        this.message.Info("See your server console for more logs.");
        this.message.Error("You must restart your server for changes to be applied.");

        if (!this.softwareUtil.isPaper()) {
            this.message.Error("");
            this.message.Error("You are using an unoptimised server jar!");
            this.message.Error("This is a problem that Kryptonite can't fix.");
            this.message.Error("");
            this.message.Error("We HIGHLY recommend using Paper for your server software.");
            this.message.Error("You are missing out on over 50 optimisations by not using Paper.");
            this.message.Error("You can download paper from papermc.io");
            this.message.Error("");
        }
    }

    private void runVanilla() {
        this.log.info("[KOS] 1/6 - Running Vanilla optimisations");

        try {
            this.plugin.getConfig().load(KRYPTONITE_CONFIG);
        } catch (IOException | InvalidConfigurationException e) {
            this.cantOpenConfig(e);
            return;
        }

        ServerProperties properties = new ServerProperties();

        properties.networkCompressionThreshold(this.plugin.getConfig().getInt("server.network-compression-threshold")+"");
        properties.simulationDistance(this.plugin.getConfig().getInt("server.distance.simulation")+"");
        properties.viewDistance(this.plugin.getConfig().getInt("server.distance.view")+"");
        properties.syncChunkWrites(this.plugin.getConfig().getBoolean("server.sync-chunk-writes"));

        properties.save();
    }

    private void runCraftBukkit() {
        if (this.softwareUtil.isCraftBukkit()) {
            this.log.info("[KOS] 2/6 - Running CraftBukkit optimisations");

            try {
                this.plugin.getConfig().load(KRYPTONITE_CONFIG);
            } catch (IOException | InvalidConfigurationException e) {
                this.cantOpenConfig(e);
                return;
            }

            Bukkit bukkit = new Bukkit(this.plugin);

            bukkit.spawnLimits(
                    this.plugin.getConfig().getInt("craftbukkit.spawn-limits.monsters"),
                    this.plugin.getConfig().getInt("craftbukkit.spawn-limits.animals"),
                    this.plugin.getConfig().getInt("craftbukkit.spawn-limits.water.animals"),
                    this.plugin.getConfig().getInt("craftbukkit.spawn-limits.water.ambient"),
                    this.plugin.getConfig().getInt("craftbukkit.spawn-limits.water.underground-creature"),
                    this.plugin.getConfig().getInt("craftbukkit.spawn-limits.axolotls"),
                    this.plugin.getConfig().getInt("craftbukkit.spawn-limits.ambient")
            );

            bukkit.ticksPer(
                    this.plugin.getConfig().getInt("craftbukkit.ticks-per.monsters"),
                    this.plugin.getConfig().getInt("craftbukkit.ticks-per.animals"),
                    this.plugin.getConfig().getInt("craftbukkit.ticks-per.water.animals"),
                    this.plugin.getConfig().getInt("craftbukkit.ticks-per.water.ambient"),
                    this.plugin.getConfig().getInt("craftbukkit.ticks-per.water.underground-creature"),
                    this.plugin.getConfig().getInt("craftbukkit.ticks-per.axolotls"),
                    this.plugin.getConfig().getInt("craftbukkit.ticks-per.ambient")
            );

            bukkit.save();
        } else {
            this.log.info("[KOS] 2/6 - Server not CraftBukkit, skipping...");
            this.log.warn("[KOS] 2/6 - This shouldn't happen, please open an issue at github.com/lewmc/kryptonite");
        }
    }

    private void runSpigot() {
        if (this.softwareUtil.isSpigot()) {
            this.log.info("[KOS] 3/6 - Running Spigot optimisations");

            try {
                this.plugin.getConfig().load(KRYPTONITE_CONFIG);
            } catch (IOException | InvalidConfigurationException e) {
                this.cantOpenConfig(e);
                return;
            }

            Spigot spigot = new Spigot(this.plugin);

            spigot.viewDistance(this.plugin.getConfig().get("spigot.view-distance"));
            spigot.mobSpawnRange(this.plugin.getConfig().getInt("spigot.mob-spawn-range"));
            spigot.entityActivationRange(
                    this.plugin.getConfig().getInt("spigot.entities.activation-range.animals"),
                    this.plugin.getConfig().getInt("spigot.entities.activation-range.monsters"),
                    this.plugin.getConfig().getInt("spigot.entities.activation-range.raiders"),
                    this.plugin.getConfig().getInt("spigot.entities.activation-range.misc"),
                    this.plugin.getConfig().getInt("spigot.entities.activation-range.water"),
                    this.plugin.getConfig().getInt("spigot.entities.activation-range.villagers"),
                    this.plugin.getConfig().getInt("spigot.entities.activation-range.flying")
            );
            spigot.entityTrackingRange(
                    this.plugin.getConfig().getInt("spigot.entities.tracking-range.players"),
                    this.plugin.getConfig().getInt("spigot.entities.tracking-range.animals"),
                    this.plugin.getConfig().getInt("spigot.entities.tracking-range.monsters"),
                    this.plugin.getConfig().getInt("spigot.entities.tracking-range.misc"),
                    this.plugin.getConfig().getInt("spigot.entities.tracking-range.other")
            );
            spigot.tickInacativeVillagers(this.plugin.getConfig().getBoolean("spigot.entities.tick-inactive-villagers"));
            spigot.nerfSpawnerMobs(this.plugin.getConfig().getBoolean("spigot.entities.spawner-mobs-nerfed"));
            spigot.mergeRadius(
                    this.plugin.getConfig().getDouble("spigot.entities.merge-radius.item"),
                    this.plugin.getConfig().getDouble("spigot.entities.merge-radius.exp")
            );
            spigot.hopperTransfer(this.plugin.getConfig().getInt("spigot.hopper.transfer"));
            spigot.hopperCheck(this.plugin.getConfig().getInt("spigot.hopper.check"));

            spigot.save();
        } else {
            log.info("[KOS] 3/6 - Server not Spigot, skipping...");
        }
    }

    private void runPaper(boolean pregeneratedWorld) {
        if (this.softwareUtil.isPaper()) {
            this.log.info("[KOS] 4/4 - Running Paper optimisations");
            try {
                this.plugin.getConfig().load(KRYPTONITE_CONFIG);
            } catch (IOException | InvalidConfigurationException e) {
                this.cantOpenConfig(e);
                return;
            }

            PaperWorld pw = new PaperWorld(this.plugin);
            pw.delayChunkUnloads(this.plugin.getConfig().getInt("paper.chunks.delay-unloads"));
            pw.maxAutosaveChunksPerTick(this.plugin.getConfig().getInt("paper.chunks.max-autosave-per-tick"));
            pw.preventMovingIntoUnloadedChunks(this.plugin.getConfig().getBoolean("paper.chunks.prevent-moving-into-unloaded"));
            pw.entityPerChunkSaveLimit(
                    this.plugin.getConfig().getInt("paper.chunks.entity-save-limit.area-effect-cloud"),
                    this.plugin.getConfig().getInt("paper.chunks.entity-save-limit.arrow"),
                    this.plugin.getConfig().getInt("paper.chunks.entity-save-limit.dragon-fireball"),
                    this.plugin.getConfig().getInt("paper.chunks.entity-save-limit.egg"),
                    this.plugin.getConfig().getInt("paper.chunks.entity-save-limit.ender-pearl"),
                    this.plugin.getConfig().getInt("paper.chunks.entity-save-limit.experience-bottle"),
                    this.plugin.getConfig().getInt("paper.chunks.entity-save-limit.experience-orb"),
                    this.plugin.getConfig().getInt("paper.chunks.entity-save-limit.eye-of-ender"),
                    this.plugin.getConfig().getInt("paper.chunks.entity-save-limit.fireball"),
                    this.plugin.getConfig().getInt("paper.chunks.entity-save-limit.llama-spit"),
                    this.plugin.getConfig().getInt("paper.chunks.entity-save-limit.potion"),
                    this.plugin.getConfig().getInt("paper.chunks.entity-save-limit.shulker-bullet"),
                    this.plugin.getConfig().getInt("paper.chunks.entity-save-limit.small-fireball"),
                    this.plugin.getConfig().getInt("paper.chunks.entity-save-limit.snowball"),
                    this.plugin.getConfig().getInt("paper.chunks.entity-save-limit.spectral-arrow"),
                    this.plugin.getConfig().getInt("paper.chunks.entity-save-limit.trident"),
                    this.plugin.getConfig().getInt("paper.chunks.entity-save-limit.wither-skull")
            );

            pw.ambientDespawnRanges(
                    this.plugin.getConfig().getInt("paper.despawn-ranges.ambient.hard"),
                    this.plugin.getConfig().getInt("paper.despawn-ranges.ambient.soft")
            );
            pw.axolotlsDespawnRanges(
                    this.plugin.getConfig().getInt("paper.despawn-ranges.axolotl.hard"),
                    this.plugin.getConfig().getInt("paper.despawn-ranges.axolotl.soft")
            );
            pw.creatureDespawnRanges(
                    this.plugin.getConfig().getInt("paper.despawn-ranges.creature.hard"),
                    this.plugin.getConfig().getInt("paper.despawn-ranges.creature.soft")
            );
            pw.miscDespawnRanges(
                    this.plugin.getConfig().getInt("paper.despawn-ranges.misc.hard"),
                    this.plugin.getConfig().getInt("paper.despawn-ranges.misc.soft")
            );
            pw.monsterDespawnRanges(
                    this.plugin.getConfig().getInt("paper.despawn-ranges.monster.hard"),
                    this.plugin.getConfig().getInt("paper.despawn-ranges.monster.soft")
            );
            pw.undergroundWaterCreatureDespawnRanges(
                    this.plugin.getConfig().getInt("paper.despawn-ranges.water.underground-creature.hard"),
                    this.plugin.getConfig().getInt("paper.despawn-ranges.water.underground-creature.soft")
            );
            pw.waterAmbientDespawnRanges(
                    this.plugin.getConfig().getInt("paper.despawn-ranges.water.ambient.hard"),
                    this.plugin.getConfig().getInt("paper.despawn-ranges.water.ambient.soft")
            );
            pw.waterCreatureDespawnRanges(
                    this.plugin.getConfig().getInt("paper.despawn-ranges.water.creature.hard"),
                    this.plugin.getConfig().getInt("paper.despawn-ranges.water.creature.soft")
            );

            pw.perPlayerMobSpawns(this.plugin.getConfig().getBoolean("paper.per-player-mob-spawns"));
            pw.maxEntityCollisions(this.plugin.getConfig().getInt("paper.max-entity-collisions"));
            pw.updatePathfindingOnBlockUpdate(this.plugin.getConfig().getBoolean("paper.update-pathfinding-on-block-update"));
            pw.fixClimbingBypassingCrammingRule(this.plugin.getConfig().getBoolean("paper.fix-climbing-bypass-cramming-rule"));
            pw.armorStandsTick(this.plugin.getConfig().getBoolean("paper.armor-stands.tick"));
            pw.armorStandsDoCollisionEntityLookups(this.plugin.getConfig().getBoolean("paper.armor-stands.do-collision-entity-lookups"));
            pw.spawnerNerfedMobsShouldJump(this.plugin.getConfig().getBoolean("entities.nerfed-spawner-mobs-can-jump"));

            if (this.plugin.server != Kryptonite.Software.PUFFERFISH) {
                pw.villagerBehaviourTickRates(
                        this.plugin.getConfig().getInt("paper.tick-rates.villager.behaviour.nearby-poi"),
                        this.plugin.getConfig().getInt("paper.tick-rates.villager.behaviour.acquire-poi")
                );
                pw.villagerSensorTickRates(
                        this.plugin.getConfig().getInt("paper.tick-rates.villager.sensor.secondary-poi"),
                        this.plugin.getConfig().getInt("paper.tick-rates.villager.sensor.nearest-bed"),
                        this.plugin.getConfig().getInt("paper.tick-rates.villager.sensor.villager-babies"),
                        this.plugin.getConfig().getInt("paper.tick-rates.villager.sensor.player"),
                        this.plugin.getConfig().getInt("paper.tick-rates.villager.sensor.nearest-living-entity")
                        );
            } else {
                this.log.info("[KOS][4/6] You're running Pufferfish, skipping some steps due to incompatibility...");
            }

            pw.altItemDespawnRate(
                    this.plugin.getConfig().getBoolean("paper.optimised-despawn.enabled"),
                    this.plugin.getConfig().getInt("paper.optimised-despawn.cobblestone"),
                    this.plugin.getConfig().getInt("paper.optimised-despawn.netherrack"),
                    this.plugin.getConfig().getInt("paper.optimised-despawn.sand"),
                    this.plugin.getConfig().getInt("paper.optimised-despawn.red-sand"),
                    this.plugin.getConfig().getInt("paper.optimised-despawn.gravel"),
                    this.plugin.getConfig().getInt("paper.optimised-despawn.dirt"),
                    this.plugin.getConfig().getInt("paper.optimised-despawn.short-grass"),
                    this.plugin.getConfig().getInt("paper.optimised-despawn.pumpkin"),
                    this.plugin.getConfig().getInt("paper.optimised-despawn.melon-slice"),
                    this.plugin.getConfig().getInt("paper.optimised-despawn.kelp"),
                    this.plugin.getConfig().getInt("paper.optimised-despawn.bamboo"),
                    this.plugin.getConfig().getInt("paper.optimised-despawn.sugar-cane"),
                    this.plugin.getConfig().getInt("paper.optimised-despawn.twisting-vines"),
                    this.plugin.getConfig().getInt("paper.optimised-despawn.weeping-vines"),
                    this.plugin.getConfig().getInt("paper.optimised-despawn.oak-leaves"),
                    this.plugin.getConfig().getInt("paper.optimised-despawn.spruce-leaves"),
                    this.plugin.getConfig().getInt("paper.optimised-despawn.birch-leaves"),
                    this.plugin.getConfig().getInt("paper.optimised-despawn.jungle-leaves"),
                    this.plugin.getConfig().getInt("paper.optimised-despawn.acacia-leaves"),
                    this.plugin.getConfig().getInt("paper.optimised-despawn.dark-oak-leaves"),
                    this.plugin.getConfig().getInt("paper.optimised-despawn.mangrove-leaves"),
                    this.plugin.getConfig().getInt("paper.optimised-despawn.cactus"),
                    this.plugin.getConfig().getInt("paper.optimised-despawn.diorite"),
                    this.plugin.getConfig().getInt("paper.optimised-despawn.granite"),
                    this.plugin.getConfig().getInt("paper.optimised-despawn.andesite"),
                    this.plugin.getConfig().getInt("paper.optimised-despawn.scaffolding")
            );

            pw.redstoneImplementation(this.plugin.getConfig().getString("paper.redstone-implementation"));
            pw.hopperDisableMoveEvent(this.plugin.getConfig().getBoolean("paper.hoppers.disable-move-event"));
            pw.hopperIgnoreOccludingBlocks(this.plugin.getConfig().getBoolean("paper.hoppers.ignore-occluding-blocks"));
            pw.tickRatesMobSpawner(this.plugin.getConfig().getInt("paper.tick-rates.mob-spawner"));
            pw.optimizeExplosions(this.plugin.getConfig().getBoolean("paper.optimise-explosions"));

            if (pregeneratedWorld) {
                this.log.info("[KOS][4/6] World is pregenerated, enabling treasure maps...");
                pw.treasureMapsEnabled(true);
            } else {
                if (this.plugin.getConfig().getBoolean("kos.override-pregenerated-world-protections")) {
                    pw.treasureMapsEnabled(true);
                    this.log.warn("[KOS][4/6] override-pregenerated-world-protections is TRUE, enabling treasure maps. This may cause lag.");
                } else {
                    pw.treasureMapsEnabled(false);
                    this.log.info("[KOS][4/6] World not pregenerated, disabling treasure maps...");
                    this.message.Warning("Treasure maps have been disabled, please pre-generate your world to re-enable them.");
                }
            }

            pw.treasureMapsFindAlreadyDiscovered(this.plugin.getConfig().getBoolean("paper.find-already-discovered-treasure-maps"));
            pw.grassSpreadTickRates(this.plugin.getConfig().getInt("paper.tick-rates.grass-spread"));
            pw.containerUpdateTickRates(this.plugin.getConfig().getInt("paper.tick-rates.container-update"));
            pw.nonPlayerArrowDespawnRate(this.plugin.getConfig().getInt("paper.optimised-despawn.arrow.non-player"));
            pw.creativeArrowDespawnRate(this.plugin.getConfig().getInt("paper.optimised-despawn.arrow.creative"));

            pw.save();
        } else {
            log.info("[KOS] 4/6 - Server not Paper, skipping...");
        }
    }

    private void runPurpur(boolean pregeneratedWorld) {
        if (this.softwareUtil.isPurpur()) {
            this.log.info("[KOS] 5/6 - Running Purpur optimisations");
            try {
                this.plugin.getConfig().load(KRYPTONITE_CONFIG);
            } catch (IOException | InvalidConfigurationException e) {
                this.cantOpenConfig(e);
                return;
            }

            Purpur purpur = new Purpur(this.plugin);
            if (this.plugin.getConfig().getBoolean("kos.using-tcpshield")) {
                this.log.info("[KOS] 5/6 - You're using TCPShield, disabling use-alternative-keepalive.");
                purpur.useAlternativeKeepalive(false);
            } else {
                purpur.useAlternativeKeepalive(this.plugin.getConfig().getBoolean("purpur.use-alternative-keepalive"));
            }

            purpur.zombieAggressiveTowardsVillagerWhenLagging(this.plugin.getConfig().getBoolean("purpur.entities.zombie.aggressive-towards-villager-when-lagging"));
            purpur.entitiesCanUsePortals(this.plugin.getConfig().getBoolean("purpur.entities.all.can-use-portals"));
            purpur.villagerIsLobotomized(this.plugin.getConfig().getBoolean("purpur.entities.villager.lobotomized"));
            purpur.villagerSearchRadiusAcquirePoi(this.plugin.getConfig().getInt("purpur.entities.villager.search-radius.acquire-poi"));
            purpur.villagerSearchRadiusNearestBedSensor(this.plugin.getConfig().getInt("purpur.entities.villager.search-radius.nearest-bed-sensor"));
            if (pregeneratedWorld) {
                this.log.info("[KOS][4/6] World is pregenerated, enabling dolphin treasure searching...");
                purpur.dolphinDisableTreasureSearching(false);
            } else {
                if (this.plugin.getConfig().getBoolean("kos.override-pregenerated-world-protections")) {
                    purpur.dolphinDisableTreasureSearching(false);
                    this.log.warn("[KOS][4/6] override-pregenerated-world-protections is TRUE, enabling dolphin treasure searching. This may cause lag.");
                } else {
                    purpur.dolphinDisableTreasureSearching(true);
                    this.log.info("[KOS][4/6] World not pregenerated, disabling dolphin treasure searching...");
                    this.message.Warning("Dolphin treasure searching has been disabled, please pre-generate your world to re-enable this.");
                }}
            purpur.teleportIfOutsideBorder(this.plugin.getConfig().getBoolean("purpur.teleport-if-outside-worldborder"));
            purpur.laggingThreshold(this.plugin.getConfig().getDouble("purpur.lagging-tps-threshold"));

            purpur.save();
        } else {
            this.log.info("[KOS] 5/6 - Server not Purpur, skipping...");
        }
    }

    private void runPufferfish() {
        if (this.softwareUtil.isPufferfish()) {
            this.log.info("[KOS] 6/6 - Running Pufferfish optimisations");
            try {
                this.plugin.getConfig().load(KRYPTONITE_CONFIG);
            } catch (IOException | InvalidConfigurationException e) {
                this.cantOpenConfig(e);
                return;
            }
            Pufferfish pufferfish = new Pufferfish(this.plugin);

            pufferfish.maxLoadsPerProjectile(this.plugin.getConfig().getInt("pufferfish.max-loads-per-projectile"));
            pufferfish.dabEnabled(this.plugin.getConfig().getBoolean("pufferfish.entities.dynamic-activation-of-brain.enabled"));
            pufferfish.dabMaxTickFreq(this.plugin.getConfig().getInt("pufferfish.entities.dynamic-activation-of-brain.max-tick-freq"));
            pufferfish.dabActivationDistMod(this.plugin.getConfig().getInt("pufferfish.entities.dynamic-activation-of-brain.activation-distance-modifier"));
            pufferfish.enableAsyncMobSpawning(this.plugin.getConfig().getBoolean("pufferfish.entities.async-mob-spawning"));
            pufferfish.enableSuffocationOptimization(this.plugin.getConfig().getBoolean("pufferfish.entities.suffocation-optimisation"));
            pufferfish.inactiveGoalSelectorThrottle(this.plugin.getConfig().getBoolean("pufferfish.entities.inactive-goal-selector-throttle"));
            pufferfish.disableMethodProfiler(this.plugin.getConfig().getBoolean("pufferfish.disable-method-profiler"));

            pufferfish.save();
        } else {
            this.log.info("[KOS] 6/6 - Server not Pufferfish, skipping...");
        }
    }

    private void cantOpenConfig(Exception e) {
        this.message.Error("Unable to open configuration, see console for more information.");
        this.message.Error("Kryptonite Optimisation System Aborted.");
        this.log.severe(e.getMessage());
    }
}

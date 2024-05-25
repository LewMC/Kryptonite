package net.lewmc.kryptonite.optimiser;

import net.lewmc.kryptonite.Kryptonite;
import net.lewmc.kryptonite.utils.LogUtil;
import net.lewmc.kryptonite.utils.MessageUtil;
import net.lewmc.kryptonite.utils.SoftwareUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;

import java.io.IOException;
import java.util.Objects;

public class Optimiser {
    private final Kryptonite plugin;
    private final MessageUtil message;
    private final SoftwareUtil softwareUtil;
    private final LogUtil log;

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
        this.runPurpur();
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
            this.plugin.getConfig().load("plugins/Kryptonite/config.yml");
        } catch (IOException | InvalidConfigurationException e) {
            this.message.Error("Unable to open configuration, see console for more information.");
            this.message.Error("Kryptonite Optimisation System Aborted.");
            this.log.severe(e.getMessage());
            return;
        }

        ServerProperties properties = new ServerProperties();

        properties.networkCompressionThreshold("256");
        properties.simulationDistance(Objects.requireNonNull(this.plugin.getConfig().get("distance.simulation")).toString());
        properties.viewDistance(Objects.requireNonNull(this.plugin.getConfig().get("distance.view")).toString());
        properties.syncChunkWrites("false");

        properties.save();
    }

    private void runCraftBukkit() {
        if (this.softwareUtil.isCraftBukkit()) {
            this.log.info("[KOS] 2/6 - Running CraftBukkit optimisations");
            Bukkit bukkit = new Bukkit(this.plugin);

            bukkit.spawnLimits(20, 5, 2, 2, 3, 3, 1);
            bukkit.ticksPer(10, 400, 400, 400, 400, 400, 400);

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
                this.plugin.getConfig().load("plugins/Kryptonite/config.yml");
            } catch (IOException | InvalidConfigurationException e) {
                this.message.Error("Unable to open configuration, see console for more information.");
                this.message.Error("Kryptonite Optimisation System Aborted.");
                this.log.severe(e.getMessage());
                return;
            }

            Spigot spigot = new Spigot(this.plugin);

            spigot.viewDistance("default");
            spigot.mobSpawnRange(3);
            spigot.entityActivationRange(16, 24, 48, 8, 8, 16, 48);
            spigot.entityTrackingRange(48, 48, 48, 32, 64);
            spigot.tickInacativeVillagers(false);
            spigot.nerfSpawnerMobs(this.plugin.getConfig().getBoolean("entities.spawner-mobs.nerfed"));
            spigot.mergeRadius(3.5, 4.0);
            spigot.hopperTransfer(8);
            spigot.hopperCheck(8);

            spigot.save();
        } else {
            log.info("[KOS] 3/6 - Server not Spigot, skipping...");
        }
    }

    private void runPaper(boolean pregeneratedWorld) {
        if (this.softwareUtil.isPaper()) {
            this.log.info("[KOS] 4/4 - Running Paper optimisations");
            try {
                this.plugin.getConfig().load("plugins/Kryptonite/config.yml");
            } catch (IOException | InvalidConfigurationException e) {
                this.message.Error("Unable to open configuration, see console for more information.");
                this.message.Error("Kryptonite Optimisation System Aborted.");
                this.log.severe(e.getMessage());
                return;
            }

            PaperWorld pw = new PaperWorld(this.plugin);
            pw.delayChunkUnloads(10);
            pw.maxAutosaveChunksPerTick(8);
            pw.preventMovingIntoUnloadedChunks(true);
            pw.entityPerChunkSaveLimit(8, 16, 3, 8, 8, 3, 16, 8, 8, 3, 8, 8, 8, 8, 16, 16, 4);

            pw.ambientDespawnRanges(72, 30);
            pw.axolotlsDespawnRanges(72, 30);
            pw.creatureDespawnRanges(72, 30);
            pw.miscDespawnRanges(72, 30);
            pw.monsterDespawnRanges(72, 30);
            pw.undergroundWaterCreatureDespawnRanges(72, 30);
            pw.waterAmbientDespawnRanges(72, 30);
            pw.waterCreatureDespawnRanges(72, 30);

            pw.perPlayerMobSpawns(true);
            pw.maxEntityCollisions(2);
            pw.updatePathfindingOnBlockUpdate(false);
            pw.fixClimbingBypassingCrammingRule(true);
            pw.armorStandsTick(false);
            pw.armorStandsDoCollisionEntityLookups(false);
            pw.spawnerNerfedMobsShouldJump(this.plugin.getConfig().getBoolean("entities.spawner-mobs.nerfed-can-jump"));

            if (this.plugin.server != Kryptonite.Software.PUFFERFISH) {
                pw.villagerBehaviourTickRates(60, 120);
                pw.villagerSensorTickRates(80, 80, 40, 40, 40);
            } else {
                this.log.info("[KOS][4/6] You're running Pufferfish, skipping some steps due to incompatibility...");
            }

            pw.altItemDespawnRate(
                    true,
                    300,
                    300,
                    300,
                    300,
                    300,
                    300,
                    300,
                    300,
                    300,
                    300,
                    300,
                    300,
                    300,
                    300,
                    300,
                    300,
                    300,
                    300,
                    300,
                    300,
                    300,
                    300,
                    300,
                    300,
                    300,
                    600
            );

            pw.redstoneImplementation("ALTERNATE_CURRENT");
            pw.hopperDisableMoveEvent(false);
            pw.hopperIgnoreOccludingBlocks(true);
            pw.tickRatesMobSpawner(2);
            pw.optimizeExplosions(true);

            if (pregeneratedWorld) {
                this.log.info("[KOS][4/6] World is pregenerated, enabling treasure maps...");
                pw.treasureMapsEnabled(true);
            } else {
                pw.treasureMapsEnabled(false);
                this.log.info("[KOS][4/6] World not pregenerated, disabling treasure maps...");
                this.message.Warning("Treasure maps have been disabled, please pre-generate your world to re-enable them.");
            }

            pw.treasureMapsFindAlreadyDiscovered(true);
            pw.grassSpreadTickRates(4);
            pw.containerUpdateTickRates(1);
            pw.nonPlayerArrowDespawnRate(20);
            pw.creativeArrowDespawnRate(20);

            pw.save();
        } else {
            log.info("[KOS] 4/6 - Server not Paper, skipping...");
        }
    }

    private void runPurpur() {
        if (this.softwareUtil.isPurpur()) {
            this.log.info("[KOS] 5/6 - Running Purpur optimisations");
        } else {
            this.log.info("[KOS] 5/6 - Server not Purpur, skipping...");
        }
    }

    private void runPufferfish() {
        if (this.softwareUtil.isPufferfish()) {
            this.log.info("[KOS] 6/6 - Running Pufferfish optimisations");
        } else {
            this.log.info("[KOS] 6/6 - Server not Pufferfish, skipping...");
        }
    }
}

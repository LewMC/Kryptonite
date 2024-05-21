package net.lewmc.kryptonite.optimiser;

import net.lewmc.kryptonite.Kryptonite;
import net.lewmc.kryptonite.utils.MessageUtil;
import net.lewmc.kryptonite.utils.SoftwareUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Optimiser {
    private final Kryptonite plugin;
    private final MessageUtil message;

    public Optimiser(CommandSender cs, Kryptonite plugin) {
        this.plugin = plugin;
        this.message = new MessageUtil(cs);
    }

    public void runDefault(boolean pregeneratedWorld) {

        this.message.Info("Running Vanilla optimisations");

        try {
            this.plugin.getConfig().load(new File("plugins/kryptonite/config.yml"));
        } catch (IOException | InvalidConfigurationException e) {
            throw new RuntimeException(e);
        }

        ServerProperties properties = new ServerProperties();

        properties.networkCompressionThreshold("256");
        properties.simulationDistance(Objects.requireNonNull(this.plugin.getConfig().get("simulation-distance")).toString());
        properties.viewDistance(Objects.requireNonNull(this.plugin.getConfig().get("view-distance")).toString());
        properties.syncChunkWrites("false");

        properties.save();

        SoftwareUtil softwareUtil = new SoftwareUtil(plugin);

        if (softwareUtil.isCraftBukkit()) {
            this.message.Info("Running CraftBukkit optimisations");
            Bukkit bukkit = new Bukkit(this.plugin);

            bukkit.spawnLimits(20, 5, 2, 2, 3, 3, 1);
            bukkit.ticksPer(10, 400, 400, 400, 400, 400, 400);

            bukkit.save();
        } else {
            this.message.Warning("Server not CraftBukkit, skipping...");
        }

        if (softwareUtil.isSpigot()) {
            this.message.Info("Running Spigot optimisations");
            Spigot spigot = new Spigot(this.plugin);

            spigot.viewDistance("default");
            spigot.mobSpawnRange(3);
            spigot.entityActivationRange(16, 24, 48, 8, 8, 16, 48);
            spigot.entityTrackingRange(48, 48, 48, 32, 64);
            spigot.tickInacativeVillagers(false);
            spigot.nerfSpawnerMobs(true);
            spigot.mergeRadius(3.5, 4.0);
            spigot.hopperTransfer(8);
            spigot.hopperCheck(8);

            spigot.save();
        } else {
            this.message.Warning("Server not Spigot, skipping...");
        }

        if (softwareUtil.isPaper()) {
            this.message.Info("Running Paper optimisations");
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

            if (this.plugin.server != Kryptonite.Software.PUFFERFISH) {
                pw.villagerBehaviourTickRates(60, 120);
                pw.villagerSensorTickRates(80, 80, 40, 40, 40);
            } else {
                this.message.Info("Running Pufferfish, skipping some steps due to incompatibility...");
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
                pw.treasureMapsEnabled(true);
            } else {
                this.message.Warning("Treasure maps disabled, please pregenerate your world to re-enable them.");
            }

            pw.treasureMapsFindAlreadyDiscovered(true);
            pw.grassSpreadTickRates(4);
            pw.containerUpdateTickRates(1);
            pw.nonPlayerArrowDespawnRate(20);
            pw.creativeArrowDespawnRate(20);

            pw.save();
        } else {
            this.message.Warning("Server not Paper, skipping...");
        }

        if (softwareUtil.isPurpur()) {
            this.message.Info("Purpur optimisations are not currently supported. Paper, Spigot, and CraftBukkit optimisations have been applied.");
        }

        if (softwareUtil.isPufferfish()) {
            this.message.Info("Pufferfish optimisations are not currently supported. Paper, Spigot, and CraftBukkit optimisations have been applied.");
        }

        this.message.Success("Done.");
        this.message.Info("It is now safe to delete Kryptonite from your server.");
        this.message.Error("You must restart your server for changes to be applied.");
    }
}

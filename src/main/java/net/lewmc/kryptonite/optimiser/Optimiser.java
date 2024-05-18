package net.lewmc.kryptonite.optimiser;

import net.lewmc.kryptonite.Kryptonite;
import net.lewmc.kryptonite.utils.MessageUtil;
import org.bukkit.command.CommandSender;

public class Optimiser {
    private final Kryptonite plugin;
    private final MessageUtil message;
    private enum Software {
        UNKNOWN,
        CRAFTBUKKIT,
        PAPER,
        SPIGOT,
        PURPUR, PUFFERFISH
    }
    Software server = Software.UNKNOWN;

    public Optimiser(CommandSender cs, Kryptonite plugin) {
        this.plugin = plugin;
        this.message = new MessageUtil(cs);
    }

    public void runDefault(boolean pregeneratedWorld) {
        this.firstChecks();

        this.message.Info("Running Minecraft optimisations");


        if (server != Software.UNKNOWN) {
            this.message.Info("Running CraftBukkit optimisations");
            Bukkit bukkit = new Bukkit(this.plugin);

            bukkit.spawnLimits(20, 5, 2, 2, 3, 3, 1);
            bukkit.ticksPer(10, 400, 400, 400, 400, 400, 400);

            bukkit.save();
        } else {
            this.message.Warning("Server not CraftBukkit, skipping...");
        }

        if (server != Software.UNKNOWN && server != Software.CRAFTBUKKIT) {
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

        if (server != Software.UNKNOWN && server != Software.CRAFTBUKKIT && server != Software.SPIGOT) {
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

            if (server != Software.PUFFERFISH) {
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

        if (server == Software.PURPUR) {
            this.message.Info("Running Purpur optimisations");
        } else {
            this.message.Warning("Server not Purpur, skipping...");
        }

        if (server == Software.PUFFERFISH) {
            this.message.Info("Running Pufferfish optimisations");
        } else {
            this.message.Warning("Server not Pufferfish, skipping...");
        }

        this.message.Success("Done.");
        this.message.Info("It is now safe to delete Kryptonite from your server.");
        this.message.Error("You must restart your server for changes to be applied.");
    }

    public void firstChecks() {
        if (this.plugin.getServer().getName().equals("CraftBukkit")) {
            this.server = Software.CRAFTBUKKIT;
        } else if (this.plugin.getServer().getName().equals("Spigot")) {
            this.server = Software.SPIGOT;
        } else if (this.plugin.getServer().getName().equals("Paper")) {
            this.server = Software.PAPER;
        } else {
            this.server = Software.UNKNOWN;
            this.message.Error("You are not running a CraftBukkit, Spigot, or Paper server.");
            this.message.Error("This plugin may not work as expected.");
        }
    }
}

package net.lewmc.kryptonite.kos.config;

import net.lewmc.kryptonite.Kryptonite;
import net.lewmc.kryptonite.utils.LogUtil;
import org.bukkit.configuration.InvalidConfigurationException;

import java.io.File;
import java.io.IOException;

public class PaperWorld {
    private final Kryptonite plugin;
    private final File file = new File("config/paper-world-defaults.yml");

    public PaperWorld(final Kryptonite plugin) {
        this.plugin = plugin;
        try {
            plugin.getConfig().load(this.file);
        } catch (IOException | InvalidConfigurationException e) {
            LogUtil log = new LogUtil(plugin);
            log.severe("Error whilst loading Paper World configuration:");
            log.severe(e.getMessage());
        }
    }

    public void delayChunkUnloads(int value) {
        this.plugin.getConfig().set("chunks.delay-chunk-unloads-by", value+"s");
    }

    public void maxAutosaveChunksPerTick(int value) {
        this.plugin.getConfig().set("chunks.max-auto-save-chunks-per-tick", value);
    }

    public void preventMovingIntoUnloadedChunks(boolean value) {
        this.plugin.getConfig().set("chunks.prevent-moving-into-unloaded-chunks", value);
    }

    public void entityPerChunkSaveLimit(int areaEffectCloud,
                                        int arrow,
                                        int dragonFireball,
                                        int egg,
                                        int enderPearl,
                                        int experienceBottle,
                                        int experienceOrb,
                                        int eyeOfEnder,
                                        int fireball,
                                        int llamaSpit,
                                        int potion,
                                        int shulkerBullet,
                                        int smallFireball,
                                        int snowball,
                                        int spectralArrow,
                                        int trident,
                                        int witherSkull
    ) {
        this.plugin.getConfig().set("chunks.entity-per-chunk-save-limit.area_effect_cloud", areaEffectCloud);
        this.plugin.getConfig().set("chunks.entity-per-chunk-save-limit.arrow", arrow);
        this.plugin.getConfig().set("chunks.entity-per-chunk-save-limit.dragon_fireball", dragonFireball);
        this.plugin.getConfig().set("chunks.entity-per-chunk-save-limit.egg", egg);
        this.plugin.getConfig().set("chunks.entity-per-chunk-save-limit.ender_pearl", enderPearl);
        this.plugin.getConfig().set("chunks.entity-per-chunk-save-limit.experience_bottle", experienceBottle);
        this.plugin.getConfig().set("chunks.entity-per-chunk-save-limit.experience_orb", experienceOrb);
        this.plugin.getConfig().set("chunks.entity-per-chunk-save-limit.eye_of_ender", eyeOfEnder);
        this.plugin.getConfig().set("chunks.entity-per-chunk-save-limit.fireball", fireball);
        this.plugin.getConfig().set("chunks.entity-per-chunk-save-limit.llama_spit", llamaSpit);
        this.plugin.getConfig().set("chunks.entity-per-chunk-save-limit.potion", potion);
        this.plugin.getConfig().set("chunks.entity-per-chunk-save-limit.shulker_bullet", shulkerBullet);
        this.plugin.getConfig().set("chunks.entity-per-chunk-save-limit.small_fireball", smallFireball);
        this.plugin.getConfig().set("chunks.entity-per-chunk-save-limit.snowball", snowball);
        this.plugin.getConfig().set("chunks.entity-per-chunk-save-limit.spectral_arrow", spectralArrow);
        this.plugin.getConfig().set("chunks.entity-per-chunk-save-limit.trident", trident);
        this.plugin.getConfig().set("chunks.entity-per-chunk-save-limit.wither_skull", witherSkull);
    }

    public void ambientDespawnRanges(Object hard, Object soft) {
        this.plugin.getConfig().set("entities.spawning.despawn-ranges.ambient.hard", hard);
        this.plugin.getConfig().set("entities.spawning.despawn-ranges.ambient.soft", soft);
    }

    public void axolotlsDespawnRanges(Object hard, Object soft) {
        this.plugin.getConfig().set("entities.spawning.despawn-ranges.axolotls.hard", hard);
        this.plugin.getConfig().set("entities.spawning.despawn-ranges.axolotls.soft", soft);
    }

    public void creatureDespawnRanges(Object hard, Object soft) {
        this.plugin.getConfig().set("entities.spawning.despawn-ranges.creature.hard", hard);
        this.plugin.getConfig().set("entities.spawning.despawn-ranges.creature.soft", soft);
    }

    public void miscDespawnRanges(Object hard, Object soft) {
        this.plugin.getConfig().set("entities.spawning.despawn-ranges.misc.hard", hard);
        this.plugin.getConfig().set("entities.spawning.despawn-ranges.misc.soft", soft);
    }

    public void monsterDespawnRanges(Object hard, Object soft) {
        this.plugin.getConfig().set("entities.spawning.despawn-ranges.monster.hard", hard);
        this.plugin.getConfig().set("entities.spawning.despawn-ranges.monster.soft", soft);
    }

    public void undergroundWaterCreatureDespawnRanges(Object hard, Object soft) {
        this.plugin.getConfig().set("entities.spawning.despawn-ranges.underground_water_creature.hard", hard);
        this.plugin.getConfig().set("entities.spawning.despawn-ranges.underground_water_creature.soft", soft);
    }

    public void waterAmbientDespawnRanges(Object hard, Object soft) {
        this.plugin.getConfig().set("entities.spawning.despawn-ranges.water_ambient.hard", hard);
        this.plugin.getConfig().set("entities.spawning.despawn-ranges.water_ambient.soft", soft);
    }

    public void waterCreatureDespawnRanges(Object hard, Object soft) {
        this.plugin.getConfig().set("entities.spawning.despawn-ranges.water_creature.hard", hard);
        this.plugin.getConfig().set("entities.spawning.despawn-ranges.water_creature.soft", soft);
    }

    public void perPlayerMobSpawns(boolean value) {
        this.plugin.getConfig().set("entities.spawning.per-player-mob-spawns", value);
    }

    public void maxEntityCollisions(int value) {
        this.plugin.getConfig().set("collisions.max-entity-collisions", value);
    }

    public void updatePathfindingOnBlockUpdate(boolean value) {
        this.plugin.getConfig().set("misc.update-pathfinding-on-block-update", value);
    }

    public void fixClimbingBypassingCrammingRule(boolean value) {
        this.plugin.getConfig().set("collisions.fix-climbing-bypassing-cramming-rule", value);
    }

    public void armorStandsTick(boolean value) {
        this.plugin.getConfig().set("entities.armor-stands.tick", value);
    }

    public void armorStandsDoCollisionEntityLookups(boolean value) {
        this.plugin.getConfig().set("entities.armor-stands.do-collision-entity-lookups", value);
    }

    public void villagerBehaviourTickRates(int nearbyPOI, int acquirePOI) {
        this.plugin.getConfig().set("tick-rates.behavior.villager.validatenearbypoi", nearbyPOI);
        this.plugin.getConfig().set("tick-rates.behavior.villager.acquirepoi", acquirePOI);
    }

    public void villagerSensorTickRates(int secondarypoisensor, int nearestbedsensor, int villagerbabiessensor, int playersensor, int nearestlivingentitysensor) {
        this.plugin.getConfig().set("tick-rates.sensor.villager.secondarypoisensor", secondarypoisensor);
        this.plugin.getConfig().set("tick-rates.sensor.villager.nearestbedsensor", nearestbedsensor);
        this.plugin.getConfig().set("tick-rates.sensor.villager.villagerbabiessensor", villagerbabiessensor);
        this.plugin.getConfig().set("tick-rates.sensor.villager.playersensor", playersensor);
        this.plugin.getConfig().set("tick-rates.sensor.villager.nearestlivingentitysensor", nearestlivingentitysensor);
    }

    public void altItemDespawnRate(boolean enabled,
                                   int cobblestone,
                                   int netherrack,
                                   int sand,
                                   int redSand,
                                   int gravel,
                                   int dirt,
                                   int shortGrass,
                                   int pumpkin,
                                   int melonSlice,
                                   int kelp,
                                   int bamboo,
                                   int sugarCane,
                                   int twistingVines,
                                   int weepingVines,
                                   int oakLeaves,
                                   int spruceLeaves,
                                   int birchLeaves,
                                   int jungleLeaves,
                                   int acaciaLeaves,
                                   int darkOakLeaves,
                                   int mangroveLeaves,
                                   int cactus,
                                   int diorite,
                                   int granite,
                                   int andesite,
                                   int scaffolding
    ) {
        this.plugin.getConfig().set("entities.spawning.alt-item-despawn-rate.enabled", enabled);
        this.plugin.getConfig().set("entities.spawning.alt-item-despawn-rate.items.cobblestone", cobblestone);
        this.plugin.getConfig().set("entities.spawning.alt-item-despawn-rate.items.netherrack", netherrack);
        this.plugin.getConfig().set("entities.spawning.alt-item-despawn-rate.items.sand", sand);
        this.plugin.getConfig().set("entities.spawning.alt-item-despawn-rate.items.red_sand", redSand);
        this.plugin.getConfig().set("entities.spawning.alt-item-despawn-rate.items.gravel", gravel);
        this.plugin.getConfig().set("entities.spawning.alt-item-despawn-rate.items.dirt", dirt);
        this.plugin.getConfig().set("entities.spawning.alt-item-despawn-rate.items.short_grass", shortGrass);
        this.plugin.getConfig().set("entities.spawning.alt-item-despawn-rate.items.pumpkin", pumpkin);
        this.plugin.getConfig().set("entities.spawning.alt-item-despawn-rate.items.melon_slice", melonSlice);
        this.plugin.getConfig().set("entities.spawning.alt-item-despawn-rate.items.kelp", kelp);
        this.plugin.getConfig().set("entities.spawning.alt-item-despawn-rate.items.bamboo", bamboo);
        this.plugin.getConfig().set("entities.spawning.alt-item-despawn-rate.items.sugar_cane", sugarCane);
        this.plugin.getConfig().set("entities.spawning.alt-item-despawn-rate.items.twisting_vines", twistingVines);
        this.plugin.getConfig().set("entities.spawning.alt-item-despawn-rate.items.weeping_vines", weepingVines);
        this.plugin.getConfig().set("entities.spawning.alt-item-despawn-rate.items.oak_leaves", oakLeaves);
        this.plugin.getConfig().set("entities.spawning.alt-item-despawn-rate.items.spruce_leaves", spruceLeaves);
        this.plugin.getConfig().set("entities.spawning.alt-item-despawn-rate.items.birch_leaves", birchLeaves);
        this.plugin.getConfig().set("entities.spawning.alt-item-despawn-rate.items.jungle_leaves", jungleLeaves);
        this.plugin.getConfig().set("entities.spawning.alt-item-despawn-rate.items.acacia_leaves", acaciaLeaves);
        this.plugin.getConfig().set("entities.spawning.alt-item-despawn-rate.items.dark_oak_leaves", darkOakLeaves);
        this.plugin.getConfig().set("entities.spawning.alt-item-despawn-rate.items.mangrove_leaves", mangroveLeaves);
        this.plugin.getConfig().set("entities.spawning.alt-item-despawn-rate.items.cactus", cactus);
        this.plugin.getConfig().set("entities.spawning.alt-item-despawn-rate.items.diorite", diorite);
        this.plugin.getConfig().set("entities.spawning.alt-item-despawn-rate.items.granite", granite);
        this.plugin.getConfig().set("entities.spawning.alt-item-despawn-rate.items.andesite", andesite);
        this.plugin.getConfig().set("entities.spawning.alt-item-despawn-rate.items.scaffolding", scaffolding);
    }

    public void redstoneImplementation(String value) {
        this.plugin.getConfig().set("misc.redstone-implementation", value);
    }

    public void hopperDisableMoveEvent(boolean value) {
        this.plugin.getConfig().set("hopper.disable-move-event", value);
    }

    public void hopperIgnoreOccludingBlocks(boolean value) {
        this.plugin.getConfig().set("hopper.ignore-occluding-blocks", value);
    }

    public void tickRatesMobSpawner(int value) {
        this.plugin.getConfig().set("tick-rates.mob-spawner", value);
    }

    public void optimizeExplosions(boolean value) {
        this.plugin.getConfig().set("environment.optimize-explosions", value);
    }

    public void treasureMapsEnabled(boolean value) {
        this.plugin.getConfig().set("environment.treasure-maps.enabled", value);
    }

    public void treasureMapsFindAlreadyDiscovered(boolean value) {
        this.plugin.getConfig().set("environment.treasure-maps.find-already-discovered.loot-tables", value);
        this.plugin.getConfig().set("environment.treasure-maps.find-already-discovered.villager-trade", value);
    }

    public void grassSpreadTickRates(int value) {
        this.plugin.getConfig().set("tick-rates.grass-spread", value);
    }

    public void containerUpdateTickRates(int value) {
        this.plugin.getConfig().set("tick-rates.container-update", value);
    }

    public void nonPlayerArrowDespawnRate(int value) {
        this.plugin.getConfig().set("entities.spawning.non-player-arrow-despawn-rate", value);
    }

    public void creativeArrowDespawnRate(int value) {
        this.plugin.getConfig().set("entities.spawning.creative-arrow-despawn-rate", value);
    }

    public void spawnerNerfedMobsShouldJump(boolean value) {
        this.plugin.getConfig().set("spawner-nerfed-mobs-should-jump", value);
    }

    public void save() {
        try {
            this.plugin.getConfig().save(this.file);
        } catch (IOException e) {
            LogUtil log = new LogUtil(plugin);
            log.severe("Error whilst saving Paper World configuration:");
            log.severe(e.getMessage());
        }
    }
}

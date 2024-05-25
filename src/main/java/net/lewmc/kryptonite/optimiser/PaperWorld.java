package net.lewmc.kryptonite.optimiser;

import net.lewmc.kryptonite.Kryptonite;
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
            throw new RuntimeException(e);
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

    public void entityPerChunkSaveLimit(int area_effect_cloud,
                                        int arrow,
                                        int dragon_fireball,
                                        int egg,
                                        int ender_pearl,
                                        int experience_bottle,
                                        int experience_orb,
                                        int eye_of_ender,
                                        int fireball,
                                        int llama_spit,
                                        int potion,
                                        int shulker_bullet,
                                        int small_fireball,
                                        int snowball,
                                        int spectral_arrow,
                                        int trident,
                                        int wither_skull
    ) {
        this.plugin.getConfig().set("chunks.entity-per-chunk-save-limit.area_effect_cloud", area_effect_cloud);
        this.plugin.getConfig().set("chunks.entity-per-chunk-save-limit.arrow", arrow);
        this.plugin.getConfig().set("chunks.entity-per-chunk-save-limit.dragon_fireball", dragon_fireball);
        this.plugin.getConfig().set("chunks.entity-per-chunk-save-limit.egg", egg);
        this.plugin.getConfig().set("chunks.entity-per-chunk-save-limit.ender_pearl", ender_pearl);
        this.plugin.getConfig().set("chunks.entity-per-chunk-save-limit.experience_bottle", experience_bottle);
        this.plugin.getConfig().set("chunks.entity-per-chunk-save-limit.experience_orb", experience_orb);
        this.plugin.getConfig().set("chunks.entity-per-chunk-save-limit.eye_of_ender", eye_of_ender);
        this.plugin.getConfig().set("chunks.entity-per-chunk-save-limit.fireball", fireball);
        this.plugin.getConfig().set("chunks.entity-per-chunk-save-limit.llama_spit", llama_spit);
        this.plugin.getConfig().set("chunks.entity-per-chunk-save-limit.potion", potion);
        this.plugin.getConfig().set("chunks.entity-per-chunk-save-limit.shulker_bullet", shulker_bullet);
        this.plugin.getConfig().set("chunks.entity-per-chunk-save-limit.small_fireball", small_fireball);
        this.plugin.getConfig().set("chunks.entity-per-chunk-save-limit.snowball", snowball);
        this.plugin.getConfig().set("chunks.entity-per-chunk-save-limit.spectral_arrow", spectral_arrow);
        this.plugin.getConfig().set("chunks.entity-per-chunk-save-limit.trident", trident);
        this.plugin.getConfig().set("chunks.entity-per-chunk-save-limit.wither_skull", wither_skull);
    }

    public void ambientDespawnRanges(int hard, int soft) {
        this.plugin.getConfig().set("entities.spawning.despawn-ranges.ambient.hard", hard);
        this.plugin.getConfig().set("entities.spawning.despawn-ranges.ambient.soft", soft);
    }

    public void axolotlsDespawnRanges(int hard, int soft) {
        this.plugin.getConfig().set("entities.spawning.despawn-ranges.axolotls.hard", hard);
        this.plugin.getConfig().set("entities.spawning.despawn-ranges.axolotls.soft", soft);
    }

    public void creatureDespawnRanges(int hard, int soft) {
        this.plugin.getConfig().set("entities.spawning.despawn-ranges.creature.hard", hard);
        this.plugin.getConfig().set("entities.spawning.despawn-ranges.creature.soft", soft);
    }

    public void miscDespawnRanges(int hard, int soft) {
        this.plugin.getConfig().set("entities.spawning.despawn-ranges.misc.hard", hard);
        this.plugin.getConfig().set("entities.spawning.despawn-ranges.misc.soft", soft);
    }

    public void monsterDespawnRanges(int hard, int soft) {
        this.plugin.getConfig().set("entities.spawning.despawn-ranges.monster.hard", hard);
        this.plugin.getConfig().set("entities.spawning.despawn-ranges.monster.soft", soft);
    }

    public void undergroundWaterCreatureDespawnRanges(int hard, int soft) {
        this.plugin.getConfig().set("entities.spawning.despawn-ranges.underground_water_creature.hard", hard);
        this.plugin.getConfig().set("entities.spawning.despawn-ranges.underground_water_creature.soft", soft);
    }

    public void waterAmbientDespawnRanges(int hard, int soft) {
        this.plugin.getConfig().set("entities.spawning.despawn-ranges.water_ambient.hard", hard);
        this.plugin.getConfig().set("entities.spawning.despawn-ranges.water_ambient.soft", soft);
    }

    public void waterCreatureDespawnRanges(int hard, int soft) {
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
                                   int red_sand,
                                   int gravel,
                                   int dirt,
                                   int short_grass,
                                   int pumpkin,
                                   int melon_slice,
                                   int kelp,
                                   int bamboo,
                                   int sugar_cane,
                                   int twisting_vines,
                                   int weeping_vines,
                                   int oak_leaves,
                                   int spruce_leaves,
                                   int birch_leaves,
                                   int jungle_leaves,
                                   int acacia_leaves,
                                   int dark_oak_leaves,
                                   int mangrove_leaves,
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
        this.plugin.getConfig().set("entities.spawning.alt-item-despawn-rate.items.red_sand", red_sand);
        this.plugin.getConfig().set("entities.spawning.alt-item-despawn-rate.items.gravel", gravel);
        this.plugin.getConfig().set("entities.spawning.alt-item-despawn-rate.items.dirt", dirt);
        this.plugin.getConfig().set("entities.spawning.alt-item-despawn-rate.items.short_grass", short_grass);
        this.plugin.getConfig().set("entities.spawning.alt-item-despawn-rate.items.pumpkin", pumpkin);
        this.plugin.getConfig().set("entities.spawning.alt-item-despawn-rate.items.melon_slice", melon_slice);
        this.plugin.getConfig().set("entities.spawning.alt-item-despawn-rate.items.kelp", kelp);
        this.plugin.getConfig().set("entities.spawning.alt-item-despawn-rate.items.bamboo", bamboo);
        this.plugin.getConfig().set("entities.spawning.alt-item-despawn-rate.items.sugar_cane", sugar_cane);
        this.plugin.getConfig().set("entities.spawning.alt-item-despawn-rate.items.twisting_vines", twisting_vines);
        this.plugin.getConfig().set("entities.spawning.alt-item-despawn-rate.items.weeping_vines", weeping_vines);
        this.plugin.getConfig().set("entities.spawning.alt-item-despawn-rate.items.oak_leaves", oak_leaves);
        this.plugin.getConfig().set("entities.spawning.alt-item-despawn-rate.items.spruce_leaves", spruce_leaves);
        this.plugin.getConfig().set("entities.spawning.alt-item-despawn-rate.items.birch_leaves", birch_leaves);
        this.plugin.getConfig().set("entities.spawning.alt-item-despawn-rate.items.jungle_leaves", jungle_leaves);
        this.plugin.getConfig().set("entities.spawning.alt-item-despawn-rate.items.acacia_leaves", acacia_leaves);
        this.plugin.getConfig().set("entities.spawning.alt-item-despawn-rate.items.dark_oak_leaves", dark_oak_leaves);
        this.plugin.getConfig().set("entities.spawning.alt-item-despawn-rate.items.mangrove_leaves", mangrove_leaves);
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
            throw new RuntimeException(e);
        }
    }
}

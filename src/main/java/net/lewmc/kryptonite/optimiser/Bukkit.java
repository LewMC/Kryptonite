package net.lewmc.kryptonite.optimiser;

import net.lewmc.kryptonite.Kryptonite;
import org.bukkit.configuration.InvalidConfigurationException;

import java.io.File;
import java.io.IOException;

public class Bukkit {
    private final Kryptonite plugin;
    private final File file = new File("bukkit.yml");

    public Bukkit(final Kryptonite plugin) {
        this.plugin = plugin;
        try {
            plugin.getConfig().load(this.file);
        } catch (IOException | InvalidConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public void spawnLimits(int monsters, int animals, int waterAnimals, int waterAmbient, int waterUndergroundCreature, int axolotls, int ambient) {
        this.plugin.getConfig().set("environment.spawn-limits.monsters", monsters);
        this.plugin.getConfig().set("environment.spawn-limits.animals", animals);
        this.plugin.getConfig().set("environment.spawn-limits.water-animals", waterAnimals);
        this.plugin.getConfig().set("environment.spawn-limits.water-ambient", waterAmbient);
        this.plugin.getConfig().set("environment.spawn-limits.water-underground-creature", waterUndergroundCreature);
        this.plugin.getConfig().set("environment.spawn-limits.axolotls", axolotls);
        this.plugin.getConfig().set("environment.spawn-limits.ambient", ambient);
    }

    public void ticksPer(int monsters, int animals, int waterAnimals, int waterAmbient, int waterUndergroundCreature, int axolotls, int ambient) {
        this.plugin.getConfig().set("environment.ticks-per.monster-spawns", monsters);
        this.plugin.getConfig().set("environment.ticks-per.animal-spawns", animals);
        this.plugin.getConfig().set("environment.ticks-per.water-spawns", waterAnimals);
        this.plugin.getConfig().set("environment.ticks-per.water-ambient-spawns", waterAmbient);
        this.plugin.getConfig().set("environment.ticks-per.water-underground-creature-spawns", waterUndergroundCreature);
        this.plugin.getConfig().set("environment.ticks-per.axolotl-spawns", axolotls);
        this.plugin.getConfig().set("environment.ticks-per.ambient-spawns", ambient);
    }

    public void save() {
        try {
            this.plugin.getConfig().save(this.file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

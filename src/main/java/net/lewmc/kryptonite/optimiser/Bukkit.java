package net.lewmc.kryptonite.optimiser;

import net.lewmc.kryptonite.Kryptonite;
import net.lewmc.kryptonite.utils.LogUtil;
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
            LogUtil log = new LogUtil(plugin);
            log.severe("Error whilst loading CraftBukkit configuration:");
            log.severe(e.getMessage());
        }
    }

    public void spawnLimits(int monsters, int animals, int waterAnimals, int waterAmbient, int waterUndergroundCreature, int axolotls, int ambient) {
        this.plugin.getConfig().set("spawn-limits.monsters", monsters);
        this.plugin.getConfig().set("spawn-limits.animals", animals);
        this.plugin.getConfig().set("spawn-limits.water-animals", waterAnimals);
        this.plugin.getConfig().set("spawn-limits.water-ambient", waterAmbient);
        this.plugin.getConfig().set("spawn-limits.water-underground-creature", waterUndergroundCreature);
        this.plugin.getConfig().set("spawn-limits.axolotls", axolotls);
        this.plugin.getConfig().set("spawn-limits.ambient", ambient);
    }

    public void ticksPer(int monsters, int animals, int waterAnimals, int waterAmbient, int waterUndergroundCreature, int axolotls, int ambient) {
        this.plugin.getConfig().set("ticks-per.monster-spawns", monsters);
        this.plugin.getConfig().set("ticks-per.animal-spawns", animals);
        this.plugin.getConfig().set("ticks-per.water-spawns", waterAnimals);
        this.plugin.getConfig().set("ticks-per.water-ambient-spawns", waterAmbient);
        this.plugin.getConfig().set("ticks-per.water-underground-creature-spawns", waterUndergroundCreature);
        this.plugin.getConfig().set("ticks-per.axolotl-spawns", axolotls);
        this.plugin.getConfig().set("ticks-per.ambient-spawns", ambient);
    }

    public void save() {
        try {
            this.plugin.getConfig().save(this.file);
        } catch (IOException e) {
            LogUtil log = new LogUtil(plugin);
            log.severe("Error whilst saving Bukkit configuration:");
            log.severe(e.getMessage());
        }
    }
}

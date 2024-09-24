package net.lewmc.kryptonite.legacy.edb;

import net.lewmc.kryptonite.Kryptonite;
import net.lewmc.kryptonite.utils.LogUtil;
import net.lewmc.kryptonite.utils.PropertiesUtil;
import net.lewmc.kryptonite.utils.SoftwareUtil;
import org.bukkit.configuration.InvalidConfigurationException;

import java.io.File;
import java.io.IOException;

public class Patch {
    private final Kryptonite plugin;
    private final SoftwareUtil softwareUtil;
    private final LogUtil log;
    private final Check check;

    private final File paperGlobalConfig = new File("config/paper-global.yml");
    private final File paperWorldDefaultsConfig = new File("config/paper-world-defaults.yml");
    private final File spigotConfig = new File("spigot.yml");

    public Patch(Kryptonite plugin) {
        this.plugin = plugin;
        this.softwareUtil = new SoftwareUtil(plugin);
        this.log = new LogUtil(plugin);
        this.check = new Check(plugin);
    }

    public boolean all() {
        return this.edb1() &&
                this.edb2() &&
                this.edb3() &&
                this.edb4() &&
                this.edb5() &&
                this.edb6() &&
                this.edb7() &&
                this.edb8() &&
                this.edb9();
    }

    public boolean edb1() {
        if (softwareUtil.supportsPaperWorld()) {
            try {
                this.plugin.getConfig().load(this.paperWorldDefaultsConfig);
                this.plugin.getConfig().set("entities.armor-stands.do-collision-entity-lookups", false);
                this.plugin.getConfig().set("entities.armor-stands.tick", false);
                this.plugin.getConfig().save(this.paperWorldDefaultsConfig);

                return this.check.edb1();
            } catch (IOException | InvalidConfigurationException e) {
                this.log.severe(e.toString());
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean edb2() {
        if (softwareUtil.supportsPaperWorld()) {
            try {
                this.plugin.getConfig().load(this.paperGlobalConfig);
                this.plugin.getConfig().set("item-validation.book-size.page-max", 1024);
                this.plugin.getConfig().save(this.paperGlobalConfig);

                return this.check.edb2();
            } catch (IOException | InvalidConfigurationException e) {
                this.log.severe(e.toString());
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean edb3() {
        if (softwareUtil.supportsPaperWorld()) {
            try {
                this.plugin.getConfig().load(this.paperWorldDefaultsConfig);
                this.plugin.getConfig().set("collisions.max-entity-collisions", 2);
                this.plugin.getConfig().set("collisions.fix-climbing-bypassing-cramming-rule", true);
                this.plugin.getConfig().save(this.paperWorldDefaultsConfig);

                return this.check.edb3();
            } catch (IOException | InvalidConfigurationException e) {
                this.log.severe(e.toString());
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean edb4() {
        if (softwareUtil.supportsPaperWorld()) {
            try {
                this.plugin.getConfig().load(this.paperGlobalConfig);
                this.plugin.getConfig().set("packet-limiter.overrides.ServerboundCommandSuggestionPacket.action", "DROP");
                this.plugin.getConfig().set("packet-limiter.overrides.ServerboundCommandSuggestionPacket.interval", 1.0);
                this.plugin.getConfig().set("packet-limiter.overrides.ServerboundCommandSuggestionPacket.max-packet-rate", 15.0);
                this.plugin.getConfig().save(this.paperGlobalConfig);

                return this.check.edb4();
            } catch (IOException | InvalidConfigurationException e) {
                this.log.severe(e.toString());
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean edb5() {
        if (softwareUtil.supportsSpigot()) {
            try {
                this.plugin.getConfig().load(this.spigotConfig);
                this.plugin.getConfig().set("commands.spam-exclusions", "[]");
                this.plugin.getConfig().save(this.spigotConfig);

                return this.check.edb5();
            } catch (IOException | InvalidConfigurationException e) {
                this.log.severe(e.toString());
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean edb6() {
        if (softwareUtil.supportsPaperWorld()) {
            try {
                this.plugin.getConfig().load(this.paperGlobalConfig);
                this.plugin.getConfig().set("misc.max-joins-per-tick", 3);
                this.plugin.getConfig().save(this.paperGlobalConfig);

                return this.check.edb6();
            } catch (IOException | InvalidConfigurationException e) {
                this.log.severe(e.toString());
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean edb7() {
        if (softwareUtil.supportsCraftBukkit()) {
            PropertiesUtil propertiesUtil = new PropertiesUtil("server.properties");
            propertiesUtil.setProperty("max-chained-neighbor-updates", "10000");

            return this.check.edb7();
        } else {
            return false;
        }
    }

    public boolean edb8() {
        if (softwareUtil.supportsPaperWorld()) {
            try {
                this.plugin.getConfig().load(this.paperWorldDefaultsConfig);
                this.plugin.getConfig().set("chunks.entity-per-chunk-save-limit.arrow", 8);
                this.plugin.getConfig().set("chunks.entity-per-chunk-save-limit.ender_pearl", 8);
                this.plugin.getConfig().set("chunks.entity-per-chunk-save-limit.experience_orb", 8);
                this.plugin.getConfig().set("chunks.entity-per-chunk-save-limit.fireball", 8);
                this.plugin.getConfig().set("chunks.entity-per-chunk-save-limit.small_fireball", 8);
                this.plugin.getConfig().set("chunks.entity-per-chunk-save-limit.snowball", 8);
                this.plugin.getConfig().save(this.paperWorldDefaultsConfig);

                return this.check.edb8();
            } catch (IOException | InvalidConfigurationException e) {
                this.log.severe(e.toString());
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean edb9() {
        if (softwareUtil.supportsPaperWorld()) {
            try {
                this.plugin.getConfig().load(this.paperGlobalConfig);
                this.plugin.getConfig().set("packet-limiter.overrides.ServerboundPlaceRecipePacket.action", "DROP");
                this.plugin.getConfig().set("packet-limiter.overrides.ServerboundPlaceRecipePacket.interval", 4.0);
                this.plugin.getConfig().set("packet-limiter.overrides.ServerboundPlaceRecipePacket.max-packet-rate", 5.0);
                this.plugin.getConfig().save(this.paperGlobalConfig);

                return this.check.edb9();
            } catch (IOException | InvalidConfigurationException e) {
                this.log.severe(e.toString());
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean edb10a() {
        if (softwareUtil.supportsPaperWorld()) {
            try {
                this.plugin.getConfig().load(this.paperWorldDefaultsConfig);
                this.plugin.getConfig().set("environment.treasure-maps.enabled", false);
                this.plugin.getConfig().save(this.paperWorldDefaultsConfig);

                return this.check.edb10a();
            } catch (IOException | InvalidConfigurationException e) {
                this.log.severe(e.toString());
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean edb10b() {
        if (softwareUtil.supportsPaperWorld()) {
            try {
                this.plugin.getConfig().load(this.paperWorldDefaultsConfig);
                this.plugin.getConfig().set("environment.treasure-maps.find-already-discovered.loot-tables", true);
                this.plugin.getConfig().set("environment.treasure-maps.find-already-discovered.villager-trade", true);
                this.plugin.getConfig().save(this.paperWorldDefaultsConfig);

                return this.check.edb10b();
            } catch (IOException | InvalidConfigurationException e) {
                this.log.severe(e.toString());
                return false;
            }
        } else {
            return false;
        }
    }
}

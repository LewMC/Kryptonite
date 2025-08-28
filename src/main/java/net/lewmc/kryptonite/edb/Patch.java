package net.lewmc.kryptonite.edb;

import net.lewmc.kryptonite.Kryptonite;
import net.lewmc.kryptonite.utils.ConfigurationUtil;
import net.lewmc.kryptonite.utils.PropertiesUtil;
import net.lewmc.kryptonite.utils.SoftwareUtil;
import org.bukkit.command.CommandSender;

public class Patch {
    private final Kryptonite plugin;
    private final SoftwareUtil softwareUtil;
    private final Check check;
    private final CommandSender user;

    public Patch(Kryptonite plugin, CommandSender sender) {
        this.plugin = plugin;
        this.softwareUtil = new SoftwareUtil(plugin);
        this.check = new Check(plugin, sender);
        this.user = sender;
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
                this.edb9() &&
                this.edb10() &&
                this.edb11() &&
                this.edb12();
    }

    public boolean edb1() {
        if (softwareUtil.supportsPaperWorld()) {
            ConfigurationUtil cf = new ConfigurationUtil(this.plugin, this.user);
            cf.load("config/paper-world-defaults.yml");

            cf.set("entities.armor-stands.do-collision-entity-lookups", "false");
            cf.set("entities.armor-stands.tick", "false");
            cf.save();

            this.plugin.restartRequired = true;

            return this.check.edb1();
        } else {
            return false;
        }
    }

    public boolean edb2() {
        if (softwareUtil.supportsPaperWorld()) {
            ConfigurationUtil cf = new ConfigurationUtil(this.plugin, this.user);
            cf.load("config/paper-global.yml");

            cf.set("item-validation.book-size.page-max", "1024");
            cf.save();

            this.plugin.restartRequired = true;

            return this.check.edb2();
        } else {
            return false;
        }
    }

    public boolean edb3() {
        if (softwareUtil.supportsPaperWorld()) {
            ConfigurationUtil cf = new ConfigurationUtil(this.plugin, this.user);
            cf.load("config/paper-world-defaults.yml");

            cf.set("collisions.max-entity-collisions", "2");
            cf.set("collisions.fix-climbing-bypassing-cramming-rule", "true");
            cf.save();

            this.plugin.restartRequired = true;

            return this.check.edb3();
        } else {
            return false;
        }
    }

    public boolean edb4() {
        if (softwareUtil.supportsPaperWorld()) {
            ConfigurationUtil cf = new ConfigurationUtil(this.plugin, this.user);
            cf.load("config/paper-global.yml");

            cf.set("packet-limiter.overrides.ServerboundCommandSuggestionPacket.action", "DROP");
            cf.set("packet-limiter.overrides.ServerboundCommandSuggestionPacket.interval", "1.0");
            cf.set("packet-limiter.overrides.ServerboundCommandSuggestionPacket.max-packet-rate", "15.0");
            cf.save();

            this.plugin.restartRequired = true;

            return this.check.edb4();
        } else {
            return false;
        }
    }

    public boolean edb5() {
        if (softwareUtil.supportsSpigot()) {
            ConfigurationUtil cf = new ConfigurationUtil(this.plugin, this.user);
            cf.load("spigot.yml");
            
            cf.set("commands.spam-exclusions", "[]");
            cf.save();

            this.plugin.restartRequired = true;

            return this.check.edb5();
        } else {
            return false;
        }
    }

    public boolean edb6() {
        if (softwareUtil.supportsPaperWorld()) {
            ConfigurationUtil cf = new ConfigurationUtil(this.plugin, this.user);
            cf.load("config/paper-global.yml");

            cf.set("misc.max-joins-per-tick", "3");
            cf.save();

            this.plugin.restartRequired = true;

            return this.check.edb6();
        } else {
            return false;
        }
    }

    public boolean edb7() {
        if (softwareUtil.supportsCraftBukkit()) {
            PropertiesUtil propertiesUtil = new PropertiesUtil("server.properties");
            propertiesUtil.setProperty("max-chained-neighbor-updates", "10000");

            this.plugin.restartRequired = true;

            return this.check.edb7();
        } else {
            return false;
        }
    }

    public boolean edb8() {
        if (softwareUtil.supportsPaperWorld()) {
            ConfigurationUtil cf = new ConfigurationUtil(this.plugin, this.user);
            cf.load("config/paper-world-defaults.yml");

            cf.set("chunks.entity-per-chunk-save-limit.arrow", "8");
            cf.set("chunks.entity-per-chunk-save-limit.ender_pearl", "8");
            cf.set("chunks.entity-per-chunk-save-limit.experience_orb", "8");
            cf.set("chunks.entity-per-chunk-save-limit.fireball", "8");
            cf.set("chunks.entity-per-chunk-save-limit.small_fireball", "8");
            cf.set("chunks.entity-per-chunk-save-limit.snowball", "8");
            cf.save();

            this.plugin.restartRequired = true;

            return this.check.edb8();
        } else {
            return false;
        }
    }

    public boolean edb9() {
        if (softwareUtil.supportsPaperWorld()) {
            ConfigurationUtil cf = new ConfigurationUtil(this.plugin, this.user);
            cf.load("config/paper-global.yml");

            cf.set("packet-limiter.overrides.ServerboundPlaceRecipePacket.action", "DROP");
            cf.set("packet-limiter.overrides.ServerboundPlaceRecipePacket.interval", "4.0");
            cf.set("packet-limiter.overrides.ServerboundPlaceRecipePacket.max-packet-rate", "5.0");
            cf.save();

            this.plugin.restartRequired = true;

            return this.check.edb9();
        } else {
            return false;
        }
    }

    public boolean edb10() {
        if (softwareUtil.supportsPaperWorld()) {
            ConfigurationUtil cf = new ConfigurationUtil(this.plugin, this.user);
            cf.load("config/paper-world-defaults.yml");

            cf.set("environment.nether-ceiling-void-damage-height", "127");
            cf.save();

            this.plugin.restartRequired = true;

            return this.check.edb10();
        } else {
            return false;
        }
    }

    public boolean edb11() {
        if (softwareUtil.supportsPaperWorld()) {
            ConfigurationUtil cf = new ConfigurationUtil(this.plugin, this.user);
            cf.load("config/paper-world-defaults.yml");

            cf.set("anticheat.anti-xray.enabled", "true");
            cf.save();

            this.plugin.restartRequired = true;

            return this.check.edb11();
        } else {
            return false;
        }
    }

    public boolean edb12() {
        if (softwareUtil.supportsMinecraft()) {
            PropertiesUtil propertiesUtil = new PropertiesUtil("server.properties");
            propertiesUtil.setProperty("online-mode", "true");

            this.plugin.restartRequired = true;

            return this.check.edb12();
        } else {
            return false;
        }
    }
}

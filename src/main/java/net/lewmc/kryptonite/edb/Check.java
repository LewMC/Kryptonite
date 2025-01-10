package net.lewmc.kryptonite.edb;

import net.lewmc.kryptonite.Kryptonite;
import net.lewmc.kryptonite.kos.config.ServerProperties;
import net.lewmc.kryptonite.utils.ConfigurationUtil;
import net.lewmc.kryptonite.utils.LogUtil;
import net.lewmc.kryptonite.utils.PropertiesUtil;
import net.lewmc.kryptonite.utils.SoftwareUtil;
import org.bukkit.command.CommandSender;
import java.util.Objects;

public class Check {
    private final Kryptonite plugin;
    private final SoftwareUtil softwareUtil;
    private final LogUtil log;
    private final CommandSender player;

    public Check(Kryptonite plugin, CommandSender player) {
        this.player = player;
        this.plugin = plugin;
        this.softwareUtil = new SoftwareUtil(plugin);
        this.log = new LogUtil(plugin);
    }

    public boolean edb1() {
        if (this.softwareUtil.supportsPaperWorld()) {
            ConfigurationUtil cf = new ConfigurationUtil(this.plugin, this.player);
            cf.load("config/paper-world-defaults.yml");

            try {
                if (cf.getBoolean("entities.armor-stands.do-collision-entity-lookups")) {
                    this.logThis(
                            "EDB-1",
                            "entities.armor-stands.do-collision-entity-lookups",
                            Objects.requireNonNull(this.plugin.getConfig().get("entities.armor-stands.do-collision-entity-lookups")).toString(),
                            "false");

                    return false;
                } else if (cf.getBoolean("entities.armor-stands.tick")) {
                    this.logThis(
                            "EDB-1",
                            "entities.armor-stands.tick",
                            Objects.requireNonNull(this.plugin.getConfig().get("entities.armor-stands.tick")).toString(),
                            "false");

                    return false;
                } else {
                    return true;
                }
            } catch (Exception e) {
                this.logThis(
                        "EDB-1",
                        "entities.armor-stands.tick",
                        e.getMessage(),
                        "false");
                return false;
            }
        } else {
            this.logThis(
                    "EDB-1",
                    "software",
                    this.plugin.getServer().getName(),
                    "Paper");
            return false;
        }
    }

    public boolean edb2() {
        if (this.softwareUtil.supportsPaperWorld()) {
            ConfigurationUtil cf = new ConfigurationUtil(this.plugin, this.player);
            cf.load("config/paper-global.yml");

            if (!Objects.equals(cf.getString("item-validation.book-size.page-max"), "1024")) {
                this.logThis(
                        "EDB-2",
                        "item-validation.book-size.page-max",
                        cf.getString("item-validation.book-size.page-max"),
                        "1024");
                return false;
            }
            return true;
        } else {
            this.logThis(
                    "EDB-2",
                    "software",
                    this.plugin.getServer().getName(),
                    "Paper");
            return false;
        }
    }

    public boolean edb3() {
        if (this.softwareUtil.supportsPaperWorld()) {
            ConfigurationUtil cf = new ConfigurationUtil(this.plugin, this.player);
            cf.load("config/paper-world-defaults.yml");

            if (!Objects.equals(cf.getString("collisions.max-entity-collisions"), "2")) {
                this.logThis(
                        "EDB-3",
                        "collisions.max-entity-collisions",
                        cf.getString("collisions.max-entity-collisions"),
                        "2");
                return false;
            }

            if (!Objects.equals(cf.getString("collisions.fix-climbing-bypassing-cramming-rule"), "true")) {
                this.logThis(
                        "EDB-3",
                        "collisions.fix-climbing-bypassing-cramming-rule",
                        cf.getString("collisions.fix-climbing-bypassing-cramming-rule"),
                        "true");
                return false;
            }
            return true;
        } else {
            this.logThis(
                    "EDB-3",
                    "software",
                    this.plugin.getServer().getName(),
                    "Paper");
            return false;
        }
    }

    public boolean edb4() {
        if (this.softwareUtil.supportsPaperWorld()) {
            ConfigurationUtil cf = new ConfigurationUtil(this.plugin, this.player);
            cf.load("config/paper-global.yml");

            if (!Objects.equals(cf.getString("packet-limiter.overrides.ServerboundCommandSuggestionPacket.action"), "DROP")) {
                this.logThis(
                        "EDB-4",
                        "packet-limiter.overrides.ServerboundCommandSuggestionPacket.action",
                        cf.getString("packet-limiter.overrides.ServerboundCommandSuggestionPacket.action"),
                        "DROP");
                return false;
            }
            if (!Objects.equals(cf.getString("packet-limiter.overrides.ServerboundCommandSuggestionPacket.interval"), "1.0")) {
                this.logThis(
                        "EDB-4",
                        "packet-limiter.overrides.ServerboundCommandSuggestionPacket.interval",
                        cf.getString("packet-limiter.overrides.ServerboundCommandSuggestionPacket.interval"),
                        "1.0");
                return false;
            }
            if (!Objects.equals(cf.getString("packet-limiter.overrides.ServerboundCommandSuggestionPacket.max-packet-rate"), "15.0")) {
                this.logThis(
                        "EDB-4",
                        "packet-limiter.overrides.ServerboundCommandSuggestionPacket.max-packet-rate",
                        cf.getString("packet-limiter.overrides.ServerboundCommandSuggestionPacket.max-packet-rate"),
                        "15.0");
                return false;
            }
            return true;
        } else {
            this.logThis(
                    "EDB-4",
                    "software",
                    this.plugin.getServer().getName(),
                    "Paper");
            return false;
        }
    }

    public boolean edb5() {
        if (this.softwareUtil.supportsSpigot()) {
            ConfigurationUtil cf = new ConfigurationUtil(this.plugin, this.player);
            cf.load("spigot.yml");

            if (!Objects.equals(cf.getString("commands.spam-exclusions"), "[]")) {
                this.logThis(
                        "EDB-5",
                        "commands.spam-exclusions",
                        cf.getString("commands.spam-exclusions"),
                        "null");
                return false;
            }
            return true;
        } else {
            this.logThis(
                    "EDB-5",
                    "software",
                    this.plugin.getServer().getName(),
                    "Spigot");
            return false;
        }
    }

    public boolean edb6() {
        if (this.softwareUtil.supportsPaperWorld()) {
            ConfigurationUtil cf = new ConfigurationUtil(this.plugin, this.player);
            cf.load("config/paper-global.yml");

            if (!Objects.equals(cf.getString("misc.max-joins-per-tick"), "3")) {
                this.logThis(
                        "EDB-6",
                        "misc.max-joins-per-tick",
                        cf.getString("misc.max-joins-per-tick"),
                        "3");
                return false;
            }

            return true;
        } else {
            this.logThis(
                    "EDB-6",
                    "software",
                    this.plugin.getServer().getName(),
                    "Paper");
            return false;
        }
    }

    public boolean edb7() {
        if (this.softwareUtil.supportsCraftBukkit()) {
            String maxChainedNeighbor = null;

            try {
                PropertiesUtil propertiesUtil = new PropertiesUtil("server.properties");
                maxChainedNeighbor = propertiesUtil.getProperty("max-chained-neighbor-updates");
            } catch (Exception e) {
                this.log.severe(e.getMessage());
            }

            if (!Objects.equals(maxChainedNeighbor, "10000")) {
                this.logThis(
                        "EDB-7",
                        "max-chained-neighbor-updates",
                        maxChainedNeighbor,
                        "10000");
                return false;
            }

            return true;
        } else {
            this.logThis(
                    "EDB-7",
                    "software",
                    this.plugin.getServer().getName(),
                    "CraftBukkit");
            return false;
        }
    }

    public boolean edb8() {
        if (this.softwareUtil.supportsPaperWorld()) {
            ConfigurationUtil cf = new ConfigurationUtil(this.plugin, this.player);
            cf.load("config/paper-world-defaults.yml");

            if (!Objects.equals(cf.getString("chunks.entity-per-chunk-save-limit.arrow"), "8")) {
                this.logThis(
                        "EDB-8",
                        "chunks.entity-per-chunk-save-limit.arrow",
                        cf.getString("chunks.entity-per-chunk-save-limit.arrow"),
                        "8");
                return false;
            }

            if (!Objects.equals(cf.getString("chunks.entity-per-chunk-save-limit.ender_pearl"), "8")) {
                this.logThis(
                        "EDB-8",
                        "chunks.entity-per-chunk-save-limit.ender_pearl",
                        cf.getString("chunks.entity-per-chunk-save-limit.ender_pearl"),
                        "8");
                return false;
            }

            if (!Objects.equals(cf.getString("chunks.entity-per-chunk-save-limit.experience_orb"), "8")) {
                this.logThis(
                        "EDB-8",
                        "chunks.entity-per-chunk-save-limit.experience_orb",
                        cf.getString("chunks.entity-per-chunk-save-limit.experience_orb"),
                        "8");
                return false;
            }

            if (!Objects.equals(cf.getString("chunks.entity-per-chunk-save-limit.fireball"), "8")) {
                this.logThis(
                        "EDB-8",
                        "chunks.entity-per-chunk-save-limit.fireball",
                        cf.getString("chunks.entity-per-chunk-save-limit.fireball"),
                        "8");
                return false;
            }

            if (!Objects.equals(cf.getString("chunks.entity-per-chunk-save-limit.small_fireball"), "8")) {
                this.logThis(
                        "EDB-8",
                        "chunks.entity-per-chunk-save-limit.small_fireball",
                        cf.getString("chunks.entity-per-chunk-save-limit.small_fireball"),
                        "8");
                return false;
            }

            if (!Objects.equals(cf.getString("chunks.entity-per-chunk-save-limit.snowball"), "8")) {
                this.logThis(
                        "EDB-8",
                        "chunks.entity-per-chunk-save-limit.snowball",
                        cf.getString("chunks.entity-per-chunk-save-limit.snowball"),
                        "8");
                return false;
            }

            return true;
        } else {
            this.logThis(
                    "EDB-8",
                    "software",
                    this.plugin.getServer().getName(),
                    "Paper");
            return false;
        }
    }

    public boolean edb9() {
        if (this.softwareUtil.supportsPaperWorld()) {
            ConfigurationUtil cf = new ConfigurationUtil(this.plugin, this.player);
            cf.load("config/paper-global.yml");

            if (!Objects.equals(cf.getString("packet-limiter.overrides.ServerboundPlaceRecipePacket.action"), "DROP")) {
                this.logThis(
                        "EDB-9",
                        "packet-limiter.overrides.ServerboundPlaceRecipePacket.action",
                        cf.getString("packet-limiter.overrides.ServerboundPlaceRecipePacket.action"),
                        "DROP");
                return false;
            }

            if (!Objects.equals(cf.getString("packet-limiter.overrides.ServerboundPlaceRecipePacket.interval"), "4.0")) {
                this.logThis(
                        "EDB-9",
                        "packet-limiter.overrides.ServerboundPlaceRecipePacket.interval",
                        cf.getString("packet-limiter.overrides.ServerboundPlaceRecipePacket.interval"),
                        "4.0");
                return false;
            }

            if (!Objects.equals(cf.getString("packet-limiter.overrides.ServerboundPlaceRecipePacket.max-packet-rate"), "5.0")) {
                this.logThis(
                        "EDB-9",
                        "packet-limiter.overrides.ServerboundPlaceRecipePacket.max-packet-rate",
                        cf.getString("packet-limiter.overrides.ServerboundPlaceRecipePacket.max-packet-rate"),
                        "5.0");
                return false;
            }

            return true;
        } else {
            this.logThis(
                    "EDB-9",
                    "software",
                    this.plugin.getServer().getName(),
                    "Paper");
            return false;
        }
    }

    public boolean edb10() {
        if (this.softwareUtil.supportsPaperWorld()) {
            ConfigurationUtil cf = new ConfigurationUtil(this.plugin, this.player);
            cf.load("config/paper-world-defaults.yml");

            if (!Objects.equals(cf.getString("environment.nether-ceiling-void-damage-height"), "127")) {
                this.logThis(
                        "EDB-10",
                        "environment.nether-ceiling-void-damage-height",
                        cf.getString("environment.nether-ceiling-void-damage-height"),
                        "127");
                return false;
            }

            return true;
        } else {
            this.logThis(
                    "EDB-10",
                    "software",
                    this.plugin.getServer().getName(),
                    "Paper");
            return false;
        }
    }

    public boolean edb11() {
        if (this.softwareUtil.supportsPaperWorld()) {
            ConfigurationUtil cf = new ConfigurationUtil(this.plugin, this.player);
            cf.load("config/paper-world-defaults.yml");

            if (!Objects.equals(cf.getString("anticheat.anti-xray.enabled"), "true")) {
                this.logThis(
                        "EDB-11",
                        "anticheat.anti-xray.enabled",
                        cf.getString("anticheat.anti-xray.enabled"),
                        "true");
                return false;
            }

            return true;
        } else {
            this.logThis(
                    "EDB-11",
                    "software",
                    this.plugin.getServer().getName(),
                    "Paper");
            return false;
        }
    }

    public boolean edb12() {
        if (this.softwareUtil.supportsServerProperties()) {
            PropertiesUtil sp = new PropertiesUtil("server.properties");

            if (!Objects.equals(sp.getProperty("online-mode"), "true")) {
                this.logThis(
                        "EDB-12",
                        "online-mode",
                        sp.getProperty("online-mode"),
                        "true");
                return false;
            }

            return true;
        } else {
            this.logThis(
                    "EDB-12",
                    "software",
                    this.plugin.getServer().getName(),
                    "MinecraftServer");
            return false;
        }
    }

    public void logThis(String id, String value, String current, String expected) {
        this.log.warn("[" + id + "][FAIL] '" + value + "' is '" + current + "' - expected '" + expected + "'");
    }

    public void warnThis(String id, String value, String current, String expected) {
        this.log.warn("[" + id + "][WARN] '" + value + "' is '" + current + "' - expected '" + expected + "'");
    }
}

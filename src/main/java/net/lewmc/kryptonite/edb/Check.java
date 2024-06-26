package net.lewmc.kryptonite.edb;

import net.lewmc.kryptonite.Kryptonite;
import net.lewmc.kryptonite.utils.LogUtil;
import net.lewmc.kryptonite.utils.PropertiesUtil;
import net.lewmc.kryptonite.utils.SoftwareUtil;
import org.bukkit.configuration.InvalidConfigurationException;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Check {
    private final Kryptonite plugin;
    private final SoftwareUtil softwareUtil;
    private final LogUtil log;

    public Check(Kryptonite plugin) {
        this.plugin = plugin;
        this.softwareUtil = new SoftwareUtil(plugin);
        this.log = new LogUtil(plugin);
    }

    public boolean edb1() {
        if (this.softwareUtil.supportsPaperWorld()) {
            try {
                this.plugin.getConfig().load(new File("config/paper-world-defaults.yml"));
            } catch (IOException | InvalidConfigurationException e) {
                throw new RuntimeException(e);
            }

            if (this.plugin.getConfig().get("entities.armor-stands.do-collision-entity-lookups") == "true") {
                this.logThis(
                        "EDB-1",
                        "entities.armor-stands.do-collision-entity-lookups",
                        Objects.requireNonNull(this.plugin.getConfig().get("entities.armor-stands.do-collision-entity-lookups")).toString(),
                        "false");

                return false;
            } else if (this.plugin.getConfig().get("entities.armor-stands.tick") == "true") {
                this.logThis(
                        "EDB-1",
                        "entities.armor-stands.tick",
                        Objects.requireNonNull(this.plugin.getConfig().get("entities.armor-stands.tick")).toString(),
                        "false");

                return false;
            } else {
                return true;
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
            try {
                this.plugin.getConfig().load(new File("config/paper-global.yml"));
            } catch (IOException | InvalidConfigurationException e) {
                throw new RuntimeException(e);
            }

            if (!Objects.equals(Objects.requireNonNull(this.plugin.getConfig().get("item-validation.book-size.page-max")).toString(), "1024")) {
                this.logThis(
                        "EDB-2",
                        "item-validation.book-size.page-max",
                        Objects.requireNonNull(this.plugin.getConfig().get("item-validation.book-size.page-max")).toString(),
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
            try {
                this.plugin.getConfig().load(new File("config/paper-world-defaults.yml"));
            } catch (IOException | InvalidConfigurationException e) {
                throw new RuntimeException(e);
            }

            if (!Objects.equals(Objects.requireNonNull(this.plugin.getConfig().get("collisions.max-entity-collisions")).toString(), "2")) {
                this.logThis(
                        "EDB-3",
                        "collisions.max-entity-collisions",
                        Objects.requireNonNull(this.plugin.getConfig().get("collisions.max-entity-collisions")).toString(),
                        "2");
                return false;
            }

            if (!Objects.equals(Objects.requireNonNull(this.plugin.getConfig().get("collisions.fix-climbing-bypassing-cramming-rule")).toString(), "true")) {
                this.logThis(
                        "EDB-3",
                        "collisions.fix-climbing-bypassing-cramming-rule",
                        Objects.requireNonNull(this.plugin.getConfig().get("collisions.fix-climbing-bypassing-cramming-rule")).toString(),
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
            try {
                this.plugin.getConfig().load(new File("config/paper-global.yml"));
            } catch (IOException | InvalidConfigurationException e) {
                throw new RuntimeException(e);
            }

            if (!Objects.equals(Objects.requireNonNull(this.plugin.getConfig().get("packet-limiter.overrides.ServerboundCommandSuggestionPacket.action")).toString(), "DROP")) {
                this.logThis(
                        "EDB-4",
                        "packet-limiter.overrides.ServerboundCommandSuggestionPacket.action",
                        Objects.requireNonNull(this.plugin.getConfig().get("packet-limiter.overrides.ServerboundCommandSuggestionPacket.action")).toString(),
                        "DROP");
                return false;
            }
            if (!Objects.equals(Objects.requireNonNull(this.plugin.getConfig().get("packet-limiter.overrides.ServerboundCommandSuggestionPacket.interval")).toString(), "1.0")) {
                this.logThis(
                        "EDB-4",
                        "packet-limiter.overrides.ServerboundCommandSuggestionPacket.interval",
                        Objects.requireNonNull(this.plugin.getConfig().get("packet-limiter.overrides.ServerboundCommandSuggestionPacket.interval")).toString(),
                        "1.0");
                return false;
            }
            if (!Objects.equals(Objects.requireNonNull(this.plugin.getConfig().get("packet-limiter.overrides.ServerboundCommandSuggestionPacket.max-packet-rate")).toString(), "15.0")) {
                this.logThis(
                        "EDB-4",
                        "packet-limiter.overrides.ServerboundCommandSuggestionPacket.max-packet-rate",
                        Objects.requireNonNull(this.plugin.getConfig().get("packet-limiter.overrides.ServerboundCommandSuggestionPacket.max-packet-rate")).toString(),
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
            try {
                this.plugin.getConfig().load(new File("spigot.yml"));
            } catch (IOException | InvalidConfigurationException e) {
                throw new RuntimeException(e);
            }

            if (!Objects.equals(Objects.requireNonNull(this.plugin.getConfig().get("commands.spam-exclusions")).toString(), "[]")) {
                this.logThis(
                        "EDB-5",
                        "commands.spam-exclusions",
                        Objects.requireNonNull(this.plugin.getConfig().get("commands.spam-exclusions")).toString(),
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
            try {
                this.plugin.getConfig().load(new File("config/paper-global.yml"));
            } catch (IOException | InvalidConfigurationException e) {
                throw new RuntimeException(e);
            }

            if (!Objects.equals(Objects.requireNonNull(this.plugin.getConfig().get("misc.max-joins-per-tick")).toString(), "3")) {
                this.logThis(
                        "EDB-6",
                        "misc.max-joins-per-tick",
                        Objects.requireNonNull(this.plugin.getConfig().get("misc.max-joins-per-tick")).toString(),
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

            if (!Objects.equals(Objects.requireNonNull(maxChainedNeighbor), "10000")) {
                this.logThis(
                        "EDB-7",
                        "max-chained-neighbor-updates",
                        Objects.requireNonNull(this.plugin.getConfig().get("max-chained-neighbor-updates")).toString(),
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
            try {
                this.plugin.getConfig().load(new File("config/paper-world-defaults.yml"));
            } catch (IOException | InvalidConfigurationException e) {
                throw new RuntimeException(e);
            }

            if (!Objects.equals(Objects.requireNonNull(this.plugin.getConfig().get("chunks.entity-per-chunk-save-limit.arrow")).toString(), "8")) {
                this.logThis(
                        "EDB-8",
                        "chunks.entity-per-chunk-save-limit.arrow",
                        Objects.requireNonNull(this.plugin.getConfig().get("chunks.entity-per-chunk-save-limit.arrow")).toString(),
                        "8");
                return false;
            }

            if (!Objects.equals(Objects.requireNonNull(this.plugin.getConfig().get("chunks.entity-per-chunk-save-limit.ender_pearl")).toString(), "8")) {
                this.logThis(
                        "EDB-8",
                        "chunks.entity-per-chunk-save-limit.ender_pearl",
                        Objects.requireNonNull(this.plugin.getConfig().get("chunks.entity-per-chunk-save-limit.ender_pearl")).toString(),
                        "8");
                return false;
            }

            if (!Objects.equals(Objects.requireNonNull(this.plugin.getConfig().get("chunks.entity-per-chunk-save-limit.experience_orb")).toString(), "8")) {
                this.logThis(
                        "EDB-8",
                        "chunks.entity-per-chunk-save-limit.experience_orb",
                        Objects.requireNonNull(this.plugin.getConfig().get("chunks.entity-per-chunk-save-limit.experience_orb")).toString(),
                        "8");
                return false;
            }

            if (!Objects.equals(Objects.requireNonNull(this.plugin.getConfig().get("chunks.entity-per-chunk-save-limit.fireball")).toString(), "8")) {
                this.logThis(
                        "EDB-8",
                        "chunks.entity-per-chunk-save-limit.fireball",
                        Objects.requireNonNull(this.plugin.getConfig().get("chunks.entity-per-chunk-save-limit.fireball")).toString(),
                        "8");
                return false;
            }

            if (!Objects.equals(Objects.requireNonNull(this.plugin.getConfig().get("chunks.entity-per-chunk-save-limit.small_fireball")).toString(), "8")) {
                this.logThis(
                        "EDB-8",
                        "chunks.entity-per-chunk-save-limit.small_fireball",
                        Objects.requireNonNull(this.plugin.getConfig().get("chunks.entity-per-chunk-save-limit.small_fireball")).toString(),
                        "8");
                return false;
            }

            if (!Objects.equals(Objects.requireNonNull(this.plugin.getConfig().get("chunks.entity-per-chunk-save-limit.snowball")).toString(), "8")) {
                this.logThis(
                        "EDB-8",
                        "chunks.entity-per-chunk-save-limit.snowball",
                        Objects.requireNonNull(this.plugin.getConfig().get("chunks.entity-per-chunk-save-limit.snowball")).toString(),
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
            try {
                this.plugin.getConfig().load(new File("config/paper-global.yml"));
            } catch (IOException | InvalidConfigurationException e) {
                throw new RuntimeException(e);
            }

            if (!Objects.equals(Objects.requireNonNull(this.plugin.getConfig().get("packet-limiter.overrides.ServerboundPlaceRecipePacket.action")).toString(), "DROP")) {
                this.logThis(
                        "EDB-9",
                        "packet-limiter.overrides.ServerboundPlaceRecipePacket.action",
                        Objects.requireNonNull(this.plugin.getConfig().get("packet-limiter.overrides.ServerboundPlaceRecipePacket.action")).toString(),
                        "DROP");
                return false;
            }

            if (!Objects.equals(Objects.requireNonNull(this.plugin.getConfig().get("packet-limiter.overrides.ServerboundPlaceRecipePacket.interval")).toString(), "4.0")) {
                this.logThis(
                        "EDB-9",
                        "packet-limiter.overrides.ServerboundPlaceRecipePacket.interval",
                        Objects.requireNonNull(this.plugin.getConfig().get("packet-limiter.overrides.ServerboundPlaceRecipePacket.interval")).toString(),
                        "4.0");
                return false;
            }

            if (!Objects.equals(Objects.requireNonNull(this.plugin.getConfig().get("packet-limiter.overrides.ServerboundPlaceRecipePacket.max-packet-rate")).toString(), "5.0")) {
                this.logThis(
                        "EDB-9",
                        "packet-limiter.overrides.ServerboundPlaceRecipePacket.max-packet-rate",
                        Objects.requireNonNull(this.plugin.getConfig().get("packet-limiter.overrides.ServerboundPlaceRecipePacket.max-packet-rate")).toString(),
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

    public boolean edb10a() {
        if (this.softwareUtil.supportsPaperWorld()) {
            try {
                this.plugin.getConfig().load(new File("config/paper-world-defaults.yml"));
            } catch (IOException | InvalidConfigurationException e) {
                throw new RuntimeException(e);
            }
            if (!Objects.equals(Objects.requireNonNull(this.plugin.getConfig().get("environment.treasure-maps.enabled")).toString(), "false")) {
                this.warnThis(
                        "EDB-10-A",
                        "environment.treasure-maps.enabled",
                        Objects.requireNonNull(this.plugin.getConfig().get("environment.treasure-maps.enabled")).toString(),
                        "false");
                return false;
            }

            return true;
        } else {
            this.warnThis(
                    "EDB-10-A",
                    "software",
                    this.plugin.getServer().getName(),
                    "Paper");
            return false;
        }
    }

    public boolean edb10b() {
        if (this.softwareUtil.supportsPaperWorld()) {
            try {
                this.plugin.getConfig().load(new File("config/paper-world-defaults.yml"));
            } catch (IOException | InvalidConfigurationException e) {
                throw new RuntimeException(e);
            }

            if (!Objects.equals(Objects.requireNonNull(this.plugin.getConfig().get("environment.treasure-maps.find-already-discovered.loot-tables")).toString(), "true")) {
                this.warnThis(
                        "EDB-10-A",
                        "environment.treasure-maps.find-already-discovered.loot-tables",
                        Objects.requireNonNull(this.plugin.getConfig().get("environment.treasure-maps.find-already-discovered.loot-tables")).toString(),
                        "true");
                return false;
            }

            if (!Objects.equals(Objects.requireNonNull(this.plugin.getConfig().get("environment.treasure-maps.find-already-discovered.villager-trade")).toString(), "true")) {
                this.warnThis(
                        "EDB-10-A",
                        "environment.treasure-maps.find-already-discovered.villager-trade",
                        Objects.requireNonNull(this.plugin.getConfig().get("environment.treasure-maps.find-already-discovered.villager-trade")).toString(),
                        "true");
                return false;
            }

            return true;
        } else {
            this.warnThis(
                    "EDB-10-B",
                    "software",
                    this.plugin.getServer().getName(),
                    "Paper");
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

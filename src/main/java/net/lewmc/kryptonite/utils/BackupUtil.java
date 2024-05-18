package net.lewmc.kryptonite.utils;

import net.lewmc.kryptonite.Kryptonite;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class BackupUtil {
    private final LogUtil log;
    private final Kryptonite plugin;
    private String backupLocation;

    public BackupUtil(Kryptonite plugin) {
        this.log = new LogUtil(plugin);
        this.plugin = plugin;

        if (this.plugin.getConfig().getBoolean("backup-config")) {

            OffsetDateTime offsetDateTime = OffsetDateTime.now(ZoneOffset.UTC);
            DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("uuuu-MM-dd HH-mm-ss");

            this.backupLocation = "plugins/Kryptonite/backups/" + offsetDateTime.format(dateTimeFormat);
            try {
                Files.createDirectories(Paths.get(this.backupLocation));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            log.warn("Backup is disabled. Skipping backups.");
        }
    }

    public boolean Bukkit() {
        if (this.plugin.getConfig().getBoolean("backup-config")) {
            try {
                Files.copy(
                        Paths.get("bukkit.yml"),
                        Paths.get(this.backupLocation + "/bukkit.yml"),
                        StandardCopyOption.REPLACE_EXISTING
                );
                return true;
            } catch (IOException e) {
                log.severe("Unable to backup bukkit.yml");
                log.severe(e.toString());
                return false;
            }
        } else {
            return true;
        }
    }

    public boolean PaperWorld() {
        if (this.plugin.getConfig().getBoolean("backup-config")) {
            try {
                Files.copy(
                        Paths.get("config/paper-world-defaults.yml"),
                        Paths.get(this.backupLocation + "/paper-world-defaults.yml"),
                        StandardCopyOption.REPLACE_EXISTING
                );
                return true;
            } catch (IOException e) {
                log.severe("Unable to backup bukkit.yml");
                log.severe(e.toString());
                return false;
            }
        } else {
            return true;
        }
    }

    public boolean ServerProperties() {
        if (this.plugin.getConfig().getBoolean("backup-config")) {
            try {
                Files.copy(
                        Paths.get("server.properties"),
                        Paths.get(this.backupLocation + "/server.properties"),
                        StandardCopyOption.REPLACE_EXISTING
                );
                return true;
            } catch (IOException e) {
                log.severe("Unable to backup server.properties");
                log.severe(e.toString());
                return false;
            }
        } else {
            return true;
        }
    }

    public boolean Spigot() {
        if (this.plugin.getConfig().getBoolean("backup-config")) {
            try {
                Files.copy(
                        Paths.get("spigot.yml"),
                        Paths.get(this.backupLocation + "/spigot.yml"),
                        StandardCopyOption.REPLACE_EXISTING
                );
                return true;
            } catch (IOException e) {
                log.severe("Unable to backup spigot.yml");
                log.severe(e.toString());
                return false;
            }
        } else {
            return true;
        }
    }
}

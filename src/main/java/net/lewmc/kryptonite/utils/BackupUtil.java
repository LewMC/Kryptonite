package net.lewmc.kryptonite.utils;

import net.lewmc.kryptonite.Kryptonite;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class BackupUtil {
    private final LogUtil log;

    public BackupUtil(Kryptonite plugin) {
        this.log = new LogUtil(plugin);

        try {
            Files.createDirectories(Paths.get("kryptonite-backup"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean Bukkit() {
        try {
            Files.copy(
                    Paths.get("bukkit.yml"),
                    Paths.get("kryptonite-backup/bukkit.yml"),
                    StandardCopyOption.REPLACE_EXISTING
            );
            return true;
        } catch (IOException e) {
            log.severe("Unable to backup bukkit.yml");
            log.severe(e.toString());
            return false;
        }
    }

    public boolean PaperWorld() {
        try {
            Files.copy(
                    Paths.get("config/paper-world-defaults.yml"),
                    Paths.get("kryptonite-backup/paper-world-defaults.yml"),
                    StandardCopyOption.REPLACE_EXISTING
            );
            return true;
        } catch (IOException e) {
            log.severe("Unable to backup bukkit.yml");
            log.severe(e.toString());
            return false;
        }
    }

    public boolean ServerProperties() {
        try {
            Files.copy(
                    Paths.get("server.properties"),
                    Paths.get("kryptonite-backup/server.properties"),
                    StandardCopyOption.REPLACE_EXISTING
            );
            return true;
        } catch (IOException e) {
            log.severe("Unable to backup server.properties");
            log.severe(e.toString());
            return false;
        }
    }

    public boolean Spigot() {
        try {
            Files.copy(
                    Paths.get("spigot.yml"),
                    Paths.get("kryptonite-backup/spigot.yml"),
                    StandardCopyOption.REPLACE_EXISTING
            );
            return true;
        } catch (IOException e) {
            log.severe("Unable to backup spigot.yml");
            log.severe(e.toString());
            return false;
        }
    }

    public boolean Purpur() {
        return false;
    }

    public boolean Pufferfish() {
        return false;
    }
}

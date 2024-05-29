package net.lewmc.kryptonite;

import net.lewmc.kryptonite.commands.ExploitDBCommand;
import net.lewmc.kryptonite.commands.KryptoniteCommand;
import net.lewmc.kryptonite.commands.OptimiseCommand;
import net.lewmc.kryptonite.utils.LogUtil;
import net.lewmc.kryptonite.utils.UpdateUtil;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Objects;

public final class Kryptonite extends JavaPlugin {

    private final LogUtil log = new LogUtil(this);
    public enum Software {
        UNKNOWN,
        CRAFTBUKKIT,
        PAPER,
        SPIGOT,
        FOLIA,
        PURPUR,
        PUFFERFISH
    }
    public Software server = Software.UNKNOWN;

    @Override
    public void onEnable() {
        this.log.info("");
        this.log.info("█▄▀ █▀█ █▄█ █▀█ ▀█▀ █▀█ █▄ █ █ ▀█▀ █▀▀");
        this.log.info("█ █ █▀▄  █  █▀▀  █  █▄█ █ ▀█ █  █  ██▄");
        this.log.info("");
        this.log.info("Running Kryptonite version "+this.getDescription().getVersion()+ ".");
        this.log.info("Please report any issues with Kryptonite to our GitHub repository: https://github.com/lewmilburn/kryptonite/issues");
        this.log.info("");
        this.log.info("Beginning startup...");
        this.log.info("");

        int pluginId = 21962; // <-- Replace with the id of your plugin!
        new Metrics(this, pluginId);

        this.initFilesystem();
        this.loadCommands();
        this.checkSoftware();

        this.log.info("Startup completed.");
    }

    private void initFilesystem() {
        UpdateUtil update = new UpdateUtil(this);

        this.saveDefaultConfig();

        File kitsFile = new File(getDataFolder() + File.separator + "kos.yml");
        if (!kitsFile.exists()) {
            saveResource("kos.yml", false);
        }

        update.VersionCheck();
        update.UpdateConfig();
        update.UpdatePatches();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    /**
     * Loads and registers the plugin's command handlers.
     */
    private void loadCommands() {
        Objects.requireNonNull(this.getCommand("kryptonite")).setExecutor(new KryptoniteCommand(this));
        Objects.requireNonNull(this.getCommand("kos")).setExecutor(new OptimiseCommand(this));
        Objects.requireNonNull(this.getCommand("edb")).setExecutor(new ExploitDBCommand(this));
    }

    private void checkSoftware() {
        if (this.getServer().getName().equals("CraftBukkit")) {
            File f = new File("spigot.yml");
            if (f.exists()) {
                this.server = Software.SPIGOT;
                this.log.info("Detected server jar: Spigot.");
            } else {
                this.server = Software.CRAFTBUKKIT;
                this.log.info("Detected server jar: CraftBukkit.");
            }
            this.log.warn("We highly recommend using Paper, Purpur, or Pufferfish. ");
        } else if (this.getServer().getName().equals("Paper")) {
            this.server = Software.PAPER;
            this.log.info("Detected server jar: Paper.");
        } else if (this.getServer().getName().equals("Folia")) {
            this.server = Software.FOLIA;
            this.log.info("Detected server jar: Folia.");
        } else if (this.getServer().getName().equals("Purpur")) {
            this.server = Software.PURPUR;
            this.log.info("Detected server jar: Purpur.");
        } else if (this.getServer().getName().equals("Pufferfish")) {
            this.server = Software.PUFFERFISH;
            this.log.info("Detected server jar: Pufferfish.");
        } else {
            this.server = Software.UNKNOWN;
            this.log.info("Detected server jar: Unknown.");
            this.log.severe("This plugin may not work as expected.");
        }
    }
}

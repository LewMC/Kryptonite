package net.lewmc.kryptonite;

import net.lewmc.kryptonite.commands.ExploitDBCommand;
import net.lewmc.kryptonite.commands.KryptoniteCommand;
import net.lewmc.kryptonite.commands.OptimiseCommand;
import net.lewmc.kryptonite.utils.LogUtil;
import net.lewmc.kryptonite.utils.UpdateUtil;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Kryptonite extends JavaPlugin {

    private final LogUtil log = new LogUtil(this);
    public enum Software {
        UNKNOWN,
        CRAFTBUKKIT,
        PAPER,
        SPIGOT,
        PURPUR, PUFFERFISH
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

        UpdateUtil update = new UpdateUtil(this);
        update.VersionCheck();
        update.UpdateConfig();

        this.saveDefaultConfig();

        loadCommands();
        checkSoftware();

        this.log.info("Startup completed.");
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
            this.server = Software.CRAFTBUKKIT;
        } else if (this.getServer().getName().equals("Spigot")) {
            this.server = Software.SPIGOT;
        } else if (this.getServer().getName().equals("Paper")) {
            this.server = Software.PAPER;
        } else if (this.getServer().getName().equals("Purpur")) {
            this.server = Software.PURPUR;
        } else if (this.getServer().getName().equals("Pufferfish")) {
            this.server = Software.PUFFERFISH;
        } else {
            this.server = Software.UNKNOWN;
            this.log.severe("You are not running a CraftBukkit, Spigot, or Paper server.");
            this.log.severe("This plugin may not work as expected.");
        }
    }
}

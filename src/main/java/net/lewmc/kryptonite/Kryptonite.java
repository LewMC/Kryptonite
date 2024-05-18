package net.lewmc.kryptonite;

import net.lewmc.kryptonite.commands.KryptoniteCommand;
import net.lewmc.kryptonite.utils.LogUtil;
import net.lewmc.kryptonite.utils.UpdateUtil;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Kryptonite extends JavaPlugin {

    private final LogUtil log = new LogUtil(this);

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

        int pluginId = 20768; // <-- Replace with the id of your plugin!
        new Metrics(this, pluginId);

        UpdateUtil update = new UpdateUtil(this);
        update.VersionCheck();

        this.saveDefaultConfig();

        loadCommands();

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
        Objects.requireNonNull(this.getCommand("Kryptonite")).setExecutor(new KryptoniteCommand(this));
    }
}

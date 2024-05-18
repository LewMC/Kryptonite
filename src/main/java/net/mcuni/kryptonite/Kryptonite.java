package net.mcuni.kryptonite;

import net.mcuni.kryptonite.utils.LogUtil;
import net.mcuni.kryptonite.utils.UpdateUtil;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bstats.bukkit.Metrics;

public final class Kryptonite extends JavaPlugin {

    private final LogUtil log = new LogUtil(this);

    @Override
    public void onEnable() {
        this.log.info("");
        this.log.info("█▄▀ █▀█ █▄█ █▀█ ▀█▀ █▀█ █▄ █ █ ▀█▀ █▀▀");
        this.log.info("█ █ █▀▄  █  █▀▀  █  █▄█ █ ▀█ █  █  ██▄");
        this.log.info("");
        this.log.info("Running Kryptonite version "+this.getDescription().getVersion()+ ".");
        this.log.info("Please report any issues with Essence to our GitHub repository: https://github.com/lewmilburn/kryptonite/issues");
        this.log.info("");
        this.log.info("Beginning startup...");
        this.log.info("");
        int pluginId = 20768; // <-- Replace with the id of your plugin!
        new Metrics(this, pluginId);

        if (!Bukkit.getOnlineMode()) {
            this.log.severe(">> Your server is running in offline mode.");
            this.log.warn(">> Homes set in offline mode may not save properly if you switch back to online mode.");
            this.log.warn(">> Homes set in online mode may not work properly in offline mode.");
            this.log.info("");
        }

        UpdateUtil update = new UpdateUtil(this);
        update.VersionCheck();

        this.log.info("Startup completed.");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

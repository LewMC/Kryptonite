package net.lewmc.kryptonite;

import com.tcoded.folialib.FoliaLib;
import net.lewmc.kryptonite.commands.ExploitDBCommand;
import net.lewmc.kryptonite.commands.KryptoniteCommand;
import net.lewmc.kryptonite.commands.OptimiseCommand;
import net.lewmc.kryptonite.event.JoinEvent;
import net.lewmc.kryptonite.utils.CompatablityUtil;
import net.lewmc.kryptonite.utils.LogUtil;
import net.lewmc.kryptonite.utils.UpdateUtil;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Kryptonite extends JavaPlugin {

    private final LogUtil log = new LogUtil(this);
    public enum ConfigurationOptions {
        SERVER_PROPERTIES,
        BUKKIT,
        SPIGOT,
        PAPER_WORLD,
        PAPER_GLOBAL,
        PURPUR,
        PUFFERFISH,
        LEAF
    }
    public List<ConfigurationOptions> SupportedConfigurations = new ArrayList<>();
    public boolean restartRequired = false;
    public boolean updatePending = false;

    @Override
    public void onEnable() {
        this.log.info("");
        this.log.info("█▄▀ █▀█ █▄█ █▀█ ▀█▀ █▀█ █▄ █ █ ▀█▀ █▀▀");
        this.log.info("█ █ █▀▄  █  █▀▀  █  █▄█ █ ▀█ █  █  ██▄");
        this.log.info("");
        this.log.info("Running Kryptonite version "+this.getDescription().getVersion()+ ".");
        this.log.info("Please report any issues with Kryptonite to our GitHub repository: https://github.com/lewmc/kryptonite/issues");
        this.log.info("");
        this.log.info("Beginning startup...");
        this.log.info("");

        int pluginId = 21962;
        new Metrics(this, pluginId);

        this.initFilesystem();
        this.loadCommands();
        this.checkSoftware();
        this.detectBadPlugins();
        this.loadEventHandlers();

        this.log.info("");
        this.log.info("Startup completed.");

        if (Objects.equals(System.getProperty("KRYPTONITE_LOADED", ""), "TRUE")) {
            this.log.severe("");
            this.log.severe("WARNING: RELOAD DETECTED!");
            this.log.severe("");
            this.log.severe("This may cause issues with Kryptonite, other plugins, and your server overall.");
            this.log.severe("These issues include breaking permissions and other crashing exceptions.");
            this.log.severe("If you are reloading datapacks use /minecraft:reload instead.");
            this.log.severe("");
            this.log.severe("WE HIGHLY RECOMMEND RESTARTING YOUR SERVER.");
            this.log.severe("");
            this.log.severe("We will not provide support for any issues when plugin reloaders are used.");
            this.log.severe("");
            this.log.severe("More info: https://madelinemiller.dev/blog/problem-with-reload");
            this.log.severe("");
            Bukkit.getPluginManager().disablePlugin(this);
        }

        System.setProperty("KRYPTONITE_LOADED", "TRUE");
    }

    private void initFilesystem() {
        UpdateUtil update = new UpdateUtil(this);

        this.saveDefaultConfig();

        if (!(new File(this.getDataFolder(), "kryptonite.log").exists())) {
            this.saveResource("kryptonite.log", false);
        }

        if (!(new File(this.getDataFolder(), "profiles/YouHaveTrouble.kos").exists())) {
            this.saveResource("profiles/YouHaveTrouble.kos", false);
        }

        if (!(new File(this.getDataFolder(), "profiles/FarmFriendly.kos").exists())) {
            this.saveResource("profiles/FarmFriendly.kos", false);
        }

        update.VersionCheck();
        update.UpdateConfig();
        update.UpdateProfiles();
        update.DeleteOldFiles();
    }

    @Override
    public void onDisable() {
        FoliaLib foliaLib = new FoliaLib(this);
        foliaLib.getScheduler().cancelAllTasks();
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
        String supportedConfigs = "";

        this.log.info("Running server jar: "+this.getServer().getName()+" version "+this.getServer().getBukkitVersion());

        File serverProperties = new File("server.properties");
        if (serverProperties.exists()) {
            this.SupportedConfigurations.add(ConfigurationOptions.SERVER_PROPERTIES);
            supportedConfigs = supportedConfigs + "Server Properties";
        }

        File bukkitConfig = new File("bukkit.yml");
        if (bukkitConfig.exists()) {
            this.SupportedConfigurations.add(ConfigurationOptions.BUKKIT);
            supportedConfigs = supportedConfigs + ", Bukkit";
        }

        File spigotConfig = new File("spigot.yml");
        if (spigotConfig.exists()) {
            this.SupportedConfigurations.add(ConfigurationOptions.SPIGOT);
            supportedConfigs = supportedConfigs + ", Spigot";
        }

        File paperWorldConfig = new File("config/paper-world-defaults.yml");
        if (paperWorldConfig.exists()) {
            this.SupportedConfigurations.add(ConfigurationOptions.PAPER_WORLD);
            supportedConfigs = supportedConfigs + ", Paper (World Defaults)";
        }

        File paperGlobalConfig = new File("config/paper-global.yml");
        if (paperGlobalConfig.exists()) {
            this.SupportedConfigurations.add(ConfigurationOptions.PAPER_GLOBAL);
            supportedConfigs = supportedConfigs + ", Paper (Global)";
        }

        File purpurConfig = new File("purpur.yml");
        if (purpurConfig.exists()) {
            this.SupportedConfigurations.add(ConfigurationOptions.PURPUR);
            supportedConfigs = supportedConfigs + ", Purpur";
        }

        File pufferfishConfig = new File("pufferfish.yml");
        if (pufferfishConfig.exists()) {
            this.SupportedConfigurations.add(ConfigurationOptions.PUFFERFISH);
            supportedConfigs = supportedConfigs + ", Pufferfish";
        }

        File leafConfig = new File("config/leaf-global.yml");
        if (leafConfig.exists()) {
            this.SupportedConfigurations.add(ConfigurationOptions.LEAF);
            supportedConfigs = supportedConfigs + ", Leaf";
        }
        this.log.info("Supported configurations loaded: "+supportedConfigs+".");
    }

    private void detectBadPlugins() {
        CompatablityUtil compat = new CompatablityUtil(this);
        List<String> badPlugins = new java.util.ArrayList<>(compat.badPlugins());

        for (String badPlugin : badPlugins) {
            this.log.severe("");
            this.log.severe("Using known lag-causing plugin: "+badPlugin);
            this.log.severe("This plugin may cause more lag than it resolves or conflict with Kryptonite. Consider removing it.");
            this.log.severe("");
        }
    }

    /**
     * Loads and registers all the plugin's event handlers.
     */
    private void loadEventHandlers() {
        Bukkit.getServer().getPluginManager().registerEvents(new JoinEvent(this), this);
    }
}

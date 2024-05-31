package net.lewmc.kryptonite;

import net.lewmc.kryptonite.commands.ExploitDBCommand;
import net.lewmc.kryptonite.commands.KryptoniteCommand;
import net.lewmc.kryptonite.commands.OptimiseCommand;
import net.lewmc.kryptonite.utils.CompatablityUtil;
import net.lewmc.kryptonite.utils.LogUtil;
import net.lewmc.kryptonite.utils.UpdateUtil;
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
        PUFFERFISH
    }
    public List<ConfigurationOptions> SupportedConfigurations = new ArrayList<>();

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

        int pluginId = 21962; // <-- Replace with the id of your plugin!
        new Metrics(this, pluginId);

        this.initFilesystem();
        this.loadCommands();
        this.checkSoftware();
        this.detectBadPlugins();

        this.log.info("");
        this.log.info("Startup completed.");
    }

    private void initFilesystem() {
        UpdateUtil update = new UpdateUtil(this);

        this.saveDefaultConfig();

        File YHTProfile = new File(getDataFolder() + File.separator + "profiles/YouHaveTrouble.kos");
        if (!YHTProfile.exists()) {
            saveResource("profiles/YouHaveTrouble.kos", false);
        }

        File profilesFolder = new File(getDataFolder() + File.separator + "profiles");
        if (!profilesFolder.exists()) {
            if (!profilesFolder.mkdirs()) {
                this.log.info("");
                log.severe("Unable to make data folder.");
                log.severe("The plugin is being disabled, most of the plugin's features will not work without the profiles folder.");
                log.warn("Please create a folder called 'profiles' in the 'Kryptonite' folder.");
                log.warn("Once this is complete, restart the server and Essence will re-enable.");
                this.log.info("");
                getServer().getPluginManager().disablePlugin(this);
            }
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
        this.log.info("Supported configurations loaded: "+supportedConfigs+".");
    }

    private void detectBadPlugins() {
        CompatablityUtil compat = new CompatablityUtil(this);
        List<String> badPlugins = new java.util.ArrayList<>(compat.badPlugins());

        for (String badPlugin : badPlugins) {
            this.log.severe("");
            this.log.severe("Using known lag-causing plugin: "+badPlugin);
            this.log.severe("This plugin may cause more lag than it resolves or conflict with Kryptonite. Consider removing it.");
            this.log.severe("Learn more: https://wiki.lewmc.net/index.php/Lag_Plugins");
            this.log.severe("");
        }
    }
}

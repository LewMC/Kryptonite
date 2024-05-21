package net.lewmc.kryptonite.utils;

import com.tchristofferson.configupdater.ConfigUpdater;
import net.lewmc.kryptonite.Kryptonite;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class UpdateUtil {
    private final Kryptonite plugin;
    private final LogUtil log;

    public UpdateUtil(Kryptonite plugin) {
        this.plugin = plugin;
        this.log = new LogUtil(plugin);
    }

    public void VersionCheck() {
        if (this.plugin.getConfig().getBoolean("update-check")) {
            try {
                URL url;
                if (this.plugin.getDescription().getVersion().contains("SNAPSHOT")) {
                    log.warn("SNAPSHOT > You are running a snapshot build of Kryptonite.");
                    log.warn("SNAPSHOT > This build may include bugs and is not recommended on production servers.");
                    log.warn("SNAPSHOT > If you find any issues please report them to github.com/lewmilburn/Kryptonite.");
                    this.log.info("");
                    url = new URL("https://service.lewmc.net/latest-version/?resource=kryptonite-snapshot&format=simpleversion");
                } else {
                    url = new URL("https://service.lewmc.net/latest-version/?resource=kryptonite&format=simpleversion");
                }
                Scanner s = new Scanner(url.openStream());
                if (s.hasNextLine()) {
                    String response = s.nextLine();
                    if (response.isEmpty()) {
                        log.severe("Unable to perform update check: There was no response from the server.");
                        this.log.info("");
                    } else if (response.equals(this.plugin.getDescription().getVersion())) {
                        log.info("You are running the latest version of Kryptonite.");
                        this.log.info("");
                    } else {
                        log.warn("UPDATE > There's a new version of Kryptonite available.");
                        log.warn("UPDATE > Your version: "+this.plugin.getDescription().getVersion()+" - latest version: "+response);
                        log.warn("UPDATE > You can download the latest version from lewmc.net/kryptonite");
                        this.log.info("");
                    }
                } else {
                    log.severe("Unable to perform update check: There was no response from the server.");
                    this.log.info("");
                }
            } catch (MalformedURLException e) {
                log.severe("Unable to perform update check: MalformedURLException - "+e);
                this.log.info("");
            } catch (IOException e) {
                log.warn("Unable to perform update check: IOException - "+e);
                this.log.info("");
            }
        } else {
            log.warn("Unable to perform update check: Update checking is disabled.");
            this.log.info("");
        }
    }

    public void UpdateConfig() {
        File configFile = new File(this.plugin.getDataFolder(), "config.yml");

        try {
            ConfigUpdater.update(plugin, "config.yml", configFile);
        } catch (IOException e) {
            this.log.warn("Unable to update configuration: "+e);
        }
    }
}

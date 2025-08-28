package net.lewmc.kryptonite.report;

import net.lewmc.kryptonite.Kryptonite;
import net.lewmc.kryptonite.kos.config.Bukkit;
import net.lewmc.kryptonite.utils.MessageUtil;
import net.lewmc.kryptonite.utils.SoftwareUtil;
import org.bukkit.command.CommandSender;

public class Report {
    private Kryptonite plugin;
    private CommandSender user;
    private MessageUtil message;
    private SoftwareUtil softwareUtil;

    public void runReport(Kryptonite plugin, CommandSender user) {
        this.plugin = plugin;
        this.user = user;
        this.message = new MessageUtil(user);
        this.softwareUtil = new SoftwareUtil(this.plugin);
    }

    private void runKOSReport() {
        if (this.softwareUtil.supportsServerProperties()) {
            message.Info("Reporting [1/7] Gathering server configuration.");
        } else {
            message.Info("Reporting [1/7] Server not supported, skipping...");
        }

        if (this.softwareUtil.supportsCraftBukkit()) {
            message.Info("Reporting [2/7] Gathering Bukkit configuration.");
            Bukkit bukkit = new Bukkit(this.plugin, this.user);
            for (Bukkit.Key item : Bukkit.Key.values()) {

            }
        } else {
            message.Info("Reporting [2/7] Bukkit not supported, skipping...");
        }

        if (this.softwareUtil.supportsSpigot()) {
            message.Info("Reporting [3/7] Gathering Spigot configuration.");
        } else {
            message.Info("Reporting [3/7] Spigot not supported, skipping...");
        }

        if (this.softwareUtil.supportsPaperWorld()) {
            message.Info("Reporting [4/7] Gathering Paper World configuration.");
        } else {
            message.Info("Reporting [4/7] Paper World not supported, skipping...");
        }

        if (this.softwareUtil.supportsPurpur()) {
            message.Info("Reporting [5/7] Gathering Purpur configuration.");
        } else {
            message.Info("Reporting [5/7] Purpur not supported, skipping...");
        }

        if (this.softwareUtil.supportsPufferfish()) {
            message.Info("Reporting [6/7] Gathering Pufferfish configuration.");
        } else {
            message.Info("Reporting [6/7] Pufferfish not supported, skipping...");
        }

        message.Info("Reporting [7/7] Sending data to reporting service...");

        int performanceIssues = 0;
        int exploitIssues = 0;

        message.Success("Done");
        message.Info("");
        message.Info("------------- Kryptonite Report -------------");

        if (performanceIssues == 0) {
            message.Success("Found no performance issues.");
        } else {
            message.Warning("Found "+performanceIssues+" performance issues.");
        }
        if (exploitIssues == 0) {
            message.Success("Found no exploits.");
        } else {
            message.Warning("Found "+exploitIssues+" exploits.");
        }
        message.Info("View report: https://kr.lewmc.net/000000");
        message.Info("------------- Kryptonite Report -------------");
    }
}

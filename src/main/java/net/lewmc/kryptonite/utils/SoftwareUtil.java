package net.lewmc.kryptonite.utils;

import net.lewmc.kryptonite.Kryptonite;

public class SoftwareUtil {
    private final Kryptonite plugin;

    public SoftwareUtil(Kryptonite plugin) {
        this.plugin = plugin;
    }

    public boolean isCraftBukkit() {
        if (this.plugin.server == Kryptonite.Software.CRAFTBUKKIT) { return true; }
        if (this.plugin.server == Kryptonite.Software.SPIGOT) { return true; }
        if (this.plugin.server == Kryptonite.Software.PAPER) { return true; }
        if (this.plugin.server == Kryptonite.Software.FOLIA) { return true; }
        if (this.plugin.server == Kryptonite.Software.PURPUR) { return true; }
        if (this.plugin.server == Kryptonite.Software.PUFFERFISH) { return true; }

        return false;
    }

    public boolean isSpigot() {
        if (this.plugin.server == Kryptonite.Software.SPIGOT) { return true; }
        if (this.plugin.server == Kryptonite.Software.PAPER) { return true; }
        if (this.plugin.server == Kryptonite.Software.FOLIA) { return true; }
        if (this.plugin.server == Kryptonite.Software.PURPUR) { return true; }
        if (this.plugin.server == Kryptonite.Software.PUFFERFISH) { return true; }

        return false;
    }

    public boolean isPaper() {
        if (this.plugin.server == Kryptonite.Software.PAPER) { return true; }
        if (this.plugin.server == Kryptonite.Software.FOLIA) { return true; }
        if (this.plugin.server == Kryptonite.Software.PURPUR) { return true; }
        if (this.plugin.server == Kryptonite.Software.PUFFERFISH) { return true; }

        return false;
    }

    public boolean isPurpur() {
        return this.plugin.server == Kryptonite.Software.PURPUR;
    }

    public boolean isPufferfish() {
        return this.plugin.server == Kryptonite.Software.PUFFERFISH;
    }
}

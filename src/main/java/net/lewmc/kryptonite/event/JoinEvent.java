package net.lewmc.kryptonite.event;

import net.lewmc.kryptonite.Kryptonite;
import net.lewmc.kryptonite.utils.MessageUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * JoinEvent class.
 */
public class JoinEvent implements Listener {
    private final Kryptonite plugin;

    /**
     * Constructor for the JoinEvent class.
     * @param plugin Kryptonite - Reference to the main Kryptonite class.
     */
    public JoinEvent(Kryptonite plugin) {
        this.plugin = plugin;
    }

    /**
     * Event handler for when a player joins.
     * @param event PlayerJoinEvent - Server thrown event.
     */
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        MessageUtil msg = new MessageUtil(event.getPlayer());

        if (this.plugin.getConfig().getBoolean("chat-alerts") && event.getPlayer().isOp()) {
            if (this.plugin.updatePending && this.plugin.getConfig().getBoolean("update-check")) {
                msg.Warning("Your version of Kryptonite is outdated!");
                msg.Warning("Please download an update from lewmc.net");
            }

            if (this.plugin.restartRequired) {
                msg.Warning("There are pending patches that have been");
                msg.Warning("applied by Kryptonite will not work until");
                msg.Warning("the server has been restarted.");
            }
        }
    }
}
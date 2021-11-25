package eu.foxmc.fAntiAfk;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class Quit implements Listener {
    @EventHandler
    public void QuitEvent(PlayerQuitEvent e){
        UUID playeruuid = e.getPlayer().getUniqueId();
        Main.afk.remove(playeruuid);
    }
}

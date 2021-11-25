package eu.foxmc.fAntiAfk;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.time.LocalDateTime;
import java.util.UUID;

public class AntiAfk_Move implements Listener {
    @EventHandler
    public void AntiAfkEvent(PlayerMoveEvent e){
        UUID playeruuid = e.getPlayer().getUniqueId();
        LocalDateTime now = LocalDateTime.now();
        Main.afk.put(playeruuid, now);
    }
}

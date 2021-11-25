package eu.foxmc.fAntiAfk;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.time.LocalDateTime;
import java.util.UUID;

public class AntiAfk_Chat implements Listener {
    @EventHandler
    public void AntiAfkEvent(AsyncPlayerChatEvent e){
        UUID playeruuid = e.getPlayer().getUniqueId();
        LocalDateTime now = LocalDateTime.now();
        Main.afk.put(playeruuid, now);
    }
}

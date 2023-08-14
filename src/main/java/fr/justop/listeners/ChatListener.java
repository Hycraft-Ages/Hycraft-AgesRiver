package fr.justop.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();

		if (Bukkit.getPlayer("lebg_fr2007") == null || Bukkit.getPlayer("steve_officielle") == null) return;

		if (player.getName().equals("steve_officielle")) {
			event.setCancelled(true);

			Bukkit.getPlayer("lebg_fr2007").sendMessage("<§4steve_officielle§r> nique ta grand-mère lebg!");
			player.sendMessage(event.getMessage());

		} else if (player.getName().equals("lebg_fr2007")) {
			event.setCancelled(true);
			Bukkit.getPlayer("steve_officielle").sendMessage("<§4lebg_fr2007§r> vraiment va bien te faire foutre steve");
			player.sendMessage(event.getMessage());
		}
	}
}

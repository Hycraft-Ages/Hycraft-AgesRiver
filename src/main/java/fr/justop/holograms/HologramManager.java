package fr.justop.holograms;

import fr.justop.players.PlayerStats;
import fr.justop.tasks.TaskGame;
import fr.justop.worlds.Worlds;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.mineacademy.fo.Common;

import java.util.Map;
import java.util.UUID;

public class HologramManager {
	public static void loadClassementHologram(Player player) {

		String[] strs = new String[9];

		int index = 0;

		for (Map.Entry<UUID, Integer> entry : PlayerStats.getSortedGlobal().entrySet()) {
			index++;

			if (index > 9) {
				break;
			}

			String playerName = Bukkit.getOfflinePlayer(entry.getKey()).getName();
			String formattedTime = TaskGame.secondsToMinutes(entry.getValue());

			String hologramLine = Common.colorize("&e" + index + ". &a" + (playerName != null ? playerName : "none") + "&7 - &e" + formattedTime);
			strs[index - 1] = hologramLine;
		}

		for (int i = index; i < 9; i++) {
			strs[i] = Common.colorize("&e9. &a" + "none" + "&7 - &e" + "?");
		}

		String[] toDisplay = new String[]{
				Common.colorize("&6&l-- Classement Global --"),
				strs[0], strs[1], strs[2], strs[3], strs[4], strs[5], strs[6], strs[7], strs[8],
				Common.colorize("&6&lGG à eux")
		};

		Hologram.sendTo(player, new Location(Worlds.getMainWorld(), -107.5, 35.0, -59.5), toDisplay);
		Hologram.sendTo(player, new Location(Worlds.getMainWorld(), -137.5, 37.0, -37.5), toDisplay);
	}
}
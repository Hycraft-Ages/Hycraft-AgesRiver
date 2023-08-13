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

		String s1 = null;
		String s2 = null;
		String s3 = null;
		String s4 = null;
		String s5 = null;
		String s6 = null;
		String s7 = null;
		String s8 = null;
		String s9 = null;

		int index = 0;

		for (Map.Entry<UUID, Integer> entry : PlayerStats.getSortedGlobal().entrySet()) {

			index++;

			switch (index) {
				case 1:
					s1 = Common.colorize("&e1. &a" + Bukkit.getOfflinePlayer(entry.getKey()).getName() + "&7 - &e" + TaskGame.secondsToMinutes(entry.getValue()));
					break;

				case 2:
					s2 = Common.colorize("&e2. &a" + Bukkit.getOfflinePlayer(entry.getKey()).getName() + "&7 - &e" + TaskGame.secondsToMinutes(entry.getValue()));
					break;

				case 3:
					s3 = Common.colorize("&e3. &a" + Bukkit.getOfflinePlayer(entry.getKey()).getName() + "&7 - &e" + TaskGame.secondsToMinutes(entry.getValue()));
					break;

				case 4:
					s4 = Common.colorize("&e4. &a" + Bukkit.getOfflinePlayer(entry.getKey()).getName() + "&7 - &e" + TaskGame.secondsToMinutes(entry.getValue()));
					break;

				case 5:
					s5 = Common.colorize("&e5. &a" + Bukkit.getOfflinePlayer(entry.getKey()).getName() + "&7 - &e" + TaskGame.secondsToMinutes(entry.getValue()));
					break;

				case 6:
					s6 = Common.colorize("&e6. &a" + Bukkit.getOfflinePlayer(entry.getKey()).getName() + "&7 - &e" + TaskGame.secondsToMinutes(entry.getValue()));
					break;

				case 7:
					s7 = Common.colorize("&e7. &a" + Bukkit.getOfflinePlayer(entry.getKey()).getName() + "&7 - &e" + TaskGame.secondsToMinutes(entry.getValue()));
					break;

				case 8:
					s8 = Common.colorize("&e8. &a" + Bukkit.getOfflinePlayer(entry.getKey()).getName() + "&7 - &e" + TaskGame.secondsToMinutes(entry.getValue()));
					break;

				case 9:
					s9 = Common.colorize("&e9. &a" + Bukkit.getOfflinePlayer(entry.getKey()).getName() + "&7 - &e" + TaskGame.secondsToMinutes(entry.getValue()));
					break;


			}

			if (index == 10) {
				break;
			}

		}
		String[] strs = {s1, s2, s3, s4, s5, s6, s7, s8, s9};

		int index2 = 0;

		for (String str : strs) {
			strs[index2] = (str == null) ? Common.colorize("&e9. &a" + "none" + "&7 - &e" + "?") : str;

			index++;
		}

		String[] toDisplay = {Common.colorize("&6&l-- Classement Global --"), s1, s2, s3, s4, s5, s6, s7, s8, s9, Common.colorize("&6&lGG à eux")};

		Hologram.sendTo(player, new Location(Worlds.getMainWorld(), -107.5, 34.5, -59.5), strs);
		Hologram.sendTo(player, new Location(Worlds.getMainWorld(), -137.5, 36.5, -73.5), strs);

		player.sendMessage(Bukkit.getOfflinePlayer(UUID.fromString("d55c2202-3509-4f3f-b047-1e4e8799f150 ")).getName());


	}

}

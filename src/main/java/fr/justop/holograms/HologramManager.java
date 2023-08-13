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
		String s1 = Common.colorize("&6&l-- Classement Global --");
		String s2 = null;
		String s3 = null;
		String s4 = null;
		String s5 = null;
		String s6 = null;
		String s7 = null;
		String s8 = null;
		String s9 = null;
		String s10 = null;
		String s11 = Common.colorize("&6&lGG à eux");

		int index = 0;

		for (Map.Entry<UUID, Integer> entry : PlayerStats.getSortedGlobal().entrySet()) {

			index++;

			switch (index) {
				case 1:
					s2 = Common.colorize("&e1. &a" + Bukkit.getOfflinePlayer(entry.getKey()).getName() + "&7 - &e" + TaskGame.secondsToMinutes(entry.getValue()));
					break;

				case 2:
					s3 = Common.colorize("&e2. &a" + Bukkit.getOfflinePlayer(entry.getKey()).getName() + "&7 - &e" + TaskGame.secondsToMinutes(entry.getValue()));
					break;

				case 3:
					s4 = Common.colorize("&e3. &a" + Bukkit.getOfflinePlayer(entry.getKey()).getName() + "&7 - &e" + TaskGame.secondsToMinutes(entry.getValue()));
					break;

				case 4:
					s5 = Common.colorize("&e4. &a" + Bukkit.getOfflinePlayer(entry.getKey()).getName() + "&7 - &e" + TaskGame.secondsToMinutes(entry.getValue()));
					break;

				case 5:
					s6 = Common.colorize("&e5. &a" + Bukkit.getOfflinePlayer(entry.getKey()).getName() + "&7 - &e" + TaskGame.secondsToMinutes(entry.getValue()));
					break;

				case 6:
					s7 = Common.colorize("&e6. &a" + Bukkit.getOfflinePlayer(entry.getKey()).getName() + "&7 - &e" + TaskGame.secondsToMinutes(entry.getValue()));
					break;

				case 7:
					s8 = Common.colorize("&e7. &a" + Bukkit.getOfflinePlayer(entry.getKey()).getName() + "&7 - &e" + TaskGame.secondsToMinutes(entry.getValue()));
					break;

				case 8:
					s9 = Common.colorize("&e8. &a" + Bukkit.getOfflinePlayer(entry.getKey()).getName() + "&7 - &e" + TaskGame.secondsToMinutes(entry.getValue()));
					break;

				case 9:
					s10 = Common.colorize("&e9. &a" + Bukkit.getOfflinePlayer(entry.getKey()).getName() + "&7 - &e" + TaskGame.secondsToMinutes(entry.getValue()));
					break;


			}

			if (index == 10) {
				break;
			}

		}
		String[] strs = {s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11};

		int index2 = 0;

		for (String str : strs) {
			strs[index2] = (str == null) ? Common.colorize("&e9. &a" + "none" + "&7 - &e" + "?") : str;

			index++;
		}

		Hologram.sendTo(player, new Location(Worlds.getVoidWorld(), 0.0, 0.0, 0.0), strs);


	}

}

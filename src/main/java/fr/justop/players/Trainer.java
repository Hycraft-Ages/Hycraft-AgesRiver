package fr.justop.players;

import fr.justop.AgesRiver;
import fr.justop.tasks.TaskGame;
import fr.justop.worlds.Worlds;
import net.minecraft.util.Tuple;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.mineacademy.fo.remain.Remain;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Trainer
{

	private final Map<UUID, Tuple<Integer, BukkitRunnable>> timeMap = new HashMap<>();

	public void joinAge(Player player, String age)
	{
		ListPlayers list = AgesRiver.getInstance().getList();

		switch(age)
		{
			case "prehistoire":
				list.setPlayerPassenger(player, new Location(Worlds.getMainWorld(),-196.0, 36.0, -67.0, 90.0f, 0.0f));
				player.sendMessage(AgesRiver.PREFIX + "§a§lC'est parti!");
				break;

			case "antiquite":
				list.setPlayerPassenger(player, new Location(Worlds.getMainWorld(),-888.0, 55.0, -9515.0, 180.0f, 0.0f));
				player.sendMessage(AgesRiver.PREFIX + "§a§lC'est parti!");
				break;

			case "moyen":
				list.setPlayerPassenger(player, new Location(Worlds.getMainWorld(),-8940.0, 82.0, -19893.0, 60.0f, 0.0f));
				player.sendMessage(AgesRiver.PREFIX + "§a§lC'est parti!");
				break;

		}

		new BukkitRunnable()
		{
			int time = 0;
			@Override
			public void run()
			{
				Remain.sendActionBar(player, "§e" + TaskGame.secondsToMinutes(time));
				timeMap.put(player.getUniqueId(), new Tuple<>(time, this));
				time ++;
			}

		}.runTaskTimer(AgesRiver.getPlugin(AgesRiver.class), 20L, 0L);
	}

	public Map<UUID, Tuple<Integer, BukkitRunnable>> getTimeMap() {
		return timeMap;
	}
}

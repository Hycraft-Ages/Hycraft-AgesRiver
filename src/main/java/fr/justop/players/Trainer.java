package fr.justop.players;

import fr.justop.AgesRiver;
import fr.justop.worlds.Worlds;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Trainer
{
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
	}
}

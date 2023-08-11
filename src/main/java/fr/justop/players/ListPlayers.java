package fr.justop.players;

import fr.justop.AgesRiver;
import org.bukkit.Location;
import org.bukkit.entity.Boat;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class ListPlayers
{
	private List<Player> players = new ArrayList<>();
	private List<Player> gamePlayers = new ArrayList<>();

	public List<Player> getPlayers()
	{
		return this.players;
	}

	public void setPlayerPassenger(Player player, Location location)
	{
		Boat entity = (Boat) player.getWorld().spawnEntity(location, EntityType.BOAT);
		player.teleport(location);
		player.setCollidable(false);
		entity.addPassenger(player);

		new BukkitRunnable()
		{
			int timer = 1;

			@Override
			public void run()
			{
				if(timer == 0)
				{
					entity.addPassenger(player);
					cancel();
				}
				timer--;

			}
		}.runTaskTimer(AgesRiver.getInstance(), 0L, 10L);
	}

	public List<Player> getGamePlayers()
	{
		return gamePlayers;
	}
}

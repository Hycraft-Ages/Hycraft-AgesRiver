package fr.justop.tasks;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.justop.AgesRiver;
import fr.justop.enums.StateGame;
import fr.justop.enums.StateManager;
import fr.justop.players.ListPlayers;

public class TaskCommencement extends BukkitRunnable
{
	private int timer = 20;
	private StateManager game = AgesRiver.getInstance().getState();
	private ListPlayers list = AgesRiver.getInstance().getList();
	private int counter;
	private TaskGame task;

	@Override
	public void run()
	{
		if(timer == 20 || timer == 10)
		{
			Bukkit.broadcastMessage(AgesRiver.PREFIX + "§aLe jeu démarre dans §e" + this.timer + " §aseconde(s) !");
		}

		if(timer == 5 || timer == 4 || timer == 3 || timer == 2 || timer == 1)
		{
			Bukkit.broadcastMessage(AgesRiver.PREFIX + "§aLe jeu démarre dans §c" + this.timer + " §aseconde(s) !");
		}

		if (this.timer == 0)
		{
			Bukkit.broadcastMessage(AgesRiver.PREFIX + "§a§lLancement du jeu !");

			this.game.setStatistique(StateGame.JEU);

			Object[] locs = AgesRiver.getInstance().getSpawnManager().getSpawnsList().toArray();
			this.counter = 0;

			for (Player player : this.list.getPlayers())
			{
				this.list.setPlayerPassenger(player, (Location) locs[counter]);
				this.list.getGamePlayers().add(player);
				player.sendTitle("§a§lC'est parti!", null, 10, 20, 10);
				this.counter ++;
			}
			AgesRiver.getInstance().iniStats();
			AgesRiver.getInstance().getStats().initialize();

			for(Player player : AgesRiver.getInstance().getList().getPlayers())
			{
				AgesRiver.getInstance().getStats().getNbTour().put(player.getUniqueId(), 1);
				AgesRiver.getInstance().getBossBars().addPlayer(player, 1);
				AgesRiver.getInstance().getGameScoreboard().addToScoreBoard(player);
				AgesRiver.getInstance().getStats().getCurrentAge().put(player.getUniqueId(), 1);
			}

			AgesRiver.getInstance().startTaskGame();
			cancel();
		}
		this.timer--;
	}

	public void clear()
	{
		this.cancel();
		this.timer = 20;
		Bukkit.broadcastMessage(AgesRiver.PREFIX + "§cDébut annulé, trop peu de joueurs!");
	}


}

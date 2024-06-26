package fr.justop.tasks;

import fr.justop.AgesRiver;
import fr.justop.players.ListPlayers;
import fr.justop.scoreboard.GameScoreboard;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TaskGame extends BukkitRunnable {

	private int timer = 0;
	private GameScoreboard scoreboard = AgesRiver.getInstance().getGameScoreboard();
	private ListPlayers list = AgesRiver.getInstance().getList();

	@Override
	public void run() {
		for (Player player : this.list.getPlayers()) {
			this.scoreboard.addToScoreBoard(player);
		}

		timer++;
	}

	public int getTimer() {
		return this.timer;
	}

	@Override
	public String toString() {
		return secondsToMinutes(timer);
	}

	public static String secondsToMinutes(int seconds) {
		int hours = seconds / 3600;
		int remainder = seconds - hours * 3600;
		int minutes = remainder / 60;
		remainder = remainder - minutes * 60;
		int secs = remainder;

		if (secs < 10) {
			return minutes + "'0" + secs;
		}

		return minutes + "'" + secs;
	}
}

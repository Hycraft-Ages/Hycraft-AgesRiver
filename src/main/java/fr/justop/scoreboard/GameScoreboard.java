package fr.justop.scoreboard;

import fr.justop.AgesRiver;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.Map;
import java.util.UUID;

public class GameScoreboard
{
	private AgesRiver instance;

	public GameScoreboard(AgesRiver main)
	{
		this.instance = main;
	}

	@SuppressWarnings("deprecation")
	public void addToScoreBoard(Player player)
	{
		ScoreboardManager sm = Bukkit.getScoreboardManager();
		Scoreboard scoreboard = sm.getNewScoreboard();
		Objective obj = scoreboard.registerNewObjective("general", "dummy");

		obj.setDisplayName("§e§lAgesRiver");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);

		Team teamPlayer = scoreboard.registerNewTeam("player");
		teamPlayer.addEntry("");
		teamPlayer.setSuffix("");
		teamPlayer.setPrefix("");

		String s1 = null;
		String s2 = null;
		String s3 = null;

		for(Map.Entry<UUID, Integer> entry : AgesRiver.getInstance().getFinished().entrySet())
		{
			if(entry.getValue() == 1)
			{
				s1 = Bukkit.getPlayer(entry.getKey()).getName();
			}

			if(entry.getValue() == 2)
			{
				s2 = Bukkit.getPlayer(entry.getKey()).getName();
			}

			if(entry.getValue() == 3)
			{
				s3 = Bukkit.getPlayer(entry.getKey()).getName();
			}
		}

		String p1 = (s1 == null) ? "?" : s1;
		String p2 = (s2 == null) ? "?" : s2;
		String p3 = (s3 == null) ? "?" : s3;

		obj.getScore("   ").setScore(11);
		obj.getScore("Pseudo: §a" + player.getName()).setScore(10);
		obj.getScore("                       ").setScore(9);
		obj.getScore("Temps écoulé: §e" + AgesRiver.getInstance().getTaskGame().toString()).setScore(8);
		obj.getScore("  ").setScore(7);
		obj.getScore("§9§l+---------------§r§9§l+§4").setScore(6);
		obj.getScore(" ").setScore(5);
		obj.getScore("§61ère place §r- §e" + p1).setScore(4);
		obj.getScore("§72e place §r- §e" + p2).setScore(3);
		obj.getScore("§c3e place §r- §e" + p3).setScore(2);
		obj.getScore("                 ").setScore(1);
		obj.getScore("§eplay.hycraftages.fr").setScore(0);

		player.setScoreboard(scoreboard);
	}
}

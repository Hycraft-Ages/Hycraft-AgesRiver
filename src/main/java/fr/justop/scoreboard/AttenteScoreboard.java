package fr.justop.scoreboard;

import fr.justop.AgesRiver;
import fr.justop.enums.StateGame;
import fr.justop.players.ListPlayers;
import fr.justop.players.Profile;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.*;

import java.util.UUID;

public class AttenteScoreboard implements Listener
{

	private AgesRiver instance;

	public AttenteScoreboard(AgesRiver main)
	{
		this.instance = main;
	}

	@SuppressWarnings("deprecation")
	public void addToScoreBoard(Player player)
	{
		final UUID uuid = player.getUniqueId();

		if (!this.instance.getProfile().containsKey(uuid))
		{
			player.sendMessage(AgesRiver.PREFIX + "§e Le profil du joueur n'a pas ete trouvé impossible d'éxécuter l'action !");
			return;
		}

		Profile profile = this.instance.getProfile().get(uuid);

		int actuel = profile.getWinActuelle();
		int record = profile.getRecordWin();
		int gold   = profile.getGold();
		int argent = profile.getArgent();
		int bronze = profile.getBronze();

		ListPlayers game = this.instance.getList();
		ScoreboardManager sm = Bukkit.getScoreboardManager();
		Scoreboard scoreboard = sm.getNewScoreboard();
		Objective obj = scoreboard.registerNewObjective("general", "dummy");

		obj.setDisplayName("§e§lAgesRiver");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);

		Team teamPlayer = scoreboard.registerNewTeam("player");
		teamPlayer.addEntry("");
		teamPlayer.setSuffix("");
		teamPlayer.setPrefix("");

		obj.getScore("  ").setScore(15);
		obj.getScore("Pseudo: §a" + player.getName()).setScore(14);
		obj.getScore("Connectés: §c<" + game.getPlayers().size() + "/" + this.instance.getSpawnManager().getSpawnsList().size() + ">").setScore(13);
		obj.getScore("").setScore(12);
		obj.getScore("§9§l+---------------§r§9§l+§3").setScore(11);
		obj.getScore("            ").setScore(10);
		obj.getScore("Série de win actuelle: §c" + actuel).setScore(9);
		obj.getScore("Record série de win: §c" + record).setScore(8);
		obj.getScore("     ").setScore(7);
		obj.getScore("§9§l+---------------§r§9§l+§4").setScore(6);
		obj.getScore(" ").setScore(5);
		obj.getScore("Point(s) §6or§r: §c" + gold).setScore(4);
		obj.getScore("Point(s) §7argent§r: §c" + argent).setScore(3);
		obj.getScore("Point(s) §cbronze§r: §c" + bronze).setScore(2);
		obj.getScore("                 ").setScore(1);
		obj.getScore("§eplay.hycraftages.fr").setScore(0);

		player.setScoreboard(scoreboard);
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onJoin(PlayerJoinEvent event)
	{
		if(AgesRiver.getInstance().getState().getStatistique() == StateGame.ATTENTE || AgesRiver.getInstance().getState().getStatistique() == StateGame.COMMENCEMENT)
		{
			for(Player player : Bukkit.getOnlinePlayers())
			{
				addToScoreBoard(player);
			}
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onQuit(PlayerQuitEvent event)
	{
		if(AgesRiver.getInstance().getState().getStatistique() == StateGame.ATTENTE || AgesRiver.getInstance().getState().getStatistique() == StateGame.COMMENCEMENT)
		{
			for(Player player : Bukkit.getOnlinePlayers())
			{
				addToScoreBoard(player);
			}
		}

	}
}

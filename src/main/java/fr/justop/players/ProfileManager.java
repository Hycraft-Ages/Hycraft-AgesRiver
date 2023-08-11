package fr.justop.players;

import java.util.UUID;

import org.bukkit.entity.Player;

import fr.justop.AgesRiver;
import fr.justop.scoreboard.AttenteScoreboard;

public class ProfileManager
{
	private AgesRiver instance;
	private AttenteScoreboard playerScoreboard;

	public ProfileManager(AgesRiver main)
	{
		this.instance = main;
	}

	public void addTrophy(Player player, String type, int count)
	{
		UUID uuid = player.getUniqueId();

		if(!this.instance.getProfile().containsKey(uuid))
		{
			player.sendMessage(AgesRiver.PREFIX + "§e Le profil du joueur n'a pas ete trouvé impossible d'éxécuter l'action!");
			return;
		}

		Profile profile = this.instance.getProfile().get(uuid);

		switch (type)
		{

			case "or":

				int gold = profile.getGold();
				profile.setGold(count + gold);
				player.sendMessage(AgesRiver.PREFIX + "§aTu as reçu §ex" + count + "§a point(s) en or!");
				break;

			case "argent":

				int argent = profile.getArgent();
				profile.setArgent(argent + count);
				player.sendMessage(AgesRiver.PREFIX + "§aTu as reçu §ex" + count + "§a points(s) en argent!");
				break;

			case "bronze":

				int bronze = profile.getBronze();
				profile.setBronze(count + bronze);
				player.sendMessage(AgesRiver.PREFIX + "§aTu as reçu §ex" + count + "§a points(s) en bronze!");
				break;

		}

		this.playerScoreboard = instance.getAttenteScoreboard();
		playerScoreboard.addToScoreBoard(player);
	}

	public void removeTrophy(Player player, String type, int count)
	{
		UUID uuid = player.getUniqueId();

		if(!this.instance.getProfile().containsKey(uuid))
		{
			player.sendMessage(AgesRiver.PREFIX + "§e Le profil du joueur n'a pas ete trouvé impossible d'éxécuter l'action!");
			return;
		}

		Profile profile = this.instance.getProfile().get(uuid);

		switch (type)
		{

			case "or":

				int gold = profile.getGold();
				profile.setGold(gold - count);
				player.sendMessage(AgesRiver.PREFIX + "§cTu as perdu §ex" + count + "§c point(s) en or!");
				break;

			case "argent":

				int argent = profile.getArgent();
				profile.setArgent(argent - count);
				player.sendMessage(AgesRiver.PREFIX + "§aTu as perdu §ex" + count + "§a point(s) en argent!");
				break;

			case "bronze":

				int bronze = profile.getBronze();
				profile.setBronze(bronze - count);
				player.sendMessage(AgesRiver.PREFIX + "§cTu as perdu §ex" + count + "§c point(s) en bronze!");
				break;

		}

		this.playerScoreboard = instance.getAttenteScoreboard();
		playerScoreboard.addToScoreBoard(player);
	}

	public void addPoint(Player player)
	{
		UUID uuid = player.getUniqueId();

		if(!this.instance.getProfile().containsKey(uuid))
		{
			player.sendMessage(AgesRiver.PREFIX + "§e Le profil du joueur n'a pas ete trouvé impossible d'éxécuter l'action!");
			return;
		}

		Profile profile = this.instance.getProfile().get(uuid);

		int actuel = profile.getWinActuelle();
		int record = profile.getRecordWin();

		profile.setWinActuelle(actuel + 1);

		if (actuel > record)
		{
			profile.setRecordWin(actuel);
		}

		this.playerScoreboard = instance.getAttenteScoreboard();
		playerScoreboard.addToScoreBoard(player);
	}

	public void clearPoints(Player player)
	{
		UUID uuid = player.getUniqueId();

		if(this.instance.getProfile().containsKey(uuid))
		{
			player.sendMessage(AgesRiver.PREFIX + "§e Le profil du joueur n'a pas ete trouvé impossible d'éxécuter l'action!");
			return;
		}

		Profile profile = this.instance.getProfile().get(uuid);

		profile.setWinActuelle(0);

		this.playerScoreboard = instance.getAttenteScoreboard();
		playerScoreboard.addToScoreBoard(player);
	}

}
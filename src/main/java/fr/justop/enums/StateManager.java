package fr.justop.enums;

import org.bukkit.entity.Player;

import fr.justop.AgesRiver;
import fr.justop.players.ListPlayers;
import fr.justop.scoreboard.AttenteScoreboard;
import fr.justop.scoreboard.GameScoreboard;

public class StateManager
{
	private int state;

	public void setStatistique(int state)
	{
		this.state = state;
		ListPlayers list = AgesRiver.getInstance().getList();
		AttenteScoreboard sc1 = AgesRiver.getInstance().getAttenteScoreboard();
		GameScoreboard sc2 = AgesRiver.getInstance().getGameScoreboard();

		switch (this.getStatistique())
		{
			case 0:

				for(Player player : list.getPlayers())
				{
					sc1.addToScoreBoard(player);
				}

				break;

			case 2:

				for(Player player : list.getPlayers())
				{
					sc2.addToScoreBoard(player);
				}

				break;

		}
	}

	public int getStatistique()
	{
		return this.state;
	}

	public String getEtat()
	{

		switch (this.getStatistique())
		{
			case 0:

				return "ATTENTE";

			case 1:
				return "COMMENCEMENT";

			case 2:
				return "JEU";

			case 3:
				return "FIN";
		}
		return null;
	}

}

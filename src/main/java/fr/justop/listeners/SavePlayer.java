package fr.justop.listeners;

import fr.justop.AgesRiver;
import fr.justop.database.DatabaseConnection;
import fr.justop.players.Profile;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class SavePlayer implements Listener
{
	private AgesRiver instance;

	public SavePlayer(AgesRiver agesRiver)
	{
		this.instance = agesRiver;
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent event)
	{
		final UUID uuid = event.getPlayer().getUniqueId();

		if (!this.instance.getProfile().containsKey(uuid))
		{
			event.getPlayer().sendMessage(AgesRiver.PREFIX + "§e Le profil du joueur n'a pas ete trouvé impossible d'éxécuter l'action!");
			return;
		}

		Profile profile  = this.instance.getProfile().get(uuid);

		int actuel 		 = profile.getWinActuelle();
		int record 		 = profile.getRecordWin();
		int gold  		 = profile.getGold();
		int argent 		 = profile.getArgent();
		int bronze 		 = profile.getBronze();
		int register 	 = profile.getRegister();

		this.instance.getProfile().remove(uuid);

		final DatabaseConnection databaseConnection = this.instance.getDatabaseManager().getEventProfileConnection();

		try
		{
			final Connection connection = databaseConnection.getConnection();

			if (register == 0)
			{
				String sql = "INSERT INTO event_profile (UUID, actuel, record, gold, argent, bronze) VALUES (?, ?, ?, ?, ?, ?)";

				PreparedStatement preparedStatement = connection.prepareStatement(sql);

				preparedStatement.setString(1, uuid.toString());
				preparedStatement.setInt(2, actuel);
				preparedStatement.setInt(3, record);
				preparedStatement.setInt(4, gold);
				preparedStatement.setInt(5, argent);
				preparedStatement.setInt(6, bronze);

				preparedStatement.execute();

				Bukkit.getConsoleSender().sendMessage(AgesRiver.PREFIX + "§aLe profil de §6" + event.getPlayer().getName() + " §aa été sauvegardé dans la database");
			}

			else if (register == 1)
			{
				String sql = "UPDATE event_profile SET actuel = ?, record = ?, gold = ?, argent = ?, bronze = ? WHERE UUID = ?";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);

				preparedStatement.setInt(1, actuel);
				preparedStatement.setInt(2, record);
				preparedStatement.setInt(3, gold);
				preparedStatement.setInt(4, argent);
				preparedStatement.setInt(5, bronze);
				preparedStatement.setString(6, uuid.toString());

				preparedStatement.executeUpdate();
				preparedStatement.close();

				Bukkit.getConsoleSender().sendMessage(AgesRiver.PREFIX + "§aLe profil de §6" + event.getPlayer().getName() + " §aa été sauvegardé dans la database");
			}
		}

		catch (SQLException exeption)
		{
			exeption.printStackTrace();
		}
	}
}


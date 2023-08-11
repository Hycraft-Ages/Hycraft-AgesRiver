package fr.justop.listeners;

import fr.justop.AgesRiver;
import fr.justop.database.DatabaseConnection;
import fr.justop.players.Profile;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class RegisterPlayer implements Listener
{
	private AgesRiver instance;

	public RegisterPlayer(AgesRiver agesRiver)
	{
		this.instance = agesRiver;
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onJoin(PlayerJoinEvent event)
	{
		UUID uuid = event.getPlayer().getUniqueId();
		final DatabaseConnection eventProfileConnection = this.instance.getDatabaseManager().getEventProfileConnection();

		Player player = event.getPlayer();

		try
		{
			final Connection connection = eventProfileConnection.getConnection();
			final PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM event_profile WHERE UUID = ?");

			preparedStatement.setString(1, uuid.toString());
			preparedStatement.executeQuery();

			final ResultSet result = preparedStatement.getResultSet();

			if (result.next())
			{
				int actuel = result.getInt("actuel");
				int record = result.getInt("record");
				int gold   = result.getInt("gold");
				int argent = result.getInt("argent");
				int bronze = result.getInt("bronze");

				Profile profile = new Profile(this.instance, uuid, actuel, record, gold, argent, bronze, 1);
				this.instance.getProfile().put(uuid, profile);

				Bukkit.getConsoleSender().sendMessage(AgesRiver.PREFIX + "§6" + event.getPlayer().getName() + "§a a été trouvé dans la database");
			}

			else
			{

				Profile profile = new Profile(this.instance, uuid, 0, 0, 0, 0, 0, 0);
				this.instance.getProfile().put(uuid, profile);

				Bukkit.getConsoleSender().sendMessage(AgesRiver.PREFIX + "§9Création du profil du joueur §a" + player.getName() + " §9réussi");

			}
		}
		catch (SQLException exeption)
		{
			exeption.printStackTrace();
		}

	}
}

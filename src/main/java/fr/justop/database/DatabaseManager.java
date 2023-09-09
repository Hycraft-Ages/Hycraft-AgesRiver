package fr.justop.database;

import fr.justop.players.PlayerStats;

import java.sql.*;
import java.util.Map;
import java.util.UUID;

public class DatabaseManager {
	private final DatabaseConnection eventProfileConnection;

	public DatabaseManager() {
		this.eventProfileConnection = new DatabaseConnection(new DatabaseCredentials("135.181.79.174", "u3_EVN3FUtSHR", "22T.JN8Bc1Zt^l1FjJS7CrY.", "s3_minigames", 3306));
	}

	public DatabaseConnection getEventProfileConnection() {
		return this.eventProfileConnection;
	}

	public void initializeEventProfileDatabase() throws SQLException {
		Statement statement = getEventProfileConnection().getConnection().createStatement();
		String sql = "CREATE TABLE IF NOT EXISTS event_profile(UUID varchar(36) primary key, actuel int, record int, gold int, argent int, bronze int)";
		String sql2 = "CREATE TABLE IF NOT EXISTS global_class(UUID varchar(36) primary key, temps int)";
		statement.execute(sql);
		statement.execute(sql2);

		statement.close();
	}

	public void saveGlobal() throws SQLException {
		final Connection connection = getEventProfileConnection().getConnection();

		for (Map.Entry<UUID, Integer> entry : PlayerStats.getGlobal().entrySet()) {


			String uuid = entry.getKey().toString();
			int temps = entry.getValue();

			String sql = "INSERT INTO global_class (UUID, temps) VALUES (?, ?)";

			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, uuid);
			statement.setInt(2, temps);

			statement.execute();

		}
	}

	public void registerGlobal() throws SQLException {
		final Connection connection = getEventProfileConnection().getConnection();
		final Statement statement = connection.createStatement();
		final ResultSet result = statement.executeQuery("SELECT * FROM global_class");

		while (result.next()) {
			UUID uuid = UUID.fromString(result.getString(1));
			int temps = result.getInt(2);

			PlayerStats.getGlobal().put(uuid, temps);
		}

		PlayerStats.sortByValues();

	}

	public void close() {
		try {
			this.eventProfileConnection.close();
		} catch (SQLException exeption) {
			exeption.printStackTrace();
		}
	}
}

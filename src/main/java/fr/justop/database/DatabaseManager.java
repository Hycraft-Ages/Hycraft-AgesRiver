package fr.justop.database;

import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager
{
	private DatabaseConnection eventProfileConnection;

	public DatabaseManager()
	{
		this.eventProfileConnection = new DatabaseConnection(new DatabaseCredentials("135.181.79.174", "u3_EVN3FUtSHR", "22T.JN8Bc1Zt^l1FjJS7CrY.", "s3_minigames", 3306));
	}

	public DatabaseConnection getEventProfileConnection()
	{
		return this.eventProfileConnection;
	}

	public void initializeEventProfileDatabase() throws SQLException
	{
		Statement statement = getEventProfileConnection().getConnection().createStatement();
		String sql = "CREATE TABLE IF NOT EXISTS event_profile(UUID varchar(36) primary key, actuel int, record int, gold int, argent int, bronze int)";
		statement.execute(sql);
	}

	public void close()
	{
		try
		{
			this.eventProfileConnection.close();
		}
		catch (SQLException exeption)
		{
			exeption.printStackTrace();
		}
	}
}

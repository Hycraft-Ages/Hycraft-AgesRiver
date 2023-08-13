package fr.justop.database;

import fr.justop.AgesRiver;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private DatabaseCredentials databaseCredentials;
	private Connection connection;

	public DatabaseConnection(DatabaseCredentials dbCredentials) {
		this.databaseCredentials = dbCredentials;
		this.connect();
	}

	public void connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.connection = DriverManager.getConnection(this.databaseCredentials.toURI(), this.databaseCredentials.getUser(), this.databaseCredentials.getPass());

			if (!(this.connection == null))
				Bukkit.getConsoleSender().sendMessage(AgesRiver.PREFIX + "§aConnection reussie à la DB");
		} catch (SQLException | ClassNotFoundException exeption) {
			exeption.printStackTrace();
		}
	}

	public void close() throws SQLException {
		if (this.connection != null && !this.connection.isClosed()) {
			this.connection.close();
			Bukkit.getConsoleSender().sendMessage(AgesRiver.PREFIX + "§aLa connection de la Database à bien été interrompue!");
		}
	}

	public Connection getConnection() throws SQLException {
		if (this.connection != null && !this.connection.isClosed()) {
			return this.connection;
		}
		this.connect();
		return this.connection;
	}
}

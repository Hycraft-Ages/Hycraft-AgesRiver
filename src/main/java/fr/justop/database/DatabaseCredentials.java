package fr.justop.database;

public class DatabaseCredentials
{
	private String host;
	private String user;
	private String pass;
	private String dbName;
	private int    port;

	public DatabaseCredentials(String host, String user, String pass, String dbName, int port)
	{
		this.host = host;
		this.user = user;
		this.pass = pass;
		this.dbName = dbName;
		this.port = port;
	}

	public String toURI()
	{
		StringBuilder builder = new StringBuilder();

		builder.append("jdbc:mysql://")
				.append(host)
				.append(":")
				.append(port)
				.append("/")
				.append(dbName);

		return builder.toString();

	}

	public String getHost()
	{
		return host;
	}

	public String getUser()
	{
		return user;
	}

	public String getPass()
	{
		return pass;
	}

	public String getDbName()
	{
		return dbName;
	}

	public int getPort()
	{
		return port;
	}

}

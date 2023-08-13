package fr.justop;

import fr.justop.commands.*;
import fr.justop.configs.SpawnManager;
import fr.justop.database.DatabaseManager;
import fr.justop.enums.StateGame;
import fr.justop.enums.StateManager;
import fr.justop.guis.InventoryPlayers;
import fr.justop.listeners.Game;
import fr.justop.listeners.RegisterPlayer;
import fr.justop.listeners.SavePlayer;
import fr.justop.listeners.VehicleMove;
import fr.justop.players.ListPlayers;
import fr.justop.players.PlayerStats;
import fr.justop.players.Profile;
import fr.justop.players.ProfileManager;
import fr.justop.scoreboard.AttenteScoreboard;
import fr.justop.scoreboard.BossBars;
import fr.justop.scoreboard.GameScoreboard;
import fr.justop.tasks.TaskGame;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.mineacademy.fo.plugin.SimplePlugin;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * PluginTemplate is a simple template you can use every time you make
 * a new plugin. This will save you time because you no longer have to
 * recreate the same skeleton and features each time.
 * <p>
 * It uses Foundation for fast and efficient development process.
 */
public final class AgesRiver extends SimplePlugin {

	public static String PREFIX = "§8[§bAgesRiver§8] §r";
	private TaskGame task;
	private StateManager game;
	private DatabaseManager databaseManager;
	private ProfileManager manager;
	private final Map<UUID, Profile> profiles = new HashMap<>();
	private AttenteScoreboard attenteScoreboard;
	private GameScoreboard gameScoreboard;
	private SpawnManager configuration;
	private ListPlayers list;
	private PlayerStats stats;
	private final Map<UUID, Integer> finished = new HashMap<>();
	private BossBars bossBars;

	/**
	 * Automatically perform login ONCE when the plugin starts.
	 */
	@Override
	protected void onPluginStart() {
		this.databaseManager = new DatabaseManager();

		try {
			this.databaseManager.initializeEventProfileDatabase();
			this.databaseManager.registerGlobal();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		this.list = new ListPlayers();
		this.game = new StateManager();
		this.game.setStatistique(StateGame.ATTENTE);

		this.bossBars = new BossBars(this);

		this.manager = new ProfileManager(this);
		this.attenteScoreboard = new AttenteScoreboard(this);
		this.gameScoreboard = new GameScoreboard(this);
		this.configuration = new SpawnManager(this);
		this.list = new ListPlayers();

		onCommands();
		onListeners();
		this.configuration.onFile();

		Bukkit.getServer().getConsoleSender().sendMessage(PREFIX + "§aLe plugin est activé");
	}

	@Override
	protected void onPluginStop() {
		try {
			this.databaseManager.saveGlobal();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		this.databaseManager.close();
		this.configuration.saveConfig();
		Bukkit.getServer().getConsoleSender().sendMessage(PREFIX + "§7Plugin §cdésactivé");
	}

	private void onCommands() {
		this.getCommand("boat-spawn").setExecutor(new CommandSpawn(this));
		this.getCommand("boat-staff").setExecutor(new CommandBoatStaff(this, this.game));
		this.getCommand("boat-warp").setExecutor(new CommandWarps(this));
		this.getCommand("boat-trophy").setExecutor(new CommandTrophy(this));
		this.getCommand("details").setExecutor(new CommandDetails());
	}

	private void onListeners() {
		PluginManager pluginManager = getServer().getPluginManager();

		pluginManager.registerEvents(new RegisterPlayer(this), this);
		pluginManager.registerEvents(new Game(this), this);
		pluginManager.registerEvents(new SavePlayer(this), this);
		pluginManager.registerEvents(new AttenteScoreboard(this), this);
		pluginManager.registerEvents(new InventoryPlayers(), this);
		pluginManager.registerEvents(new VehicleMove(), this);
	}

	public void iniStats() {
		this.stats = new PlayerStats();
	}

	public PlayerStats getStats() {
		return this.stats;
	}

	public fr.justop.database.DatabaseManager getDatabaseManager() {
		return this.databaseManager;
	}

	public ProfileManager getProfileManager() {
		return this.manager;
	}

	public Map<UUID, Profile> getProfile() {
		return this.profiles;
	}

	public AttenteScoreboard getAttenteScoreboard() {
		return this.attenteScoreboard;
	}

	public SpawnManager getSpawnManager() {
		return this.configuration;
	}

	public ListPlayers getList() {
		return this.list;
	}

	public StateManager getState() {
		return this.game;
	}

	public GameScoreboard getGameScoreboard() {
		return gameScoreboard;
	}

	public BossBars getBossBars() {
		return this.bossBars;
	}

	/**
	 * @return the finished
	 */
	public Map<UUID, Integer> getFinished() {
		return finished;
	}

	public void startTaskGame() {
		task = new TaskGame();
		task.runTaskTimer(getInstance(), 0L, 20L);

	}

	public TaskGame getTaskGame() {
		return this.task;
	}

	/**
	 * Automatically perform login when the plugin starts and each time it is reloaded.
	 */

	public static AgesRiver getInstance() {
		return (AgesRiver) SimplePlugin.getInstance();
	}

}

package fr.justop.listeners;

import fr.justop.AgesRiver;
import fr.justop.enums.Mode;
import fr.justop.enums.StateGame;
import fr.justop.enums.StateManager;
import fr.justop.guis.InventoryPlayers;
import fr.justop.holograms.HologramManager;
import fr.justop.players.ListPlayers;
import fr.justop.players.PlayerStats;
import fr.justop.tasks.TaskCommencement;
import fr.justop.tasks.TaskGame;
import fr.justop.worlds.Worlds;
import org.bukkit.*;
import org.bukkit.entity.Boat;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.vehicle.VehicleEntityCollisionEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.event.vehicle.VehicleMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.mineacademy.fo.remain.CompBarColor;
import org.mineacademy.fo.remain.CompBarStyle;
import org.mineacademy.fo.remain.Remain;

import java.awt.Color;
import java.util.*;
import java.util.Map.Entry;

public class Game implements Listener {
	private AgesRiver instance;
	private static TaskCommencement task;
	private static PlayerStats playerStats;
	private static List<Player> file = new LinkedList<>();
	private Player player1;
	private Player player2;
	private Player player3;
	private Player player4;
	private Player player5;
	private Player player6;
	private Player player7;
	private Player player8;
	private Player player9;

	private static Location locationBlock1;
	private static Location locationBlock2;
	private static Location locationBlock3;
	private static Location locationBlock4;
	private static Location locationBlock5;
	private static Location locationBlock6;
	private static Location locationBlock7;
	private static Location locationBlock8;
	private static Location locationBlock9;
	private static Location locationBlock10;
	private static Location locationBlock11;
	private static Location locationBlock12;
	private static Location locationBlock13;
	private static Location locationBlock14;
	private static Location locationBlock15;
	private static Location locationBlock16;
	private static Location locationBlock17;
	private static Location locationBlock18;
	private static Location locationBlock19;
	private static Location locationBlock20;
	private static Location locationBlock21;
	private static Location locationBlock22;
	private static Location locationBlock23;
	private static Location locationBlock24;
	private static Location locationBlock25;
	private static Location locationBlock26;
	private static Location locationBlock27;
	private static Location locationBlock28;
	private static Location locationBlock29;
	private static Location locationBlock30;
	private static Location locationBlock31;
	private static Location locationBlock32;
	private static Location locationBlock33;
	private static Location locationBlock34;
	private static Location locationBlock35;
	private static Location locationBlock36;
	private static Location locationBlock37;
	private static Location locationBlock38;
	private static Location locationBlock39;
	private static Location locationBlock40;
	private static Location locationBlock41;
	private static Location locationBlock42;
	private Location locationBlock43;
	private static Location locationBlock44;
	private static Location locationBlock46;
	private static Location locationBlock45;
	private static Location locationBlock47;
	private static Location locationBlock48;
	private static Location locationBlock49;
	private static Location locationBlock50;

	public Game(AgesRiver main) {
		this.instance = main;
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onJoin(PlayerJoinEvent event)
	{
		Player player = event.getPlayer();

		if (player.hasPermission("hycraft.staff")) {
			player.teleport(new Location(Bukkit.getWorld("Ages_River"), 0.0, 0.0, 0.0, 90.0F, 0.0F));
			player.setGameMode(GameMode.CREATIVE);
			HologramManager.loadClassementHologram(player);
			player.sendMessage("");
			player.sendMessage(AgesRiver.PREFIX + "§eBonjour à toi cher membre du staff §b" + player.getDisplayName() + "§e. " +
					"D'office, tu es exclu de la file d'attente pour pouvoir vaquer à tes occupations. Mais si tu" +
					" souhaites rejoindre les autres participants, utilise §b/boat-staff join §eet au contraire §b" +
					"/boat-staff leave §epour la quitter. Tu as également la permission d'ajouter des trophées ou à" +
					" en soustraire au joueur de to choix. Pour cela utilise§b /boat-trophy (add/remove) (or/argent/bronze)" +
					" (joueur) (nombre)");
			player.sendMessage("");

			for (Player p : Bukkit.getOnlinePlayers()) {
				if (p.hasPermission("hycraft.boatrace.staff")) {
					p.sendMessage(AgesRiver.PREFIX + "§4" + player.getName() + "§e vient de se connecter");
				}
			}

			InitializePositions();
			event.setJoinMessage(null);
			return;
		}

		if(AgesRiver.getInstance().getState().getMode() == Mode.GAME)
		{
			joinPlayer(player);
		}
		else
		{
			trainPlayer(player);
		}


	}

	public static void trainPlayer(Player player)
	{
		player.teleport(new Location(Bukkit.getWorld("Ages_River"), -105.0D, 33.0D, -64.0D, 90.0F, 0.0F));
		player.setAllowFlight(false);
		player.setGameMode(GameMode.ADVENTURE);
		player.setHealth(20D);
		player.setFoodLevel(Integer.MAX_VALUE);
		player.getInventory().clear();

		HologramManager.loadClassementHologram(player);

		player.sendMessage("");
		player.sendMessage("§8§m--------------" + AgesRiver.PREFIX + "§r§8§m---------------");
		player.sendMessage("");
		player.sendMessage("    §eBienvenue Joueurs d'Hycraft sur le tout    ");
		player.sendMessage("       §epremier évenement organisé sur le        ");
		player.sendMessage("                      §eserveur !                  ");
		player.sendMessage("    §eTu a rejoint le jeu en mode entraînement. ");
		player.sendMessage("      §ePour t'entraîner dans les différentes       ");
		player.sendMessage("                §eépoques, utilise le           ");
		player.sendMessage("                §a§lTraining stick");
		player.sendMessage("");
		player.sendMessage("                 §6§lBonne séance!            ");
		player.sendMessage("§8§m----------------------------------------");
		player.sendMessage("");

		giveStick(player);
	}

	public static void joinPlayer(Player player)
	{
		StateManager game = AgesRiver.getInstance().getState();

		player.teleport(new Location(Bukkit.getWorld("Ages_River"), -105.0D, 33.0D, -64.0D, 90.0F, 0.0F));
		player.setAllowFlight(false);
		player.setGameMode(GameMode.ADVENTURE);
		player.setHealth(20D);
		player.setFoodLevel(Integer.MAX_VALUE);
		player.getInventory().clear();

		HologramManager.loadClassementHologram(player);

		if (!(game.getStatistique() == StateGame.ATTENTE)) {
			player.setGameMode(GameMode.ADVENTURE);
			player.getAllowFlight();
			player.setFlying(true);
			player.teleport(player.getLocation().add(0,1,0));
			player.sendMessage(AgesRiver.PREFIX + "§cLe jeu a déja commencé, Patiente la prochaine partie! Tu t'est inscrit dans la file d'attente.");
			player.sendMessage(AgesRiver.PREFIX + "§eUtilise la boussole pour te §btéléporter §eaux autres joueurs!");
			giveCompass(player);

			if(!file.contains(player))
			{
				file.add(player);
			}

			return;
		}

		player.sendMessage("");
		player.sendMessage("§8§m--------------" + AgesRiver.PREFIX + "§r§8§m---------------");
		player.sendMessage("");
		player.sendMessage("    §eBienvenue Joueurs d'Hycraft sur le tout    ");
		player.sendMessage("       §epremier évenement organisé sur le        ");
		player.sendMessage("                      §eserveur !                  ");
		player.sendMessage("   §eIl s'agit ici de terminer le plus rapidement ");
		player.sendMessage("           §epossible une course de bateau        ");
		player.sendMessage("                §aà travers les ages.             ");
		player.sendMessage("");
		player.sendMessage("             §6§lBonne chance a tous!            ");
		player.sendMessage("§8§m----------------------------------------");
		player.sendMessage("");

		ListPlayers playersList = AgesRiver.getInstance().getList();

		if (game.getStatistique() == StateGame.ATTENTE && playersList.getPlayers().size() <= AgesRiver.getInstance().getSpawnManager().getSpawnsList().size()) {
			if (!playersList.getPlayers().contains(player)) {
				playersList.getPlayers().add(player);

				for (Player p : Bukkit.getOnlinePlayers()) {
					AgesRiver.getInstance().getAttenteScoreboard().addToScoreBoard(p);
				}
			}

			Bukkit.broadcastMessage(AgesRiver.PREFIX + "§e " + player.getName() + "§c a rejoint la partie ! §4<" + playersList.getPlayers().size() + "/" + AgesRiver.getInstance().getSpawnManager().getSpawnsList().size() + ">");
		}

		if (playersList.getPlayers().size() == AgesRiver.getInstance().getSpawnManager().getSpawnsList().size()) {
			startGame();
		}
	}

	@EventHandler
	public void onDamage(EntityDamageEvent event) {
		if (event.getEntity() instanceof Player) {
			event.setCancelled(true);
		} else if (event.getEntity() instanceof Boat && event.getCause() == EntityDamageEvent.DamageCause.FALL)
		{
			event.setCancelled(true);
		}

	}


	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		ListPlayers playersList = this.instance.getList();

		if (player.hasPermission("hycraft.boatrace.staff")) {
			if (playersList.getPlayers().contains(player)) {
				playersList.getPlayers().remove(player);
			}

			event.setQuitMessage(null);
			return;
		}

		playersList.getPlayers().remove(player);

		if (this.instance.getState().getStatistique() == StateGame.ATTENTE) {
			for (Player p : Bukkit.getOnlinePlayers()) {
				this.instance.getAttenteScoreboard().addToScoreBoard(p);
			}

			event.setQuitMessage(AgesRiver.PREFIX + "§e " + player.getName() + "§c a quitté la partie ! §4<" + playersList.getPlayers().size() + "/" + this.instance.getSpawnManager().getSpawnsList().size() + ">");
		}

		if (this.instance.getState().getStatistique() == StateGame.COMMENCEMENT) {
			if (playersList.getPlayers().size() == 1) {
				this.task.clear();
			} else {
				event.setQuitMessage(AgesRiver.PREFIX + "§e " + player.getName() + "§c a quitté la partie ! §4<" + playersList.getPlayers().size() + "/" + this.instance.getSpawnManager().getSpawnsList().size() + ">");
			}

		}
		if (this.instance.getState().getStatistique() == StateGame.JEU) {
			player.getVehicle().remove();
			event.setQuitMessage(AgesRiver.PREFIX + "§e " + player.getName() + "§c a quitté la partie !");
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onExitBoat(VehicleExitEvent event) {
		if (event.getVehicle().getPassenger().isOp() && AgesRiver.getInstance().getState().getStatistique() != StateGame.JEU) {
			return;
		}

		if (event.getVehicle() instanceof Boat && AgesRiver.getInstance().getState().getStatistique() == StateGame.JEU && AgesRiver.getInstance().getList().getPlayers().contains(event.getVehicle().getPassenger())) {
			event.setCancelled(true);
		}
	}

	public static void startGame() {
		ListPlayers playersList = AgesRiver.getInstance().getList();
		StateManager game = AgesRiver.getInstance().getState();

		if (game.getStatistique() == StateGame.ATTENTE && playersList.getPlayers().size() >= 2) {
			task = new TaskCommencement();
			game.setStatistique(StateGame.COMMENCEMENT);
			InitializePositions();
			playerStats = AgesRiver.getInstance().getStats();
			task.runTaskTimer(AgesRiver.getInstance(), 0L, 20L);


		}
	}

	public TaskCommencement getTask() {
		return this.task;
	}

	public static void InitializePositions() {
		locationBlock1 = new Location(Bukkit.getWorld("Ages_River"), -349.0, 52.0, -209.0, 0.0f, 0.0f);
		locationBlock2 = new Location(Bukkit.getWorld("Ages_River"), -348.0, 52.0, -209.0, 0.0f, 0.0f);
		locationBlock3 = new Location(Bukkit.getWorld("Ages_River"), -348.0, 52.0, -208.0, 0.0f, 0.0f);
		locationBlock4 = new Location(Bukkit.getWorld("Ages_River"), -347.0, 52.0, -208.0, 0.0f, 0.0f);
		locationBlock5 = new Location(Bukkit.getWorld("Ages_River"), -347.0, 52.0, -207.0, 0.0f, 0.0f);
		locationBlock6 = new Location(Bukkit.getWorld("Ages_River"), -346.0, 52.0, -207.0, 0.0f, 0.0f);
		locationBlock7 = new Location(Bukkit.getWorld("Ages_River"), -346.0, 52.0, -206.0, 0.0f, 0.0f);
		locationBlock8 = new Location(Bukkit.getWorld("Ages_River"), -345.0, 52.0, -206.0, 0.0f, 0.0f);
		locationBlock9 = new Location(Bukkit.getWorld("Ages_River"), -345.0, 52.0, -205.0, 0.0f, 0.0f);
		locationBlock10 = new Location(Bukkit.getWorld("Ages_River"), -344.0, 52.0, -205.0, 0.0f, 0.0f);
		locationBlock11 = new Location(Bukkit.getWorld("Ages_River"), -344.0, 52.0, -204.0, 0.0f, 0.0f);
		locationBlock12 = new Location(Bukkit.getWorld("Ages_River"), -343.0, 52.0, -204.0, 0.0f, 0.0f);
		locationBlock13 = new Location(Bukkit.getWorld("Ages_River"), -343.0, 52.0, -203.0, 0.0f, 0.0f);
		locationBlock14 = new Location(Bukkit.getWorld("Ages_River"), -342.0, 52.0, -203.0, 0.0f, 0.0f);

		locationBlock15 = new Location(Bukkit.getWorld("Ages_River"), -902.0, 0.0, -9995.0, 0.0f, 0.0f);
		locationBlock16 = new Location(Bukkit.getWorld("Ages_River"), -903.0, 0.0, -9995.0, 0.0f, 0.0f);
		locationBlock17 = new Location(Bukkit.getWorld("Ages_River"), -904.0, 0.0, -9995.0, 0.0f, 0.0f);
		locationBlock18 = new Location(Bukkit.getWorld("Ages_River"), -905.0, 0.0, -9995.0, 0.0f, 0.0f);
		locationBlock19 = new Location(Bukkit.getWorld("Ages_River"), -906.0, 0.0, -9995.0, 0.0f, 0.0f);
		locationBlock20 = new Location(Bukkit.getWorld("Ages_River"), -907.0, 0.0, -9995.0, 0.0f, 0.0f);
		locationBlock21 = new Location(Bukkit.getWorld("Ages_River"), -908.0, 0.0, -9995.0, 0.0f, 0.0f);
		locationBlock22 = new Location(Bukkit.getWorld("Ages_River"), -909.0, 0.0, -9995.0, 0.0f, 0.0f);
		locationBlock23 = new Location(Bukkit.getWorld("Ages_River"), -910.0, 0.0, -9995.0, 0.0f, 0.0f);
		locationBlock24 = new Location(Bukkit.getWorld("Ages_River"), -911.0, 0.0, -9995.0, 0.0f, 0.0f);
		locationBlock25 = new Location(Bukkit.getWorld("Ages_River"), -912.0, 0.0, -9995.0, 0.0f, 0.0f);
		locationBlock26 = new Location(Bukkit.getWorld("Ages_River"), -913.0, 0.0, -9995.0, 0.0f, 0.0f);

		locationBlock27 = new Location(Bukkit.getWorld("Ages_River"), -8832.0, 53.0, -19929.0, 0.0f, 0.0f);
		locationBlock28 = new Location(Bukkit.getWorld("Ages_River"), -8832.0, 53.0, -19930.0, 0.0f, 0.0f);
		locationBlock29 = new Location(Bukkit.getWorld("Ages_River"), -8832.0, 53.0, -19931.0, 0.0f, 0.0f);
		locationBlock30 = new Location(Bukkit.getWorld("Ages_River"), -8832.0, 53.0, -19932.0, 0.0f, 0.0f);
		locationBlock31 = new Location(Bukkit.getWorld("Ages_River"), -8832.0, 53.0, -19933.0, 0.0f, 0.0f);
		locationBlock32 = new Location(Bukkit.getWorld("Ages_River"), -8832.0, 53.0, -19934.0, 0.0f, 0.0f);
		locationBlock33 = new Location(Bukkit.getWorld("Ages_River"), -8832.0, 53.0, -19935.0, 0.0f, 0.0f);
		locationBlock34 = new Location(Bukkit.getWorld("Ages_River"), -8832.0, 53.0, -19936.0, 0.0f, 0.0f);
		locationBlock35 = new Location(Bukkit.getWorld("Ages_River"), -8832.0, 53.0, -19937.0, 0.0f, 0.0f);
		locationBlock36 = new Location(Bukkit.getWorld("Ages_River"), -8832.0, 53.0, -19938.0, 0.0f, 0.0f);
		locationBlock37 = new Location(Bukkit.getWorld("Ages_River"), -8832.0, 53.0, -19939.0, 0.0f, 0.0f);
		locationBlock38 = new Location(Bukkit.getWorld("Ages_River"), -8832.0, 53.0, -19940.0, 0.0f, 0.0f);
		locationBlock39 = new Location(Bukkit.getWorld("Ages_River"), -8832.0, 53.0, -19941.0, 0.0f, 0.0f);
		locationBlock40 = new Location(Bukkit.getWorld("Ages_River"), -8832.0, 53.0, -19942.0, 0.0f, 0.0f);
		locationBlock41 = new Location(Bukkit.getWorld("Ages_River"), -8832.0, 53.0, -19943.0, 0.0f, 0.0f);
		locationBlock42 = new Location(Bukkit.getWorld("Ages_River"), -8832.0, 53.0, -19944.0, 0.0f, 0.0f);
		locationBlock44 = new Location(Bukkit.getWorld("Ages_River"), -8832.0, 53.0, -19946.0, 0.0f, 0.0f);
		locationBlock45 = new Location(Bukkit.getWorld("Ages_River"), -8832.0, 53.0, -19947.0, 0.0f, 0.0f);
		locationBlock46 = new Location(Bukkit.getWorld("Ages_River"), -8832.0, 53.0, -19948.0, 0.0f, 0.0f);
		locationBlock47 = new Location(Bukkit.getWorld("Ages_River"), -8832.0, 53.0, -19949.0, 0.0f, 0.0f);
		locationBlock48 = new Location(Bukkit.getWorld("Ages_River"), -8832.0, 53.0, -19950.0, 0.0f, 0.0f);
		locationBlock49 = new Location(Bukkit.getWorld("Ages_River"), -8832.0, 53.0, -19951.0, 0.0f, 0.0f);
		locationBlock50 = new Location(Bukkit.getWorld("Ages_River"), -8832.0, 53.0, -19952.0, 0.0f, 0.0f);
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onMoveBoat(VehicleMoveEvent event) {

		if (event.getVehicle() instanceof Boat) {
			Boat boat = (Boat) event.getVehicle();

			if (boat.isEmpty()) {
				Bukkit.getConsoleSender().sendMessage("Null");
				return;
			}

			final Player player = (Player) boat.getPassenger();

			if (player == null) {
				Bukkit.getConsoleSender().sendMessage("Null Player");
			}

			this.playerStats = AgesRiver.getInstance().getStats();

			Location locationTo = event.getTo().getBlock().getLocation();

			if (locationTo.equals(locationBlock1) ||
					locationTo.equals(locationBlock2) ||
					locationTo.equals(locationBlock3) ||
					locationTo.equals(locationBlock4) ||
					locationTo.equals(locationBlock5) ||
					locationTo.equals(locationBlock6) ||
					locationTo.equals(locationBlock7) ||
					locationTo.equals(locationBlock8) ||
					locationTo.equals(locationBlock9) ||
					locationTo.equals(locationBlock10) ||
					locationTo.equals(locationBlock11) ||
					locationTo.equals(locationBlock12) ||
					locationTo.equals(locationBlock13) ||
					locationTo.equals(locationBlock14)) {

				AgesRiver.getInstance().getList().setPlayerPassenger(player, new Location(Bukkit.getWorld("Ages_River"), -888.0, 55.0, -9515.0, 180.0f, 0.0f));
				player.sendMessage(AgesRiver.PREFIX + "§aVous entrez en Antiquité!");
				boat.remove();

				player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 1.0f, 1.0f);
				Remain.removeBossbar(player);
				Remain.sendBossbarPercent(player, "§e§lAntiquité", 100, CompBarColor.YELLOW, CompBarStyle.SOLID);
				int current = this.playerStats.getNbTour().get(player.getUniqueId());

				switch (current) {
					case 1:
						this.playerStats.getSeg1().put(player.getUniqueId(), AgesRiver.getInstance().getTaskGame().getTimer());
						this.instance.getStats().getCurrentAge().put(player.getUniqueId(), 2);
						break;

					case 2:
						this.playerStats.getSeg4().put(player.getUniqueId(), AgesRiver.getInstance().getTaskGame().getTimer()
								- this.playerStats.getSeg1().get(player.getUniqueId())
								- this.playerStats.getSeg2().get(player.getUniqueId())
								- this.playerStats.getSeg3().get(player.getUniqueId()));
						this.instance.getStats().getCurrentAge().put(player.getUniqueId(), 2);
						break;

					case 3:
						this.playerStats.getSeg7().put(player.getUniqueId(), AgesRiver.getInstance().getTaskGame().getTimer()
								- this.playerStats.getSeg1().get(player.getUniqueId())
								- this.playerStats.getSeg2().get(player.getUniqueId())
								- this.playerStats.getSeg3().get(player.getUniqueId())
								- this.playerStats.getSeg4().get(player.getUniqueId())
								- this.playerStats.getSeg5().get(player.getUniqueId())
								- this.playerStats.getSeg6().get(player.getUniqueId()));
						this.instance.getStats().getCurrentAge().put(player.getUniqueId(), 2);
						break;
				}

				boat.remove();
				return;
			}

			if (locationTo.equals(locationBlock15) ||
					locationTo.equals(locationBlock16) ||
					locationTo.equals(locationBlock17) ||
					locationTo.equals(locationBlock18) ||
					locationTo.equals(locationBlock19) ||
					locationTo.equals(locationBlock20) ||
					locationTo.equals(locationBlock21) ||
					locationTo.equals(locationBlock22) ||
					locationTo.equals(locationBlock23) ||
					locationTo.equals(locationBlock24) ||
					locationTo.equals(locationBlock25) ||
					locationTo.equals(locationBlock26)) {
				AgesRiver.getInstance().getList().setPlayerPassenger(player, new Location(Bukkit.getWorld("Ages_River"), -8940.0, 82.0, -19893.0, 60.0f, 0.0f));
				player.sendMessage(AgesRiver.PREFIX + "§aVous entrez au Moyen-Age!");
				boat.remove();

				player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 1.0f, 1.0f);
				Remain.removeBossbar(player);
				Remain.sendBossbarPercent(player, "§b§lMoyen-âge", 100, CompBarColor.BLUE, CompBarStyle.SOLID);
				int current = this.playerStats.getNbTour().get(player.getUniqueId());

				switch (current) {
					case 1:
						this.playerStats.getSeg2().put(player.getUniqueId(), AgesRiver.getInstance().getTaskGame().getTimer()
								- this.playerStats.getSeg1().get(player.getUniqueId()));
						this.instance.getStats().getCurrentAge().put(player.getUniqueId(), 3);
						break;

					case 2:
						this.playerStats.getSeg5().put(player.getUniqueId(), AgesRiver.getInstance().getTaskGame().getTimer()
								- this.playerStats.getSeg1().get(player.getUniqueId())
								- this.playerStats.getSeg2().get(player.getUniqueId())
								- this.playerStats.getSeg3().get(player.getUniqueId())
								- this.playerStats.getSeg4().get(player.getUniqueId()));
						this.instance.getStats().getCurrentAge().put(player.getUniqueId(), 3);
						break;

					case 3:
						this.playerStats.getSeg8().put(player.getUniqueId(), AgesRiver.getInstance().getTaskGame().getTimer()
								- this.playerStats.getSeg1().get(player.getUniqueId())
								- this.playerStats.getSeg2().get(player.getUniqueId())
								- this.playerStats.getSeg3().get(player.getUniqueId())
								- this.playerStats.getSeg4().get(player.getUniqueId())
								- this.playerStats.getSeg5().get(player.getUniqueId())
								- this.playerStats.getSeg6().get(player.getUniqueId())
								- this.playerStats.getSeg7().get(player.getUniqueId()));
						this.instance.getStats().getCurrentAge().put(player.getUniqueId(), 3);
						break;
				}
				return;
			}

			if (locationTo.equals(locationBlock27) ||
					locationTo.equals(locationBlock28) ||
					locationTo.equals(locationBlock29) ||
					locationTo.equals(locationBlock30) ||
					locationTo.equals(locationBlock31) ||
					locationTo.equals(locationBlock32) ||
					locationTo.equals(locationBlock33) ||
					locationTo.equals(locationBlock34) ||
					locationTo.equals(locationBlock35) ||
					locationTo.equals(locationBlock36) ||
					locationTo.equals(locationBlock37) ||
					locationTo.equals(locationBlock38) ||
					locationTo.equals(locationBlock39) ||
					locationTo.equals(locationBlock40) ||
					locationTo.equals(locationBlock41) ||
					locationTo.equals(locationBlock42) ||
					locationTo.equals(locationBlock43) ||
					locationTo.equals(locationBlock44) ||
					locationTo.equals(locationBlock45) ||
					locationTo.equals(locationBlock46) ||
					locationTo.equals(locationBlock47) ||
					locationTo.equals(locationBlock48) ||
					locationTo.equals(locationBlock49) ||
					locationTo.equals(locationBlock50))
			{
				player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
				boat.remove();

				int current = this.playerStats.getNbTour().get(player.getUniqueId());

				switch (current) {
					case 1:

						AgesRiver.getInstance().getList().setPlayerPassenger(player, new Location(Bukkit.getWorld("Ages_River"), -196.0, 36.0, -67.0, 90.0f, 0.0f));

						this.playerStats.getSeg3().put(player.getUniqueId(), AgesRiver.getInstance().getTaskGame().getTimer()
								- this.playerStats.getSeg1().get(player.getUniqueId())
								- this.playerStats.getSeg2().get(player.getUniqueId()));
						this.instance.getStats().getCurrentAge().put(player.getUniqueId(), 1);

						this.playerStats.getNbTour().put(player.getUniqueId(), 2);

						player.sendTitle("§e2ème tour", null, 10, 20, 10);
						Remain.removeBossbar(player);
						Remain.sendBossbarPercent(player, "§a§lPréhistoire",100, CompBarColor.GREEN, CompBarStyle.SOLID);
						player.sendMessage(AgesRiver.PREFIX + "§aVous entamez le tour §e2 §a! Tour §e1§a complété en §e" + AgesRiver.getInstance().getTaskGame().secondsToMinutes(AgesRiver.getInstance().getTaskGame().getTimer()));
						break;

					case 2:

						AgesRiver.getInstance().getList().setPlayerPassenger(player, new Location(Bukkit.getWorld("Ages_River"), -196.0, 36.0, -67.0, 90.0f, 0.0f));

						this.playerStats.getSeg6().put(player.getUniqueId(), AgesRiver.getInstance().getTaskGame().getTimer()
								- this.playerStats.getSeg1().get(player.getUniqueId())
								- this.playerStats.getSeg2().get(player.getUniqueId())
								- this.playerStats.getSeg3().get(player.getUniqueId())
								- this.playerStats.getSeg4().get(player.getUniqueId())
								- this.playerStats.getSeg5().get(player.getUniqueId()));
						this.instance.getStats().getCurrentAge().put(player.getUniqueId(), 1);

						this.playerStats.getNbTour().put(player.getUniqueId(), 3);

						int time = AgesRiver.getInstance().getTaskGame().getTimer()
								- this.playerStats.getSeg1().get(player.getUniqueId())
								- this.playerStats.getSeg2().get(player.getUniqueId())
								- this.playerStats.getSeg3().get(player.getUniqueId());

						player.sendTitle("§cDernier tour", null, 10, 20, 10);
						Remain.removeBossbar(player);
						Remain.sendBossbarPercent(player, "§a§lPréhistoire",100, CompBarColor.GREEN, CompBarStyle.SOLID);
						player.sendMessage(AgesRiver.PREFIX + "§aVous entamez le tour §e3 §a! Tour §e2§a complété en §e" + AgesRiver.getInstance().getTaskGame().secondsToMinutes(time));
						break;

					case 3:

						AgesRiver.getInstance().getFinished().put(player.getUniqueId(), AgesRiver.getInstance().getFinished().size() + 1);
						int size = AgesRiver.getInstance().getFinished().size();
						this.playerStats.getSeg9().put(player.getUniqueId(), AgesRiver.getInstance().getTaskGame().getTimer()
								- this.playerStats.getSeg1().get(player.getUniqueId())
								- this.playerStats.getSeg2().get(player.getUniqueId())
								- this.playerStats.getSeg3().get(player.getUniqueId())
								- this.playerStats.getSeg4().get(player.getUniqueId())
								- this.playerStats.getSeg5().get(player.getUniqueId())
								- this.playerStats.getSeg6().get(player.getUniqueId())
								- this.playerStats.getSeg7().get(player.getUniqueId())
								- this.playerStats.getSeg8().get(player.getUniqueId()));

						Remain.removeBossbar(player);
						AgesRiver.getInstance().getStats().getCurrentAge().remove(player.getUniqueId());

						for (Player p : AgesRiver.getInstance().getList().getPlayers()) {
							AgesRiver.getInstance().getGameScoreboard().addToScoreBoard(p);

						}

						player.teleport(new Location(Bukkit.getWorld("Ages_River"), -9051.0, 101.0, -19941.0, 90.0f, 0.0f));

						AgesRiver.getInstance().getStats().getFinalTime().put(player.getUniqueId(), AgesRiver.getInstance().getTaskGame().getTimer());
						Bukkit.broadcastMessage(AgesRiver.PREFIX + "§e " + player.getName() + "§6 a complété la course en position §en°" + size + "§6. §e[" + AgesRiver.getInstance().getTaskGame().toString() + "]");
						player.setAllowFlight(true);
						player.setFlying(true);
						player.setInvisible(true);

						giveCompass(player);

						player.sendMessage("");
						player.sendMessage(AgesRiver.PREFIX + "§eEn attendant la fin de la course, vous avez la possibilité de regarder les autres joueurs la finir grâce à la §bboussole "
								+ "§eou bien regarder les détails de votre course via §b/details§e. Fly activé. Attendez la fin de la course pour obtenir les §brécompenses§e et voir le §bclassement§e!");
						player.sendMessage("");

						checkEnd();


						break;
				}
			}
		}
	}

	private static void giveCompass(Player player) {
		player.getInventory().clear();

		ItemStack compass = new ItemStack(Material.COMPASS, 1);
		ItemMeta meta = compass.getItemMeta();

		meta.setDisplayName("§b§lBoussole de TP");
		meta.setLore(Arrays.asList("", "§eClic droit sur la boussole", "§epour ouvrir le menu de", "§etéléportation."));
		compass.setItemMeta(meta);

		player.getInventory().setItem(4, compass);
		player.updateInventory();

	}

	public static void giveStick(Player player)
	{
		player.getInventory().clear();

		ItemStack stick = new ItemStack(Material.STICK, 1);
		ItemMeta meta = stick.getItemMeta();

		meta.setDisplayName("§b§lTraining stick");
		meta.setLore(Arrays.asList("", "§eCe bâton te permet d'aller", "§et'entraîner sur les différentes", "§eépoques"));
		stick.setItemMeta(meta);

		player.getInventory().setItem(4, stick);
		player.updateInventory();
	}


	@EventHandler
	public void onCollision(VehicleEntityCollisionEvent event) {
		if (event.getEntity() instanceof Boat && event.getVehicle() instanceof Boat) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onDrop(PlayerDropItemEvent event) {
		if (event.getItemDrop().getItemStack().getItemMeta().getDisplayName().equals("§b§lBoussole de TP")
			||event.getItemDrop().getItemStack().getItemMeta().getDisplayName().equals("§b§lTraining stick")) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (event.getItem() == null) return;

			if(AgesRiver.getInstance().getState().getMode() == Mode.GAME)
			{
				event.getPlayer().sendMessage(AgesRiver.PREFIX + "§cLa map n'est pas ouverte à l'entraînement pour le moment!");
			}

			InventoryPlayers invPlayers = new InventoryPlayers();

			if (event.getItem().getItemMeta().getDisplayName().equals("§b§lBoussole de TP")) {

				invPlayers.buildHeads();
				invPlayers.openInventory(event.getPlayer());

			} else if (event.getItem().getItemMeta().getDisplayName().equals("§b§lTraining stick"))
			{
				invPlayers.openTrainingInventory(event.getPlayer());
			}
		}
	}

	private void checkEnd() {
		if (AgesRiver.getInstance().getFinished().size() == AgesRiver.getInstance().getList().getPlayers().size()) {
			AgesRiver.getInstance().getTaskGame().cancel();
			AgesRiver.getInstance().getState().setStatistique(StateGame.FIN);

			for (Entry<UUID, Integer> entry : AgesRiver.getInstance().getFinished().entrySet()) {
				if (entry.getValue() == 1) {
					this.player1 = Bukkit.getPlayer(entry.getKey());
				} else if (entry.getValue() == 2) {
					this.player2 = Bukkit.getPlayer(entry.getKey());
				} else if (entry.getValue() == 3) {
					this.player3 = Bukkit.getPlayer(entry.getKey());
				} else if (entry.getValue() == 4) {
					this.player4 = Bukkit.getPlayer(entry.getKey());
				} else if (entry.getValue() == 5) {
					this.player5 = Bukkit.getPlayer(entry.getKey());
				} else if (entry.getValue() == 6) {
					this.player6 = Bukkit.getPlayer(entry.getKey());
				} else if (entry.getValue() == 7) {
					this.player7 = Bukkit.getPlayer(entry.getKey());
				} else if (entry.getValue() == 8) {
					this.player8 = Bukkit.getPlayer(entry.getKey());
				} else if (entry.getValue() == 9) {
					this.player9 = Bukkit.getPlayer(entry.getKey());
				}
			}

			for (Player player : AgesRiver.getInstance().getList().getPlayers()) {

				int finalTime = AgesRiver.getInstance().getStats().getFinalTime().get(player.getUniqueId());

				if (!PlayerStats.getGlobal().containsKey(player.getUniqueId()) || PlayerStats.getGlobal().get(player.getUniqueId()) < finalTime) {
					PlayerStats.getGlobal().put(player.getUniqueId(), AgesRiver.getInstance().getStats().getFinalTime().get(player.getUniqueId()));
				}

				AgesRiver.getInstance().getAttenteScoreboard().addToScoreBoard(player);
				player.setAllowFlight(false);
				player.setInvisible(false);
				player.getInventory().clear();
				player.setGameMode(GameMode.ADVENTURE);
				player.teleport(new Location(Bukkit.getWorld("Ages_River"), -105.0, 33.0, -64.0, 90.0f, 0.0f));

				if (!(player1 == null)) {
					player1.teleport(new Location(Worlds.getMainWorld(), -127.0, 39.0, -56.0, 180.0f, 0.0f));
				}

				if (!(player2 == null)) {
					player2.teleport(new Location(Worlds.getMainWorld(), -124.0, 38.0, -57.0, 180.0f, 0.0f));
				}

				if (!(player3 == null)) {
					player3.teleport(new Location(Worlds.getMainWorld(), -130.0, 37.0, -57.0, 180.0f, 0.0f));
				}

				PlayerStats.sortByValues();
				HologramManager.loadClassementHologram(player);

				String p3 = (this.player3 == null) ? "null" : player3.getName();
				String t3 = (this.player3 == null) ? "?'??" : TaskGame.secondsToMinutes(AgesRiver.getInstance().getStats().getFinalTime().get(player3.getUniqueId()));

				player.sendMessage("");
				player.sendMessage("§8§m--------------" + AgesRiver.PREFIX + "§r§8§m---------------");
				player.sendMessage("");
				player.sendMessage("               §e§lClassement:                   ");
				player.sendMessage("           ");
				player.sendMessage("           §6§l1er: §r" + this.player1.getName() + " §b[" + TaskGame.secondsToMinutes(AgesRiver.getInstance().getStats().getFinalTime().get(player1.getUniqueId())) + "]");
				player.sendMessage("           §7§l2e: §r" + this.player2.getName() + " §b[" + TaskGame.secondsToMinutes(AgesRiver.getInstance().getStats().getFinalTime().get(player2.getUniqueId())) + "]");
				player.sendMessage("           §c§l3e: §r" + p3 + " §b[" + t3 + "]");
				player.sendMessage("");
				player.sendMessage("               §6§lGG à eux!               ");
				player.sendMessage("§8§m----------------------------------------");
				player.sendMessage("");
			}


			AgesRiver.getInstance().getProfileManager().addPoint(player1);
			AgesRiver.getInstance().getProfileManager().addTrophy(player1, "or", 5);
			AgesRiver.getInstance().getProfileManager().addTrophy(player2, "or", 3);

			if(player3 != null) {
				AgesRiver.getInstance().getProfileManager().addTrophy(player3, "or", 1);
			}

			if(player4 != null) {
				AgesRiver.getInstance().getProfileManager().addTrophy(player4, "argent", 5);
			}
			if(player5 != null) {
				AgesRiver.getInstance().getProfileManager().addTrophy(player5, "argent", 3);
			}
			if(player6 != null) {
				AgesRiver.getInstance().getProfileManager().addTrophy(player6, "argent", 1);
			}

			if(player7 != null) {
				AgesRiver.getInstance().getProfileManager().addTrophy(player7, "bronze", 5);
			}
			if(player8 != null) {
				AgesRiver.getInstance().getProfileManager().addTrophy(player8, "bronze", 3);
			}
			if(player9 != null) {
				AgesRiver.getInstance().getProfileManager().addTrophy(player9, "bronze", 1);
			}

			new BukkitRunnable() {
				int timer = 0;

				@Override
				public void run() {
					if (timer <= 10) {
						Location loc = player1.getLocation();

						Firework firework = loc.getWorld().spawn(loc, Firework.class);
						FireworkMeta fireworkMeta = firework.getFireworkMeta();

						FireworkEffect effect = FireworkEffect.builder()
								.withColor((Iterable<?>) Color.RED)
								.withFade((Iterable<?>) Color.YELLOW)
								.with(FireworkEffect.Type.BALL)
								.trail(true)
								.build();
						fireworkMeta.addEffect(effect);

						firework.setFireworkMeta(fireworkMeta);
						firework.detonate();
					}

					if (timer == 30) {
						for (Player player : AgesRiver.getInstance().getList().getPlayers()) {
							player.kickPlayer("§bMerci pour votre participation!");
							AgesRiver.getInstance().getList().getPlayers().clear();
						}

						AgesRiver.getInstance().getStats().getCurrentAge().clear();
						AgesRiver.getInstance().getStats().getFinalTime().clear();
						AgesRiver.getInstance().getStats().getSeg1().clear();
						AgesRiver.getInstance().getStats().getNbTour().clear();
						AgesRiver.getInstance().getStats().getSeg2().clear();
						AgesRiver.getInstance().getStats().getSeg3().clear();
						AgesRiver.getInstance().getStats().getSeg4().clear();
						AgesRiver.getInstance().getStats().getSeg5().clear();
						AgesRiver.getInstance().getStats().getSeg6().clear();
						AgesRiver.getInstance().getStats().getSeg7().clear();
						AgesRiver.getInstance().getStats().getSeg8().clear();
						AgesRiver.getInstance().getStats().getSeg9().clear();

						AgesRiver.getInstance().getState().setStatistique(StateGame.ATTENTE);


						cancel();
					}

					timer++;

				}

			}.runTaskTimer(AgesRiver.getInstance(), 20L, 0L);

			for(Player player : file)
			{
				this.joinPlayer(player);
			}
		}

	}

}

package fr.justop.listeners;

import fr.justop.AgesRiver;
import fr.justop.enums.StateGame;
import fr.justop.enums.StateManager;
import fr.justop.guis.InventoryPlayers;
import fr.justop.holograms.HologramManager;
import fr.justop.players.ListPlayers;
import fr.justop.players.PlayerStats;
import fr.justop.tasks.TaskCommencement;
import fr.justop.tasks.TaskGame;
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

import java.util.Arrays;
import java.util.Map.Entry;
import java.util.UUID;

public class Game implements Listener {
	private AgesRiver instance;
	private TaskCommencement task;
	private PlayerStats playerStats;
	private Player player1;
	private Player player2;
	private Player player3;
	private Player player4;
	private Player player5;
	private Player player6;
	private Player player7;
	private Player player8;
	private Player player9;

	private Location locationBlock1;
	private Location locationBlock2;
	private Location locationBlock3;
	private Location locationBlock4;
	private Location locationBlock5;
	private Location locationBlock6;
	private Location locationBlock7;
	private Location locationBlock8;
	private Location locationBlock9;
	private Location locationBlock10;
	private Location locationBlock11;
	private Location locationBlock12;
	private Location locationBlock13;
	private Location locationBlock14;
	private Location locationBlock15;
	private Location locationBlock16;
	private Location locationBlock17;
	private Location locationBlock18;
	private Location locationBlock19;
	private Location locationBlock20;
	private Location locationBlock21;
	private Location locationBlock22;
	private Location locationBlock23;
	private Location locationBlock24;
	private Location locationBlock25;
	private Location locationBlock26;
	private Location locationBlock27;
	private Location locationBlock28;
	private Location locationBlock29;
	private Location locationBlock30;
	private Location locationBlock31;
	private Location locationBlock32;
	private Location locationBlock33;
	private Location locationBlock34;
	private Location locationBlock35;
	private Location locationBlock36;
	private Location locationBlock37;
	private Location locationBlock38;
	private Location locationBlock39;
	private Location locationBlock40;
	private Location locationBlock41;
	private Location locationBlock42;
	private Location locationBlock43;
	private Location locationBlock44;
	private Location locationBlock46;
	private Location locationBlock45;
	private Location locationBlock47;
	private Location locationBlock48;
	private Location locationBlock49;
	private Location locationBlock50;

	public Game(AgesRiver main) {
		this.instance = main;
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		StateManager game = this.instance.getState();

		if (player.hasPermission("hycraft.staff")) {
			player.teleport(new Location(Bukkit.getWorld("Ages_River"), 0.0, 0.0, 0.0, 90.0F, 0.0F));
			player.setGameMode(GameMode.CREATIVE);
			player.sendMessage("");
			player.sendMessage(AgesRiver.PREFIX + "§eBonjour à toi cher membre du staff §b" + player.getDisplayName() + "§e. " +
					"D'office, tu es exclu de la file d'attente pour pouvoir vaquer à tes occupations. Mais si tu" +
					" souhaites rejoindre les autres participants, utilise §b/boat-staff join §eet au contraire §b" +
					"/boat-staff leave §epour la quitter. Tu as également la permission d'ajouter des trophées ou à" +
					" en soustraire au joueur de to choix. Pour cela utilise§b /boat-trophy (add/remove) (or/argent/bronze)" +
					" (joueur) (nombre)");
			player.sendMessage("");

			for (Player p : Bukkit.getOnlinePlayers()) {
				if (p.isOp()) {
					p.sendMessage(AgesRiver.PREFIX + "§4" + player.getName() + "§e vient de se connecter");
				}
			}

			this.InitializePositions();
			event.setJoinMessage(null);
			return;
		}


		player.teleport(new Location(Bukkit.getWorld("Ages_River"), -105.0D, 33.0D, -64.0D, 90.0F, 0.0F));
		player.setAllowFlight(false);
		player.setGameMode(GameMode.ADVENTURE);
		player.setHealth(20D);
		player.setFoodLevel(Integer.MAX_VALUE);
		player.getInventory().clear();

		HologramManager.loadClassementHologram(player);

		if (!(game.getStatistique() == StateGame.ATTENTE)) {
			player.setGameMode(GameMode.SPECTATOR);
			player.sendMessage(AgesRiver.PREFIX + "§7Le jeu a déja commencé, veuillez patienter la prochaine partie");
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

		ListPlayers playersList = this.instance.getList();

		if (game.getStatistique() == StateGame.ATTENTE && playersList.getPlayers().size() <= this.instance.getSpawnManager().getSpawnsList().size()) {
			if (!playersList.getPlayers().contains(player)) {
				playersList.getPlayers().add(player);

				for (Player p : Bukkit.getOnlinePlayers()) {
					this.instance.getAttenteScoreboard().addToScoreBoard(p);
				}
			}

			Bukkit.broadcastMessage(AgesRiver.PREFIX + "§e " + player.getName() + "§c a rejoint la partie ! §4<" + playersList.getPlayers().size() + "/" + this.instance.getSpawnManager().getSpawnsList().size() + ">");
		}

		if (playersList.getPlayers().size() == this.instance.getSpawnManager().getSpawnsList().size()) {
			startGame();
		}

	}

	@EventHandler
	public void onDamage(EntityDamageEvent event) {
		if (!(event.getEntity() instanceof Player)) return;
		event.setCancelled(true);
	}


	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		ListPlayers playersList = this.instance.getList();

		if (player.isOp()) {
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

	public void startGame() {
		ListPlayers playersList = AgesRiver.getInstance().getList();
		StateManager game = AgesRiver.getInstance().getState();

		if (game.getStatistique() == StateGame.ATTENTE && playersList.getPlayers().size() >= 2) {
			this.task = new TaskCommencement();
			game.setStatistique(StateGame.COMMENCEMENT);
			this.InitializePositions();
			this.playerStats = AgesRiver.getInstance().getStats();
			AgesRiver.getInstance().iniStats();
			task.runTaskTimer(AgesRiver.getInstance(), 0L, 20L);


		}
	}

	public TaskCommencement getTask() {
		return this.task;
	}

	public void InitializePositions() {
		this.locationBlock1 = new Location(Bukkit.getWorld("Ages_River"), -349.0, 52.0, -209.0, 0.0f, 0.0f);
		this.locationBlock2 = new Location(Bukkit.getWorld("Ages_River"), -348.0, 52.0, -209.0, 0.0f, 0.0f);
		this.locationBlock3 = new Location(Bukkit.getWorld("Ages_River"), -348.0, 52.0, -208.0, 0.0f, 0.0f);
		this.locationBlock4 = new Location(Bukkit.getWorld("Ages_River"), -347.0, 52.0, -208.0, 0.0f, 0.0f);
		this.locationBlock5 = new Location(Bukkit.getWorld("Ages_River"), -347.0, 52.0, -207.0, 0.0f, 0.0f);
		this.locationBlock6 = new Location(Bukkit.getWorld("Ages_River"), -346.0, 52.0, -207.0, 0.0f, 0.0f);
		this.locationBlock7 = new Location(Bukkit.getWorld("Ages_River"), -346.0, 52.0, -206.0, 0.0f, 0.0f);
		this.locationBlock8 = new Location(Bukkit.getWorld("Ages_River"), -345.0, 52.0, -206.0, 0.0f, 0.0f);
		this.locationBlock9 = new Location(Bukkit.getWorld("Ages_River"), -345.0, 52.0, -205.0, 0.0f, 0.0f);
		this.locationBlock10 = new Location(Bukkit.getWorld("Ages_River"), -344.0, 52.0, -205.0, 0.0f, 0.0f);
		this.locationBlock11 = new Location(Bukkit.getWorld("Ages_River"), -344.0, 52.0, -204.0, 0.0f, 0.0f);
		this.locationBlock12 = new Location(Bukkit.getWorld("Ages_River"), -343.0, 52.0, -204.0, 0.0f, 0.0f);
		this.locationBlock13 = new Location(Bukkit.getWorld("Ages_River"), -343.0, 52.0, -203.0, 0.0f, 0.0f);
		this.locationBlock14 = new Location(Bukkit.getWorld("Ages_River"), -342.0, 52.0, -203.0, 0.0f, 0.0f);

		this.locationBlock15 = new Location(Bukkit.getWorld("Ages_River"), -902.0, 0.0, -9995.0, 0.0f, 0.0f);
		this.locationBlock16 = new Location(Bukkit.getWorld("Ages_River"), -903.0, 0.0, -9995.0, 0.0f, 0.0f);
		this.locationBlock17 = new Location(Bukkit.getWorld("Ages_River"), -904.0, 0.0, -9995.0, 0.0f, 0.0f);
		this.locationBlock18 = new Location(Bukkit.getWorld("Ages_River"), -905.0, 0.0, -9995.0, 0.0f, 0.0f);
		this.locationBlock19 = new Location(Bukkit.getWorld("Ages_River"), -906.0, 0.0, -9995.0, 0.0f, 0.0f);
		this.locationBlock20 = new Location(Bukkit.getWorld("Ages_River"), -907.0, 0.0, -9995.0, 0.0f, 0.0f);
		this.locationBlock21 = new Location(Bukkit.getWorld("Ages_River"), -908.0, 0.0, -9995.0, 0.0f, 0.0f);
		this.locationBlock22 = new Location(Bukkit.getWorld("Ages_River"), -909.0, 0.0, -9995.0, 0.0f, 0.0f);
		this.locationBlock23 = new Location(Bukkit.getWorld("Ages_River"), -910.0, 0.0, -9995.0, 0.0f, 0.0f);
		this.locationBlock24 = new Location(Bukkit.getWorld("Ages_River"), -911.0, 0.0, -9995.0, 0.0f, 0.0f);
		this.locationBlock25 = new Location(Bukkit.getWorld("Ages_River"), -912.0, 0.0, -9995.0, 0.0f, 0.0f);
		this.locationBlock26 = new Location(Bukkit.getWorld("Ages_River"), -913.0, 0.0, -9995.0, 0.0f, 0.0f);

		this.locationBlock27 = new Location(Bukkit.getWorld("Ages_River"), -8832.0, 53.0, -19929.0, 0.0f, 0.0f);
		this.locationBlock28 = new Location(Bukkit.getWorld("Ages_River"), -8832.0, 53.0, -19930.0, 0.0f, 0.0f);
		this.locationBlock29 = new Location(Bukkit.getWorld("Ages_River"), -8832.0, 53.0, -19931.0, 0.0f, 0.0f);
		this.locationBlock30 = new Location(Bukkit.getWorld("Ages_River"), -8832.0, 53.0, -19932.0, 0.0f, 0.0f);
		this.locationBlock31 = new Location(Bukkit.getWorld("Ages_River"), -8832.0, 53.0, -19933.0, 0.0f, 0.0f);
		this.locationBlock32 = new Location(Bukkit.getWorld("Ages_River"), -8832.0, 53.0, -19934.0, 0.0f, 0.0f);
		this.locationBlock33 = new Location(Bukkit.getWorld("Ages_River"), -8832.0, 53.0, -19935.0, 0.0f, 0.0f);
		this.locationBlock34 = new Location(Bukkit.getWorld("Ages_River"), -8832.0, 53.0, -19936.0, 0.0f, 0.0f);
		this.locationBlock35 = new Location(Bukkit.getWorld("Ages_River"), -8832.0, 53.0, -19937.0, 0.0f, 0.0f);
		this.locationBlock36 = new Location(Bukkit.getWorld("Ages_River"), -8832.0, 53.0, -19938.0, 0.0f, 0.0f);
		this.locationBlock37 = new Location(Bukkit.getWorld("Ages_River"), -8832.0, 53.0, -19939.0, 0.0f, 0.0f);
		this.locationBlock38 = new Location(Bukkit.getWorld("Ages_River"), -8832.0, 53.0, -19940.0, 0.0f, 0.0f);
		this.locationBlock39 = new Location(Bukkit.getWorld("Ages_River"), -8832.0, 53.0, -19941.0, 0.0f, 0.0f);
		this.locationBlock40 = new Location(Bukkit.getWorld("Ages_River"), -8832.0, 53.0, -19942.0, 0.0f, 0.0f);
		this.locationBlock41 = new Location(Bukkit.getWorld("Ages_River"), -8832.0, 53.0, -19943.0, 0.0f, 0.0f);
		this.locationBlock42 = new Location(Bukkit.getWorld("Ages_River"), -8832.0, 53.0, -19944.0, 0.0f, 0.0f);
		this.locationBlock43 = new Location(Bukkit.getWorld("Ages_River"), -8832.0, 53.0, -19945.0, 0.0f, 0.0f);
		this.locationBlock44 = new Location(Bukkit.getWorld("Ages_River"), -8832.0, 53.0, -19946.0, 0.0f, 0.0f);
		this.locationBlock45 = new Location(Bukkit.getWorld("Ages_River"), -8832.0, 53.0, -19947.0, 0.0f, 0.0f);
		this.locationBlock46 = new Location(Bukkit.getWorld("Ages_River"), -8832.0, 53.0, -19948.0, 0.0f, 0.0f);
		this.locationBlock47 = new Location(Bukkit.getWorld("Ages_River"), -8832.0, 53.0, -19949.0, 0.0f, 0.0f);
		this.locationBlock48 = new Location(Bukkit.getWorld("Ages_River"), -8832.0, 53.0, -19950.0, 0.0f, 0.0f);
		this.locationBlock49 = new Location(Bukkit.getWorld("Ages_River"), -8832.0, 53.0, -19951.0, 0.0f, 0.0f);
		this.locationBlock50 = new Location(Bukkit.getWorld("Ages_River"), -8832.0, 53.0, -19952.0, 0.0f, 0.0f);
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
				Bukkit.getConsoleSender().sendMessage("§aOK");

				AgesRiver.getInstance().getList().setPlayerPassenger(player, new Location(Bukkit.getWorld("Ages_River"), -888.0, 55.0, -9515.0, 180.0f, 0.0f));
				player.sendMessage(AgesRiver.PREFIX + "§aVous entrez en Antiquité!");
				boat.remove();

				player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 1.0f, 1.0f);
				AgesRiver.getInstance().getBossBars().removePlayer(player);
				AgesRiver.getInstance().getBossBars().addPlayer(player, 2);
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
				AgesRiver.getInstance().getBossBars().removePlayer(player);
				AgesRiver.getInstance().getBossBars().addPlayer(player, 3);
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
					locationTo.equals(locationBlock50)) {
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
						AgesRiver.getInstance().getBossBars().removePlayer(player);
						AgesRiver.getInstance().getBossBars().addPlayer(player, 1);
						player.sendMessage(AgesRiver.PREFIX + "§aVous entamez le tour §e2§a! Tour §e1§a complété en §e" + AgesRiver.getInstance().getTaskGame().secondsToMinutes(AgesRiver.getInstance().getTaskGame().getTimer()));
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
						AgesRiver.getInstance().getBossBars().removePlayer(player);
						AgesRiver.getInstance().getBossBars().addPlayer(player, 1);
						player.sendMessage(AgesRiver.PREFIX + "§aVous entamez le tour §e3§a! Tour §e2§a complété en §e" + AgesRiver.getInstance().getTaskGame().secondsToMinutes(time));
						break;

					case 3:

						AgesRiver.getInstance().getFinished().put(player.getUniqueId(), AgesRiver.getInstance().getFinished().size() + 1);
						int size = AgesRiver.getInstance().getFinished().size();
						this.playerStats.getSeg9().put(player.getUniqueId(), AgesRiver.getInstance().getTaskGame().getTimer());

						AgesRiver.getInstance().getBossBars().removePlayer(player);
						AgesRiver.getInstance().getStats().getCurrentAge().remove(player.getUniqueId());

						for (Player p : AgesRiver.getInstance().getList().getPlayers()) {
							AgesRiver.getInstance().getGameScoreboard().addToScoreBoard(p);

						}

						player.teleport(new Location(Bukkit.getWorld("Ages_River"), -9051.0, 101.0, -19941.0, 90.0f, 0.0f));

						AgesRiver.getInstance().getStats().getFinalTime().put(player.getUniqueId(), AgesRiver.getInstance().getTaskGame().getTimer());
						Bukkit.broadcastMessage(AgesRiver.PREFIX + "§e " + player.getName() + "§6 a complété la course en position §en°" + size + "§6. §e[" + AgesRiver.getInstance().getTaskGame().toString() + "]");
						player.setAllowFlight(true);
						player.setInvisible(true);

						giveCompass(player);

						player.sendMessage("");
						player.sendMessage(AgesRiver.PREFIX + "§eEn attendant la fin de la course, vous avez la possibilité de regarder les autres joueurs la finir grâce à la §bboussole "
								+ "§eou bien regarder les détails de votre course via §b/details§e. Fly activé. Attendez la fin de la course pour obtenir les §brécompenses§e et voir le §bclassement§e!");
						player.sendMessage("");

						checkEnd();


						break;
				}

				return;
			}
		}
	}

	private void giveCompass(Player player) {
		player.getInventory().clear();

		ItemStack compass = new ItemStack(Material.COMPASS, 1);
		ItemMeta meta = compass.getItemMeta();

		meta.setDisplayName("§b§lBoussole de TP");
		meta.setLore(Arrays.asList("", "§eClic droit sur la boussole", "§epour ouvrir le menu de", "§etéléportation."));
		compass.setItemMeta(meta);

		player.getInventory().setItem(4, compass);
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
		if (event.getItemDrop().getItemStack().getItemMeta().getDisplayName().equals("§b§lBoussole de TP")) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (event.getItem() == null) return;

			if (event.getItem().getItemMeta().getDisplayName().equals("§b§lBoussole de TP")) {
				InventoryPlayers invPlayers = new InventoryPlayers();

				invPlayers.buildHeads();
				invPlayers.openInventory(event.getPlayer());
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
				PlayerStats.getGlobal().put(player.getUniqueId(), AgesRiver.getInstance().getStats().getFinalTime().get(player.getUniqueId()));
				AgesRiver.getInstance().getAttenteScoreboard().addToScoreBoard(player);
				player.setAllowFlight(false);
				player.setInvisible(false);
				player.getInventory().clear();
				player.setGameMode(GameMode.ADVENTURE);
				player.teleport(new Location(Bukkit.getWorld("Ages_River"), -105.0, 33.0, -64.0, 90.0f, 0.0f));

				String p3 = (this.player3 == null) ? "null" : player3.getName();
				String t3 = (this.player3 == null) ? "?'??" : TaskGame.secondsToMinutes(AgesRiver.getInstance().getStats().getFinalTime().get(player3.getUniqueId())) + "]";

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

			PlayerStats.sortByValues();


			AgesRiver.getInstance().getProfileManager().addPoint(player1);
			AgesRiver.getInstance().getProfileManager().addTrophy(player1, "or", 5);
			AgesRiver.getInstance().getProfileManager().addTrophy(player2, "or", 3);
			AgesRiver.getInstance().getProfileManager().addTrophy(player3, "or", 1);

			AgesRiver.getInstance().getProfileManager().addTrophy(player4, "argent", 5);
			AgesRiver.getInstance().getProfileManager().addTrophy(player5, "argent", 3);
			AgesRiver.getInstance().getProfileManager().addTrophy(player6, "argent", 1);

			AgesRiver.getInstance().getProfileManager().addTrophy(player7, "bronze", 5);
			AgesRiver.getInstance().getProfileManager().addTrophy(player8, "bronze", 3);
			AgesRiver.getInstance().getProfileManager().addTrophy(player9, "bronze", 1);

			new BukkitRunnable() {
				int timer = 0;

				@Override
				public void run() {
					if (timer <= 10) {
						Location loc = player1.getLocation();

						Firework firework = loc.getWorld().spawn(loc, Firework.class);
						FireworkMeta fireworkMeta = firework.getFireworkMeta();

						FireworkEffect effect = FireworkEffect.builder()
								.withColor(Color.RED)
								.withFade(Color.YELLOW)
								.with(FireworkEffect.Type.BALL)
								.trail(true)
								.build();
						fireworkMeta.addEffect(effect);

						firework.setFireworkMeta(fireworkMeta);
						firework.detonate();
					}

					if (timer == 20) {
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

		}

	}

}

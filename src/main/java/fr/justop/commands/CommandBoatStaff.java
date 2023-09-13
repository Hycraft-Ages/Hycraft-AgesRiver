package fr.justop.commands;

import fr.justop.AgesRiver;
import fr.justop.enums.Mode;
import fr.justop.enums.StateGame;
import fr.justop.enums.StateManager;
import fr.justop.listeners.Game;
import fr.justop.players.ListPlayers;
import fr.justop.players.Profile;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CommandBoatStaff implements CommandExecutor, TabCompleter {

	private AgesRiver instance;
	private StateManager game;

	public CommandBoatStaff(AgesRiver main, StateManager game) {
		this.game = game;
		this.instance = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			Bukkit.getServer().getConsoleSender().sendMessage("§cSeul un joueur peur effectuer cette commande");
			return false;
		}

		Player player = (Player) sender;

		if (command.getName().equalsIgnoreCase("boat-staff")) {
			if (player.hasPermission("hycraft.boatrace.staff")) {
				ListPlayers list = this.instance.getList();

				switch (args.length) {
					case 0:

						player.sendMessage("§8§m-------§r§8[ " + AgesRiver.PREFIX + "§8]§r§8§m------");
						player.sendMessage("");
						player.sendMessage("§e -§6§l/boat-staff force-start §8- §ePermet de forcer le démarrage de la partie");
						player.sendMessage("§e  §7(Si §a2 §ejoueurs sont connecter)");
						player.sendMessage("§e -§6§l/boat-staff leave §8- §ePermet de quitter la file d'attente pour build");
						player.sendMessage("§e -§6§l/boat-staff join §8- §ePermet de rejoindre à nouveau la partit après avoir utiliser la commande §7/leave");
						player.sendMessage("§e -§6§l/staff addOr <Joueur> <Quantité> §8- §eFait gagner un trophée en or au joueur évoqué");
						player.sendMessage("§e -§6§l/staff addArgent <Joueur> <Quantité> §8- §eFait gagner une médaille en argent au joueur évoqué");
						player.sendMessage("§e -§6§l/staff addBronze <Joueur> <Quantité> §8- §eFait gagner une médaille de bronze au joueur évoqué");
						player.sendMessage("");
						player.sendMessage("§8§m----------------------------------------------");
						break;

					case 1:
						if (args[0].equalsIgnoreCase("state")) {
							player.sendMessage(AgesRiver.PREFIX + "State: " + game.getEtat());
							return true;
						}

						if (args[0].equalsIgnoreCase("addSpawn")) {
							YamlConfiguration configuration = this.instance.getSpawnManager().getSpawnConfig();

							Location location = player.getLocation();
							this.instance.getSpawnManager().getSpawnsList().add(location);

							configuration.set("spawns.spawn_0" + this.instance.getSpawnManager().getSpawnsList().size() + ".locX", location.getX());
							configuration.set("spawns.spawn_0" + this.instance.getSpawnManager().getSpawnsList().size() + ".locY", location.getY());
							configuration.set("spawns.spawn_0" + this.instance.getSpawnManager().getSpawnsList().size() + ".locZ", location.getZ());
							configuration.set("spawns.spawn_0" + this.instance.getSpawnManager().getSpawnsList().size() + ".yaw", location.getYaw());
							configuration.set("spawns.spawn_0" + this.instance.getSpawnManager().getSpawnsList().size() + ".pitch", location.getPitch());

							this.instance.getSpawnManager().saveConfig();

							player.sendMessage(AgesRiver.PREFIX + "§aVous avez ajouté un spawn de bateau avec succès!");
						}

						if (args[0].equalsIgnoreCase("force-start")) {
							if (!(game.getStatistique() == StateGame.ATTENTE) || !(list.getPlayers().size() >= 2)) {
								player.sendMessage(AgesRiver.PREFIX + "§cVous devez être au minimum 2 joueurs pour commencer une partie !");
								return false;
							}
							Game games = new Game(this.instance);
							games.startGame();
							return true;
						}

						if (args[0].equalsIgnoreCase("leave")) {
							player.setGameMode(GameMode.CREATIVE);
							if (list.getPlayers().contains(player)) {
								list.getPlayers().remove(player);

								for (Player p : Bukkit.getOnlinePlayers()) {
									this.instance.getAttenteScoreboard().addToScoreBoard(p);
								}
							}
							Bukkit.broadcastMessage(AgesRiver.PREFIX + "§e " + player.getName() + "§c a quitté la partie ! §4<" + list.getPlayers().size() + "/" + this.instance.getSpawnManager().getSpawnsList().size() + ">");
							return true;
						}

						if (args[0].equalsIgnoreCase("join")) {
							if (!(game.getStatistique() == StateGame.ATTENTE)) {
								player.sendMessage(AgesRiver.PREFIX + "§cLe jeu a déjà commencé, veuillez patienter pour la prochaine partie!");
								return false;
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

							if (!list.getPlayers().contains(player)) {
								list.getPlayers().add(player);

								for (Player p : Bukkit.getOnlinePlayers()) {
									this.instance.getAttenteScoreboard().addToScoreBoard(p);
								}
							}

							player.setGameMode(GameMode.ADVENTURE);
							player.teleport(new Location(Bukkit.getWorld("Ages_River"), -105.0, 33.0, -64.0, 90.0f, 0.0f));
							player.setHealth(20D);
							player.setFoodLevel(Integer.MAX_VALUE);
							player.getInventory().clear();
							Bukkit.broadcastMessage(AgesRiver.PREFIX + "§e " + player.getName() + "§c a rejoint la partie ! §4<" + list.getPlayers().size() + "/" + this.instance.getSpawnManager().getSpawnsList().size() + ">");
							return true;
						}

						if (args[0].equalsIgnoreCase("reload")) {
							Bukkit.getPluginManager().disablePlugin(this.instance);
							Bukkit.getPluginManager().enablePlugin(this.instance);
							player.sendMessage(AgesRiver.PREFIX + "§aPlugin reload !");
							Bukkit.getServer().getConsoleSender().sendMessage(AgesRiver.PREFIX + "§2" + player.getName() + " §aa relaod le plugin");
							return true;
						}


						break;

					case 2:

						if (args[0].equalsIgnoreCase("profile")) {
							if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(args[1]))) {
								Player cible = Bukkit.getPlayer(args[1]);
								Profile profile = instance.getProfile().get(cible.getUniqueId());

								int golds = profile.getGold();
								int argents = profile.getArgent();
								int bronzes = profile.getBronze();
								int actuel = profile.getWinActuelle();
								int record = profile.getRecordWin();

								player.sendMessage("§8--------------" + AgesRiver.PREFIX + "§r§8----------------");
								player.sendMessage("");
								player.sendMessage("§e -§eSérie de win actuelle: §6§l" + actuel);
								player.sendMessage("§e -§eRecord série de win: §6§l" + record);
								player.sendMessage("§e -§eTrophée(s) en or: §6§l" + golds);
								player.sendMessage("§e -§eMédaille(s) d'argent: §6§l" + argents);
								player.sendMessage("§e -§eMédaille(s) de bronze: §6§l" + bronzes);
								player.sendMessage("");
								player.sendMessage("§8-----------------------------------------");

								break;
							}
						}

						if (args[0].equalsIgnoreCase("switchMode"))
						{
							if(AgesRiver.getInstance().getState().getStatistique() != StateGame.ATTENTE)
							{
								player.sendMessage(AgesRiver.PREFIX + "§cVous ne pouvez pas executer cette action si une partie est lancée ou en cours de lancement!");
								return false;
							}

							switch(args[1])
							{
								case "training":

									if(AgesRiver.getInstance().getState().getMode() == Mode.TRAINING)
									{
										player.sendMessage(AgesRiver.PREFIX + "§cRien n'a changé!");
										return false;
									}

									AgesRiver.getInstance().getState().setMode(Mode.TRAINING);
									AgesRiver.getInstance().getList().getPlayers().clear();

									Bukkit.broadcastMessage(AgesRiver.PREFIX + "§eLe mode est desormais défini sur entraînement. §c<0/16>");

									for(Player playerFromList : Bukkit.getOnlinePlayers())
									{
										AgesRiver.getInstance().getAttenteScoreboard().addToScoreBoard(playerFromList);
									}

									break;

								case "game":

									if(AgesRiver.getInstance().getState().getMode() == Mode.GAME)
									{
										player.sendMessage(AgesRiver.PREFIX + "§cRien n'a changé!");
										return false;
									}

									AgesRiver.getInstance().getState().setMode(Mode.GAME);
									Bukkit.broadcastMessage(AgesRiver.PREFIX + "§eLe mode est desormais défini sur jeu.");

									for(Player playerFromList : Bukkit.getOnlinePlayers())
									{
										if(!playerFromList.hasPermission("hycraft.boatrace.staff"))
										{
											Game.joinPlayer(playerFromList);
										}

									}


									break;
							}
						}

				}
				return true;
			}
			player.sendMessage(AgesRiver.PREFIX + "§cTu n'as pas la permission");
			return false;
		}
		player.sendMessage(AgesRiver.PREFIX + "§9Mauvaise commande. Veuillez taper §a/staff");
		return false;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		ArrayList<String> result = new ArrayList<>();
		String[] VALUES = {"force-start", "leave", "join", "reload", "profile", "state", "switchMode"};

		if (command.getName().equalsIgnoreCase("boat-staff")) {
			switch (args.length) {
				case 1:
					for (String value : VALUES) {
						if (value.toLowerCase().startsWith(args[0].toLowerCase())) result.add(value);
					}
					break;

			}

		}
		return result;
	}

}

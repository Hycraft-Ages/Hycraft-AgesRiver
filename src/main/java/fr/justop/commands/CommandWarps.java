package fr.justop.commands;

import com.google.common.collect.ImmutableList;
import fr.justop.AgesRiver;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class CommandWarps implements CommandExecutor, TabCompleter {

	private AgesRiver instance;

	public CommandWarps(AgesRiver main) {
		this.instance = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		File file = new File(this.instance.getDataFolder(), "Warps.yml");
		FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);

		if (!(sender instanceof Player)) {
			Bukkit.getServer().getConsoleSender().sendMessage(AgesRiver.PREFIX + "§cSeul un joueur peut effectuer cette commande");
			return false;
		}

		Player player = (Player) sender;

		if (command.getName().equalsIgnoreCase("boat-warp")) {
			if (player.hasPermission("hycraft.boatrace.staff")) {
				switch (args.length) {
					case 0:
						player.sendMessage("§8§m▬▬▬▬§r§8[ " + AgesRiver.PREFIX + "§8]§r§8§m▬▬▬▬");
						player.sendMessage("");
						player.sendMessage("§e -§6§l/warp create <nom> §8- §ePermet de créer un warp à ta position");
						player.sendMessage("§e -§6§l/warp tp <nom> §8- §ePermet de te tp au warp de ton choix");
						player.sendMessage("");
						player.sendMessage("§8§m▬▬▬▬▬▬▬▬▬▬▬▬");
						break;

					default:
						if (args[0].equalsIgnoreCase("create")) {
							if (args[1] == null) {
								player.sendMessage(AgesRiver.PREFIX + "§9Veuillez indiquer le nom du warp");
								return false;
							}
							if (configuration.contains(args[1])) {
								player.sendMessage(AgesRiver.PREFIX + "§9Le warp §2" + args[1] + " §9éxiste déjà");
								return false;
							}

							configuration.set(args[1] + ".world", player.getLocation().getWorld().getName());
							configuration.set(args[1] + ".x", player.getLocation().getX());
							configuration.set(args[1] + ".y", player.getLocation().getY());
							configuration.set(args[1] + ".z", player.getLocation().getZ());
							configuration.set(args[1] + ".yaw", player.getLocation().getYaw());
							configuration.set(args[1] + ".pitch", player.getLocation().getPitch());

							player.sendMessage(AgesRiver.PREFIX + "§9Le warp §a" + args[1] + " §9a été créé.");

							try {
								configuration.save(file);
							} catch (IOException exeption) {
								exeption.printStackTrace();
							}

						}

						if (args[0].equalsIgnoreCase("delete")) {
							if (args[1] == null) {
								player.sendMessage(AgesRiver.PREFIX + "§9Veuillez indiquer le nom du warp");
								return false;
							}

							if (configuration.getString(args[1]) == null) {
								player.sendMessage(AgesRiver.PREFIX + "§9Le warp §c" + args[1] + " §9n'éxiste pas");
								return false;
							}

							configuration.set(args[1], null);
							player.sendMessage(AgesRiver.PREFIX + "§9Le warp §4" + args[1] + " §9a été supprimer.");
						}

						if (args[0].equalsIgnoreCase("teleport")) {

							if (args[1] == null) {
								player.sendMessage(AgesRiver.PREFIX + "§9Veuillez indiquer le nom d'un warp");
								return false;
							}
							if (configuration.getString(args[1]) == null) {
								player.sendMessage(AgesRiver.PREFIX + "§9Le warp §c" + args[1] + " §9n'éxiste pas");
								return false;
							}

							World world = Bukkit.getServer().getWorld(configuration.getString(args[1] + ".world"));
							double x = configuration.getDouble(args[1] + ".x");
							double y = configuration.getDouble(args[1] + ".y");
							double z = configuration.getDouble(args[1] + ".z");
							float yaw = configuration.getInt(args[1] + ".yaw");
							float pitch = configuration.getInt(args[1] + ".pitch");

							player.teleport(new Location(world, x, y, z, yaw, pitch));
							player.sendMessage(AgesRiver.PREFIX + "§9Tu as été téléporter au warp §e" + args[1]);

						}
						break;
				}
				return true;

			}
			player.sendMessage(AgesRiver.PREFIX + "§cVous n'avez pas la permission pour cette commande");
			return false;
		}
		player.sendMessage(AgesRiver.PREFIX + "§9Mauvaise commande. Pour connaitre la commande veuillez écrire §a/warp");
		return false;
	}


	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		File file = new File(this.instance.getDataFolder(), "Warps.yml");
		FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);

		switch (args.length) {
			case 1:
				return args.length == 1 ? ImmutableList.of("create", "delete", "teleport") : Collections.emptyList();
			case 2:
				for (String warps : configuration.getKeys(false))
					return args.length == 2 ? ImmutableList.of(warps) : Collections.emptyList();
		}
		return null;
	}

}

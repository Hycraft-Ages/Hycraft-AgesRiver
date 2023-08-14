package fr.justop.commands;

import fr.justop.AgesRiver;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CommandSpawn implements CommandExecutor, TabCompleter {

	private AgesRiver instance;

	public CommandSpawn(AgesRiver main) {
		this.instance = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		YamlConfiguration configuration = this.instance.getSpawnManager().getSpawnConfig();

		if (!(sender instanceof Player)) {
			Bukkit.getServer().getConsoleSender().sendMessage(AgesRiver.PREFIX + "§cSeul un joueur peut effectuer cette commande");
			return false;
		}

		Player player = (Player) sender;

		if (command.getName().equalsIgnoreCase("boat-spawn")) {
			switch (args.length) {
				case 0:
					player.sendMessage(AgesRiver.PREFIX + "§cUtilisez §b/boat-spawn add");
					break;

				case 1:
					if (args[0].equalsIgnoreCase("add")) {
						if (!player.hasPermission("hycraft.boatrace.staff")) {
							player.sendMessage(AgesRiver.PREFIX + "§cTu n'as pas la permission");
							return false;
						}

						UUID uuid = UUID.randomUUID();
						String segment = "spawns." + uuid.toString();

						configuration.set(segment + ".world", player.getLocation().getWorld().getName());
						configuration.set(segment + ".X", player.getLocation().getX());
						configuration.set(segment + ".Y", player.getLocation().getY());
						configuration.set(segment + ".Z", player.getLocation().getZ());
						configuration.set(segment + ".yaw", player.getLocation().getYaw());
						configuration.set(segment + ".pitch", player.getLocation().getPitch());

						player.sendMessage(AgesRiver.PREFIX + "§9Le spawn a été §acréé");

						this.instance.getSpawnManager().saveConfig();

						return true;
					}
					break;
			}
			return true;
		}
		player.sendMessage(AgesRiver.PREFIX + "§9Mauvaise commande. Pour connaitre la commande veuillez écrire §a/spawn");
		return false;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		String[] VALUES = {"add"};
		ArrayList<String> list = new ArrayList<>();

		if (command.getName().equalsIgnoreCase("boat-spawn")) {
			switch (args.length) {
				case 1:
					for (String value : VALUES) {
						if (value.toLowerCase().startsWith(args[0].toLowerCase())) list.add(value);
					}
					break;
			}

		}
		return list;
	}

}

package fr.justop.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import fr.justop.AgesRiver;

public class CommandTrophy implements CommandExecutor, TabCompleter
{

	private AgesRiver instance;

	public CommandTrophy(AgesRiver main)
	{
		this.instance = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{

		if (!(sender instanceof Player))
		{
			Bukkit.getServer().getConsoleSender().sendMessage("§cSeul un joueur peur effectuer cette commande");
			return false;
		}

		Player player = (Player) sender;

		if (command.getName().equalsIgnoreCase("boat-trophy"))
		{
			if (player.hasPermission("hycraft.boatrace.staff"))
			{
				//boat-staff args[0]	 args[1]	 args[2]  	args[3]
				//boat-staff add     	<type>  	<player>  	<nombre>
				if (args.length == 4)
				{
					if (!Bukkit.getPlayer(args[2]).isOnline())
					{
						player.sendMessage(AgesRiver.PREFIX + "§9Le joueur §c" + args[2] +  " §9n'est pas connecter");
						return false;
					}

					Player target = Bukkit.getPlayer(args[2]);
					int amount = Integer.parseInt(args[3]);

					if (args[1].equalsIgnoreCase("bronze") || args[1].equalsIgnoreCase("argent") || args[1].equalsIgnoreCase("or"))
					{
						if (!this.instance.getProfile().containsKey(target.getUniqueId()))
						{
							target.sendMessage(AgesRiver.PREFIX + "§e Le profil du joueur n'a pas ete trouvé impossible d'éxécuter l'action !");
							return false;
						}

						if (args[0].equalsIgnoreCase("add"))
						{
							this.instance.getProfileManager().addTrophy(target, args[1], amount);
							return true;
						}

						else if (args[0].equalsIgnoreCase("remove"))
						{
							this.instance.getProfileManager().removeTrophy(target, args[1], amount);
							return true;
						}
					}

				}
				return true;
			}
			player.sendMessage(AgesRiver.PREFIX + "§cTu n'as pas la permission");
			return false;
		}
		player.sendMessage(AgesRiver.PREFIX + "§9Mauvaise commande. Veuillez taper §a/Boat-Staff");
		return false;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args)
	{
		ArrayList<String> result = new ArrayList<>();

		String[] VALUES = { "add", "remove" };
		String[] TROPHY_TYPE = { "bronze", "argent", "or" };
		if (command.getName().equalsIgnoreCase("boat-trophy"))
		{
			switch (args.length)
			{
				case 1:
					for (String value : VALUES)
					{
						if (value.toLowerCase().startsWith(args[0].toLowerCase())) result.add(value);
					}
					break;

				case 2:
					for (String trophy_type : TROPHY_TYPE)
					{
						if (trophy_type.toLowerCase().startsWith(args[1].toLowerCase())) result.add(trophy_type);
					}
					break;

				case 3:
					for (Player player : Bukkit.getOnlinePlayers())
					{
						if (player.getName().toLowerCase().startsWith(args[2].toLowerCase())) result.add(player.getName());
					}
					break;
			}

		}
		return result;
	}

}
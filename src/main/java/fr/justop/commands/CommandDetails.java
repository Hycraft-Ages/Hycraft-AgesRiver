package fr.justop.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.justop.AgesRiver;
import fr.justop.enums.StateGame;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class CommandDetails implements CommandExecutor
{

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		if(sender instanceof Player)
		{
			Player player = (Player) sender;

			if (command.getName().equalsIgnoreCase("details") && args.length == 0)
			{
				if(AgesRiver.getInstance().getState().getStatistique() == StateGame.JEU || AgesRiver.getInstance().getState().getStatistique() == StateGame.FIN)
				{
					if(AgesRiver.getInstance().getFinished().containsKey(player.getUniqueId()))
					{
						String seg1 = "§a[" + AgesRiver.getInstance().getTaskGame().secondsToMinutes(AgesRiver.getInstance().getStats().getSeg1().get(player.getUniqueId())) + "] §r";
						TextComponent msg1 = new TextComponent(seg1);
						msg1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§aPréhistoire 1er tour").create()));

						String seg2 = "§e[" + AgesRiver.getInstance().getTaskGame().secondsToMinutes(AgesRiver.getInstance().getStats().getSeg2().get(player.getUniqueId())) + "] §r";
						TextComponent msg2 = new TextComponent(seg2);
						msg2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§eAntiquité 1er tour").create()));

						String seg3 = "§b[" + AgesRiver.getInstance().getTaskGame().secondsToMinutes(AgesRiver.getInstance().getStats().getSeg3().get(player.getUniqueId())) + "] §r";
						TextComponent msg3 = new TextComponent(seg3);
						msg3.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§bMoyen-Âge 1er tour").create()));


						String seg4 = "§a[" + AgesRiver.getInstance().getTaskGame().secondsToMinutes(AgesRiver.getInstance().getStats().getSeg4().get(player.getUniqueId())) + "] §r";
						TextComponent msg4 = new TextComponent(seg4);
						msg4.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§aPréhistoire 2e tour").create()));

						String seg5 = "§e[" + AgesRiver.getInstance().getTaskGame().secondsToMinutes(AgesRiver.getInstance().getStats().getSeg5().get(player.getUniqueId())) + "] §r";
						TextComponent msg5 = new TextComponent(seg5);
						msg5.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§eAntiquité 2e tour").create()));

						String seg6 = "§b[" + AgesRiver.getInstance().getTaskGame().secondsToMinutes(AgesRiver.getInstance().getStats().getSeg6().get(player.getUniqueId())) + "] §r";
						TextComponent msg6 = new TextComponent(seg6);
						msg6.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§bMoyen-Âge 2e tour").create()));


						String seg7 = "§a[" + AgesRiver.getInstance().getTaskGame().secondsToMinutes(AgesRiver.getInstance().getStats().getSeg7().get(player.getUniqueId())) + "] §r";
						TextComponent msg7 = new TextComponent(seg7);
						msg7.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§aPréhistoire 3e tour").create()));

						String seg8 = "§e[" + AgesRiver.getInstance().getTaskGame().secondsToMinutes(AgesRiver.getInstance().getStats().getSeg8().get(player.getUniqueId())) + "] §r";
						TextComponent msg8 = new TextComponent(seg8);
						msg8.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§eAntiquité 3e tour").create()));

						String seg9 = "§b[" + AgesRiver.getInstance().getTaskGame().secondsToMinutes(AgesRiver.getInstance().getStats().getSeg9().get(player.getUniqueId())) + "] §r";
						TextComponent msg9 = new TextComponent(seg9);
						msg9.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§bMoyen-Âge 3e tour").create()));

						player.sendMessage("");
						player.sendMessage("§8§m--------------" + AgesRiver.PREFIX + "§r§8§m---------------");
						player.sendMessage("");
						player.sendMessage("              §e§lStatistiques:                   ");
						player.sendMessage("           ");
						player.sendMessage("         §6§l1er tour: §r" + seg1 + seg2 + seg3);
						player.sendMessage("         §6§l2e tour: §r" + seg4 + seg5 + seg6);
						player.sendMessage("         §6§l3e tour: §r" + seg7 + seg8 + seg9);
						player.sendMessage("                      ");
						player.sendMessage("§8§m----------------------------------------");
						player.sendMessage("");

						return true;
					}
					player.sendMessage(AgesRiver.PREFIX + "§eIl semble que vous n'avez pas fini la course. Impossible de vous donner vos statistiques.");
					return true;
				}
				player.sendMessage(AgesRiver.PREFIX + "§eIl semble que vous n'avez pas fait de partie récemment. Impossible de donner vos statistiques.");
				return true;
			}
			player.sendMessage(AgesRiver.PREFIX + "§eVeuillez taper §b/details§e.");
			return false;
		}

		Bukkit.getServer().getConsoleSender().sendMessage(AgesRiver.PREFIX + "§cVous devez être un joueur pour effectuer cette commande.");
		return false;
	}

}


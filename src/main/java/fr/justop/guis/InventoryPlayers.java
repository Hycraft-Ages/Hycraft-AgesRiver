package fr.justop.guis;

import fr.justop.AgesRiver;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InventoryPlayers implements Listener
{
	private Inventory inventaire = Bukkit.createInventory(null, 18, "Menu de Téléportation");
	private List<ItemStack> heads = new ArrayList<>();
	private ItemStack FILLER;
	private int count = 0;


	@SuppressWarnings("deprecation")
	public void buildHeads()
	{
		for(Player player : AgesRiver.getInstance().getList().getPlayers())
		{
			if(AgesRiver.getInstance().getFinished().containsKey(player.getUniqueId())) return;

			ItemStack head = new ItemStack(Material.PLAYER_HEAD);
			SkullMeta meta = (SkullMeta) head.getItemMeta();
			meta.setOwner(player.getName());
			meta.setDisplayName("§a" + player.getName());
			meta.setLore(Arrays.asList("§b-------------------", "§eClique pour te TP", "§eà la position de", "§6" + player.getName()));
			head.setItemMeta(meta);

			this.heads.add(head);

			ItemStack filler = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
			ItemMeta fillerMeta = filler.getItemMeta();
			fillerMeta.setDisplayName("§7Null object");
			fillerMeta.setLore(null);
			filler.setItemMeta(fillerMeta);

			this.FILLER = filler;
		}
	}

	public void openInventory(Player player)
	{
		for (int fill = 0; fill < 18; fill++)
		{
			inventaire.setItem(fill, FILLER);
		}

		for(ItemStack head : heads)
		{
			inventaire.setItem(count, head);
			count++;
		}

		player.openInventory(inventaire);
	}

	@EventHandler
	public void onClickInventory(InventoryClickEvent event)
	{
		ItemStack clickedItem = event.getCurrentItem();

		if (event.getView().getTitle() == "Menu de Téléportation")
		{
			Player player = (Player) event.getWhoClicked();

			event.setCancelled(true);

			if (event.getClickedInventory() == null || clickedItem == null || clickedItem.getType() == Material.BLUE_STAINED_GLASS_PANE) return;

			SkullMeta meta = (SkullMeta) clickedItem.getItemMeta();
			@SuppressWarnings("deprecation")
			Player target = Bukkit.getPlayer(meta.getOwner());

			if(AgesRiver.getInstance().getFinished().containsKey(target.getUniqueId()))
			{
				player.sendMessage(AgesRiver.PREFIX + "§cImpossible de se téléporter au joueur, il a déjà terminé la course!");
				return;
			}

			if(!(target.isOnline()))
			{
				player.sendMessage(AgesRiver.PREFIX + "§cImpossible de se téléporter au joueur §e" + target.getName() + "§c, il est offLine!");
				return;
			}

			player.teleport(target);
			player.sendMessage(AgesRiver.PREFIX + "§a Vous avez été téléporté au joueur §e" + target.getName());
		}
	}



}

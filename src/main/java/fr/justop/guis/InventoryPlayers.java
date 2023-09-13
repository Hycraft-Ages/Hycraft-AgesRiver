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

	private Inventory inventory = Bukkit.createInventory(null, 18, "Menu d'entraînement");
	private List<ItemStack> heads = new ArrayList<>();
	private ItemStack FILLER;
	private int count = 0;


	@SuppressWarnings("deprecation")
	public void buildHeads()
	{
		for(Player player : AgesRiver.getInstance().getList().getPlayers())
		{
			if(AgesRiver.getInstance().getFinished().containsKey(player.getUniqueId())) continue;

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

	public void openTrainingInventory(Player player)
	{
		ItemStack prehistoire = new ItemStack(Material.BIRCH_LEAVES, 1);
		ItemMeta pmeta = prehistoire.getItemMeta();
		pmeta.setDisplayName("§a§lPréhistoire");
		pmeta.setLore(Arrays.asList("", "§eClique pour t'entrainer", "§eà la préhistoire"));
		prehistoire.setItemMeta(pmeta);

		ItemStack antiquite = new ItemStack(Material.CHISELED_SANDSTONE,1);
		ItemMeta ameta = antiquite.getItemMeta();
		ameta.setDisplayName("§6§lAntiquité");
		ameta.setLore(Arrays.asList("", "§eClique pour t'entrainer", "§eà l'antiquité"));
		antiquite.setItemMeta(ameta);

		ItemStack moyen = new ItemStack(Material.STONE_BRICKS,1);
		ItemMeta mmeta = moyen.getItemMeta();
		mmeta.setDisplayName("§b§lMoyen-âge");
		mmeta.setLore(Arrays.asList("", "§eClique pour t'entrainer", "§eau Moyen-âge"));
		moyen.setItemMeta(mmeta);

		ItemStack filler = new ItemStack(Material.GREEN_STAINED_GLASS_PANE, 1);
		ItemMeta fmeta = filler.getItemMeta();
		fmeta.setDisplayName("null item");
		fmeta.setLore(null);
		filler.setItemMeta(fmeta);

		for(int slot = 0; slot < 18; slot++)
		{
			inventory.setItem(slot, filler);
		}

		inventory.setItem(11, prehistoire);
		inventory.setItem(13, antiquite);
		inventory.setItem(15, moyen);

		player.openInventory(inventory);


	}

	@EventHandler
	public void onClickInventory(InventoryClickEvent event)
	{
		ItemStack clickedItem = event.getCurrentItem();

		if (event.getView().getTitle() == "Menu de Téléportation" || event.getView().getTitle() == "Menu d'entraînement")
		{
			Player player = (Player) event.getWhoClicked();

			event.setCancelled(true);

			if (event.getClickedInventory() == null || clickedItem == null || clickedItem.getType() == Material.BLUE_STAINED_GLASS_PANE || clickedItem.getType() == Material.GREEN_STAINED_GLASS_PANE) return;

			player.closeInventory();

			if(event.getView().getTitle() == "Menu de Téléportation")
			{
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
			} else {

				switch (clickedItem.getType())
				{
					case BIRCH_LEAVES:
						AgesRiver.getInstance().getTrainer().joinAge(player, "prehistoire");
						break;

					case CHISELED_SANDSTONE:
						AgesRiver.getInstance().getTrainer().joinAge(player, "antiquite");
						break;

					case STONE_BRICKS:
						AgesRiver.getInstance().getTrainer().joinAge(player, "moyen");
						break;
				}
			}
		}
	}



}

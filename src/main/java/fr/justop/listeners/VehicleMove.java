package fr.justop.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Boat;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleMoveEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.Vector;
import org.mineacademy.fo.plugin.SimplePlugin;

public class VehicleMove implements Listener {
	@EventHandler(priority = EventPriority.LOWEST)
	public void onMoveBoat(VehicleMoveEvent event)
	{
		final Vehicle vehicle = event.getVehicle();
		if (vehicle instanceof Boat)
		{
			Boat boat = (Boat) vehicle;
			Location locationBoat = boat.getLocation();
			Location blockUnderBoat = locationBoat.getBlock().getRelative(BlockFace.DOWN).getLocation();

			if (!boat.isEmpty() && blockUnderBoat.getBlock().getType() == Material.LIME_TERRACOTTA)
			{
				Player player = (Player) boat.getPassenger();
				player.sendMessage("yes");
				long lastJump = boat.hasMetadata("jumped") ? boat.getMetadata("jumped").get(0).asLong() : 0;

				if (lastJump == 0 || System.currentTimeMillis() - lastJump > 1000)
				{
					player.sendMessage("YEs2");
					Vector currentVelocity = boat.getVelocity();
					boat.setVelocity(currentVelocity.add(new Vector(0, 1, 0)));

					boat.setMetadata("jumped", new FixedMetadataValue(SimplePlugin.getInstance(), System.currentTimeMillis()));

				}
			}
		}
	}
}


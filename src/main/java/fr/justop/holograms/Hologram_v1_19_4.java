package fr.justop.holograms;

import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.protocol.game.ClientboundSetEntityDataPacket;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.level.Level;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.mineacademy.fo.remain.Remain;

public class Hologram_v1_19_4 extends Hologram {

	@Override
	protected Object createEntity(Object nmsWorld, Location location) {
		return new ArmorStand((Level) nmsWorld, location.getX(), location.getY(), location.getZ());
	}

	@Override
	protected void sendPackets(Player player, Object nmsArmorStands) {
		ArmorStand nmsArmorStand = (ArmorStand) nmsArmorStands;

		Remain.sendPacket(player, new ClientboundAddEntityPacket(nmsArmorStand));
		Remain.sendPacket(player, new ClientboundSetEntityDataPacket(nmsArmorStand.getId(), nmsArmorStand.getEntityData().getNonDefaultValues()));
	}
}

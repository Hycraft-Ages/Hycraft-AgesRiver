package fr.justop.holograms;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.mineacademy.fo.MinecraftVersion;
import org.mineacademy.fo.ReflectionUtil;
import org.mineacademy.fo.exception.FoException;
import org.mineacademy.fo.remain.Remain;

public abstract class Hologram {
	public void show(final Player player, Location location, final String... lines) {

		final Object nmsWorld = Remain.getHandleWorld(location.getWorld());

		for (String line : lines) {
			Object nmsArmorStand = this.createEntity(nmsWorld, location);
			final ArmorStand armorStand = ReflectionUtil.invoke("getBukkitEntity", nmsArmorStand);

			armorStand.setInvisible(true);

			Remain.setCustomName(armorStand, line);

			this.sendPackets(player, nmsArmorStand);

			location = location.subtract(0, 0.26, 0);

		}
	}

	protected abstract Object createEntity(Object nmsWorld, Location location);

	protected abstract void sendPackets(Player player, Object nmsArmorStands);

	protected static void sendTo(final Player player, Location location, final String... lines) {
		Hologram holo;

		if (MinecraftVersion.equals(MinecraftVersion.V.v1_19)) {
			holo = new Hologram_v1_19_4();

		} else
			throw new FoException("Unsuported minecraft version" + MinecraftVersion.getServerVersion());

		holo.show(player, location, lines);
	}

}

package fr.justop.configs;

import fr.justop.AgesRiver;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SpawnManager {
	private File file;
	private YamlConfiguration configuration;
	private AgesRiver instance;
	private List<Location> spawnsList = new ArrayList<>();

	public SpawnManager(AgesRiver main) {
		this.instance = main;
	}

	public void onFile() {

		loadConfig();

		if (this.configuration.getConfigurationSection("spawns") == null) return;

		ConfigurationSection spawnsSection = this.configuration.getConfigurationSection("spawns");

		for (String spawn : spawnsSection.getKeys(false)) {
			double locX = spawnsSection.getDouble(spawn + ".X");
			double locY = spawnsSection.getDouble(spawn + ".Y");
			double locZ = spawnsSection.getDouble(spawn + ".Z");
			float yaw = (float) spawnsSection.getDouble(spawn + ".yaw");
			float pitch = (float) spawnsSection.getDouble(spawn + ".pitch");

			Location loc = new Location(Bukkit.getWorld("Ages_River"), locX, locY, locZ, yaw, pitch);
			this.spawnsList.add(loc);

		}
	}

	public void loadConfig() {

		if (!this.instance.getDataFolder().exists()) this.instance.getDataFolder().mkdir();

		this.file = new File(this.instance.getDataFolder(), "Spawn.yml");

		if (!file.exists()) {
			try {
				this.file.createNewFile();
			} catch (IOException exeption) {
				exeption.printStackTrace();
			}
		}
		this.configuration = YamlConfiguration.loadConfiguration(file);
	}

	public void saveConfig() {
		try {
			this.configuration.save(file);
		} catch (IOException exeption) {
			exeption.printStackTrace();
		}
	}

	public YamlConfiguration getSpawnConfig() {
		return this.configuration;
	}

	public List<Location> getSpawnsList() {
		return spawnsList;
	}
}

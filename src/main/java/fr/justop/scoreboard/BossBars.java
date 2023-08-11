package fr.justop.scoreboard;

import fr.justop.AgesRiver;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class BossBars
{
	private final AgesRiver instance;
	private BossBar bar1;
	private BossBar bar2;
	private BossBar bar3;

	public BossBars(AgesRiver main)
	{
		this.instance = main;
		bar1 = Bukkit.createBossBar(format("&a&lPrehistoire"), BarColor.GREEN, BarStyle.SOLID);
		bar2 = Bukkit.createBossBar(format("&e&lAntiquité"), BarColor.YELLOW, BarStyle.SOLID);
		bar3 = Bukkit.createBossBar(format("&b§lMoyen-Âge"), BarColor.BLUE, BarStyle.SOLID);
	}

	public void addPlayer(Player player, int bar)
	{
		switch(bar)
		{
			case 1:
				bar1.addPlayer(player);
				break;

			case 2:
				bar2.addPlayer(player);
				break;

			case 3:
				bar3.addPlayer(player);
				break;
		}
	}

	public void removePlayer(Player player)
	{
		List<BossBar> list = new ArrayList<>();
		Bukkit.getServer().getBossBars().forEachRemaining(list::add);

		for(BossBar bar : list)
		{
			bar.removePlayer(player);
		}
	}

	public BossBar getBar1()
	{
		return bar1;
	}

	public BossBar getBar2()
	{
		return bar2;
	}

	public BossBar getBar3()
	{
		return bar3;
	}

	public void createBars()
	{
		this.bar1 = Bukkit.createBossBar(format("&a&lPrehistoire - "), BarColor.GREEN, BarStyle.SOLID);
		this.bar2 = Bukkit.createBossBar(format("&e-- &lAntiquité §r§e-- "), BarColor.YELLOW, BarStyle.SOLID);
		this.bar3 = Bukkit.createBossBar(format("&b&lMoyen-âge - " + AgesRiver.getInstance().getTaskGame().toString()), BarColor.BLUE, BarStyle.SOLID);
	}

	public String format(String msg)
	{
		return ChatColor.translateAlternateColorCodes('&', msg);
	}
}

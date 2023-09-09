package fr.justop.scoreboard;

import fr.justop.AgesRiver;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.mineacademy.fo.remain.Remain;

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
		Remain.removeBossbar(player);
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

	public String format(String msg)
	{
		return ChatColor.translateAlternateColorCodes('&', msg);
	}
}

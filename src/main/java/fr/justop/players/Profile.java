package fr.justop.players;

import java.util.UUID;

import fr.justop.AgesRiver;

public class Profile
{
	private int winActuelle;
	private int recordWin;
	private int gold;
	private int argent;
	private int bronze;
	private int register;
	private AgesRiver instance;

	public Profile()
	{
		super();
	}

	public Profile(AgesRiver main, UUID uuid, int actuel, int record, int gold, int argent, int bronze, int register)
	{
		this.instance 		= main;

		this.winActuelle	= actuel;
		this.recordWin		= record;
		this.gold			= gold;
		this.argent			= argent;
		this.bronze			= bronze;
		this.register		= register;

		this.instance.getProfile().put(uuid, this);
	}

	/**
	 * @param uuid
	 * @apiNote get info Player
	 */
	public Profile getProfile(UUID uuid)
	{
		return this.instance.getProfile().get(uuid);
	}

	public int getWinActuelle()
	{
		return winActuelle;
	}

	public void setWinActuelle(int winActuelle)
	{
		this.winActuelle = winActuelle;
	}

	public int getRecordWin()
	{
		return recordWin;
	}

	public void setRecordWin(int recordWin)
	{
		this.recordWin = recordWin;
	}

	public int getGold()
	{
		return gold;
	}

	public void setGold(int gold)
	{
		this.gold = gold;
	}

	public int getArgent()
	{
		return argent;
	}

	public void setArgent(int argent)
	{
		this.argent = argent;
	}

	public int getBronze()
	{
		return bronze;
	}

	public void setBronze(int bronze)
	{
		this.bronze = bronze;
	}

	public int getRegister()
	{
		return register;
	}

	public void setRegister(int register)
	{
		this.register = register;
	}

}

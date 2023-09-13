package fr.justop.enums;

public class StateManager
{
	private int state;
	private int mode;

	public void setStatistique(int state)
	{
		this.state = state;

	}

	public void setMode(int mode)
	{
		this.mode = mode;
	}

	public int getMode()
	{
		return mode;
	}

	public int getStatistique()
	{
		return this.state;
	}

	public String getEtat()
	{

		switch (this.getStatistique())
		{
			case 0:

				return "ATTENTE";

			case 1:
				return "COMMENCEMENT";

			case 2:
				return "JEU";

			case 3:
				return "FIN";
		}
		return null;
	}

}

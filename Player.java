public abstract class Player
{
	public String playerType;
	
	public double cost;
	
	public int points;
	
	public double getCost()
	{
		return cost;
	}

	public int getPoints()
	{
		return points;
	}
	
	
	public String getDesc()
	{
		return playerType;
	}

}
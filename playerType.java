public class playerType extends Player
{
	
	public playerType() //empty constructor
	{
		
	}
	
	public playerType(String pl,int p, double c)  //Player type, points, cost
	{
		playerType=pl;
		points = p;
		cost = c;
		
	}
	
	
	public String toString()
	{
		
		return playerType + " " + "|Points: " + points + "  " + "|Cost: " + cost;
		
	}
	
	
}

public class Acc 
{
	private String name;
	private double cost;
	private int points;
	
	public Acc()
	{
		name="";
		cost=0;
		points=0;
		
	}
	public Acc(String n, double c, int p)
	{
	name=n;
	cost=c;
	points=p;	
	}
	
	
	public double getCost()
	{
		return cost;
	}
	
	public int getPoints()
	{
		return points;
	}
	
	
	
	
	
	
	
	
	
	
}

public class Report
{
	private String dateDone;
	private String custName;
	private String playerType;
	private double accesoryCost;
	private double totalCost;
	
	public Report()
	{
		dateDone=null;
		custName="";
		playerType="";
		accesoryCost=0;
		totalCost=0;
	}

	
	public Report(String d, String c, String p, double a, double t)
	{
		dateDone=d;
		custName=c;
		playerType=p;
		accesoryCost=a;
		totalCost=t;
	}
	
	public String toString()
	{	
		return "Date: " + dateDone + " CustomerName: " + custName + " PlayerType: " + playerType + " AccessoryCost: " + accesoryCost + 
		" TotalCost: " + totalCost;
	}
	
	
	
	
	
	
	
	
}
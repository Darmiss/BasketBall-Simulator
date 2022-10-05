public class CustomAcc
{
private String accType;
private int accPoints;
private double accCost;

	public CustomAcc(String a,int p,double c) //TYPE,COST,Point
	{
		accType=a;
		accPoints=p;
		accCost=c;
	}
	
	public CustomAcc()
	{
		accType="";
		accCost=0;
		accPoints=0;
		
	}
	
	public String getAccType()
	{
		return accType;
	}

	public int getAccPoints()
	{
		return accPoints;
	}
	
	public double getAccCost()
	{
		return accCost;
	}


	public String toString()
	{
		return accType + "|Points: " + accPoints + "|Cost: " + accCost;
		
	}






}
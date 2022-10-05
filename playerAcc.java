public class playerAcc extends PlayerAccessories
{
	Player player;
	CustomAcc custAcc;
	private String accType;
	private double accCost;
	private int accPoints;
	public playerAcc(Player player,CustomAcc custAcc)  //player its going onto,customAcc
	{
		accType = custAcc.getAccType();
		accCost = custAcc.getAccCost();
		accPoints= custAcc.getAccPoints();
		this.player=player;
		this.custAcc=custAcc;
		
	}
	

	public String getDesc()
	{
		return player.getDesc() + " " + accType + " ";
	}
	
	public double getCost()
	{
		return accCost + player.getCost();
	}
	
	
	public int getPoints()
	{
		return accPoints + player.getPoints();
	}
	
	
}
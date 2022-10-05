public class Test
{
	
	public static void main(String[] args)
	{
		
		Player customPlayer = new playerType("8th grader",45,45.50);
		CustomAcc newAccessory = new CustomAcc("WristBand",45,23.30);
		CustomAcc newAccessory2 = new CustomAcc("legbands",9,283.30);
		customPlayer = new playerAcc(customPlayer,newAccessory);
		customPlayer = new playerAcc(customPlayer,newAccessory);
		customPlayer = new playerAcc(customPlayer,newAccessory2);
		System.out.println(customPlayer.getDesc() + customPlayer.getPoints() + " " + customPlayer.getCost());
		
		//HOW TO USE,
		//Player oldMan = new playerType("Old man 5'2 whatever", 52(points),23.20(cost))
		//TO ADD ACCESSORIES
		//CustomAcc WristBand = new CustomAcc("WristBand",23(points),12.20(cost))
		//CAN DO MULTIPLE TIMES SO
		//CustomAcc WristBand = new CustomAcc("WristBand",23(points),12.20(cost))
		//CustomAcc WristBand = new CustomAcc("WristBand",23(points),12.20(cost))
		//CustomAcc WristBand = new CustomAcc("WristBand",23(points),12.20(cost))
		//TO PRINT
		//System.out.println(oldMan.getDesc())
		//System.out.println(oldMan.getPoints())
		//System.out.println(oldMan.getCost())
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
}
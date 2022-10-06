
import java.util.Scanner; //user cmd input
import java.io.FileWriter; //make the json files
import java.io.FileReader; //read the json files
import java.io.BufferedReader;
import java.io.IOException;
import com.google.gson.Gson;  //json
import com.google.gson.GsonBuilder;  //json to store (( Date, player and accessories, customer name, individual accessory price(s), total price)
import java.time.format.DateTimeFormatter; //For date
import java.time.LocalDateTime; //For date
import java.util.concurrent.TimeUnit; //to slow it down for neatness
import java.io.*;
import java.util.*;

public class BasketBall{
	static Scanner input = new Scanner(System.in); //For all userInput in cmd
	static String[] playerData;
	static String[] accessoryData;
	static Player customerPlayer; //to store playertype for reports
	static String customerName; //customer name for a session
	static double playerCost; //subtracting this from total cost to get accesory cost for reports
    public static void main(String[]args) throws  InterruptedException //throws for the timeUnit.Seconds
	{
	  checkForReportFile(); //ensuring a report.json file is present
	  System.out.println("BASKETBALL SIMULATOR V0.00001, by: Lucas Mazur & Austin McCready");
	  TimeUnit.SECONDS.sleep(1);
	  loadOriginals();
	   String userInput="";
	   int intInput=-1;
	   do
	   {
	   do
	   {
	   TimeUnit.SECONDS.sleep(1);
	   System.out.println("Who are you?");
	   System.out.print("1.) A Manager, " + " 2.)A User, " + "3.) Exit " + "\n");
	   userInput=input.nextLine();
	   try
	   {
	   intInput = Integer.parseInt(userInput);
	   break;
	   }catch(NumberFormatException e)
	   {
		   TimeUnit.SECONDS.sleep(1);
		   System.out.println("Not a number, nice try.");
	   }
	   if(intInput !=1 && intInput != 2 && intInput != 3)
	   {
		    TimeUnit.SECONDS.sleep(1);
		   System.out.println("Please choose 1,2 or 3:");
	   }
	   }
	   while( intInput !=1 && intInput !=2 && intInput !=3);
	
	   if(intInput == 1)
	   {
		   //Manager 
		   managerMethod();
	   }
	   else if(intInput == 2)
	   {
		   if(customerName==null)
		   {
			   System.out.println("Please enter your name: ");
			   customerName=input.nextLine();
		   }
		   //User
		   userMethod();
	   }
	   }while(intInput!=3);
	   
	   
	   
	   

}

	private static void loadOriginals()
	{
			if(findJsonFile("AccessoryData.json",new File("AccessoryData.json"))==0)
			{
				System.out.println("AccessoryData not found, AccessoryData not loaded, new blank file made");
				try
				{
				FileWriter newFile = new FileWriter("AccessoryData.json");
				newFile.close();
				accessoryData=getJsonFileString("AccessoryData.json");
				}
				catch(IOException e)
				{
				e.printStackTrace();
				}
			}
			else
				
			{
				accessoryData=getJsonFileString("AccessoryData.json");
				System.out.println("AccessoryData loaded");
			}
			if(findJsonFile("PlayerData.json",new File("PlayerData.json"))==0)
			{
				System.out.println("PlayerData not found, PlayerData not loaded, new blank file made");
				try
				{
				FileWriter newFile = new FileWriter("PlayerData.json");
				newFile.close();
				playerData=getJsonFileString("PlayerData.json");
				}
				catch(IOException e)
				{
				e.printStackTrace();
				}
			}
			else
			{
				playerData=getJsonFileString("PlayerData.json");
				System.out.println("PlayerData loaded");
				
			}
	}

	private static void userMethod()
	{
		String userString="";
		int userInt=-1;
		do
		{
			System.out.println("Press 1 to view the menu, Press 2 to Make an Order, 3 to go back."); //Menu items
			userString=input.nextLine();
				
			try
			{
			userInt=Integer.parseInt(userString);
			}
			catch(NumberFormatException e)
			{
				System.out.println("Not a number");
				userInt=-1;
			}
			if(userInt == 1)
			{
				System.out.println(showMenu());
			}
			else if(userInt == 2)
			{
				String tempStr = "";
				int x =-1;
				System.out.println("Print the menu? 1 for Yes, 2 for No");
				do
				{
				tempStr=input.nextLine();
				x=Integer.parseInt(tempStr);
				}while(x!=1 && x!=2);
				if(x==1)
				{
					System.out.println(showMenu());
				}
				makeAnOrder();
			}
			
			if(userInt != 1 && userInt !=2)
			{
				if(userInt!=3)
				{
				System.out.println("Error: Wrong input");
				}
			}
		
		}
		while(userInt!=3);
	}
	
	private static void managerMethod()
	{
		String managerString="";
		int managerInt = -1;
		do
		{
		try
		{
		TimeUnit.SECONDS.sleep(1);
		}catch(InterruptedException e)
		{
			e.printStackTrace();
		}
			System.out.println("Press 1 to print report, Press 2 to read in accesoryData json file, Press 3 to read in playerData json file, " + "\n" +
			"Press 4 to add Accessory, Press 5 to remove Accesory, Press 6 to add PlayerData, " + "\n" +"Press 7 to remove PlayerData,Press 8 to save the playerData." + "Press 9 to save the AccessoryData" + "\n" + "Press 10 to go back"); //menu options
			managerString=input.nextLine();
			try
			{
			managerInt=Integer.parseInt(managerString);
			}
			catch(NumberFormatException e)
			{
				System.out.println("Not a number");
				managerInt=-1;
			}
			
			if(managerInt==1)
			{
				printReport();
			}
			else if(managerInt==2)
			{
				Gson gson = new Gson();
				System.out.println("Note the accessoryData will be overwritten");
				String fileName="";
				System.out.println("AccessoryData: Please enter a json file name,(include .json)");
				fileName=input.nextLine();
				if(findJsonFile(fileName,new File(fileName))==0)
				{
					System.out.println("File not found");
				}
				else
				{
				readAccessoryFile(fileName);
				}
			}
			else if(managerInt==3)
			{
				System.out.println("Note the playerData file will be overwritten, save before or previous types will be loss");
				String fileName="";
				System.out.println("PlayerData: Please enter a json file name,(include .json)");
				fileName =input.nextLine();
				if(findJsonFile(fileName,new File(fileName))==0)
				{
					System.out.println("File not found");
				}
				else
				{
				readPlayerDataFile(fileName);
				}
			}
			else if(managerInt==4)
			{
				boolean Isuccess=false;
				boolean Dsuccess=false;
				String tempString="";
				String newType="";
				int points=0;
				double cost=0;
				System.out.println("Please enter the new accessory descritpion(i.e wristband etc): ");
				newType=input.nextLine();
				System.out.println("Please enter the amount of points: ");
				while(!Isuccess)
				{
				try
				{
				tempString=input.nextLine();
				points=Integer.parseInt(tempString);
				Isuccess=true;
				}
				catch(NumberFormatException e)
				{
				System.out.println("Not a number, Enter a number:");
				}
				}
				System.out.println("Please enter the cost of this accessory: ");
				while(!Dsuccess)
				{
				try
				{
					tempString=input.nextLine();
					cost=Double.parseDouble(tempString);
					Dsuccess=true;
				}
				catch(NumberFormatException e)
				{
					System.out.println("Not a number, Enter a number:(Dont add the $)");
				}
				}
				addAccessory(newType,points,cost);
				
			}
			else if(managerInt==5)
			{
				removeAccesory();
			}
			else if(managerInt==6)
			{
				//Add playerData
				boolean Isuccess=false;
				boolean Dsuccess=false;
				String tempString="";
				String newType="";
				int points=0;
				double cost=0;
				System.out.println("Please enter the new players type(i.e oldman 5'2 etc): ");
				newType=input.nextLine();
				System.out.println("Please enter the amount of points: ");
				while(!Isuccess)
				{
				try
				{
				tempString=input.nextLine();
				points=Integer.parseInt(tempString);
				Isuccess=true;
				}
				catch(NumberFormatException e)
				{
				System.out.println("Not a number, Enter a number:");
				}
				}
				System.out.println("Please enter the cost of this player: ");
				while(!Dsuccess)
				{
				try
				{
					tempString=input.nextLine();
					cost=Double.parseDouble(tempString);
					Dsuccess=true;
				}
				catch(NumberFormatException e)
				{
					System.out.println("Not a number, Enter a number:(Dont add the $)");
				}
				}
				addPlayerData(newType,points,cost);
				
			}
			else if(managerInt == 7)
			{
				removePlayerData();
			}
			else if(managerInt == 8)
			{
				System.out.println("Saving playerData...");
				savePlayerData();
			}
			else if(managerInt ==9)
			{
				System.out.println("Saving accessoryData...");
				saveAccessoryData();
			}
			
			
			
			
			if(managerInt != 1 && managerInt !=2 && managerInt !=3 && managerInt !=4 && managerInt !=5 &&managerInt !=6 && managerInt!=7 && managerInt!=8 && managerInt !=9)
			{
				if(managerInt!=10)
				{
				System.out.println("Error: Wrong input");
				}
			}
		}
		while(managerInt!=10);
		
	}
	
	private static String showMenu() //will print the menu(each time the menu changes)
	{
		Gson gson = new Gson();
		String menu ="";
		if (accessoryData.length==0 && playerData.length==0)
		{
			return "No accessory and no player data, please load in an json file,No menu displayed"; 
		}
		else if (accessoryData.length==0)
		{
			menu+= "No accessories found, please load an accessory json file";  //can order players with no accessories
		}
		else if(playerData.length==0)
		{
			return "No player data, please load in an player Data json file";
		}
		
		//if hits here, has atleast player Data to display
		if(accessoryData.length==0)
		{
			int n =0;
			for(int i = 0; i < playerData.length; i++)
			{
			System.out.print(n + "| ");
			n++;
			System.out.println(gson.fromJson(playerData[i],playerType.class).toString());
			}	
		}
		else
		{
			//has both playerdata and accessory data
			int n = 0;
			System.out.println();
			System.out.println("PLAYER TYPES");
			for(int i = 0; i < playerData.length; i++)
			{
			System.out.print(n + "| ");
			n++;
			System.out.println(gson.fromJson(playerData[i],playerType.class).toString());
			}	
			int a = 0;
			System.out.println();
			System.out.println("ACCESSORY TYPES");
			for(int i = 0; i < accessoryData.length; i++)
			{
				System.out.print(a + "| ");
				a++;
				System.out.println(gson.fromJson(accessoryData[i],CustomAcc.class).toString());	
			}
			
			
		}
		
	
		return menu;
	}
	
	private static void makeAnOrder() //Users uses this to order players, will also print total cost/points per game
	{
		if(playerData.length==0)
		{
			System.out.println("No playerdata found, cannot order");
			return;
		}
		Gson gson = new Gson();
		String temp ="";
		int orderInput=-1;
		System.out.println("Select a playerType 0-" + (playerData.length-1));
		do
		{
			try
			{
		temp=input.nextLine();
		orderInput=Integer.parseInt(temp);
			}
			catch(NumberFormatException e)
			{
				System.out.println("Wrong Input");
			}
		if(orderInput < 0 || orderInput > (playerData.length-1))
		{
			System.out.println("Please enter a valid range for the Player Table");
		}
		}while(orderInput < 0 || orderInput > (playerData.length-1));
		
		Player newPlayer = new playerType();
		newPlayer = gson.fromJson(playerData[orderInput],playerType.class);
		playerCost = newPlayer.getCost(); //for reports
		int accInput=-1;
		System.out.println("Do you want accessories?,1 for Yes, 2 for No");
		do 
		{
		temp=input.nextLine();
		try{
		accInput=Integer.parseInt(temp);
		}catch(NumberFormatException e)
		{
			System.out.println("Wrong Input");
		}
		if(accInput!=1 && accInput!=2)
		{
			System.out.println("Pick 1 or 2");	
		}
		}while(accInput!=1 && accInput !=2);
		
		if(accInput ==1)
		{
		System.out.println("Select a accessoryType 0-" + (accessoryData.length-1));
		do
		{
			try
			{
				temp=input.nextLine();
				orderInput=Integer.parseInt(temp);
			}
			catch(NumberFormatException e)
			{
				System.out.println("Wrong Input");
			}
			if(orderInput <0 || orderInput >(accessoryData.length-1))
			{
				System.out.println("Please enter a valid range for the Accessory Table");
			}
		} while(orderInput <0 || orderInput >(accessoryData.length-1));
		
		newPlayer = new playerAcc(newPlayer,gson.fromJson(accessoryData[orderInput],CustomAcc.class));
		System.out.println("Accessory added.");
		System.out.println("Would you like to add another?");
		System.out.println("1 for Yes, 2 for No");
		do
		{
		temp=input.nextLine();
		try
		{
		orderInput=Integer.parseInt(temp);
		}
		catch(NumberFormatException e)
		{
			System.out.println("Wrong Input");
		}
		if(orderInput !=1 && orderInput !=2)
		{
			System.out.println("Please press 1 for More Accessories and 2 for Done");
		}
		}while(orderInput!= 1 && orderInput !=2);
		
		if(orderInput==1)
		{
			do
			{
			System.out.println(showMenu());
			System.out.println("Select a accessoryType 0-" + (accessoryData.length-1));
		do
		{
			try
			{
				temp=input.nextLine();
				orderInput=Integer.parseInt(temp);
			}
			catch(NumberFormatException e)
			{
				System.out.println("Wrong Input");
			}
			if(orderInput <0 || orderInput >(accessoryData.length-1))
			{
				System.out.println("Please enter a valid range for the Accessory Table");
			}
		} while(orderInput <0 || orderInput >(accessoryData.length-1));
		
		newPlayer = new playerAcc(newPlayer,gson.fromJson(accessoryData[orderInput],CustomAcc.class));
		System.out.println("Accessory added.");
		System.out.println("More?, press 1 to add another.");
		temp=input.nextLine();
		accInput=Integer.parseInt(temp);
			}while(accInput==1);
			
			
			
			
		}
		
		}
		
		
		customerPlayer=newPlayer;
		System.out.println(newPlayer.toString());
		
		addReport();
	}
	
	private static  void saveAccessoryData()
	{
		Gson gson = new Gson();
		int length = -1;
		try
		{
			FileWriter fw = new FileWriter("SavedAccessoryData.json");
			FileReader fr = new FileReader("AccessoryData.json");
			BufferedReader br = new BufferedReader(fr);
			
			for(int i = 0; i < accessoryData.length;i++) //loop through each line in the file
			{
				
					CustomAcc readAcc = gson.fromJson(accessoryData[i],CustomAcc.class);
					fw.write(gson.toJson(readAcc));
					fw.write("\n");
			}
			

			fr.close();
			fw.close();
			br.close();
			System.out.println("Accessory File saved as, SavedAccessoryData.json");
		}catch(FileNotFoundException e)
			{
				e.printStackTrace();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		
	}
	
	private static void savePlayerData()
	{
		Gson gson = new Gson();
		int length = -1;
		try
		{
			FileWriter fw = new FileWriter("SavedPlayerData.json");
			FileReader fr = new FileReader("PlayerData.json");
			BufferedReader br = new BufferedReader(fr);
			
			for(int i = 0; i < playerData.length;i++) //loop through each line in the file
			{
				
			
						Player newPlayer = gson.fromJson(playerData[i],playerType.class);
						fw.write(gson.toJson(newPlayer));
						fw.write("\n");
				
			}
			

			fr.close();
			fw.close();
			br.close();
			System.out.println("PlayerData File saved as, SavedPlayerData.json");
		}
			catch(FileNotFoundException e)
			{
				e.printStackTrace();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			
		
	}

	private static void checkForReportFile()
	{
		if(findJsonFile("report.json",new File("report.json")) == 0)  //Checking for a report json file, if not found new one is made
		{
			System.out.println("No report file found, making blank new one");
			try
			{
			FileWriter newReport = new FileWriter("report.json");
			newReport.close();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}	
	}
	

	
	private static void readAccessoryFile(String fileName) //Manager uses to read in a accessory file
	{
		overwriteFile("AccessoryData.json",1,fileName);
		accessoryData=getJsonFileString("AccessoryData.json");
		
	}
	
	private static void readPlayerDataFile(String fileName) //Manager uses to read in a player file
	{
		
		overwriteFile("PlayerData.json",2,fileName);
		playerData=getJsonFileString(fileName);
	}
	
	private static void printReport() //Will print report of orders made(sorted by latest date)
	{
		Gson gson = new Gson();
		try
		{
		TimeUnit.SECONDS.sleep(1);
		}catch(InterruptedException e)
		{
			e.printStackTrace();
		}
		//Report file is named report.json
	    //might be blank, but will exist
		String[] stringArr = getJsonFileString("report.json");
		if(stringArr.length==0)
		{
			System.out.println("No reports present ");
		}
		System.out.println("NEWEST->OLDEST");
		for(int i=stringArr.length-1;i>=0;i--)
		{
		System.out.println(gson.fromJson(stringArr[i],Report.class));
		}
		System.out.println();
		
	}
	
	private static void addReport() //Invoked when an order is made
	{
		Date date = new Date();
		Report newReport = new Report(date.toString(),customerName,customerPlayer.getDesc(),(customerPlayer.getCost()-playerCost),customerPlayer.getCost());
		Gson gson = new Gson();
		try
		{
			FileWriter reportFile = new FileWriter("report.json",true); //report file exists, checked at begining of program
			reportFile.write(gson.toJson(newReport));
			reportFile.write("\n");
			reportFile.close();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		
	}
	
	private static void addAccessory(String type,int points,double cost) //Manager uses to append accesory onto json files
	{
		//gson
		Gson gson = new Gson();
		CustomAcc newAccType = new CustomAcc(type,points,cost);
		try
		{
		   FileWriter playerFile = new FileWriter("AccessoryData.json",true);
		   playerFile.write(gson.toJson(newAccType));
		   playerFile.write("\n");
		   playerFile.close();
		   accessoryData=getJsonFileString("AccessoryData.json");
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		System.out.println("Accessory added");
		System.out.println(showMenu());
	}
	
	private static void removeAccesory() //Manager uses to remove accessory from json files
	{
		Gson gson = new Gson();
		String temp="";
		int orderInput=-1;
		if(accessoryData.length==0)
		{
			System.out.println("There are no accessories to remove");
			return;
		}
		System.out.println(showMenu());
		System.out.println("Which Accessory would you like to remove? ");
		System.out.println("Select a Accessory Type 0-" + (accessoryData.length-1));
		do
		{
			try
			{
				temp=input.nextLine();
				orderInput=Integer.parseInt(temp);
			}
			catch(NumberFormatException e)
			{
				System.out.println("Wrong Input");
			}
			if(orderInput <0 || orderInput >(accessoryData.length-1))
			{
				System.out.println("Please enter a valid range for the Accessory Table");
			}
		} while(orderInput <0 || orderInput >(accessoryData.length-1));
		
		deleteLineInFile("AccessoryData.json",orderInput,1);
		System.out.println("Accessory removed");
		accessoryData=getJsonFileString("AccessoryData.json");
		System.out.println(showMenu());
		
		
		
	
	}
	
	private static void addPlayerData(String type,int points,double cost) //Manager uses to add player type to a json file
	{
		//gson
		Gson gson = new Gson();
		Player newPlayerType = new playerType(type,points,cost);
		try
		{
		   FileWriter playerFile = new FileWriter("PlayerData.json",true);
		   playerFile.write(gson.toJson(newPlayerType));
		   playerFile.write("\n");
		   playerFile.close();
		   playerData=getJsonFileString("PlayerData.json");
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		System.out.println("PlayerType added");
		System.out.println(showMenu());
		
	}
	
	private static void removePlayerData() //Manager uses this to remove player type from a json file
	{
		Gson gson = new Gson();
		String temp="";
		int orderInput=-1;
		if(playerData.length==0)
		{
			System.out.println("There are no player types to remove");
			return;
		}
		System.out.println(showMenu());
		System.out.println("Which Player Type would you like to remove? ");
		System.out.println("Select a Player Type 0-" + (playerData.length-1));
		do
		{
			try
			{
				temp=input.nextLine();
				orderInput=Integer.parseInt(temp);
			}
			catch(NumberFormatException e)
			{
				System.out.println("Wrong Input");
			}
			if(orderInput <0 || orderInput >(playerData.length-1))
			{
				System.out.println("Please enter a valid range for the PlayerType Table");
			}
		} while(orderInput <0 || orderInput >(playerData.length-1));
		
		deleteLineInFile("PlayerData.json",orderInput,2);
		System.out.println("Player Type removed");
		playerData=getJsonFileString("PlayerData.json");
		System.out.println(showMenu());
		
	}
	
	private static int findJsonFile(String fileName,File wantedFile) //1 if found 0 if not(same directory as java file only)
	{ 
		File reportFile = new File(fileName);
		if(reportFile.exists())
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}
	

	public static void deleteLineInFile(String fileName,int linetoRemove,int accOrPlayer) //removing a json object(line) from a file for the remove methods
	{
		//accOrPlayer, 1 = accessory, 2 = player
		Gson gson = new Gson();
		File oldFile = new File(fileName);
		int length = -1;
		try
		{
			FileWriter fw = new FileWriter(fileName);
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			
			if(accOrPlayer==1)
			{
			length = accessoryData.length;
			}
			else if (accOrPlayer ==2)
			{
			length = playerData.length;	
			}
			
			for(int i = 0; i < length;i++) //loop through each line in the file
			{
				
				if(i != linetoRemove)
				{
					if(accOrPlayer==1)
					{
					CustomAcc readAcc = gson.fromJson(accessoryData[i],CustomAcc.class);
					fw.write(gson.toJson(readAcc));
					fw.write("\n");
					}
					else if(accOrPlayer ==2)
					{
						Player newPlayer = gson.fromJson(playerData[i],playerType.class);
						fw.write(gson.toJson(newPlayer));
						fw.write("\n");
					}
				}
			}
			

			fr.close();
			fw.close();
			br.close();

			
			
		}
		catch(FileNotFoundException e)
		{
		System.out.println("deleteLineInFile Error");
		}
		catch(IOException e)
		{
			System.out.println("deleteLineInFile Error");
		}
	}
	
	public static void overwriteFile(String fileName,int accOrPlayer,String overWriteFileName) //removing a json object(line) from a file for the remove methods
	{
		//accOrPlayer, 1 = accessory, 2 = player
		//For manager reading to overwrite with a new json file, the accesorydata or playerdata json files
		Gson gson = new Gson();
		File oldFile = new File(fileName);
		int length = -1;
		String[] overWriteFileString = getJsonFileString(overWriteFileName);
		try
		{
			FileWriter fw = new FileWriter(fileName);
			FileReader fr = new FileReader(overWriteFileName);
			BufferedReader br = new BufferedReader(fr);
			
			for(int i = 0; i < overWriteFileString.length;i++) //loop through each line in the file
			{
				
				
					if(accOrPlayer==1)
					{
					CustomAcc readAcc = gson.fromJson(overWriteFileString[i],CustomAcc.class);
					fw.write(gson.toJson(readAcc));
					fw.write("\n");
					}
					else if(accOrPlayer ==2)
					{
						Player newPlayer = gson.fromJson(overWriteFileString[i],playerType.class);
						fw.write(gson.toJson(newPlayer));
						fw.write("\n");
					}
				
			}
			

			fr.close();
			fw.close();
			br.close();
			System.out.println("New data loaded: File overwritten");
			
			
		}
		catch(FileNotFoundException e)
		{
		System.out.println("File Not Found.");
		}
		catch(IOException e)
		{
			System.out.println("deleteLineInFile Error");
		}
	}
	
	
	
	
	
	
	public static String[] getJsonFileString(String fileName) //given json file name, will return a string array of each line of that json file(can then use fromJson(*))
	{
		Gson gson = new Gson();
		String[] stringArr = null;
		try
		{
		BufferedReader in = new BufferedReader(new FileReader(fileName));
		String str;
		List<String> list = new ArrayList<String>();
		while((str = in.readLine()) != null)  //Reading file line by line, putting into string array, using arraylist prior(resizable)
		{
		list.add(str);
		}
		stringArr = list.toArray(new String[0]);
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File not found");
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return stringArr;
	}
	
	
}


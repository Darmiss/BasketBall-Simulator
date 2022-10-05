
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
	static String[] reportData;
	static String customerName; //customer name for a session
	static boolean  readInPlayerData=false;//No files loaded on game begin
	static boolean  readInAccessoryData=false;
	static String accesoryFileName;
	static String playerDataFileName;
    public static void main(String[]args) throws  InterruptedException //throws for the timeUnit.Seconds
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
		
	  System.out.println("BASKETBALL SIMULATOR V0.00001, by: Lucas Mazur & Austin McCready");
	  TimeUnit.SECONDS.sleep(1);
	  
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
			   System.out.println("Please enter you name: ");
			   customerName=input.nextLine();
		   }
		   //User
		   userMethod();
	   }
	   }while(intInput!=3);
	   
	   
	   
	   
/*
	try {
         FileWriter file = new FileWriter("OriginalAccessories.json");
         file.write(gson.toJson(wow));
         file.close();
      } catch (IOException e) {
         e.printStackTrace();
      }
	*/  
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
			"Press 4 to add Accessory, Press 5 to remove Accesory, Press 6 to add PlayerData, " + "\n" +"Press 7 to remove PlayerData,Press 8 to go back."); //menu options
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
				readAccessoryFile();
			}
			else if(managerInt==3)
			{
				readPlayerDataFile();
			}
			else if(managerInt==4)
			{
			   addAccessory();
			}
			else if(managerInt==5)
			{
				removeAccesory();
			}
			else if(managerInt==6)
			{
				addPlayerData();
			}
			else
			{
				removePlayerData();
			}
			
			
			
			
			if(managerInt != 1 && managerInt !=2)
			{
				if(managerInt!=8)
				{
				System.out.println("Error: Wrong input");
				}
			}
		}
		while(managerInt!=8);
		
	}
	
	private static String showMenu() //will print the menu(each time the menu changes)
	{
		String menu ="";
		if (playerData==null && accessoryData==null)
		{
			return "No accessory and no player data, please load in an json file,No menu displayed"; 
		}
		else if (accessoryData==null)
		{
			menu+= "No accessory data, please load an accesory json file";  //can order players with no accessories
		}
		else if(playerData==null )
		{
			return "No player data, please load in an player Data json file";
		}
		
		//if hits here, has atleast player Data to display
		
		
		return menu;
	}
	
	private static void makeAnOrder() //Users uses this to order players, will also print total cost/points per game
	{
		//make report in the json file,customerName is defined when here
		//Decorator
		//Player newPlayer = new Player(new Acc)
		addReport();
	}
	
	
	private static void readAccessoryFile() //Manager uses to read in a accessory file
	{
		//gson
		readInAccessoryData=true;
		accesoryFileName="";
	}
	
	private static void readPlayerDataFile() //Manager uses to read in a player file
	{
		//gson
		readInPlayerData=true;
		playerDataFileName="";
	}
	
	private static void printReport() //Will print report of orders made(sorted by latest date)
	{
		try
		{
		TimeUnit.SECONDS.sleep(1);
		}catch(InterruptedException e)
		{
			e.printStackTrace();
		}
		//Report file is named report.json
	    //might be blank, but will exist
		Gson gson = new Gson();
		try
		{
		BufferedReader in = new BufferedReader(new FileReader("report.json"));
		String str;
		List<String> list = new ArrayList<String>();
		while((str = in.readLine()) != null)  //Reading file line by line, putting into string array, using arraylist prior(resizable)
		{
		list.add(str);
		}
		String[] stringArr = list.toArray(new String[0]);
		if(stringArr.length==0)
		{
			System.out.println("No reports present ");
		}
		for(int i=stringArr.length-1;i>=0;i--)
		{
		System.out.println(gson.fromJson(stringArr[i],Report.class));
		}
		System.out.println();
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
	
	private static void addReport() //Invoked when an order is made
	{
		Date date = new Date();
		Report newReport = new Report(date.toString(),customerName,"15 blah blah blah",25.50,48.50);
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
	
	private static void addAccessory() //Manager uses to append accesory onto json files
	{
		//gson
	}
	
	private static void removeAccesory() //Manager uses to remove accessory from json files
	{
		if(readInAccessoryData==false)
		{
			return;
		}
		//gson
	}
	
	private static void addPlayerData() //Manager uses to add player type to a json file
	{
		//gson
	}
	
	private static void removePlayerData() //Manager uses this to remove player type from a json file
	{
		if(readInPlayerData==false)
		{
			return;
		}
		//gson
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
	
}


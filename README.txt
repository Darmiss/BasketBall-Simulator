TO COMPILE:
1.)cmd, cd to java files directory
2.) javac -cp json-simple-1.1.1.jar *.java

TO RUN:
java -cp json-simple-1.1.1.jar;. BasketBall.java
(semicolon and period after .jar, otherwise will not find other class files)


SourceFiles, contains the files for the project i.e 45 year old, and accessories i.e WristBand,
copy and paste into the main directory and they will be automatically used, otherwise blank playerdata
and accesory data will be made, which you can then manually(using manager) add new types of players and accessories, you can manually load them using read in manager(manager and playerdata json files)



-Example of decorator pattern:
//Player oldMan = new playerType("Old man 5'2 whatever", 52(points),23.20(cost))
//TO MAKE ACCESSORIES
//CustomAcc WristBand = new CustomAcc("WristBand",23(points),12.20(cost))
//CustomAcc WristBand = new CustomAcc("WristBand",23(points),12.20(cost))
//CustomAcc WristBand = new CustomAcc("WristBand",23(points),12.20(cost))
//CustomAcc WristBand = new CustomAcc("WristBand",23(points),12.20(cost))
//TO ADD THEM TO PLAYER
//oldMan = new playerAcc(oldMan,WristBand);
//oldMan = new playerAcc(oldMan,WristBand);
//oldMan = new playerAcc(oldMan,WristBand);
//TO PRINT
//System.out.println(oldMan.toString())
	
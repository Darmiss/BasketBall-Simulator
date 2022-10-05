TO COMPILE:
1.)cmd, cd to java files directory
2.) javac -cp json-simple-1.1.1.jar *.java

TO RUN:
java -cp json-simple-1.1.1.jar;. BasketBall.java
(semicolon and period after .jar, otherwise will not find other class files)

/HOW TO USE,
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
		//System.out.println(oldMan.getDesc())
		//System.out.println(oldMan.getPoints())
		//System.out.println(oldMan.getCost())

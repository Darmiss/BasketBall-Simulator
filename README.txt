TO COMPILE:
1.)cmd, cd to java files directory
2.) javac -cp json-simple-1.1.1.jar *.java

TO RUN:
java -cp json-simple-1.1.1.jar;. BasketBall.java
(semicolon and period after .jar, otherwise will not find other class files)


*****NOTE: Each session(java run) of the program requires you to read in a playerdata json file and a accessorydata json file that are in the same directory as the java file,(notice how the menu is empty if this is not done)****
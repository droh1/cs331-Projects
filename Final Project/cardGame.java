//package finalProjct;

/*
 * Driver class
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Random;

/**
 * Constructor 
 * @author Daniel Roh
 * Drive class, used to run the game x times
 */
public class cardGame{
	public static void main(String[] args){
		
		File filee = new File("output.txt");
		filee.delete(); //Delete output file if it exists
		
		//File files = new File("output.txt");
		
		Random rand = new Random();
		int times = rand.nextInt(40) + 1; //Gets a random from 1 to 40;
		
		System.out.println("Game will be run " + times + " times"); //DEBUG
		
		//Check for output file.txt, if it exists, delete it because it will be
		//made inside of game		
		
		for(int x = 0; x < times; x++){ //Run the games for x times
			System.out.println("START OF ROUND " + (x+1)); //Used to show program is still running
			game round = new game();
			System.out.println("END OF ROUND"); //Used to show program is still running
			round = null; //Delete the last round 
		}
			
			PrintWriter printer = null; 
			try{ //Open a output file
				File file = new File ("output.txt");
				FileWriter wr = new FileWriter(file, true);
				printer = new PrintWriter(wr);
			}catch(IOException e){
				System.out.println("Output.txt cant be made");
			}
		System.out.println("End of game");
		printer.println("End of game");
		printer.close();
	}
}



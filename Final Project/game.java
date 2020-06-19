//package finalProjct;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/*
 * game class, runs the game
 */

public class game {

	private ArrayList<cards> deck = new ArrayList<cards>();
	private ArrayList<cards> player1Mem = new ArrayList<cards>();
	private ArrayList<cards> player2Mem = new ArrayList<cards>();
	
	/**
	 * Constructor
	 * -Runs the game
	 */
	public game(){
		player player1 = new player(0);
		player player2 = new player(1);
		
		Scanner scan = null;
		File file1 = new File("deck.txt"); //Open file for reading
		try {
			scan = new Scanner(file1);
		} catch (FileNotFoundException e) {
			System.out.println("deck.txt has not been found, please find and re-run program");
		} 
				
		//int pl = 0;
		while(scan.hasNextLine()){ //Scans file untill EOF
			String line = scan.nextLine(); //Reads the file
			
			deck.add(new cards(line)); //Make a new card and add to deck
		}
		
		Random rands = new Random();
		Collections.shuffle(deck, new Random()); //Shuffle deck before start of game
		
		
		PrintWriter printer = null; 
		try{ //Open a output file
			File file = new File ("output.txt");
			FileWriter wr = new FileWriter(file, true);
			printer = new PrintWriter(wr);
		}catch(IOException e){
			System.out.println("Output.txt cant be made");
		}
		
		printer.println("Start Round");
		printer.close();
		
		int completedMatches = 0;
		while(completedMatches != 25){	//Once 25 matches have been completed ((52/2)-1=25 minus 1 because of 0 index)
			/*==========================
			 * 	Start of player 1 turn
			 * 	Uses double random algo
			 *=========================*/
			boolean playerOneTurn = true;
			while(playerOneTurn != false){ //Player one turn
				if(completedMatches == 25){
					break; //If the game is already over
				}
				
				PrintWriter printerx = null; 
				try{ //Open a output file
					File file = new File ("output.txt");
					FileWriter wr = new FileWriter(file, true);
					printerx = new PrintWriter(wr);
				}catch(IOException e){
					System.out.println("Output.txt cant be made");
				}
				//System.out.println("Player 1 turn");
				printerx.println("Player 1 turn");
				printerx.close();
				
				int pos1 = 0;
				int pos2 = 0;
				boolean randcheck = false;
				while(randcheck == false){ //While the proper random is not found
					pos1 = ThreadLocalRandom.current().nextInt(0, deck.size()); //Find a new random
					//pos1 = rands.nextInt(deck.size()-1);
					
					if(deck.get(pos1).getMatched() == false){ //Check if the random has been matched
						deck.get(pos1).setFlipped();
						randcheck = true;
					}
				}
				
				if(added(player1, pos1) == false){ //If the item was not found in the memory
					player1Mem.add(deck.get(pos1)); //Add card to memory
				}
				
				//Check in memory
				boolean memcheck = false;
				for(int p = 0; p < player1Mem.size(); p++){ //Check memory for card
					if(pos1 != p){ //If the card is not the same
						if(deck.get(p).getMatched() == false){ //If the card was not already matched
							if(cardCompare(pos1, p) == true){//If a compariable card is found
								pos2 = p;
								memcheck = true;
								break;
							}
						}
					}
				}
				
				if(memcheck == false){ //If memory did not find a card, pick a random card
					while(randcheck == false){ //While the proper random is not found
						pos2 = ThreadLocalRandom.current().nextInt(0, deck.size()); //Find a new random
						//pos2 = rands.nextInt(deck.size()-1);
						if(deck.get(pos2).getMatched() == false){ //Check if the random has been matched
							deck.get(pos1).setFlipped();
							randcheck = true;
						}
					}
					
					if(added(player1, pos2) == false){ //If the item was not in memory
						player1Mem.add(deck.get(pos2));
					}
				
				}
	
				boolean checkMatch = cardCompare(pos1, pos2);
				if(checkMatch == true){ //check if the cards matched
					completedMatches++;
					player1.setMatches(); //DEBUGGING
					deck.get(pos1).setMatched();
					deck.get(pos2).setMatched();
					printOut(player1, pos1, pos2);
					playerOneTurn = true; //Player gets new turn
				}else{
					playerOneTurn = false; //Turn is over
				}
			} //End of player 1 turn
			
			
			/*============================
			 * 	Start of player 2 turn
			 *	Uses the double linear algo
			  ============================ */	
			boolean playerTwoTurn = true;
			while(playerTwoTurn != false){
				if(completedMatches == 25){ //If the game is already over
					break;
				}
				
				PrintWriter printerx = null; 
				try{ //Open a output file
					File file = new File ("output.txt");
					FileWriter wr = new FileWriter(file, true);
					printerx = new PrintWriter(wr);
				}catch(IOException e){
					System.out.println("Output.txt cant be made");
				}
				//System.out.println("Player 2 turn");
				printerx.println("Player 2 turn");
				printerx.close();
				
				int loc1 = 0, loc2 = 0;
				
				boolean inmem = false;
				
				for(int x = 0; x < 52; x++){
					if(deck.get(x).getMatched() == false){ //If the item has not been matched
						if(inmem = added(player2, x) == false){ //If the item is not in memory
							deck.get(x).setFlipped();
							loc1 = x; 
							player2Mem.add(deck.get(x)); //Add to memory
							break;
						}
					}
				}
				
				boolean playerMemcheck = false;
				for(int x = 0; x < player2Mem.size(); x++){
					if(loc1 != x){ //If the card is not the same
						if(deck.get(x).getMatched() == false){ //If the card is not matched
							if(cardCompare(loc1, x) == true){ //If a match is found
								loc2 = x;
								playerMemcheck = true;
								break;
							}
						}
					}
				}
				
				if(playerMemcheck == false){
					for(int x = 0; x < 52; x++){ //Going backwords
						if(deck.get(x).getMatched() == false){
							if(loc1 != x){
								deck.get(x).setFlipped();
								loc2 = x;
								break;
							}
						}
					}
					if(added(player2, loc2) == false){
						player2Mem.add(deck.get(loc2));
					}
				}
				
				boolean checkcheck = cardCompare(loc1, loc2);
				if(checkcheck == true){
					completedMatches++;
					player2.setMatches(); //DEBUGGING
					deck.get(loc1).setMatched();
					deck.get(loc2).setMatched();
					printOut(player2, loc1, loc2);
					playerTwoTurn = true;
				}else{
					playerTwoTurn = false;
				}
		
			}

		}
		
		PrintWriter printers = null; 
		try{ //Open a output file
			File file = new File ("output.txt");
			FileWriter wr = new FileWriter(file, true);
			printers = new PrintWriter(wr);
		}catch(IOException e){
			System.out.println("Output.txt cant be made");
		}
		
		if(player1.getMatches() == player2.getMatches()){
			System.out.println("Player 1 tied with Player 2");
			printers.println("Player 1 tied with Player2");
		}
		else if(player1.getMatches() > player2.getMatches()){
			System.out.println("Player 1 wins");
			printers.println("Player 1 Wins");
		}else{
			System.out.println("Player 2 wins");
			printers.println("Player 2 Wins");
		}
		
		//System.out.println("player1: " + player1.getMatches()); //DEBUG
		//System.out.println("player2: " + player2.getMatches()); //DEBUG
		printers.close(); //Close file
	}
	

	
	/**
	 * added
	 * @param person player to check
	 * @param pos posision of the deck to check
	 * @return a boolean value
	 */
	public boolean added(player person, int pos){
		boolean test = false;
		if(person.pickCard() == 0){
			for(int p = 0; p < (player1Mem.size()); p++){ //Check memory if this item was already added
				if(deck.get(pos).getCard() == deck.get(p).getCard()){ //Check if a sim card is found
					test = true;
					break;
				}
			}
		}else{
			for(int p = 0; p < (player2Mem.size()); p++){ //Check memory if this item was already added
				if(deck.get(pos).getCard() == deck.get(p).getCard()){ //Check if a sim card is found
					test = true;
					break;
				}
			}
		}
		return test;
	}
	
	/**
	 * cardCompare
	 * @param pos1 location of card 1
	 * @param pos2 location of card 2
	 * @return A boolean if a match is found
	 */
	public boolean cardCompare(int pos1, int pos2){
		if(pos1 == pos2){ //If you happen to pick the same card
			return false;
		}
		char aSuite = deck.get(pos1).getSuite(); //Card one
		int aValue =  deck.get(pos1).getValue();
		
		char bSuite = deck.get(pos2).getSuite(); //Card two
		int bValue = deck.get(pos2).getValue();
		
		//System.out.println("Card compare: (" + aSuite + aValue + ")   (" + bSuite + bValue); //DEBUG
		//Cases can be Clubs, Shapads, Hearts, Dimonds
		// c = s
		// d = h
		switch(aSuite){
		case 'C': 
			if(bSuite == 'S' && aValue == bValue){
				//System.out.println("TRUE");
				return true;
			}
			break;
			
		case 'S':
			if(bSuite == 'C' && aValue == bValue){
				//System.out.println("TRUE");
				return true;
			}
			break;
			
		case 'H':
			if(bSuite == 'D' && aValue == bValue){
				//System.out.println("TRUE");
				return true;
			}
			break;
			
		case 'D':
			if(bSuite == 'H' && aValue == bValue){
				//System.out.println("TRUE");
				return true;
			}
			break; //Not nessasarly needed because end of switch
		}
		
		PrintWriter printers = null; 
		try{ //Open a output file
			File file = new File ("output.txt");
			FileWriter wr = new FileWriter(file, true);
			printers = new PrintWriter(wr);
		}catch(IOException e){
			System.out.println("Output.txt cant be made in compare");
		}
		printers.println("No match found");
		printers.close(); 
		return false; //If a match is not found;
	}
	
	/**
	 * printOut
	 * @param t player to check
	 * @param pos1 position of card 1
	 * @param pos2 posision of card 2
	 */
	public void printOut(player t, int pos1, int pos2){
		//Open file
		
		PrintWriter printer = null; 
		try{ //Open a output file
			File file = new File ("output.txt");
			FileWriter wr = new FileWriter(file, true);
			printer = new PrintWriter(wr);
		}catch(IOException e){
			System.out.println("Output.txt cant be made");
		}
		
		if(t.pickCard() == 0){
			System.out.println("Player 1 matched "+ deck.get(pos1).getCard() + " with " + deck.get(pos2).getCard());
			printer.println("Player 1 matched "+ deck.get(pos1).getCard() + " with " + deck.get(pos2).getCard());
		}else{
			System.out.println("Player 2 matched "+ deck.get(pos1).getCard() + " with " + deck.get(pos2).getCard());
			printer.println("Player 2 matched "+ deck.get(pos1).getCard() + " with " + deck.get(pos2).getCard());
		}
		
		//Print in 4 rows and 13 cols
		for(int x = 0; x < deck.size(); x++){
			if(x != 0){
				if(x % 13 == 0){
					System.out.print("\n");
					printer.print("\n");
				}
			}
			
			if(deck.get(x).getMatched() == false && deck.get(x).getFlipped() == false){
				System.out.print(" OO ");
				printer.print(" OO ");
			}else if(deck.get(x).getMatched() == false && deck.get(x).getFlipped() == true){
				System.out.print(" " + deck.get(x).getCard() + " ");
				printer.print(" " + deck.get(x).getCard() + " ");
			}else{
				System.out.print(" XX ");
				printer.print(" XX ");
			}
			
		
		}
		
		System.out.println(); //Formatting
		printer.println();
		
		printer.close();	
		
	}
}

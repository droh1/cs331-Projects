
//package finalProjct;
/*
 * player class
 */

public class player extends cardGame {

	private int won;
	private int streak;
	private int matches;
	private int algo;
	
	/**
	 * Constructor
	 * @param t To tell the diffrence between players
	 */
	public player(int t){
		won = 0;
		streak = 0;
		matches = 0;
		if(t == 0){
			algo = 0;
		}else{
			algo = 1;
		}
	}
	
	/**
	 * pickCard
	 * @return returns the player type
	 */
	public int pickCard(){
		return algo;
	}
	
	/**
	 * If the player won the game
	 */
	public void wonGame(){
		won++; //Player won a game
	}
	
	/**
	 * Streak
	 * @param x streak of current player
	 * NOTE - this function is used for debugging
	 */
	public void streak(int x){
		if(x > streak){ //If the new streak is more then current
			streak = x;
		}
	}
	
	/**
	 * Updates the amount of matches made
	 */
	public void setMatches(){
		matches++; //Player made a match
	}
	
	
	/**
	 * getWin
	 * @return returns the wins of the player
	 */
	public int getWin(){
		return won;
	}
	
	/**
	 * getStreak
	 * @return returns the streak of the player
	 */
	public int getStreak(){
		return streak;
	}
	
	/**
	 * getMatches
	 * @return returns the matches made by the player
	 */
	public int getMatches(){
		return matches;
	}
	
	
}

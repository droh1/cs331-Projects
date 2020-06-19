//package finalProjct;
/*
 * card class
 */

public class cards {
	private char suite;
	private char value;
	private String card;
	private Boolean flipped;
	private Boolean matched;
	
	/**
	 * Constructor
	 * @param x String of the card
	 */
	public cards(String x){
		value = x.charAt(0);
		suite = x.charAt(1);
		card = x;
		flipped = false;
		matched = false;
	}
	
	/**
	 * getCard
	 * @return returns card string
	 */
	public String getCard(){
		return card;
	}
	
	/**
	 * getSuite
	 * @return returns Suite of card
	 */
	public char getSuite(){
		return suite;
	}
	
	/**
	 * getValue
	 * @return returns the value of the card
	 */
	public char getValue(){
		return value;
	}
	
	/**
	 * Sets the card to flipped
	 */
	public void setFlipped(){
		flipped = true;
	}
	
	/**
	 * Sets the card to matched
	 */
	public void setMatched(){
		matched = true;
	}
	
	/**
	 * getFlipped
	 * @return returns the flipped status
	 */
	public Boolean getFlipped(){
		return flipped;
	}
	
	/**
	 * getMatched
	 * @return returns the matched status
	 */
	public Boolean getMatched(){
		return matched;
	}
	
	
}
